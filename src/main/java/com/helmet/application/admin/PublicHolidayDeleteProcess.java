package com.helmet.application.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AdminService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.admin.abztract.AdminAction;
import com.helmet.bean.PublicHoliday;


public class PublicHolidayDeleteProcess extends AdminAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
    	PublicHoliday publicHoliday = (PublicHoliday)dynaForm.get("publicHoliday");

		AdminService adminService = ServiceFactory.getInstance().getAdminService();
		
		int rowCount = adminService.deletePublicHoliday(publicHoliday.getPublicHolidayId(), publicHoliday.getNoOfChanges(), getAdministratorLoggedIn().getAdministratorId());

    	logger.exit("Out going !!!");
    	
    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
    							 actionForward.getPath() + "?client.clientId=" + publicHoliday.getClientId(),
    	                         actionForward.getRedirect());
    }

}
