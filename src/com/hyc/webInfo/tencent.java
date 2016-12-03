package com.hyc.webInfo;

import java.util.ArrayList;
import java.util.List;

public class tencent
{
	private final tuple5 mili = new tuple5(classifyName.mili,"http://mil.qq.com/mil_index.htm","","","");
	private final tuple5 inter= new tuple5(classifyName.inter,"http://news.qq.com/world_index.shtml","","","");
	private final tuple5 soc  = new tuple5(classifyName.society,"http://news.qq.com/society_index.shtml","","","");
	
	
	public List<String> miliList = new ArrayList<String>();
	public List<String> interList= new ArrayList<String>();
	public List<String> socList  = new ArrayList<String>();
	
	public void getUrl() throws InterruptedException
	{
		crawler cra = new crawler();
		miliList = crawler.getUrl(this.mili.getUrl());
		Thread.sleep(2000);
		interList= crawler.getUrl(this.inter.getUrl());
		Thread.sleep(2000);
		//socList  = crawler.getUrl(this.soc.getUrl());
		Thread.sleep(2000);
	}
	
}
