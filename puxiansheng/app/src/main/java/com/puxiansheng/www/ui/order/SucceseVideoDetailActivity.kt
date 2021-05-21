package com.puxiansheng.www.ui.order

import android.content.Intent
import android.content.res.Configuration
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.http.SuccessVideoBean
import com.puxiansheng.util.ext.MyScreenUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.common.urlCircleImg
import com.puxiansheng.www.tools.SpUtils
import com.puxiansheng.www.ui.login.LoginActivity
import com.puxiansheng.www.ui.release.InsertOrUpdateTransferOutOrderActivity
import com.zhiniao.player.media.IjkPlayerViews
import kotlinx.android.synthetic.main.activity_exo_success_video_detail.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class SucceseVideoDetailActivity : MyBaseActivity(), IjkPlayerViews.OnPlayViewClick {

    private lateinit var viewModel: SuccessVideoDetailViewModel
    private var type = 0
    private var fullScreen = true
    private var videoUrl = ""
    private var isLandscape = false

    override fun getLayoutId(): Int {
        MyScreenUtil.setStateBarStyle(this, true, R.color.color81, true)
        return R.layout.activity_exo_success_video_detail
    }

    override fun business() {
        viewModel = ViewModelProvider(this)[SuccessVideoDetailViewModel::class.java]
        initView()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (player_view != null) player_view.configurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            isLandscape = true
            top_content.visibility = View.GONE
            bottomLayout.visibility = View.GONE
            bt_publish.visibility = View.GONE
        } else {
            isLandscape = false
            top_content.visibility = View.VISIBLE
            bottomLayout.visibility = View.VISIBLE
            bt_publish.visibility = View.VISIBLE
        }
    }

    private fun initView() {
        var cityId = SpUtils.get(API.USER_CITY_ID, 0)
        button_back.setOnClickListener {
            onBackPressed()
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
                initPlayStudyBaUI(video.title,video.video)
            }


            recommend_video.layoutManager =
                GridLayoutManager(this@SucceseVideoDetailActivity, 2)
            recommend_video.adapter = SuccessVideoAdapter(
                this@SucceseVideoDetailActivity,
                arrayListOf(), 1
            )
            viewModel.requestRecommendShopList(
                cityId.toString(), intent.getStringExtra("shopID")
            )?.let { it ->
                (recommend_video.adapter as SuccessVideoAdapter).addList(
                    it as ArrayList<SuccessVideoBean>,
                    true
                )
            }

        }

    }

    private fun initPlayStudyBaUI(title: String, link: String) {
        player_view.init(this)
            .setTitle(title)
            .setVideoPaths(Uri.parse(link))
            .start()
    }

    override fun setOnClick(click: Int) {
        // 0 竖屏 1全屏
        when (click) {
            0 -> {
                if (null != videoUrl) top_content.visibility = View.VISIBLE
                bottomLayout.visibility = View.VISIBLE
                MyScreenUtil.setLandscape(this, false)
                MyScreenUtil.fullscreen(this, false)
                player_view.layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, windowManager.defaultDisplay.height / 2
                )
                fullScreen = true
            }
            1 -> {
                if (null != videoUrl) {
                    top_content.visibility = View.GONE
                    bottomLayout.visibility = View.GONE
                }
                MyScreenUtil.setLandscape(this, true)
                MyScreenUtil.fullscreen(this, true)
                player_view.layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
                )
                fullScreen = true
            }
            99 -> if (fullScreen) {
                top_content.visibility = View.GONE
                bottomLayout.visibility = View.GONE
                MyScreenUtil.setLandscape(this, true)
                MyScreenUtil.fullscreen(this, true)
                player_view.layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
                )
                fullScreen = false
            } else {
                top_content.visibility = View.VISIBLE
                bottomLayout.visibility = View.VISIBLE
                MyScreenUtil.setLandscape(this, false)
                MyScreenUtil.fullscreen(this, false)
                player_view.layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, windowManager.defaultDisplay.height / 2
                )
                fullScreen = true
            }
            else -> {

            }
        }
    }


    override fun onResume() {
        super.onResume()
        if (player_view != null) {
            player_view.onResume()
            player_view.start()
        }
    }

    override fun onPause() {
        super.onPause()
        if (player_view != null) {
            player_view.pause()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (player_view != null) {
            player_view.onDestroy()
        }
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (player_view != null) if (player_view.handleVolumeKey(keyCode)) {
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onBackPressed() {
        if (player_view != null) if (player_view.onBackPressed()) {
            return
        }
        super.onBackPressed()
    }



}