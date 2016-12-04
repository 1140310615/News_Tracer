package com.hyc.webInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;

public class sohu 
{
	private final tuple5 mili = new tuple5(classifyName.mili,"http://mil.sohu.com","","","");
	private final tuple5 inter= new tuple5(classifyName.inter,"http://news.sohu.com/guojixinwen.shtml","","","");
	private final tuple5 news  = new tuple5(classifyName.news,"http://news.sohu.com/shehuixinwen.shtml","","","");
	
	
	public List<String> miliList = new ArrayList<String>();
	public List<String> interList= new ArrayList<String>();
	public List<String> newsList  = new ArrayList<String>();
	
	public void getUrl() throws InterruptedException
	{
		miliList = getUrlList(this.mili.getUrl());
		Thread.sleep(2000);
		interList= getUrlList(this.inter.getUrl());
		Thread.sleep(2000);
		//socList  = crawler.getUrl(this.soc.getUrl());
		newsList = getUrlList(this.news.getUrl());
		Thread.sleep(2000);
	}
	public List<String> getUrlList(String url)
	{
		List<String> urlList = new ArrayList<String>();
		try
		{
			Parser parser = crawler.getParser(url);     //鏋勯�犺В鏋愬櫒
			NodeFilter nodefilter = null;
			nodefilter = new NodeClassFilter(LinkTag.class);
			NodeList nodelist = parser.extractAllNodesThatMatch(nodefilter);
			for (int i = 0;i < nodelist.size();i++)
			{
				Node node = nodelist.elementAt(i);
				String strUrl = node.getText();
				if (strUrl.indexOf("history")!=-1)
					continue;
				int start = strUrl.indexOf("http");
				int end   = strUrl.indexOf("html");
				if (end == -1 || start == -1)
					continue;
				else 
					end = end + 4;
				strUrl = strUrl.substring(start, end);
				//System.out.println(strUrl);
				Pattern p = Pattern.compile("http://\\w+[.]\\w+[.]com/\\d\\d\\d\\d\\d\\d\\d\\d/n\\d+.shtml");
				//Pattern p = Pattern.compile("(http://)*");
				Matcher m = p.matcher(strUrl);
				if (m.matches())
					urlList.add(strUrl);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
		return urlList;
	}

}
