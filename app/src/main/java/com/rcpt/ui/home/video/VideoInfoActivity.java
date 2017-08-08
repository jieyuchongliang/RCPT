package com.rcpt.ui.home.video;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.WindowManager;

import com.lecloud.sdk.constant.PlayerEvent;
import com.lecloud.sdk.constant.PlayerParams;
import com.lecloud.sdk.constant.StatusCode;
import com.lecloud.sdk.videoview.IMediaDataVideoView;
import com.lecloud.sdk.videoview.VideoViewListener;
import com.lecloud.skin.videoview.vod.UIVodVideoView;
import com.rcpt.LoginHelper;
import com.rcpt.R;
import com.rcpt.base.adapter.FragmentAdapter;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.bean.VideoInfoBaseBean;
import com.rcpt.bean.VideoInfoBean;
import com.rcpt.databinding.ActivityVideoInfoBinding;
import com.rcpt.mvp.contract.VideoInfoContract;
import com.rcpt.mvp.presenter.VideoInfoPresenter;
import com.rcpt.ui.login.LoginActivity;
import com.rcpt.utils.VideoLayoutParams;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class VideoInfoActivity extends BaseActivity<ActivityVideoInfoBinding, VideoInfoContract.View, VideoInfoPresenter>
        implements VideoInfoContract.View, VideoInfoCatalogFragment.OnClickVideoCatalogItemCallback, VideoInfoBasicFragment.OnDataLoadCallback {

    private static final String CLASS_TYPE_ID = "type_id";
    private static final String COURSE_TYPE_NAME = "course_type_name";
    private static final String COURSE_TYPE_ID = "course_type_id";
    private static final String TEACHING_STYLE = "teaching_style";

    private IMediaDataVideoView videoView;

    LinkedHashMap<String, String> rateMap = new LinkedHashMap<String, String>();
    VideoViewListener mVideoViewListener = new VideoViewListener() {


        @Override
        public void onStateResult(int event, Bundle bundle) {
            handleVideoInfoEvent(event, bundle);// 处理视频信息事件
            handlePlayerEvent(event, bundle);// 处理播放器事件
            handleLiveEvent(event, bundle);// 处理直播类事件,如果是点播，则这些事件不会回调

        }

        @Override
        public String onGetVideoRateList(LinkedHashMap<String, String> map) {
            rateMap = map;
            for (Map.Entry<String, String> rates : map.entrySet()) {
                if (rates.getValue().equals("高清")) {
                    return rates.getKey();
                }
            }
            return "";
        }
    };

    private long beginTime;

    /**
     * @param context
     * @param classTypeId    课程id
     * @param courseTypeName 课程分类型名称
     * @param courseTypeId   课程分类id
     * @param teachingStyle  授课类型名称
     */
    public static void actionStart(Context context, String classTypeId, String courseTypeName, String courseTypeId, String teachingStyle) {
        Intent intent = new Intent(context, VideoInfoActivity.class);
        intent.putExtra(CLASS_TYPE_ID, classTypeId);
        intent.putExtra(COURSE_TYPE_NAME, courseTypeName);
        intent.putExtra(COURSE_TYPE_ID, courseTypeId);
        intent.putExtra(TEACHING_STYLE, teachingStyle);
        /*// 这里指定了共享的视图元素
        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation((Activity) context, transitionView, "image");
        ActivityCompat.startActivity(context, intent, options.toBundle());*/
        context.startActivity(intent);
    }

    private FragmentAdapter mFragmentAdapter;
    private boolean isInitVideo;//判断是否初始化完成视频控件
    public VideoInfoBaseBean mVideoInfoBaseBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 设置窗口透明，可避免播放器SurfaceView初始化时的黑屏现象
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        // 视频播放界面，保持屏幕常亮利于视频观看体验
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState);
    }

    /**
     * 未实现的方法
     */
    @Override
    protected void setupTitle() {

    }

    @Override
    protected void initFieldBeforeMethods() {
        beginTime = System.currentTimeMillis();
        isUseDefaultTitle = false;
        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
    }

    @Override
    protected void initViews() {
        mDataBinding.setPresenter(mPresenter);
       /* //设置默认显示的图片
        mDataBinding.setImageUrl(getIntent().getStringExtra(IMAGE_URL));*/
        mDataBinding.viewPager.setAdapter(mFragmentAdapter);
        mDataBinding.tabLayout.setupWithViewPager(mDataBinding.viewPager);
        mPresenter.loadPageData();
       /* // 这里指定了被共享的视图元素
        ViewCompat.setTransitionName(mDataBinding.ivLogo, "image");*/
    }

    @Override
    public void bindPageFragment(List<Fragment> fragmentList, List<String> titleList) {
        mFragmentAdapter.addFragmentWithTitle(fragmentList, titleList);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_info;
    }

    /**
     * 获取课程类型id
     *
     * @return
     */
    @Override
    public String getCourseTypeId() {
        return getIntent().getStringExtra(COURSE_TYPE_ID);
    }

    /**
     * 获取课程类型名称
     *
     * @return
     */
    @Override
    public String getCourseTypeName() {
        return getIntent().getStringExtra(COURSE_TYPE_NAME);
    }

    /**
     * 获取授课类型
     *
     * @return
     */
    @Override
    public String getTeachingStyle() {
        return getIntent().getStringExtra(TEACHING_STYLE);
    }

    @Override
    public String getClassTypeId() {
        return getIntent().getStringExtra(CLASS_TYPE_ID);
    }

    @Override
    protected VideoInfoPresenter createPresenter() {
        return new VideoInfoPresenter();
    }

    public void pageGone(){
        mDataBinding.llParent.setVisibility(View.GONE);
    }

    /**
     * 当详情信息成功获取后的回调方法
     *
     * @param baseBean
     */
    @Override
    public void onDataCallback(VideoInfoBaseBean baseBean) {
        mDataBinding.llParent.setVisibility(View.VISIBLE);
        mVideoInfoBaseBean = baseBean;
        mDataBinding.setBean(mVideoInfoBaseBean);
        mDataBinding.setImageUrl(mVideoInfoBaseBean.getCover());
    }

    @Override
    public VideoInfoBaseBean getVideoInfoBaseBean() {
        return mVideoInfoBaseBean;
    }

    /**
     * 当课程目录的Item被点击时的回调接口
     *
     * @param infoBean 视频信息的实体类
     */
    @Override
    public void onVideoCatalogItemClick(VideoInfoBean infoBean) {
        if (!isInitVideo) {
            mDataBinding.rlInitVideo.setVisibility(View.GONE);

            videoView = new UIVodVideoView(this);
            videoView.setVideoViewListener(mVideoViewListener);

            mDataBinding.rlPlayParent.addView((View) videoView, VideoLayoutParams.computeContainerSize(this, 16, 9));
            mDataBinding.rlPlayParent.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mDataBinding.rlPlayParent.setFitsSystemWindows(true);
                    mDataBinding.rlPlayParent.invalidate();
                }
            }, 100);
            isInitVideo = true;

        }
        videoView.resetPlayer();
        if (videoView instanceof UIVodVideoView) {
            ((UIVodVideoView) videoView).setNeedbuy(infoBean.getNeedBuy());
            ((UIVodVideoView) videoView).setTryLookTime(infoBean.getTryLookTime());
        }
        Bundle bundle = new Bundle();
        // 配置播放类型为 点播
        bundle.putInt(PlayerParams.KEY_PLAY_MODE, PlayerParams.VALUE_PLAYER_VOD);
        bundle.putString(PlayerParams.KEY_PLAY_UUID, infoBean.getUuId());
        bundle.putString(PlayerParams.KEY_PLAY_VUID, infoBean.getVuId());
        bundle.putString(PlayerParams.KEY_CUID, infoBean.getCuid());
        bundle.putString(PlayerParams.KEY_UTOKEN, infoBean.getUtoken());
        videoView.setDataSource(bundle);
       /* videoView.setDataSource("http://112.253.22.164/2/w/d/p/q/wdpqdxzxjsfeqcumrazrpjxcortvnt/" +
                "hc.yinyuetai.com/C452015B5725DB7D03D0AE6C13A5C136.mp4?sc=a9d3e400d50ea14c");*/
    }


 /*   @Override
    protected void onResume() {
        super.onResume();
        if (videoView != null) {
            videoView.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (videoView != null) {
            videoView.onPause();
        }

    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoView != null) {
            videoView.onDestroy();
        }
    }

    @Override
    public void onBackClick() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (videoView != null) {
            videoView.onConfigurationChanged(newConfig);
        }
    }

    /**
     * 处理播放器本身事件，具体事件可以参见IPlayer类
     */
    private void handlePlayerEvent(int state, Bundle bundle) {
        switch (state) {
            case PlayerEvent.PLAY_VIDEOSIZE_CHANGED:
                /**
                 * 获取到视频的宽高的时候，此时可以通过视频的宽高计算出比例，进而设置视频view的显示大小。
                 * 如果不按照视频的比例进行显示的话，(以surfaceView为例子)内容会填充整个surfaceView。
                 * 意味着你的surfaceView显示的内容有可能是拉伸的
                 */
                break;

            case PlayerEvent.PLAY_PREPARED:
                // 播放器准备完成，此刻调用start()就可以进行播放了
                if (videoView != null) {
                    videoView.onStart();
                }
                break;
            case PlayerEvent.PLAY_INFO:
                int code = bundle.getInt(PlayerParams.KEY_RESULT_STATUS_CODE);
                if (code == StatusCode.PLAY_INFO_VIDEO_RENDERING_START) {
                    long startPlayTime = (System.currentTimeMillis() - beginTime);
                    float num = (float) startPlayTime / 1000;
                   /* if (timeText != null) {
                        timeText.setText("起播耗时：" + num + "秒");
                    }*/
                }
                break;
            default:
                break;
        }
    }

    /**
     * 处理直播类事件
     */
    private void handleLiveEvent(int state, Bundle bundle) {
    }

    /**
     * 处理视频信息类事件
     */
    private void handleVideoInfoEvent(int state, Bundle bundle) {
    }

}
