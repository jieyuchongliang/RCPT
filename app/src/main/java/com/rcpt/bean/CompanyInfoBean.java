package com.rcpt.bean;

import com.rcpt.http.ParamNames;

import java.util.List;

/**
 * Created by lvzp on 2017/4/10.
 * 类描述：
 */

public class CompanyInfoBean {

    private String organizeCode;//	组织代码
    private List<Object> comPictUploadList;//	企业展示图片列表
    private String businessLicense;//	营业执照扫描件
    private String taxNumber;//		税号
    private String provinceId;//	省代码
    private String cityId;//	市代码
    private String addressAreaId;//区代码
    private String address;
    private String industryId;//行业Id
    private String enterpriseNatureId;//公司性质Id（国企、外企等）
    private String isQuotedCompanyId;//是否上市的id

    public String getIsQuotedCompanyId() {
        return isQuotedCompanyId;
    }

    public void setIsQuotedCompanyId(String isQuotedCompanyId) {
        this.isQuotedCompanyId = isQuotedCompanyId;
    }

    public String getEnterpriseNatureId() {
        return enterpriseNatureId;
    }

    public void setEnterpriseNatureId(String enterpriseNatureId) {
        this.enterpriseNatureId = enterpriseNatureId;
    }

    public void setIndustryId(String industryId) {
        this.industryId = industryId;
    }

    public String getIndustryId() {
        return industryId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrganizeCode() {
        return organizeCode;
    }

    public void setOrganizeCode(String organizeCode) {
        this.organizeCode = organizeCode;
    }

    public List<Object> getComPictUploadList() {
        return comPictUploadList;
    }

    public void setComPictUploadList(List<Object> comPictUploadList) {
        this.comPictUploadList = comPictUploadList;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getAddressAreaId() {
        return addressAreaId;
    }

    public void setAddressAreaId(String addressAreaId) {
        this.addressAreaId = addressAreaId;
    }

    ////////////////////以下为上传的参数///////////////////////////
    private String companyId;//		用户id（企业id）
    private String comName;//		公司名称
    private String legalRepresentative;//	企业法人代表
    @ParamNames("logo")
    private String comLogo;//	标志
    private String industry;//		行业类别代码
    private String enterpriseNature;//公司性质（国企、外企等）
    private String scale;//		企业规模
    private String registeredCapital;//	注册资金
    private String registTime;//	注册时间
    private String serviceDistribution;//	业务分部
    @ParamNames("Province")
    private String addressProvince;//	省
    @ParamNames("city")
    private String addressCity;//	市
    private String addressDetail;//	详细地址
    private String addressArea;//	区
    private String zipCode;//		邮编
    private String website;//		网址
    private String contact;//		联系人
    private String tel;//	联系电话
    private String mail;//		邮箱
    private String companyIntroduction;//	介绍
    private String isQuotedCompany;//是否上市

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    public String getComLogo() {
        return comLogo;
    }

    public void setComLogo(String comLogo) {
        this.comLogo = comLogo;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getEnterpriseNature() {
        return enterpriseNature;
    }

    public void setEnterpriseNature(String enterpriseNature) {
        this.enterpriseNature = enterpriseNature;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(String registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public String getRegistTime() {
        return registTime;
    }

    public void setRegistTime(String registTime) {
        this.registTime = registTime;
    }

    public String getServiceDistribution() {
        return serviceDistribution;
    }

    public void setServiceDistribution(String serviceDistribution) {
        this.serviceDistribution = serviceDistribution;
    }

    public String getAddressProvince() {
        return addressProvince;
    }

    public void setAddressProvince(String addressProvince) {
        this.addressProvince = addressProvince;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getAddressArea() {
        return addressArea;
    }

    public void setAddressArea(String addressArea) {
        this.addressArea = addressArea;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCompanyIntroduction() {
        return companyIntroduction;
    }

    public void setCompanyIntroduction(String companyIntroduction) {
        this.companyIntroduction = companyIntroduction;
    }

    public String getIsQuotedCompany() {
        return isQuotedCompany;
    }

    public void setIsQuotedCompany(String isQuotedCompany) {
        this.isQuotedCompany = isQuotedCompany;
    }

}
