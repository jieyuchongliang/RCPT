package com.rcpt;

import android.support.annotation.StringDef;

/**
 * Created by lvzp on 2017/2/16.
 * 类描述：
 */

public class Constant {

    public static final String WX_APP_ID = "wx1a522fd90ee2cc1f";
    /**
     * 测试题类型，职业倾向测试题
     */
    public static final String TEST_QUESTION_TYPE_PROFESSION = "12";
    /**
     * 测试题类型，能力测试题
     */
    public static final String TEST_QUESTION_TYPE_ABILITY = "13";
    /**
     * 本地广播Flag，头像修改的广播
     */
    public static final String LOCAL_BROADCAST_USER_AVATAR_FLAG = "user_avatar";
    /**
     * 本地广播Flag，姓名或企业名称修改的广播
     */
    public static final String LOCAL_BROADCAST_USER_NAME_FLAG = "user_name";
    /**
     * 本地广播Flag，视频订单列表的更新
     */
    public static final String LOCAL_BROADCAST_UPDATE_ORDER_LIST = "update_order_list";


    @StringDef({CREATE_CV_ALL, CREATE_CV_EDUCATION, CREATE_CV_JOB_INTENT, CREATE_CV_WORK_HISTORY, CREATE_CV_PROJECT_EXPERIENCE})
    public @interface CreateCVType {
    }

    /**
     * 创建简历信息的类型--------创建全部
     * 当类型为全部时，默认的创建顺序为
     * 1.教育背景
     * 2.创建工作/实习经历
     * 3.项目经验
     * 4.求职意向
     */
    public static final String CREATE_CV_ALL = "all";
    /**
     * 创建简历信息的类型--------创建教育背景
     */
    public static final String CREATE_CV_EDUCATION = "education";
    /**
     * 创建简历信息的类型--------创建工作经历
     */
    public static final String CREATE_CV_WORK_HISTORY = "work_history";
    /**
     * 创建简历信息的类型--------创建项目经验
     */
    public static final String CREATE_CV_PROJECT_EXPERIENCE = "project_experience";
    /**
     * 创建简历信息的类型--------创建求职意向
     */
    public static final String CREATE_CV_JOB_INTENT = "job_intent";

    ////////////////////////////测试类型///////////////////////////////////

    /**
     * 测试的选择类型 --- 专业知识测试
     */
    public static final String TEST_TYPE_MAJOR = "major";
    /**
     * 测试的选择类型 --- 能力测试
     */
    public static final String TEST_TYPE_ABILITY = "ability";
    /**
     * 测试的选择类型 --- 职业倾向测试
     */
    public static final String TEST_TYPE_PROFESSION = "profession";


    /**
     * 获取用户的类型
     */
    public enum UserType {
        /**
         * 管理员用户
         */
        MANAGE("0"),
        /**
         * 个人
         */
        PERSON("1"),
        /**
         * 企业
         */
        ENTERPRISE("2"),
        /**
         * 其他身份（比如：培训机构或者是学校）
         */
        OTHER("3");

        private String value;

        UserType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum AppModel {
        /**
         * 首页
         */
        HOME(0),
        /**
         * 企业
         */
        ENTERPRISE(1),
        /**
         * 招聘
         */
        RECRUIT(2),
        /**
         * 个人
         */
        ME(3);

        private int value;

        AppModel(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }


    public static String getCreateCVInfoTitile(String crateCVType) {
        switch (crateCVType) {
            case CREATE_CV_ALL:
                return "教育背景";
            case CREATE_CV_EDUCATION://教育背景
                return "教育背景";
            case CREATE_CV_WORK_HISTORY://工作/实习经历
                return "工作/实习经历";
            case CREATE_CV_JOB_INTENT://求职意向
                return "求职意向";
            case CREATE_CV_PROJECT_EXPERIENCE://项目经验
                return "项目经验";
            default:
                return "教育背景";
        }
    }

    public static String getTestQuestionType(int questType) {
        switch (questType) {
            case 1:
                return "单选题";
            case 2:
                return "多选题";
            case 3:
                return "判断题";
            default:
                return "单选题";
        }
    }

    /**
     * 山东省code
     */
    public static String SHANDONG_PROVINCE_CODE = "16";

    /**
     * 济南市code
     */
    public static String JINAN_CITY_CODE = "170";

}
