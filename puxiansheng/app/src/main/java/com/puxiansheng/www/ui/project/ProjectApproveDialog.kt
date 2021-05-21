package com.puxiansheng.www.ui.project

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.puxiansheng.www.databinding.DialogLoginSuccessTipsBinding
import com.puxiansheng.www.databinding.DialogProjectTipsBinding
import com.puxiansheng.www.ui.main.MainActivity

class ProjectApproveDialog(private var projectId:String, private var projectName:String):DialogFragment (){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DialogProjectTipsBinding.inflate(inflater).apply {
        lifecycleOwner = viewLifecycleOwner

        buttonOk.setOnClickListener {
           dismiss()
            val intent = Intent(context, ProjectApproveActivity::class.java)
            intent.putExtra("projectId", projectId)
            intent.putExtra("projectName", projectName)
            startActivity(intent)
        }


    }.root


}