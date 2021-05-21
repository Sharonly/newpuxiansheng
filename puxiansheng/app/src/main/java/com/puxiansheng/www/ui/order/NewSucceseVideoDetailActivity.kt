/*
package com.puxiansheng.www.ui.order

import android.content.Intent
import android.content.res.Configuration
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.http.SuccessVideoBean
import com.puxiansheng.util.ext.MyScreenUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.common.player.MyPlayerViews
import com.puxiansheng.www.common.urlCircleImg
import com.puxiansheng.www.tools.SpUtils
import com.puxiansheng.www.tools.UMengKeys
import com.puxiansheng.www.ui.login.LoginActivity
import com.puxiansheng.www.ui.release.InsertOrUpdateTransferOutOrderActivity
import com.umeng.analytics.MobclickAgent
import com.zhiniao.player.media.IjkPlayerViews
import kotlinx.android.synthetic.main.activity_success_video_detail.*
import kotlinx.android.synthetic.main.layout_video.*
import kotlinx.android.synthetic.main.layout_video.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch
import java.lang.Exception


@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class NewSucceseVideoDetailActivity : MyBaseActivity() {

    private lateinit var viewModel: SuccessVideoDetailViewModel
    private var type = 0
    private var fullScreen = true //false竖着全屏，trur横着全屏
    private var videoUrl = ""
    private var isLandscape = false
    private var isVertical = false

    override fun getLayoutId(): Int {
        MyScreenUtil.setStateBarStyle(this,true,R.color.color81,true)
        return R.layout.activity_success_video_detail
    }

    override fun business() {
        viewModel = ViewModelProvider(this)[SuccessVideoDetailViewModel::class.java]
        initView()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            isLandscape = true
//            player_view.onChangeLandscape(true)
            println("横着拍的视频切换为横屏")
            top_content.visibility = View.GONE
            layout_detail.visibility = View.GONE
            bt_publish.visibility = View.GONE
            player_view.layoutParams.height=MyScreenUtil.getScreenHeight(this)
        } else {
            println("横着拍的视频切换为竖屏")
            isLandscape = false
//            player_view.onChangeLandscape(false)
            top_content.visibility = View.VISIBLE
            layout_detail.visibility = View.VISIBLE
            bt_publish.visibility = View.VISIBLE
            player_view.layoutParams.height=MyScreenUtil.dip2px(this,220f)
        }


    }

    private fun initView() {
        var cityId = SpUtils.get(API.USER_CITY_ID, 0)
        button_back.setOnClickListener {
                if (isLandscape) {
                    MyScreenUtil.setLandscape(this, false)
                } else {
                    finish()
                }
        }

        icon_author.urlCircleImg(R.mipmap.ic_author_icon)
        
        layout_detail.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            bt_publish.rollBack()
        }

        bt_publish.setOnClickListener {
            if (SpUtils.get(API.LOGIN_USER_TOKEN, "").toString().isNotEmpty()) {
                val intent =
                    Intent(this, InsertOrUpdateTransferOutOrderActivity::class.java)
                intent.putExtra("shopID", "0")
                startActivity(intent)
            } else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }

        lifecycleScope.launch {
            viewModel.requestSuccessVideoDetail(intent.getStringExtra("shopID"))?.let { video ->
                video_title.text = video.title
                shop_title.text = video.title
                shop_info.text = video.detail
                Log.e("IJKMEDIA SucceseVideoDetailActivity ","111  video url = "+video.video)
               var link = "http://pxs3-img-test.oss-cn-shenzhen.aliyuncs.com/uploads/video/20210408142330/944c4ce22e925e00fb51b425b14524d10dafcc06.mp4"
//                var link ="http://pxs3-img.oss-cn-shenzhen.aliyuncs.com/uploads/video/20210328160314/c096ff6c03b053433417266f65b4633b785ba0d4.mp4"
                initPlayStudyBaUI(video.title, link)
            }


            recommend_video.layoutManager =
                GridLayoutManager(this@NewSucceseVideoDetailActivity, 2)
            recommend_video.adapter = SuccessVideoAdapter(
                this@NewSucceseVideoDetailActivity,
                arrayListOf(),1
            )
            viewModel.requestRecommendShopList(
                cityId.toString(), intent.getStringExtra("shopID")
            )?.let {it ->
                (recommend_video.adapter as SuccessVideoAdapter).addList(it as ArrayList<SuccessVideoBean>, true)
            }

        }

    }

    fun getPlayTime(mUri:String) {
        val mmr =MediaMetadataRetriever();
        try {
            if (mUri != null) {
                var headers =HashMap<String,String>()
                headers?.put("User-Agent", "Mozilla/5.0 (Linux; U; Android 4.4.2; zh-CN; MW-KW-001 Build/JRO03C) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 UCBrowser/1.0.0.001 U4/0.8.0 Mobile Safari/533.1");
                mmr.setDataSource(mUri, headers);
            }
            //    String duration = mmr.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_DURATION);//时长(毫秒)
            val width = mmr.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH);//宽
            val height = mmr.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT);//高

            Log.e("获取视频", width+"___"+height);
            if (width > height) fullScreen=true else fullScreen=false

        } catch (ex:Exception) {
            Log.e("获取视频异常：", "MediaMetadataRetriever exception " + ex);
        } finally {
            mmr.release();
        }

    }

    private fun initPlayStudyBaUI(title: String, link: String) {


        getPlayTime(link)


        player_view.setVideoPath(link).startPlay()
        player_view.listener = object : MyPlayerViews.OnCLickFullScreenListener {
            override fun onClickFullScreenView() {
                if (fullScreen) {
                    println("视频横着拍")
                    MyScreenUtil.setLandscape(this@NewSucceseVideoDetailActivity, !isLandscape)
                } else {

                    if (isVertical) {
                        println("视频竖着拍小屏::${isVertical}")
                        isVertical = false
                        video_rootView.layoutParams.height=MyScreenUtil.dip2px(this@NewSucceseVideoDetailActivity,220f)
                        player_view.layoutParams.height=MyScreenUtil.dip2px(this@NewSucceseVideoDetailActivity,220f)

                        top_content.visibility = View.VISIBLE
                        layout_detail.visibility=View.VISIBLE
                        bt_publish.visibility = View.VISIBLE
                    } else {
                        println("视频竖着拍全屏::${isVertical}")
                        isVertical = true

                        var tempHeight=0
                        if (MyScreenUtil.getStatusBarHeight(this@NewSucceseVideoDetailActivity)>80){
                            tempHeight=MyScreenUtil.getStatusBarHeight(this@NewSucceseVideoDetailActivity)+MyScreenUtil.getScreenHeight(this@NewSucceseVideoDetailActivity)
                        }else{
                            tempHeight=MyScreenUtil.getScreenHeight(this@NewSucceseVideoDetailActivity)
                        }

                        video_rootView.layoutParams.height=tempHeight
                        player_view.layoutParams.height=tempHeight

                        top_content.visibility = View.GONE
                        layout_detail.visibility=View.GONE
                        bt_publish.visibility = View.GONE
                    }
                    player_view.setFullBtImg(isVertical)
                }
            }

            override fun onError(msg: Int) {
                Toast.makeText(this@NewSucceseVideoDetailActivity,"播放异常:${msg}",Toast.LENGTH_SHORT).show()
            }

            override fun onCompletion() {
                player_view.setVideoPath(link).rePlay()
            }
        }



//        player_view.init(this)
//            .setTitle(title)
//            .setVideoPaths(Uri.parse(link))
//            .start()


//        var callBack = MediaPlayer.OnErrorListener { p0, i, p2 ->
//            if (i == -10000) {
//                // finish();
//            }
//            false
//        }
//        player_view.setOnErrorListener(callBack)
//        player_view.alwaysFullScreen()
    }

//    override fun setOnClick(click: Int) {
//        // 0 竖屏 1全屏
//        when (click) {
//            0 -> {
//                if (null != videoUrl) top_content.visibility = View.VISIBLE
//                bottomLayout.visibility = View.VISIBLE
//                MyScreenUtil.setLandscape(this, false)
//                MyScreenUtil.fullscreen(this, false)
//                player_view.layoutParams = LinearLayout.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT, windowManager.defaultDisplay.height / 2
//                )
//                fullScreen = true
//            }
//            1 -> {
//                if (null != videoUrl) {
//                    top_content.visibility = View.GONE
//                    bottomLayout.visibility = View.GONE
//                }
//                MyScreenUtil.setLandscape(this, true)
//                MyScreenUtil.fullscreen(this, true)
//                player_view.layoutParams = LinearLayout.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
//                )
//                fullScreen = true
//            }
//            99 -> if (fullScreen) {
//                top_content.visibility = View.GONE
//                bottomLayout.visibility = View.GONE
//                MyScreenUtil.setLandscape(this, true)
//                MyScreenUtil.fullscreen(this, true)
//                player_view.layoutParams = LinearLayout.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
//                )
//                fullScreen = false
//            } else {
//                top_content.visibility = View.VISIBLE
//                bottomLayout.visibility = View.VISIBLE
//                MyScreenUtil.setLandscape(this, false)
//                MyScreenUtil.fullscreen(this, false)
//                player_view.layoutParams = LinearLayout.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT, windowManager.defaultDisplay.height / 2
//                )
//                fullScreen = true
//            }
//            else -> {
//
//            }
//        }
//    }


//    override fun onResume() {
//        super.onResume()
//        if (player_view != null) {
//            player_view.onResume()
//            player_view.start()
//        }
//    }

//    override fun onPause() {
//        super.onPause()
//        if (player_view != null) {
//            player_view.pause()
//        }
//    }

    override fun onDestroy() {
        super.onDestroy()
        if (player_view != null) {
            player_view.onDestory()
        }
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
//        if (player_view != null) if (player_view.handleVolumeKey(keyCode)) {
//            return true
//        }
//        return super.onKeyDown(keyCode, event)

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isLandscape) {
                MyScreenUtil.setLandscape(this, false)
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

//    override fun onBackPressed() {
//        if (player_view != null) if (player_view.onBackPressed()) {
//            return
//        }
//        super.onBackPressed()
//    }

}*/
