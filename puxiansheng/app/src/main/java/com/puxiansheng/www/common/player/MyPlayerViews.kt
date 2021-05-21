package com.puxiansheng.www.common.player

import android.content.Context
import android.media.MediaPlayer
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.SeekBar
import androidx.constraintlayout.widget.ConstraintLayout
import com.puxiansheng.util.ext.MyScreenUtil
import com.puxiansheng.www.R
import com.zhiniao.player.media.IjkPlayerViews
import com.zhiniao.player.utils.StringUtils
import kotlinx.android.synthetic.main.activity_success_video_detail.*
import kotlinx.android.synthetic.main.layout_video.view.*
import java.text.SimpleDateFormat
import java.util.*


class MyPlayerViews(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs),
    SeekBar.OnSeekBarChangeListener,
    MediaPlayer.OnPreparedListener,
    MediaPlayer.OnCompletionListener,
    MediaPlayer.OnErrorListener,
    MediaPlayer.OnInfoListener,
    MediaPlayer.OnBufferingUpdateListener,
    View.OnClickListener {

    private val mHandler: Handler = Handler()
    private var isPause = false
    private var showControlView = false  //是否触摸显示控制view
    private var mIsSeeking = false
    private var mSeekPosition= 0

    /**
     * 进度条拖动监听
     */
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//        Log.e("onProgressChanged"," fromUser = "+fromUser)
//        if (!fromUser) {
//            // We're not interested in programmatically generated changes to
//            // the progress bar's position.
//            return
//        }
//        var duration = vv.duration
//        // 计算目标位置
////        Log.e("onProgressChanged", " progress = " + progress + "  duration= " + duration)
//        mTargetPosition = (duration * progress) / MAX_VIDEO_SEEK
//        var deltaTime: Long = ((mTargetPosition - curPosition) / 1000)
//        var desc = ""
//        // 对比当前位置来显示快进或后退
////        Log.e("onProgressChanged", " mTargetPosition = " + mTargetPosition)
//        if (mTargetPosition > curPosition) {
//            desc =
//                StringUtils.generateTime(mTargetPosition.toLong()) + "/" + StringUtils.generateTime(
//                    duration.toLong()
//                ) + "\n" + "+" + deltaTime + "秒";
//        } else {
//            desc =
//                StringUtils.generateTime(mTargetPosition.toLong()) + "/" + StringUtils.generateTime(
//                    duration.toLong()
//                ) + "\n" + deltaTime + "秒";
//        }
////        _setFastForward(desc)
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
        //mIsSeeking = true
        mHandler.removeCallbacks(runnable1)
        //curPosition = vv.currentPosition.toLong()
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        mIsSeeking = true
        //mTargetPosition = seekBar!!.progress
        Log.e("onProgressChanged", "00  mTargetPosition 66-------- = " + seekBar!!.progress)
       // setProgress()
        mSeekPosition = seekBar!!.progress

        println("视频停止拖动时进度-->${seekBar!!.progress}-->${vv.currentPosition}")
        mHandler.postDelayed(runnable1, 1000)

    }


    /**
     * 播放器开始，完成或者异常监听
     */
    override fun onPrepared(mp: MediaPlayer?) {
        println("PlayerView--->onPrepared}")
        isPause = false
        showControlView = true
        mp?.setOnBufferingUpdateListener(this)
        pb?.visibility = View.INVISIBLE
        iv_play.visibility = View.INVISIBLE
        iv_play.setImageResource(R.mipmap.ic_video_pause)
        seekbar?.max = mp!!.duration;
        tv_total_duration?.text = "${time(mp?.duration!!.toLong())}"
        mHandler.postDelayed(runnable1, 0)
    }

    override fun onCompletion(mp: MediaPlayer?) {
        isPause = true
        showControlView = false
        removeCallbacks(runnable2)
        seekbar?.progress = mp!!.duration
        iv_preview.visibility = View.VISIBLE
        iv_play.visibility = View.VISIBLE
        iv_play.setImageResource(R.mipmap.ic_video_play)
        ll_video_control.visibility = View.VISIBLE
        listener?.onCompletion()
    }

    override fun onError(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
        isPause = true
        showControlView = false
        removeCallbacks(runnable2)
        iv_preview.visibility = View.VISIBLE
        iv_play.visibility = View.VISIBLE
        iv_play.setImageResource(R.mipmap.ic_video_play)
        ll_video_control.visibility = View.VISIBLE
        listener?.onError(extra)
        return true
    }


    override fun onBufferingUpdate(mp: MediaPlayer?, percent: Int) {
        val progress = (mp?.getDuration() ?: 0) * (percent / 100.0)
        seekbar?.secondaryProgress = progress.toInt()
    }


    /**
     * 缓冲监听
     */
    override fun onInfo(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
        if (what == MediaPlayer.MEDIA_INFO_BUFFERING_START) {
            println("PlayerView--->MEDIA_INFO_BUFFERING_START")
        } else {
            println("PlayerView--->非MEDIA_INFO_BUFFERING_START")
        }
        return true
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_play -> {
                if (isPause) {
                    startPlay()
                    isPause = false
                    showControlView = true
                    pb?.visibility = View.INVISIBLE
                    mHandler.postDelayed(runnable1, 0)
                    postDelayed(runnable2, 5000)
                    iv_play.setImageResource(R.mipmap.ic_video_pause)
                } else {
                    if (vv!!.isPlaying) {
                        vv?.pause()
                        isPause = true
                        pb?.visibility = View.INVISIBLE
                        mHandler.removeCallbacks(runnable1)
                        iv_play.setImageResource(R.mipmap.ic_video_play)
                    }
                }
            }

            R.id.iv_fullscreen -> {
                //  if (isNotFastClick){
                listener?.onClickFullScreenView()
                //  }
            }

            R.id.iv_back -> {
                listener?.onClickFullScreenView()
            }
        }
    }

   // private var currentPosi=0
   var currentPosition = 0
    private val runnable1 = object : Runnable {
        override fun run() {
            if (vv!!.isPlaying) {
               println("VideoView获取当前播放进度-->${vv?.currentPosition}")
                if(mIsSeeking){
                    vv?.seekTo(mSeekPosition)
                    currentPosition = mSeekPosition
                    mIsSeeking = false
                }else{
                    currentPosition= vv?.currentPosition ?: 0
                }
               seekbar?.progress = currentPosition
               tv_current_duration?.text = time(currentPosition.toLong())
                Log.e("onProgressChanged"," 11 currentPosition = "+currentPosition +"  11  tv_current_duration. = "+ tv_current_duration.text)
               mHandler.postDelayed(this, 1000)
            }
        }
    }

    private val runnable2 = object : Runnable {
        override fun run() {
            iv_play.visibility = View.INVISIBLE
//            ll_video_control.visibility= View.INVISIBLE
        }
    }


    init {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_video, this, false)
        addView(view)
        seekbar?.setOnSeekBarChangeListener(this)
        vv.setOnPreparedListener(this)
        vv.setOnCompletionListener(this)
        vv.setOnErrorListener(this)
        vv.setOnInfoListener(this)
        iv_back.setOnClickListener(this)
        iv_play.setOnClickListener(this)
        iv_fullscreen.setOnClickListener(this)

        onChangeLandscape(false)
    }


    fun setVideoPath(path: String): MyPlayerViews {
        vv.setVideoPath(path)
        return this
    }


    /**
     * 开始播放
     */
    fun startPlay() {
//        ll_video_control.visibility= View.INVISIBLE
        iv_play.visibility = View.INVISIBLE
        pb?.visibility = View.VISIBLE
        iv_preview.visibility = View.GONE
        vv.start()
    }


    fun rePlay() {
        iv_play.visibility = View.INVISIBLE
        iv_preview.visibility = View.GONE
        vv.start()
    }


    /**
     * 转换成播放时间
     */
    fun time(millionSeconds: Long): String? {
        val simpleDateFormat = SimpleDateFormat("mm:ss")
        val c: Calendar = Calendar.getInstance()
        c.timeInMillis = millionSeconds
        return simpleDateFormat.format(c.getTime())
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                if (showControlView) {
                    removeCallbacks(runnable2)
                    if (iv_play.visibility != View.VISIBLE) {
                        iv_play.visibility = View.VISIBLE
                    }

                    if (ll_video_control.visibility != View.VISIBLE) {
                        ll_video_control.visibility = View.VISIBLE
                    }
                }
            }
            MotionEvent.ACTION_CANCEL,
            MotionEvent.ACTION_UP -> {
                if (showControlView) {
                    postDelayed(runnable2, 5000)
                }
            }
        }
        return true
    }

    /**
     * 销毁
     */
    fun onDestory() {
        if (vv.isPlaying()) {
            vv.stopPlayback()
        }
        mHandler.removeCallbacks(runnable1)
        removeCallbacks(runnable2)
    }


    /**
     * 屏幕旋转切换横竖屏
     */
    fun onChangeLandscape(isLandscape: Boolean) {
        if (isLandscape) {
            video_rootView.layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
            vv.layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
            )
            iv_fullscreen.setImageResource(R.mipmap.ic_fullscreen_exit)
            iv_back.visibility = View.VISIBLE
        } else {
            video_rootView.layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                MyScreenUtil.dip2px(context, 220f)
            )

            val lp = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
            )
            lp.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID
            lp.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID
            vv.layoutParams = lp
            iv_fullscreen.setImageResource(R.mipmap.ic_fullscreen)
            iv_back.visibility = View.GONE
        }
    }


    fun setFullBtImg(isFull: Boolean) {
        if (isFull) {
            iv_fullscreen.setImageResource(R.mipmap.ic_fullscreen_exit)
            iv_back.visibility = View.VISIBLE
        } else {
            iv_fullscreen.setImageResource(R.mipmap.ic_fullscreen)
            iv_back.visibility = View.GONE
        }
    }



    var listener: OnCLickFullScreenListener? = null

    interface OnCLickFullScreenListener {
        fun onClickFullScreenView()
        fun onError(msg: Int)
        fun onCompletion()
    }


//    private fun setProgress(): Int {
//        if (vv == null || mIsSeeking) {
//            return 0
//        }
//        // 视频播放的当前进度
//        val position: Int = Math.max(vv.currentPosition, mInterruptPosition)
//        // 视频总的时长
//        val duration: Int = vv.duration
//        Log.e("onProgressChanged","22  vv.currentPosition = "+vv.currentPosition+" mInterruptPosition = "+mInterruptPosition)
////        if (duration > 0) {
////            // 转换为 Seek 显示的进度值
////            val pos = 100 * position / duration
////            seekbar.progress = position
////            Log.e("onProgressChanged","22  seekbar.progress = "+ seekbar.progress)
////        }
//        // 获取缓冲的进度百分比，并显示在 Seek 的次进度
//        //seekbar的位置移到需要的地方
//        seekbar.progress = position
//        val percent: Int = vv.bufferPercentage
//        seekbar.secondaryProgress = percent * 10
//
//
////        if (mIsEnableDanmaku) {
////            mDanmakuPlayerSeek.setSecondaryProgress(percent * 10)
////        }
//        // 更新播放时间
////        tv_current_duration.text = StringUtils.generateTime(position.toLong())
//        tv_current_duration.text =  time(position.toLong())
//        Log.e("onProgressChanged","22  tv_current_duration. = "+ tv_current_duration.text)
//        // 返回当前播放进度
//        return position
//    }


}