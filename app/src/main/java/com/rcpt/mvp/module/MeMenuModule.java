package com.rcpt.mvp.module;

import android.support.v7.view.menu.MenuItemImpl;

import com.rcpt.Constant;
import com.rcpt.LoginHelper;
import com.rcpt.R;
import com.rcpt.base.mvp.MenuModuleIml;
import com.rcpt.bean.MenuBean;

/**
 * Created by lvzp on 2017/3/1.
 * 类描述：
 */

public class MeMenuModule extends MenuModuleIml<MenuBean> {

    @Override
    public MenuBean getMenuBean(MenuItemImpl menuItem) {
        if (Constant.UserType.OTHER.getValue().equals(LoginHelper.getInstance().getUserBean().getUserType())) {
            if (menuItem.getItemId() == R.id.me_menu_item_news)
                return null;
        }
        return new MenuBean();
    }


}
