package com.puxiansheng.www.ui.info

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.puxiansheng.www.R
import com.puxiansheng.www.common.AppFragment
import com.puxiansheng.www.databinding.FragmentInfoHomeBinding
import com.puxiansheng.www.ui.main.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch

class InfoHomeListFragment : AppFragment() {

    private lateinit var infoListViewModel: InfoListViewModel
    private lateinit var appModel: MainViewModel
    var category:Int = 0


    override fun onAttach(context: Context) {
        super.onAttach(context)
        infoListViewModel = ViewModelProvider(this)[InfoListViewModel::class.java]
        appModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = rootView ?: FragmentInfoHomeBinding.inflate(inflater).apply {
        lifecycleOwner = viewLifecycleOwner

        buttonBack.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.homeNavHost).navigateUp()
        }

        btSearch.addTextChangedListener {
            infoListViewModel.title = it.toString()
        }
        btSearch.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH && infoListViewModel.title.isNotEmpty()) {
                hideKeyboard(btSearch)
//                order_list.removeAllViews()
//                infoListViewModel.refresh(category = )
                return@OnEditorActionListener true
            }
            false
        })

//        appModel.currentSignatureToken.observe(viewLifecycleOwner, Observer {
            lifecycleScope.launch {
                //isLoaded = true
                infoListViewModel.getInfoCategoriesFromRemote()?.let {
                    //                    println(it)
                    pager.adapter = InfoPagerAdapter(
                        fragmentManager = childFragmentManager,
                        fragments = it.map { category ->
                            InfoListFragment.newInstance(category = category.menuID.toInt())
                        },
                        titles = it.map { text ->
                            text.text
                        })
                    pager.offscreenPageLimit = 5
                    tabs.setupWithViewPager(pager)
                }




            }
//        })

    }.root

    fun hideKeyboard(view: View) {
        val manager: InputMethodManager = view.context
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}