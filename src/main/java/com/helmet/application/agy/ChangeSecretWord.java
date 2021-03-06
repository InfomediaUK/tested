package com.helmet.application.agy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.application.agy.abztract.AgyAction;


public class ChangeSecretWord extends AgyAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
    	

     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    	
     	// re-initialize the form
    	dynaForm.initialize(mapping);
    	
      	return mapping.findForward("success");
    }

}
