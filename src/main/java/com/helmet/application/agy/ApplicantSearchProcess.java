package com.helmet.application.agy;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.Constants;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.Applicant;


public class ApplicantSearchProcess extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;

    logger.entry("In coming !!!");
    ActionMessages errors = new ActionMessages();
    MessageResources messageResources = getResources(request);
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    sdf.setLenient(false);
    String lastNameOperator = (String)dynaForm.get("lastNameOperator");
    String lastName = (String)dynaForm.get("lastName");
    String performanceEvaluationOperator = (String)dynaForm.get("performanceEvaluationOperator");
    String performanceEvaluation = (String)dynaForm.get("performanceEvaluation");
    String assessment12WeekOperator = (String)dynaForm.get("assessment12WeekOperator");
    String assessment12Week = (String)dynaForm.get("assessment12Week");
    String interviewedOperator = (String)dynaForm.get("interviewedOperator");
    String interviewed = (String)dynaForm.get("interviewed");
    String areaOfSpecialityIdOperator = (String)dynaForm.get("areaOfSpecialityIdOperator");
    String areaOfSpecialityId = (String)dynaForm.get("areaOfSpecialityId");
    String areaOfSpecialityId2Operator = (String)dynaForm.get("areaOfSpecialityId2Operator");
    String areaOfSpecialityId2 = (String)dynaForm.get("areaOfSpecialityId2");
    String geographicalRegionIdOperator = (String)dynaForm.get("geographicalRegionIdOperator");
    String geographicalRegionId = (String)dynaForm.get("geographicalRegionId");
    String disciplineCategoryIdOperator = (String)dynaForm.get("disciplineCategoryIdOperator");
    String disciplineCategoryId = (String)dynaForm.get("disciplineCategoryId");
    String clientGroupOperator = (String)dynaForm.get("clientGroupOperator");
    String clientGroup = (String)dynaForm.get("clientGroup");
    String niNumberStatusOperator = (String)dynaForm.get("niNumberStatusOperator");
    String niNumberStatus = (String)dynaForm.get("niNumberStatus");
    String hasVaricellaDocumentOperator = (String)dynaForm.get("hasVaricellaDocumentOperator");
    String hasVaricellaDocument = (String)dynaForm.get("hasVaricellaDocument");
    String hasHepbDocumentOperator = (String)dynaForm.get("hasHepbDocumentOperator");
    String hasHepbDocument = (String)dynaForm.get("hasHepbDocument");
    String hasTuberculosisDocumentOperator = (String)dynaForm.get("hasTuberculosisDocumentOperator");
    String hasTuberculosisDocument = (String)dynaForm.get("hasTuberculosisDocument");
    String hasMMRX2DocumentOperator = (String)dynaForm.get("hasMMRX2DocumentOperator");
    String hasMMRX2Document = (String)dynaForm.get("hasMMRX2Document");
    String hasMMRDocumentOperator = (String)dynaForm.get("hasMMRDocumentOperator");
    String hasMMRDocument = (String)dynaForm.get("hasMMRDocument");
    String hasFitToWorkDocumentOperator = (String)dynaForm.get("hasFitToWorkDocumentOperator");
    String hasFitToWorkDocument = (String)dynaForm.get("hasFitToWorkDocument");
    String hasCRBDocumentOperator = (String)dynaForm.get("hasCRBDocumentOperator");
    String hasCRBDocument = (String)dynaForm.get("hasCRBDocument");
    String hasHPCDocumentOperator = (String)dynaForm.get("hasHPCDocumentOperator");
    String hasHPCDocument = (String)dynaForm.get("hasHPCDocument");
    String hasTrainingCertificateOperator = (String)dynaForm.get("hasTrainingCertificateOperator");
    String hasTrainingCertificate = (String)dynaForm.get("hasTrainingCertificate");
    String hasPassportOperator = (String)dynaForm.get("hasPassportOperator");
    String hasPassport = (String)dynaForm.get("hasPassport");
    String hasProofOfAddressOperator = (String)dynaForm.get("hasProofOfAddressOperator");
    String hasProofOfAddress = (String)dynaForm.get("hasProofOfAddress");
    String hasBirthCertificateOperator = (String)dynaForm.get("hasBirthCertificateOperator");
    String hasBirthCertificate = (String)dynaForm.get("hasBirthCertificate");
    String hasOverseasPoliceClearanceOperator = (String)dynaForm.get("hasOverseasPoliceClearanceOperator");
    String hasOverseasPoliceClearance = (String)dynaForm.get("hasOverseasPoliceClearance");
    String hasDegreeOperator = (String)dynaForm.get("hasDegreeOperator");
    String hasDegree = (String)dynaForm.get("hasDegree");
    String isArchivedOperator = (String)dynaForm.get("isArchivedOperator");
    String isArchived = (String)dynaForm.get("isArchived");
    String isCompliantOperator = (String)dynaForm.get("isCompliantOperator");
    String isCompliant = (String)dynaForm.get("isCompliant");

    ApplicantSearchParameters applicantSearchParameters = new ApplicantSearchParameters();
    String availabilityDateStr = (String)dynaForm.get("availabilityDate");
    Date availabilityDate = null;
    if (StringUtils.isNotEmpty(availabilityDateStr))
    {
      String availabilityDateOperator = (String)dynaForm.get("availabilityDateOperator");
      availabilityDate = getDate(sdf, availabilityDateStr, errors, messageResources, "label.availableOnOrBefore");
      applicantSearchParameters.setAvailabilityDate(availabilityDate);
      applicantSearchParameters.setAvailabilityDateOperator(availabilityDateOperator);
      session.setAttribute("availabilityDateOperator", availabilityDateOperator);      
      applicantSearchParameters.setAvailabilityDateLabel(messageResources.getMessage("label.availableOnOrBefore"));
    }
    String visaExpiryDateStr = (String)dynaForm.get("visaExpiryDate");
    Date visaExpiryDate = null;
    if (StringUtils.isNotEmpty(visaExpiryDateStr))
    {
      String visaExpiryDateOperator = (String)dynaForm.get("visaExpiryDateOperator");
      visaExpiryDate = getDate(sdf, visaExpiryDateStr, errors, messageResources, "label.visaExpiresOnOrBefore");
      applicantSearchParameters.setVisaExpiryDate(visaExpiryDate);
      applicantSearchParameters.setVisaExpiryDateOperator(visaExpiryDateOperator);
      session.setAttribute("visaExpiryDateOperator", visaExpiryDateOperator);      
      applicantSearchParameters.setVisaExpiryDateLabel(messageResources.getMessage("label.visaExpiresOnOrBefore"));
    }
    String passportExpiryDateStr = (String)dynaForm.get("passportExpiryDate");
    Date passportExpiryDate = null;
    if (StringUtils.isNotEmpty(passportExpiryDateStr))
    {
      String passportExpiryDateOperator = (String)dynaForm.get("passportExpiryDateOperator");
      passportExpiryDate = getDate(sdf, passportExpiryDateStr, errors, messageResources, "label.passportExpiresOnOrBefore");
      applicantSearchParameters.setPassportExpiryDate(passportExpiryDate);
      applicantSearchParameters.setPassportExpiryDateOperator(passportExpiryDateOperator);
      session.setAttribute("passportExpiryDateOperator", passportExpiryDateOperator);      
      applicantSearchParameters.setPassportExpiryDateLabel(messageResources.getMessage("label.passportExpiresOnOrBefore"));
    }
    String crbExpiryDateStr = (String)dynaForm.get("crbExpiryDate");
    Date crbExpiryDate = null;
    if (StringUtils.isNotEmpty(crbExpiryDateStr))
    {
      String crbExpiryDateOperator = (String)dynaForm.get("crbExpiryDateOperator");
      crbExpiryDate = getDate(sdf, crbExpiryDateStr, errors, messageResources, "label.crbExpiresOnOrBefore");
      applicantSearchParameters.setCrbExpiryDate(crbExpiryDate);
      applicantSearchParameters.setCrbExpiryDateOperator(crbExpiryDateOperator);
      session.setAttribute("crbExpiryDateOperator", crbExpiryDateOperator);      
      applicantSearchParameters.setCrbExpiryDateLabel(messageResources.getMessage("label.crbExpiresOnOrBefore"));
    }
    String hpcExpiryDateStr = (String)dynaForm.get("hpcExpiryDate");
    Date hpcExpiryDate = null;
    if (StringUtils.isNotEmpty(hpcExpiryDateStr))
    {
      String hpcExpiryDateOperator = (String)dynaForm.get("hpcExpiryDateOperator");
      hpcExpiryDate = getDate(sdf, hpcExpiryDateStr, errors, messageResources, "label.hpcExpiresOnOrBefore");
      applicantSearchParameters.setHpcExpiryDate(hpcExpiryDate);
      applicantSearchParameters.setHpcExpiryDateOperator(hpcExpiryDateOperator);
      session.setAttribute("hpcExpiryDateOperator", hpcExpiryDateOperator);      
      applicantSearchParameters.setHpcExpiryDateLabel(messageResources.getMessage("label.hpcExpiresOnOrBefore"));
    }
    String trainingExpiryDateStr = (String)dynaForm.get("trainingExpiryDate");
    Date trainingExpiryDate = null;
    if (StringUtils.isNotEmpty(trainingExpiryDateStr))
    {
      String trainingExpiryDateOperator = (String)dynaForm.get("trainingExpiryDateOperator");
      trainingExpiryDate = getDate(sdf, trainingExpiryDateStr, errors, messageResources, "label.trainingExpiresOnOrBefore");
      applicantSearchParameters.setTrainingExpiryDate(trainingExpiryDate);
      applicantSearchParameters.setTrainingExpiryDateOperator(trainingExpiryDateOperator);
      session.setAttribute("trainingExpiryDateOperator", trainingExpiryDateOperator);      
      applicantSearchParameters.setTrainingExpiryDateLabel(messageResources.getMessage("label.trainingExpiresOnOrBefore"));
    }
    String fitToWorkExpiryDateStr = (String)dynaForm.get("fitToWorkExpiryDate");
    Date fitToWorkExpiryDate = null;
    if (StringUtils.isNotEmpty(fitToWorkExpiryDateStr))
    {
      String fitToWorkExpiryDateOperator = (String)dynaForm.get("fitToWorkExpiryDateOperator");
      fitToWorkExpiryDate = getDate(sdf, fitToWorkExpiryDateStr, errors, messageResources, "label.fitToWorkExpiresOnOrBefore");
      applicantSearchParameters.setFitToWorkExpiryDate(fitToWorkExpiryDate);
      applicantSearchParameters.setFitToWorkExpiryDateOperator(fitToWorkExpiryDateOperator);
      session.setAttribute("fitToWorkExpiryDateOperator", fitToWorkExpiryDateOperator);      
      applicantSearchParameters.setFitToWorkExpiryDateLabel(messageResources.getMessage("label.fitToWorkExpiresOnOrBefore"));
    }
    applicantSearchParameters.setLastNameOperator("".equals(lastName) ? null : lastNameOperator);
    session.setAttribute("lastNameOperator", lastNameOperator);      
    applicantSearchParameters.setLastName("".equals(lastName) ? null : lastName);

    applicantSearchParameters.setPerformanceEvaluationOperator("".equals(performanceEvaluation) ? null : performanceEvaluationOperator);
    session.setAttribute("performanceEvaluationOperator", performanceEvaluationOperator);
    applicantSearchParameters.setPerformanceEvaluation("".equals(performanceEvaluation) ? null : "true".equals(performanceEvaluation));

    applicantSearchParameters.setAssessment12WeekOperator("".equals(assessment12Week) ? null : assessment12WeekOperator);
    session.setAttribute("assessment12WeekOperator", assessment12WeekOperator);
    applicantSearchParameters.setAssessment12Week("".equals(assessment12Week) ? null : "true".equals(assessment12Week));
    applicantSearchParameters.setInterviewedOperator("".equals(interviewed) ? null : interviewedOperator);
    session.setAttribute("interviewedOperator", interviewedOperator);
    applicantSearchParameters.setInterviewed("".equals(interviewed) ? null : "true".equals(interviewed));
    applicantSearchParameters.setAreaOfSpecialityIdOperator("".equals(areaOfSpecialityId) ? null : areaOfSpecialityIdOperator);
    session.setAttribute("areaOfSpecialityIdOperator", areaOfSpecialityIdOperator);
    applicantSearchParameters.setAreaOfSpecialityId("".equals(areaOfSpecialityId) ? null : Integer.parseInt(areaOfSpecialityId));
    applicantSearchParameters.setAreaOfSpecialityId2Operator("".equals(areaOfSpecialityId2) ? null : areaOfSpecialityId2Operator);
    session.setAttribute("areaOfSpecialityId2Operator", areaOfSpecialityId2Operator);
    applicantSearchParameters.setAreaOfSpecialityId2("".equals(areaOfSpecialityId2) ? null : Integer.parseInt(areaOfSpecialityId2));
    applicantSearchParameters.setGeographicalRegionIdOperator("".equals(geographicalRegionId) ? null : geographicalRegionIdOperator);
    session.setAttribute("geographicalRegionIdOperator", geographicalRegionIdOperator);
    applicantSearchParameters.setGeographicalRegionId("".equals(geographicalRegionId) ? null : Integer.parseInt(geographicalRegionId));
    applicantSearchParameters.setDisciplineCategoryIdOperator("".equals(disciplineCategoryId) ? null : disciplineCategoryIdOperator);
    session.setAttribute("disciplineCategoryIdOperator", disciplineCategoryIdOperator);
    applicantSearchParameters.setDisciplineCategoryId("".equals(disciplineCategoryId) ? null : Integer.parseInt(disciplineCategoryId));
    applicantSearchParameters.setClientGroupOperator("".equals(clientGroup) ? null : clientGroupOperator);
    session.setAttribute("clientGroupOperator", clientGroupOperator);
    applicantSearchParameters.setClientGroup("".equals(clientGroup) ? null : Integer.parseInt(clientGroup));
    applicantSearchParameters.setNiNumberStatusOperator("".equals(niNumberStatus) ? null : niNumberStatusOperator);
    session.setAttribute("niNumberStatusOperator", niNumberStatusOperator);
    applicantSearchParameters.setNiNumberStatus("".equals(niNumberStatus) ? null : Integer.parseInt(niNumberStatus));
    applicantSearchParameters.setHasVaricellaDocumentOperator("".equals(hasVaricellaDocument) ? null : hasVaricellaDocumentOperator);
    session.setAttribute("hasVaricellaDocumentOperator", hasVaricellaDocumentOperator);
    applicantSearchParameters.setHasVaricellaDocument("".equals(hasVaricellaDocument) ? null : "true".equals(hasVaricellaDocument));
    applicantSearchParameters.setHasHepbDocumentOperator("".equals(hasHepbDocument) ? null : hasHepbDocumentOperator);
    session.setAttribute("hasHepbDocumentOperator", hasHepbDocumentOperator);
    applicantSearchParameters.setHasHepbDocument("".equals(hasHepbDocument) ? null : "true".equals(hasHepbDocument));
    applicantSearchParameters.setHasTuberculosisDocumentOperator("".equals(hasTuberculosisDocument) ? null : hasTuberculosisDocumentOperator);
    session.setAttribute("hasTuberculosisDocumentOperator", hasTuberculosisDocumentOperator);
    applicantSearchParameters.setHasTuberculosisDocument("".equals(hasTuberculosisDocument) ? null : "true".equals(hasTuberculosisDocument));
    applicantSearchParameters.setHasMMRX2DocumentOperator("".equals(hasMMRX2Document) ? null : hasMMRX2DocumentOperator);
    session.setAttribute("hasMMRX2DocumentOperator", hasMMRX2DocumentOperator);
    applicantSearchParameters.setHasMMRX2Document("".equals(hasMMRX2Document) ? null : "true".equals(hasMMRX2Document));
    applicantSearchParameters.setHasMMRDocumentOperator("".equals(hasMMRDocument) ? null : hasMMRDocumentOperator);
    session.setAttribute("hasMMRDocumentOperator", hasMMRDocumentOperator);
    applicantSearchParameters.setHasMMRDocument("".equals(hasMMRDocument) ? null : "true".equals(hasMMRDocument));
    applicantSearchParameters.setHasFitToWorkDocumentOperator("".equals(hasFitToWorkDocument) ? null : hasFitToWorkDocumentOperator);
    session.setAttribute("hasFitToWorkDocumentOperator", hasFitToWorkDocumentOperator);
    applicantSearchParameters.setHasFitToWorkDocument("".equals(hasFitToWorkDocument) ? null : "true".equals(hasFitToWorkDocument));
    applicantSearchParameters.setHasCRBDocumentOperator("".equals(hasCRBDocument) ? null : hasCRBDocumentOperator);
    session.setAttribute("hasCRBDocumentOperator", hasCRBDocumentOperator);
    applicantSearchParameters.setHasCRBDocument("".equals(hasCRBDocument) ? null : "true".equals(hasCRBDocument));
    applicantSearchParameters.setHasHPCDocumentOperator("".equals(hasHPCDocument) ? null : hasHPCDocumentOperator);
    session.setAttribute("hasHPCDocumentOperator", hasHPCDocumentOperator);
    applicantSearchParameters.setHasHPCDocument("".equals(hasHPCDocument) ? null : "true".equals(hasHPCDocument));
    applicantSearchParameters.setHasTrainingCertificateOperator("".equals(hasTrainingCertificate) ? null : hasTrainingCertificateOperator);
    session.setAttribute("hasTrainingCertificateOperator", hasTrainingCertificateOperator);
    applicantSearchParameters.setHasTrainingCertificate("".equals(hasTrainingCertificate) ? null : "true".equals(hasTrainingCertificate));
    applicantSearchParameters.setHasPassportOperator("".equals(hasPassport) ? null : hasPassportOperator);
    session.setAttribute("hasPassportOperator", hasPassportOperator);
    applicantSearchParameters.setHasPassport("".equals(hasPassport) ? null : "true".equals(hasPassport));
    applicantSearchParameters.setHasProofOfAddressOperator("".equals(hasProofOfAddress) ? null : hasProofOfAddressOperator);
    session.setAttribute("hasProofOfAddressOperator", hasProofOfAddressOperator);
    applicantSearchParameters.setHasProofOfAddress("".equals(hasProofOfAddress) ? null : "true".equals(hasProofOfAddress));
    applicantSearchParameters.setHasBirthCertificateOperator("".equals(hasBirthCertificate) ? null : hasBirthCertificateOperator);
    session.setAttribute("hasBirthCertificateOperator", hasBirthCertificateOperator);
    applicantSearchParameters.setHasBirthCertificate("".equals(hasBirthCertificate) ? null : "true".equals(hasBirthCertificate));
    applicantSearchParameters.setHasOverseasPoliceClearanceOperator("".equals(hasOverseasPoliceClearance) ? null : hasOverseasPoliceClearanceOperator);
    session.setAttribute("hasOverseasPoliceClearanceOperator", hasOverseasPoliceClearanceOperator);
    applicantSearchParameters.setHasOverseasPoliceClearance("".equals(hasOverseasPoliceClearance) ? null : "true".equals(hasOverseasPoliceClearance));
    applicantSearchParameters.setHasDegreeOperator("".equals(hasDegree) ? null : hasDegreeOperator);
    session.setAttribute("hasDegreeOperator", hasDegreeOperator);
    applicantSearchParameters.setHasDegree("".equals(hasDegree) ? null : "true".equals(hasDegree));
    applicantSearchParameters.setIsArchivedOperator("".equals(isArchived) ? null : isArchivedOperator);
    session.setAttribute("isArchivedOperator", isArchivedOperator);
    applicantSearchParameters.setIsArchived("".equals(isArchived) ? null : "true".equals(isArchived));
    applicantSearchParameters.setIsCompliantOperator("".equals(isCompliant) ? null : isCompliantOperator);
    session.setAttribute("isCompliantOperator", isCompliantOperator);
    applicantSearchParameters.setIsCompliant("".equals(isCompliant) ? null : "true".equals(isCompliant));

    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    List<Applicant> list = agyService.getApplicantsForAgencySearch(getConsultantLoggedIn().getAgencyId(), applicantSearchParameters, true);
    dynaForm.set("applicantSearchParameters", applicantSearchParameters);
    dynaForm.set("list", list);
    logger.exit("Out going !!!");

    return mapping.findForward("success");

  }
  private Date getDate(SimpleDateFormat sdf, String dateStr, ActionMessages errors, MessageResources messageResources, String messageKey)
  {
    Date date = null;
    try
    {
      date = new Date(sdf.parse(dateStr).getTime());
    }
    catch (ParseException e)
    {
      errors.add("applicantSearch", new ActionMessage("errors.invalid", messageResources.getMessage(messageKey)));
    }
    return date;
  }
}