package com.puxiansheng.www.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.util.Util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Hashtable;


/**
 * Created by Administrator on 2018/9/11.
 */

public class RemoveViewHolder extends RecyclerView.ViewHolder {

    private Context mContext;

    public RemoveViewHolder(Context context, View itemView) {
        super(itemView);
        this.mContext = context;
    }

    //通过ID获取控件
    public <T extends View> T getView(int viewId) {
        View view = itemView.findViewById(viewId);
        return (T) view;
    }

    public RemoveViewHolder setImageRes(int viewId, int imgRes) {
        if (Util.isOnMainThread()) {
            ImageView view = getView(viewId);
//            Glide.with(mContext).load(imgRes)
//                    .apply(AppConfig.ListFit())
//                    .into(view);
        }
        return this;
    }

    public RemoveViewHolder setImageUrl(int viewId, String imageUrl) {
        ImageView view = getView(viewId);
        if (Util.isOnMainThread()) {
//            Glide.with(mContext).load(imageUrl)
//                    .apply(AppConfig.ListCent())
//                    .into(view);
        }
        return this;
    }

    public RemoveViewHolder setImageUrlfxy(int viewId, String imageUrl) {
        ImageView view = getView(viewId);
        if (Util.isOnMainThread()) {
//            Glide.with(mContext).load(imageUrl)
//                    .apply(AppConfig.ListfitXy())
//                    .into(view);
        }
        return this;
    }


    public RemoveViewHolder setImageUrlGiffxy(int viewId, String imageUrl) {
        final ImageView view = getView(viewId);
        if (Util.isOnMainThread()) {
            Glide.with(mContext).load(imageUrl)
                    .into(new SimpleTarget<Drawable>() {
                        @Override
                        public void onResourceReady(@NonNull Drawable drawable, @Nullable Transition<? super Drawable> transition) {
                            if (drawable instanceof GifDrawable) {
                                GifDrawable gifDrawable = (GifDrawable) drawable;
                                gifDrawable.setLoopCount(2);
                                view.setImageDrawable(drawable);
                                gifDrawable.start();
                            }
                        }
                    });
        }
        return this;
    }

//    public RemoveViewHolder setImageUrlErroC(int viewId, String imageUrl, int drawid) {
//        ImageView view = getView(viewId);
//        if (Util.isOnMainThread()) {
//            Glide.with(mContext).load(imageUrl)
//                    .apply(AppConfig.ListCent())
//                    .into(view);
//        }
//        return this;
//    }
//
//    public RemoveViewHolder setImageUrlErroF(int viewId, String imageUrl, int drawid) {
//        if (Util.isOnMainThread()) {
//            ImageView view = getView(viewId);//loadVideoScreenshot
//            Glide.with(mContext).load(imageUrl)
//                    .apply(AppConfig.ListFit())
//                    .into(view);
//        }
//        return this;
//    }

    public RemoveViewHolder loadvideo(int viewId, String imageUrl) {
        ImageView view = getView(viewId);//loadVideoScreenshot loadvideoImage loadvideo
        loadVideoScreenshot(imageUrl, view, MediaStore.Images.Thumbnails.MINI_KIND);
        return this;
    }

    public RemoveViewHolder loadvideoImage(int viewId, final String imageUrl) {
        if (Util.isOnMainThread()) {
            final ImageView view = getView(viewId);//loadVideoScreenshot
            // view.setTag(getLayoutPosition());
            final Handler handler = new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    Bundle bundle = msg.getData();
                    Bitmap bitmap = bundle.getParcelable("bitmap");
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    Glide.with(mContext).load(baos.toByteArray()).into(view);
//                    if (getLayoutPosition() == (Integer) view.getTag())
//                    view.setImageBitmap(bitmap);
                    return false;
                }
            });

            Thread loadimgThread = new Thread(new Runnable() {
                boolean falg = true;

                @Override
                public void run() {
                    while (falg) {
                        Bitmap bitmap = createVideoThumbnail(imageUrl, MediaStore.Images.Thumbnails.MINI_KIND);
                        Message message = new Message();
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("bitmap", bitmap);
                        message.setData(bundle);
                        handler.sendMessage(message);
                        falg = false;
                    }
                    //handler.send
                }
            });
            loadimgThread.start();

            //loadVideoScreenshot(imageUrl, view, 1);
        }
        return this;
    }

    public static Bitmap getVideoThumnail(String videoPath, long timeUs) {
        MediaMetadataRetriever media = new MediaMetadataRetriever();
        media.setDataSource(videoPath);
        // 获取第一个关键帧
        // OPTION_CLOSEST 在给定的时间，检索最近一个帧，这个帧不一定是关键帧。
        // OPTION_CLOSEST_SYNC 在给定的时间，检索最近一个关键帧。
        // OPTION_NEXT_SYNC 在给定时间之后，检索一个关键帧。
        // OPTION_PREVIOUS_SYNC 在给定时间之前，检索一个关键帧。
        return media.getFrameAtTime(timeUs, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);

        // 得到视频第一帧的缩略图
        // return media.getFrameAtTime();
    }

    public  void loadVideoScreenshot(String uri, ImageView imageView, long frameTimeMicros) {
//        RequestOptions requestOptions = RequestOptions.frameOf(frameTimeMicros)
//                .centerCrop()
//                .placeholder(R.drawable.icon_loading)
//                .error(R.drawable.error_bg5);
//        requestOptions.set(FRAME_OPTION, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
//        requestOptions.transform(new BitmapTransformation() {
//            @Override
//            protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
//                return toTransform;
//            }
//
//            @Override
//            public void updateDiskCacheKey(MessageDigest messageDigest) {
//                try {
//                    messageDigest.update((mContext.getPackageName() + "RotateTransform").getBytes("utf-8"));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });

        // if (Util.isOnMainThread()) {
//        Glide.with(mContext).load(uri).apply(AppConfig.ListCent()).into(imageView);
        //   }
    }

//    public RemoveViewHolder setImageUrlfit(int viewId, String imageUrl) {
//        ImageView view = getView(viewId);
//        Glide.with(mContext).load(imageUrl)
//                .apply(AppConfig.ListFit())
//                .into(view);
//
//        return this;
//    }
//
//    public RemoveViewHolder setImageUrlCen(int viewId, String imageUrl) {
//        ImageView view = getView(viewId);
//        if (Util.isOnMainThread()) {
//            Glide.with(mContext).load(imageUrl)
//                    .apply(AppConfig.ListCent())
//                    .into(view);
//        }
//        return this;
//    }
//
//    public RemoveViewHolder setImageUserhead(int viewId, String imageUrl) {
//        ImageView view = getView(viewId);
//        if (Util.isOnMainThread()) {
//            Glide.with(mContext).load(imageUrl)
//                    .apply(AppConfig.ListUsers())
//                    .into(view);
//        }
//        return this;
//    }

    public RemoveViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public RemoveViewHolder setTextBudlier(int viewId, CharSequence charSequence) {
        TextView tv = getView(viewId);
        tv.setText(charSequence);
        return this;
    }

    public RemoveViewHolder setTextHtml(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(Html.fromHtml(text));
        return this;
    }

    public RemoveViewHolder setTextColor(int viewId, int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public RemoveViewHolder setTextSize(int viewId, float size) {
        TextView view = getView(viewId);
        view.setTextSize(size);
        return this;
    }

    public RemoveViewHolder setTextColorRes(int viewId, int textColorRes) {
        TextView view = getView(viewId);
        view.setTextColor(mContext.getResources().getColor(textColorRes));
        return this;
    }

    public RemoveViewHolder setVisible(int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    public RemoveViewHolder setVisiblePlaceHolder(int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
        return this;
    }

    public RemoveViewHolder setOnClickListener(int viewId, Object o, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setTag(o);
        view.setOnClickListener(listener);
        return this;
    }


    public static Bitmap createVideoThumbnail(String filePath, int kind) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            if (filePath.startsWith("http://")
                    || filePath.startsWith("https://")
                    || filePath.startsWith("widevine://")) {
                retriever.setDataSource(filePath, new Hashtable<String, String>());
            } else {
                retriever.setDataSource(filePath);
            }
            bitmap = retriever.getFrameAtTime(0, MediaMetadataRetriever.OPTION_CLOSEST_SYNC); //retriever.getFrameAtTime(-1);
        } catch (IllegalArgumentException ex) {
            // Assume this is a corrupt video file
            ex.printStackTrace();
        } catch (RuntimeException ex) {
            // Assume this is a corrupt video file.
            ex.printStackTrace();
        } finally {
            try {
                retriever.release();
            } catch (RuntimeException ex) {
                // Ignore failures while cleaning up.
                ex.printStackTrace();
            }
        }

        if (bitmap == null) {
            return null;
        }

        if (kind == MediaStore.Images.Thumbnails.MINI_KIND) {//压缩图片 开始处
            bitmap = compressImage(bitmap);
        } else if (kind == MediaStore.Images.Thumbnails.MICRO_KIND) {
            bitmap = compressImage(bitmap);
        }
        return bitmap;
    }

    /**
     * 质量压缩方法
     *
     * @param image
     * @return
     */
    public static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 50, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 90;

        while (baos.toByteArray().length / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset(); // 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }


}
