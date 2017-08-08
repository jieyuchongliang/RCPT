package com.rcpt.widget;

import android.app.Activity;
import android.os.Build;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rcpt.R;
import com.rcpt.utils.InputMethodUtils;

/**
 * Created by lvzp on 2017/4/20.
 * 类描述：
 */

public class SearchTitlePopWindow extends TitleBasePopWindow {

    private final EditText mEditInputSearch;
    private LinearLayout mSearchLayout;
    private OnClickSearchListener mSearchListener;

    /**
     * 初始化一个PopupWindow
     *
     * @param context 上下文对象
     */
    public SearchTitlePopWindow(Activity context) {
        super(context, R.layout.layout_pop_search_data);
        mSearchLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.include_layout_search_dialog_title, null);
        mEditInputSearch = (EditText) mSearchLayout.findViewById(R.id.edit_input);
        initTitleLayout();
        init();
        mRightView.setButtonDrawable(R.drawable.ic_vector_search);
        mEditInputSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    closePopupWindow();
                    if (mSearchListener != null)
                        mSearchListener.onClickSearch(mEditInputSearch.getText().toString().trim());
                }
                return false;
            }
        });
        mRightView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
                if (mSearchListener != null) {
                    mSearchListener.onClickSearch(mEditInputSearch.getText().toString().trim());
                }
            }
        });
        getContentView().findViewById(R.id.empty_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
            }
        });
    }

    /**
     * 设置将搜索框放置在左右控件的中间
     */
    private void init() {
        //获取CenterView的布局参数
        RelativeLayout.LayoutParams layoutParams = null;
        //判断如果它的布局参数不为空的时候，就直接取出赋值,如果为空就新建一个
        if (contentView.getLayoutParams() != null) {
            layoutParams = (RelativeLayout.LayoutParams) contentView.getLayoutParams();
        } else {
            layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        layoutParams.leftMargin = 10;
        layoutParams.rightMargin = 10;
        //设置CenterView在布局中需要依附到哪些控件的旁边
        layoutParams.addRule(RelativeLayout.LEFT_OF, mRightView.getId());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            layoutParams.addRule(RelativeLayout.START_OF, mRightView.getId());
        }
        layoutParams.addRule(RelativeLayout.RIGHT_OF, leftView.getId());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            layoutParams.addRule(RelativeLayout.END_OF, leftView.getId());
        }
    }

    @Override
    public View getAddContentView() {
        return mSearchLayout;
    }

    public void setOnClickSearchListener(OnClickSearchListener listener) {
        mSearchListener = listener;
    }

    public void showPopupWindow(String inputSearch, View parent) {
        super.showPopupWindow(parent);
        mEditInputSearch.setText("");
        if (!TextUtils.isEmpty(inputSearch)) {
            mEditInputSearch.setHint(inputSearch);
            return;
        }
        InputMethodUtils.openInputMethod(parent.getContext());
    }

    public interface OnClickSearchListener {
        /**
         * 点击搜索按钮后进行的接口回调
         *
         * @param searchText 搜索框中搜索的内容
         */
        void onClickSearch(String searchText);
    }

}
