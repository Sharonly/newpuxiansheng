package com.puxiansheng.www.common;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

    /**
     *
     */

public class ShareUtils {

        /**
         * 微信7.0版本号，兼容处理微信7.0版本分享到朋友圈不支持多图片的问题
         */
        private static final int VERSION_CODE_FOR_WEI_XIN_VER7 = 1380;
        /**
         * 微信包名
         */
        public static final String PACKAGE_NAME_WEI_XIN = "com.tencent.mm";

        public static ShareUtils shareUtils = null;
        private Context context = null;
        private List<File> files = new ArrayList<>();

        public static ShareUtils Initialize() {
            if (shareUtils == null) {
                shareUtils = new ShareUtils();
            }
            return shareUtils;
        }

        public ShareUtils setContext(Context context) {
            this.context = context;
            files.clear();
            return shareUtils;
        }

//        public void shareQQ(String[] url) {
//
//            if (!Tools.isAppAvilible(context, "com.tencent.mobileqq")) {
//                Toast.makeText(context, "您还没有安装QQ", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            ShareSave(url, 3);
//        }
//
//        public void shareWeiXin(final String[] url) {
//            if (!Tools.isAppAvilible(context, "com.tencent.mm")) {
//                Toast.makeText(context, "您还没有安装微信", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            ShareSave(url, 0);
//        }
//
//        public void shareQZone(String[] url) {
//            if (!Tools.isAppAvilible(context, "com.tencent.mobileqq")) {
//                Toast.makeText(context, "您还没有安装QQ", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            ShareSave(url, 2);
//
//        }
//
//        public void sharePYQ(String[] url) {
//            if (!Tools.isAppAvilible(context, "com.tencent.mm")) {
//                Toast.makeText(context, "您还没有安装微信", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            ShareSave(url, 1);
//        }
//
//        public void shareWB(final String[] url) {
//
//            if (!Tools.isAppAvilible(context, "com.sina.weibo")) {
//                Toast.makeText(context, "您还没有安装微博", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            ShareSave(url, 4);
//        }

        public void ShareSave(final String[] url, final int type) {

//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    for (int i = 0; i < url.length; i++) {
//                        File file = null;
//                        if (url[i].contains("http")) {
//                            file = Tools.saveImageToSdCard(context, url[i]);
//                        } else {
//                            file = new File(url[i]);
//                        }
//                        files.add(file);
//                    }
//                    ArrayList<Uri> imageUris = new ArrayList<Uri>();
//                    for (File f : files) {
//                        imageUris.add(Uri.fromFile(f));
//                    }
//
//
//                    if (type == 0) {
//                        shareWXSomeImg(context, imageUris);
//                    } else if (type == 1) {
//                        shareweipyqSomeImg(context, imageUris);
//                    } else if (type == 2) {
//                        shareQZoneImg(context, imageUris);
//                    } else if (type == 3) {
//                        shareQQImg(context, imageUris);
//                    } else {
//                        shareWBImg(context, imageUris);
//                    }
//
//                }
//            }).start();
        }


        private void shareweipyqSomeImg(final Context context, ArrayList<Uri> uri) {
            Intent shareIntent = new Intent();
            shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            //2添加图片数组
            ArrayList<Uri> imageUris = new ArrayList<>();
            for (int i = 0; i < uri.size(); i++) {
                Uri url = null;
                try {
                    url = Uri.parse(android.provider.MediaStore.Images.
                            Media.insertImage(context.getContentResolver(),
                            files.get(i).getAbsolutePath(), files.get(i).getName(), null));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                imageUris.add(url);
            }
            if (getVersionCode(context, PACKAGE_NAME_WEI_XIN) < VERSION_CODE_FOR_WEI_XIN_VER7) {
                // 微信7.0以下版本
                shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
                shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
            } else {
                // 微信7.0及以上版本,朋友圈只支持单张图片分享
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_STREAM, imageUris.get(0));
            }
            shareIntent.setType("image/*");
            //3指定选择微信
            ComponentName componentName = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");
            shareIntent.setComponent(componentName);
            //4开始分享
            context.startActivity(Intent.createChooser(shareIntent, "分享图片"));
        }
        /**
         * 拉起微信发送多张图片给好友
         */
        private void shareWXSomeImg(Context context, ArrayList<Uri> uri) {
            Intent shareIntent = new Intent();
            //1调用系统分析
            shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
            shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //2添加图片数组
            ArrayList<Uri> imageUris = new ArrayList<>();
            for (int i = 0; i < uri.size(); i++) {
                Uri url = null;
                try {
                    url = Uri.parse(android.provider.MediaStore.Images.
                            Media.insertImage(context.getContentResolver(),
                            files.get(i).getAbsolutePath(), files.get(i).getName(), null));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                imageUris.add(url);
            }
            shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
            shareIntent.setType("image/*");
            //3指定选择微信
            ComponentName componentName = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI");
            shareIntent.setComponent(componentName);
            //4开始分享
            context.startActivity(Intent.createChooser(shareIntent, "分享图片"));
        }
        /**
         * 拉起QQ发送多张图片给好友
         */
        private void shareQQImg(Context context, ArrayList<Uri> uri) {
            Intent shareIntent = new Intent();
            //1调用系统分析
            shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
            shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //2添加图片数组
            ArrayList<Uri> imageUris = new ArrayList<>();
            for (int i = 0; i < files.size(); i++) {
                Uri url = null;
                try {
                    url = Uri.parse(android.provider.MediaStore.Images.
                            Media.insertImage(context.getContentResolver(),
                            files.get(i).getAbsolutePath(), files.get(i).getName(), null));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                imageUris.add(url);
            }
            shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
            shareIntent.setType("image/*");
            //3指定选择微信
            ComponentName componentName = new ComponentName("com.tencent.mobileqq", "com.tencent.mobileqq.activity.JumpActivity");
            shareIntent.setComponent(componentName);
            //4开始分享
            context.startActivity(Intent.createChooser(shareIntent, "分享图片"));
        }
        /**
         * 拉起QQ发送多张图片给好友
         */
        private void shareQZoneImg(Context context, ArrayList<Uri> uri) {
            Intent shareIntent = new Intent();
            //1调用系统分析
            shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
            shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //2添加图片数组
            ArrayList<Uri> imageUris = new ArrayList<>();
            for (int i = 0; i < files.size(); i++) {
                Uri url = null;
                try {
                    url = Uri.parse(android.provider.MediaStore.Images.
                            Media.insertImage(context.getContentResolver(),
                            files.get(i).getAbsolutePath(), files.get(i).getName(), null));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                imageUris.add(url);
            }
            shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
            shareIntent.setType("image/*");
            //3指定选择微信
            ComponentName componentName = new ComponentName("com.qzone", "com.qzonex.module.operation.ui.QZonePublishMoodActivity");
            shareIntent.setComponent(componentName);
            //4开始分享
            context.startActivity(Intent.createChooser(shareIntent, "分享图片"));
        }
        /**
         * 拉起微博发送多张图片给好友
         */
        private void shareWBImg(Context context, ArrayList<Uri> uri) {
            Intent shareIntent = new Intent();
            //1调用系统分析
            shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
            shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //2添加图片数组
            ArrayList<Uri> imageUris = new ArrayList<>();
            for (int i = 0; i < files.size(); i++) {
                Uri url = null;
                try {
                    url = Uri.parse(android.provider.MediaStore.Images.
                            Media.insertImage(context.getContentResolver(),
                            files.get(i).getAbsolutePath(), files.get(i).getName(), null));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                imageUris.add(url);
            }
            shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
            shareIntent.setType("image/*");
            //3指定选择微信
//        ComponentName componentName = new ComponentName("com.sina.weibo", "com.sina.weibo.activity.JumpActivity");
//        shareIntent.setComponent(componentName);
            shareIntent.setPackage("com.sina.weibo");
            //4开始分享
            context.startActivity(Intent.createChooser(shareIntent, "分享图片"));
        }
        /**
         * 获取制定包名应用的版本的versionCode
         *
         * @param context
         * @param
         * @return
         */
        public static int getVersionCode(Context context, String packageName) {
            try {
                PackageManager manager = context.getPackageManager();
                PackageInfo info = manager.getPackageInfo(packageName, 0);
                int version = info.versionCode;
                return version;
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
    }

