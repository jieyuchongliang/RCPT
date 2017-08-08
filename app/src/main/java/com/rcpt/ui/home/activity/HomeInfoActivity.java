package com.rcpt.ui.home.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;

import com.rcpt.R;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.bean.HomeInfoBean;
import com.rcpt.databinding.ActivityHomeInfoBinding;
import com.rcpt.mvp.contract.HomeInfoContract;
import com.rcpt.mvp.presenter.HomeInfoPresenter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 列表界面的详情页
 * 包含（政策法规的详情页咨询服务的详情页和新闻的详情页）
 */
public class HomeInfoActivity extends BaseActivity<ActivityHomeInfoBinding, HomeInfoContract.View, HomeInfoPresenter>
        implements HomeInfoContract.View {

    private static final String INFO_TYPE = "info";
    private static final String ID_TAG = "id";

    private String mId;
    private String mInfoType;

    /**
     * @param context
     * @param id
     * @param type
     */
    public static void actionStart(Context context, String id, @HomeInfoContract.HomeInfoType String type) {
        Intent intent = new Intent(context, HomeInfoActivity.class);
        intent.putExtra(ID_TAG, id);
        intent.putExtra(INFO_TYPE, type);
        context.startActivity(intent);
    }

    @Override
    protected void setupTitle() {
        openTitleLeftView(true);
    }

    @Override
    protected void initFieldBeforeMethods() {
        super.initFieldBeforeMethods();
        Intent intent = getIntent();
        mId = intent.getStringExtra(ID_TAG);
        mInfoType = intent.getStringExtra(INFO_TYPE);
        mDataBinding.setInfoType(mInfoType);
    }

    @Override
    public String getId() {
        return mId;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void initViews() {
        mPresenter.loadInfo(mInfoType);
        mDataBinding.setHomeInfoPresenter(mPresenter);
//        mDataBinding.dashedLine1.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        mDataBinding.dashedLine2.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        mDataBinding.dashedLine3.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        WebSettings webSettings = mDataBinding.webView.getSettings();
        // 支持缩放(适配到当前屏幕)
        webSettings.setSupportZoom(true);
        webSettings.setJavaScriptEnabled(true);
        // webSettings.setLayoutAlgorithm(LayoutAlgorithn.NARROW_COLUMNS);//适应内容大小
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_info;
    }

    @Override
    protected HomeInfoPresenter createPresenter() {
        return new HomeInfoPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void updateInfo(HomeInfoBean bean) {
        mDataBinding.setInfoBean(bean);
        mDataBinding.webView.loadDataWithBaseURL("", getNewContent(bean.getContent()), "text/html", "utf-8", "");
    }


    private String getNewContent(String htmltext) {
        Document doc = Jsoup.parse(htmltext);
        Elements elements = doc.getElementsByTag("img");
        for (Element element : elements) {
            //element.attr("width", "80%").attr("height", "auto");
            element.parent().attr("style","text-align:center");
            element.attr("style", "max-width:90%;height:auto;");
        }
        return doc.toString();
    }
}
