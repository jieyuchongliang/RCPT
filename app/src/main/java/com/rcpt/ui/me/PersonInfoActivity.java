package com.rcpt.ui.me;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.PopupWindow;

import com.rcpt.R;
import com.rcpt.adapter.PersonInfoAdapter;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.bean.InputMenuBean;
import com.rcpt.databinding.ActivityPersonInfoBinding;
import com.rcpt.mvp.contract.PersonInfoContract;
import com.rcpt.mvp.presenter.PersonInfoPresenter;
import com.rcpt.recycler_listener.OnRecyclerItemClickListener;
import com.rcpt.utils.DialogUtils;
import com.rcpt.utils.MenuBuildUtils;
import com.rcpt.utils.SpaceItemDecoration;
import com.rcpt.widget.InputSingleDataPopWindow;
import com.rcpt.widget.SimpleListSelectPopWindow;
import com.rcpt.widget.SinglePictureSelectPopWindow;
import com.rcpt.widget.SingleSelectDialog;

import java.util.Calendar;
import java.util.List;

/**
 * 个人信息的界面
 */
public class PersonInfoActivity extends BaseActivity<ActivityPersonInfoBinding, PersonInfoContract.View, PersonInfoPresenter>
        implements PersonInfoContract.View {

    private PersonInfoAdapter mAdapter;
    private SinglePictureSelectPopWindow mSelectPopWindow;
    private InputSingleDataPopWindow mInputContentPop;

    private DatePickerDialog mDateDialog;
    private Calendar mCalendar;

    private SingleSelectDialog mSexSelectDialog;
    private SingleSelectDialog mOverseasSelectDialog;

    private SimpleListSelectPopWindow mListSelectPopWindow;
    private boolean isChange;

    @Override
    protected void setupTitle() {
        setTitleText("个人信息");
        openTitleLeftView(true);
    }

    @Override
    public void initRightView() {
        setTitleRightText("保存").setVisibility(View.GONE);
        getTitleRightView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onClickSave();
            }
        });
    }

    @Override
    protected void initFieldBeforeMethods() {
        mCalendar = Calendar.getInstance();
    }

    @Override
    protected void initViews() {

        mPresenter.initMenu();
        mPresenter.loadListData();

        initSexDialog();
        initOverseasDialog();
        mInputContentPop = new InputSingleDataPopWindow(this);
        mSelectPopWindow = new SinglePictureSelectPopWindow(this);
        mSelectPopWindow.createHelper();
        mInputContentPop.setOnClickSaveCallback(new InputSingleDataPopWindow.OnClickSaveCallback() {
            @Override
            public void onSaveCallback(String content) {
                mPresenter.itemDataChange(content);
            }
        });
        mSelectPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                lightOn();
            }
        });
    }

    /**
     * 初始化婚姻状态的PopWindow
     */
    @Override
    public void initMaritalSelectPop() {
        mListSelectPopWindow = new SimpleListSelectPopWindow(this);
        mListSelectPopWindow.setTitleText("婚姻状态选择");
        mListSelectPopWindow.setOnListSelectCallback(new SimpleListSelectPopWindow.OnListSelectDataCallback() {
            @Override
            public void onSelectCallback(String data, int position) {
                mPresenter.itemDataChange(data);
            }
        });
    }

    /**
     * 初始化性别选择的Dialog
     */
    private void initSexDialog() {
        mSexSelectDialog = new SingleSelectDialog(mContext);
        mSexSelectDialog.setTitleText("性别选择");
        mSexSelectDialog.setSelectData("男", "女");
        mSexSelectDialog.setOnClickSelectCallback(new SingleSelectDialog.OnClickSelectCallback() {
            @Override
            public void onDataCallback(String data) {
                mPresenter.itemDataChange(data);
            }
        });
    }

    /**
     * 初始化海外工作经历的Dialog
     */
    private void initOverseasDialog() {
        mOverseasSelectDialog = new SingleSelectDialog(mContext);
        mOverseasSelectDialog.setTitleText("海外工作经历选择");
        mOverseasSelectDialog.setSelectData("有", "无");
        mOverseasSelectDialog.setOnClickSelectCallback(new SingleSelectDialog.OnClickSelectCallback() {
            @Override
            public void onDataCallback(String data) {
                mPresenter.itemDataChange(data);
            }
        });
    }

    @Override
    public void updateSingleItem(int position, String value) {
        isChange = mAdapter.updateSingleItem(position, value);
        if (isChange && getTitleRightView().getVisibility() == View.GONE) {
            getTitleRightView().setVisibility(View.VISIBLE);
        }
    }


    @Override
    public List<MenuItemImpl> getMenuList() {
        return MenuBuildUtils.buildMenuItemList(mContext, R.menu.person_info_menu);
    }

    @Override
    public void initRecyclerView() {
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        OnItemClickListener onItemClickListener = new OnItemClickListener(mDataBinding.recyclerView);
        mDataBinding.recyclerView.addOnItemTouchListener(onItemClickListener);
        mAdapter = new PersonInfoAdapter();
        mDataBinding.recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void openSelectPhotoPop() {
        lightOff();
        mSelectPopWindow.showPopupWindow(mDataBinding.getRoot());
    }

    @Override
    public void setPopInputHintText(String inputHintText) {
        mInputContentPop.setInputHintText(inputHintText);
    }

    @Override
    public void setPopTitleText(String titleText) {
        mInputContentPop.setTitleText(titleText);
    }

    @Override
    public void setPopInputType(int inputType) {
        mInputContentPop.setInputType(inputType);
    }

    @Override
    public void setInputDataMax(int max) {
        mInputContentPop.setCheckInputMaxLength(max);
    }

    @Override
    public void openInputSinglePop(String value) {
        mInputContentPop.showPopupWindow(value, mDataBinding.getRoot());
    }

    /**
     * 显示性别选择的提示框
     */
    @Override
    public void showSexSelectDialog(String selectName) {
        mSexSelectDialog.setSelectItemName(selectName);
        mSexSelectDialog.show();
    }

    /**
     * 显示是否有过海外工作经历Dialog
     */
    @Override
    public void showOverseasDialog(String selectName) {
        mOverseasSelectDialog.setSelectItemName(selectName);
        mOverseasSelectDialog.show();
    }

    /**
     * 显示婚姻状态的PopWindow
     */
    @Override
    public void showSelectPop(List<String> dataSource, String selectItemName) {
        mListSelectPopWindow.setSelectItemName(selectItemName);
        mListSelectPopWindow.bindListData(dataSource);
        mListSelectPopWindow.showPopupWindow(mDataBinding.getRoot());
    }

    /**
     * 初始化学历选择的PopWindow
     */
    @Override
    public void initEducationSelectPop() {
        mListSelectPopWindow = new SimpleListSelectPopWindow(this);
        mListSelectPopWindow.setTitleText("学历/学位选择列表");
        mListSelectPopWindow.setOnListSelectCallback(new SimpleListSelectPopWindow.OnListSelectDataCallback() {
            @Override
            public void onSelectCallback(String data, int position) {
                mPresenter.updateEducationItem(data, position);
            }
        });
    }


    @Override
    public void bindListData(List<InputMenuBean> beanList) {
        mDataBinding.recyclerView.addItemDecoration(new SpaceItemDecoration(mContext, beanList));
        mAdapter.setupData(beanList);
    }


    @Override
    public RecyclerView getRecyclerView() {
        return mDataBinding.recyclerView;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_info;
    }

    @Override
    protected PersonInfoPresenter createPresenter() {
        return new PersonInfoPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 显示时间选择器的提示框
     */
    @Override
    public void showDateSelectDialog(long dataTime) {
        mCalendar.setTimeInMillis(dataTime);
        mDateDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mPresenter.itemDataChange(year + "-" + (month + 1) + "-" + dayOfMonth);
            }
        }, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));
        mDateDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String imageUrl = mSelectPopWindow.getSelectHelper().onActivityResult(requestCode, resultCode, data, null, false);
            mAdapter.updateAvatar(imageUrl);
            mPresenter.startUploadUserAvatar(imageUrl);
        }
    }

    /**
     * 改变修改的状态
     *
     * @param isChange
     */
    @Override
    public void changeSaveState(boolean isChange) {
        this.isChange = isChange;
        if (isChange) {
            getTitleRightView().setVisibility(View.VISIBLE);
        } else {
            getTitleRightView().setVisibility(View.GONE);
        }
    }

    @Override
    public void finish() {
        if (isChange) {
            DialogUtils.builderAlertDialog(mContext, "温馨提示", "您的数据有更改，是否立即保存")
                    .setNegativeButton("暂不保存", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            isChange = false;
                            finish();
                        }
                    })
                    .setPositiveButton("立即保存", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mPresenter.onClickSave();
                        }
                    }).show();
        } else
            super.finish();
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

}
