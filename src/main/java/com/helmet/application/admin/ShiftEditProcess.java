package com.helmet.application.admin;

import java.sql.Time;

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
import com.helmet.bean.Shift;


public class ShiftEditProcess extends AdminAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");

     	Shift shift = (Shift)dynaForm.get("shift");

     	//
     	String shiftStartHour = (String)dynaForm.get("shiftStartHour");
     	String shiftStartMinute = (String)dynaForm.get("shiftStartMinute");
     	String shiftEndHour = (String)dynaForm.get("shiftEndHour");
     	String shiftEndMinute = (String)dynaForm.get("shiftEndMinute");
     	String shiftBreakStartHour = (String)dynaForm.get("shiftBreakStartHour");
     	String shiftBreakStartMinute = (String)dynaForm.get("shiftBreakStartMinute");
     	String shiftBreakEndHour = (String)dynaForm.get("shiftBreakEndHour");
     	String shiftBreakEndMinute = (String)dynaForm.get("shiftBreakEndMinute");

     	ShiftUtilities.sortTimes(shift,
     			                 shiftStartHour, shiftStartMinute, shiftEndHour, shiftEndMinute, 
     			                 shiftBreakStartHour, shiftBreakStartMinute, shiftBreakEndHour, shiftBreakEndMinute);
     	
     	ActionMessages errors = new ActionMessages();

		MessageResources messageResources = getResources(request);
		
     	AdminService adminService = ServiceFactory.getInstance().getAdminService();
		
		try {
		  int rowCount = adminService.updateShift(shift, getAdministratorLoggedIn().getAdministratorId());
		}  
        catch (DuplicateDataException e) {
            errors.add("location", new ActionMessage("errors.duplicate", messageResources.getMessage("label." + e.getField())));
            saveErrors(request, errors);
    		return mapping.getInputForward();
        }

    	logger.exit("Out going !!!");
    	
    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
    							 actionForward.getPath() + "?location.locationId=" + shift.getLocationId(),
    	                         actionForward.getRedirect());

    }

}
