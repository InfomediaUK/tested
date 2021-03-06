package com.helmet.application.mgr.abztract;

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

import com.helmet.application.AlreadyLockedRuntimeException;
import com.helmet.application.BookingLockHandler;
import com.helmet.application.NotLockedRuntimeException;

public abstract class MgrBookingLockAction extends MgrAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

	protected ActionForward beforeDoExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;
     	Integer bookingId = (Integer)dynaForm.get("bookingId");
    	logger.debug("About to lock - " + bookingId + " from " + getClass().getName());

    	try {
			BookingLockHandler.getInstance().addLock(bookingId);
		} catch (AlreadyLockedRuntimeException e) {

	    	logger.warn("***** Already locked - " + bookingId);
			
			ActionMessages errors = new ActionMessages();
    		MessageResources messageResources = getResources(request);
            errors.add("bookingLocked", new ActionMessage("error.bookingLocked"));
            saveErrors(request, errors);
            return mapping.findForward("bookingLocked");
		}    	
 		return null;
		
	}
	
	protected void afterDoExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;
     	Integer bookingId = (Integer)dynaForm.get("bookingId");
    	logger.debug("About to unlock - " + bookingId);

    	try {
    		BookingLockHandler.getInstance().removeLock(bookingId);    	
		} catch (NotLockedRuntimeException e) {
	    	logger.error("Error trying to remove lock on bookingId - " + bookingId);
		}    	
		
	}

}
