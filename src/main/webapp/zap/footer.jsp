<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="date" class="java.util.Date" />
<br/>
<bean:message key="text.copyright" />&nbsp;<fmt:formatDate value="${date}" pattern="yyyy" />
<br/>
<br/>
<%
String serverNamePrefix = null;
String mmjLogo = null;
if (request.getServerName().contains("matchmyjob.co.uk"))
{
  // Could be www or test or local.
  serverNamePrefix = request.getServerName().substring(0, request.getServerName().indexOf("."));
  serverNamePrefix = "www".equals(serverNamePrefix) ? "" : serverNamePrefix;
  mmjLogo = request.getContextPath() + "/images/" + serverNamePrefix + "master-logo.jpg";
}
else
{
  // Could be localhost or www.befriened.co.uk.
  serverNamePrefix = request.getServerName();
  mmjLogo = request.getContextPath() + "/images/localmaster-logo.jpg";
}
%>

<tiles:useAttribute name="showHeaderLinks"/>

<logic:equal name="showHeaderLinks" value="true">
<html:link page="/.." titleKey="title.mmjLogo"><html:img src="<%= mmjLogo %>" width="150" height="58" /></html:link>
</logic:equal>

<logic:notEqual name="showHeaderLinks" value="true">
<html:img src="<%= mmjLogo %>" width="150" height="58" />
</logic:notEqual>

<jsp:include flush="true" page="/sessionStuff.jsp"/>
