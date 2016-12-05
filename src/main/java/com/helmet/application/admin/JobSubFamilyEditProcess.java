package com.helmet.application.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AdminService;
import com.helmet.api.ServiceFactory;
import com.helmet.api.exceptions.DuplicateDataException;
import com.helmet.application.admin.abztract.AdminAction;
import com.helmet.bean.JobSubFamily;


public class JobSubFamilyEditProcess extends AdminAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");

     	JobSubFamily jobSubFamily = (JobSubFamily)dynaForm.get("jobSubFamily");

		ActionMessages errors = new ActionMessages();

		MessageResources messageResources = getResources(request);
		
     	AdminService adminService = ServiceFactory.getInstance().getAdminService();
		
		try {
		  int rowCount = adminService.updateJobSubFamily(jobSubFamily, getAdministratorLoggedIn().getAdministratorId());
		}  
        catch (DuplicateDataException e) {
            errors.add("jobSubFamily", new ActionMessage("errors.duplicate", messageResources.getMessage("label." + e.getField())));
            saveErrors(request, errors);
    		return mapping.getInputForward();
        }

    	logger.exit("Out going !!!");
    	
    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
    							 actionForward.getPath() + "?jobSubFamily.jobSubFamilyId=" + jobSubFamily.getJobSubFamilyId(),
    	                         actionForward.getRedirect());

    }

}
