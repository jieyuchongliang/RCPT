package com.rcpt.bean;

import java.util.List;

/**
 * Created by 860617003 on 2017/6/7.
 */

public class SystemUserListBean {


    /**
     * userList : [{"NUMBER":"131233123","PASSWORD":"14ed4622bc06915f92fb81bbd153b4e57906a044","REGION_NAME":"市辖区","IP":"","PHONE":"13411112222","USER_ID":"c97f04404eca495f8d16577ca7a125bd","USERNAME":"lv1233","ROLE_ID":"3","LAST_LOGIN":"2017-06-07 09:48:50","ROLE_NAME":"管理员","EMAIL":"sdjfjk@135.com","NAME":"立刻就赶快"},{"NUMBER":"1111","PASSWORD":"92678479b81e89dfd1f9b48e2fd5971654e0a335","REGION_NAME":"市中区","IP":"","PHONE":"18919919991","USER_ID":"737aa214b1014bc582c3ba4b1e718169","USERNAME":"lv12344","ROLE_ID":"e29a1d9d62b94aa9858c14f007937d31","LAST_LOGIN":"2017-06-07 09:46:28","ROLE_NAME":"测试管理员","EMAIL":"984289348@qq.com","NAME":"鹏哥"},{"NUMBER":"1115","PASSWORD":"5e69f381af9bb0a694e4515b98c97674f9f85282","REGION_NAME":"市辖区","IP":"0:0:0:0:0:0:0:1","PHONE":"18612344321","USER_ID":"daca2933708742c7a1b5b752fb416550","USERNAME":"2017060201","ROLE_ID":"bb2cc71390bf49bcb3b142ef325e9050","LAST_LOGIN":"2017-06-02 14:48:10","ROLE_NAME":"测试20170602","EMAIL":"108@qq.com","NAME":"xuehx"},{"NUMBER":"zqsswj01","PASSWORD":"924db295ebabb4af6d017d7fde7e2b561d7d0494","REGION_NAME":"章丘区","IP":"","PHONE":"16666666666","USER_ID":"ed144543ed4145a39574f0ec7cc07821","USERNAME":"zqsswj01","ROLE_ID":"6b9254be94f348899e8d423f1ea4dc72","LAST_LOGIN":"2017-05-31 09:21:03","ROLE_NAME":"申报管理员","EMAIL":"zqsswj01@163.com","NAME":"章丘市商务局01"},{"NUMBER":"SXQSWJ_02","PASSWORD":"b2478239cd7e68e8052398d8eb87d385bf962085","REGION_NAME":"市辖区","IP":"0:0:0:0:0:0:0:1","PHONE":"18660101234","USER_ID":"6add3fdb1c5b4013a2a823bb90bf83f8","USERNAME":"test","ROLE_ID":"3","LAST_LOGIN":"2017-05-31 09:20:31","ROLE_NAME":"管理员","EMAIL":"65464654@qq.com","NAME":"test"},{"NUMBER":"zqsswj02","PASSWORD":"914c01d42f3701325fa5b47f5fb85ec926264e65","REGION_NAME":"章丘区","IP":"","PHONE":"16666666666","USER_ID":"5935046e3f294ad39c19da4d6619d313","USERNAME":"zqsswj02","ROLE_ID":"6b9254be94f348899e8d423f1ea4dc72","LAST_LOGIN":"2017-05-05 13:20:31","ROLE_NAME":"申报管理员","EMAIL":"zqsswj02@163.com","NAME":"章丘市商务局02"},{"NUMBER":"003","PASSWORD":"f16b00e22f549af312752ddd917483234ea460a0","REGION_NAME":"市辖区","IP":"","PHONE":"16666666666","USER_ID":"42f82d4cd12f473dad8b5f0057264106","USERNAME":"jnsswj01","ROLE_ID":"6b9254be94f348899e8d423f1ea4dc72","LAST_LOGIN":"2017-05-05 09:47:17","ROLE_NAME":"申报管理员","EMAIL":"jnsswj01@163.com","NAME":"济南市商务局"},{"NUMBER":"123","PASSWORD":"26079ff703d741917d864492e2a959ecbd97c0ed","REGION_NAME":"市中区","IP":"","PHONE":"13125452542","USER_ID":"4bd9982bd0dd4d3c953c59c1ddb6fdd8","USERNAME":"cjuser01","ROLE_ID":"2","LAST_LOGIN":"2017-04-10 14:03:37","ROLE_NAME":"超级管理员","EMAIL":"2654@qq.com","NAME":"超级管理"},{"NUMBER":"008","PASSWORD":"f2038312ca33966b543e34d58960749900e1fdcd","REGION_NAME":"历下区","IP":"","PHONE":"16666666666","USER_ID":"63bfd3322f2b49648a5253cc3b7881a5","USERNAME":"gxqswj02","ROLE_ID":"6b9254be94f348899e8d423f1ea4dc72","LAST_LOGIN":"2017-04-07 09:53:04","ROLE_NAME":"申报管理员","EMAIL":"gxqswj02@163.com","NAME":"高新区商务局02"},{"NUMBER":"004","PASSWORD":"6e99f4242eb53fcf0ee6f20b7c5d42d8f1df9b81","REGION_NAME":"槐荫区","IP":"","PHONE":"16666666666","USER_ID":"213dcf1721a94b98a2d24d7070b6619b","USERNAME":"jnsswj02","ROLE_ID":"6b9254be94f348899e8d423f1ea4dc72","LAST_LOGIN":"2017-04-05 11:49:27","ROLE_NAME":"申报管理员","EMAIL":"jnsswj02@163.com","NAME":"济南市商务局02"},{"NUMBER":"007","PASSWORD":"6d8a135fcd224f4f206a275f90e40a3497ce4ff4","REGION_NAME":"高新区","IP":"","PHONE":"16666666666","USER_ID":"7637c2329bad479abe25f4edbe5bbb40","USERNAME":"gxqswj01","ROLE_ID":"6b9254be94f348899e8d423f1ea4dc72","LAST_LOGIN":"2017-03-31 16:40:57","ROLE_NAME":"申报管理员","EMAIL":"gxqswj01@163.com","NAME":"高新区商务局01"},{"NUMBER":"JYSWJ","PASSWORD":"58e317306ad5667e40aff07715104c265f8be08c","REGION_NAME":"济阳县","IP":"172.29.140.131","PHONE":"15610183728","USER_ID":"183ae95d1064452dbc9b57d2472901b7","USERNAME":"JYSWJ","ROLE_ID":"2","LAST_LOGIN":"2017-03-24 11:12:56","ROLE_NAME":"超级管理员","EMAIL":"JYSWJ@a.com","NAME":"JYSWJ"},{"NUMBER":"009","PASSWORD":"0422e640910de9186da70f1513a2cb1f203a35ef","REGION_NAME":"商河县","IP":"","PHONE":"16666666666","USER_ID":"5a37328fb3614c25aa29cb373135dcc1","USERNAME":"shxswj01","ROLE_ID":"6b9254be94f348899e8d423f1ea4dc72","LAST_LOGIN":"2017-03-24 10:01:53","ROLE_NAME":"申报管理员","EMAIL":"shxswj01@163.com","NAME":"商河县商务局01"},{"NUMBER":"010","PASSWORD":"1735d2a10d6fbfd9adf0289c7b55fa6af2c41da2","REGION_NAME":"商河县","IP":"","PHONE":"16666666666","USER_ID":"66c373bced934325b702ef46a63bb4a8","USERNAME":"shxswj02","ROLE_ID":"6b9254be94f348899e8d423f1ea4dc72","LAST_LOGIN":"2017-03-24 10:01:33","ROLE_NAME":"申报管理员","EMAIL":"shxswj02@163.com","NAME":"商河县商务局02"},{"NUMBER":"1003","PASSWORD":"a88e610c1feeda41ab45ca4b264da2f79216a15a","REGION_NAME":"市辖区","IP":"","PHONE":"15235265125","USER_ID":"2524211deb4547839b2966a3ecb73a41","USERNAME":"useradmin","ROLE_ID":"6b9254be94f348899e8d423f1ea4dc72","LAST_LOGIN":"2017-03-24 10:00:09","ROLE_NAME":"申报管理员","EMAIL":"26544@qq.com","NAME":"申报管理"},{"NUMBER":"1222","PASSWORD":"9cb69057692d9a8358ba130d2a196304a636cc10","REGION_NAME":"章丘区","IP":"","PHONE":"13896523698","USER_ID":"2305073f8ba84dbb8630dc9eb5f9797e","USERNAME":"1222","ROLE_ID":"6b9254be94f348899e8d423f1ea4dc72","LAST_LOGIN":"2017-03-24 09:56:48","ROLE_NAME":"申报管理员","EMAIL":"gxqswj022@163.com","NAME":"222"},{"NUMBER":"002","PASSWORD":"0015103e3d955b141b7afa925cdf54a78f8a40d2","REGION_NAME":"天桥区","IP":"","PHONE":"16666666666","USER_ID":"4f40759f10d2429fb72b5882ac853cff","USERNAME":"100","ROLE_ID":"6b9254be94f348899e8d423f1ea4dc72","LAST_LOGIN":"2017-03-24 09:55:57","ROLE_NAME":"申报管理员","EMAIL":"sdsswt02@163.com","NAME":"山东省商务厅02"},{"NUMBER":"001","PASSWORD":"4613beb4521e31239fc334e1c45b468a4ee8cbb5","REGION_NAME":"市辖区","IP":"","PHONE":"16666666666","USER_ID":"9c32c1afdbd049ed96fdf91d5a761c68","USERNAME":"sdsswt01","ROLE_ID":"6b9254be94f348899e8d423f1ea4dc72","LAST_LOGIN":"2017-03-24 09:55:25","ROLE_NAME":"申报管理员","EMAIL":"sdsswt01@163.com","NAME":"山东省商务厅01"},{"NUMBER":"1","PASSWORD":"e2fc21eedebfbc54ef57a4d1182c1535d4eb6b97","REGION_NAME":"历下区","IP":"","PHONE":"15205412541","USER_ID":"06501ee619064a42ad1eaa9cdcac28e0","USERNAME":"ceshi1","ROLE_ID":"e29a1d9d62b94aa9858c14f007937d31","LAST_LOGIN":"","ROLE_NAME":"测试管理员","EMAIL":"15205412541@163.com","NAME":"小王"},{"NUMBER":"admintest","PASSWORD":"4dd215594e91b48a795a7b2bddd50f49640e824a","REGION_NAME":"市辖区","IP":"","PHONE":"15615712121","USER_ID":"d2afd4851b9d475e9eecc88dc60d4966","USERNAME":"admintest","ROLE_ID":"2","LAST_LOGIN":"","ROLE_NAME":"超级管理员","EMAIL":"2115646541654@qq.com","NAME":"测试"}]
     * totalPage : 2
     * isNext : true
     */

    private String totalPage;
    private boolean isNext;
    private List<UserListBean> userList;

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    public boolean isIsNext() {
        return isNext;
    }

    public void setIsNext(boolean isNext) {
        this.isNext = isNext;
    }

    public List<UserListBean> getUserList() {
        return userList;
    }

    public void setUserList(List<UserListBean> userList) {
        this.userList = userList;
    }

    public static class UserListBean {
        /**
         * NUMBER : 131233123
         * PASSWORD : 14ed4622bc06915f92fb81bbd153b4e57906a044
         * REGION_NAME : 市辖区
         * IP :
         * PHONE : 13411112222
         * USER_ID : c97f04404eca495f8d16577ca7a125bd
         * USERNAME : lv1233
         * ROLE_ID : 3
         * LAST_LOGIN : 2017-06-07 09:48:50
         * ROLE_NAME : 管理员
         * EMAIL : sdjfjk@135.com
         * NAME : 立刻就赶快
         */

        private String NUMBER;
        private String PASSWORD;
        private String REGION_NAME;
        private String IP;
        private String PHONE;
        private String USER_ID;
        private String USERNAME;
        private String ROLE_ID;
        private String LAST_LOGIN;
        private String ROLE_NAME;
        private String EMAIL;
        private String NAME;
        private String BZ;
        private String AUTHORITIES;//商务局id

        public String getAUTHORITIES() {
            return AUTHORITIES;
        }

        public void setAUTHORITIES(String AUTHORITIES) {
            this.AUTHORITIES = AUTHORITIES;
        }

        public String getBZ() {
            return BZ;
        }

        public void setBZ(String BZ) {
            this.BZ = BZ;
        }

        public String getNUMBER() {
            return NUMBER;
        }

        public void setNUMBER(String NUMBER) {
            this.NUMBER = NUMBER;
        }

        public String getPASSWORD() {
            return PASSWORD;
        }

        public void setPASSWORD(String PASSWORD) {
            this.PASSWORD = PASSWORD;
        }

        public String getREGION_NAME() {
            return REGION_NAME + "商务局";
        }

        public void setREGION_NAME(String REGION_NAME) {
            this.REGION_NAME = REGION_NAME;
        }

        public String getIP() {
            return IP;
        }

        public void setIP(String IP) {
            this.IP = IP;
        }

        public String getPHONE() {
            return PHONE;
        }

        public void setPHONE(String PHONE) {
            this.PHONE = PHONE;
        }

        public String getUSER_ID() {
            return USER_ID;
        }

        public void setUSER_ID(String USER_ID) {
            this.USER_ID = USER_ID;
        }

        public String getUSERNAME() {
            return USERNAME;
        }

        public void setUSERNAME(String USERNAME) {
            this.USERNAME = USERNAME;
        }

        public String getROLE_ID() {
            return ROLE_ID;
        }

        public void setROLE_ID(String ROLE_ID) {
            this.ROLE_ID = ROLE_ID;
        }

        public String getLAST_LOGIN() {
            return LAST_LOGIN;
        }

        public void setLAST_LOGIN(String LAST_LOGIN) {
            this.LAST_LOGIN = LAST_LOGIN;
        }

        public String getROLE_NAME() {
            return ROLE_NAME;
        }

        public void setROLE_NAME(String ROLE_NAME) {
            this.ROLE_NAME = ROLE_NAME;
        }

        public String getEMAIL() {
            return EMAIL;
        }

        public void setEMAIL(String EMAIL) {
            this.EMAIL = EMAIL;
        }

        public String getNAME() {
            return NAME;
        }

        public void setNAME(String NAME) {
            this.NAME = NAME;
        }
    }
}
