package com.rcpt.ui.recruit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.rcpt.BR;
import com.rcpt.Constant;
import com.rcpt.LoginHelper;
import com.rcpt.R;
import com.rcpt.adapter.FilterListAdapter;
import com.rcpt.base.adapter.BindingViewHolder;
import com.rcpt.base.adapter.SimpleBindingAdapter;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.bean.ChooseProvinceBean;
import com.rcpt.bean.FilterSelectBean;
import com.rcpt.bean.RecruitListBean;
import com.rcpt.bean.User;
import com.rcpt.databinding.ActivityRecruitJobListBinding;
import com.rcpt.databinding.ItemLayoutRecruitCvListBinding;
import com.rcpt.mvp.contract.RecruitListContract;
import com.rcpt.mvp.presenter.RecruitListPresenter;
import com.rcpt.recycler_listener.OnRecyclerItemClickListener;
import com.rcpt.ui.register.group.ChooseProvinceActivity;
import com.rcpt.utils.AnimationUtils;
import com.rcpt.utils.FilterPopupWindowUtils;
import com.rcpt.utils.ViewUtils;
import com.rcpt.widget.FilterItemView;
import com.rcpt.widget.SearchTitlePopWindow;

import java.util.List;

/**
 * 人才通道的列表页面（包含职位招聘列表和简历列表）
 */
@SuppressLint("WrongConstant")
public class RecruitJobListActivity extends BaseActivity<ActivityRecruitJobListBinding, RecruitListContract.View, RecruitListPresenter>
        implements RecruitListContract.View, SpringView.OnFreshListener, View.OnClickListener, FilterListAdapter.OnSelectDataCallback
        , FilterListAdapter.OnAutoSelectDataCallback, SearchTitlePopWindow.OnClickSearchListener, FilterPopupWindowUtils.OnFilterPopupListener {

    private static final int REQUEST_CODE_ADDRESS_SELECT = 0x000001;
    private FilterListAdapter mFilterLeftAdapter;
    private FilterListAdapter mFilterRightAdapter;
    private String mAddressProvinceId;
    private String mAddressCityId;
    private FilterItemView.DataHolder mFilterItemDataHolder;
    private String mFilterItemChildName;


    public static void actionStart(Context context, boolean isOpenSearch) {
        Intent intent = new Intent(context, RecruitJobListActivity.class);
        intent.putExtra("is_open_search", isOpenSearch);
        context.startActivity(intent);
    }


    private SimpleBindingAdapter<RecruitListBean.JobListBean> mJobAdapter;
    private SimpleBindingAdapter<RecruitListBean.CVListBean> mCVAdapter;

    private SearchTitlePopWindow mSearchPop;

    private CheckBox mOldSelectFilterItem;

    private boolean isEnd;
    private int mCheckedId;

    private boolean isPerson;
    private boolean isFilterLayoutIn;
    private boolean isChecked;
    private FilterSelectBean mFilterSelectBean;
    private int mClickId;
    private String mTitleName;
    private String mInputSearchText;

    private boolean isOpenSearch;

    private FilterPopupWindowUtils mFilterPopupWindowUtils;

    @Override
    protected void initFieldBeforeMethods() {
        isOpenSearch = getIntent().getBooleanExtra("is_open_search", false);
        User user = LoginHelper.getInstance().getUserBean();
        if (user.getUserType().equals(Constant.UserType.PERSON.getValue())) {
            isPerson = true;
        } else {
            isPerson = false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isOpenSearch)
            mSearchView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isOpenSearch = false;
                    showSearchPop(null);
                }
            }, 100);
    }

    @Override
    protected void setupTitle() {
        String title;
        if (isPersonType()) {
            title = "人才通道";
        } else {
            title = "人才库";
        }
        mTitleName = title;
        setTitleText(mTitleName);
        openTitleLeftView(true);
        mSearchView.setVisibility(View.VISIBLE);
    }

    /**
     * 显示标题栏中右侧的按钮
     */
    @Override
    public void showTitleRightView(int selectNum) {
        setTitleRightText(String.format("已选%s", "" + selectNum));
        getTitleRightView().setVisibility(View.VISIBLE);
        toRightViewLeft();
    }

    /**
     * 隐藏标题栏右侧的按钮
     */
    @Override
    public void hintTitleRightView() {
        getTitleRightView().setVisibility(View.GONE);
        setSearchViewToParentRight();
    }

    @Override
    protected void initViews() {
        mSearchPop = new SearchTitlePopWindow(this);
        mSearchPop.setOnClickSearchListener(this);
        mFilterPopupWindowUtils = new FilterPopupWindowUtils(mContext);
        mFilterPopupWindowUtils.setOnFilterItemClickCloseListener(this);
        mFilterPopupWindowUtils.getPopupWindow().setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                closeGrayBg();
            }
        });
        setOnClickListener(mDataBinding.includeJobFilter.jobFilterIndustry
                , mDataBinding.includeJobFilter.jobFilterSalRange
                , mDataBinding.includeJobFilter.jobFilterJobType
                , mDataBinding.includeJobFilter.jobFilterRange
                , mDataBinding.includePositionFilter.positionFilterEducation
                , mDataBinding.includePositionFilter.positionFilterJob
                , mDataBinding.includePositionFilter.positionFilterJobTime
                , mDataBinding.includePositionFilter.positionFilterSalRange
                , mDataBinding.bgGray
                , mDataBinding.includePositionFilter.positionFilterAddress
                , getTitleRightView()
                , mDataBinding.includePositionFilter.tvFilterCancel
                , mSearchView
                , mDataBinding.includePositionFilter.tvFilterConfirm);
    /*    if (!isOpenSearch)*/
        mPresenter.loadListData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recruit_job_list;
    }

    @Override
    protected RecruitListPresenter createPresenter() {
        return new RecruitListPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initRecyclerView() {

        mDataBinding.springView.setListener(this);
        mDataBinding.springView.setHeader(new DefaultHeader(this));
        mDataBinding.springView.setFooter(new DefaultFooter(this));

        mDataBinding.recyclerView.addOnItemTouchListener(new OnItemClickListener(getRecyclerView()));
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mDataBinding.recyclerView.setRefrshView(mDataBinding.springView);
        mDataBinding.recyclerView.setEmptyView(findViewById(R.id.empty_view));

        if (isPersonType()) {
            mDataBinding.includeJobFilter.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            mDataBinding.includeJobFilter.llGroupJobFilter.setVisibility(View.VISIBLE);
            mJobAdapter = new SimpleBindingAdapter<>(R.layout.item_layout_recruit_job_list, BR.recruitJobListBean);
            mDataBinding.recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
            mDataBinding.recyclerView.setAdapter(mJobAdapter);
        } else {
            mDataBinding.includePositionFilter.recyclerViewRight.setLayoutManager(new LinearLayoutManager(mContext));
            mDataBinding.includePositionFilter.recyclerViewLeft.setLayoutManager(new LinearLayoutManager(mContext));
            mDataBinding.includePositionFilter.llGroupPositionFilter.setVisibility(View.VISIBLE);
            mCVAdapter = new SimpleBindingAdapter<RecruitListBean.CVListBean>(R.layout.item_layout_recruit_cv_list, BR.cvListBean) {
                @Override
                protected void bindingViews(BindingViewHolder<ViewDataBinding> holder, int position, RecruitListBean.CVListBean cvListBean) {
                    super.bindingViews(holder, position, cvListBean);
                    ItemLayoutRecruitCvListBinding binding = (ItemLayoutRecruitCvListBinding) holder.getBinding();
                    binding.tvPosition.setText(TextUtils.isEmpty(cvListBean.getPosition().replace("null", "")) ? "暂未完善期待职位" : "期待职位：" + cvListBean.getPosition().replace("null", ""));
                }
            };
            mDataBinding.recyclerView.setAdapter(mCVAdapter);
        }

    }

    @Override
    public void bindListData(List<RecruitListBean.JobListBean> beanList) {

    }

    @Override
    public void bindJobListData(List<RecruitListBean.JobListBean> listData) {
        setListData(listData);
        mJobAdapter.setupData(listData);
    }

    @Override
    public void bindCVListData(List<RecruitListBean.CVListBean> listData) {
        setListData(listData);
        mCVAdapter.setupData(listData);
    }


    @Override
    public RecyclerView getRecyclerView() {
        return mDataBinding.recyclerView;
    }

    @Override
    public void updateIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    /**
     * 下拉刷新，回调接口
     */
    @Override
    public void onRefresh() {
        onRefresh(isEnd);
    }

    /**
     * 上拉加载，回调接口
     */
    @Override
    public void onLoadmore() {
        onLoadMore(isEnd);
    }

    @Override
    protected void loadListData(int page) {
        mPresenter.onLoadMore(page);
    }

    @Override
    protected ViewGroup getRefreshView() {
        return mDataBinding.springView;
    }

    /**
     * 显示灰色的背景层
     */
    @Override
    public void showGrayBg() {
        if (mDataBinding.bgGray.getVisibility() == View.GONE) {
            AnimationUtils.mackAlphaAnimation(mDataBinding.bgGray, true).setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    mDataBinding.bgGray.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animation animation) {

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
    }

    /**
     * 灰色背景关闭
     */
    @Override
    public void closeGrayBg() {
        if (mDataBinding.bgGray.getVisibility() == View.VISIBLE) {
            AnimationUtils.mackAlphaAnimation(mDataBinding.bgGray, false).setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    mDataBinding.bgGray.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
        if (mOldSelectFilterItem != null)
            mOldSelectFilterItem.setChecked(false);
    }

    /**
     * 获取是否为普通用户登录的身份
     *
     * @return
     */
    @Override
    public boolean isPersonType() {
        return isPerson;
    }

    /**
     * 获取点击的按钮是否是选中的状态
     *
     * @return
     */
    @Override
    public boolean isChecked() {
        return isChecked;
    }

    /**
     * 获取筛选点击的id
     *
     * @return
     */
    @Override
    public int getCheckId() {
        return mCheckedId;
    }

    /**
     * 筛选条件布局开始进入
     */
    @Override
    public void layoutStartIn() {
        if (!isFilterLayoutIn) {
            if (isPersonType()) {
                AnimationUtils.mackTranslationYAnimation(mDataBinding.includeJobFilter.llParent, getJobTopScrollHeight(), true).start();
            } else {
                AnimationUtils.mackTranslationYAnimation(mDataBinding.includePositionFilter.llFilterParent, getPositionTopScrollHeight(), true).start();
            }
            isFilterLayoutIn = true;
        } else if (Build.VERSION_CODES.LOLLIPOP <= Build.VERSION.SDK_INT) {
            if (isPersonType()) {
                ViewUtils.viewIn(mDataBinding.includeJobFilter.llParent);
            } else {
                ViewUtils.viewIn(mDataBinding.includePositionFilter.llFilterParent);
            }

        }
    }

    /**
     * 筛选条件的布局出来
     */
    @Override
    public void layoutStartOut() {
        if (isFilterLayoutIn) {
            if (isPersonType()) {
                AnimationUtils.mackTranslationYAnimation(mDataBinding.includeJobFilter.llParent, getJobTopScrollHeight(), false).start();
            } else {
                AnimationUtils.mackTranslationYAnimation(mDataBinding.includePositionFilter.llFilterParent, getPositionTopScrollHeight(), false).start();
            }
            isFilterLayoutIn = false;
        }
    }

    /**
     * 重置所有筛选的条件的名称
     */
    @Override
    public void resetFilterAllName() {
        if (mFilterLeftAdapter != null)
            mFilterLeftAdapter.dataReset();
        if (mFilterRightAdapter != null)
            mFilterRightAdapter.dataReset();
        if (isPersonType()) {
            mDataBinding.includeJobFilter.jobFilterIndustry.setText("行业");
            mDataBinding.includeJobFilter.jobFilterJobType.setText("工作类型");
            mDataBinding.includeJobFilter.jobFilterSalRange.setText("薪资");
        } else {
            mAddressProvinceId = null;
            mAddressCityId = null;
            mDataBinding.includePositionFilter.positionFilterAddress.setText("地点");
            mDataBinding.includePositionFilter.positionFilterEducation.setText("学历");
            mDataBinding.includePositionFilter.positionFilterJob.setText("职位");
            mDataBinding.includePositionFilter.positionFilterJobTime.setText("年限");
            mDataBinding.includePositionFilter.positionFilterSalRange.setText("薪资");
        }
    }

    /**
     * 进入到地址选择
     */
    @Override
    public void startGoAddress() {
        ChooseProvinceActivity.actionStart(this, false, false,mAddressProvinceId, mAddressCityId, REQUEST_CODE_ADDRESS_SELECT);
    }

    /**
     * 绑定筛选选择的名称到View中
     *
     * @param filterName
     */
    @Override
    public void setupFilterName(String filterName) {
        if (mOldSelectFilterItem != null)
            mOldSelectFilterItem.setText(filterName);
    }

    /**
     * 这个只用在企业身份登录的状态时才会调用
     * 绑定左侧的筛选列表数据
     *
     * @param listData
     * @param isMathParent 是否需要填充全屏
     */
    @Override
    public void bindLeftFilterData(List<FilterSelectBean> listData, boolean isMathParent) {
        mFilterLeftAdapter = new FilterListAdapter();
        mFilterLeftAdapter.setupData(listData);
        mFilterLeftAdapter.setOnAutoSelectCallback(this);
        if (isPersonType()) {
            mFilterLeftAdapter.setClickMode(FilterListAdapter.CLICK_MODE_AUTOMATIC_ONE);
            mDataBinding.includeJobFilter.recyclerView.setAdapter(mFilterLeftAdapter);
        } else {
            if (isMathParent) {
                mDataBinding.includePositionFilter.llRightParent.setVisibility(View.GONE);
                mFilterLeftAdapter.setClickMode(FilterListAdapter.CLICK_MODE_AUTOMATIC_ONE);
            } else {
                mFilterLeftAdapter.setOnSelectDataCallback(this);
                mFilterLeftAdapter.setClickMode(FilterListAdapter.CLICK_MODE_MANUAL_ONE);
                if (mDataBinding.includePositionFilter.llRightParent.getVisibility() == View.GONE) {
                    mDataBinding.includePositionFilter.llRightParent.setVisibility(View.VISIBLE);
                }
            }
            mDataBinding.includePositionFilter.recyclerViewLeft.setAdapter(mFilterLeftAdapter);
        }


    }

    /**
     * 这个只用在企业身份登录的状态时才会调用
     * 绑定右侧的筛选列表数据
     *
     * @param listData
     * @param parentName 父级的名称
     */
    @Override
    public void bindRightFilterData(List<FilterSelectBean> listData, String parentName) {
        mFilterSelectBean = new FilterSelectBean(parentName, "0", true);
        mDataBinding.includePositionFilter.tvParentName.setText(parentName);
        mFilterRightAdapter = new FilterListAdapter(FilterListAdapter.CLICK_MODE_MANUAL_FINITE);
        mFilterRightAdapter.setOnSelectDataCallback(this);
        mFilterRightAdapter.setupData(listData);
        mDataBinding.includePositionFilter.recyclerViewRight.setAdapter(mFilterRightAdapter);
    }

    /**
     * 获取筛选条件的类型对象
     *
     * @return
     */
    @Override
    public FilterSelectBean getFilterSelectBean() {
        return mFilterSelectBean;
    }

    /**
     * 获取选择筛选条件的数组
     *
     * @return
     */
    @Override
    public List<FilterSelectBean> getSelectFilterList() {
        RecyclerView.Adapter adapter = mDataBinding.includePositionFilter.recyclerViewRight.getAdapter();
        if (adapter != null && adapter instanceof FilterListAdapter)
            return ((FilterListAdapter) adapter).getSelectFilterList();
        return null;
    }

    /**
     * 返回选择的数据对象
     *
     * @param bean
     */
    @Override
    public void onAutoCallback(FilterSelectBean bean) {
        mFilterSelectBean = bean;
        mPresenter.onFilterSelectOver();
    }

    /**
     * 当条目再次选中后的回调
     *
     * @param bean
     * @param position
     */
    @Override
    public void onReselectedCallback(FilterSelectBean bean, int position) {
        layoutStartOut();
        closeGrayBg();
    }

    /**
     * 返回选择的数据对象
     *
     * @param bean             当前选中的对象
     * @param isSelectOne      是否是单选
     * @param isSelectOverflow 是否超出最大选择的数量
     */
    @Override
    public void onCallback(FilterSelectBean bean, boolean isSelectOne, boolean isSelectOverflow) {
        if (isSelectOne) {
            mFilterSelectBean = bean;
            mPresenter.onFilterJobParentClick();
        } else if (isSelectOverflow) {
            showToast("已超出最大选择数量");
        }
    }

    /**
     * 开启已选中的条件PopupWindow
     */
    @Override
    public void showSelectFilterPop() {
        if (isPersonType()) {
            LinearLayout llParent = mDataBinding.includeJobFilter.llGroupJobFilter;
            mFilterPopupWindowUtils.setupFilterCount(llParent.getChildCount());
            mFilterPopupWindowUtils.showPop(llParent);
        } else {
            LinearLayout llPrent = mDataBinding.includePositionFilter.llGroupPositionFilter;
            mFilterPopupWindowUtils.setupFilterCount(llPrent.getChildCount());
            mFilterPopupWindowUtils.showPop(llPrent);
        }
        showGrayBg();
    }

    /**
     * 关闭已选中条件的PopupWindow
     */
    @Override
    public void closeSelectFilterPop() {
        mFilterPopupWindowUtils.dismiss();
    }


    /**
     * 将Filter的单个条目添加到PopupWindow中
     *
     * @param holder
     */
    @Override
    public int addFilterItemToPop(FilterItemView.DataHolder holder, boolean isNeedClose) {
        return mFilterPopupWindowUtils.addFilterItem(holder, isNeedClose);
    }

    /**
     * 当里面的Item被点击关闭的回调
     *
     * @param holder
     */
    @Override
    public void onItemClickClose(FilterItemView.DataHolder holder) {
        mFilterItemDataHolder = holder;
        mPresenter.onFilterItemClose();
    }

    /**
     * 获取删除条件的子类型的名称
     *
     * @return
     */
    @Override
    public String getFilterRemoveChildName() {
        return mFilterItemChildName;
    }

    /**
     * 当筛选条件的子Item被删除的回调
     *
     * @param holder
     * @param childName
     */
    @Override
    public void onItemChildClickClose(FilterItemView.DataHolder holder, String childName) {
        mFilterItemDataHolder = holder;
        mFilterItemChildName = childName;
        mPresenter.onFilterItemChildClose();
    }

    /**
     * 当点击重置的回调接口
     */
    @Override
    public void onFiltersReset() {
        mPresenter.onFilterReset();
    }

    /**
     * 获取条件筛选删除的DataHolder
     *
     * @return
     */
    @Override
    public FilterItemView.DataHolder getFilterRemoveDataHolder() {
        return mFilterItemDataHolder;
    }

    /**
     * 获取点击的Id
     *
     * @return
     */
    @Override
    public int getClickId() {
        return mClickId;
    }

    /**
     * 当筛选条件的地址被清空的时候进行的回调
     */
    @Override
    public void onFilterAddressClean() {
        mAddressProvinceId = null;
        mAddressCityId = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bg_gray:
                layoutStartOut();
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        closeGrayBg();
                    }
                }, 300);
                return;
        }
        if (v instanceof CheckBox && v.getId() != R.id.cbx_app_title_layout_right) {
            isChecked = ((CheckBox) v).isChecked();
            mCheckedId = v.getId();
            mPresenter.onFilterItemClick();
            //将原有选中的View设置为未选中状态
            if (mOldSelectFilterItem != null && mOldSelectFilterItem != v) {
                mOldSelectFilterItem.setChecked(false);
            }
            mOldSelectFilterItem = (CheckBox) v;
        } else {
            mClickId = v.getId();
            mPresenter.onViewClick();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_ADDRESS_SELECT:
                if (resultCode == RESULT_OK && data != null) {
                    ChooseProvinceBean provinceBean = data.getParcelableExtra("provinceBean");
                    ChooseProvinceBean cityBean = data.getParcelableExtra("cityBean");
                    String cityId = cityBean.getRegionId();
                    String provinceId = provinceBean.getRegionId();
                    String regionName = cityBean.getRegionName();
                    mFilterSelectBean = new FilterSelectBean(regionName, cityId, true);
                    mPresenter.onFilterSelectOver();
                    mAddressProvinceId = provinceId;
                    mAddressCityId = cityId;

                } else {
                    mOldSelectFilterItem.setChecked(false);
                }
                break;
        }

    }

    //关于搜索的逻辑处理

    /**
     * 显示搜索的PopWindow
     *
     * @param searchText
     */
    @Override
    public void showSearchPop(String searchText) {
        mSearchPop.showPopupWindow(searchText, mDataBinding.getRoot());
    }

    /**
     * 获取搜索内容
     *
     * @return
     */
    @Override
    public String getInputSearchText() {
        return mInputSearchText;
    }

    /**
     * 重置标题名称
     */
    @Override
    public void resetTitleName() {
        setTitleText(mTitleName);
    }

    /**
     * 设置标题栏的名字
     */
    @Override
    public void setTitleName(String titleName) {
        setTitleText(titleName);
    }

    /**
     * 点击搜索按钮后进行的接口回调
     *
     * @param searchText 搜索框中搜索的内容
     */
    @Override
    public void onClickSearch(String searchText) {
        mInputSearchText = searchText;
        mPresenter.onPopLayoutClickSearch();
    }

    //搜索处理逻辑end
    private void setOnClickListener(View... views) {
        for (View view : views) {
            view.setOnClickListener(this);
        }
    }

    private class OnItemClickListener extends OnRecyclerItemClickListener {

        public OnItemClickListener(RecyclerView recyclerView) {
            super(recyclerView);
        }

        @Override
        public void onItemClick(RecyclerView.ViewHolder vh) {
            mPresenter.onItemClick(vh, vh.getLayoutPosition());
        }
    }

    /**
     * 5.0以上geiHeight方法会帮你自动加入顶部View的高度，低于5.0的就不会
     *
     * @return
     */
    private int getPositionTopScrollHeight() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            return mDataBinding.includePositionFilter.llFilterParent.getHeight();
        return mDataBinding.includePositionFilter.llFilterParent.getHeight() + mDataBinding.includePositionFilter.llGroupPositionFilter.getHeight();
    }

    private int getJobTopScrollHeight() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            return mDataBinding.includeJobFilter.llParent.getHeight();
        return mDataBinding.includeJobFilter.llParent.getHeight() + mDataBinding.includeJobFilter.llGroupJobFilter.getHeight();
    }

    /**
     * 设置搜索控件在右侧控件的左侧
     */
    private void toRightViewLeft() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.LEFT_OF, getTitleRightView().getId());
        layoutParams.rightMargin = 8;
        mSearchView.setLayoutParams(layoutParams);
    }

    /**
     * 设置搜索控件在父控件的最右侧
     */
    private void setSearchViewToParentRight() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        layoutParams.rightMargin = 12;
        mSearchView.setLayoutParams(layoutParams);
    }

}
