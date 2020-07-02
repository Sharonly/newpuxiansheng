package com.puxiansheng.www.common

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction


abstract class AppFragment : Fragment() {
    protected var rootView: View? = null
    private val STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        if (savedInstanceState != null) {
//            var isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN)
//            var ft: FragmentTransaction = fragmentManager?.beginTransaction()!!
//            if (isSupportHidden) {
//                ft.hide(this)
//            } else {
//                ft.show(this)
//            }
//            ft.commit()
//        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        userVisibleHint = true
//        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden)
    }

    override fun onDestroy() {
        rootView = null
        super.onDestroy()
//        val refWatcher: RefWatcher? = App.getRefWatcher(App.getMyContext()) //1
//        refWatcher?.watch(this)
    }


}