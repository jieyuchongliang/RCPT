package com.rcpt.adapter;

import android.databinding.ViewDataBinding;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;

import com.rcpt.BR;
import com.rcpt.Constant;
import com.rcpt.R;
import com.rcpt.base.adapter.BindingBaseRecycleAdapter;
import com.rcpt.base.adapter.BindingViewHolder;
import com.rcpt.base.adapter.SimpleBindingAdapter;
import com.rcpt.bean.TestQuestionInfoBean;
import com.rcpt.databinding.ItemLayoutTestQuestionInfoBinding;
import com.rcpt.widget.NestRecyclerView;

/**
 * Created by lvzp on 2017/4/6.
 * 类描述：
 */

public class TestMajorQuestionAdapter extends BindingBaseRecycleAdapter<TestQuestionInfoBean.ContentBean, ItemLayoutTestQuestionInfoBinding> {

    private String mTestType;

    public TestMajorQuestionAdapter(String testType) {
        super(R.layout.item_layout_test_question_info);
        mTestType = testType;
    }

    @Override
    protected void bindingViews(BindingViewHolder<ItemLayoutTestQuestionInfoBinding> holder, int position, TestQuestionInfoBean.ContentBean contentBean) {

        final TestQuestionInfoBean.ContentBean item = getItem(position);
        if (mTestType.equals(Constant.TEST_TYPE_MAJOR)) {
            String title = (position + 1) + "、【" + Constant.getTestQuestionType(item.getQuestionTypeId()) + "】" + item.getTitle() + "（ " + item.getQuestionPoint() + " ）分";
            int scoreStartIndex = title.lastIndexOf("（");
            int scoreLastIndex = title.lastIndexOf("）");
            SpannableString formatString = new SpannableString(title);
            formatString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(holder.getBinding().getRoot().getContext(), R.color.colorBlue)), scoreStartIndex + 1, scoreLastIndex, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            holder.getBinding().setTitleName(formatString);
        } else {
            holder.getBinding().setTitleName((position + 1) + "、" + item.getTitle());
        }
        holder.getBinding().setQuestionInfo(item);
        //设置答案的列表
        SimpleBindingAdapter<TestQuestionInfoBean.ContentBean.AppChoiceListBean> answerAdapter = new SimpleBindingAdapter<TestQuestionInfoBean.ContentBean.AppChoiceListBean>(R.layout.item_layout_test_answer_info, BR.answerBean) {
            @Override
            protected void bindingViews(final BindingViewHolder<ViewDataBinding> holder, int position, TestQuestionInfoBean.ContentBean.AppChoiceListBean appChoiceListBean) {
                super.bindingViews(holder, position, appChoiceListBean);
                if (item.getAppChoiceImgList() != null && item.getAppChoiceImgList().size() > position) {
                    TestQuestionInfoBean.ContentBean.AppChoiceListBean imageBean = item.getAppChoiceImgList().get(position);
                    holder.getBinding().setVariable(BR.imageUrl, imageBean.getValue());
                    holder.getBinding().executePendingBindings();
                }
                holder.getBinding().getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int layoutPosition = holder.getLayoutPosition();
                        for (int i = 0; i < getItemCount(); i++) {
                            TestQuestionInfoBean.ContentBean.AppChoiceListBean bean = getItem(i);
                            if (bean.isSelect()) {
                                bean.setSelect(false);
                                notifyItemChanged(i);
                                break;
                            }
                        }
                        TestQuestionInfoBean.ContentBean.AppChoiceListBean bean = getItem(layoutPosition);
                        bean.setSelect(true);
                        notifyItemChanged(layoutPosition);
                    }
                });
            }
        };
        ItemLayoutTestQuestionInfoBinding binding = holder.getBinding();
        NestRecyclerView answerRecyclerView = binding.answerRecyclerView;
        answerRecyclerView.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        answerRecyclerView.setAdapter(answerAdapter);
        answerAdapter.setupData(item.getAppChoiceList());
    }
}
