<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<br/>
<br/>
<br/>
<br/>
<%
String imagePrefix = System.getenv("IMAGE_PREFIX");
String mmjLogo = request.getContextPath() + "/images/" + imagePrefix + "master-logo.jpg";
%>
<html:link forward="home"><img src="<%= mmjLogo %>" width="150" height="58" /></html:link>
<br/>
<br/>
<h1><bean:message key="text.strapLine" /></h1>
