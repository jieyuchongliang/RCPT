package com.rcpt.bean;

import android.text.TextUtils;

import java.util.List;

/**
 * Created by lvzp on 2017/3/31.
 * 类描述：
 */

public class CVPreviewInfoBean {


    /**
     * projectpdList : [{"projectDescription":"主要负责的是税源信息采集和纳税评估(建安环节，不动产环节)。以不动产为例介绍：采集不动产交易信息，包括项目基本信息，交易基本信息，纳税人基本信息，选取纳税人和项目信息是通过谈出子窗体选取相应的信息，在页面除了基本的验证外，使用Ajax技术进行同名检测，巡查后的信息可补充，核实。","developmentTool":"Android Studio开发","startTime":"2015-03-01","jobDescription":"主要就是针对南京溧水县当地的房地产，建立的一个房地产税收管理系统，主要有：税源信息，纳税评估，行政执法，电子档案，综合查询，系统设置，信息变更，地图管理等模块构成，税源信息主要实现税收管理员对土地、建安、增量房、存量房等交易信息的采集、补充、核实。核实后的信息由税收管理员进行纳税评估，计算纳税人应该上缴的税务之后就进入行政执法,对违规的信息进行处理。","endTime":"2017-03-01","technology":"涉及的技术非常的多，不可一一列举","projectName":"人才平台"}]
     * traingpdList : []
     * experiencepdList : [{"enterpriseNature":"国有企业","industryType":"计算机软件","positions":"Android开发工程师","jobDescription":"主要负责商品销售和查询统计：商品销售主要涉及到销售和退货，首先商品销售需要选取客户和商品，是从另一个列表页面读取的，是通过在页面编写javascript脚本语言实现的。也可以输入商品编码，页面会自动填写商品的基本信息，使用Ajax发送请求完成。输入销售数量，则会自动计算金额(应收和未收款)，通过javascript实现，保存成功后，根据商品类型和操作时间来生成销售单号，同时对应商品的库存量减少。","startTime":"2013-03-01","endTime":"2016-03-01","department":"技术开发部","enterpriseName":"中国科学院"}]
     * cvPertsonalInfo : {"userPicture":"uploadFiles/userPicure/390f2541a17f4895a30cef5d06b1d75c.jpg","contactInfo":"18633744516","mail":"984006337@qq.com","sex":"男","name":"噜啦啦","workAge":4,"userId":"5d0248a37b814d10a1505733cb26e53e","overseasWork":"否","age":0,"maritalStatus":"未婚"}
     * certpdList : []
     * jobIntension : {"cvId":1,"city":"鄂尔多斯市","selfAppraisal":"自我评价一下","position1":"渠道/分销专员","position2":"销售工程师","position3":"客户代表","salary":"6001-8000元/月","cvName":"多年工作经验的大师","province":"内蒙古自治区","indeustry3":"IT服务（系统/数据/维护）","workType":"全职","indeustry4":"计算机软件","indeustry1":"电子技术/半导体/集成电路","indeustry2":"互联网/电子商务","workingState":"全职"}
     * languagepdList : []
     * itList : []
     * ediucationpdList : [{"specialty":"物联网应用技术","education":"本科","overseasStudy":"无","startTime":"2012-03-01","endTime":"2015-03-01","schoolName":"淄博职业学院"}]
     */
    private String Invitationcheck;
    private String collectcheck;
    private CvPertsonalInfoBean cvPertsonalInfo;
    private JobIntensionBean jobIntension;
    private List<ProjectpdListBean> projectpdList;
    private List<ExperiencepdListBean> experiencepdList;
    private List<EdiucationpdListBean> ediucationpdList;

    public String getInvitationcheck() {
        return Invitationcheck;
    }

    public void setInvitationcheck(String invitationcheck) {
        Invitationcheck = invitationcheck;
    }

    public String getCollectcheck() {
        return collectcheck;
    }

    public void setCollectcheck(String collectcheck) {
        this.collectcheck = collectcheck;
    }

    public CvPertsonalInfoBean getCvPertsonalInfo() {
        return cvPertsonalInfo;
    }

    public void setCvPertsonalInfo(CvPertsonalInfoBean cvPertsonalInfo) {
        this.cvPertsonalInfo = cvPertsonalInfo;
    }

    public JobIntensionBean getJobIntension() {
        return jobIntension;
    }

    public void setJobIntension(JobIntensionBean jobIntension) {
        this.jobIntension = jobIntension;
    }

    public List<ProjectpdListBean> getProjectpdList() {
        return projectpdList;
    }

    public void setProjectpdList(List<ProjectpdListBean> projectpdList) {
        this.projectpdList = projectpdList;
    }

    public List<ExperiencepdListBean> getExperiencepdList() {
        return experiencepdList;
    }

    public void setExperiencepdList(List<ExperiencepdListBean> experiencepdList) {
        this.experiencepdList = experiencepdList;
    }

    public List<EdiucationpdListBean> getEdiucationpdList() {
        return ediucationpdList;
    }

    public void setEdiucationpdList(List<EdiucationpdListBean> ediucationpdList) {
        this.ediucationpdList = ediucationpdList;
    }

    public static class CvPertsonalInfoBean {
        /**
         * userPicture : uploadFiles/userPicure/390f2541a17f4895a30cef5d06b1d75c.jpg
         * contactInfo : 18633744516
         * mail : 984006337@qq.com
         * sex : 男
         * name : 噜啦啦
         * workAge : 4
         * userId : 5d0248a37b814d10a1505733cb26e53e
         * overseasWork : 否
         * age : 0
         * maritalStatus : 未婚
         */

        private String userPicture;
        private String contactInfo;
        private String mail;
        private int sex;
        private String name;
        private int workAge;
        private String userId;
        private String overseasWork;
        private int age;
        private String maritalStatus;
        private String education;

        public String getUserPicture() {
            return userPicture;
        }

        public void setUserPicture(String userPicture) {
            this.userPicture = userPicture;
        }

        public String getContactInfo() {
            return contactInfo;
        }

        public void setContactInfo(String contactInfo) {
            this.contactInfo = contactInfo;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getOverseasWork() {
            return overseasWork;
        }

        public void setOverseasWork(String overseasWork) {
            this.overseasWork = overseasWork;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getWorkAge() {
            return workAge;
        }

        public void setWorkAge(int workAge) {
            this.workAge = workAge;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getMaritalStatus() {
            return maritalStatus;
        }

        public void setMaritalStatus(String maritalStatus) {
            this.maritalStatus = maritalStatus;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }
    }

    public static class JobIntensionBean {
        /**
         * cvId : 1
         * city : 鄂尔多斯市
         * selfAppraisal : 自我评价一下
         * position1 : 渠道/分销专员
         * position2 : 销售工程师
         * position3 : 客户代表
         * salary : 6001-8000元/月
         * cvName : 多年工作经验的大师
         * province : 内蒙古自治区
         * indeustry3 : IT服务（系统/数据/维护）
         * workType : 全职
         * indeustry4 : 计算机软件
         * indeustry1 : 电子技术/半导体/集成电路
         * indeustry2 : 互联网/电子商务
         * workingState : 全职
         */

        private int cvId;
        private String city;
        private String selfAppraisal = "暂未填写自我评价";
        private String position1;
        private String position2;
        private String position3;
        private String position4;
        private String position5;
        private String salary;
        private String cvName;
        private String province;
        private String workType;
        private String indeustry1;
        private String indeustry2;
        private String indeustry3;
        private String indeustry4;
        private String indeustry5;
        private String workingState;
        private String workPlace;

        public String getWorkPlace(){
            if(TextUtils.isEmpty(province) || TextUtils.isEmpty(city)){
                return "无";
            }else {
                return province + city;
            }
        }

        public String getPosition() {
            String positionnTxt;
            if (position1 == null) {
                positionnTxt = "";
            } else if (position2 == null) {
                positionnTxt = position1;
            } else if (position3 == null) {
                positionnTxt = position1 + "，" + position2;
            } else if (position4 == null) {
                positionnTxt = position1 + "，" + position2 + "，" + position3;
            } else if (position5 == null) {
                positionnTxt = position1 + "，" + position2 + "，" + position3
                        + "，" + position4;
            } else {
                positionnTxt = position1 + "，" + position2 + "，" + position3
                        + "，" + position4 + "，" + position5;
            }
            return (positionnTxt);
        }

        public String getIndeustry() {
            String indeustryTxt;
            if (indeustry1 == null) {
                indeustryTxt = "";
            } else if (indeustry2 == null) {
                indeustryTxt = indeustry1;
            } else if (indeustry3 == null) {
                indeustryTxt = indeustry1 + "，" + indeustry2;
            } else if (indeustry4 == null) {
                indeustryTxt = indeustry1 + "，" + indeustry2 + "，" + indeustry3;
            } else if (indeustry5 == null) {
                indeustryTxt = indeustry1 + "，" + indeustry2 + "，" + indeustry3
                        + "，" + indeustry4;
            } else {
                indeustryTxt = indeustry1 + "，" + indeustry2 + "，" + indeustry3
                        + "，" + indeustry4 + "，" + indeustry5;
            }
            return (indeustryTxt);
        }

        public int getCvId() {
            return cvId;
        }

        public String getPosition4() {
            return position4;
        }

        public void setPosition4(String position4) {
            this.position4 = position4;
        }

        public String getPosition5() {
            return position5;
        }

        public void setPosition5(String position5) {
            this.position5 = position5;
        }

        public String getIndeustry5() {
            return indeustry5;
        }

        public void setIndeustry5(String indeustry5) {
            this.indeustry5 = indeustry5;
        }

        public void setCvId(int cvId) {
            this.cvId = cvId;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getSelfAppraisal() {
            return selfAppraisal;
        }

        public void setSelfAppraisal(String selfAppraisal) {
            this.selfAppraisal = selfAppraisal;
        }

        public String getPosition1() {
            return position1;
        }

        public void setPosition1(String position1) {
            this.position1 = position1;
        }

        public String getPosition2() {
            return position2;
        }

        public void setPosition2(String position2) {
            this.position2 = position2;
        }

        public String getPosition3() {
            return position3;
        }

        public void setPosition3(String position3) {
            this.position3 = position3;
        }

        public String getSalary() {
            return salary;
        }

        public void setSalary(String salary) {
            this.salary = salary;
        }

        public String getCvName() {
            return cvName;
        }

        public void setCvName(String cvName) {
            this.cvName = cvName;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getIndeustry3() {
            return indeustry3;
        }

        public void setIndeustry3(String indeustry3) {
            this.indeustry3 = indeustry3;
        }

        public String getWorkType() {
            return workType;
        }

        public void setWorkType(String workType) {
            this.workType = workType;
        }

        public String getIndeustry4() {
            return indeustry4;
        }

        public void setIndeustry4(String indeustry4) {
            this.indeustry4 = indeustry4;
        }

        public String getIndeustry1() {
            return indeustry1;
        }

        public void setIndeustry1(String indeustry1) {
            this.indeustry1 = indeustry1;
        }

        public String getIndeustry2() {
            return indeustry2;
        }

        public void setIndeustry2(String indeustry2) {
            this.indeustry2 = indeustry2;
        }

        public String getWorkingState() {
            return workingState;
        }

        public void setWorkingState(String workingState) {
            this.workingState = workingState;
        }
    }

    public static class ProjectpdListBean {
        /**
         * projectDescription : 主要负责的是税源信息采集和纳税评估(建安环节，不动产环节)。以不动产为例介绍：采集不动产交易信息，包括项目基本信息，交易基本信息，纳税人基本信息，选取纳税人和项目信息是通过谈出子窗体选取相应的信息，在页面除了基本的验证外，使用Ajax技术进行同名检测，巡查后的信息可补充，核实。
         * developmentTool : Android Studio开发
         * startTime : 2015-03-01
         * jobDescription : 主要就是针对南京溧水县当地的房地产，建立的一个房地产税收管理系统，主要有：税源信息，纳税评估，行政执法，电子档案，综合查询，系统设置，信息变更，地图管理等模块构成，税源信息主要实现税收管理员对土地、建安、增量房、存量房等交易信息的采集、补充、核实。核实后的信息由税收管理员进行纳税评估，计算纳税人应该上缴的税务之后就进入行政执法,对违规的信息进行处理。
         * endTime : 2017-03-01
         * technology : 涉及的技术非常的多，不可一一列举
         * projectName : 人才平台
         */

        private String projectDescription;
        private String developmentTool;
        private String startTime;
        private String jobDescription;
        private String endTime;
        private String technology;
        private String projectName;

        public String getTime() {
            return startTime + " - " + endTime;
        }

        public String getProjectDescription() {
            return projectDescription;
        }

        public void setProjectDescription(String projectDescription) {
            this.projectDescription = projectDescription;
        }

        public String getDevelopmentTool() {
            return developmentTool;
        }

        public void setDevelopmentTool(String developmentTool) {
            this.developmentTool = developmentTool;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getJobDescription() {
            return jobDescription;
        }

        public void setJobDescription(String jobDescription) {
            this.jobDescription = jobDescription;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getTechnology() {
            return technology;
        }

        public void setTechnology(String technology) {
            this.technology = technology;
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }
    }

    public static class ExperiencepdListBean {
        /**
         * enterpriseNature : 国有企业
         * industryType : 计算机软件
         * positions : Android开发工程师
         * jobDescription : 主要负责商品销售和查询统计：商品销售主要涉及到销售和退货，首先商品销售需要选取客户和商品，是从另一个列表页面读取的，是通过在页面编写javascript脚本语言实现的。也可以输入商品编码，页面会自动填写商品的基本信息，使用Ajax发送请求完成。输入销售数量，则会自动计算金额(应收和未收款)，通过javascript实现，保存成功后，根据商品类型和操作时间来生成销售单号，同时对应商品的库存量减少。
         * startTime : 2013-03-01
         * endTime : 2016-03-01
         * department : 技术开发部
         * enterpriseName : 中国科学院
         */

        private String enterpriseNature;
        private String industryType = "其他";
        private String positions;
        private String jobDescription;
        private String startTime;
        private String endTime;
        private String department;
        private String enterpriseName;

        public String getTime() {
            return startTime + " - " + endTime;
        }

        public String getPrimaryInfo() {
            return positions + " | " + department;
        }

        public String getSubInfo() {
            return industryType + " | " + enterpriseNature;
        }

        public String getEnterpriseNature() {
            return enterpriseNature;
        }

        public void setEnterpriseNature(String enterpriseNature) {
            this.enterpriseNature = enterpriseNature;
        }

        public String getIndustryType() {
            return industryType;
        }

        public void setIndustryType(String industryType) {
            this.industryType = industryType;
        }

        public String getPositions() {
            return positions;
        }

        public void setPositions(String positions) {
            this.positions = positions;
        }

        public String getJobDescription() {
            return jobDescription;
        }

        public void setJobDescription(String jobDescription) {
            this.jobDescription = jobDescription;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getEnterpriseName() {
            return enterpriseName;
        }

        public void setEnterpriseName(String enterpriseName) {
            this.enterpriseName = enterpriseName;
        }
    }

    public static class EdiucationpdListBean {
        /**
         * specialty : 物联网应用技术
         * education : 本科
         * overseasStudy : 无
         * startTime : 2012-03-01
         * endTime : 2015-03-01
         * schoolName : 淄博职业学院
         */

        private String specialty;
        private String education;
        private String overseasStudy;
        private String startTime;
        private String endTime;
        private String schoolName;

        public String getTime() {
            return startTime + " - " + endTime;
        }

        public String getSubInfo() {
            return education + "|" + specialty;
        }

        public String getSpecialty() {
            return specialty;
        }

        public void setSpecialty(String specialty) {
            this.specialty = specialty;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getOverseasStudy() {
            return overseasStudy;
        }

        public void setOverseasStudy(String overseasStudy) {
            this.overseasStudy = overseasStudy;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getSchoolName() {
            return schoolName;
        }

        public void setSchoolName(String schoolName) {
            this.schoolName = schoolName;
        }
    }
}
