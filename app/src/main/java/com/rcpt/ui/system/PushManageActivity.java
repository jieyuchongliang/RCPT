package com.rcpt.ui.system;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.igexin.sdk.PushManager;
import com.rcpt.R;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.databinding.ActivityPushManageBinding;
import com.rcpt.mvp.contract.PushManageContract;
import com.rcpt.mvp.presenter.PushManagePresenter;
import com.rcpt.utils.EditUtils;

public class PushManageActivity extends BaseActivity<ActivityPushManageBinding, PushManageContract.View, PushManagePresenter>
        implements PushManageContract.View {

    private EditUtils mEditUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 未实现的方法
     */
    @Override
    protected void setupTitle() {
        setTitleText("信息推送");
        openTitleLeftView(true);
    }

    @Override
    protected void initFieldBeforeMethods() {
        EditUtils.WatcherBuilder watcherBuilder = new EditUtils.WatcherBuilder();
        watcherBuilder.setMaxInputNum(500);
        watcherBuilder.setShowInputNumTextView(mDataBinding.tvShowInput);
        watcherBuilder.bindEditText(mDataBinding.editContent);
        mEditUtils = watcherBuilder.create();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mEditUtils != null)
            mEditUtils.detachWatcherEditView();
    }

    /**
     * 获取用户的类型
     *
     * @return
     */
    @Override
    public String getUserType() {
        int selectChildPosition = 0;
        int childCount = mDataBinding.radioGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = mDataBinding.radioGroup.getChildAt(i);
            if (childView instanceof RadioButton) {
                if (((RadioButton) childView).isChecked()) {
                    selectChildPosition = i;
                    break;
                }
            }
        }
        selectChildPosition -= 1;
        return String.valueOf(selectChildPosition);
    }

    @Override
    public String getPushTitle() {
        return mDataBinding.editTitle.getText().toString();
    }

    @Override
    public String getMessage() {
        return mDataBinding.editContent.getText().toString();
    }

    @Override
    protected void initViews() {
        mDataBinding.setPresenter(mPresenter);
        ((RadioButton) mDataBinding.radioGroup.getChildAt(1)).setChecked(true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_push_manage;
    }

    @Override
    protected PushManagePresenter createPresenter() {
        return new PushManagePresenter();
    }

    @Override
    public void closeActivityForOk() {
        setResult(RESULT_OK);
        finish();
    }
}
