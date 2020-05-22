package com.puxiansheng.www.ui.mine

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.puxiansheng.logic.api.API
import com.puxiansheng.util.ext.SharedPreferencesUtil.Companion.get
import com.puxiansheng.www.R
import com.puxiansheng.www.common.urlIcon
import com.puxiansheng.www.databinding.FragmentMineBinding
import com.puxiansheng.www.ui.login.LoginActivity
import com.puxiansheng.www.ui.main.MainViewModel
import com.puxiansheng.www.ui.mine.history.MyHistoryActivity
import com.puxiansheng.www.ui.mine.relase.MyReleaseAllActivity
import com.puxiansheng.www.ui.mine.favor.MyfarvorActivity
import com.puxiansheng.www.ui.mine.relase.OrderProcessingActivity
import com.puxiansheng.www.ui.mine.relase.OrderPublicActivity
import com.puxiansheng.www.ui.mine.relase.OrderSoldOutActivity

class MineFragment : Fragment() {
    private lateinit var mineViewModel: MineViewModel
    private lateinit var appModel: MainViewModel
    var isLoading = false
    private var isLogin = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mineViewModel = ViewModelProvider(requireActivity())[MineViewModel::class.java]
        appModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentMineBinding.inflate(inflater).apply {

        if (get(API.LOGIN_USER_TOKEN, "").toString().isNotEmpty()) {
            isLogin = true
            userAccount.text = get(API.LOGIN_NICK_NAME, "").toString()
            userPhone.text = get(API.LOGIN_USER_PHONE, "").toString()
//            userIcon.urlIcon(get(API.LOGIN_USER_ICON, "").toString())
        } else {
            userAccount.text = "请登录"
            userPhone.text = ""
        }

        userIcon.setOnClickListener {
            if (get(API.LOGIN_USER_TOKEN, "").toString().isNotEmpty()) {
                Navigation.findNavController(requireActivity(), R.id.homeNavHost)
                    .navigate(R.id.action_mainFragment_to_userSettingFragment)
            } else {
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
            }
        }

        userAccount.setOnClickListener {
            if (!isLogin) {
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
            }
        }


        iconSetting.setOnClickListener {
            if (!isLogin) {
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
            } else {
                Navigation.findNavController(requireActivity(), R.id.homeNavHost).navigate(
                    R.id.action_mainFragment_to_settingFragment
                )
            }
        }
        btMyRelease.setOnClickListener {
            if (!isLogin) {
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(requireActivity(), MyReleaseAllActivity::class.java)
                startActivity(intent)
            }
        }
        btMyFarvior.setOnClickListener {
            if (!isLogin) {
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(requireActivity(), MyfarvorActivity::class.java)
                startActivity(intent)
            }
        }
        btMyHistory.setOnClickListener {
            if (!isLogin) {
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(requireActivity(), MyHistoryActivity::class.java)
                startActivity(intent)
            }
        }

        layoutPublic.setOnClickListener {
            if (!isLogin) {
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(requireActivity(), OrderPublicActivity::class.java)
                startActivity(intent)
            }
        }
        layoutProcessing.setOnClickListener {
            if (!isLogin) {
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(requireActivity(), OrderProcessingActivity::class.java)
                startActivity(intent)
            }
        }

        layoutSoldOut.setOnClickListener {
            if (!isLogin) {
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(requireActivity(), OrderSoldOutActivity::class.java)
                startActivity(intent)
            }
        }


        btMyKefu.setOnClickListener { }
        btRequest.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.homeNavHost)
                .navigate(R.id.action_mainFragment_to_userSuggestFragment)
        }


        appModel.currentUser?.observe(requireActivity(), Observer { user ->
            user?.let {
                if (it.isLogin) {
                    isLogin = true
                    userAccount.text = user.nickname
                    userPhone.text = user.userPhoneNumber
                    userIcon.urlIcon(user.icon)
                } else {
                    isLogin = false
                    userAccount.text = "请登录"
                    userPhone.visibility = View.INVISIBLE
                }
            }
        })


    }.root
}