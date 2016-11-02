package com.hyc.webInfo;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;

import com.hyc.news.newsVo;


public class crawler 
{
	public static List<String> getUrl(String url)
	{
		List<String> urlList = new ArrayList<String>();
		try
		{
			Parser parser = getParser(url);     //鏋勯�犺В鏋愬櫒
			NodeFilter nodefilter = null;
			nodefilter = new NodeClassFilter(LinkTag.class);
			NodeList nodelist = parser.extractAllNodesThatMatch(nodefilter);
			for (int i = 0;i < nodelist.size();i++)
			{
				Node node = nodelist.elementAt(i);
				String strUrl = node.getText();
				if (strUrl.indexOf("slide")==-1 && strUrl.indexOf("roll")==-1 && strUrl.indexOf("comment")==-1 && strUrl.indexOf("http")!=-1 && (strUrl.indexOf("news")!=-1 || strUrl.indexOf("mil")!=-1 || strUrl.indexOf("tech")!=-1 || strUrl.indexOf("ent")!=-1 || strUrl.indexOf("sports")!=-1 || strUrl.indexOf("blog")!=-1))
				{
					int start = strUrl.indexOf("http");
					strUrl = strUrl.substring(start);
					int end = strUrl.indexOf("\"");
					if (end == -1)
						end = strUrl.indexOf(" ");
					strUrl = strUrl.substring(0, end);
					if ((strUrl.indexOf("php") != -1 && strUrl.indexOf("?")==-1) || (strUrl.indexOf("thread")!=-1 && strUrl.indexOf("viewthread")==-1))
						continue;
					if (strUrl.indexOf("list")!=-1 || strUrl.indexOf("video")!=-1)
						continue;
					urlList.add(strUrl);
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
		if (urlList != null && urlList.size()>200)
		{
			Comparator<String> comparator = new Comparator<String>(){
				@Override
				public int compare(String o1, String o2) {
					// TODO Auto-generated method stub
					return o2.length() - o1.length();
				}
			};
			urlList.sort(comparator);
			urlList = urlList.subList(10, 60);
		}
		return urlList;
	}
	
	public String getKeywords(String url)
	{
		String keywords = "";
		try
		{
			Parser parser = getParser(url);
			NodeFilter keyFilter = new AndFilter(new TagNameFilter("p"),new HasAttributeFilter("class","art_keywords"));
			NodeList keyList = parser.extractAllNodesThatMatch(keyFilter);
			if (keyList.size() == 0)
				return "";
			else
			{
				Node node = keyList.elementAt(0);
				NodeList children = node.getChildren();
				for (int i = 0;i < children.size();i++)
				{
					Node child = children.elementAt(i);
					if (child instanceof LinkTag)
					{
						String str = child.toPlainTextString();
						keywords = keywords + str + " ";
					}
				}
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return keywords.trim();
	}
	
	public String getHead(String url)
	{
		String title = "";
		try
		{
			Parser parser = getParser(url);
			NodeFilter titleFilter = new TagNameFilter("title");
			NodeList nodelist = parser.extractAllNodesThatMatch(titleFilter);
			Node titleNode = nodelist.elementAt(0);
			
			title = titleNode.toPlainTextString();
			int[] index = new int[3];
			index[0] = title.indexOf("|");
			index[1] = title.indexOf("_");
			index[2] = title.indexOf("?");
			Arrays.sort(index);
			if (index[2] != -1)
			{
				int i = 0;
				while(index[i] == -1)
					i++;
				title = title.substring(0, index[i]);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return "";
		}
		return title;
	}
	
	public ArrayList<newsVo> getText(String url)
	{
		ArrayList<newsVo> body = new ArrayList<newsVo>();
		try
		{
			Parser parser = getParser(url);
			NodeFilter textFilter = new TagNameFilter("p");
			NodeList nodelist = parser.extractAllNodesThatMatch(textFilter);
			for (int i = 0;i < nodelist.size();i++)
			{
				Node node = nodelist.elementAt(i);
				String str = node.toPlainTextString();
				char s = 12288;
				String ss = String.valueOf(s);
				String temp = str.replaceAll(ss, "");
				if (temp.indexOf("（")==0 && temp.indexOf("）")==(temp.length()-1) && temp.length()<8)
				{
					break;
				}
				if (str.indexOf("更多精彩内容敬请关注")!=-1)
					break;
				if (str.indexOf("欢迎访问")!=-1)
					break;
				if (str.indexOf("标签:")!=-1 || str.indexOf("新浪简介")!=-1 || str.indexOf("文章关键词")!=-1)
					break;
				str = str.replaceAll("&nbsp;", "");
				if (str != null && !str.equals(""))
				{
					newsVo vo = new newsVo();
					vo.setUrl(str);
					body.add(vo);
				}
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
		String quo = this.getQuotation(url);
		if (quo!=null && !quo.equals(""))
			body.remove(0);
		return body;
	}
	
	public String getQuotation(String url)
	{
		String quotation = "";
		try
		{
			Parser parser = getParser(url);
			NodeFilter quoFilter = new AndFilter(new TagNameFilter("div"),new HasAttributeFilter("class","quotation"));
			NodeList nodelist = parser.extractAllNodesThatMatch(quoFilter);
			Node node = nodelist.elementAt(0);
			quotation = node.toPlainTextString();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return "";
		}
		return quotation;
	}
	
	public static Parser getParser(String url) throws IOException
	{
		try
		{
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();
			Parser parser = new Parser(conn);     //鏁版嵁婧�
			parser.setEncoding("utf-8");          //缂栫爜
			return parser;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

}
