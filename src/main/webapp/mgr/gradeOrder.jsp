<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
<form action="gradeOrderProcess.do" name="DisplayOrderFormMgr">
  <html:hidden name="DisplayOrderFormMgr" property="order"/>
  <html:hidden name="DisplayOrderFormMgr" property="zeroiseDisplayOrder"/>
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.gradeOrder"/>
		</td>
    <td align="right" valign="middle" width="75">
		  <html:submit onclick="javascript:loadvalues(document.DisplayOrderFormMgr.commaDelimitedKey, document.DisplayOrderFormMgr.order)" styleClass="titleButton">
		    <bean:message key="button.save"/>
		  </html:submit>
		</td>
    <td align="right" valign="middle" width="75">
		  <html:submit onclick="javascript:zeroiseDisplayOrder.value='true';loadvalues(document.DisplayOrderFormMgr.commaDelimitedKey, document.DisplayOrderFormMgr.order)" styleClass="titleButton">
		    <bean:message key="button.zeroise"/>
		  </html:submit>
		</td>
    <td align="right" valign="middle" width="75"><html:cancel styleClass="titleButton"><bean:message key="button.cancel"/></html:cancel></td>
	</tr>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <td align="left" width="100%">
    
  <html:select name="DisplayOrderFormMgr" property="commaDelimitedKey" size="20" style="width: 400px">
  <logic:iterate id="grade" name="DisplayOrderFormMgr" property="list" type="com.helmet.bean.Grade">
    <bean:define id="id" name="grade" property="gradeId" />
    <bean:define id="noOfChanges" name="grade" property="noOfChanges" />
    <bean:define id="name" name="grade" property="name" />
    <option value="<%= id %>,<%= noOfChanges %>"><%= name %></option>
  </logic:iterate>
  </html:select>

		</td>
	</tr>
  <tr>
    <td align="left">
		  <html:button onclick="javascript:swap(document.DisplayOrderFormMgr.commaDelimitedKey, -1);" property="up" styleClass="titleButton">
		    <bean:message key="button.up"/>
		  </html:button>
		  <html:button onclick="javascript:swap(document.DisplayOrderFormMgr.commaDelimitedKey, 1);" property="down" styleClass="titleButton">
		    <bean:message key="button.down"/>
		  </html:button>
		</td>
  </tr>
</form>
</table>

<script type="text/javascript">
<!--

var dividerText = '***';
var dividerValue = 0;

function swap(listbox, direction)
{
  var i = listbox.selectedIndex;

  if (i > -1)
  {
    // An item has been selected.
    var src = i;
    var tgt = i + direction;

    if (tgt > -1 &&
        tgt < listbox.length)
    {
      // We are NOT at the top moving up OR at the bottom moving down.
      if ((tgt == listbox.length - 1 &&
           listbox.options[src].value == dividerValue) ||
          (tgt == 0 &&
           listbox.options[src].value == dividerValue))
      {
        alert('no can do - cannot move a divider to the top or bottom');
      }
      else
      {
  // AND we are not moving a divider down to the bottom.

  srctext = listbox.options[src].text;
  srcvalue = listbox.options[src].value;
  tgttext = listbox.options[tgt].text;
  tgtvalue = listbox.options[tgt].value;

        if (srcvalue < 0 && tgtvalue < 0)
  {
          alert('no can do - cannot swap a menu type option with another.');
  }
  else
  {
      // AND we are not moving an option with a negative value with another option with a negative value.

    if ((srcvalue < 0 && src == 0) ||
        (tgtvalue < 0 && tgt == 0))
    {
      alert('no can do - cannot move menu type from top.');
    }
    else
    {
      // AND we are not moving an option with a negative value with another option with a negative value.

    //
    // KRD KRD KRD KRD KRD KRD KRD KRD KRD KRD KRD KRD KRD KRD KRD KRD KRD KRD
    //
    // Still not bullet proof !!!
    //

    if ((srcvalue == dividerValue &&
         listbox.options[tgt - (direction * -1)].value < 0) ||
            (tgtvalue == dividerValue &&
         listbox.options[src - (direction * -1)].value < 0))
    {
        alert('no can do - cannot move a divider next to a menu type option.');
      }
      else
      {
      // AND we are not moving a divider type option next to a menu type option.

      listbox.options[src].text = tgttext;
      listbox.options[src].value = tgtvalue;
      listbox.options[tgt].text = srctext;
      listbox.options[tgt].value = srcvalue;

      listbox.selectedIndex = tgt;
    }
    }
  }
      }
    }
    else
    {
      alert('no can do - cannot be at the top moving up OR at the bottom moving down.');
    }
  }
  else
  {
    alert('nothing selected to move !!!');
  }
}

function loadvalues(listbox, valueField)
{
  var values = '';

  for (var i = 0; i < listbox.length; i++)
  {
    if (values != '')
    {
      values = values + '|';
    }
    values = values + listbox.options[i].value;

  }

  valueField.value = values;

//  alert(values);
}

function showIt() {
  alert(document.DisplayOrderFormMgr.order.value);
}

//-->
</script>

