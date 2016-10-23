package com.hyc.webInfo;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.net.URLConnection;

/*
 * 说明：
 * out_file_name:下载到的图片的名字（也可以是相对路径或绝对路径）
 * 提供相应图片的地址，即可下载对应的图片
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
			//创建一个url对象
			URL realurl = new URL(url);
			isArr[0] = realurl.openStream();
			long fileLen = getFileLength(realurl);
			//以输出文件名创建第一个RandomAccessFile输出流
			outArr[0] = new RandomAccessFile(out_file_name,"rw");
			//创建一个与下载资源大小相同的空文件
			for (int i = 0;i < fileLen;i++)
			{
				outArr[0].write(0);
			}
			//每个线程应该下载的字节数
			long numPerThread = fileLen / down_thread_num;
			//整个下载资源整除后剩余的余数
			long left = fileLen % down_thread_num;
			for (int i = 0;i < down_thread_num; i++)
			{
				//为每个线程打开一个输入流、一个RandomAccessFile对象，让每个线程分别负责下载资源的不同部分
				if (i != 0)
				{
					isArr[i] = realurl.openStream();
					outArr[i] = new RandomAccessFile(out_file_name,"rw");
				}
				//分别启动多个线程来下载网络资源
				if (i == down_thread_num - 1)
				{
					new DownThread(i * numPerThread,(i+1)*numPerThread+left,isArr[i],outArr[i]).start();
				}
				else
				{
					//每个线程负责下载一定的字节
					new DownThread(i * numPerThread,(i+1)*numPerThread,isArr[i],outArr[i]).start();
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	//获得远程资源的大小	public static long getFileLength(URL url) throws Exception
	public static long getFileLength(URL url) throws Exception
	{
		URLConnection con = url.openConnection();
		return con.getContentLength();
	}
	//内部类，用来下载图片的线程
	private class DownThread extends Thread
	{
		private final int buff_len = 32;      //定义字节长度
		private long start;                   //定义下载的起始点
		private long end;                     //定义下载的结束点
		private InputStream is;               //下载资源对应的输入流
		private RandomAccessFile raf;         //将下载到的资源输入到raf中
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
				is.skip(start);     //纪录指针向前移动start个字符
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