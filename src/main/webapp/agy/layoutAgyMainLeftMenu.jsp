<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" errorPage="/errorPages/error500.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<%  org.apache.struts.util.ModuleUtils.getInstance().selectModule(request, pageContext.getServletContext()); %>
<!DOCTYPE html>
<html>
<head>
<tiles:useAttribute name="includeZapatec"/>
<tiles:useAttribute name="titleKey"/>
<mmj-agy:agency var="agency"/>
<title>AGY (<bean:write name="agency" property="code"/>) - <bean:message name="titleKey" property="value" scope="page"/></title>
<meta name="description" content="<tiles:getAsString name="description"/>">
<meta name="keywords" content="<tiles:getAsString name="keywords"/>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Expires" content="0" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Refresh" content="<%= pageContext.getSession().getMaxInactiveInterval() %>; url=<%= request.getContextPath() %>/agyLoggedOut.do">
<link rel="icon" href="<%= request.getContextPath() %>/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="<%= request.getContextPath() %>/favicon.ico" type="image/x-icon">
<logic:equal name="includeZapatec" property="value" value="true">
<link rel="stylesheet" type="text/css" media="all" href="/mmj/zapatec/themes/winxp.css" title="Calendar Theme - winxp.css">
<link href="/mmj/zapatec/doc/css/zpcal.css" rel="stylesheet" type="text/css">
</logic:equal>
<link type="text/css" rel="stylesheet" href="site.css" media="screen">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/tabber.css" media="screen">
<logic:equal name="includeZapatec" property="value" value="true">
<!-- import the calendar script -->
<script type="text/javascript" src="/mmj/zapatec/src/utils.js"></script>
<script type="text/javascript" src="/mmj/zapatec/src/calendar.js"></script>
<!-- import the language module -->
<script type="text/javascript" src="/mmj/zapatec/lang/calendar-en.js"></script>
<!-- import the calendar setup script -->
<script type="text/javascript" src="/mmj/zapatec/src/calendar-setup.js"></script>
</logic:equal>
<script language="javascript" type="text/javascript" src="site.js"></script>
<script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/tabber.js"></script>
</head>
<body>

<%-- jsp:include flush="true" page="/siteUnavailable.jsp"/--%>

<bean:parameter id="bendyParam" name="bendy" value="" />
<logic:equal name="bendyParam" value="false">
<% session.setAttribute("bendy", "false"); %>
</logic:equal>
<logic:equal name="bendyParam" value="true">
<% session.removeAttribute("bendy"); %>
</logic:equal>

<bean:define id="pageWidth" value="815"/> <!-- 815 100% -->
<bean:define id="helpWidth" value="315"/> <!-- 315 100% -->
<bean:define id="menuWidth" value="95"/> <!-- 95 50 -->
<bean:define id="spacerWidth" value="11"/>
<bean:define id="contentWidth" value="709"/> <!-- 709 100% -->
<bean:define id="headerHeight" value="25"/>
<bean:define id="contentHeight" value="550"/>
<bean:define id="footerHeight" value="25"/>
<logic:notPresent name="bendy" scope="session">
	<bean:define id="pageWidth" value="100%"/>
	<bean:define id="helpWidth" value="100%"/>
	<bean:define id="menuWidth" value="50"/>
	<bean:define id="contentWidth" value="100%"/>
</logic:notPresent>

<div align="left">
<%/* TEMPORARY */%>

<bean:define id="agencyName" name="agency" property="name" type="java.lang.String"/>
<bean:define id="agencyLogo" name="agency" property="logoUrl" type="java.lang.String"/>
<bean:define id="agencyLogoWidth" name="agency" property="logoWidth" type="java.lang.Integer"/>
<bean:define id="agencyLogoHeight" name="agency" property="logoHeight" type="java.lang.Integer"/>

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
// String serverNamePrefix = request.getServerName().substring(0, request.getServerName().indexOf("."));
// serverNamePrefix = "www".equals(serverNamePrefix) ? "" : serverNamePrefix;
// String mmjLogo = request.getContextPath() + "/images/" + serverNamePrefix + "master-logo.jpg";
%>

<table width="<bean:write name="pageWidth"/>" height="76" cellpadding="0" cellspacing="0" border="0">
  <tr height="76">
    <td valign="top" align="left" width="180"><html:link forward="home" titleKey="title.mmjLogo"><html:img src="<%= mmjLogo %>" width="150" height="58" /></html:link></td>
    <td valign="top" align="left" width="70"><html:img src="images/trans.gif" width="70" height="70" /></td>
    <td valign="top" align="left" width="<bean:write name="helpWidth"/>">
    <div id="pageHelp">
      <table height="100%">
        <tr>
          <td valign="top">
      <tiles:useAttribute name="pageHelpKey"/>
			<bean:message name="pageHelpKey" property="value" scope="page"/>
          </td>
        </tr>
        <tr>
          <td valign="bottom">
			<bean:message key="pageHelp.helpDesk"/>
		      </td>
		    </tr>
      </table>
    </div>
    </td>
    <td valign="top" align="left" width="250">
      <table width="250" cellpadding="0" cellspacing="0">
        <tr height="50">
          <td valign="top" align="right">
<logic:equal name="agencyLogoWidth" value="0">
            <bean:write name="agency" property="name"/>
</logic:equal>
<logic:notEqual name="agencyLogoWidth" value="0">
            <html:img src="<%= request.getContextPath() + agencyLogo %>" width="<%= agencyLogoWidth.toString() %>" height="<%= agencyLogoHeight.toString() %>" title="<%= agencyName %>" alt="<%= agencyName %>"/>
</logic:notEqual>
          </td>
        </tr>
        <tr height="26">
          <td valign="bottom" align="right">
            <tiles:insert attribute="header"/>

<script type="text/javascript">
<!--

var theRequest;

function getRequest() {
	if (window.XMLHttpRequest) {
		return new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		return new ActiveXObject("Microsoft.XMLHTTP");
	}
}

function toggleShowPageHelp() {
	theRequest = getRequest();
//	alert(theRequest);
	theRequest.open("POST", "<%= request.getContextPath() %>/agy/toggleShowPageHelp.do", true);
	theRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	theRequest.onreadystatechange = callbackMethodHelp;
	theRequest.send("x=123");
}

function callbackMethodHelp() {
	if ((theRequest.readyState == 4) && (theRequest.status == 200)) {
		var response = theRequest.responseText;
//    window.status = window.status + response;
    if (response == "OK") {
      toggle('pageHelp')
    }
	}
}

//-->
</script>

          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>

<mmj-agy:consultant var="consultant"/>
<logic:equal name="consultant" property="user.showPageHelp" value="true">
  <script language="javascript" type="text/javascript">
	<!--
	showIt('pageHelp');
	// -->
	</script>
</logic:equal>
<logic:notEqual name="consultant" property="user.showPageHelp" value="true">
  <script language="javascript" type="text/javascript">
	<!--
	hideIt('pageHelp');
	// -->
	</script>
</logic:notEqual>

<table border="0" cellpadding="0" cellspacing="0" width="<bean:write name="pageWidth"/>">
  <tr><td height="5" width="<bean:write name="pageWidth"/>"></td></tr>
  <tr><td height="1" width="<bean:write name="pageWidth"/>" bgcolor="#e0e0e0"></td></tr>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="<bean:write name="pageWidth"/>" height="<bean:write name="contentHeight"/>">
  <tr>
    <td align="left" valign="top" width="<bean:write name="menuWidth"/>" class="leftColumn">
    <table cellpadding="0" cellspacing="0" align="left" height="100%" width="100%">
      <tr>
        <td align="left" valign="top" nowrap="nowrap">
      <tiles:insert attribute="menu"/>
        </td>
      </tr>
    </table>
    </td>
    <td align="left" valign="top" width="<bean:write name="spacerWidth"/>" height="100%">
<%--
--%>
    <table cellpadding="0" cellspacing="0" align="left" width="<bean:write name="spacerWidth"/>" height="100%">
      <tr>
    <td align="left" valign="top" width="5" height="100%"><html:img src="images/trans.gif" width="5" height="100%" /></td>
    <td align="left" valign="top" width="1" height="100%" bgcolor="#e0e0e0"><html:img src="images/trans.gif" width="1" height="100%" /></td>
    <td align="left" valign="top" width="5" height="100%"><html:img src="images/trans.gif" width="5" height="100%" /></td>
      </tr>
    </table>
    </td>
    <td align="left" valign="top" width="<bean:write name="contentWidth"/>" class="contentColumn">
      <tiles:insert attribute="body"/>
    </td>
  </tr>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="<bean:write name="pageWidth"/>">
  <tr><td height="1" width="<bean:write name="pageWidth"/>" bgcolor="#e0e0e0"></td></tr>
  <tr><td height="5" width="<bean:write name="pageWidth"/>"></td></tr>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="<bean:write name="pageWidth"/>" height="<bean:write name="footerHeight"/>">
  <tr>
    <td align="center" valign="top" class="footer">
      <tiles:insert attribute="footer"/>
    </td>
  </tr>
</table>
</div>
<script language="javascript">
<!--
// MUST BE AT THE BOTTOM OF THE PAGE OTHERWISE FIREHELMET SCREWS UP!
var formSubmitCount = 0;
// -->
</script>
</body>
</html>