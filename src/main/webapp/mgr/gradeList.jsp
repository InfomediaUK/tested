<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
		<bean:message key="title.gradeList"/>
		</td>
		<mmj-mgr:hasAccess forward="gradeNew">
    <html:form action="/gradeNew.do" onsubmit="return singleSubmit();">
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.new"/></html:submit></td>
    </html:form>
		</mmj-mgr:hasAccess>
		<mmj-mgr:hasAccess forward="gradeOrder">
    <html:form action="/gradeOrder.do" onsubmit="return singleSubmit();">
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.order"/></html:submit></td>
    </html:form>
		</mmj-mgr:hasAccess>
  </tr>
</table>

<logic:notPresent name="ListFormMgr" property="list">
  <bean:message key="text.noDetails"/>
</logic:notPresent>
<logic:present name="ListFormMgr" property="list">
<logic:empty name="ListFormMgr" property="list">
  <bean:message key="text.noDetails"/>
</logic:empty>
<logic:notEmpty name="ListFormMgr" property="list">
<table class="simple" width="100%">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.grade"/></th>
  </tr>
  </thead>
  <logic:iterate id="grade" name="ListFormMgr" property="list" indexId="gradeIndex">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="left">
			<mmj-mgr:hasAccess forward="gradeView">
  	  <html:link forward="gradeView" paramId="grade.gradeId" paramName="grade" paramProperty="gradeId">
	      <bean:write name="grade" property="name"/>
	 	  </html:link>
			</mmj-mgr:hasAccess>
			<mmj-mgr:hasNoAccess forward="gradeView">
      <bean:write name="grade" property="name"/>
			</mmj-mgr:hasNoAccess>
    </td>
  </tr> 
  </logic:iterate>
</table> 
</logic:notEmpty> 
</logic:present>