package com.puxiansheng.www.ui.project

import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.puxiansheng.logic.api.API
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import kotlinx.android.synthetic.main.activity_project_approve.*
import kotlinx.coroutines.launch

class ProjectApproveActivity : MyBaseActivity() {
    private lateinit var viewModel: ProjectApprovelViewModel

    override fun getLayoutId(): Int {
        return R.layout.activity_project_approve
    }

    override fun business() {
        viewModel = ViewModelProvider(this)[ProjectApprovelViewModel::class.java]
      initView()
    }

    fun initView() {

//        edit_project.addTextChangedListener {
//            viewModel.projectName = it.toString()
//        }

        edit_project.text =   intent.getStringExtra("projectName")
        viewModel.projectId =  intent.getStringExtra("projectId")
        edit_phone.addTextChangedListener {
           viewModel.phone= it.toString()
        }

        edit_name.addTextChangedListener {
            viewModel.name = it.toString()
        }

        edit_invest.addTextChangedListener {
            viewModel.money = it.toString()
        }

        submit.setOnClickListener {
            lifecycleScope.launch {
                viewModel.submitProve()?.let {
                    if (it.code == API.CODE_SUCCESS) {
                        onBackPressed()
                    }
                    Toast.makeText(this@ProjectApproveActivity, it.msg, Toast.LENGTH_SHORT)
                }
            }
        }

    }
}