package com.rcpt.ui.system;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.rcpt.BR;
import com.rcpt.R;
import com.rcpt.base.adapter.BindingViewHolder;
import com.rcpt.base.adapter.SimpleBindingAdapter;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.bean.SystemUserInfoBean;
import com.rcpt.bean.SystemUserListBean;
import com.rcpt.databinding.ActivitySystemUserBinding;
import com.rcpt.databinding.ItemLayoutSystemSystemUserListBinding;
import com.rcpt.databinding.LayoutDialogInputPasswordBinding;
import com.rcpt.mvp.contract.SystemUserContract;
import com.rcpt.mvp.presenter.SystemUserPresenter;
import com.rcpt.utils.DialogUtils;
import com.rcpt.widget.AddNewSystemUserPopWindow;
import com.rcpt.widget.ListSelectPopWindow;
import com.rcpt.widget.SearchTitlePopWindow;
import com.rcpt.widget.SimpleListSelectPopWindow;

import java.util.List;

/**
 * 系统用户列表的Activity
 */
@SuppressLint("WrongConstant")
public class SystemUserActivity extends BaseActivity<ActivitySystemUserBinding, SystemUserContract.View, SystemUserPresenter>
        implements SystemUserContract.View, SpringView.OnFreshListener, ListSelectPopWindow.OnListSelectDataCallback, View.OnClickListener, SearchTitlePopWindow.OnClickSearchListener {

    private SearchTitlePopWindow mSearchPop;
    private boolean isEnd;
    private SimpleBindingAdapter<SystemUserListBean.UserListBean> mAdapter;
    private AddNewSystemUserPopWindow mAddPopWindow;
    private SimpleListSelectPopWindow mSelectPopWindow;

    private int mClickSelectListPosition;
    private String mSelectValue;
    private int mClickListItemPosition;

    /**
     * 设置搜索控件在右侧控件的左侧
     */
    private void toRightViewLeft() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.LEFT_OF, mSearchView.getId());
        layoutParams.rightMargin = 8;
        getTitleRightView().setLayoutParams(layoutParams);
    }

    /**
     * 构建出系统用户的基本信息
     *
     * @param userListBean
     * @return
     */
    private SystemUserInfoBean buildUpdateBean(SystemUserListBean.UserListBean userListBean) {
        SystemUserInfoBean infoBean = new SystemUserInfoBean();
        if (userListBean == null) return infoBean;
        infoBean.setUSER_ID(userListBean.getUSER_ID());
        infoBean.setNAME(userListBean.getNAME());
        infoBean.setUSERNAME(userListBean.getUSERNAME());
        infoBean.setAUTHORITIES(userListBean.getAUTHORITIES());
        infoBean.setPHONE(userListBean.getPHONE());
        infoBean.setNUMBER(userListBean.getNUMBER());
        infoBean.setBZ(userListBean.getBZ());
        infoBean.setROLE_ID(userListBean.getROLE_ID());
        infoBean.setEMAIL(userListBean.getEMAIL());
        infoBean.setRoleName(userListBean.getROLE_NAME());
        infoBean.setAuthoritiesName(userListBean.getREGION_NAME());
        return infoBean;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 未实现的方法
     */
    @Override
    protected void setupTitle() {
        setTitleText("系统用户管理");
        openTitleLeftView(true);
        CheckBox rightView = getTitleRightView();
        rightView.setVisibility(View.VISIBLE);
        rightView.setButtonDrawable(R.drawable.ic_vectror_add2);
        rightView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAddPopWindow.bindUserBean(null, false);
                mAddPopWindow.showPopupWindow(mDataBinding.getRoot());
                mAddPopWindow.setTitleText("新增系统用户");
            }
        });
        mSearchView.setVisibility(View.VISIBLE);
        mSearchView.setOnClickListener(this);
        toRightViewLeft();
    }

    @Override
    protected void initViews() {
        mPresenter.loadListData();
        initAddPopWindow();
        initSelectPopWindow();
    }

    private void initSelectPopWindow() {
        mSelectPopWindow = new SimpleListSelectPopWindow(this);
        mSelectPopWindow.setOnListSelectCallback(this);
    }

    private void initAddPopWindow() {
        mAddPopWindow = new AddNewSystemUserPopWindow(this);
        mAddPopWindow.getPopDataBinding().setPresenter(mPresenter);
    }


    /**
     * 显示筛选的PopWindow
     *
     * @param selectVale
     */
    @Override
    public void showSelectPop(List<String> listData, String selectVale) {
        mSelectPopWindow.setSelectItemName(selectVale);
        mSelectPopWindow.bindListData(listData);
        mSelectPopWindow.showPopupWindow(mDataBinding.getRoot());
    }

    /**
     * 设置筛选Pop的标题
     *
     * @param popTitle
     */
    @Override
    public void setSelectPopTitle(String popTitle) {
        mSelectPopWindow.setTitleText(popTitle);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_system_user;
    }

    @Override
    protected SystemUserPresenter createPresenter() {
        return new SystemUserPresenter();
    }


    @Override
    public void updateIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    @Override
    public void initRecyclerView() {

        mDataBinding.springView.setHeader(new DefaultHeader(mContext));
        mDataBinding.springView.setFooter(new DefaultFooter(mContext));
        mDataBinding.springView.setListener(this);

        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new SimpleBindingAdapter<SystemUserListBean.UserListBean>(R.layout.item_layout_system_system_user_list, BR.systemUserBean) {
            @Override
            protected void bindingViews(BindingViewHolder<ViewDataBinding> holder, final int position, final SystemUserListBean.UserListBean userListBean) {
                super.bindingViews(holder, position, userListBean);
                ItemLayoutSystemSystemUserListBinding binding = (ItemLayoutSystemSystemUserListBinding) holder.getBinding();
                binding.getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SystemUserInfoBean infoBean = buildUpdateBean(userListBean);
                        mAddPopWindow.bindUserBean(infoBean, true);
                        mAddPopWindow.setTitleText("修改系统用户");
                        mAddPopWindow.showPopupWindow(SystemUserActivity.this.mDataBinding.getRoot());
                    }
                });
                binding.tvDel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogUtils.showSimpleDialog(mContext, "您是否要删除该系统管理员", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mClickListItemPosition = position;
                                mPresenter.onClickDelSystemUser();
                            }
                        });
                    }
                });
                binding.tvReset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mClickListItemPosition = position;
                        showInputPasswordDialog();
                    }
                });
            }
        };
        mDataBinding.recyclerView.setAdapter(mAdapter);
        mDataBinding.recyclerView.setRefrshView(mDataBinding.springView);
        mDataBinding.recyclerView.setEmptyView(findViewById(R.id.empty_view));

    }

    /**
     * 显示输入密码的提示框
     */
    public void showInputPasswordDialog() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_dialog_input_password, null);
        final LayoutDialogInputPasswordBinding binding = DataBindingUtil.bind(view);
        DialogUtils.buildAlertDialogWithCancel(mContext, "请输入您要修改的密码", "")
                .setView(view)
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String s = binding.editPassword.getText().toString();
                        mPresenter.onClickResetSystemUserPassword(s);
                    }
                }).show();
    }

    /**
     * 获取点击列表中点击Item的Position
     *
     * @return
     */
    @Override
    public int getClickListItemPosition() {
        return mClickListItemPosition;
    }

    /**
     * 删除列表中的某一个Item
     *
     * @param position
     */
    @Override
    public void removeAdapterItem(int position) {
        mAdapter.notifyItemRemoved(position);
    }

    /**
     * 获取
     *
     * @return
     */
    @Override
    public String getSelectRoleName() {
        return mAddPopWindow.getPopDataBinding().tvRole.getText().toString();
    }

    @Override
    public String getSelectAddressName() {
        return mAddPopWindow.getPopDataBinding().tvAuthorities.getText().toString();
    }

    @Override
    public void bindListData(List<SystemUserListBean.UserListBean> beanList) {
        setListData(beanList);
        mAdapter.setupData(beanList);
    }

    @Override
    public void onRefresh() {
        onRefresh(isEnd);
    }

    @Override
    public void onLoadmore() {
        onLoadMore(isEnd);
    }

    @Override
    protected ViewGroup getRefreshView() {
        return mDataBinding.springView;
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mDataBinding.recyclerView;
    }


    @Override
    protected void loadListData(int page) {
        mPresenter.onLoadMore(page);
    }

    /**
     * 获取筛选列表的点击条目Position
     *
     * @return
     */
    @Override
    public int getClickSelectListPosition() {
        return mClickSelectListPosition;
    }

    /**
     * 获取筛选列表的选择的值
     *
     * @return
     */
    @Override
    public String getSelectValue() {
        return mSelectValue;
    }

    @Override
    public void updatePupSelectData(@IdRes int viewId, String selectId) {
        switch (viewId) {
            case R.id.tv_role:
                mAddPopWindow.getUpdateUserBean().setROLE_ID(selectId);
                mAddPopWindow.getUpdateUserBean().setRoleName(getSelectValue());
                break;
            case R.id.tv_authorities:
                mAddPopWindow.getUpdateUserBean().setAUTHORITIES(selectId);
                mAddPopWindow.getUpdateUserBean().setAuthoritiesName(getSelectValue());
                break;
        }
        ((TextView) mAddPopWindow.getPopDataBinding().getRoot().findViewById(viewId)).setText(getSelectValue());
    }

    /**
     * 当条目被点击是的回调方法
     *
     * @param data     被选中的Item名称
     * @param position 被选中Item的Position
     */
    @Override
    public void onSelectCallback(String data, int position) {
        mClickSelectListPosition = position;
        mSelectValue = data;
        mPresenter.onSelectValueCallback();

    }


    /**
     * 系统用户是否处于编辑状态
     *
     * @return
     */
    @Override
    public boolean isEditSystemUser() {
        return mAddPopWindow.isEdit();
    }

    /**
     * 获取系统用户的实体类
     *
     * @return
     */
    @Override
    public SystemUserInfoBean getUploadSystemUserBean() {
        return mAddPopWindow.getUpdateUserBean();
    }


    boolean isSeachOrResetting;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_app_title_layout_search:
                if (isSeachOrResetting) {
                    resetting();
                } else {
                    showPopWindow();
                }
                break;
        }
    }

    /**
     * 重置搜索按钮
     */
    private void resetting() {
        onRefresh("");
        setTitleText("系统用户管理");
        mSearchView.setImageResource(R.drawable.ic_vector_search);
        isSeachOrResetting = false;
    }

    /**
     * 搜索按钮
     */
    private void showPopWindow() {
        mSearchPop = new SearchTitlePopWindow(this);
        mSearchPop.setOnClickSearchListener(this);
        mSearchPop.showPopupWindow("", mDataBinding.getRoot());
    }

    @Override
    public void onClickSearch(String searchText) {
        if (!TextUtils.isEmpty(searchText) && searchText != null) {
            onRefresh(searchText);
            isSeachOrResetting = true;
            mSearchView.setImageResource(R.drawable.ic_vector_reset);
            setTitleText("搜索内容:" + searchText);
        }
    }

    private void onRefresh(String searchText) {
        mPresenter.setKeyWord(searchText);
        mPresenter.loadListDataWithClean();
    }
}
