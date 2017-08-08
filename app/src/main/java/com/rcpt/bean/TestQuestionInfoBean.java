package com.rcpt.bean;

import java.util.List;

/**
 * Created by lvzp on 2017/4/6.
 * 类描述：
 */

public class TestQuestionInfoBean {


    /**
     * duration : 66
     * name : To ML
     * id : 246
     * content : [{"questionTypeId":1,"appChoiceList":[{"value":"123","key":"A"},{"value":"123","key":"B"},{"value":"123","key":"C"},{"value":"123","key":"D"},{"value":"123","key":"E"},{"value":"123","key":"F"}],"appChoiceImgList":[{"value":"uploadFiles/question/57d14f65dc014ecba244b8c2cd46de0e.jpg","key":"A"},{"value":"uploadFiles/question/4d18b40c68f14c16b6337aab19a54f5d.jpg","key":"B"},{"value":"uploadFiles/question/72aa06e96ce14812841e46bbb56b1f14.jpg","key":"C"},{"value":"uploadFiles/question/37cb16f2c1454bb4a9c0898b0b3fabba.jpg","key":"D"},{"value":"uploadFiles/question/92e012e19e024ae98d9e5496705fee3e.jpg","key":"E"},{"value":"uploadFiles/question/84017c816c2e47b58136513e596b6f19.jpg","key":"F"}],"title":"1234","titleImg":"uploadFiles/question/8a3f93eb9dfa42d4a3eebc5268026050.jpg","questionId":329,"questionPoint":2},{"questionTypeId":1,"appChoiceList":[{"value":"测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试","key":"A"},{"value":"测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试","key":"B"},{"value":"测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试","key":"C"},{"value":"测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试","key":"D"},{"value":"测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试","key":"E"},{"value":"测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试","key":"F"}],"appChoiceImgList":[{"value":"uploadFiles/question/599e605845f84145826ea970f25c355b.jpg","key":"A"},{"value":"uploadFiles/question/d85c4aaf8a7042d8aa31ba6c45bcf4f0.jpg","key":"B"},{"value":"uploadFiles/question/732dd3d0449641bea998af79f51dcd95.jpg","key":"C"},{"value":"uploadFiles/question/24ca49d9627d4044a0bc1e40aaf53749.jpg","key":"D"},{"value":"uploadFiles/question/f3c2100c15de43ccbcf9ea89a5797ba2.jpg","key":"E"},{"value":"uploadFiles/question/6475115437ae494f976727e9b4321eaf.jpg","key":"F"}],"title":"测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试","titleImg":"uploadFiles/question/411de063ec7c41caafc929d2ed8d5e93.jpg","questionId":335,"questionPoint":2}]
     */

    private int duration;//花费时间
    private String name;//名称
    private String id;//试卷id
    private List<ContentBean> content;//试题内容

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * questionTypeId : 1
         * appChoiceList : [{"value":"123","key":"A"},{"value":"123","key":"B"},{"value":"123","key":"C"},{"value":"123","key":"D"},{"value":"123","key":"E"},{"value":"123","key":"F"}]
         * appChoiceImgList : [{"value":"uploadFiles/question/57d14f65dc014ecba244b8c2cd46de0e.jpg","key":"A"},{"value":"uploadFiles/question/4d18b40c68f14c16b6337aab19a54f5d.jpg","key":"B"},{"value":"uploadFiles/question/72aa06e96ce14812841e46bbb56b1f14.jpg","key":"C"},{"value":"uploadFiles/question/37cb16f2c1454bb4a9c0898b0b3fabba.jpg","key":"D"},{"value":"uploadFiles/question/92e012e19e024ae98d9e5496705fee3e.jpg","key":"E"},{"value":"uploadFiles/question/84017c816c2e47b58136513e596b6f19.jpg","key":"F"}]
         * title : 1234
         * titleImg : uploadFiles/question/8a3f93eb9dfa42d4a3eebc5268026050.jpg
         * questionId : 329
         * questionPoint : 2
         */

        private int questionTypeId;//试卷类型id（1.单选，2.多选，3.判断）
        private String title;//题目名称
        private String titleImg;//题目图片
        private String questionId;//题目id
        private float questionPoint;//分数
        private List<AppChoiceListBean> appChoiceList;//答案集合
        private List<AppChoiceListBean> appChoiceImgList;//图片集合

        public int getQuestionTypeId() {
            return questionTypeId;
        }

        public void setQuestionTypeId(int questionTypeId) {
            this.questionTypeId = questionTypeId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitleImg() {
            return titleImg;
        }

        public void setTitleImg(String titleImg) {
            this.titleImg = titleImg;
        }

        public String getQuestionId() {
            return questionId;
        }

        public void setQuestionId(String questionId) {
            this.questionId = questionId;
        }

        public float getQuestionPoint() {
            return questionPoint;
        }

        public void setQuestionPoint(int questionPoint) {
            this.questionPoint = questionPoint;
        }

        public List<AppChoiceListBean> getAppChoiceList() {
            return appChoiceList;
        }

        public void setAppChoiceList(List<AppChoiceListBean> appChoiceList) {
            this.appChoiceList = appChoiceList;
        }

        public List<AppChoiceListBean> getAppChoiceImgList() {
            return appChoiceImgList;
        }

        public void setAppChoiceImgList(List<AppChoiceListBean> appChoiceImgList) {
            this.appChoiceImgList = appChoiceImgList;
        }

        public static class AppChoiceListBean {
            /**
             * value : 123
             * key : A
             */

            private String value;
            private String key;
            private boolean isSelect;

            public boolean isSelect() {
                return isSelect;
            }

            public void setSelect(boolean select) {
                isSelect = select;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }
        }
    }
}
