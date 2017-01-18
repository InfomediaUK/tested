package com.helmet.application.agy;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.Applicant;


public class ApplicantNotificationsAction extends AgyAction 
{
  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response) 
  {
  	
   	DynaValidatorForm dynaForm = (DynaValidatorForm)form;
  	logger.entry("In coming !!!");
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    Calendar calendar = Calendar.getInstance();
//    calendar.set(Calendar.HOUR, 00);
//    calendar.set(Calendar.MINUTE, 00);
//    calendar.set(Calendar.SECOND, 00);
//    calendar.set(Calendar.MILLISECOND, 00);
    Date dateToday = new Date(calendar.getTimeInMillis());
//    calendar.set(Calendar.HOUR, 23);
//    calendar.set(Calendar.MINUTE, 59);
//    calendar.set(Calendar.SECOND, 59);
//    calendar.set(Calendar.MILLISECOND, 999);
    calendar.add(Calendar.MONTH, 1);
    Date dateOneMonthAhead = new Date(calendar.getTimeInMillis());
    List<Applicant> newList = agyService.getApplicantsForAgencyNew(getConsultantLoggedIn().getAgencyId(), dateToday);
    List<Applicant> crbList = agyService.getApplicantsForAgencyCrbAboutToExpire(getConsultantLoggedIn().getAgencyId(), dateOneMonthAhead);
    List<Applicant> dbsList = agyService.getApplicantsForAgencyDbsAboutToExpire(getConsultantLoggedIn().getAgencyId(), dateOneMonthAhead);
    List<Applicant> fitToWorkList = agyService.getApplicantsForAgencyFitToWorkAboutToExpire(getConsultantLoggedIn().getAgencyId(), dateOneMonthAhead);
    List<Applicant> hpcList = agyService.getApplicantsForAgencyHpcAboutToExpire(getConsultantLoggedIn().getAgencyId(), dateOneMonthAhead);
    List<Applicant> idDocumentList = agyService.getApplicantsForAgencyIdDocumentAboutToExpire(getConsultantLoggedIn().getAgencyId(), dateOneMonthAhead);
    List<Applicant> trainingList = agyService.getApplicantsForAgencyTrainingAboutToExpire(getConsultantLoggedIn().getAgencyId(), dateOneMonthAhead);
    List<Applicant> visaList = agyService.getApplicantsForAgencyVisaAboutToExpire(getConsultantLoggedIn().getAgencyId(), dateOneMonthAhead);
    List<Applicant> reference1List = agyService.getApplicantsForAgencyReference1NotSatisfied(getConsultantLoggedIn().getAgencyId(), dateToday);
    List<Applicant> reference2List = agyService.getApplicantsForAgencyReference2NotSatisfied(getConsultantLoggedIn().getAgencyId(), dateToday);
    calendar.add(Calendar.MONTH, 1);
    Date dateTwoMonthsAhead = new Date(calendar.getTimeInMillis());
    List<Applicant> drivingLicenseList = agyService.getApplicantsForAgencyDrivingLicenseAboutToExpire(getConsultantLoggedIn().getAgencyId(), dateTwoMonthsAhead);
    // Unarchived applicants are of special interest. Show list.
    List<Applicant> unarchivedList = agyService.getApplicantsForAgencyUnarchived(getConsultantLoggedIn().getAgencyId());
    // Applicants that are newly compliant are of special interest. Show list.
    List<Applicant> recentlyCompliantList = agyService.getApplicantsForAgencyRecentlyCompliant(getConsultantLoggedIn().getAgencyId());
    List<Applicant> recentProspectList = agyService.getApplicantsForAgencyRecentProspect(getConsultantLoggedIn().getAgencyId());
    dynaForm.set("newList", newList);
    dynaForm.set("crbList", crbList);
    dynaForm.set("dbsList", dbsList);
    dynaForm.set("fitToWorkList", fitToWorkList);
    dynaForm.set("hpcList", hpcList);
    dynaForm.set("idDocumentList", idDocumentList);
    dynaForm.set("trainingList", trainingList);
    dynaForm.set("visaList", visaList);
    dynaForm.set("reference1List", reference1List);
    dynaForm.set("reference2List", reference2List);
    dynaForm.set("drivingLicenseList", drivingLicenseList);
    dynaForm.set("unarchivedList", unarchivedList);
    dynaForm.set("recentlyCompliantList", recentlyCompliantList);
    dynaForm.set("recentProspectList", recentProspectList);
  	logger.exit("Out going !!!");
  	return mapping.findForward("success");
  }

}
