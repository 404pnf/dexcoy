<%--
  Licensed to the Apache Software Foundation (ASF) under one or more
  contributor license agreements.  See the NOTICE file distributed with
  this work for additional information regarding copyright ownership.
  The ASF licenses this file to You under the Apache License, Version 2.0
  (the "License"); you may not use this file except in compliance with
  the License.  You may obtain a copy of the License at
  
  http://www.apache.org/licenses/LICENSE-2.0
  
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
--%>
<%@ page 
  session="false"
  contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"

  import="java.io.*"
  import="java.util.*"
  import="java.net.*"
  import="javax.servlet.http.*"
  import="javax.servlet.*"

  import="org.apache.nutch.html.Entities"
  import="org.apache.nutch.metadata.Nutch"
  import="org.apache.nutch.searcher.*"
  import="org.apache.nutch.plugin.*"
  import="org.apache.nutch.clustering.*"
  import="org.apache.hadoop.conf.*"
  import="org.apache.nutch.util.NutchConfiguration"
%><%!
  /**
   * Number of hits to retrieve and cluster if clustering extension is available
   * and clustering is on. By default, 100. Configurable via nutch-conf.xml.
   */
  private int HITS_TO_CLUSTER;

  /**
   * Maximum hits per page to be displayed.
   */
  private int MAX_HITS_PER_PAGE;

  /**
   * An instance of the clustering extension, if available.
   */
  private OnlineClusterer clusterer;
  
  /**
   * Nutch configuration for this servlet.
   */
  private Configuration nutchConf;

  /**
   * Initialize search bean.
   */
  public void jspInit() {
    super.jspInit();
    
    final ServletContext application = getServletContext(); 
    nutchConf = NutchConfiguration.get(application);
	  HITS_TO_CLUSTER = nutchConf.getInt("extension.clustering.hits-to-cluster", 100);
    MAX_HITS_PER_PAGE = nutchConf.getInt("searcher.max.hits.per.page", -1);

    try {
      clusterer = new OnlineClustererFactory(nutchConf).getOnlineClusterer();
    } catch (PluginRuntimeException e) {
      super.log("Could not initialize online clusterer: " + e.toString());
    }
  }
%>

<%--
// Uncomment this to enable query refinement.
// Do the same to "refine-query.jsp" below.,
<%@ include file="./refine-query-init.jsp" %>
--%>

<%
  // The Nutch bean instance is initialized through a ServletContextListener 
  // that is setup in the web.xml file
  NutchBean bean = NutchBean.get(application, nutchConf);
  // set the character encoding to use when interpreting request values 
  request.setCharacterEncoding("UTF-8");

  bean.LOG.info("query request from " + request.getRemoteAddr());

  // get query from request
  String queryString = request.getParameter("query");
  if (queryString == null)
    queryString = "";
  String htmlQueryString = Entities.encode(queryString);
  
  // a flag to make the code cleaner a bit.
  boolean clusteringAvailable = (clusterer != null);

  String clustering = "";
  if (clusteringAvailable && "yes".equals(request.getParameter("clustering")))
    clustering = "yes";

  int start = 0;          // first hit to display
  String startString = request.getParameter("start");
  if (startString != null)
    start = Integer.parseInt(startString);

  int hitsPerPage = 10;          // number of hits to display
  String hitsString = request.getParameter("hitsPerPage");
  if (hitsString != null)
    hitsPerPage = Integer.parseInt(hitsString);
  if(MAX_HITS_PER_PAGE > 0 && hitsPerPage > MAX_HITS_PER_PAGE)
    hitsPerPage = MAX_HITS_PER_PAGE;

  int hitsPerSite = 0;                            // max hits per site
  String hitsPerSiteString = request.getParameter("hitsPerSite");
  if (hitsPerSiteString != null)
    hitsPerSite = Integer.parseInt(hitsPerSiteString);

  String sort = request.getParameter("sort");
  boolean reverse =
    sort!=null && "true".equals(request.getParameter("reverse"));

  String params = "&hitsPerPage="+hitsPerPage
     +(sort==null ? "" : "&sort="+sort+(reverse?"&reverse=true":""));

  int hitsToCluster = HITS_TO_CLUSTER;            // number of hits to cluster

  // get the lang from request
  String queryLang = request.getParameter("lang");
  if (queryLang == null) { queryLang = ""; }
  Query query = Query.parse(queryString, queryLang, nutchConf);
  bean.LOG.info("query: " + queryString);
  bean.LOG.info("lang: " + queryLang);

  String language =
    ResourceBundle.getBundle("org.nutch.jsp.search", request.getLocale())
    .getLocale().getLanguage();
  String requestURI = HttpUtils.getRequestURL(request).toString();
  String base = requestURI.substring(0, requestURI.lastIndexOf('/'));
  String rss = "../opensearch?query="+htmlQueryString
    +"&hitsPerSite="+hitsPerSite+"&lang="+queryLang+params;
%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
  // To prevent the character encoding declared with 'contentType' page
  // directive from being overriden by JSTL (apache i18n), we freeze it
  // by flushing the output buffer. 
  // see http://java.sun.com/developer/technicalArticles/Intl/MultilingualJSP/
  out.flush();
%>
<%@ taglib uri="http://jakarta.apache.org/taglibs/i18n" prefix="i18n" %>
<i18n:bundle baseName="org.nutch.jsp.search"/>
<html lang="<%= language %>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<head>
		<title>Nutch: <i18n:message key="title"/></title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
		<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon"/>
		<link rel="alternate" type="application/rss+xml" title="RSS" href="<%=rss%>"/>
		<jsp:include page="include/style.html"/>
		<base href="<%= base  + "/" + language %>/">
		<script type="text/javascript">
		<!--
		function queryfocus() { document.search.query.focus(); }
		// -->
		</script>
	</head>

	<body onLoad="queryfocus();" style = "margin-left:20px;">

		<jsp:include page="<%= language + \"/include/header.html\"%>"/>

	  <form name="search" action="../search.jsp" method="get">
		 <input name="query"  class = "search_form" value="<%=htmlQueryString%>">
		 <input type="hidden" name="hitsPerPage" value="<%=hitsPerPage%>">
		 <input type="hidden" name="lang" value="<%=language%>">
		 <input type="submit" class = "search_submit" value="<i18n:message key="search"/>">
		 <% if (clusteringAvailable) { %>
			 <input id="clustbox" type="checkbox" name="clustering" value="yes" <% if (clustering.equals("yes")) { %>CHECKED<% } %>>
				<label for="clustbox"><i18n:message key="clustering"/></label>
		 <% } %>
		<!--  <a href="help.html">help</a> -->
	 </form>
<div class = "search_list_left" style = "float:left;">
	<%--
	// Uncomment this to enable query refinement.
	// Do the same to "refine-query-init.jsp" above.
	<%@ include file="./refine-query.jsp" %>
	--%>

	<%
		 // how many hits to retrieve? if clustering is on and available,
		 // take "hitsToCluster", otherwise just get hitsPerPage
		 int hitsToRetrieve = (clusteringAvailable && clustering.equals("yes") ? hitsToCluster : hitsPerPage);

		 if (clusteringAvailable && clustering.equals("yes")) {
			 bean.LOG.info("Clustering is on, hits to retrieve: " + hitsToRetrieve);
		 }

		 // perform query
			// NOTE by Dawid Weiss:
			// The 'clustering' window actually moves with the start
			// position.... this is good, bad?... ugly?....
		 Hits hits;
		 try{
				query.getParams().initFrom(start + hitsToRetrieve, hitsPerSite, "site", sort, reverse);
			 hits = bean.search(query);
		 } catch (IOException e){
			 hits = new Hits(0,new Hit[0]);	
		 }
		 int end = (int)Math.min(hits.getLength(), start + hitsPerPage);
		 int length = end-start;
		 int realEnd = (int)Math.min(hits.getLength(), start + hitsToRetrieve);

		 Hit[] show = hits.getHits(start, realEnd-start);
		 HitDetails[] details = bean.getDetails(show);
		 Summary[] summaries = bean.getSummary(details, query);
		 bean.LOG.info("total hits: " + hits.getTotal());
	%>

	<i18n:message key="hits">
		<i18n:messageArg value="<%=new Long((end==0)?0:(start+1))%>"/>
		<i18n:messageArg value="<%=new Long(end)%>"/>
		<i18n:messageArg value="<%=new Long(hits.getTotal())%>"/>
	</i18n:message>

	<%
	// be responsive
	out.flush();
	%>

	<br><br>

	<% if (clustering.equals("yes") && length != 0) { %>
	<table border=0 cellspacing="3" cellpadding="0">
		<tr>
		<td valign="top">

		<% } %>

		<%

		  Map<String, Hit> moreMap = new HashMap<String, Hit>();
		  List<String> contentList = new ArrayList<String>();
			for (int i = 0; i < length; i++) {      // display the hits
				Hit hit = show[i];
				HitDetails detail = details[i];
				String title = detail.getValue("title");
				String url = detail.getValue("url");
				String id = "idx=" + hit.getIndexNo() + "&id=" + hit.getUniqueKey();
				String summary = summaries[i].toHtml(true);
				String caching = detail.getValue("cache");
				boolean showSummary = true;
				boolean showCached = true;
				if (caching != null) {
					showSummary = !caching.equals(Nutch.CACHING_FORBIDDEN_ALL);
					showCached = !caching.equals(Nutch.CACHING_FORBIDDEN_NONE);
				}
				
				// 除掉重复
				if (contentList.contains(url)) {
					continue;
				} else {
					contentList.add(url);
				}

				if (title == null || title.equals("")) {      // use url for docs w/o title
					title = url;
				}
				%>
		 <div style = "width:35.7em;">
				<b><a href="<%=url%>"><%=Entities.encode(title)%></a></b>
				<%@ include file="more.jsp" %>
				<% if (!"".equals(summary) && showSummary) { %>
				<br><%=summary%>
				<% } %>
				<br>
				<span class="url"><%=Entities.encode(url)%></span>
	   <% if (hit.moreFromDupExcluded()) {
				String more =
				"query="+URLEncoder.encode("site:"+hit.getDedupValue()+" "+queryString, "UTF8")
				+params+"&hitsPerSite="+0
				+"&lang="+queryLang
				+"&clustering="+clustering;
				moreMap.put(more,hit);
				%>
				<% } %>
	
				<br><br>
		 </div>
		<% } %>

		<% if (clustering.equals("yes") && length != 0) { %>
		</td>

		<!-- clusters -->
		<td style="border-right: 1px dotted gray;" />&#160;</td>
		<td align="left" valign="top" width="25%"><%@ include file="cluster.jsp" %></td>
		</tr>
	</table>

	<% } %>
	
	<table><tr><td>
    <%
		if (start > 2 && hits.totalIsExact()) {
			int start1 = 0;
			if (end == hits.getTotal()) {
				start1 = end-hitsPerPage-end%hitsPerPage;
			} else {
				start1 = end-hitsPerPage*2;
			}
			String preUrl = "../search.jsp?"
			+ "query=" + htmlQueryString
			+ "&lang=" + queryLang
			+ "&start=" + start1
			+ "&hitsPerPage=" + hitsPerPage
			+ "&hitsPerSite=" + hitsPerSite
			+ "&clustering=" + clustering;
			 if (sort != null) { 
				 preUrl = preUrl + "&sort=" + sort
					+ "&reverse=" + reverse;
			 }
	%>
	<a href="<%= preUrl %>">上一页</a>
	<%
		}
	%>
</td>

	<% 
		int currentPage = 0;
		int disCount1 = 0;
		int start2 = 0;
		
		if (end%hitsPerPage==0) {
		    currentPage = end/hitsPerPage;
		} else {
		    currentPage = end/hitsPerPage + 1;
		}
		
		if (currentPage > 5) {
			disCount1 = 5;
		} else {
			disCount1 = currentPage - 1;
		}
		for (int i = disCount1; i > 0; i--) {
	%>
 <td>
	<%
		String preUrl = "../search.jsp?"
			+ "query=" + htmlQueryString
			+ "&lang=" + queryLang
			+ "&start=" + (currentPage- i-1)*hitsPerPage
			+ "&hitsPerPage=" + hitsPerPage
			+ "&hitsPerSite=" + hitsPerSite
			+ "&clustering=" + clustering;
			if (sort != null) { 
			   preUrl = preUrl + "&sort=" + sort+ "&reverse=" + reverse;
			 }
	%>
	<a href="<%= preUrl %>">[<%= currentPage - i %>]</a>
</td>
<% } %>

<td style = "color:red"><%= currentPage%></td>
	<%
		int disCount2 = 0;
		long maxPageCount = 0;
		
		if (hits.getTotal()%hitsPerPage==0) {
			maxPageCount = hits.getTotal() / hitsPerPage;
		} else {
			maxPageCount = hits.getTotal() / hitsPerPage + 1;
		}
		if (maxPageCount - currentPage > 5) {
			disCount2 = 5;
		} else {
			disCount2 = Long.valueOf(maxPageCount).intValue() - currentPage;
		}
		for (int i = 1; i <= disCount2; i++) {
	%>
	<td>
		<%
			String preUrl = "../search.jsp?"
				+ "query=" + htmlQueryString
				+ "&lang=" + queryLang
				+ "&start=" + (currentPage+ i -1)*hitsPerPage
				+ "&hitsPerPage=" + hitsPerPage
				+ "&hitsPerSite=" + hitsPerSite
				+ "&clustering=" + clustering;
			 if (sort != null) { 
				 preUrl = preUrl + "&sort=" + sort
					+ "&reverse=" + reverse;
			 }
		%>
		<a href="<%= preUrl %>">[<%= currentPage + i %>]</a>
	</td>
	<% } %>
	<td>
	<%
	if ((hits.totalIsExact() && end < hits.getTotal()) || (!hits.totalIsExact() && (hits.getLength() > start+hitsPerPage))) {
		String preUrl = "../search.jsp?"
			+ "query=" + htmlQueryString
			+ "&lang=" + queryLang
			+ "&start=" + end
			+ "&hitsPerPage=" + hitsPerPage
			+ "&hitsPerSite=" + hitsPerSite
			+ "&clustering=" + clustering;
		 if (sort != null) { 
			 preUrl = preUrl + "&sort=" + sort
				+ "&reverse=" + reverse;
		 }
	%>
	<a href="<%= preUrl %>">下一页</a>
	<%
		}
	%>
	</td></tr></table>
	</div>

	<%
	Map<String, String> hitMap = new HashMap<String, String>();
	hitMap.put("2u4u.2u4u.com.cn","悠游首页");
	hitMap.put("ebook.2u4u.com.cn","电子图书馆");
	hitMap.put("video.2u4u.com.cn","多媒体课堂");
	hitMap.put("quiz.2u4u.com.cn","测试中心");
	hitMap.put("answer.2u4u.com.cn","有问有答");
	hitMap.put("dl.2u4u.com.cn","下载中心");
	hitMap.put("nce.2u4u.com.cn","NCE");
	hitMap.put("french.2u4u.com.cn","法语学习");
	hitMap.put("shop.2u4u.com.cn","悠游商店");
	%>
	<div class = "search_list_right" style = "float:left;margin-left:10%;border-left:1px solid #e1e1e1;padding-left:10px;word-break:break-all;word-wrap:break-word;">	
	<%
		if (!moreMap.isEmpty()) {
	%>
	<b>更多网站信息</b><br><br>	 
	<% for (Map.Entry<String,Hit> moreLink : moreMap.entrySet()) {%>		
	<a href="../search.jsp?<%=moreLink.getKey()%>"><i18n:message key="moreFrom"/>&nbsp;&nbsp;<%=hitMap.get(moreLink.getValue().getDedupValue())%></a><br><br>
	<% } %>
	
	<%
		}
	%>
	</div>
	<p style = "clear:both;">
	<!-- <a href="http://wiki.apache.org/nutch/FAQ">
	<img border="0" src="../img/poweredbynutch_01.gif">
	</a> -->
	<jsp:include page="/include/footer.html"/>
  </p>
	</body>
</html>
