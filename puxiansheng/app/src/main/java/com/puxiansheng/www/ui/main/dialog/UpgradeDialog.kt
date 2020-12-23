package com.puxiansheng.www.ui.main.dialog

import android.Manifest
import android.app.Activity
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.puxiansheng.util.ext.FileDownloadUtil
import com.puxiansheng.www.databinding.DialogUpgradeBinding
import com.puxiansheng.www.ui.main.MainViewModel

class UpgradeDialog() : DialogFragment() {
    var mContext: Activity? = null

    private var mVersionName: String = ""
    private var mFileDownUrl: String = ""
    private var mUpgradeTips: String = ""
    private var upgradeType: Int = 0
    private var isForceUpgrade: Boolean = false

    private lateinit var appModel: MainViewModel
    val requiredPermissions = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    companion object {
        private var instance: UpgradeDialog? = null

        fun getInstance(): UpgradeDialog {
            if (instance == null) {
                synchronized(UpgradeDialog::class.java) {
                    if (instance == null) {
                        instance =
                            UpgradeDialog()
                    }
                }
            }
            return instance!!
        }
    }


    fun setData(
        context: Activity,
        versionName: String,
        fileDownUrl: String,
        upgadeTips: String,
        type: Int,
        isUpgrade: Boolean
    ) {
        mContext = context
        mVersionName = versionName
        mFileDownUrl= fileDownUrl
        mUpgradeTips = upgadeTips
        upgradeType= type
        isForceUpgrade = isUpgrade
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        isCancelable = false
        downLoadUrl = mFileDownUrl
    }

    var downLoadUrl: String = ""
    override fun onStart() {
        super.onStart()
        dialog?.let {
            it.window?.let { window ->
                window.setLayout(
                    resources.displayMetrics.widthPixels.times(0.9).toInt(),
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                window.setGravity(Gravity.CENTER)
                window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DialogUpgradeBinding.inflate(inflater).apply {
        lifecycleOwner = viewLifecycleOwner
        upgradeVersion.text = mVersionName
        upgradeDesc.text = mUpgradeTips
        upgradeDesc.movementMethod = ScrollingMovementMethod.getInstance()
        when (upgradeType) {//，没包
            0 -> {
                buttonSure.visibility = View.VISIBLE
                buttonOk.visibility = View.GONE
                buttonCancel.visibility = View.GONE
                line.visibility = View.GONE
            }
            1 -> {
                if (!isForceUpgrade) {
                    buttonSure.visibility = View.GONE
                    buttonOk.visibility = View.VISIBLE
                    buttonCancel.visibility = View.VISIBLE
                    line.visibility = View.VISIBLE
                } else {
                    buttonSure.visibility = View.VISIBLE
                    buttonSure.text = "立即更新"
                    buttonOk.visibility = View.GONE
                    buttonCancel.visibility = View.GONE
                    line.visibility = View.GONE

                }
            }
        }

        buttonSure.setOnClickListener {
            Log.d("---upgrade", "upgradeType = " + upgradeType)
            when (upgradeType) {
                0 -> dismiss()
                1 -> {
                    if (mFileDownUrl.isNotEmpty()) {
                        mContext?.let { it1 -> FileDownloadUtil.DownLoadApk(it1, mFileDownUrl) }
                    }
                    dismiss()
                }
            }

        }
        buttonOk.setOnClickListener {
            if (mFileDownUrl.isNotEmpty()) {
                context?.let { it1 -> FileDownloadUtil.DownLoadApk(it1, mFileDownUrl) }
            }
            dismiss()
        }

        buttonCancel.setOnClickListener {
            dismiss()
        }
    }.root


}