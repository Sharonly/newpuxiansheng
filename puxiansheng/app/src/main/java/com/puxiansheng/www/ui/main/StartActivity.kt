package com.puxiansheng.www.ui.main

import android.content.Context
import android.content.Intent
import android.webkit.WebSettings
import android.widget.Toast
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import kotlinx.android.synthetic.main.activity_start.*


class StartActivity : MyBaseActivity() {
    var context: Context = this@StartActivity
    var isSeleted = false

    override fun getLayoutId(): Int {
        return R.layout.activity_start
    }

    override fun business() {
        web_view.loadUrl("file:///android_asset/privacy.html")
        web_view.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        web_view.settings.loadWithOverviewMode = true;
        web_view.settings.setSupportZoom(true)


        ic_selected.setOnClickListener {
            if (!isSeleted) {
                isSeleted = true
                ic_selected.setImageDrawable(resources.getDrawable(R.mipmap.selection))
                button_sure.setBackgroundResource(R.drawable.bg_bt_login_slected)

            } else {
                isSeleted = false
                ic_selected.setImageDrawable(resources.getDrawable(R.mipmap.unchecked))
                button_sure.setBackgroundResource(R.drawable.bg_bt_confirm_no_slected)
            }
        }


        button_sure.setOnClickListener {
            if(isSeleted){
                getSharedPreferences("pxs_privacy", Context.MODE_PRIVATE).edit().putInt("show_privacy", 1).commit()
                val intent = Intent(context, PermissionActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(context, "请先勾选用户协议和隐私政策", Toast.LENGTH_SHORT).show()
            }
        }
    }

}