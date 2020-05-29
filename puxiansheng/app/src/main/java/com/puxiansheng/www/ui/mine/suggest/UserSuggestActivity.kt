package com.puxiansheng.www.ui.mine.suggest


import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.puxiansheng.logic.api.API
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import kotlinx.android.synthetic.main.activity_user_suggest.*
import kotlinx.coroutines.launch

class UserSuggestActivity : MyBaseActivity() {

    private lateinit var userSuggestViewModel: UserSuggestViewModel

    override fun getLayoutId(): Int {
        return R.layout.activity_user_suggest
    }

    override fun business() {
        userSuggestViewModel = ViewModelProvider(this)[UserSuggestViewModel::class.java]
    }


    fun initView(){
        button_back.setOnClickListener {
            onBackPressed()
        }

        bt_more.setOnClickListener {
            QuestionDialog().show(supportFragmentManager, QuestionDialog::class.java.name)
        }

        DividerItemDecoration(this, DividerItemDecoration.VERTICAL).let {
            it.setDrawable(resources.getDrawable(R.drawable.recyclerview_divider_thin, null))
            quest_list.addItemDecoration(it)
        }
        quest_list.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            userSuggestViewModel.getRequestType().let {
                var typeTileList = mutableListOf<String>()
                it?.forEach { item->
                    typeTileList.add(item.text)
                }
                if(typeTileList.isNotEmpty()){
                    val adapter = ArrayAdapter(this@UserSuggestActivity, R.layout.item_pulldown_select, typeTileList)
                    adapter.setDropDownViewResource(R.layout.item_pulldown_select)
                    type_spinner.adapter = adapter
                }
            }

            userSuggestViewModel.getRequestList().let {
                quest_list.adapter = it?.let { it1 -> RequestAdapter(it1) }
            }
        }

        input_suggest.addTextChangedListener{ editable ->
            editable?.toString()?.let {
                userSuggestViewModel.suggesttion = it
            }}

        submit_suggest.setOnClickListener {
            if (userSuggestViewModel.suggesttion.isEmpty()) {
                Toast.makeText(this, "请输入建议！", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            userSuggestViewModel.submitSuggest()
        }


        userSuggestViewModel.toastMsg.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        userSuggestViewModel.suggestResult.observe(this, Observer {
            if (it == API.CODE_SUCCESS) {
                Navigation.findNavController(this, R.id.homeNavHost).navigateUp()
            }
        })

    }


}