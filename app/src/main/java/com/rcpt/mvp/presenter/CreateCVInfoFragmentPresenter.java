package com.rcpt.mvp.presenter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.rcpt.Constant;
import com.rcpt.DateSelectDialog;
import com.rcpt.LoginHelper;
import com.rcpt.R;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.AttrSelectBean;
import com.rcpt.bean.CVEducationListBean;
import com.rcpt.bean.CVJobIntentInfoBean;
import com.rcpt.bean.CVProjectExListBean;
import com.rcpt.bean.CVWorkExListBean;
import com.rcpt.bean.ChooseIndustryBean;
import com.rcpt.bean.InputMenuBean;
import com.rcpt.bean.JobCategoryBean;
import com.rcpt.http.api.ApiClient;
import com.rcpt.http.bean.RequestCVEducationBean;
import com.rcpt.http.bean.RequestCVJobIntentBean;
import com.rcpt.http.bean.RequestCVProjectBean;
import com.rcpt.http.bean.RequestCVWorkExBean;
import com.rcpt.mvp.contract.CreateCVInfoFragmentContract;
import com.rcpt.mvp.module.CreateCVInfoMenuModule;
import com.rcpt.utils.DateUtils;
import com.rcpt.utils.RECheckUtils;
import com.rcpt.utils.StringUtils;
import com.rcpt.widget.InputDataPopWindow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lvzp on 2017/3/9.
 * 类描述：CreateCVInfoFragmentPresenter
 */

public class CreateCVInfoFragmentPresenter extends BasePresenter<CreateCVInfoFragmentContract.View>
        implements CreateCVInfoFragmentContract.Presenter, DateSelectDialog.OnClickListener,
        InputDataPopWindow.OnClickSaveCallback {

    private CreateCVInfoMenuModule mModule;

    private CVEducationListBean mCVEducationBean;
    private CVProjectExListBean mCVProjectExBean;
    private CVWorkExListBean mCVWorkExBean;

    private CVJobIntentInfoBean mCVJobIntentInfoBean;


    @Override
    public void attach(CreateCVInfoFragmentContract.View view) {
        super.attach(view);
        getView().initRecyclerView();
        mModule = new CreateCVInfoMenuModule();

        mModule.attachMenuList(getView().getMenuList());
        createItemClickListener(getView().getRecyclerView());

    }

    @Override
    public void initMenu() {
        getView().bindListData(mModule.getMenuBeanList());
    }

    /**
     * 加载简历信息的页面
     */
    @Override
    public void loadListData() {
        switch (getView().getCreateType()) {
            case Constant.CREATE_CV_EDUCATION://加载教育背景的内容
                mCVEducationBean = (CVEducationListBean) getView().getEditBean();
                ApiClient.menuDataToList(mCVEducationBean, mModule.getMenuBeanList());
                getView().bindListData(mModule.getMenuBeanList());
                break;
            case Constant.CREATE_CV_JOB_INTENT://加载求职意向的内容
                mModule.requestCVJboIntentInfo(getView().getContext(), LoginHelper.getInstance().getUserToken(), getView().getCVId(), new OnDataGetCallback<CVJobIntentInfoBean>() {

                    @Override
                    public void onSuccessResult(CVJobIntentInfoBean result) {
                        mCVJobIntentInfoBean = result;
                        if (mCVJobIntentInfoBean != null) {
                            if (!TextUtils.isEmpty(mCVJobIntentInfoBean.getProvince()) && !TextUtils.isEmpty(mCVJobIntentInfoBean.getCity())) {
                                getView().updateAddressId(mCVJobIntentInfoBean.getProvinceCode(), mCVJobIntentInfoBean.getCityCode());
                                result.setAddress(mCVJobIntentInfoBean.getProvince() + "- " + mCVJobIntentInfoBean.getCity());
                            }
                            initIndustryList(mCVJobIntentInfoBean.getExpectedIndustry(), mCVJobIntentInfoBean.getExpectedIndustryCode());
                            ApiClient.menuDataToList(mCVJobIntentInfoBean, mModule.getMenuBeanList());
                        }
                        getView().bindListData(mModule.getMenuBeanList());
                    }
                });
                break;
            case Constant.CREATE_CV_PROJECT_EXPERIENCE://加载项目经验的内容
                mCVProjectExBean = (CVProjectExListBean) getView().getEditBean();
                ApiClient.menuDataToList(mCVProjectExBean, mModule.getMenuBeanList());
                getView().bindListData(mModule.getMenuBeanList());
                break;
            case Constant.CREATE_CV_WORK_HISTORY://加载工作经历的内容
                mCVWorkExBean = (CVWorkExListBean) getView().getEditBean();
                initIndustryList(mCVWorkExBean.getComIndId(), mCVWorkExBean.getComIndCode());
                ApiClient.menuDataToList(mCVWorkExBean, mModule.getMenuBeanList());
                getView().bindListData(mModule.getMenuBeanList());
                break;
        }
    }

    /**
     * 上传更新简历信息的方法
     */
    @Override
    public void uploadCrateCVInfo() {
        String userId = LoginHelper.getInstance().getUserToken();
        switch (getView().getCreateType()) {
            case Constant.CREATE_CV_EDUCATION://教育背景
                RequestCVEducationBean bean = new RequestCVEducationBean();
                bean.setCvId(getView().getCVId());
                bean.setUserId(userId);
                if (getView().isEdit()) {
                    bean.setInfoId(mCVEducationBean.getEduInfoId());
                    bean.setEducation(mCVEducationBean.getEducationCode());
                    bean.setOsStudy(mCVEducationBean.getOsStudyCode());
                }
                //判断填写的数据中是否有空数据
                List<String> educationEmptyList = ApiClient.checkMenuListIsEmpty(mModule.getMenuBeanList());
                if (!educationEmptyList.isEmpty()) {
                    getView().showToast("请将" + educationEmptyList.get(0) + "补充完整");
                    return;
                }
                ApiClient.menuListToData(mModule.getMenuBeanList(), bean);
                if (getView().isEdit()) {
                    if (StringUtils.countStr(bean.getStartT(), "-", true) < 2) {
                        bean.setStartT(bean.getStartT() + "-01");
                    }
                    if (StringUtils.countStr(bean.getEndT(), "-", true) < 2) {
                        bean.setEndT(bean.getEndT() + "-01");
                    }
                }
                if (!RECheckUtils.checkTwoTime(bean.getStartT(), bean.getEndT())) {
                    getView().showToast("入学时间不能晚于毕业时间");
                    return;
                }
                Map<String, String> educationParams = ApiClient.createBodyMap(bean);
                if (getView().isEdit()) {
                    mModule.editEducationInfo(getView().getContext(), educationParams, new OnDataGetCallback<String>() {
                        @Override
                        public void onSuccessResult(String s) {
                            getView().showToast(s);
                            getView().uploadInfoSuccess();
                        }
                    });
                } else
                    mModule.uploadEducationInfo(getView().getContext(), educationParams, new OnDataGetCallback<String>() {
                        @Override
                        public void onSuccessResult(String s) {
                            getView().showToast(s);
                            getView().uploadInfoSuccess();
                        }
                    });
                break;
            case Constant.CREATE_CV_JOB_INTENT://求职意向
                RequestCVJobIntentBean intentInfoBean = new RequestCVJobIntentBean();
                intentInfoBean.setUserId(userId);
                intentInfoBean.setCvId(getView().getCVId());
                if (mCVJobIntentInfoBean != null) {
                    intentInfoBean.setExpectedIndustry(mCVJobIntentInfoBean.getExpectedIndustryCode());
                    intentInfoBean.setExpectedPosition(mCVJobIntentInfoBean.getExpectedPositionCode());
                    intentInfoBean.setWorkType(mCVJobIntentInfoBean.getWorkTypeCode());
                    intentInfoBean.setState(mCVJobIntentInfoBean.getStateCode());
                    intentInfoBean.setHopeSalaryId(mCVJobIntentInfoBean.getHopeSalaryCode());
                }

                intentInfoBean.setProvince(getView().getProvinceId());
                intentInfoBean.setCity(getView().getCityId());

                if (getView().isCreateNewCV()) {
                    intentInfoBean.setCvName(getView().getCVName());
                }

                //判断填写的数据中是否有空数据
                List<String> intentEmptyList = ApiClient.checkMenuListIsEmpty(mModule.getMenuBeanList());
                if (!intentEmptyList.isEmpty()) {
                    getView().showToast("请将" + intentEmptyList.get(0) + "补充完整");
                    return;
                }
                //这个方法，会将现在当前选择的内容替换掉原有的数据
                ApiClient.menuListToData(mModule.getMenuBeanList(), intentInfoBean);
                Map<String, String> intentParams = ApiClient.createBodyMap(intentInfoBean);
                //判断如果需要创建新的简历时，调用的方法
                if (getView().isCreateNewCV()) {
                    mModule.requestCreateNewCV(getView().getContext(), intentParams, new OnDataGetCallback<String>() {
                        @Override
                        public void onSuccessResult(String data) {
                            getView().showToast(data);
                            getView().uploadInfoSuccess();
                        }
                    });
                } else
                    mModule.updateJobIntentInfo(getView().getContext(), intentParams, new OnDataGetCallback<String>() {
                        @Override
                        public void onSuccessResult(String result) {
                            getView().showToast(result);
                            getView().uploadInfoSuccess();
                        }
                    });
                break;
            case Constant.CREATE_CV_PROJECT_EXPERIENCE://项目经验
                RequestCVProjectBean projectBean = new RequestCVProjectBean();
                projectBean.setCvId(getView().getCVId());
                projectBean.setUserId(userId);

                //判断填写的数据中是否有空数据
                List<String> projectEmptyList = ApiClient.checkMenuListIsEmpty(mModule.getMenuBeanList());
                if (!projectEmptyList.isEmpty()) {
                    getView().showToast("请将" + projectEmptyList.get(0) + "补充完整");
                    return;
                }

                ApiClient.menuListToData(mModule.getMenuBeanList(), projectBean);
                if (getView().isEdit()) {
                    projectBean.setInfoId(mCVProjectExBean.getProjectInfoId());
                }
                if (getView().isEdit()) {
                    if (StringUtils.countStr(projectBean.getStartTime(), "-", true) < 2) {
                        projectBean.setStartTime(projectBean.getStartTime() + "-01");
                    }
                    if (StringUtils.countStr(projectBean.getEndTime(), "-", true) < 2) {
                        projectBean.setEndTime(projectBean.getEndTime() + "-01");
                    }
                }
                if (!RECheckUtils.checkTwoTime(projectBean.getStartTime(), projectBean.getEndTime())) {
                    getView().showToast("开始时间不能晚于结束时间");
                    return;
                }
                Map<String, String> projectParams = ApiClient.createBodyMap(projectBean);
                if (getView().isEdit()) {
                    mModule.editProjectInfo(getView().getContext(), projectParams, new OnDataGetCallback<String>() {
                        @Override
                        public void onSuccessResult(String s) {
                            getView().showToast(s);
                            getView().uploadInfoSuccess();
                        }
                    });
                } else
                    mModule.uploadProjectInfo(getView().getContext(), projectParams, new OnDataGetCallback<String>() {
                        @Override
                        public void onSuccessResult(String s) {
                            getView().showToast(s);
                            getView().uploadInfoSuccess();
                        }
                    });
                break;
            case Constant.CREATE_CV_WORK_HISTORY://工作经历
                RequestCVWorkExBean workExBean = new RequestCVWorkExBean();
                workExBean.setCvId(getView().getCVId());
                workExBean.setUserId(userId);
                workExBean.setUnderling("0");       //下属人数默认0
                if (getView().isEdit()) {
                    workExBean.setInfoId(mCVWorkExBean.getWorkInfoId());
                    workExBean.setComIndId(mCVWorkExBean.getComIndCode());
                    workExBean.setComNatureId(mCVWorkExBean.getComNatureCode());
                }
                //判断填写的数据中是否有空数据
                List<String> workEmptyList = ApiClient.checkMenuListIsEmpty(mModule.getMenuBeanList());
                if (!workEmptyList.isEmpty()) {
                    getView().showToast("请将" + workEmptyList.get(0) + "补充完整");
                    return;
                }
                ApiClient.menuListToData(mModule.getMenuBeanList(), workExBean);
                if (getView().isEdit()) {
                    if (StringUtils.countStr(workExBean.getWorkBeginDate(), "-", true) < 2) {
                        workExBean.setWorkBeginDate(workExBean.getWorkBeginDate() + "-01");
                    }
                    if (StringUtils.countStr(workExBean.getWorkEndDate(), "-", true) < 2) {
                        workExBean.setWorkEndDate(workExBean.getWorkEndDate() + "-01");
                    }
                }
                if (!RECheckUtils.checkTwoTime(workExBean.getWorkBeginDate(), workExBean.getWorkEndDate())) {
                    getView().showToast("开始时间不能晚于结束时间");
                    return;
                }
                Map<String, String> workExParams = ApiClient.createBodyMap(workExBean);
                if (getView().isEdit()) {
                    mModule.editWorExInfo(getView().getContext(), workExParams, new OnDataGetCallback<String>() {
                        @Override
                        public void onSuccessResult(String s) {
                            getView().showToast(s);
                            getView().uploadInfoSuccess();
                        }
                    });
                } else
                    mModule.uploadWorExInfo(getView().getContext(), workExParams, new OnDataGetCallback<String>() {
                        @Override
                        public void onSuccessResult(String s) {
                            getView().showToast(s);
                            getView().uploadInfoSuccess();
                        }
                    });
                break;
        }
    }

    /**
     * 条目点击事件
     *
     * @param vh
     * @param position
     */
    @Override
    public void onItemClick(RecyclerView.ViewHolder vh, int position) {
        final InputMenuBean bean = mModule.getItemBeanForPosition(position);
        String inputValue = bean.getMenuValue();
        String timeValue = bean.getUpdateValue() == null ? bean.getMenuValue() : bean.getUpdateValue();
        switch (getView().getCreateType()) {
            case Constant.CREATE_CV_EDUCATION://教育背景
                switch (bean.getId()) {
                    case R.id.cv_menu_item_education_diploma:
                        getView().setListSelectPopTitle("学历列表");
                        getView().showListSelectPop();
                        mModule.requestEducationList(getView().getContext(), new OnDataGetCallback<List<AttrSelectBean>>() {
                            @Override
                            public void onSuccessResult(List<AttrSelectBean> educationBeen) {
                                List<String> list = new ArrayList<>();
                                for (AttrSelectBean educationBean : educationBeen) {
                                    list.add(educationBean.getValue());
                                }
                                getView().bindAttrListData(list, bean.getMenuValue());
                            }
                        });
                        break;
                    case R.id.cv_menu_item_education_enroll_time:
                    case R.id.cv_menu_item_education_graduation_time:
                        getView().showDateSelectDialog(DateUtils.stringToDate(timeValue));
                        break;
                    case R.id.cv_menu_item_education_major_name:
                        getView().setSingleInputPopTitle("专业名称");
                        getView().setInputDataMax(50, true);
                        getView().showInputSingleDataPop(inputValue);
                        break;
                    case R.id.cv_menu_item_education_school_name:
                        getView().setSingleInputPopTitle("学校名称");
                        getView().setInputDataMax(50, true);
                        getView().showInputSingleDataPop(inputValue);
                        break;
                    case R.id.cv_menu_item_education_overseas_ex:
                        getView().showSingleSelectDialog(bean.getMenuValue());
                        break;
                }
                break;
            case Constant.CREATE_CV_JOB_INTENT://求职意向
                switch (bean.getId()) {
                    case R.id.cv_menu_item_trade_mode://行业列表
                        getView().startGoChooseIndustry();
                        break;
                    case R.id.cv_menu_item_job_address://期望地址
                        getView().startGoSelectAddress();
                        break;
                    case R.id.cv_menu_item_job_status://求职状态
                        getView().setListSelectPopTitle("求职状态");
                        getView().showListSelectPop();
                        mModule.requestWorkStatusList(getView().getContext(), new OnDataGetCallback<List<AttrSelectBean>>() {
                            @Override
                            public void onSuccessResult(List<AttrSelectBean> s) {
                                List<String> list = new ArrayList<>();
                                for (AttrSelectBean attr : s) {
                                    list.add(attr.getValue());
                                }
                                getView().bindAttrListData(list, bean.getMenuValue());
                            }
                        });
                        break;
                    case R.id.cv_menu_item_job_type://工作性质
                        getView().setListSelectPopTitle("工作性质");
                        getView().showListSelectPop();
                        mModule.requestWorkTypeList(getView().getContext(), new OnDataGetCallback<List<AttrSelectBean>>() {
                            @Override
                            public void onSuccessResult(List<AttrSelectBean> s) {
                                List<String> list = new ArrayList<>();
                                for (AttrSelectBean attr : s) {
                                    list.add(attr.getValue());
                                }
                                getView().bindAttrListData(list, bean.getMenuValue());
                            }
                        });
                        break;
                    case R.id.cv_menu_item_job_mode://职位类别
                        getView().setJobCategoryListPopTitle("职位类别");
                        getView().showJobCategoryListPop();
                        mModule.requestJobCategoryList(getView().getContext(), new OnDataGetCallback<List<JobCategoryBean>>() {
                            @Override
                            public void onSuccessResult(List<JobCategoryBean> list) {
                                getView().bindJobCategoryListPopData(list, bean.getMenuValue());
                            }
                        });
                        break;
                    case R.id.cv_menu_item_job_money://期望薪资
                        getView().setListSelectPopTitle("期望薪资");
                        getView().showListSelectPop();
                        mModule.requestSalaryList(getView().getContext(), new OnDataGetCallback<List<AttrSelectBean>>() {
                            @Override
                            public void onSuccessResult(List<AttrSelectBean> s) {
                                List<String> list = new ArrayList<>();
                                for (AttrSelectBean attr : s) {
                                    list.add(attr.getValue());
                                }
                                getView().bindAttrListData(list, bean.getMenuValue());
                            }
                        });
                        break;

                }
                break;
            case Constant.CREATE_CV_PROJECT_EXPERIENCE://项目经验
                switch (bean.getId()) {
                    case R.id.cv_menu_item_project_time_start:
                    case R.id.cv_menu_item_project_time_end:
                        getView().showDateSelectDialog(DateUtils.stringToDate(timeValue));
                        break;
                    case R.id.cv_menu_item_project_name:
                        getView().setSingleInputPopTitle("项目名称");
                        getView().setInputDataMax(50, true);
                        getView().showInputSingleDataPop(inputValue);
                        break;
                    case R.id.cv_menu_item_project_tools:
                        getView().setSingleInputPopTitle("开发工具");
                        getView().setInputDataMax(50, true);
                        getView().showInputSingleDataPop(inputValue);
                        break;
                    case R.id.cv_menu_item_project_duty:
                        getView().setInputPopTitle("项目职责");
                        getView().setInputDataMax(255, false);
                        getView().showInputDataPop(inputValue);
                        break;
                    case R.id.cv_menu_item_project_describe:
                        getView().setInputPopTitle("项目描述");
                        getView().setInputDataMax(210, false);
                        getView().showInputDataPop(inputValue);
                        break;
                    case R.id.cv_menu_item_project_involve:
                        getView().setInputPopTitle("涉及技术");
                        getView().setInputDataMax(50, false);
                        getView().showInputDataPop(inputValue);
                        break;
                }
                break;
            case Constant.CREATE_CV_WORK_HISTORY://工作经历
                switch (bean.getId()) {
                    case R.id.cv_menu_item_work_type:
                        getView().setListSelectPopTitle("企业性质");
                        getView().showListSelectPop();
                        mModule.requestCompanyTypeList(getView().getContext(), new OnDataGetCallback<List<AttrSelectBean>>() {
                            @Override
                            public void onSuccessResult(List<AttrSelectBean> educationBeen) {
                                List<String> list = new ArrayList<>();
                                for (AttrSelectBean educationBean : educationBeen) {
                                    list.add(educationBean.getValue());
                                }
                                getView().bindAttrListData(list, bean.getMenuValue());
                            }
                        });
                        break;
                    case R.id.cv_menu_item_work_time_start:
                    case R.id.cv_menu_item_work_time_end:
                        getView().showDateSelectDialog(DateUtils.stringToDate(timeValue));
                        break;
                    case R.id.cv_menu_item_work_trade_mode://所在行业
                        getView().startGoChooseIndustrySingle();
                        break;
                    case R.id.cv_menu_item_work_job_name://岗位名称
                        getView().setSingleInputPopTitle("岗位名称");
                        getView().setInputDataMax(50, true);
                        getView().showInputSingleDataPop(inputValue);
                        break;
                    case R.id.cv_menu_item_work_department://所在部门
                        getView().setSingleInputPopTitle("所在部门");
                        getView().setInputDataMax(50, true);
                        getView().showInputSingleDataPop(inputValue);
                        break;
                    case R.id.cv_menu_item_work_name:
                        getView().setInputDataMax(50, true);
                        getView().showInputSingleDataPop(inputValue);
                        getView().setSingleInputPopTitle("公司名称");
                        break;
                    case R.id.cv_menu_item_work_describe://工作描述
                        getView().setInputPopTitle("工作描述");
                        getView().setInputDataMax(210, false);
                        getView().showInputDataPop(inputValue);
                        break;
                }
                break;
        }
    }

    /**
     * 更新列表中Item的值，只是简单的更新值
     * @param value
     */
    @Override
    public void updateListItemValue(String value) {
        mModule.getCurrentClickMenuBean().setMenuValue(value);
        getView().updateList(mModule.getCurrentClickPosition());
    }

    /**
     * 更新列表Item显示的值，并且将附加的内容，更新到需要上传到服务器的内容上
     * @param value
     * @param add
     */
    @Override
    public void updateListItemValue(String value, String add) {
        updateListItemValue(value);
        mModule.getCurrentClickMenuBean().setUpdateValue(add);
    }

    /**
     * 更新列表Item显示的值，此方法是在需要在列表中选值的时候，调用的方法
     * @param value
     * @param position
     */
    @Override
    public void updateListItemValue(String value, int position) {
        updateListItemValue(value);
        mModule.getCurrentClickMenuBean().setUpdateValue(mModule.getAttrSelectList().get(position).getDistinguishId());
    }

    /**
     * 职位类别选择保存的回调方法
     */
    @Override
    public void jobIntentSelectClickSave() {
        List<JobCategoryBean.ChildBean> childBeanList = getView().getJobIntentSelectList();
        if (childBeanList != null && !childBeanList.isEmpty()) {
            String industryTxt = "";
            String industryId = "";
            for (JobCategoryBean.ChildBean childBean : childBeanList) {
                industryTxt += childBean.getName() + "、";
                industryId += childBean.getId() + ",";
            }
            industryTxt = industryTxt.substring(0, industryTxt.length() - 1);
            industryId = industryId.substring(0, industryId.length() - 1);
            updateListItemValue(industryTxt, industryId);
        }
    }

    private void initIndustryList(String industry, String industryId) {
        if (industry != null && industryId != null) {
            String[] industrys = industry.split(",");
            String[] industryIds = industryId.split(",");
            int count = Math.min(industrys.length, industryIds.length);
            ChooseIndustryBean[] beens = new ChooseIndustryBean[count];
            for (int i = 0; i < count; i++) {
                ChooseIndustryBean bean = new ChooseIndustryBean();
                bean.setId(industryIds[i].replace(" ", ""));
                bean.setValue(industrys[i].replace(" ", ""));
                bean.setSelect(true);
                beens[i] = bean;
            }
            getView().initIndustrySelectList(beens);
        }

    }

    @Override
    public void onClick(String year, String money, boolean isNow) {
        String value;
        String updateValue = year + "-" + money + "-" + "1";
        if (isNow) {
            value = "至今";
        } else {
            value = year + "-" + money;
        }
        updateListItemValue(value);
        mModule.getCurrentClickMenuBean().setUpdateValue(updateValue);
    }

    @Override
    public void onSaveCallback(String content) {
        updateListItemValue(content);
    }


}
