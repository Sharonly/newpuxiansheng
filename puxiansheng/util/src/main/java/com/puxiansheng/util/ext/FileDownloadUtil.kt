package com.puxiansheng.util.ext

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Build
import android.os.Environment
import androidx.core.content.FileProvider
import java.io.File

class FileDownloadUtil {
    //下载路径版本小于10 Environment.getExternalStorageDirectory().getAbsolutePath

    companion object {
        fun DownLoadApk(context: Context, dowloadPath: String) {
            val dManager: DownloadManager =
                context?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            var uri: Uri = Uri.parse(dowloadPath)
            var request: DownloadManager.Request = DownloadManager.Request(uri)
            // 设置下载路径和文件名
//        path1: File = new File(filePath);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "铺先生.apk")
            //request.setDestinationInExternalFilesDir(context,"","/download/pxs/")
            request.setDescription("正在下载")
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            request.setMimeType("application/vnd.android.package-archive")
            // 设置为可被媒体扫描器找到
            request.allowScanningByMediaScanner()
            // 设置为可见和可管理
            request.setVisibleInDownloadsUi(true)
            // 获取此次下载的ID
            var refernece = dManager.enqueue(request)
            // 注册广播接收器，当下载完成时自动安装
            var filter: IntentFilter =
                IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
            var receiver: BroadcastReceiver = object : BroadcastReceiver() {
                override fun onReceive(context: Context?, intent: Intent?) {
                    var myDownloadID =
                        intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
                    if (refernece == myDownloadID) {
                        var query: DownloadManager.Query = DownloadManager.Query()
                        query.setFilterById(myDownloadID)
                        var cursor = dManager.query(query)
                        if (cursor.moveToFirst()) {
                            var fileName =
                                cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI))
                            if (fileName != null) {
                                if (context != null) {
                                    openAPK(context, fileName)
                                }
                            }
                        }
                        cursor.close()
//                    var downloadFileUri = dManager.getUriForDownloadedFile(refernece)
//                    if (downloadFileUri != null) {
//                        appModel.openAPK(downloadFileUri)
//                    }
                    }

                }
            }
            context.registerReceiver(receiver, filter)
        }


        fun openAPK(context: Context, fileSavePath: String) {
            var file = File(Uri.parse(fileSavePath).path)
            var filePath = file.absolutePath
            var intent = Intent(Intent.ACTION_VIEW)
            var data: Uri
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//判断版本大于等于7.0
                data = FileProvider.getUriForFile(
                    context,
                    "com.puxiansheng.www.fileProvider",
                    File(filePath)
                );
                intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION;// 给目标应用一个临时授权
            } else {
                data = Uri.fromFile(file);
            }
            intent.setDataAndType(data, "application/vnd.android.package-archive")
            context?.startActivity(intent)
        }
    }

    //    fun goAppStore(marketPkg: String) {
// 1       val intent = Intent("android.intent.action.MAIN")
//        intent.addCategory("android.intent.category.APP_MARKET");
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);

// 2       var uri = Uri.parse("market://details?id=" + "com.puxiansheng.www");
//        val intent = Intent(Intent.ACTION_VIEW, uri);
//            marketPkg?.let{
//                intent.setPackage(marketPkg)
//            }
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
//    }

}