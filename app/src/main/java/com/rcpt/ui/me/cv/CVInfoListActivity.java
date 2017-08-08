package com.rcpt.ui.me.cv;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rcpt.BR;
import com.rcpt.Constant;
import com.rcpt.R;
import com.rcpt.base.adapter.SimpleBindingAdapter;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.bean.CVInfoListBean;
import com.rcpt.databinding.ActivityCvInfoListBinding;
import com.rcpt.mvp.contract.CVInfoListContract;
import com.rcpt.mvp.presenter.CVInfoListPresenter;
import com.rcpt.recycler_listener.OnRecyclerItemListener;
import com.rcpt.utils.DialogUtils;

import java.util.List;

/**
 * 信息列表（包含教育背景列表，工作经历列表，项目经验列表）
 */
public class CVInfoListActivity extends BaseActivity<ActivityCvInfoListBinding, CVInfoListContract.View, CVInfoListPresenter>
        implements CVInfoListContract.View {

    private static final int REQUEST_CODE_CREATE_CV_INFO = 0x0000121;
    private static final int REQUEST_CODE_EDIT_CV_INFO = 0x0000122;

    private static final String CREATE_CV_TYPE = "type";
    private static final String CV_ID_TAG = "id_tag";

    private String mCreateCVType;
    private String mCVId;

    private SimpleBindingAdapter<CVInfoListBean> mAdapter;
    private int mClickPosition;


    public static void actionStart(Context context, String createCVType, String cvId) {
        Intent intent = new Intent(context, CVInfoListActivity.class);
        intent.putExtra(CREATE_CV_TYPE, createCVType);
        intent.putExtra(CV_ID_TAG, cvId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initFieldBeforeMethods() {
        Intent intent = getIntent();
        mCreateCVType = intent.getStringExtra(CREATE_CV_TYPE);
        mCVId = intent.getStringExtra(CV_ID_TAG);
    }

    @Override
    protected void setupTitle() {
        String title = Constant.getCreateCVInfoTitile(mCreateCVType);
        mDataBinding.btnAdd.setText(getString(R.string.create_cv_list_add, title));
        setTitleText(title);
        openTitleLeftView(true);
    }

    @Override
    protected void initViews() {
        mPresenter.loadListData();
        mDataBinding.setPresenter(mPresenter);
    }

    @Override
    public void actionGoCreateCVInfo() {
        CreateCVInfoActivity.actionStart(mActivity, mCreateCVType, mCVId, REQUEST_CODE_CREATE_CV_INFO);
    }

    @Override
    public void actionGoEditCVInfo(Parcelable editBean) {
        CreateCVInfoActivity.actionStart(mActivity, mCreateCVType, mCVId, true, editBean, REQUEST_CODE_EDIT_CV_INFO);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cv_info_list;
    }

    @Override
    protected CVInfoListPresenter createPresenter() {
        return new CVInfoListPresenter();
    }

    @Override
    public String getCreateCVType() {
        return mCreateCVType;
    }

    @Override
    public String getCVId() {
        return mCVId;
    }

    /**
     * 获取点击条目的Position
     *
     * @return
     */
    @Override
    public int getClickPosition() {
        return mClickPosition;
    }

    @Override
    public void initRecyclerView() {
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        mDataBinding.recyclerView.addOnItemTouchListener(new OnItemListener(mDataBinding.recyclerView));
        DividerItemDecoration decoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
        decoration.setDrawable(ContextCompat.getDrawable(mContext, R.drawable.divider_line_10dp));
        mDataBinding.recyclerView.addItemDecoration(decoration);
        mAdapter = new SimpleBindingAdapter<>(R.layout.item_layout_cv_info_list, BR.infoBean);
        mDataBinding.recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void deleteListItem(int position) {
        mAdapter.notifyItemRemoved(position);
    }

    @Override
    public void bindListData(List<CVInfoListBean> beanList) {
        if (beanList.isEmpty()) {
            if (mDataBinding.recyclerView.getVisibility() == View.VISIBLE) {
                mDataBinding.recyclerView.setVisibility(View.GONE);
                findViewById(R.id.empty_view).setVisibility(View.VISIBLE);
            }

        } else {
            if (findViewById(R.id.empty_view).getVisibility() == View.VISIBLE) {
                findViewById(R.id.empty_view).setVisibility(View.GONE);
                mDataBinding.recyclerView.setVisibility(View.VISIBLE);
            }
        }
        mAdapter.setupData(beanList);
    }


    @Override
    public RecyclerView getRecyclerView() {
        return mDataBinding.recyclerView;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_CREATE_CV_INFO:
                case REQUEST_CODE_EDIT_CV_INFO:
                    mPresenter.loadListData();
                    break;
            }
        }
    }

    private class OnItemListener extends OnRecyclerItemListener {


        private OnItemListener(RecyclerView recyclerView) {
            super(recyclerView);
        }

        @Override
        public void onItemClick(RecyclerView.ViewHolder vh) {
            mClickPosition = vh.getAdapterPosition();
            mPresenter.onItemClick();
        }

        @Override
        public boolean onItemLongClick(final RecyclerView.ViewHolder vh) {
            DialogUtils.buildAlertDialogWithCancel(mContext, "温馨提示", "您是否要删除该条目")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mClickPosition = vh.getAdapterPosition();
                            mPresenter.onItemLongClick();
                        }
                    }).show();
            return true;
        }
    }
}
