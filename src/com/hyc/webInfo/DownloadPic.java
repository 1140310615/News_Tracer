package com.hyc.webInfo;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.net.URLConnection;

/*
 * ˵����
 * out_file_name:���ص���ͼƬ�����֣�Ҳ���������·�������·����
 * �ṩ��ӦͼƬ�ĵ�ַ���������ض�Ӧ��ͼƬ
 */
class DownloadPic
{
	private String out_file_name;
	public DownloadPic(String out_file_name)
	{
		this.out_file_name = out_file_name.endsWith(".jpg")?out_file_name:out_file_name+".jpg";
	}
	public void getPic(String url)
	{
		final int down_thread_num = 4;
		InputStream[] isArr = new InputStream[down_thread_num];
		RandomAccessFile[] outArr = new RandomAccessFile[down_thread_num];
		try
		{
			//����һ��url����
			URL realurl = new URL(url);
			isArr[0] = realurl.openStream();
			long fileLen = getFileLength(realurl);
			//������ļ���������һ��RandomAccessFile�����
			outArr[0] = new RandomAccessFile(out_file_name,"rw");
			//����һ����������Դ��С��ͬ�Ŀ��ļ�
			for (int i = 0;i < fileLen;i++)
			{
				outArr[0].write(0);
			}
			//ÿ���߳�Ӧ�����ص��ֽ���
			long numPerThread = fileLen / down_thread_num;
			//����������Դ������ʣ�������
			long left = fileLen % down_thread_num;
			for (int i = 0;i < down_thread_num; i++)
			{
				//Ϊÿ���̴߳�һ����������һ��RandomAccessFile������ÿ���̷ֱ߳���������Դ�Ĳ�ͬ����
				if (i != 0)
				{
					isArr[i] = realurl.openStream();
					outArr[i] = new RandomAccessFile(out_file_name,"rw");
				}
				//�ֱ���������߳�������������Դ
				if (i == down_thread_num - 1)
				{
					new DownThread(i * numPerThread,(i+1)*numPerThread+left,isArr[i],outArr[i]).start();
				}
				else
				{
					//ÿ���̸߳�������һ�����ֽ�
					new DownThread(i * numPerThread,(i+1)*numPerThread,isArr[i],outArr[i]).start();
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	//���Զ����Դ�Ĵ�С	public static long getFileLength(URL url) throws Exception
	public static long getFileLength(URL url) throws Exception
	{
		URLConnection con = url.openConnection();
		return con.getContentLength();
	}
	//�ڲ��࣬��������ͼƬ���߳�
	private class DownThread extends Thread
	{
		private final int buff_len = 32;      //�����ֽڳ���
		private long start;                   //�������ص���ʼ��
		private long end;                     //�������صĽ�����
		private InputStream is;               //������Դ��Ӧ��������
		private RandomAccessFile raf;         //�����ص�����Դ���뵽raf��
		public DownThread(long start,long end,InputStream is,RandomAccessFile raf)
		{
			this.start = start;
			this.end = end;
			this.is = is;
			this.raf = raf;
		}
		public void run()
		{
			try
			{
				is.skip(start);     //��¼ָ����ǰ�ƶ�start���ַ�
				raf.seek(start);
				byte[] buff = new byte[buff_len];
				long contentLen = end - start;
				long times = contentLen/buff_len + 4;
				int hasRead = 0;
				for (int i = 0; i < times; i++)
				{
					hasRead = is.read(buff);
					if (hasRead < 0)
					{
						break;
					}
					raf.write(buff, 0, hasRead);
				}
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
			finally
			{
				try
				{
					if (is != null)
					{
						is.close();
					}
					if (raf != null)
					{
						raf.close();
					}
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
			}
		}
	}
}