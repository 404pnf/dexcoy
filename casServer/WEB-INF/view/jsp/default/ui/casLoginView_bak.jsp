<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--
<%@ page import="org.jasig.cas.util.DESCoder"%>
-->
<%--

    Licensed to Jasig under one or more contributor license
    agreements. See the NOTICE file distributed with this work
    for additional information regarding copyright ownership.
    Jasig licenses this file to you under the Apache License,
    Version 2.0 (the "License"); you may not use this file
    except in compliance with the License. You may obtain a
    copy of the License at:

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on
    an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied. See the License for the
    specific language governing permissions and limitations
    under the License.

--%>

<%@ page contentType="text/html; charset=UTF-8" %>	
<div style="display:none;">
<jsp:directive.include file="includes/top.jsp" />
</div></div></div></div>
<div id="div1" style="display:none;">
			<form:form method="post" id="fm1" cssClass="fm-v clearfix" commandName="${commandName}" htmlEscape="true">
			    <form:errors path="*" cssClass="errors" id="status" element="div" />
                <div class="box fl-panel" id="login">
                <!-- <spring:message code="screen.welcome.welcome" /> -->
                    <h2><spring:message code="screen.welcome.instructions" /></h2>
                    <div class="row fl-controls-left">
                        <label for="username" class="fl-label"><spring:message code="screen.welcome.label.netid" /></label>
						<c:if test="${not empty sessionScope.openIdLocalId}">
						<strong>${sessionScope.openIdLocalId}</strong>
						<input type="hidden" id="username" name="username" value="${sessionScope.openIdLocalId}" />
						</c:if>

						<c:if test="${empty sessionScope.openIdLocalId}">
						<spring:message code="screen.welcome.label.netid.accesskey" var="userNameAccessKey" />
						<form:input cssClass="required" cssErrorClass="error" id="username" size="25" tabindex="1" accesskey="${userNameAccessKey}" path="username" autocomplete="false" htmlEscape="true" />
						</c:if>
                    </div>
                    <div class="row fl-controls-left">
                        <label for="password" class="fl-label"><spring:message code="screen.welcome.label.password" /></label>
						<%--
						NOTE: Certain browsers will offer the option of caching passwords for a user.  There is a non-standard attribute,
						"autocomplete" that when set to "off" will tell certain browsers not to prompt to cache credentials.  For more
						information, see the following web page:
						http://www.geocities.com/technofundo/tech/web/ie_autocomplete.html
						--%>
						<spring:message code="screen.welcome.label.password.accesskey" var="passwordAccessKey" />
						<form:password cssClass="required" cssErrorClass="error" id="password" size="25" tabindex="2" path="password"  accesskey="${passwordAccessKey}" htmlEscape="true" autocomplete="off" />
                    </div>
                    <div class="row check">
                        <input id="warn" name="warn" value="true" tabindex="3" accesskey="<spring:message code="screen.welcome.label.warn.accesskey" />" type="checkbox" />
                        <label for="warn"><spring:message code="screen.welcome.label.warn" /></label>
                    </div>
                    <div class="row btn-row">
						<input type="hidden" name="lt" value="${flowExecutionKey}" />
						<input type="hidden" name="_eventId" value="submit" />

                        <input class="btn-submit" id="submit" name="submit" accesskey="l" value="<spring:message code="screen.welcome.button.login" />" tabindex="4" type="submit" />
                        <input class="btn-reset" id="reset" name="reset" accesskey="c" value="<spring:message code="screen.welcome.button.clear" />" tabindex="5" type="reset" />
                    </div>
                </div>
            </form:form>
</div>
<style type="text/css">
.login_body {
	margin: 0 auto;
	width: 853px;
	padding: 50px;
}

.login_content {
	width: 600px;
	margin: 0 auto;
	padding: 0px;
	text-align: center;
}

.login_content h3 {
	border-bottom: 1px solid #CCC;
	padding: 5px;
}
.proccess { 
BACKGROUND: #ffffff; BORDER-BOTTOM: 1px solid; BORDER-LEFT: 1px solid; BORDER-RIGHT: 1px solid; BORDER-TOP: 1px solid; HEIGHT: 15px; MARGIN: 3px; WIDTH: 10px 
} 
.login_body .login_content .content_text {
	font-size: 12px;
	line-height: 20px;
	color: #FF6600;
	height: 60px;
	width: 550px;
	margin-top: 0px;
	margin-right: auto;
	margin-bottom: 0px;
	margin-left: auto;
	border: 1px solid #FF9966;
	background-color: #f2f2f2;
}
</style>
	
<div id="div2" style="display:block;">
<form:form method="post" id="fm1" cssClass="fm-v clearfix" commandName="${commandName}" htmlEscape="true">
<div class="login_body">
<div class="login_content">
<div class='content_text'>
<font size="3">认证中心验证通过，画面自动返回。</font><br>
<font size="3">请稍等... ...</font>
</div></div></div>
 </form:form>
</div>
<div id="div3" style="display:block;">
<form:form method="post" id="fm1" cssClass="fm-v clearfix" commandName="${commandName}" htmlEscape="true">
<div class="login_body">
<div class="login_content">
<div class='content_text'>
<font size="3">认证中心验证失败，您还没有在外研社网站登录。</font><br>
<font size="3">画面自动返回到登录画面。</font><br>
<font size="3">请稍等... ...</font>
</div></div></div>
 </form:form>
</div>
<script type="text/javascript">
	<% 
		String uticket = request.getParameter("uticket");
        //out.println("alert('uticket====' + '"+uticket+"')");
        //String str = DESCoder.decrypt(uticket,key);
        //DES解密
		String username = "";
		String password = "";
		if (uticket != null && uticket.length() > 0) {
        	String[] split_uticket = uticket.split("@");
			username = split_uticket[0];
			if (split_uticket.length == 2) {
				password = split_uticket[1];
			}
        }
    %>
    var tempUsername = "<%=username%>";
    var tempPassword = "<%=password%>";
    if(tempUsername != "" && tempPassword != ""){
    	document.getElementById("div1").style.display="none";//隐藏
        document.getElementById("div2").style.display="block";//显示
        document.getElementById("div3").style.display="none";//隐藏
		//document.forms["fm1"].username.value = "<%=username%>";
		//document.forms["fm1"].password.value = "<%=password%>";
    	document.getElementById("username").value = "<%=username%>";
        document.getElementById("password").value = "<%=password%>";
		var statusObj = document.getElementById("status");
		if (statusObj == null) {
			//document.forms["fm1"].submit.click();
	        document.getElementById("submit").click();
		} else {
			<% 
				String service = request.getParameter("service");
				if (service != null && service.length() > 0) {
					int n = service.indexOf('?');
			        if(n > 0){
						service = service + "&errorid=UsernameOrPasswordInvalid";
					}else{
						service = service + "?errorid=UsernameOrPasswordInvalid";
				    }
		    	}
			%>
			window.location.href = "<%=service%>";
		}
    } else {
    	document.getElementById("div1").style.display="none";//隐藏
        document.getElementById("div2").style.display="none";//隐藏
        document.getElementById("div3").style.display="block";//显示
        var tempService = "<%=service%>";
    	var tempService1 = tempService.replace("cas?destination=","");
    	var tempService2 = tempService1.replace("&errorid=UsernameOrPasswordInvalid","");
    	window.location.href = tempService2;
    }
</script>
<!--
            <div id="sidebar">
                <p class="fl-panel fl-note fl-bevel-white fl-font-size-80"><spring:message code="screen.welcome.security" /></p>
                <div id="list-languages" class="fl-panel">
                <%final String queryString = request.getQueryString() == null ? "" : request.getQueryString().replaceAll("&locale=([A-Za-z][A-Za-z]_)?[A-Za-z][A-Za-z]|^locale=([A-Za-z][A-Za-z]_)?[A-Za-z][A-Za-z]", "");%>
					<c:set var='query' value='<%=queryString%>' />
                    <c:set var="xquery" value="${fn:escapeXml(query)}" />
                  <h3>Languages:</h3>
                  <c:choose>
                     <c:when test="${not empty requestScope['isMobile'] and not empty mobileCss}">
                        <form method="get" action="login?${xquery}">
                           <select name="locale">
                               <option value="en">English</option>
                               <option value="es">Spanish</option>
                               <option value="fr">French</option>
                               <option value="ru">Russian</option>
                               <option value="nl">Nederlands</option>
                               <option value="sv">Svenskt</option>
                               <option value="it">Italiano</option>
                               <option value="ur">Urdu</option>
                               <option value="zh_CN">Chinese (Simplified)</option>
                               <option value="de">Deutsch</option>
                               <option value="ja">Japanese</option>
                               <option value="hr">Croatian</option>
                               <option value="cs">Czech</option>
                               <option value="sl">Slovenian</option>
                               <option value="pl">Polish</option>
                               <option value="ca">Catalan</option>
                               <option value="mk">Macedonian</option>
                           </select>
                           <input type="submit" value="Switch">
                        </form>
                     </c:when>
                     <c:otherwise>
                        <c:set var="loginUrl" value="login?${xquery}${not empty xquery ? '&' : ''}locale=" />
						<ul
							><li class="first"><a href="${loginUrl}en">English</a></li
							><li><a href="${loginUrl}es">Spanish</a></li
							><li><a href="${loginUrl}fr">French</a></li
							><li><a href="${loginUrl}ru">Russian</a></li
							><li><a href="${loginUrl}nl">Nederlands</a></li
							><li><a href="${loginUrl}sv">Svenskt</a></li
							><li><a href="${loginUrl}it">Italiano</a></li
							><li><a href="${loginUrl}ur">Urdu</a></li
							><li><a href="${loginUrl}zh_CN">Chinese (Simplified)</a></li
							><li><a href="${loginUrl}de">Deutsch</a></li
							><li><a href="${loginUrl}ja">Japanese</a></li
							><li><a href="${loginUrl}hr">Croatian</a></li
							><li><a href="${loginUrl}cs">Czech</a></li
							><li><a href="${loginUrl}sl">Slovenian</a></li
                            ><li><a href="${loginUrl}ca">Catalan</a></li
                            ><li><a href="${loginUrl}mk">Macedonian</a></li
							><li class="last"><a href="${loginUrl}pl">Polish</a></li
						></ul>
                     </c:otherwise>
                   </c:choose>
                </div>
            </div>

<jsp:directive.include file="includes/bottom.jsp" />
-->