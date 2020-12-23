package com.puxiansheng.www.ui.main

import android.content.Context
import android.content.Intent
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import kotlinx.android.synthetic.main.activity_permisson.*

class PermissionActivity : MyBaseActivity() {
    var context: Context = this@PermissionActivity
    var isSeleted = false

    override fun getLayoutId(): Int {
        return R.layout.activity_permisson
    }

    override fun business() {
        button_go.setOnClickListener {
                val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            finish()
        }
    }


}