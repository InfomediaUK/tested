package com.helmet.application.agy;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.api.exceptions.DuplicateDataException;
import com.helmet.bean.Applicant;

public class ApplicantNewProcess extends ApplicantCommonProcess
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    logger.entry("In coming !!!");
    ActionMessages errors = new ActionMessages();
    MessageResources messageResources = getResources(request);
    Applicant applicant = (Applicant) dynaForm.get("applicant");
    loadApplicant(applicant, dynaForm, errors, messageResources);
    if (!errors.isEmpty())
    {
      saveErrors(request, errors);
      return mapping.getInputForward();
    }
    applicant.setAgencyId(getConsultantLoggedIn().getAgencyId());
    // Get the set of new Unavailable dates from the form.
    String unavailableDates = (String)dynaForm.get("unavailableDates");
    FormFile photoFile = (FormFile) dynaForm.get("document");
    String contentType = photoFile.getContentType();
    String photoFilename = photoFile.getFileName();
    int fileSize = photoFile.getFileSize();
    if (StringUtils.isNotEmpty(photoFilename))
    {
      applicant.setPhotoFilename(photoFilename);
    }
    // CVFILE -->
    FormFile cvFile = (FormFile) dynaForm.get("cvFormFile");
    String cvFilename = cvFile.getFileName();
    if (StringUtils.isNotEmpty(cvFilename))
    {
      applicant.setCvFilename(cvFilename);
    }
    // <-- CVFILE
    // Remove all \r characters but leave the \n characters.
    String notes = ((String)dynaForm.get("notes")).replaceAll("\r", "");
    StringBuffer notesStringBuffer = new StringBuffer(notes);
    signAndDateNotes(notesStringBuffer);
    String temp = Long.toString(new java.util.Date().getTime());
    applicant.getUser().setLogin(temp);
    applicant.getUser().setPwd(temp);
    applicant.getUser().setPwdHint(temp);

    AgyService agyService = ServiceFactory.getInstance().getAgyService();

    int rowCount = 0;
    try
    {
      rowCount = agyService.insertApplicant(applicant, getConsultantLoggedIn().getConsultantId());
    }
    catch (DuplicateDataException e)
    {
      errors.add("consultant", new ActionMessage("errors.duplicate", messageResources.getMessage("label." + e.getField())));
      saveErrors(request, errors);
      return mapping.getInputForward();
    }
    if (StringUtils.isNotEmpty(unavailableDates))
    {
      rowCount += updateUnavailablesForApplicant(agyService, applicant, unavailableDates);
    }
    if (StringUtils.isNotEmpty(photoFilename))
    {
      // Upload the Photo file
      uploadPhotoFile(applicant, photoFile);
    }
    // CVFILE -->
    if (StringUtils.isNotEmpty(cvFilename))
    {
      // now upload the CV file
      uploadCvFile(applicant, cvFile);
    }
    // <-- CVFILE
    upoadApplicantFiles(applicant, dynaForm);
    // NEW -->
    saveApplicantNotes(applicant, notesStringBuffer.toString());
    // <-- NEW
    logger.exit("Out going !!!");

    ActionForward actionForward = mapping.findForward("success");

    return new ActionForward(actionForward.getName(), actionForward.getPath() + "?applicant.applicantId=" + applicant.getApplicantId(), actionForward.getRedirect());

  }

}