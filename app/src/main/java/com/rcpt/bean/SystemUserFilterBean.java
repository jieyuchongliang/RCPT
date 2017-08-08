package com.rcpt.bean;

import java.util.List;

/**
 * 添加或者是修改系统用户已时修改内容
 */

public class SystemUserFilterBean {


    private List<AreaListBean> areaList;
    private List<RoleListBean> roleList;

    public List<AreaListBean> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<AreaListBean> areaList) {
        this.areaList = areaList;
    }

    public List<RoleListBean> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<RoleListBean> roleList) {
        this.roleList = roleList;
    }

    public static class AreaListBean {
        /**
         * region_order : 0
         * parent_id : 170
         * region_shortname_en : 2
         * region_id : 1725
         * region_name : 市辖区
         * del_flg : 0
         * region_level : 0
         * region_name_en : Shixiaqu
         * region_code : 370101
         */

        private int region_order;
        private int parent_id;
        private String region_shortname_en;
        private String region_id;
        private String region_name;
        private String del_flg;
        private int region_level;
        private String region_name_en;
        private String region_code;

        public int getRegion_order() {
            return region_order;
        }

        public void setRegion_order(int region_order) {
            this.region_order = region_order;
        }

        public int getParent_id() {
            return parent_id;
        }

        public void setParent_id(int parent_id) {
            this.parent_id = parent_id;
        }

        public String getRegion_shortname_en() {
            return region_shortname_en;
        }

        public void setRegion_shortname_en(String region_shortname_en) {
            this.region_shortname_en = region_shortname_en;
        }

        public String getRegion_id() {
            return region_id;
        }

        public void setRegion_id(String region_id) {
            this.region_id = region_id;
        }

        public String getRegion_name() {
            return region_name;
        }

        public void setRegion_name(String region_name) {
            this.region_name = region_name;
        }

        public String getDel_flg() {
            return del_flg;
        }

        public void setDel_flg(String del_flg) {
            this.del_flg = del_flg;
        }

        public int getRegion_level() {
            return region_level;
        }

        public void setRegion_level(int region_level) {
            this.region_level = region_level;
        }

        public String getRegion_name_en() {
            return region_name_en;
        }

        public void setRegion_name_en(String region_name_en) {
            this.region_name_en = region_name_en;
        }

        public String getRegion_code() {
            return region_code;
        }

        public void setRegion_code(String region_code) {
            this.region_code = region_code;
        }
    }

    public static class RoleListBean {
        /**
         * rights : 32984542474214
         * qx_ID : 2
         * role_ID : 2
         * role_NAME : 超级管理员
         * parent_ID : 1
         * add_QX : 50331878
         * del_QX : 50331698
         * edit_QX : 50331682
         * cha_QX : 54
         */

        private String rights;
        private String qx_ID;
        private String role_ID;
        private String role_NAME;
        private String parent_ID;
        private String add_QX;
        private String del_QX;
        private String edit_QX;
        private String cha_QX;

        public String getRights() {
            return rights;
        }

        public void setRights(String rights) {
            this.rights = rights;
        }

        public String getQx_ID() {
            return qx_ID;
        }

        public void setQx_ID(String qx_ID) {
            this.qx_ID = qx_ID;
        }

        public String getRole_ID() {
            return role_ID;
        }

        public void setRole_ID(String role_ID) {
            this.role_ID = role_ID;
        }

        public String getRole_NAME() {
            return role_NAME;
        }

        public void setRole_NAME(String role_NAME) {
            this.role_NAME = role_NAME;
        }

        public String getParent_ID() {
            return parent_ID;
        }

        public void setParent_ID(String parent_ID) {
            this.parent_ID = parent_ID;
        }

        public String getAdd_QX() {
            return add_QX;
        }

        public void setAdd_QX(String add_QX) {
            this.add_QX = add_QX;
        }

        public String getDel_QX() {
            return del_QX;
        }

        public void setDel_QX(String del_QX) {
            this.del_QX = del_QX;
        }

        public String getEdit_QX() {
            return edit_QX;
        }

        public void setEdit_QX(String edit_QX) {
            this.edit_QX = edit_QX;
        }

        public String getCha_QX() {
            return cha_QX;
        }

        public void setCha_QX(String cha_QX) {
            this.cha_QX = cha_QX;
        }
    }
}
