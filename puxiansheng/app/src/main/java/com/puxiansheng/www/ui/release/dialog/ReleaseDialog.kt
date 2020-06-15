package com.puxiansheng.www.ui.release.dialog

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.puxiansheng.www.R
import com.puxiansheng.www.databinding.DialogReleaseSubmitTipsBinding
import com.puxiansheng.www.ui.mine.relase.MyReleaseAllActivity
import com.puxiansheng.www.ui.release.InsertOrUpdateTransferInOrderActivity

class ReleaseDialog(private var type: Int) : DialogFragment() {

    override fun onStart() {
        super.onStart()
        dialog?.let {
            it.window?.let { window ->
                window.setLayout(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
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
    ): View? = DialogReleaseSubmitTipsBinding.inflate(inflater).apply {
        when (type) {
            0 -> {//保存成功
                layoutSubmit.visibility = View.GONE
                layoutSave.visibility = View.VISIBLE
                saveIcon.setImageResource(R.mipmap.ic_save_success)
                saveTitle.text = "保存成功"
                subTitle.text = "请尽快上传图片，通过审核"
                btOk.setTextColor(resources.getColor(R.color.release_sava_text_color))
            }
            1 -> {//发布成功
                layoutSubmit.visibility = View.VISIBLE
                layoutSave.visibility = View.GONE
                dialogIcon.setImageResource(R.mipmap.ic_release_success)
                title.text = "恭喜您！发布成功"
                btReturn.setTextColor(resources.getColor(R.color.release_success_text_color))
                btReturn.text = "确定"
            }
            2 -> {//发布失败
                layoutSubmit.visibility = View.VISIBLE
                layoutSave.visibility = View.GONE
                dialogIcon.setImageResource(R.mipmap.ic_release_fail)
                title.text = "哎呀！发布失败"
                btReturn.setTextColor(resources.getColor(R.color.release_fail_text_color))
                btReturn.text = "返回"
            }
        }
        btOk.setOnClickListener {
            dismiss()
        }
        btReturn.setOnClickListener {
            when (type) {
                1 -> {
                    val intent = Intent(requireActivity(), MyReleaseAllActivity::class.java)
                    startActivity(intent)

                }
            }
            dismiss()
        }

    }.root
}