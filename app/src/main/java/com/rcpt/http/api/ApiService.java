package com.rcpt.http.api;

import com.rcpt.base.HttpResult;
import com.rcpt.base.bean.CodeBean;
import com.rcpt.bean.AcademyInfoBean;
import com.rcpt.bean.AlipayBean;
import com.rcpt.bean.AttrSelectBean;
import com.rcpt.bean.BidContactsBean;
import com.rcpt.bean.BidInfoBean;
import com.rcpt.bean.BidListBean;
import com.rcpt.bean.BidPersonInfoBean;
import com.rcpt.bean.BidRecordListBean;
import com.rcpt.bean.CVEducationListBean;
import com.rcpt.bean.CVInfoBean;
import com.rcpt.bean.CVJobIntentInfoBean;
import com.rcpt.bean.CVPreviewInfoBean;
import com.rcpt.bean.CVProjectExListBean;
import com.rcpt.bean.CVSelectBean;
import com.rcpt.bean.CVSelfAppraisalInfoBean;
import com.rcpt.bean.CVWorkExListBean;
import com.rcpt.bean.ChooseIndustryBean;
import com.rcpt.bean.ChooseProvinceBean;
import com.rcpt.bean.CompanyFavoritesListBean;
import com.rcpt.bean.CompanyInfoBean;
import com.rcpt.bean.CompanyNewsListBean;
import com.rcpt.bean.CompanyRecruitAcceptNumListBean;
import com.rcpt.bean.CompanyRecruitInfoBean;
import com.rcpt.bean.CompanyRecruitListBean;
import com.rcpt.bean.ConsultServiceListBean;
import com.rcpt.bean.EnterpriseInfoBean;
import com.rcpt.bean.EnterpriseListBean;
import com.rcpt.bean.FavoritesBean;
import com.rcpt.bean.HomeInfoBean;
import com.rcpt.bean.InstituteBean;
import com.rcpt.bean.InstitutionDetailBean;
import com.rcpt.bean.InterviewMessageListBean;
import com.rcpt.bean.JobCategoryBean;
import com.rcpt.bean.JobGuideListBean;
import com.rcpt.bean.JobInviteListBean;
import com.rcpt.bean.MyOrderListBean;
import com.rcpt.bean.NewsListBean;
import com.rcpt.bean.PersonInfoBean;
import com.rcpt.bean.PolicyListBean;
import com.rcpt.bean.PolicyTypeBean;
import com.rcpt.bean.RecruitFragmentDataBean;
import com.rcpt.bean.RecruitJobFilterBean;
import com.rcpt.bean.RecruitJobInfoBean;
import com.rcpt.bean.RecruitListBean;
import com.rcpt.bean.RecruitPositionFilterBean;
import com.rcpt.bean.RegisterClauseBean;
import com.rcpt.bean.SchoolListBean;
import com.rcpt.bean.ScrollFilterBean;
import com.rcpt.bean.SendRecordBean;
import com.rcpt.bean.SystemMessageBean;
import com.rcpt.bean.SystemUserFilterBean;
import com.rcpt.bean.SystemUserListBean;
import com.rcpt.bean.TestAnswerBean;
import com.rcpt.bean.TestMajorListBean;
import com.rcpt.bean.TestMajorTypeBean;
import com.rcpt.bean.TestQuestionInfoBean;
import com.rcpt.bean.TestRecordListBean;
import com.rcpt.bean.User;
import com.rcpt.bean.UserManageListBean;
import com.rcpt.bean.VideoCourseTypeBean;
import com.rcpt.bean.VideoInfoBaseBean;
import com.rcpt.bean.VideoInfoCatalogBean;
import com.rcpt.bean.VideoListBean;
import com.rcpt.bean.VideoPermissionBean;
import com.rcpt.bean.WxPayBean;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by lvzp on 2017/3/10.
 * 类描述：
 */

public interface ApiService {

    // String HTTP_HOST = "http://172.29.140.86:8080/";
    // String BASE_URL = "http://123.232.109.129/";
    //String HTTP_HOST = "http://103.239.152.67:6090/";
    String HTTP_HOST = "http://rcpt.jinansourcing.com/";
    //String HTTP_HOST = "http://172.29.140.141:8080/";
    String BASE_URL = HTTP_HOST + "RCPT/";
    String IMAGE_BASE = HTTP_HOST + "images/";

    /**
     * 注册条款
     *
     * @return
     */
    @GET("personRegapp/goRegistration.app")
    Observable<HttpResult<RegisterClauseBean>> registerClause();

    /**
     * 个人注册的接口
     *
     * @param regType 注册类型，分为手机注册和邮箱注册 {@see 手机注册(String phone)}  {@see 邮箱注册(String mail)}
     * @param account 账户
     * @param pwd     密码
     * @param mail    邮箱
     * @return
     */
    @POST("personRegapp/personReg.app")
    @FormUrlEncoded
    Observable<HttpResult<String>> personReg(@Field("regType") String regType, @Field("account") String account,
                                             @Field("pwd") String pwd, @Field("mail") String mail, @Field("phone") String phone, @Field("random") String verify);

    /**
     * 个人注册-获取手机验证码
     *
     * @param phoneNum 手机号码
     * @return
     */
    @GET("personRegapp/sendMsgByTel.app")
    Observable<HttpResult<CodeBean>> personSendPhoneCode(@Query("phone") String phoneNum);

    /**
     * 个人注册-获取邮箱验证码
     *
     * @param email
     * @return
     */
    @GET("personRegapp/sendMsgByMail.app")
    Observable<HttpResult<CodeBean>> personSendEmailCode(@Query("mail") String email);


    /**
     * 登录
     *
     * @param account 帐号
     * @param pwd     密码
     * @return
     */
    @POST("userInfoapp/login.app")
    @FormUrlEncoded
    Observable<HttpResult<User>> login(@Field("account") String account, @Field("pwd") String pwd);

    /**
     * 政策法规类型
     *
     * @return
     */
    @GET("policiesapp/policytype.app")
    Observable<HttpResult<List<PolicyTypeBean>>> policyType();

    /**
     * 政策法规列表
     *
     * @param category
     * @return
     */
    @GET("policiesapp/policylist.app")
    Observable<HttpResult<PolicyListBean>> policyList(@Query("category") String category, @Query("pageNum") int pageNum);

    /**
     * 政策法规的详情
     *
     * @param id
     * @return
     */
    @GET("policiesapp/policydetail.app")
    Observable<HttpResult<HomeInfoBean>> policyDetail(@Query("id") String id);

    /**
     * 新闻类型
     *
     * @return
     */
    @GET("newsapp/newstype.app")
    Observable<HttpResult<List<PolicyTypeBean>>> newsType();

    /**
     * 新闻列表
     *
     * @param category
     * @return
     */
    @GET("newsapp/newslist.app")
    Observable<HttpResult<NewsListBean>> newsList(@Query("category") String category, @Query("pageNum") int page);

    /**
     * 新闻详情
     *
     * @param id
     * @return
     */
    @GET("newsapp/newsdetail.app")
    Observable<HttpResult<HomeInfoBean>> newsDetail(@Query("id") String id);

    /**
     * 企业新闻详情
     *
     * @return
     */
    @GET("newsapp/companynewsdetail.app")
    Observable<HttpResult<HomeInfoBean>> companyNewsDetail(@Query("id") String id);

    /**
     * 咨询服务的类型
     *
     * @return
     */
    @GET("policiesapp/consultancytype.app")
    Observable<HttpResult<List<PolicyTypeBean>>> consultancyType();

    /**
     * 行业类别列表
     *
     * @return
     */
    @GET("teamRegapp/listIndustry.app")
    Observable<HttpResult<List<ChooseIndustryBean>>> industryList();

    /**
     * 省列表
     *
     * @return
     */
    @GET("teamRegapp/province.app")
    Observable<HttpResult<List<ChooseProvinceBean>>> provinceList(@Query("parentId") String provinceId);

    /**
     * 咨询服务的列表
     *
     * @param category
     * @return
     */
    @GET("policiesapp/consultancylist.app")
    Observable<HttpResult<ConsultServiceListBean>> consultancyList(@Query("category") String category, @Query
            ("pageNum") int page);

    /**
     * 咨询服务的详情
     *
     * @param id
     * @return
     */
    @GET("policiesapp/consultancydetail.app")
    Observable<HttpResult<HomeInfoBean>> consultancyDetail(@Query("id") String id);

    /**
     * 根据手机号重置密码-发送验证码
     *
     * @param phoneNum 手机号
     * @return
     */
    @POST("userInfoapp/sendMsgByTel.app")
    @FormUrlEncoded
    Observable<HttpResult<CodeBean>> sendCodeByPhone(@Field("phone") String phoneNum);

    /**
     * 根据手机号重置密码-修改密码
     *
     * @param phoneNum 手机号码
     * @param password 新密码
     * @return
     */
    @POST("userInfoapp/editPwdByTel.app")
    @FormUrlEncoded
    Observable<HttpResult<String>> editCodeByPhone(@Field("phone") String phoneNum, @Field("pwd") String password);

    /**
     * 根据邮箱重置密码-发送验证码
     *
     * @param email 邮箱号
     * @return
     */
    @GET("userInfoapp/sendMsgByMail.app")
    Observable<HttpResult<CodeBean>> sendCodeByEmail(@Query("mail") String email);

    /**
     * 根据邮箱重置密码-修改密码
     *
     * @param email    邮箱号
     * @param password 新密码
     * @return
     */
    @POST("userInfoapp/editPwdByMail.app")
    @FormUrlEncoded
    Observable<HttpResult<String>> editCodeByEmail(@Field("mail") String email, @Field("pwd") String password);

    /**
     * 修改个人信息
     *
     * @param userId
     * @param name
     * @param sex           性别的编码写在了实体类中
     * @param birthDate
     * @param contactInfo
     * @param mail
     * @param workDate
     * @param overseasWork
     * @param maritalStatus 婚姻状态的编码是写在了实体类中
     * @return
     */
    @POST("personalInfoapp/updatePersonalInfo.app")
    @FormUrlEncoded
    Observable<HttpResult<String>> updatePersonInfo(
            @Field("userId") String userId//用户id
            , @Field("name") String name//用户名
            , @Field("sex") String sex//用户性别
            , @Field("birthDate") String birthDate//用户的生日
            , @Field("contactInfo") String contactInfo//联系电话
            , @Field("mail") String mail//联系邮箱
            , @Field("workDate") String workDate//参加工作日期
            , @Field("overseasWork") String overseasWork//海外工作经历
            , @Field("maritalStatus") String maritalStatus
            , @Field("education") String education//婚姻状态
    );

    /**
     * 获取个人信息
     *
     * @param userId
     * @return
     */
    @GET("personalInfoapp/getPersonalInfo.app")
    Observable<HttpResult<PersonInfoBean>> getPersonInfo(@Query("userId") String userId);

    /**
     * 修改个人头像
     *
     * @param photo
     * @param userId
     * @return
     */
    @POST("personalInfoapp/updateUserPicture.app")
    @Multipart
    Observable<HttpResult<String>> uploadAvatar(@Part MultipartBody.Part photo, @Part("userId") RequestBody userId);

    /**
     * 学历列表数据
     *
     * @return
     */
    @GET("cvManagerapp/getEducation.app")
    Observable<HttpResult<List<AttrSelectBean>>> getEducation();

    /**
     * 查询简历
     */
    @GET("cvManagerapp/getCvInfoList.app")
    Observable<HttpResult<List<CVInfoBean>>> getCvInfo(@Query("userId") String userId);

    /**
     * 创建新的简历
     *
     * @param params 上传的参数
     * @return 返回创建完成的简历Id
     */
    @POST("cvManagerapp/saveCvBasicInfo.app")
    @FormUrlEncoded
    Observable<HttpResult<String>> createNewCv(@FieldMap Map<String, String> params);

    /**
     * 保存教育背景信息
     *
     * @param map
     * @return
     */
    @POST("cvManagerapp/saveEducationInfo.app")
    @FormUrlEncoded
    Observable<HttpResult<String>> saveEducationInfo(@FieldMap Map<String, String> map);

    /**
     * 修改教育背景
     */
    @POST("cvManagerapp/updateCvEducationInfo.app")
    @FormUrlEncoded
    Observable<HttpResult<String>> editCVEducationInfo(@FieldMap Map<String, String> map);

    /**
     * 保存工作经历信息
     *
     * @param map
     * @return
     */
    @POST("cvManagerapp/saveWorkExperience.app")
    @FormUrlEncoded
    Observable<HttpResult<String>> saveWorkExperience(@FieldMap Map<String, String> map);

    /**
     * 修改工作经历信息
     *
     * @param map
     * @return
     */
    @POST("cvManagerapp/updateWorkExperience.app")
    @FormUrlEncoded
    Observable<HttpResult<String>> editWorkExperience(@FieldMap Map<String, String> map);

    /**
     * 保存项目经验信息
     *
     * @param map
     * @return
     */
    @POST("cvManagerapp/saveCvProject.app")
    @FormUrlEncoded
    Observable<HttpResult<String>> saveCvProject(@FieldMap Map<String, String> map);

    /**
     * 修改项目经验信息
     *
     * @param map
     * @return
     */
    @POST("cvManagerapp/updateCvProject.app")
    @FormUrlEncoded
    Observable<HttpResult<String>> editCvProject(@FieldMap Map<String, String> map);

    /**
     * 获取招聘页面的展示列表
     *
     * @return
     */
    @GET("recruitapp/recruitindex.app")
    Observable<HttpResult<RecruitFragmentDataBean>> getRecruitIndex();

    /**
     * 获取教育背景列表
     */
    @GET("cvManagerapp/getCvEducationInfo.app")
    Observable<HttpResult<List<CVEducationListBean>>> getCVEducationList(@Query("userId") String userId, @Query("cvId") String cvId);

    /**
     * 获取工作经历列表
     */
    @GET("cvManagerapp/getExperienceList.app")
    Observable<HttpResult<List<CVWorkExListBean>>> getCVExperienceList(@Query("userId") String userId, @Query("cvId") String cvId);

    /**
     * 获取项目经验列表
     */
    @GET("cvManagerapp/getCvProject.app")
    Observable<HttpResult<List<CVProjectExListBean>>> getCVProjectExList(@Query("userId") String userId, @Query("cvId") String cvId);

    /**
     * 企业性质列表数据
     *
     * @return
     */
    @GET("businessjobapp/businesstype.app")
    Observable<HttpResult<List<AttrSelectBean>>> getBusinessType();

    /**
     * 企业注册
     *
     * @param license
     * @param params
     * @return
     */
    @POST("teamRegapp/teamReg.app")
    @Multipart
    Observable<HttpResult<String>> enterpriseRegister(@Part MultipartBody.Part license, @QueryMap Map<String, String> params);

    /**
     * 获取自我评价
     *
     * @param userId
     * @param cvId
     * @return
     */
    @GET("cvManagerapp/getCvSelfAppraisalInfo.app")
    Observable<HttpResult<CVSelfAppraisalInfoBean>> getCVSelfAppraisalInfo(@Query("userId") String userId, @Query("cvId") String cvId);

    /**
     * 修改自我评价
     *
     * @param userId
     * @param cvId
     * @param selfAppr
     * @return
     */
    @POST("cvManagerapp/updateCvSelfAppraisalInfo.app")
    @FormUrlEncoded
    Observable<HttpResult<String>> updateCVSelfAppraisalInfo(@Field("userId") String userId, @Field("cvId") String cvId, @Field("selfAppr") String selfAppr);

    /**
     * 修改简历名称
     *
     * @return
     */
    @POST("cvManagerapp/updateCvName.app")
    @FormUrlEncoded
    Observable<HttpResult<String>> updateCVName(@Field("userId") String userId, @Field("cvId") String cvId, @Field("resumeName") String resumeName);

    /**
     * 检测个人信息是否完善
     *
     * @param userId 用户id
     * @return
     */
    @GET("cvManagerapp/checkPersonalInfo.app")
    Observable<HttpResult<String>> checkPersonInfo(@Query("userId") String userId);

    /**
     * 获取职位类别的列表
     *
     * @return
     */
    @GET("teamRegapp/workTypeList.app")
    Observable<HttpResult<List<AttrSelectBean>>> getWorkTypeList();

    /**
     * 获取薪资的列表
     *
     * @return
     */
    @GET("teamRegapp/salaryList.app")
    Observable<HttpResult<List<AttrSelectBean>>> getSalaryList();

    /**
     * 获取求职状态的列表
     *
     * @return
     */
    @GET("teamRegapp/workStatusList.app")
    Observable<HttpResult<List<AttrSelectBean>>> getWorkStatusList();

    /**
     * 获取职位类型
     *
     * @return
     */
    @GET("teamRegapp/positionType.app")
    Observable<HttpResult<List<JobCategoryBean>>> getJobCatrgoryList();

    /**
     * 获取求职意向信息
     *
     * @return
     */
    @GET("cvManagerapp/getExpectedWorkInfo.app")
    Observable<HttpResult<CVJobIntentInfoBean>> getCVJobIntentInfo(@Query("userId") String userId, @Query("cvId") String cvId);

    /**
     * 设置简历的公开状态
     *
     * @return
     */
    @POST("cvManagerapp/updateCvPublicSet.app")
    @FormUrlEncoded
    Observable<HttpResult<String>> updateCVPublisSet(@Field("userId") String userId, @Field("cvId") String cvId, @Field("publicSet") String publicSet);

    /**
     * 上传求职信息
     *
     * @param map
     * @return
     */
    @POST("cvManagerapp/updateCvBasicInfo.app")
    @FormUrlEncoded
    Observable<HttpResult<String>> updateCVJobIntent(@FieldMap Map<String, String> map);


    /**
     * 获取收藏夹列表
     *
     * @param userId 用户id
     * @return
     */
    @GET("favoritesapp/getFavoriteslist.app")
    Observable<HttpResult<FavoritesBean>> getFavoriteslist(@Query("userId") String userId, @Query("pageNum") int page);

    /**
     * 获取收藏夹内容
     *
     * @param userId
     * @param recordId
     * @return
     */
    @GET("favoritesapp/getFavoriteInfo.app")
    Observable<HttpResult<FavoritesBean.FavoritesListBean>> getFavoritesDetails(@Query("userId") String userId, @Query("id") String recordId);

    /**
     * 获取企业列表
     *
     * @param category
     * @param pageNum
     * @return
     */
    @GET("businessjobapp/business.app")
    Observable<HttpResult<EnterpriseListBean>> getEnterpriseList(@Query("category") String category, @Query("keyword") String keyword, @Query("pageNum") int pageNum);

    /**
     * 获取企业详情
     *
     * @return
     */
    @GET("businessjobapp/businessinfo.app")
    Observable<HttpResult<EnterpriseInfoBean>> getEnterpriseInfo(@Query("companyId") String companyId);

    /**
     * 根据类型的不同，获取职位的列表或者是简历的列表
     *
     * @param userType 用户类型 个人用户和企业用户
     * @param pageNum  页数
     * @return 招聘的实体类
     */
    @POST("businessjobapp/talentlist.app")
    @FormUrlEncoded
    Observable<HttpResult<RecruitListBean>> getRecruitList(@Field("userType") String userType
            , @Field("pageNum") int pageNum
            , @Field("HopeJobId") String HopeJobId
            , @Field("cityId") String cityId
            , @Field("eduBackGround") String eduBackGround
            , @Field("workyear") String workyear
            , @Field("hopeSalaryId") String hopeSalaryId
            , @Field("JobType") String JobType
            , @Field("Salary") String Salary
            , @Field("WorkTypeID") String WorkTypeID
            , @Field("OrientedGroup") String OrientedGroupId
            , @Field("name") String name
            , @Field("JobName") String JobName);

    /**
     * 获取职位详情
     *
     * @param companyId
     * @param userId
     * @param recruitmentInfoId
     * @return
     */
    @GET("businessjobapp/talentinfo.app")
    Observable<HttpResult<RecruitJobInfoBean>> getRecruitJobInfo(@Query("companyId") String companyId, @Query("userId") String userId, @Query("recruitmentInfoId") String recruitmentInfoId);

    /**
     * 获取求职指南详情
     *
     * @param employmentId
     * @return
     */
    @GET("recruitapp/employmentGuide.app")
    Observable<HttpResult<HomeInfoBean>> getEmploymentGuide(@Query("employmentId") String employmentId);

    /**
     * 获取学校详情
     *
     * @param schoolId
     * @return
     */
    @GET("recruitapp/schoolinfo.app")
    Observable<HttpResult<AcademyInfoBean>> getSchoolInfo(@Query("schoolId") String schoolId);

    /**
     * 获取学校的筛选器
     *
     * @return
     */
    @GET("recruitapp/conditionList.app")
    Observable<HttpResult<ScrollFilterBean>> getScrollFilter();

    /**
     * 获取求职指南的类型
     *
     * @return
     */
    @GET("recruitapp/recruittype.app")
    Observable<HttpResult<List<AttrSelectBean>>> getJobGuideType();

    /**
     * 获取求职指南列表
     *
     * @param pageNum
     * @return
     */
    @GET("recruitapp/recruitlist.app")
    Observable<HttpResult<JobGuideListBean>> getJobGuideList(@Query("category") String category, @Query("pageNum") int pageNum);

    /**
     * 获取学校列表
     *
     * @param batch
     * @param schoolType
     * @param pageNum
     * @return
     */
    @GET("recruitapp/schoollist.app")
    Observable<HttpResult<SchoolListBean>> getSchoolList(@Query("batch") String batch, @Query("schoolType") String schoolType, @Query("pageNum") int pageNum);

    /**
     * 获取简历列表
     *
     * @return
     */
    @GET("companyapp/getCv.app")
    Observable<HttpResult<List<CVSelectBean>>> getCVSelectList(@Query("userId") String userId);

    /**
     * 开始投递简历
     *
     * @param cvId          简历id
     * @param userId        用户id
     * @param recruitmentId 职位id
     * @param companyId     公司id
     * @return
     */
    @POST("companyapp/sendCv.app")
    @FormUrlEncoded
    Observable<HttpResult<String>> startSendResume(@Field("cvId") String cvId, @Field("userId") String userId, @Field("recruitmentId") String recruitmentId, @Field("companyId") String companyId);

    /**
     * 获取投递记录列表
     *
     * @param userId
     * @return
     */
    @GET("deliveryRecordapp/getCvSendInfoList.app")
    Observable<HttpResult<SendRecordBean>> getSendRecordList(@Query("userId") String userId, @Query("pageNum") int page);

    /**
     * 删除投递记录
     *
     * @param recordId
     * @param userId
     * @return
     */
    @DELETE("deliveryRecordapp/deleteCvSendInfo.app")
    Observable<HttpResult<String>> deleteCVSendRecord(@Query("recordId") String recordId, @Query("userId") String userId);

    /**
     * 获取职位邀请的列表
     *
     * @param userId
     * @return
     */
    @GET("posInvitationapp/getInvitationlist.app")
    Observable<HttpResult<JobInviteListBean>> getInvitationList(@Query("userId") String userId, @Query("pageNum") int page);

    /**
     * 收藏职位
     *
     * @param userId        用户id
     * @param recruitmentId 招聘信息Id
     * @param companyId     招聘公司id
     * @return
     */
    @GET("companyapp/collectJob.app")
    Observable<HttpResult<String>> collectJob(@Query("userId") String userId, @Query("recruitmentId") String recruitmentId, @Query("companyId") String companyId);

    /**
     * 删除收藏夹中的职位收藏
     *
     * @param userId
     * @param id
     * @return
     */
    @DELETE("favoritesapp/deleteFavorites.app")
    Observable<HttpResult<String>> deleteFavorite(@Query("userId") String userId, @Query("id") String id);

    /**
     * 获取招聘列表中的简历预览的信息
     *
     * @param cvId
     * @param cvUserId
     * @param userId
     * @return
     */
    @GET("resumeDetailsapp/getResumeDetailsInfo.app")
    Observable<HttpResult<CVPreviewInfoBean>> getCVPreviewInfo(@Query("cvId") String cvId, @Query("cvUserId") String cvUserId, @Query("companyId") String userId);

    /**
     * 人的简历预览的信息
     *
     * @param cvId
     * @param userId
     * @return
     */
    @GET("resumeDetailsapp/previewResume.app")
    Observable<HttpResult<CVPreviewInfoBean>> getCVPreviewResume(@Query("cvId") String cvId, @Query("userId") String userId);

    /**
     * 获取专业测试的类型
     *
     * @return
     */
    @GET("testapp/Knowledgetype.app")
    Observable<HttpResult<List<TestMajorTypeBean>>> getTestMajorType();

    /**
     * 专业测试详细信息
     *
     * @param id
     * @return
     */
    @GET("testapp/evaluationStartTesting.app")
    Observable<HttpResult<TestQuestionInfoBean>> getTestMajorInfo(@Query("id") String id, @Query("userId") String userId);

    /**
     * 专业测试列表数据
     *
     * @param pageNum
     * @param pointId
     * @param testPaperName
     * @return
     */
    @GET("testapp/evaluationTestPaperList.app")
    Observable<HttpResult<TestMajorListBean>> getTestMajorList(@Query("pageNum") int pageNum, @Query("pointId") String pointId, @Query("testPaperName") String testPaperName);

    /**
     * 专业知识测试交卷
     *
     * @param userId
     * @param id
     * @param as
     * @return
     */
    @POST("testapp/examSubmit.app")
    @FormUrlEncoded
    Observable<HttpResult<TestAnswerBean>> uploadTestMajorSubmit(@Field("userId") String userId, @Field("id") String id, @Field("as") String as);

    /**
     * 查询测试题的详细信息
     *
     * @param fieldId 12为职业倾向测试
     *                13为能力测试
     * @param userId  用户id
     * @return
     */
    @GET("testapp/abilityTest.app")
    Observable<HttpResult<TestQuestionInfoBean>> getTestQuestionInfo(@Query("fieldId") String fieldId, @Query("userId") String userId);

    /**
     * 能力测试试卷答案提交
     *
     * @param userId
     * @param id
     * @param as
     * @return
     */
    @POST("testapp/abilityPaperSubmit.app")
    @FormUrlEncoded
    Observable<HttpResult<TestAnswerBean>> uploadTestAbilitySubmit(@Field("userId") String userId, @Field("id") String id, @Field("as") String as);

    /**
     * 提交职业答案
     *
     * @param userId
     * @param id
     * @param as
     * @return
     */
    @POST("testapp/occupationtestPaperSubmit.app")
    @FormUrlEncoded
    Observable<HttpResult<TestAnswerBean>> uploadTestOccupation(@Field("userId") String userId, @Field("id") String id, @Field("as") String as);

    /**
     * 获取个人界面中----企业详情
     *
     * @param companyId
     * @return
     */
    @GET("companyConsoleapp/getCompanyBaseInfo.app")
    Observable<HttpResult<CompanyInfoBean>> getCompanyInfo(@Query("companyId") String companyId);

    /**
     * 上传企业信息
     *
     * @param params
     * @return
     */
    @POST("companyConsoleapp/updateCompanyBaseInfo.app")
    @FormUrlEncoded
    Observable<HttpResult<String>> updateCompanyInfo(@FieldMap Map<String, String> params);

    /**
     * 获取企业新闻列表
     *
     * @param userId 用户id
     * @param page   分页页数
     * @return
     */
    @GET("companyNewsapp/getNewslistPage.app")
    Observable<HttpResult<CompanyNewsListBean>> getCompanyNewsList(@Query("companyId") String userId, @Query("pageNum") int page);

    /**
     * 修改企业Logo
     */
    @POST("companyConsoleapp/updateCompanyLogo.app")
    @Multipart
    Observable<HttpResult<String>> updateCompanyLogo(@Part MultipartBody.Part photo, @Part("companyId") RequestBody userId);

    /**
     * 获取简历列表筛选的条件
     *
     * @return
     */
    @GET("businessjobapp/businefilter.app")
    Observable<HttpResult<RecruitPositionFilterBean>> getRecruitPositionFilter();

    /**
     * 获取简历列表筛选的子职位
     */
    @GET("businessjobapp/getChild.app")
    Observable<HttpResult<List<RecruitPositionFilterBean.JobChilCateListBean>>> getRecruitChildPosition(@Query("parentId") String parentId);

    /**
     * 获取职位列表筛选的条件
     *
     * @return
     */
    @GET("businessjobapp/talentfilter.app")
    Observable<HttpResult<RecruitJobFilterBean>> getRecruitJobFilter();

    /**
     * 获取首页五条培训机构
     *
     * @return
     */
    @GET("indexapp/indexnews.app")
    Observable<HttpResult<InstituteBean>> getHomeInstitutionList();

    /**
     * 获取培训机构列表
     *
     * @return
     */
    @GET("institutionapp/institutionListMore.app")
    Observable<HttpResult<InstituteBean>> getInstituteList(@Query("pageNum") int page);

    /**
     * 获取培训机构详情
     *
     * @param institutionsId 培训机构id
     * @return
     */
    @GET("institutionapp/institutionInfo.app")
    Observable<HttpResult<InstitutionDetailBean>> getInstitutionInfo(@Query("institutionsId") String institutionsId);

    /**
     * 收藏简历信息
     *
     * @return
     */
    @POST("companyapp/collectResume.app")
    @FormUrlEncoded
    Observable<HttpResult<String>> collectResume(@Field("cvId") String cvId, @Field("cvUserId") String cvUserId, @Field("companyId") String companyId);

    /**
     * 企业收藏列表
     *
     * @return
     */
    @GET("companyConsoleapp/getCompanyFavorites.app")
    Observable<HttpResult<CompanyFavoritesListBean>> getCompanyFavorites(@Query("companyId") String companyId, @Query("pageNum") int pageNum);

    /**
     * 删除收藏
     *
     * @return
     */
    @DELETE("companyConsoleapp/deleteCompanyFavorites.app")
    Observable<HttpResult<String>> deleteCompanyFavorites(@Query("companyId") String companyId, @Query("id") String id);

    /**
     * 获取面试邀请的列表
     *
     * @param userId
     * @param pageNum
     * @return
     */
    @GET("interviewapp/getInterviewlist.app")
    Observable<HttpResult<InterviewMessageListBean>> getInterviewMessageList(@Query("userId") String userId, @Query("pageNum") int pageNum);

    /**
     * 处理面试邀请的结果
     */
    @POST("interviewapp/updateInterviewStatus.app")
    @FormUrlEncoded
    Observable<HttpResult<String>> updateInterviewStatus(@Field("id") String id, @Field("status") String status, @Field("reason") String reason);

    /**
     * 首页-接发包-竞标
     *
     * @param page
     * @return
     */
    @GET("packageapp/integratedproject.app")
    Observable<HttpResult<BidListBean>> getBidObjectList(@Query("pageNum") int page);

    /**
     * 首页-接发包-人员
     *
     * @param page
     * @return
     */
    @GET("packageapp/integratedperson.app")
    Observable<HttpResult<BidListBean>> getBidPersonList(@Query("pageNum") int page);

    /**
     * 首页-接发包-竞标-详情
     *
     * @param bidId
     * @return
     */
    @GET("packageapp/projectinfo.app")
    Observable<HttpResult<BidInfoBean>> getBidInfo(@Query("id") String bidId, @Query("companyId") String companyId);

    /**
     * 首页-接发包-人员-详情
     *
     * @param bidPersonId
     * @return
     */
    @GET("packageapp/personnelprojectinfo.app")
    Observable<HttpResult<BidPersonInfoBean>> getBidPersonInfo(@Query("id") String bidPersonId, @Query("companyId") String companyId);

    /**
     * 获取投标的人信息
     *
     * @return
     */
    @GET("packageapp/projectbackinfo.app")
    Observable<HttpResult<BidContactsBean>> getBidContract(@Query("companyId") String companyId);

    /**
     * 项目投标
     *
     * @return
     */
    @POST("packageapp/companyprojectcheck.app")
    @FormUrlEncoded
    Observable<HttpResult<String>> companyProjectBid(@FieldMap Map<String, String> params);


    /**
     * 人员投标
     *
     * @return
     */
    @POST("packageapp/personpprojectcheck.app?")
    @FormUrlEncoded
    Observable<HttpResult<String>> companyPresenterBid(@FieldMap Map<String, String> params);

    /**
     * 获取当前企业的招聘信息列表
     */
    @GET("companyRecruitapp/getRecruitlist.app")
    Observable<HttpResult<CompanyRecruitListBean>> getRecruitList(@Query("companyId") String companyId, @Query("pageNum") int pageNum, @Query("cvUserId") String cvUserId, @Query("cvId") String cvId);

    /**
     * 发布职位邀请
     *
     * @return
     */
    @POST("companyapp/addJobInvitation.app")
    @FormUrlEncoded
    Observable<HttpResult<String>> addJobInvitation(@FieldMap Map<String, String> params);

    /**
     * 获取当前企业的招聘信息详情
     *
     * @param companyId
     * @param recruitmentInfoId
     * @return
     */
    @GET("companyRecruitapp/toUpdateRecruit.app")
    Observable<HttpResult<CompanyRecruitInfoBean>> getCompanyRecruitInfo(@Query("companyId") String companyId, @Query("recruitmentInfoId") String recruitmentInfoId);

    /**
     * 修改密码
     */
    @POST("personalInfoapp/updatePossword.app")
    @FormUrlEncoded
    Observable<HttpResult<String>> updatePassword(@Field("userId") String userId, @Field("oldPwd") String oldPwd, @Field("newPwd") String newPwd);

    /**
     * 删除教育背景
     *
     * @return
     */
    @DELETE("cvManagerapp/deleteedu.app")
    Observable<HttpResult<String>> deleteEdu(@Query("userId") String userId, @Query("infoId") String infoId, @Query("cvId") String cvId);

    /**
     * 删除项目经验
     *
     * @return
     */
    @DELETE("cvManagerapp/deletepro.app")
    Observable<HttpResult<String>> deletePro(@Query("userId") String userId, @Query("infoId") String infoId, @Query("cvId") String cvId);

    /**
     * 删除工作经历
     *
     * @return
     */
    @DELETE("cvManagerapp/deletework.app")
    Observable<HttpResult<String>> deleteWork(@Query("userId") String userId, @Query("infoId") String infoId, @Query("cvId") String cvId);

    /**
     * 获取职位招聘的应聘人数列表
     *
     * @param companyId
     * @param recruitmentInfoId
     * @param pageNum
     * @return
     */
    @GET("companyRecruitapp/getJobPeople.app")
    Observable<HttpResult<CompanyRecruitAcceptNumListBean>> getJobPeople(@Query("companyId") String companyId, @Query("recruitmentInfoId") String recruitmentInfoId, @Query("pageNum") int pageNum);

    /**
     * 发送面试通知
     *
     * @param params
     * @return
     */
    @POST("companyRecruitapp/addNotice.app")
    @FormUrlEncoded
    Observable<HttpResult<String>> addNotice(@FieldMap Map<String, String> params);

    /**
     * 更新简历的阅览状态
     */
    @GET("companyRecruitapp/updateBeenViewed.app")
    Observable<HttpResult<String>> updateCVPreviewStatus(@Query("companyId") String companyId, @Query("userId") String userId, @Query("cvId") String cvId);

    /**
     * 获取支付宝的支付订单信息
     *
     * @return
     */
    @POST("courseinterfaceapp/alipayPerOrder.app")
    @FormUrlEncoded
    Observable<HttpResult<AlipayBean>> getAlipayOrderInfo(@Field("user_id") String user_id
            , @Field("course_name") String course_name
            , @Field("commodity_id") String commodity_id
            , @Field("body") String body
            , @Field("course_id") String course_id
            , @Field("subject_id") String subject_id
            , @Field("teaching_style") String teaching_style
            , @Field("subject") String subject
            , @Field("total_amount") String total_amount
            , @Field("order_number") String order_number);

    /**
     * 获取微信的支付订单信息
     *
     * @return
     */
    @POST("courseinterfaceapp/wxpayPlaceOrder.app")
    @FormUrlEncoded
    Observable<HttpResult<WxPayBean>> getWeixinOrderInfo(@Field("user_id") String user_id
            , @Field("course_name") String course_name
            , @Field("body") String body
            , @Field("course_id") String course_id
            , @Field("total_fee") String total_fee
            , @Field("ip") String ip
            , @Field("commodity_id") String commodity_id
            , @Field("subject_id") String subject_id
            , @Field("teaching_style") String teaching_style
            , @Field("subject") String subject
            , @Field("order_number") String order_number);

    /**
     * 0元课程购买
     *
     * @return
     */
    @POST("courseinterfaceapp/pay.app")
    @FormUrlEncoded
    Observable<HttpResult<String>> videoFreePay(@Field("userId") String user_id
            , @Field("courseName") String course_name
            , @Field("commodityId") String commodity_id
            , @Field("body") String body
            , @Field("courseId") String course_id
            , @Field("subjectId") String subject_id
            , @Field("teachingStyle") String teaching_style
            , @Field("subject") String subject
            , @Field("price") String total_amount
    );

    /**
     * 获取视频的列表
     *
     * @param itemOneId    全部课程
     * @param itemSecondId 学科小类
     * @param flag         授课方式
     * @param sort         排序方式
     * @param pageNum      页码
     * @return
     */
    @GET("courseinterfaceapp/appGetCourseList.app")
    Observable<HttpResult<VideoListBean>> getVideoList(@Query("itemOneId") String itemOneId, @Query("itemSecondId") String itemSecondId, @Query("flag") String flag, @Query("sort") String sort, @Query("pageNum") int pageNum);

    /**
     * 获取视频的详细资料
     *
     * @param classTypeId
     * @param userId
     * @return
     */
    @GET("courseinterfaceapp/appGetCourseDetail.app")
    Observable<HttpResult<VideoInfoBaseBean>> getVideoInfoBase(@Query("userId") String userId, @Query("classTypeId") String classTypeId);

    /**
     * 获取视频目录列表
     *
     * @param userId
     * @param classTypeId
     * @return
     */
    @GET("courseinterfaceapp/appGetCourseMenu.app")
    Observable<HttpResult<List<VideoInfoCatalogBean>>> getVideoCatalogList(@Query("userId") String userId, @Query("classTypeId") String classTypeId);

    /**
     * 获取视频课程类型的列表
     *
     * @return
     */
    @GET("courseinterfaceapp/appGetCourseType.app")
    Observable<HttpResult<List<VideoCourseTypeBean>>> getVideoCourseTypeList();

    /**
     * 获取视频课程订单
     *
     * @param user_id
     * @param payFlag
     * @param pageNum
     * @return
     */
    @GET("courseinterfaceapp/purchasedCourseList.app")
    Observable<HttpResult<MyOrderListBean>> getVideoPayOrderList(@Query("user_id") String user_id, @Query("payFlag") String payFlag, @Query("pageNum") int pageNum);

    /**
     * 检测视频播放的权限
     *
     * @param userId
     * @param classTypeId
     * @param moduleId
     * @param lectureId
     * @return
     */
    @GET("courseinterfaceapp/appGetCoursePermission.app")
    Observable<HttpResult<VideoPermissionBean>> getCoursePermission(@Query("userId") String userId
            , @Query("classTypeId") String classTypeId
            , @Query("moduleId") String moduleId
            , @Query("lectureId") String lectureId
    );

    /**
     * 获取测评记录的列表
     *
     * @param userId
     * @param pageNum
     * @return
     */
    @GET("testapp/getAbility.app")
    Observable<HttpResult<TestRecordListBean>> getTestRecordList(@Query("userId") String userId, @Query("pageNum") int pageNum);

    /**
     * 项目竞标记录
     */
    @GET("packageapp/comBidListInfo.app")
    Observable<HttpResult<BidRecordListBean>> getProjectBidListInfo(@Query("insertUser") String userId, @Query("bidStatus") String bidStatus, @Query("pageNum") int pageNum);

    /**
     * 人员竞标记录
     */
    @GET("packageapp/perBidListInfo.app")
    Observable<HttpResult<BidRecordListBean>> getPersonBidListInfo(@Query("insertUser") String userId, @Query("bidStatus") String bidStatus, @Query("pageNum") int pageNum);

    /**
     * 取消订单
     *
     * @param userId
     * @param orderNumber
     * @return
     */
    @GET("courseinterfaceapp/delOrder.app")
    Observable<HttpResult<String>> delOrder(@Query("userId") String userId, @Query("orderNumber") String orderNumber);

    /**
     * 获取个人用户的列表
     *
     * @param pageNum
     * @return
     */
    @POST("personalInfoapp/getOperateUser.app")
    @FormUrlEncoded
    Observable<HttpResult<UserManageListBean>> getOperateUser(@Field("userName") String username, @Field("user_status") String userStatus, @Field("pageNum") int pageNum);

    /**
     * 获取团体用户列表
     *
     * @param pageNum
     * @return
     */
    @POST("companyapp/getOperateCompany.app")
    @FormUrlEncoded
    Observable<HttpResult<UserManageListBean>> getOperateCompany(@Field("companyName") String companyName
            , @Field("userName") String username
            , @Field("userStatus") String userStatus
            , @Field("Province") String Province
            , @Field("City") String City
            , @Field("userType") String userType
            , @Field("pageNum") int pageNum);

    /**
     * 企业用户锁定
     *
     * @return
     */
    @POST("companyapp/locked.app")
    @FormUrlEncoded
    Observable<HttpResult<String>> companyLocked(@Field("userId") String userId, @Field("reviewer") String reviewer);

    /**
     * 用户审核驳回
     *
     * @param userId
     * @param reviewer
     * @return
     */
    @POST("companyapp/reviewFail.app")
    @FormUrlEncoded
    Observable<HttpResult<String>> companyReviewFail(@Field("userId") String userId, @Field("reviewer") String reviewer);

    /**
     * 用户审核通过/解锁
     *
     * @param userId
     * @param reviewer
     * @return
     */
    @POST("companyapp/reviewUnlock.app")
    @FormUrlEncoded
    Observable<HttpResult<String>> companyReviewUnlock(@Field("userId") String userId, @Field("reviewer") String reviewer, @Field("user_type") String userType);

    /**
     * 企业注销
     *
     * @param userId
     * @return
     */
    @GET("companyapp/delUser.app")
    Observable<HttpResult<String>> companyDelUser(@Query("userId") String userId, @Field("user_type") String userType);

    /**
     * 重置用户密码
     *
     * @return
     */
    @POST("personalInfoapp/resetPassword.app")
    @FormUrlEncoded
    Observable<HttpResult<String>> resetPassword(@Field("userId") String userId, @Field("userPassword") String userPassword);

    /**
     * 更改用户状态
     *
     * @param userId
     * @param userStatus
     * @return
     */
    @POST("personalInfoapp/updateStatus.app")
    @FormUrlEncoded
    Observable<HttpResult<String>> updatePersonStatus(@Field("userId") String userId, @Field("userStatus") String userStatus);

    /**
     * 发布推送消息
     *
     * @param type
     * @param title
     * @param content
     * @param user_id
     * @return
     */
    @POST("messageapp/publishMessage.app")
    @FormUrlEncoded
    Observable<HttpResult<String>> publishMessage(@Field("type") String type, @Field("title") String title, @Field("content") String content, @Field("user_id") String user_id);

    /**
     * 获取系统用户的列表
     *
     * @param pageNum
     * @param keyword
     * @return
     */
    @GET("sysUserapp/listUsers.app")
    Observable<HttpResult<SystemUserListBean>> getSystemUserList(@Query("pageNum") int pageNum, @Query("keyword") String keyword);

    /**
     * 获取添加系统用户时需要的参数
     *
     * @return
     */
    @GET("sysUserapp/goAddU.app")
    Observable<HttpResult<SystemUserFilterBean>> getSystemUserFilterList();

    /**
     * 新增用户保存
     *
     * @param params
     * @return
     */
    @POST("sysUserapp/saveU.app")
    @FormUrlEncoded
    Observable<HttpResult<String>> saveSystemUser(@FieldMap Map<String, String> params);

    /**
     * 修改用户信息保存
     *
     * @param params
     * @return
     */
    @POST("sysUserapp/editU.app")
    @FormUrlEncoded
    Observable<HttpResult<String>> updateSystemUser(@FieldMap Map<String, String> params);

    /**
     * 重置密码
     *
     * @param userId
     * @param userName
     * @param password
     * @return
     */
    @POST("sysUserapp/resetPass.app")
    @FormUrlEncoded
    Observable<HttpResult<String>> resetSystemUserPasswrod(@Field("USER_ID") String userId, @Field("USERNAME") String userName, @Field("PASSWORD") String password);

    /**
     * 删除用户
     *
     * @param userId
     * @return
     */
    @POST("sysUserapp/deleteU.app")
    @FormUrlEncoded
    Observable<HttpResult<String>> delSystemUser(@Field("USER_ID") String userId);

    /**
     * 登录时保存CID
     *
     * @param username
     * @param client_id
     * @return
     */
    @GET("messageapp/saveCID.app")
    Observable<HttpResult<String>> saveCID(@Query("username") String username, @Query("client_id") String client_id);

    /**
     * 用户查看消息列表
     *
     * @param user_type
     * @param pageNum
     * @return
     */
    @GET("messageapp/list.app")
    Observable<HttpResult<SystemMessageBean>> getMessageList(@Query("user_type") String user_type, @Query("pageNum") int pageNum);

}
