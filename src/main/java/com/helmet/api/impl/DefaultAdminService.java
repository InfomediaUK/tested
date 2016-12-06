package com.helmet.api.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.helmet.api.AdminService;
import com.helmet.api.exceptions.DataNotFoundException;
import com.helmet.api.exceptions.DuplicateDataException;
import com.helmet.api.exceptions.InvalidDetailException;
import com.helmet.bean.AdminAccess;
import com.helmet.bean.AdminAccessGroup;
import com.helmet.bean.AdminAccessGroupEntity;
import com.helmet.bean.AdminAccessGroupItem;
import com.helmet.bean.Administrator;
import com.helmet.bean.AdministratorAccess;
import com.helmet.bean.AdministratorAccessGroup;
import com.helmet.bean.AdministratorAccessGroupUser;
import com.helmet.bean.AdministratorAccessUser;
import com.helmet.bean.AdministratorEntity;
import com.helmet.bean.Agency;
import com.helmet.bean.AgencyUser;
import com.helmet.bean.AgencyUserEntity;
import com.helmet.bean.AgyAccess;
import com.helmet.bean.AgyAccessGroup;
import com.helmet.bean.AgyAccessGroupEntity;
import com.helmet.bean.AgyAccessGroupItem;
import com.helmet.bean.Applicant;
import com.helmet.bean.BookingDateUserApplicant;
import com.helmet.bean.BudgetTransaction;
import com.helmet.bean.BudgetTransactionUser;
import com.helmet.bean.Client;
import com.helmet.bean.ClientAgencyJobProfile;
import com.helmet.bean.ClientAgencyJobProfileGrade;
import com.helmet.bean.ClientAgencyJobProfileUserEntity;
import com.helmet.bean.ClientReEnterPwd;
import com.helmet.bean.ClientUserEntity;
import com.helmet.bean.Consultant;
import com.helmet.bean.ConsultantAccess;
import com.helmet.bean.ConsultantAccessGroup;
import com.helmet.bean.ConsultantAccessGroupUser;
import com.helmet.bean.ConsultantAccessUser;
import com.helmet.bean.ConsultantEntity;
import com.helmet.bean.Country;
import com.helmet.bean.DisciplineCategory;
import com.helmet.bean.IntValue;
import com.helmet.bean.PassportType;
import com.helmet.bean.VisaType;
import com.helmet.bean.EmailAction;
import com.helmet.bean.AreaOfSpeciality;
import com.helmet.bean.GeographicalRegion;
import com.helmet.bean.JobFamily;
import com.helmet.bean.JobFamilyEntity;
import com.helmet.bean.JobProfile;
import com.helmet.bean.JobProfileGrade;
import com.helmet.bean.JobProfileUserEntity;
import com.helmet.bean.JobSubFamily;
import com.helmet.bean.JobSubFamilyEntity;
import com.helmet.bean.LocationJobProfile;
import com.helmet.bean.NhsBackingReport;
import com.helmet.bean.ReEnterPwd;
import com.helmet.persistence.AdminAccessDAO;
import com.helmet.persistence.AdminAccessGroupDAO;
import com.helmet.persistence.AdminAccessGroupItemDAO;
import com.helmet.persistence.AdministratorAccessDAO;
import com.helmet.persistence.AdministratorAccessGroupDAO;
import com.helmet.persistence.AdministratorDAO;
import com.helmet.persistence.AgyAccessDAO;
import com.helmet.persistence.AgyAccessGroupDAO;
import com.helmet.persistence.AgyAccessGroupItemDAO;
import com.helmet.persistence.ApplicantDAO;
import com.helmet.persistence.BookingGradeApplicantDAO;
import com.helmet.persistence.BudgetTransactionDAO;
import com.helmet.persistence.ClientReEnterPwdDAO;
import com.helmet.persistence.ConsultantAccessDAO;
import com.helmet.persistence.ConsultantAccessGroupDAO;
import com.helmet.persistence.ConsultantDAO;
import com.helmet.persistence.JobFamilyDAO;
import com.helmet.persistence.JobProfileGradeDAO;
import com.helmet.persistence.JobSubFamilyDAO;
import com.helmet.persistence.ReEnterPwdDAO;
import com.helmet.persistence.Utilities;

public class DefaultAdminService extends DefaultCommonService implements AdminService {

  private AdministratorDAO administratorDAO;
  
  private ReEnterPwdDAO reEnterPwdDAO;
  
  private ClientReEnterPwdDAO clientReEnterPwdDAO;
  
  private AdminAccessDAO adminAccessDAO;
  
  private AdministratorAccessDAO administratorAccessDAO;
  
  private AdminAccessGroupDAO adminAccessGroupDAO;
  
  private AdministratorAccessGroupDAO administratorAccessGroupDAO;
  
  private AdminAccessGroupItemDAO adminAccessGroupItemDAO;
  
  private JobFamilyDAO jobFamilyDAO;
  
  private JobSubFamilyDAO jobSubFamilyDAO;
  
  private JobProfileGradeDAO jobProfileGradeDAO;
  
  private ConsultantDAO consultantDAO;
  
  private AgyAccessDAO agyAccessDAO;
  
  private AgyAccessGroupDAO agyAccessGroupDAO;
  
  private AgyAccessGroupItemDAO agyAccessGroupItemDAO;
  
  private ConsultantAccessDAO consultantAccessDAO;
  
  private ConsultantAccessGroupDAO consultantAccessGroupDAO;
  
  private BudgetTransactionDAO budgetTransactionDAO;
  
  private ApplicantDAO applicantDAO;

  private BookingGradeApplicantDAO bookingGradeApplicantDAO;
  
  public ApplicantDAO getApplicantDAO()
  {
    return applicantDAO;
  }

  public void setApplicantDAO(ApplicantDAO applicantDAO)
  {
    this.applicantDAO = applicantDAO;
  }

  public void setAdministratorDAO(AdministratorDAO administratorDAO) {
    this.administratorDAO = administratorDAO;
  }

  public void setReEnterPwdDAO(ReEnterPwdDAO reEnterPwdDAO) {
    this.reEnterPwdDAO = reEnterPwdDAO;
  }

  public void setClientReEnterPwdDAO(ClientReEnterPwdDAO clientReEnterPwdDAO) {
    this.clientReEnterPwdDAO = clientReEnterPwdDAO;
  }

  public void setAdminAccessDAO(AdminAccessDAO adminAccessDAO) {
    this.adminAccessDAO = adminAccessDAO;
  }

  public void setAdministratorAccessDAO(AdministratorAccessDAO administratorAccessDAO) {
    this.administratorAccessDAO = administratorAccessDAO;
  }

  public void setAdminAccessGroupDAO(AdminAccessGroupDAO adminAccessGroupDAO) {
    this.adminAccessGroupDAO = adminAccessGroupDAO;
  }

  public void setAdministratorAccessGroupDAO(AdministratorAccessGroupDAO administratorAccessGroupDAO) {
    this.administratorAccessGroupDAO = administratorAccessGroupDAO;
  }

  public void setAdminAccessGroupItemDAO(AdminAccessGroupItemDAO adminAccessGroupItemDAO) {
    this.adminAccessGroupItemDAO = adminAccessGroupItemDAO;
  }

  public void setJobFamilyDAO(JobFamilyDAO jobFamilyDAO) {
    this.jobFamilyDAO = jobFamilyDAO;
  }

  public void setJobSubFamilyDAO(JobSubFamilyDAO jobSubFamilyDAO) {
    this.jobSubFamilyDAO = jobSubFamilyDAO;
  }

  public void setJobProfileGradeDAO(JobProfileGradeDAO jobProfileGradeDAO) {
    this.jobProfileGradeDAO = jobProfileGradeDAO;
  }

  public void setConsultantDAO(ConsultantDAO consultantDAO) {
    this.consultantDAO = consultantDAO;
  }

  public void setAgyAccessDAO(AgyAccessDAO agyAccessDAO) {
    this.agyAccessDAO = agyAccessDAO;
  }

  public void setAgyAccessGroupDAO(AgyAccessGroupDAO agyAccessGroupDAO) {
    this.agyAccessGroupDAO = agyAccessGroupDAO;
  }

  public void setAgyAccessGroupItemDAO(AgyAccessGroupItemDAO agyAccessGroupItemDAO) {
    this.agyAccessGroupItemDAO = agyAccessGroupItemDAO;
  }

  public void setConsultantAccessDAO(ConsultantAccessDAO consultantAccessDAO) {
    this.consultantAccessDAO = consultantAccessDAO;
  }

  public void setConsultantAccessGroupDAO(
      ConsultantAccessGroupDAO consultantAccessGroupDAO) {
    this.consultantAccessGroupDAO = consultantAccessGroupDAO;
  }

  public void setBudgetTransactionDAO(BudgetTransactionDAO budgetTransactionDAO) {
    this.budgetTransactionDAO = budgetTransactionDAO;
  }

  public void setBookingGradeApplicantDAO(BookingGradeApplicantDAO bookingGradeApplicantDAO)
  {
    this.bookingGradeApplicantDAO = bookingGradeApplicantDAO;
  }
  
  /*
   * Administrator stuff ...
   * 
   */
  
  public List<Administrator> getAdministratorAudits() {

    List<Administrator> administrators = null;
    administrators = administratorDAO.getAdministratorAudits();
    return administrators;
    
  }
  public List<Administrator> getAdministrators(boolean showOnlyActive) {

    List<Administrator> administrators = null;
    administrators = administratorDAO.getAdministrators(showOnlyActive);
    return administrators;
    
  }
  public Administrator getAdministrator(Integer administratorId) {
    
    Administrator administrator = null;
      administrator = administratorDAO.getAdministrator(administratorId);
    return administrator;
    
  }
  public AdministratorEntity getAdministratorEntity(Integer administratorId) {

    AdministratorEntity administratorEntity = null;
    administratorEntity = administratorDAO.getAdministratorEntity(administratorId);
    administratorEntity.setAdministratorAccessUsers(administratorAccessDAO.getAdministratorAccessUsersForAdministrator(administratorId));
    administratorEntity.setAdminAccesses(adminAccessDAO.getAdminAccessesNotForAdministrator(administratorId));
    administratorEntity.setAdministratorAccessGroupUsers(administratorAccessGroupDAO.getAdministratorAccessGroupUsersForAdministrator(administratorId));
    administratorEntity.setAdminAccessGroups(adminAccessGroupDAO.getAdminAccessGroupsNotForAdministrator(administratorId));
    return administratorEntity;

  }
  public int insertAdministrator(Administrator administrator, Integer administratorId) {

    Administrator duplicateAdministrator = administratorDAO.getAdministratorForLogin(administrator.getUser().getLogin());
    if (duplicateAdministrator != null) {
      throw new DuplicateDataException("login");
    }
    int rc = administratorDAO.insertAdministrator(administrator, administratorId);
        return rc;      
  
  }
  public int updateAdministrator(Administrator administrator, Integer auditorId) {

    Administrator duplicateAdministrator = administratorDAO.getAdministratorForLogin(administrator.getUser().getLogin());
    if (duplicateAdministrator != null && 
      !duplicateAdministrator.getAdministratorId().equals(administrator.getAdministratorId())) {
      throw new DuplicateDataException("login");
    }
    int rc = administratorDAO.updateAdministrator(administrator, auditorId);
    return rc;
  
  }
  public int deleteAdministrator(Integer administratorId, Integer noOfChanges, Integer auditorId) {

    int rc = administratorDAO.deleteAdministrator(administratorId, noOfChanges, auditorId);
    return rc;
  
  }
  public Administrator validateLogin(Administrator administrator) {

    Administrator administratorX = administratorDAO.getAdministratorForLogin(administrator.getUser().getLogin());
    if (administratorX == null) {
      throw new DataNotFoundException();
    }
    String encryptedPwd = Utilities.encryptPassword(administrator.getUser().getPwd());
    if (!encryptedPwd.equals(administratorX.getUser().getPwd())) {
      administrator.getUser().setPwdHint(administratorX.getUser().getPwdHint());
      throw new InvalidDetailException();
    }
      return administratorX;
  
  }
  
  
  public Administrator validateSecretWord(Administrator administrator) {

    Administrator administratorX = administratorDAO.getAdministratorForLogin(administrator.getUser().getLogin());
    if (administratorX == null) {
      throw new DataNotFoundException();
    }
    if (!administrator.getUser().getSecretWord().equalsIgnoreCase(administratorX.getUser().getSecretWord())) {
      throw new InvalidDetailException();
    }
    administrator.getUser().setPwdHint(administratorX.getUser().getPwdHint());
    administrator.setAdministratorId(administratorX.getAdministratorId());
    return administratorX;

  }

    public int updateAdministratorPwd(Integer administratorId, String newPwd, String pwdHint, Integer noOfChanges, Integer auditorId) {

    int rc = administratorDAO.updateAdministratorPwd(administratorId, newPwd, pwdHint, noOfChanges, auditorId);
    return rc;

    }
  
    public int updateAdministratorSecretWord(Integer administratorId, String newSecretWord, Integer noOfChanges, Integer auditorId) {

    int rc = administratorDAO.updateAdministratorSecretWord(administratorId, newSecretWord, noOfChanges, auditorId);
    return rc;

    }

    
  
  
  
  
  
  /*
   * Country stuff ...
   * 
   */

//  public List<Country> getCountries() {
//
//    return getCountries(false);
//    
//  }
  public Country getCountry(Integer countryId) {
    
    Country country = null;
    country = getCountryDAO().getCountry(countryId);
    return country;
    
  }
  public int insertCountry(Country country, Integer auditorId) {

    Country duplicateCountry = getCountryDAO().getCountryForName(country.getName());
    if (duplicateCountry != null) {
      throw new DuplicateDataException("name");
    }
    duplicateCountry = getCountryDAO().getCountryForIsoCode(country.getIsoCode());
    if (duplicateCountry != null) {
      throw new DuplicateDataException("isoCode");
    }
    int rc = getCountryDAO().insertCountry(country, auditorId);
    return rc;
  
  }
  public int updateCountry(Country country, Integer auditorId) {

    Country duplicateCountry = getCountryDAO().getCountryForName(country.getName());
    if (duplicateCountry != null && 
      !duplicateCountry.getCountryId().equals(country.getCountryId())) {
      throw new DuplicateDataException("name");
    }
    duplicateCountry = getCountryDAO().getCountryForIsoCode(country.getIsoCode());
    if (duplicateCountry != null && 
      !duplicateCountry.getCountryId().equals(country.getCountryId())) {
      throw new DuplicateDataException("isoCode");
    }
    int rc = getCountryDAO().updateCountry(country, auditorId);
    return rc;
  
  }
  public int deleteCountry(Integer countryId, Integer noOfChanges, Integer auditorId) {

    int rc = getCountryDAO().deleteCountry(countryId, noOfChanges, auditorId);
    return rc;
  
  }
// NEW -->
  public EmailAction getEmailAction(Integer emailActionId) {
    
    EmailAction emailAction = null;
    emailAction = getEmailActionDAO().getEmailAction(emailActionId);
    return emailAction;
  }
  public int insertEmailAction(EmailAction emailAction, Integer auditorId) {

    EmailAction duplicateEmailAction = getEmailActionDAO().getEmailActionForName(emailAction.getName());
    if (duplicateEmailAction != null) {
      throw new DuplicateDataException("name");
    }
    int rc = getEmailActionDAO().insertEmailAction(emailAction, auditorId);
    return rc;
  }
  public int updateEmailAction(EmailAction emailAction, Integer auditorId) {

    EmailAction duplicateEmailAction = getEmailActionDAO().getEmailActionForName(emailAction.getName());
    if (duplicateEmailAction != null && 
      !duplicateEmailAction.getEmailActionId().equals(emailAction.getEmailActionId())) {
      throw new DuplicateDataException("name");
    }
    int rc = getEmailActionDAO().updateEmailAction(emailAction, auditorId);
    return rc;
  }
  public int deleteEmailAction(Integer emailActionId, Integer noOfChanges, Integer auditorId) {

    int rc = getEmailActionDAO().deleteEmailAction(emailActionId, noOfChanges, auditorId);
    return rc;
  }
//<-- NEW
//NEW -->
  public Applicant getApplicant(Integer applicantId) 
  {
    Applicant applicant = null;
    applicant = applicantDAO.getApplicant(applicantId);
    return applicant;
  }

  public List<Applicant> getApplicantsToCopy(Integer sourceAgencyId, Integer targetAgencyId)
  {
    List<Applicant> applicantsToCopy = null;
    applicantsToCopy = getApplicantDAO().getApplicantsToCopy(sourceAgencyId, targetAgencyId);
    return applicantsToCopy;
  }

  public int insertApplicant(Applicant applicant, Integer auditorId) 
  {
    Applicant duplicateApplicant = applicantDAO.getApplicantForLogin(applicant.getAgencyId(), applicant.getUser().getLogin());
    if (duplicateApplicant != null) 
    {
      throw new DuplicateDataException("login");
    }
    int rc = applicantDAO.insertApplicant(applicant, auditorId);
    return rc;
  }
  
  public NhsBackingReport getNhsBackingReport(Integer nhsBackingReportId) {
    
    NhsBackingReport nhsBackingReport = null;
    nhsBackingReport = getNhsBackingReportDAO().getNhsBackingReport(nhsBackingReportId);
    return nhsBackingReport;
  }
  
//<-- NEW
  public GeographicalRegion getGeographicalRegion(Integer geographicalRegionId) {
    
    GeographicalRegion geographicalRegion = null;
    geographicalRegion = getGeographicalRegionDAO().getGeographicalRegion(geographicalRegionId);
    return geographicalRegion;
    
  }
  public int insertGeographicalRegion(GeographicalRegion geographicalRegion, Integer geographicalRegionId) {

    GeographicalRegion duplicateGeographicalRegion = getGeographicalRegionDAO().getGeographicalRegionForName(geographicalRegion.getName());
    if (duplicateGeographicalRegion != null) {
      throw new DuplicateDataException("name");
    }
    duplicateGeographicalRegion = getGeographicalRegionDAO().getGeographicalRegionForCode(geographicalRegion.getCode());
    if (duplicateGeographicalRegion != null) {
      throw new DuplicateDataException("code");
    }
    int rc = getGeographicalRegionDAO().insertGeographicalRegion(geographicalRegion, geographicalRegionId);
    return rc;
  
  }
  public int updateGeographicalRegion(GeographicalRegion geographicalRegion, Integer auditorId) {

    GeographicalRegion duplicateGeographicalRegion = getGeographicalRegionDAO().getGeographicalRegionForName(geographicalRegion.getName());
    if (duplicateGeographicalRegion != null && 
      !duplicateGeographicalRegion.getGeographicalRegionId().equals(geographicalRegion.getGeographicalRegionId())) {
      throw new DuplicateDataException("name");
    }
    duplicateGeographicalRegion = getGeographicalRegionDAO().getGeographicalRegionForCode(geographicalRegion.getCode());
    if (duplicateGeographicalRegion != null && 
      !duplicateGeographicalRegion.getGeographicalRegionId().equals(geographicalRegion.getGeographicalRegionId())) {
      throw new DuplicateDataException("code");
    }
    int rc = getGeographicalRegionDAO().updateGeographicalRegion(geographicalRegion, auditorId);
    return rc;
  
  }
  public int deleteGeographicalRegion(Integer geographicalRegionId, Integer noOfChanges, Integer auditorId) {

    int rc = getGeographicalRegionDAO().deleteGeographicalRegion(geographicalRegionId, noOfChanges, auditorId);
    return rc;
  
  }
  public AreaOfSpeciality getAreaOfSpeciality(Integer areaOfSpecialityId) {
    
    AreaOfSpeciality areaOfSpeciality = null;
    areaOfSpeciality = getAreaOfSpecialityDAO().getAreaOfSpeciality(areaOfSpecialityId);
    return areaOfSpeciality;
    
  }
  public int insertAreaOfSpeciality(AreaOfSpeciality areaOfSpeciality, Integer areaOfSpecialityId) {

    AreaOfSpeciality duplicateAreaOfSpeciality = getAreaOfSpecialityDAO().getAreaOfSpecialityForName(areaOfSpeciality.getName());
    if (duplicateAreaOfSpeciality != null) {
      throw new DuplicateDataException("name");
    }
    int rc = getAreaOfSpecialityDAO().insertAreaOfSpeciality(areaOfSpeciality, areaOfSpecialityId);
    return rc;
  
  }
  public int updateAreaOfSpeciality(AreaOfSpeciality areaOfSpeciality, Integer auditorId) {

    AreaOfSpeciality duplicateAreaOfSpeciality = getAreaOfSpecialityDAO().getAreaOfSpecialityForName(areaOfSpeciality.getName());
    if (duplicateAreaOfSpeciality != null && 
      !duplicateAreaOfSpeciality.getAreaOfSpecialityId().equals(areaOfSpeciality.getAreaOfSpecialityId())) {
      throw new DuplicateDataException("name");
    }
    int rc = getAreaOfSpecialityDAO().updateAreaOfSpeciality(areaOfSpeciality, auditorId);
    return rc;
  
  }
  public int deleteAreaOfSpeciality(Integer areaOfSpecialityId, Integer noOfChanges, Integer auditorId) {

    int rc = getAreaOfSpecialityDAO().deleteAreaOfSpeciality(areaOfSpecialityId, noOfChanges, auditorId);
    return rc;
  
  }
  public List<com.helmet.xml.jaxb.model.DisciplineCategory> getJerseyDisciplineCategories(boolean showOnlyActive) 
  {
    List<DisciplineCategory> disciplineCategories = null;
    disciplineCategories = getDisciplineCategoryDAO().getDisciplineCategories(showOnlyActive);
    List<com.helmet.xml.jaxb.model.DisciplineCategory> jerseyDisciplineCategories = new ArrayList<com.helmet.xml.jaxb.model.DisciplineCategory>();
    for (DisciplineCategory disciplineCategory : disciplineCategories)
    {
      jerseyDisciplineCategories.add(fillJerseyDisciplineCategory(disciplineCategory));
    }
    return jerseyDisciplineCategories;
  }
  public com.helmet.xml.jaxb.model.DisciplineCategory getJerseyDisciplineCategory(Integer disciplineCategoryId)
  {
    DisciplineCategory disciplineCategory = null;
    disciplineCategory = getDisciplineCategoryDAO().getDisciplineCategory(disciplineCategoryId);
    if (disciplineCategory == null)
    {
      throw new RuntimeException("Get: Discipline with " + disciplineCategoryId + " not found");
    }
    return fillJerseyDisciplineCategory(disciplineCategory);
  }
  private com.helmet.xml.jaxb.model.DisciplineCategory fillJerseyDisciplineCategory(DisciplineCategory disciplineCategory)
  {
    com.helmet.xml.jaxb.model.DisciplineCategory jerseyDisciplineCategory = new com.helmet.xml.jaxb.model.DisciplineCategory();
    jerseyDisciplineCategory.setId(disciplineCategory.getDisciplineCategoryId());
    jerseyDisciplineCategory.setCode(disciplineCategory.getCode());
    jerseyDisciplineCategory.setName(disciplineCategory.getName());
    jerseyDisciplineCategory.setDisplayOrder(disciplineCategory.getDisplayOrder());
    return jerseyDisciplineCategory;
  }
  public DisciplineCategory getDisciplineCategory(Integer disciplineCategoryId) 
  {
    DisciplineCategory disciplineCategory = null;
    disciplineCategory = getDisciplineCategoryDAO().getDisciplineCategory(disciplineCategoryId);
    return disciplineCategory;
  }
  public DisciplineCategory getDisciplineCategoryForCode(String code) 
  {
    DisciplineCategory disciplineCategory = null;
    disciplineCategory = getDisciplineCategoryDAO().getDisciplineCategoryForCode(code);
    return disciplineCategory;
  }
  public DisciplineCategory getDisciplineCategoryForName(String name) 
  {
    DisciplineCategory disciplineCategory = null;
    disciplineCategory = getDisciplineCategoryDAO().getDisciplineCategoryForCode(name);
    return disciplineCategory;
  }
  public int insertDisciplineCategory(DisciplineCategory disciplineCategory, Integer disciplineCategoryId) 
  {
    DisciplineCategory duplicateDisciplineCategory = getDisciplineCategoryDAO().getDisciplineCategoryForName(disciplineCategory.getName());
    if (duplicateDisciplineCategory != null) {
      throw new DuplicateDataException("name");
    }
    duplicateDisciplineCategory = getDisciplineCategoryDAO().getDisciplineCategoryForCode(disciplineCategory.getCode());
    if (duplicateDisciplineCategory != null) {
      throw new DuplicateDataException("code");
    }
    int rc = getDisciplineCategoryDAO().insertDisciplineCategory(disciplineCategory, disciplineCategoryId);
    return rc;
  }
  public int updateDisciplineCategory(DisciplineCategory disciplineCategory, Integer auditorId) 
  {
    DisciplineCategory duplicateDisciplineCategory = getDisciplineCategoryDAO().getDisciplineCategoryForName(disciplineCategory.getName());
    if (duplicateDisciplineCategory != null && 
      !duplicateDisciplineCategory.getDisciplineCategoryId().equals(disciplineCategory.getDisciplineCategoryId())) {
      throw new DuplicateDataException("name");
    }
    duplicateDisciplineCategory = getDisciplineCategoryDAO().getDisciplineCategoryForCode(disciplineCategory.getCode());
    if (duplicateDisciplineCategory != null && 
      !duplicateDisciplineCategory.getDisciplineCategoryId().equals(disciplineCategory.getDisciplineCategoryId())) {
      throw new DuplicateDataException("code");
    }
    int rc = getDisciplineCategoryDAO().updateDisciplineCategory(disciplineCategory, auditorId);
    return rc;
  }
  public int updateDisciplineCategoryDisplayOrder(DisciplineCategory disciplineCategory, Integer auditorId) 
  {
    int rc = getDisciplineCategoryDAO().updateDisciplineCategory(disciplineCategory, auditorId);
    return rc;
  }
  public int deleteDisciplineCategory(Integer disciplineCategoryId, Integer noOfChanges, Integer auditorId){
    int rc = getDisciplineCategoryDAO().deleteDisciplineCategory(disciplineCategoryId, noOfChanges, auditorId);
    return rc;
  }
  public List<com.helmet.xml.jaxb.model.VisaType> getJerseyVisaTypes(boolean showOnlyActive) 
  {
    List<VisaType> visaTypes = null;
    visaTypes = getVisaTypeDAO().getVisaTypes(showOnlyActive);
    List<com.helmet.xml.jaxb.model.VisaType> jerseyVisaTypes = new ArrayList<com.helmet.xml.jaxb.model.VisaType>();
    for (VisaType visaType : visaTypes)
    {
      jerseyVisaTypes.add(fillJerseyVisaType(visaType));
    }
    return jerseyVisaTypes;
  }
  public com.helmet.xml.jaxb.model.VisaType getJerseyVisaType(Integer visaTypeId)
  {
    VisaType visaType = null;
    visaType = getVisaTypeDAO().getVisaType(visaTypeId);
    if (visaType == null)
    {
      throw new RuntimeException("Get: VisaType with " + visaTypeId + " not found");
    }
    return fillJerseyVisaType(visaType);
  }
  private com.helmet.xml.jaxb.model.VisaType fillJerseyVisaType(VisaType visaType)
  {
    com.helmet.xml.jaxb.model.VisaType jerseyVisaType = new com.helmet.xml.jaxb.model.VisaType();
    jerseyVisaType.setId(visaType.getVisaTypeId());
    jerseyVisaType.setCode(visaType.getCode());
    jerseyVisaType.setName(visaType.getName());
    jerseyVisaType.setDisplayOrder(visaType.getDisplayOrder());
    return jerseyVisaType;
  }
  public VisaType getVisaType(Integer visaTypeId) 
  {
    VisaType visaType = null;
    visaType = getVisaTypeDAO().getVisaType(visaTypeId);
    return visaType;
  }
  public VisaType getVisaTypeForCode(String code) 
  {
    
    VisaType visaType = null;
    visaType = getVisaTypeDAO().getVisaTypeForCode(code);
    return visaType;
    
  }
  public VisaType getVisaTypeForName(String name) 
  {
    
    VisaType visaType = null;
    visaType = getVisaTypeDAO().getVisaTypeForCode(name);
    return visaType;
    
  }
  public int insertVisaType(VisaType visaType, Integer visaTypeId) 
  {
    VisaType duplicateVisaType = getVisaTypeDAO().getVisaTypeForName(visaType.getName());
    if (duplicateVisaType != null) {
      throw new DuplicateDataException("name");
    }
    duplicateVisaType = getVisaTypeDAO().getVisaTypeForCode(visaType.getCode());
    if (duplicateVisaType != null) {
      throw new DuplicateDataException("code");
    }
    int rc = getVisaTypeDAO().insertVisaType(visaType, visaTypeId);
    return rc;
  }
  public int updateVisaType(VisaType visaType, Integer auditorId) 
  {
    VisaType duplicateVisaType = getVisaTypeDAO().getVisaTypeForName(visaType.getName());
    if (duplicateVisaType != null && 
      !duplicateVisaType.getVisaTypeId().equals(visaType.getVisaTypeId())) {
      throw new DuplicateDataException("name");
    }
    duplicateVisaType = getVisaTypeDAO().getVisaTypeForCode(visaType.getCode());
    if (duplicateVisaType != null && 
      !duplicateVisaType.getVisaTypeId().equals(visaType.getVisaTypeId())) {
      throw new DuplicateDataException("code");
    }
    int rc = getVisaTypeDAO().updateVisaType(visaType, auditorId);
    return rc;
  }
  public int updateVisaTypeDisplayOrder(VisaType visaType, Integer auditorId) 
  {
    int rc = getVisaTypeDAO().updateVisaType(visaType, auditorId);
    return rc;
  }
  public int deleteVisaType(Integer visaTypeId, Integer noOfChanges, Integer auditorId){
    int rc = getVisaTypeDAO().deleteVisaType(visaTypeId, noOfChanges, auditorId);
    return rc;
  }
  public List<com.helmet.xml.jaxb.model.PassportType> getJerseyPassportTypes(boolean showOnlyActive) 
  {
    List<PassportType> passportTypes = null;
    passportTypes = getPassportTypeDAO().getPassportTypes(showOnlyActive);
    List<com.helmet.xml.jaxb.model.PassportType> jerseyPassportTypes = new ArrayList<com.helmet.xml.jaxb.model.PassportType>();
    for (PassportType passportType : passportTypes)
    {
      jerseyPassportTypes.add(fillJerseyPassportType(passportType));
    }
    return jerseyPassportTypes;
  }
  public com.helmet.xml.jaxb.model.PassportType getJerseyPassportType(Integer passportTypeId)
  {
    PassportType passportType = null;
    passportType = getPassportTypeDAO().getPassportType(passportTypeId);
    if (passportType == null)
    {
      throw new RuntimeException("Get: PassportType with " + passportTypeId + " not found");
    }
    return fillJerseyPassportType(passportType);
  }
  private com.helmet.xml.jaxb.model.PassportType fillJerseyPassportType(PassportType passportType)
  {
    com.helmet.xml.jaxb.model.PassportType jerseyPassportType = new com.helmet.xml.jaxb.model.PassportType();
    jerseyPassportType.setId(passportType.getPassportTypeId());
    jerseyPassportType.setCode(passportType.getCode());
    jerseyPassportType.setName(passportType.getName());
    jerseyPassportType.setDisplayOrder(passportType.getDisplayOrder());
    return jerseyPassportType;
  }
  public PassportType getPassportType(Integer passportTypeId) 
  {
    PassportType passportType = null;
    passportType = getPassportTypeDAO().getPassportType(passportTypeId);
    return passportType;
  }
  public PassportType getPassportTypeForCode(String code) 
  {
    
    PassportType passportType = null;
    passportType = getPassportTypeDAO().getPassportTypeForCode(code);
    return passportType;
    
  }
  public PassportType getPassportTypeForName(String name) 
  {
    
    PassportType passportType = null;
    passportType = getPassportTypeDAO().getPassportTypeForCode(name);
    return passportType;
    
  }
  public int insertPassportType(PassportType passportType, Integer passportTypeId) 
  {
    PassportType duplicatePassportType = getPassportTypeDAO().getPassportTypeForName(passportType.getName());
    if (duplicatePassportType != null) {
      throw new DuplicateDataException("name");
    }
    duplicatePassportType = getPassportTypeDAO().getPassportTypeForCode(passportType.getCode());
    if (duplicatePassportType != null) {
      throw new DuplicateDataException("code");
    }
    int rc = getPassportTypeDAO().insertPassportType(passportType, passportTypeId);
    return rc;
  }
  public int updatePassportType(PassportType passportType, Integer auditorId) 
  {
    PassportType duplicatePassportType = getPassportTypeDAO().getPassportTypeForName(passportType.getName());
    if (duplicatePassportType != null && 
      !duplicatePassportType.getPassportTypeId().equals(passportType.getPassportTypeId())) {
      throw new DuplicateDataException("name");
    }
    duplicatePassportType = getPassportTypeDAO().getPassportTypeForCode(passportType.getCode());
    if (duplicatePassportType != null && 
      !duplicatePassportType.getPassportTypeId().equals(passportType.getPassportTypeId())) {
      throw new DuplicateDataException("code");
    }
    int rc = getPassportTypeDAO().updatePassportType(passportType, auditorId);
    return rc;
  }
  public int updatePassportTypeDisplayOrder(PassportType passportType, Integer auditorId) 
  {
    int rc = getPassportTypeDAO().updatePassportType(passportType, auditorId);
    return rc;
  }
  public int deletePassportType(Integer passportTypeId, Integer noOfChanges, Integer auditorId){
    int rc = getPassportTypeDAO().deletePassportType(passportTypeId, noOfChanges, auditorId);
    return rc;
  }
  public List<com.helmet.xml.jaxb.model.Agency> getJerseyAgencies(boolean showOnlyActive) 
  {
    List<Agency> agencies = null;
    agencies = getAgencyDAO().getAgencies(showOnlyActive);
    List<com.helmet.xml.jaxb.model.Agency> jerseyAgencies = new ArrayList<com.helmet.xml.jaxb.model.Agency>();
    for (Agency agency : agencies)
    {
      jerseyAgencies.add(fillJerseyAgency(agency));
    }
    return jerseyAgencies;
  }
  public com.helmet.xml.jaxb.model.Agency getJerseyAgency(Integer agencyId)
  {
    Agency agency = null;
    agency = getAgencyDAO().getAgency(agencyId);
    if (agency == null)
    {
      throw new RuntimeException("Get: Agency with " + agencyId + " not found");
    }
    return fillJerseyAgency(agency);
  }
  private com.helmet.xml.jaxb.model.Agency fillJerseyAgency(Agency agency)
  {
    com.helmet.xml.jaxb.model.Agency jerseyAgency = new com.helmet.xml.jaxb.model.Agency();
    jerseyAgency.setId(agency.getAgencyId());
    jerseyAgency.setCode(agency.getCode());
    jerseyAgency.setName(agency.getName());
    jerseyAgency.setDisplayOrder(agency.getDisplayOrder());
    return jerseyAgency;
  }
  public List<ReEnterPwd> getReEnterPwds(boolean showOnlyActive) {

    List<ReEnterPwd> reEnterPwds = null;
    reEnterPwds = reEnterPwdDAO.getReEnterPwds(showOnlyActive);
    return reEnterPwds;
    
  }
  public ReEnterPwd getReEnterPwd(Integer reEnterPwdId) {
    
    ReEnterPwd reEnterPwd = null;
    reEnterPwd = reEnterPwdDAO.getReEnterPwd(reEnterPwdId);
    return reEnterPwd;
    
  }
  public int insertReEnterPwd(ReEnterPwd reEnterPwd, Integer reEnterPwdId) {

    ReEnterPwd duplicateReEnterPwd = reEnterPwdDAO.getReEnterPwdForName(reEnterPwd.getName());
    if (duplicateReEnterPwd != null) {
      throw new DuplicateDataException("name");
    }
    int rc = reEnterPwdDAO.insertReEnterPwd(reEnterPwd, reEnterPwdId);
    return rc;
  
  }
  public int updateReEnterPwd(ReEnterPwd reEnterPwd, Integer auditorId) {

    ReEnterPwd duplicateReEnterPwd = reEnterPwdDAO.getReEnterPwdForName(reEnterPwd.getName());
    if (duplicateReEnterPwd != null && 
      !duplicateReEnterPwd.getReEnterPwdId().equals(reEnterPwd.getReEnterPwdId())) {
      throw new DuplicateDataException("name");
    }
    int rc = reEnterPwdDAO.updateReEnterPwd(reEnterPwd, auditorId);
    return rc;
  
  }
  public int deleteReEnterPwd(Integer reEnterPwdId, Integer noOfChanges, Integer auditorId) {

    int rc = reEnterPwdDAO.deleteReEnterPwd(reEnterPwdId, noOfChanges, auditorId);
    return rc;
  
  }

  
  /*
   * Client stuff ...
   * 
   */

  public Client getClient(Integer clientId) {
    
    Client client = null;
    client = getClientDAO().getClient(clientId);
    return client;
    
  }

  public Client getClientForReference(String reference) 
  {
    Client client = null;
    client = getClientDAO().getClientForReference(reference);
    return client;
  }

  public List<Client> getClientsForTradeshiftSbsPayablesCode(String tradeshiftSbsPayablesCode) 
  {
    List<Client> clients = null;
    clients = getClientDAO().getClientsForTradeshiftSbsPayablesCode(tradeshiftSbsPayablesCode);
    return clients;
  }

  public ClientUserEntity getClientUserEntity(Integer clientId) {
    
    return getClientUserEntity(clientId, true);
  
  }

  
  public ClientUserEntity getClientUserEntity(Integer clientId, boolean showOnlyActive) {

    ClientUserEntity clientUserEntity = null;
    clientUserEntity = getClientDAO().getClientUserEntity(clientId);
    clientUserEntity.setSites(getSiteDAO().getSiteUsersForClient(clientId, showOnlyActive));
    clientUserEntity.setManagers(getManagerDAO().getManagersForClient(clientId, showOnlyActive));
    clientUserEntity.setJobFamilies(jobFamilyDAO.getJobFamiliesForClient(clientId, showOnlyActive));
    clientUserEntity.setGrades(getGradeDAO().getGradesForClient(clientId, showOnlyActive));
    clientUserEntity.setClientAgencies(getClientAgencyDAO().getClientAgencyUsersForClient(clientId));
    clientUserEntity.setAgencies(getAgencyDAO().getAgencyUsersNotForClient(clientId));
    clientUserEntity.setReasonForRequests(getReasonForRequestDAO().getReasonForRequestsForClient(clientId, showOnlyActive));
    clientUserEntity.setPublicHolidays(getPublicHolidayDAO().getPublicHolidaysForClient(clientId, showOnlyActive));
//    clientUserEntity.setUplifts(upliftDAO.getUpliftsForClient(clientId, showOnlyActive));
    clientUserEntity.setMgrAccessGroups(getMgrAccessGroupDAO().getMgrAccessGroupsForClient(clientId, showOnlyActive));
    
    clientUserEntity.setClientReEnterPwds(clientReEnterPwdDAO.getClientReEnterPwdUsersForClient(clientId));
    clientUserEntity.setReEnterPwds(reEnterPwdDAO.getReEnterPwdsNotForClient(clientId));
    
    return clientUserEntity;

  }

  public List<ClientUserEntity> getClientUserEntities(boolean showOnlyActive) {
    
    List<ClientUserEntity> clientUserEntities = null;
    clientUserEntities = getClientDAO().getClientUserEntities(showOnlyActive);
    
    for (ClientUserEntity clientUserEntity: clientUserEntities) {
      clientUserEntity.setSites(getSiteDAO().getSiteUsersForClient(clientUserEntity.getClientId(), showOnlyActive));
      clientUserEntity.setManagers(getManagerDAO().getManagersForClient(clientUserEntity.getClientId(), showOnlyActive));
    }
    
    return clientUserEntities;
    
  }
  public int insertClient(Client client, Integer auditorId) {

    Client duplicateClient = getClientDAO().getClientForName(client.getName());
    if (duplicateClient != null) {
      throw new DuplicateDataException("name");
    }
    duplicateClient = getClientDAO().getClientForCode(client.getCode());
    if (duplicateClient != null) {
      throw new DuplicateDataException("code");
    }
    
    int rc = getClientDAO().insertClient(client, auditorId);
    return rc;
  
  }
  
  // update moved to common service

  public int deleteClient(Integer clientId, Integer noOfChanges, Integer auditorId) {

    int rc = getClientDAO().deleteClient(clientId, noOfChanges, auditorId);
    return rc;
  
  }
  
  public int updateClientDisplayOrder(String order, boolean zeroiseDisplayOrder, Integer auditorId) {
    int rc = 0;

    StringTokenizer stringTokenizer = new StringTokenizer(order, "|");
    String commaDelimitedKey = null;
    int displayOrder = 0;
    while (stringTokenizer.hasMoreTokens())
    {
        commaDelimitedKey = stringTokenizer.nextToken();
        if (!zeroiseDisplayOrder)
        {
          // Increment the display order.
          displayOrder++;
        }
      StringTokenizer st = new StringTokenizer(commaDelimitedKey, ",");
      Integer clientId = Integer.parseInt(st.nextToken());
      Integer noOfChanges = Integer.parseInt(st.nextToken());
        rc += getClientDAO().updateClientDisplayOrder(clientId, displayOrder, noOfChanges, auditorId);
    }
    
    return rc;
  }
  
  /*
   * AdminAccess stuff
   * 
   */

  public List<AdminAccess> getAdminAccesses(boolean showOnlyActive) {

    List<AdminAccess> adminAccesses = null;
    adminAccesses = adminAccessDAO.getAdminAccesses(showOnlyActive);
    return adminAccesses;

  }
  public List<AdminAccess> getActiveAdminAccessesForAdministrator(Integer administratorId) {

    List<AdminAccess> adminAccesses = null;
    adminAccesses = adminAccessDAO.getActiveAdminAccessesForAdministrator(administratorId);
    return adminAccesses;

  }
  public AdminAccess getAdminAccess(Integer adminAccessId) {

    AdminAccess adminAccess = null;
    adminAccess = adminAccessDAO.getAdminAccess(adminAccessId);
    return adminAccess;

  }
  public int insertAdminAccess(AdminAccess adminAccess, Integer auditorId) {

    AdminAccess duplicateAdminAccess = adminAccessDAO.getAdminAccessForName(adminAccess.getName());
    if (duplicateAdminAccess != null) {
      throw new DuplicateDataException("name");
    }
    int rc = adminAccessDAO.insertAdminAccess(adminAccess, auditorId);
    return rc;

  }

  public int updateAdminAccess(AdminAccess adminAccess, Integer auditorId) {

    AdminAccess duplicateAdminAccess = adminAccessDAO.getAdminAccessForName(adminAccess.getName());
    if (duplicateAdminAccess  != null && 
      !duplicateAdminAccess.getAdminAccessId().equals(adminAccess.getAdminAccessId())) {
      throw new DuplicateDataException("name");
    }
        int rc = adminAccessDAO.updateAdminAccess(adminAccess, auditorId);
    return rc;

  }

  public int deleteAdminAccess(Integer adminAccessId, Integer noOfChanges, Integer auditorId) {

    int rc = adminAccessDAO.deleteAdminAccess(adminAccessId, noOfChanges, auditorId);
    return rc;

  }

  /*
   * AdministratorAccess stuff
   * 
   */

  public List<AdministratorAccessUser> getAdministratorAccessUsersForAdministrator(Integer administratorId) {

    List<AdministratorAccessUser> administratorAccesses = null;
    administratorAccesses = administratorAccessDAO.getAdministratorAccessUsersForAdministrator(administratorId);
    return administratorAccesses;

  }

  public int insertAdministratorAccess(AdministratorAccess administratorAccess, Integer auditorId) {

        int rc = administratorAccessDAO.insertAdministratorAccess(administratorAccess, auditorId);
    return rc;

  }

  public int deleteAdministratorAccess(Integer administratorAccessId, Integer noOfChanges, Integer auditorId) {

    int rc = administratorAccessDAO.deleteAdministratorAccess(administratorAccessId, noOfChanges, auditorId);
    return rc;

  }

  /*
   * AdminAccessGroup stuff
   * 
   */
  
  public List<AdminAccessGroup> getAdminAccessGroups(boolean showOnlyActive) {

    List<AdminAccessGroup> adminAccessGroups = null;
    adminAccessGroups = adminAccessGroupDAO.getAdminAccessGroups(showOnlyActive);
    return adminAccessGroups;

  }
  public AdminAccessGroup getAdminAccessGroup(Integer adminAccessGroupId) {

    AdminAccessGroup adminAccessGroup = null;
    adminAccessGroup = adminAccessGroupDAO.getAdminAccessGroup(adminAccessGroupId);
    return adminAccessGroup;

  }
  public AdminAccessGroupEntity getAdminAccessGroupEntity(Integer adminAccessGroupId) {

    AdminAccessGroupEntity adminAccessGroupEntity = null;
    adminAccessGroupEntity = adminAccessGroupDAO.getAdminAccessGroupEntity(adminAccessGroupId);
    adminAccessGroupEntity.setAdminAccessGroupItemUsers(adminAccessGroupItemDAO.getAdminAccessGroupItemUsersForAdminAccessGroup(adminAccessGroupId));
    adminAccessGroupEntity.setAdminAccesses(adminAccessDAO.getAdminAccessesNotForAdminAccessGroup(adminAccessGroupId));
    return adminAccessGroupEntity;

  }
  public int insertAdminAccessGroup(AdminAccessGroup adminAccessGroup, Integer auditorId) {

    AdminAccessGroup duplicateAdminAccessGroup = adminAccessGroupDAO.getAdminAccessGroupForName(adminAccessGroup.getName());
    if (duplicateAdminAccessGroup != null) {
      throw new DuplicateDataException("name");
    }
    int rc = adminAccessGroupDAO.insertAdminAccessGroup(adminAccessGroup, auditorId);
    return rc;

  }

  public int updateAdminAccessGroup(AdminAccessGroup adminAccessGroup, Integer auditorId) {

    AdminAccessGroup duplicateAdminAccessGroup = adminAccessGroupDAO.getAdminAccessGroupForName(adminAccessGroup.getName());
    if (duplicateAdminAccessGroup  != null && 
      !duplicateAdminAccessGroup.getAdminAccessGroupId().equals(adminAccessGroup.getAdminAccessGroupId())) {
      throw new DuplicateDataException("name");
    }
        int rc = adminAccessGroupDAO.updateAdminAccessGroup(adminAccessGroup, auditorId);
    return rc;

  }

  public int deleteAdminAccessGroup(Integer adminAccessGroupId, Integer noOfChanges, Integer auditorId) {

    int rc = adminAccessGroupDAO.deleteAdminAccessGroup(adminAccessGroupId, noOfChanges, auditorId);
    return rc;

  }

  /*
   * AdministratorAccessGroup stuff
   * 
   */

  public List<AdministratorAccessGroupUser> getAdministratorAccessGroupUsersForAdministrator(Integer administratorId) {

    List<AdministratorAccessGroupUser> administratorAccessGroupes = null;
    administratorAccessGroupes = administratorAccessGroupDAO.getAdministratorAccessGroupUsersForAdministrator(administratorId);
    return administratorAccessGroupes;

  }

  public int insertAdministratorAccessGroup(AdministratorAccessGroup administratorAccessGroup, Integer auditorId) {

        int rc = administratorAccessGroupDAO.insertAdministratorAccessGroup(administratorAccessGroup, auditorId);
    return rc;

  }

  public int deleteAdministratorAccessGroup(Integer administratorAccessGroupId, Integer noOfChanges, Integer auditorId) {

    int rc = administratorAccessGroupDAO.deleteAdministratorAccessGroup(administratorAccessGroupId, noOfChanges, auditorId);
    return rc;

  }

  public int insertAdminAccessGroupItem(AdminAccessGroupItem adminAccessGroupItem, Integer auditorId) {

        int rc = adminAccessGroupItemDAO.insertAdminAccessGroupItem(adminAccessGroupItem, auditorId);
    return rc;

  }

  public int deleteAdminAccessGroupItem(Integer adminAccessGroupItemId, Integer noOfChanges, Integer auditorId) {

    int rc = adminAccessGroupItemDAO.deleteAdminAccessGroupItem(adminAccessGroupItemId, noOfChanges, auditorId);
    return rc;

  }

  /*
   * JobFamily stuff
   * 
   */

  public List<JobFamily> getJobFamiliesForClient(Integer clientId, boolean showOnlyActive) {

    List<JobFamily> jobFamilies = null;
    jobFamilies = jobFamilyDAO.getJobFamiliesForClient(clientId, showOnlyActive);
    return jobFamilies;

  }

  public JobFamily getJobFamily(Integer jobFamilyId) {

    JobFamily jobFamily = null;
    jobFamily = jobFamilyDAO.getJobFamily(jobFamilyId);
    return jobFamily;

  }
  public JobFamilyEntity getJobFamilyEntity(Integer jobFamilyId, boolean showOnlyActive) {

    JobFamilyEntity jobFamilyEntity = null;
    jobFamilyEntity = jobFamilyDAO.getJobFamilyEntity(jobFamilyId);
    jobFamilyEntity.setJobSubFamilies(jobSubFamilyDAO.getJobSubFamiliesForJobFamily(jobFamilyId, showOnlyActive));
    return jobFamilyEntity;

  }

  public int insertJobFamily(JobFamily jobFamily, Integer auditorId) {

    JobFamily duplicateJobFamily = jobFamilyDAO.getJobFamilyForName(jobFamily.getClientId(), jobFamily.getName());
    if (duplicateJobFamily != null) {
      throw new DuplicateDataException("name");
    }
    if (jobFamily.getCode() != null && !"".equals(jobFamily.getCode())) {
      // not mandatory, so olny check if entered
      duplicateJobFamily = jobFamilyDAO.getJobFamilyForCode(jobFamily.getClientId(), jobFamily.getCode());
      if (duplicateJobFamily != null) {
        throw new DuplicateDataException("code");
      }
    }
    
        int rc = jobFamilyDAO.insertJobFamily(jobFamily, auditorId);
    return rc;

  }

  public int updateJobFamily(JobFamily jobFamily, Integer auditorId) {

    JobFamily duplicateJobFamily = jobFamilyDAO.getJobFamilyForName(jobFamily.getClientId(), jobFamily.getName());
    if (duplicateJobFamily != null &&
      !duplicateJobFamily.getJobFamilyId().equals(jobFamily.getJobFamilyId())) {
      throw new DuplicateDataException("name");
    }
    if (jobFamily.getCode() != null && !"".equals(jobFamily.getCode())) {
      // not mandatory, so olny check if entered
      duplicateJobFamily = jobFamilyDAO.getJobFamilyForCode(jobFamily.getClientId(), jobFamily.getCode());
      if (duplicateJobFamily != null &&
        !duplicateJobFamily.getJobFamilyId().equals(jobFamily.getJobFamilyId())) {
        throw new DuplicateDataException("code");
      }
    }
      
        int rc = jobFamilyDAO.updateJobFamily(jobFamily, auditorId);
    return rc;

  }

  public int deleteJobFamily(Integer jobFamilyId, Integer noOfChanges, Integer auditorId) {

    int rc = jobFamilyDAO.deleteJobFamily(jobFamilyId, noOfChanges, auditorId);
    return rc;

  }

  public int updateJobFamilyDisplayOrder(String order, boolean zeroiseDisplayOrder, Integer auditorId) {
    int rc = 0;

    StringTokenizer stringTokenizer = new StringTokenizer(order, "|");
    String commaDelimitedKey = null;
    int displayOrder = 0;
    while (stringTokenizer.hasMoreTokens())
    {
        commaDelimitedKey = stringTokenizer.nextToken();
        if (!zeroiseDisplayOrder)
        {
          // Increment the display order.
          displayOrder++;
        }
      StringTokenizer st = new StringTokenizer(commaDelimitedKey, ",");
      Integer jobFamilyId = Integer.parseInt(st.nextToken());
      Integer noOfChanges = Integer.parseInt(st.nextToken());
        rc += jobFamilyDAO.updateJobFamilyDisplayOrder(jobFamilyId, displayOrder, noOfChanges, auditorId);
    }
    
    return rc;
  }
  
  /*
   * JobSubFamily stuff
   * 
   */

  public JobSubFamily getJobSubFamily(Integer jobSubFamilyId) {

    JobSubFamily jobSubFamily = null;
    jobSubFamily = jobSubFamilyDAO.getJobSubFamily(jobSubFamilyId);
    return jobSubFamily;

  }

  public JobSubFamilyEntity getJobSubFamilyEntity(Integer jobSubFamilyId, boolean showOnlyActive) {

    JobSubFamilyEntity jobSubFamilyEntity = null;
    jobSubFamilyEntity = jobSubFamilyDAO.getJobSubFamilyEntity(jobSubFamilyId);
    jobSubFamilyEntity.setJobProfiles(getJobProfileDAO().getJobProfilesForJobSubFamily(jobSubFamilyId, showOnlyActive));
    return jobSubFamilyEntity;

  }

  public List<JobSubFamily> getJobSubFamiliesForJobFamily(Integer jobFamilyId, boolean showOnlyActive) {

    List<JobSubFamily> jobSubFamilies = null;
    jobSubFamilies = jobSubFamilyDAO.getJobSubFamiliesForJobFamily(jobFamilyId, showOnlyActive);
    return jobSubFamilies;

  }

  public int insertJobSubFamily(JobSubFamily jobSubFamily, Integer auditorId) {

    JobSubFamily duplicateJobSubFamily = jobSubFamilyDAO.getJobSubFamilyForName(jobSubFamily.getJobFamilyId(), jobSubFamily.getName());
    if (duplicateJobSubFamily != null) {
      throw new DuplicateDataException("name");
    }
    if (jobSubFamily.getCode() != null && !"".equals(jobSubFamily.getCode())) {
      // not mandatory, so olny check if entered
      duplicateJobSubFamily = jobSubFamilyDAO.getJobSubFamilyForCode(jobSubFamily.getJobFamilyId(), jobSubFamily.getCode());
      if (duplicateJobSubFamily != null) {
        throw new DuplicateDataException("code");
      }
    }
    
        int rc = jobSubFamilyDAO.insertJobSubFamily(jobSubFamily, auditorId);
    return rc;

  }

  public int updateJobSubFamily(JobSubFamily jobSubFamily, Integer auditorId) {

    JobSubFamily duplicateJobSubFamily = jobSubFamilyDAO.getJobSubFamilyForName(jobSubFamily.getJobFamilyId(), jobSubFamily.getName());
    if (duplicateJobSubFamily != null &&
      !duplicateJobSubFamily.getJobSubFamilyId().equals(jobSubFamily.getJobSubFamilyId())) {
      throw new DuplicateDataException("name");
    }
    if (jobSubFamily.getCode() != null && !"".equals(jobSubFamily.getCode())) {
      // not mandatory, so olny check if entered
      duplicateJobSubFamily = jobSubFamilyDAO.getJobSubFamilyForCode(jobSubFamily.getJobFamilyId(), jobSubFamily.getCode());
      if (duplicateJobSubFamily != null &&
        !duplicateJobSubFamily.getJobSubFamilyId().equals(jobSubFamily.getJobSubFamilyId())) {
        throw new DuplicateDataException("code");
      }
    }
    
        int rc = jobSubFamilyDAO.updateJobSubFamily(jobSubFamily, auditorId);
    return rc;

  }

  public int deleteJobSubFamily(Integer jobSubFamilyId, Integer noOfChanges, Integer auditorId) {

    int rc = jobSubFamilyDAO.deleteJobSubFamily(jobSubFamilyId, noOfChanges, auditorId);
    return rc;

  }

  public int updateJobSubFamilyDisplayOrder(String order, boolean zeroiseDisplayOrder, Integer auditorId) {
    int rc = 0;

    StringTokenizer stringTokenizer = new StringTokenizer(order, "|");
    String commaDelimitedKey = null;
    int displayOrder = 0;
    while (stringTokenizer.hasMoreTokens())
    {
        commaDelimitedKey = stringTokenizer.nextToken();
        if (!zeroiseDisplayOrder)
        {
          // Increment the display order.
          displayOrder++;
        }
      StringTokenizer st = new StringTokenizer(commaDelimitedKey, ",");
      Integer jobSubFamilyId = Integer.parseInt(st.nextToken());
      Integer noOfChanges = Integer.parseInt(st.nextToken());
        rc += jobSubFamilyDAO.updateJobSubFamilyDisplayOrder(jobSubFamilyId, displayOrder, noOfChanges, auditorId);
    }
    
    return rc;
  }
  
  /*
   * JobProfile stuff
   * 
   */

  public JobProfile getJobProfile(Integer jobProfileId) {

    JobProfile jobProfile = null;
    jobProfile = getJobProfileDAO().getJobProfile(jobProfileId);
    return jobProfile;

  }

  public JobProfileUserEntity getJobProfileUserEntity(Integer jobProfileId) {

    JobProfileUserEntity jobProfileUserEntity = null;
    jobProfileUserEntity = getJobProfileDAO().getJobProfileUserEntity(jobProfileId);
    jobProfileUserEntity.setJobProfileGradeUsers(jobProfileGradeDAO.getJobProfileGradeUsersForJobProfile(jobProfileId));
    jobProfileUserEntity.setGrades(getGradeDAO().getGradesNotForJobProfile(jobProfileUserEntity.getClientId(), jobProfileId));
    jobProfileUserEntity.setClientAgencyJobProfileUsers(getClientAgencyJobProfileDAO().getClientAgencyJobProfileUsersForJobProfile(jobProfileId));
    jobProfileUserEntity.setClientAgencyUsers(getClientAgencyDAO().getClientAgencyUsersNotForJobProfile(jobProfileUserEntity.getClientId(), jobProfileId));
    return jobProfileUserEntity;

  }

  public List<JobProfile> getJobProfilesForJobSubFamily(Integer jobSubFamilyId, boolean showOnlyActive) {

    List<JobProfile> jobProfiles = null;
    jobProfiles = getJobProfileDAO().getJobProfilesForJobSubFamily(jobSubFamilyId, showOnlyActive);
    return jobProfiles;

  }

  public int insertJobProfile(JobProfile jobProfile, Integer auditorId) {

    JobProfile duplicateJobProfile = getJobProfileDAO().getJobProfileForName(jobProfile.getJobSubFamilyId(), jobProfile.getName());
    if (duplicateJobProfile != null) {
      throw new DuplicateDataException("name");
    }
    if (jobProfile.getCode() != null && !"".equals(jobProfile.getCode())) {
      // not mandatory, so olny check if entered
      duplicateJobProfile = getJobProfileDAO().getJobProfileForCode(jobProfile.getJobSubFamilyId(), jobProfile.getCode());
      if (duplicateJobProfile != null) {
        throw new DuplicateDataException("code");
      }
    }
    
        int rc = getJobProfileDAO().insertJobProfile(jobProfile, auditorId);
    return rc;

  }

  public int updateJobProfile(JobProfile jobProfile, Integer auditorId) {

    JobProfile duplicateJobProfile = getJobProfileDAO().getJobProfileForName(jobProfile.getJobSubFamilyId(), jobProfile.getName());
    if (duplicateJobProfile != null &&
      !duplicateJobProfile.getJobProfileId().equals(jobProfile.getJobProfileId())) {
      throw new DuplicateDataException("name");
    }
    if (jobProfile.getCode() != null && !"".equals(jobProfile.getCode())) {
      // not mandatory, so olny check if entered
      duplicateJobProfile = getJobProfileDAO().getJobProfileForCode(jobProfile.getJobSubFamilyId(), jobProfile.getCode());
      if (duplicateJobProfile != null &&
        !duplicateJobProfile.getJobProfileId().equals(jobProfile.getJobProfileId())) {
        throw new DuplicateDataException("code");
      }
    }
    
        int rc = getJobProfileDAO().updateJobProfile(jobProfile, auditorId);
    return rc;

  }

  public int deleteJobProfile(Integer jobProfileId, Integer noOfChanges, Integer auditorId) {

    int rc = getJobProfileDAO().deleteJobProfile(jobProfileId, noOfChanges, auditorId);
    return rc;

  }

  public int updateJobProfileDisplayOrder(String order, boolean zeroiseDisplayOrder, Integer auditorId) {
    int rc = 0;

    StringTokenizer stringTokenizer = new StringTokenizer(order, "|");
    String commaDelimitedKey = null;
    int displayOrder = 0;
    while (stringTokenizer.hasMoreTokens())
    {
        commaDelimitedKey = stringTokenizer.nextToken();
        if (!zeroiseDisplayOrder)
        {
          // Increment the display order.
          displayOrder++;
        }
      StringTokenizer st = new StringTokenizer(commaDelimitedKey, ",");
      Integer jobProfileId = Integer.parseInt(st.nextToken());
      Integer noOfChanges = Integer.parseInt(st.nextToken());
        rc += getJobProfileDAO().updateJobProfileDisplayOrder(jobProfileId, displayOrder, noOfChanges, auditorId);
    }
    
    return rc;
  }
  
  public int insertJobProfileGrade(JobProfileGrade jobProfileGrade, Integer auditorId) {

        int rc = jobProfileGradeDAO.insertJobProfileGrade(jobProfileGrade, auditorId);
    return rc;

  }

  public int deleteJobProfileGrade(Integer jobProfileGradeId, Integer noOfChanges, Integer auditorId) {

    int rc = jobProfileGradeDAO.deleteJobProfileGrade(jobProfileGradeId, noOfChanges, auditorId);
    return rc;

  }

  /*
   * Agency stuff ...
   * 
   */

  public Agency getAgency(Integer agencyId) {
    
    Agency agency = null;
    agency = getAgencyDAO().getAgency(agencyId);
    return agency;
    
  }
  public AgencyUser getAgencyUser(Integer agencyId) {
    
    AgencyUser agencyUser = null;
    agencyUser = getAgencyDAO().getAgencyUser(agencyId);
    return agencyUser;
    
  }
  public Consultant getProspectConsultant(Integer agencyId)
  {
    return consultantDAO.getProspectConsultant(agencyId);
  }
  public AgencyUserEntity getAgencyUserEntity(Integer agencyId, boolean showOnlyActive) {

    AgencyUserEntity agencyUserEntity = null;
    agencyUserEntity = getAgencyDAO().getAgencyUserEntity(agencyId);
    agencyUserEntity.setConsultants(consultantDAO.getConsultantsForAgency(agencyId, showOnlyActive));
    agencyUserEntity.setAgyAccessGroups(agyAccessGroupDAO.getAgyAccessGroupsForAgency(agencyId, showOnlyActive));
    agencyUserEntity.setClientAgencyUsers(getClientAgencyDAO().getClientAgencyUsersForAgency(agencyId, showOnlyActive));
    return agencyUserEntity;

  }
  public int insertAgency(Agency agency, Integer auditorId) {

    Agency duplicateAgency = getAgencyDAO().getAgencyForName(agency.getName());
    if (duplicateAgency != null) {
      throw new DuplicateDataException("name");
    }
    duplicateAgency = getAgencyDAO().getAgencyForCode(agency.getCode());
    if (duplicateAgency != null) {
      throw new DuplicateDataException("code");
    }
    
    int rc = getAgencyDAO().insertAgency(agency, auditorId);
    return rc;
  
  }
  public int updateAgency(Agency agency, Integer auditorId) {

    Agency duplicateAgency = getAgencyDAO().getAgencyForName(agency.getName());
    if (duplicateAgency != null &&
      !duplicateAgency.getAgencyId().equals(agency.getAgencyId())) {
      throw new DuplicateDataException("name");
    }
    duplicateAgency = getAgencyDAO().getAgencyForCode(agency.getCode());
    if (duplicateAgency != null &&
      !duplicateAgency.getAgencyId().equals(agency.getAgencyId())) {
      throw new DuplicateDataException("code");
    }

    int rc = getAgencyDAO().updateAgency(agency, auditorId);
    return rc;
  
  }
  public int deleteAgency(Integer agencyId, Integer noOfChanges, Integer auditorId) {

    int rc = getAgencyDAO().deleteAgency(agencyId, noOfChanges, auditorId);
    return rc;
  
  }
  
  public int updateAgencyDisplayOrder(String order, boolean zeroiseDisplayOrder, Integer auditorId) {
    int rc = 0;

    StringTokenizer stringTokenizer = new StringTokenizer(order, "|");
    String commaDelimitedKey = null;
    int displayOrder = 0;
    while (stringTokenizer.hasMoreTokens())
    {
        commaDelimitedKey = stringTokenizer.nextToken();
        if (!zeroiseDisplayOrder)
        {
          // Increment the display order.
          displayOrder++;
        }
      StringTokenizer st = new StringTokenizer(commaDelimitedKey, ",");
      Integer agencyId = Integer.parseInt(st.nextToken());
      Integer noOfChanges = Integer.parseInt(st.nextToken());
        rc += getAgencyDAO().updateAgencyDisplayOrder(agencyId, displayOrder, noOfChanges, auditorId);
    }
    
    return rc;
  }
  
  public int insertClientReEnterPwd(ClientReEnterPwd clientReEnterPwd, Integer auditorId) {

        int rc = clientReEnterPwdDAO.insertClientReEnterPwd(clientReEnterPwd, auditorId);
    return rc;

  }

  public int deleteClientReEnterPwd(Integer clientReEnterPwdId, Integer noOfChanges, Integer auditorId) {

    int rc = clientReEnterPwdDAO.deleteClientReEnterPwd(clientReEnterPwdId, noOfChanges, auditorId);
    return rc;

  }

  public int insertClientAgencyJobProfile(ClientAgencyJobProfile clientAgencyJobProfile, Integer auditorId) {

        int rc = getClientAgencyJobProfileDAO().insertClientAgencyJobProfile(clientAgencyJobProfile, auditorId);
    return rc;

  }

  public int deleteClientAgencyJobProfile(Integer clientAgencyJobProfileId, Integer noOfChanges, Integer auditorId) {

    int rc = getClientAgencyJobProfileDAO().deleteClientAgencyJobProfile(clientAgencyJobProfileId, noOfChanges, auditorId);
    return rc;

  }

  public ClientAgencyJobProfile getClientAgencyJobProfile(Integer clientAgencyJobProfileId) {
    
    ClientAgencyJobProfile clientAgencyJobProfile = null;
    clientAgencyJobProfile = getClientAgencyJobProfileDAO().getClientAgencyJobProfile(clientAgencyJobProfileId);
    return clientAgencyJobProfile;
    
  }

  public ClientAgencyJobProfileUserEntity getClientAgencyJobProfileUserEntity(Integer clientAgencyJobProfileId) {
    
    ClientAgencyJobProfileUserEntity clientAgencyJobProfileUserEntity = null;
    clientAgencyJobProfileUserEntity = getClientAgencyJobProfileDAO().getClientAgencyJobProfileUserEntity(clientAgencyJobProfileId);
    clientAgencyJobProfileUserEntity.setClientAgencyJobProfileGradeUsers(getClientAgencyJobProfileGradeDAO().getClientAgencyJobProfileGradeUsersForClientAgencyJobProfile(clientAgencyJobProfileId));
    clientAgencyJobProfileUserEntity.setGrades(getGradeDAO().getGradesNotForClientAgencyJobProfile(clientAgencyJobProfileUserEntity.getJobProfileId(), clientAgencyJobProfileId));
    return clientAgencyJobProfileUserEntity;
    
  }

  public int updateClientAgencyJobProfile(ClientAgencyJobProfile clientAgencyJobProfile, Integer auditorId) {

    int rc = getClientAgencyJobProfileDAO().updateClientAgencyJobProfile(clientAgencyJobProfile, auditorId);
    return rc;
  
  }

  public int insertClientAgencyJobProfileGrade(ClientAgencyJobProfileGrade clientAgencyJobProfileGrade, Integer auditorId) {

        int rc = getClientAgencyJobProfileGradeDAO().insertClientAgencyJobProfileGrade(clientAgencyJobProfileGrade, auditorId);
    return rc;

  }

  public int deleteClientAgencyJobProfileGrade(Integer clientAgencyJobProfileGradeId, Integer noOfChanges, Integer auditorId) {

    int rc = getClientAgencyJobProfileGradeDAO().deleteClientAgencyJobProfileGrade(clientAgencyJobProfileGradeId, noOfChanges, auditorId);
    return rc;

  }

  public ClientAgencyJobProfileGrade getClientAgencyJobProfileGrade(Integer clientAgencyJobProfileGradeId) {
    
    ClientAgencyJobProfileGrade clientAgencyJobProfileGrade = null;
    clientAgencyJobProfileGrade = getClientAgencyJobProfileGradeDAO().getClientAgencyJobProfileGrade(clientAgencyJobProfileGradeId);
    return clientAgencyJobProfileGrade;
    
  }

  public int updateClientAgencyJobProfileGrade(ClientAgencyJobProfileGrade clientAgencyJobProfileGrade, Integer auditorId) {

    int rc = getClientAgencyJobProfileGradeDAO().updateClientAgencyJobProfileGrade(clientAgencyJobProfileGrade, auditorId);
    return rc;
  
  }

  /*
   * Consultant stuff ...
   * 
   */
  
  public List<Consultant> getConsultantsForAgency(Integer agencyId, boolean showOnlyActive) {

    List<Consultant> consultants = null;
    consultants = consultantDAO.getConsultantsForAgency(agencyId, showOnlyActive);
    return consultants;
  }

  public Consultant getConsultant(Integer consultantId) {
    
    Consultant consultant = null;
      consultant = consultantDAO.getConsultant(consultantId);
    return consultant;
    
  }

  public ConsultantEntity getConsultantEntity(Integer consultantId) {

    ConsultantEntity consultantEntity = null;
    consultantEntity = consultantDAO.getConsultantEntity(consultantId);
    consultantEntity.setConsultantAccessUsers(consultantAccessDAO.getConsultantAccessUsersForConsultant(consultantId));
    consultantEntity.setAgyAccesses(agyAccessDAO.getAgyAccessesNotForConsultant(consultantId));
    consultantEntity.setConsultantAccessGroupUsers(consultantAccessGroupDAO.getConsultantAccessGroupUsersForConsultant(consultantId));
    consultantEntity.setAgyAccessGroups(agyAccessGroupDAO.getAgyAccessGroupsNotForConsultant(consultantEntity.getAgencyId(), consultantId));
    return consultantEntity;

  }

  
  public int insertConsultant(Consultant consultant, Integer auditorId) {

    Consultant duplicateConsultant = consultantDAO.getConsultantForLogin(consultant.getAgencyId(), consultant.getUser().getLogin());
    if (duplicateConsultant != null) {
      throw new DuplicateDataException("login");
    }
        int rc = consultantDAO.insertConsultant(consultant, auditorId);
    return rc;

  }

  public int updateConsultant(Consultant consultant, Integer auditorId) {

    Consultant duplicateConsultant = consultantDAO.getConsultantForLogin(consultant.getAgencyId(), consultant.getUser().getLogin());
    if (duplicateConsultant != null &&
      !duplicateConsultant.getConsultantId().equals(consultant.getConsultantId())) {
      throw new DuplicateDataException("login");
    }
        int rc = consultantDAO.updateConsultant(consultant, auditorId);
    return rc;

  }

  public int deleteConsultant(Integer consultantId, Integer noOfChanges, Integer auditorId) {

        int rc = consultantDAO.deleteConsultant(consultantId, noOfChanges, auditorId);
    return rc;

  }

    public int updateConsultantPwd(Integer consultantId, String newPwd, String pwdHint, Integer noOfChanges, Integer auditorId) {

    int rc = consultantDAO.updateConsultantPwd(consultantId, newPwd, pwdHint, noOfChanges, auditorId);
    return rc;
  
  }
  
    public int updateConsultantSecretWord(Integer consultantId, String newSecretWord, Integer noOfChanges, Integer auditorId) {

    int rc = consultantDAO.updateConsultantSecretWord(consultantId, newSecretWord, noOfChanges, auditorId);
    return rc;
  
  }
  
  /*
   * AgyAccess stuff
   * 
   */

  public List<AgyAccess> getAgyAccesses(boolean showOnlyActive) {

    List<AgyAccess> agyAccesses = null;
    agyAccesses = agyAccessDAO.getAgyAccesses(showOnlyActive);
    return agyAccesses;

  }
  public List<AgyAccess> getActiveAgyAccessesForConsultant(Integer consultantId) {

    List<AgyAccess> agyAccesses = null;
    agyAccesses = agyAccessDAO.getActiveAgyAccessesForConsultant(consultantId);
    return agyAccesses;

  }
  public AgyAccess getAgyAccess(Integer agyAccessId) {

    AgyAccess agyAccess = null;
    agyAccess = agyAccessDAO.getAgyAccess(agyAccessId);
    return agyAccess;

  }
  public int insertAgyAccess(AgyAccess agyAccess, Integer auditorId) {

    AgyAccess duplicateAgyAccess = agyAccessDAO.getAgyAccessForName(agyAccess.getName());
    if (duplicateAgyAccess != null) {
      throw new DuplicateDataException("name");
    }
    int rc = agyAccessDAO.insertAgyAccess(agyAccess, auditorId);
    return rc;

  }

  public int updateAgyAccess(AgyAccess agyAccess, Integer auditorId) {

    AgyAccess duplicateAgyAccess = agyAccessDAO.getAgyAccessForName(agyAccess.getName());
    if (duplicateAgyAccess  != null && 
      !duplicateAgyAccess.getAgyAccessId().equals(agyAccess.getAgyAccessId())) {
      throw new DuplicateDataException("name");
    }
        int rc = agyAccessDAO.updateAgyAccess(agyAccess, auditorId);
    return rc;

  }

  public int deleteAgyAccess(Integer agyAccessId, Integer noOfChanges, Integer auditorId) {

    int rc = agyAccessDAO.deleteAgyAccess(agyAccessId, noOfChanges, auditorId);
    return rc;

  }

  /*
   * AgyAccessGroup stuff
   * 
   */
  
  public List<AgyAccessGroup> getAgyAccessGroupsForAgency(Integer agencyId, boolean showOnlyActive) {

    List<AgyAccessGroup> agyAccessGroups = null;
    agyAccessGroups = agyAccessGroupDAO.getAgyAccessGroupsForAgency(agencyId, showOnlyActive);
    return agyAccessGroups;

  }
  public AgyAccessGroup getAgyAccessGroup(Integer agyAccessGroupId) {

    AgyAccessGroup agyAccessGroup = null;
    agyAccessGroup = agyAccessGroupDAO.getAgyAccessGroup(agyAccessGroupId);
    return agyAccessGroup;

  }
  public AgyAccessGroupEntity getAgyAccessGroupEntity(Integer agyAccessGroupId) {

    AgyAccessGroupEntity agyAccessGroupEntity = null;
    agyAccessGroupEntity = agyAccessGroupDAO.getAgyAccessGroupEntity(agyAccessGroupId);
    agyAccessGroupEntity.setAgyAccessGroupItemUsers(agyAccessGroupItemDAO.getAgyAccessGroupItemUsersForAgyAccessGroup(agyAccessGroupId));
    agyAccessGroupEntity.setAgyAccesses(agyAccessDAO.getAgyAccessesNotForAgyAccessGroup(agyAccessGroupId));
    return agyAccessGroupEntity;

  }
  public int insertAgyAccessGroup(AgyAccessGroup agyAccessGroup, Integer auditorId) {

    AgyAccessGroup duplicateAgyAccessGroup = agyAccessGroupDAO.getAgyAccessGroupForName(agyAccessGroup.getAgencyId(), agyAccessGroup.getName());
    if (duplicateAgyAccessGroup != null) {
      throw new DuplicateDataException("name");
    }
    int rc = agyAccessGroupDAO.insertAgyAccessGroup(agyAccessGroup, auditorId);
    return rc;

  }

  public int updateAgyAccessGroup(AgyAccessGroup agyAccessGroup, Integer auditorId) {

    AgyAccessGroup duplicateAgyAccessGroup = agyAccessGroupDAO.getAgyAccessGroupForName(agyAccessGroup.getAgencyId(), agyAccessGroup.getName());
    if (duplicateAgyAccessGroup  != null && 
      !duplicateAgyAccessGroup.getAgyAccessGroupId().equals(agyAccessGroup.getAgyAccessGroupId())) {
      throw new DuplicateDataException("name");
    }
        int rc = agyAccessGroupDAO.updateAgyAccessGroup(agyAccessGroup, auditorId);
    return rc;

  }

  public int deleteAgyAccessGroup(Integer agyAccessGroupId, Integer noOfChanges, Integer auditorId) {

    int rc = agyAccessGroupDAO.deleteAgyAccessGroup(agyAccessGroupId, noOfChanges, auditorId);
    return rc;

  }

  public int insertAgyAccessGroupItem(AgyAccessGroupItem agyAccessGroupItem, Integer auditorId) {

        int rc = agyAccessGroupItemDAO.insertAgyAccessGroupItem(agyAccessGroupItem, auditorId);
    return rc;

  }

  public int deleteAgyAccessGroupItem(Integer agyAccessGroupItemId, Integer noOfChanges, Integer auditorId) {

    int rc = agyAccessGroupItemDAO.deleteAgyAccessGroupItem(agyAccessGroupItemId, noOfChanges, auditorId);
    return rc;

  }

  
  /*
   * ConsultantAccess stuff
   * 
   */

  public List<ConsultantAccessUser> getConsultantAccessUsersForConsultant(Integer consultantId) {

    List<ConsultantAccessUser> consultantAccesses = null;
    consultantAccesses = consultantAccessDAO.getConsultantAccessUsersForConsultant(consultantId);
    return consultantAccesses;

  }

  public int insertConsultantAccess(ConsultantAccess consultantAccess, Integer auditorId) {

        int rc = consultantAccessDAO.insertConsultantAccess(consultantAccess, auditorId);
    return rc;

  }

  public int deleteConsultantAccess(Integer consultantAccessId, Integer noOfChanges, Integer auditorId) {

    int rc = consultantAccessDAO.deleteConsultantAccess(consultantAccessId, noOfChanges, auditorId);
    return rc;

  }

  /*
   * ConsultantAccessGroup stuff
   * 
   */

  public List<ConsultantAccessGroupUser> getConsultantAccessGroupUsersForConsultant(Integer consultantId) {

    List<ConsultantAccessGroupUser> consultantAccessGroupes = null;
    consultantAccessGroupes = consultantAccessGroupDAO.getConsultantAccessGroupUsersForConsultant(consultantId);
    return consultantAccessGroupes;

  }

  public int insertConsultantAccessGroup(ConsultantAccessGroup consultantAccessGroup, Integer auditorId) {

        int rc = consultantAccessGroupDAO.insertConsultantAccessGroup(consultantAccessGroup, auditorId);
    return rc;

  }

  public int deleteConsultantAccessGroup(Integer consultantAccessGroupId, Integer noOfChanges, Integer auditorId) {

    int rc = consultantAccessGroupDAO.deleteConsultantAccessGroup(consultantAccessGroupId, noOfChanges, auditorId);
    return rc;

  }

  /*
   * BudgetTransaction stuff
   * 
   */

  public List<BudgetTransactionUser> getBudgetTransactionUsersForClient(Integer clientId, boolean showOnlyActive) {

    List<BudgetTransactionUser> budgetTransactions = null;
    budgetTransactions = budgetTransactionDAO.getBudgetTransactionUsersForClient(clientId, showOnlyActive);
    return budgetTransactions;

  }

  public BudgetTransaction getBudgetTransaction(Integer budgetTransactionId) {

    BudgetTransaction budgetTransaction = null;
    budgetTransaction = budgetTransactionDAO.getBudgetTransaction(budgetTransactionId);
    return budgetTransaction;

  }

  public int insertBudgetTransaction(BudgetTransaction budgetTransaction, Integer auditorId) {

    LocationJobProfile locationJobProfile = getLocationJobProfileDAO().getLocationJobProfile(budgetTransaction.getLocationJobProfileId());
    //
    budgetTransaction.setBalance(locationJobProfile.getBudget().add(budgetTransaction.getValue()));
    budgetTransaction.setVatBalance(locationJobProfile.getVatBudget().add(budgetTransaction.getVatValue()));
    budgetTransaction.setExpenseBalance(locationJobProfile.getExpenseBudget().add(budgetTransaction.getExpenseValue()));
    budgetTransaction.setNonPayBalance(locationJobProfile.getNonPayBudget().add(budgetTransaction.getNonPayValue()));
        //
    int rc = budgetTransactionDAO.insertBudgetTransaction(budgetTransaction, auditorId);
    // add the value to the locationJobProfile
    rc = getLocationJobProfileDAO().updateLocationJobProfileBudget(budgetTransaction.getLocationJobProfileId(), 
                                  budgetTransaction.getValue(), 
                                  budgetTransaction.getVatValue(), 
                                  budgetTransaction.getExpenseValue(), 
                                  budgetTransaction.getNonPayValue(), 
                                  locationJobProfile.getNoOfChanges(), 
                                                          auditorId);
    //
    return rc;

  }

//  public int updateBudgetTransaction(BudgetTransaction budgetTransaction, Integer auditorId) {
//
//        int rc = budgetTransactionDAO.updateBudgetTransaction(budgetTransaction, auditorId);
//    return rc;
//
//  }

  public int deleteBudgetTransaction(Integer budgetTransactionId, Integer noOfChanges, Integer auditorId) {

    int rc = budgetTransactionDAO.deleteBudgetTransaction(budgetTransactionId, noOfChanges, auditorId);
    return rc;

  }

  public List<BookingDateUserApplicant> getBookingDateUserApplicantsForAdmin(Date fromDate, Date toDate) 
  {
    return  getBookingDateDAO().getBookingDateUserApplicantsForAdmin(fromDate, toDate);
  }

  public IntValue getActiveBookingGradeApplicantsCountForClientAgencyJobProfileGrade(Integer clientAgencyJobProfileGradeId)
  {
    IntValue rc = bookingGradeApplicantDAO.getActiveBookingGradeApplicantsCountForClientAgencyJobProfileGrade(clientAgencyJobProfileGradeId);
    return rc;
  }

}
