package com.rcpt.mvp.presenter;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.support.v7.view.menu.MenuItemImpl;

import com.rcpt.LoginHelper;
import com.rcpt.R;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.MenuModuleIml;
import com.rcpt.bean.MenuBean;
import com.rcpt.mvp.contract.SystemMainContract;
import com.rcpt.ui.main.activity.MainActivity;
import com.rcpt.ui.me.message.SystemMessageActivity;
import com.rcpt.ui.system.PushManageActivity;
import com.rcpt.ui.system.SystemUserActivity;
import com.rcpt.ui.system.UserManageActivity;
import com.rcpt.utils.DialogUtils;

import java.util.List;

/**
 * Created by 860617003 on 2017/6/1.
 */
@SuppressLint("RestrictedApi")
public class SystemMainPresenter extends BasePresenter<SystemMainContract.View> implements SystemMainContract.Presenter {

    private MenuModuleIml<MenuBean> mMenuModule;

    @Override
    public void attach(SystemMainContract.View view) {
        super.attach(view);
        getView().initRecyclerView();
    }

    @Override
    public void loadListData() {
        getView().bindListData(mMenuModule.getMenuBeanList());
    }

    @Override
    public void onItemClick() {
        int clickPosition = getView().getClickPosition();
        MenuBean itemBean = mMenuModule.getItemBeanForPosition(clickPosition);
        switch (itemBean.getId()) {
            case R.id.system_main_push:
                getView().actionStartActivity(SystemMessageActivity.class);
                break;
            case R.id.system_main_user:
                getView().actionStartActivity(UserManageActivity.class);
                break;
            case R.id.system_main_manage:
                getView().actionStartActivity(SystemUserActivity.class);
                break;
            case R.id.system_main_exit:
                exitLogin();
                break;
        }
    }

    @Override
    public void initMenu() {
        mMenuModule = new MenuModuleIml<MenuBean>() {
            @Override
            public MenuBean getMenuBean(MenuItemImpl menuItem) {
                return new MenuBean();
            }
        };
        List<MenuItemImpl> menuList = getView().getMenuList();

        if (!LoginHelper.getInstance().getUserBean().isGroupRole() && !LoginHelper.getInstance().getUserBean().isPersonalRole()) {
            for (MenuItemImpl menuItem : menuList) {
                if (menuItem.getItemId() == R.id.system_main_user) {
                    menuList.remove(menuItem);
                    break;
                }
            }
        }

        if (!LoginHelper.getInstance().getUserBean().isSysUserRole()) {
            for (MenuItemImpl menuItem : menuList) {
                if (menuItem.getItemId() == R.id.system_main_manage) {
                    menuList.remove(menuItem);
                    break;
                }
            }
        }
        mMenuModule.attachMenuList(menuList);
    }

    private void exitLogin() {
        DialogUtils
                .buildAlertDialogWithCancel(getView().getContext(), "温馨提示", "您是否要退出登录")
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        LoginHelper.getInstance().userExit();
                        getView().actionStartActivity(MainActivity.class);
                        getView().closeActivity();
                        getView().showToast("退出成功");
                    }
                }).show();
    }
}
