package com.rcpt.mvp.presenter;

import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;

import com.rcpt.Constant;
import com.rcpt.LoginHelper;
import com.rcpt.R;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.MenuLoadContract;
import com.rcpt.bean.MenuBean;
import com.rcpt.mvp.contract.MeContract;
import com.rcpt.mvp.module.MeMenuModule;
import com.rcpt.ui.login.LoginActivity;
import com.rcpt.ui.main.activity.MainActivity;
import com.rcpt.ui.me.AboutMeActivity;
import com.rcpt.ui.me.BidRecordActivity;
import com.rcpt.ui.me.CVManagementActivity;
import com.rcpt.ui.me.FavoritesActivity;
import com.rcpt.ui.me.JobInviteActivity;
import com.rcpt.ui.me.PersonInfoActivity;
import com.rcpt.ui.me.SendRecordActivity;
import com.rcpt.ui.me.TestRecordActivity;
import com.rcpt.ui.me.company.CompanyAccountManageActivity;
import com.rcpt.ui.me.company.CompanyFavoriteActivity;
import com.rcpt.ui.me.company.CompanyInfoActivity;
import com.rcpt.ui.me.company.CompanyNewsListActivity;
import com.rcpt.ui.me.company.CompanyRecruitActivity;
import com.rcpt.ui.me.message.InterviewMessageActivity;
import com.rcpt.ui.me.message.SystemMessageActivity;
import com.rcpt.ui.me.video.MyCourseActivity;
import com.rcpt.ui.me.video.MyOrderActivity;
import com.rcpt.ui.pay.PayStatusActivity;
import com.rcpt.utils.DialogUtils;

/**
 * Created by lvzp on 2017/3/1.
 * 类描述：
 */

public class MePresenter extends BasePresenter<MeContract.View> implements MeContract.Presenter {

    private MenuLoadContract.Module<MenuBean> mMenuModule;

    @Override
    public void attach(MeContract.View view) {
        super.attach(view);
        mMenuModule = new MeMenuModule();
        mMenuModule.attachMenuList(getView().getMenuList());
        getView().initRecyclerView();
        createItemClickListener(getView().getRecyclerView());
    }

    @Override
    public void onClickGoProjectInfo() {
        if (Constant.UserType.PERSON.getValue().equals(LoginHelper.getInstance().getUserBean().getUserType())) {
            getView().actionStartActivity(PersonInfoActivity.class);
        } else {
            getView().actionStartActivity(CompanyInfoActivity.class);
        }

    }

    @Override
    public void initMenu() {
        getView().bindListData(mMenuModule.getMenuBeanList());
    }

    @Override
    public void loadListData() {

    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder vh, int position) {
        MenuBean bean = mMenuModule.getMenuBeanList().get(position);
        switch (bean.getId()) {
            case R.id.me_menu_item_notify:
                getView().actionStartActivity(SystemMessageActivity.class);
                return;
            case R.id.me_menu_item_exit:
                exitLogin();
                return;
        }
        if (Constant.UserType.PERSON.getValue().equals(LoginHelper.getInstance().getUserBean().getUserType())) {
            switch (bean.getId()) {
                case R.id.me_menu_item_send_record://查看投递简历
                    getView().actionStartActivity(SendRecordActivity.class);
                    break;
                case R.id.me_menu_item_cv_manage://简历管理
                    getView().actionStartActivity(CVManagementActivity.class);
                    break;
                case R.id.me_menu_item_job_invite://职位邀请
                    getView().actionStartActivity(JobInviteActivity.class);
                    break;
                case R.id.me_menu_item_message://面试邀请
                    getView().actionStartActivity(InterviewMessageActivity.class);
                    break;
                case R.id.me_menu_item_favorites://收藏夹
                    getView().actionStartActivity(FavoritesActivity.class);
                    break;
                case R.id.me_menu_item_user_info:
                    getView().actionStartActivity(CompanyAccountManageActivity.class);
                    break;
                case R.id.me_menu_item_order:
                    getView().actionStartActivity(MyOrderActivity.class);
                    break;
                case R.id.me_menu_item_test_record://测评管理
                    getView().actionStartActivity(TestRecordActivity.class);
                    break;
            }

        } else {
            switch (bean.getId()) {
                case R.id.me_menu_item_news:
                    getView().actionStartActivity(CompanyNewsListActivity.class);
                    break;
                case R.id.me_menu_item_favorites:
                    getView().actionStartActivity(CompanyFavoriteActivity.class);
                    break;
                case R.id.me_menu_item_bid_record://关于我们
                    getView().actionStartActivity(BidRecordActivity.class);
                    break;
                case R.id.me_menu_item_recruit:
                    CompanyRecruitActivity.actionStart(getView().getContext(), CompanyRecruitActivity.FROM_WHERE_TAG_ME, null, null);
                    break;
                case R.id.me_menu_item_user_info:
                    getView().actionStartActivity(CompanyAccountManageActivity.class);
                    break;
            }
        }

    }


    private void exitLogin() {
        DialogUtils
                .buildAlertDialogWithCancel(getView().getContext(), "温馨提示", "您是否要退出登录")
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        LoginHelper.getInstance().userExit();
                        getView().closeActivity();
                        getView().actionStartActivity(MainActivity.class);
                        getView().showToast("退出成功");
                    }
                }).show();
    }

}
