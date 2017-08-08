package com.rcpt.mvp.module;

import android.content.Context;
import android.content.res.Resources;

import com.rcpt.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 860617003 on 2017/6/8.
 */

public class UserManageFilterModule {

    private Map<String, String> mGroupUserTypes;
    private Map<String, String> mGroupUserStatus;
    private Map<String, String> mPersonUserStatus;
    private List<String> mGroupUserTypeList;
    private List<String> mGroupUserStatusList;
    private List<String> mPersonUserStatusList;


    public void buildData(Context context) {
        Resources resources = context.getResources();
        String[] groupUserStatusCodes = resources.getStringArray(R.array.group_user_status_code);
        String[] groupUserTypeCodes = resources.getStringArray(R.array.group_user_type_code);
        String[] personStatusTypeCodes = resources.getStringArray(R.array.person_user_status_code);
        String[] groupUserStatus = resources.getStringArray(R.array.group_user_status);
        String[] groupUserType = resources.getStringArray(R.array.group_user_type);
        String[] personStatusType = resources.getStringArray(R.array.person_user_status);
        mGroupUserTypes = createMap(groupUserTypeCodes);
        mGroupUserStatus = createMap(groupUserStatusCodes);
        mPersonUserStatus = createMap(personStatusTypeCodes);
        mGroupUserStatusList = new ArrayList<>(Arrays.asList(groupUserStatus));
        mGroupUserTypeList = new ArrayList<>(Arrays.asList(groupUserType));
        mPersonUserStatusList = new ArrayList<>(Arrays.asList(personStatusType));
    }

    private Map<String, String> createMap(String[] arrays) {
        HashMap<String, String> map = new HashMap<>();
        if (arrays == null || arrays.length <= 0)
            return map;
        for (String userStatus : arrays) {
            String[] items = userStatus.split(",");
            map.put(items[0], items[1]);
        }
        return map;
    }


    public Map<String, String> getGroupUserTypes() {
        return mGroupUserTypes;
    }

    public Map<String, String> getGroupUserStatus() {
        return mGroupUserStatus;
    }

    public Map<String, String> getPersonUserStatus() {
        return mPersonUserStatus;
    }

    public List<String> getGroupUserTypeList() {
        return mGroupUserTypeList;
    }

    public List<String> getGroupUserStatusList() {
        return mGroupUserStatusList;
    }

    public List<String> getPersonUserStatusList() {
        return mPersonUserStatusList;
    }
}
