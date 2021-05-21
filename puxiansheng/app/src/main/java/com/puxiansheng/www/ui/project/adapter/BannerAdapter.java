package com.puxiansheng.www.ui.project.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.puxiansheng.www.R;
import com.zhiniao.player.taobao.JZVideoPlayer;
import com.zhiniao.player.taobao.JZVideoPlayerStandard;

import java.util.List;

public class BannerAdapter extends PagerAdapter {
    private Context mContext;
    private List<String> imgUrls;
//    private BannerListener bannerListener;

    public BannerAdapter(Context context) {
        this.mContext = context;
    }

    public void update(List<String> resIds) {
        if (this.imgUrls != null)
            this.imgUrls.clear();
        if (resIds != null)
            this.imgUrls = resIds;
    }

    @Override
    public int getCount() {
        return imgUrls == null ? 0 : Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        String url = imgUrls.get(position % imgUrls.size());
        if (!url.contains(".mp4")) {
            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
//            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(mContext).load(url).into(imageView);
            container.addView(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    bannerListener.bannerClick(position % imgUrls.size());
                }
            });
            return imageView;
        } else {
//            final VideoView videoView = new VideoView(mContext);
//            Uri uri = Uri.parse(url);
//            videoView.setVideoURI(uri);
//            videoView.start();
            JZVideoPlayerStandard jzVideoPlayerStandard = new JZVideoPlayerStandard(mContext);
            jzVideoPlayerStandard.setUp(url, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "");
//        Glide.with(this).load("http://jzvd-pic.nathen.cn/jzvd-pic/1bb2ebbe-140d-4e2e-abd2-9e7e564f71ac.png").into(myJZVideoPlayerStandard.thumbImageView);
            Glide.with(mContext).load(imgUrls.get(0)).error(R.mipmap.img_pxs_defult_big).placeholder(R.mipmap.img_pxs_defult_big)
                    .fallback(R.mipmap.img_pxs_defult_big).into(jzVideoPlayerStandard.thumbImageView);
            JZVideoPlayer.setJzUserAction(null);
            container.addView(jzVideoPlayerStandard);
//            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                @Override
//                public void onCompletion(MediaPlayer mp) {
////                    bannerListener.playEnd();
//                }
//            });
            return jzVideoPlayerStandard;
        }
    }

//    public void setBannerListener(BannerListener bannerListener) {
//        this.bannerListener = bannerListener;
//    }

}
