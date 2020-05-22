package com.puxiansheng.www.common

import android.view.View
import androidx.fragment.app.Fragment

abstract class AppFragment : Fragment() {
    protected var rootView: View? = null

    override fun onDestroy() {
        rootView = null
        super.onDestroy()
    }


}