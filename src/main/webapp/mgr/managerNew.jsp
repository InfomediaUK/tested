<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
<html:form action="/managerNewProcess.do" focus="manager.user.firstName" onsubmit="return singleSubmit();">
<html:hidden property="manager.noOfChanges"/>
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.managerNew"/>
		</td>
    <mmj-mgr:hasAccess forward="managerNew">
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.save"/></html:submit></td>
		</mmj-mgr:hasAccess>
    <td align="right" valign="middle" width="75"><html:cancel styleClass="titleButton"><bean:message key="button.cancel"/></html:cancel></td>
  </tr>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <th align="left" width="35%"><bean:message key="label.firstName"/></th>
    <td align="left" width="65%"><html:text property="manager.user.firstName"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.lastName"/></th>
    <td align="left"><html:text property="manager.user.lastName"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.emailAddress"/></th>
    <td align="left"><html:text property="manager.user.emailAddress"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.login"/></th>
    <td align="left"><html:text property="manager.user.login"/></td>
  </tr>
</html:form>
</table>
  