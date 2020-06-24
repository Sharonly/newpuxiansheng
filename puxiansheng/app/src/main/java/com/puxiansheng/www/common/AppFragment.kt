package com.puxiansheng.www.common

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.puxiansheng.www.app.App
import com.squareup.leakcanary.RefWatcher




abstract class AppFragment : Fragment() {
    protected var rootView: View? = null



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        userVisibleHint = true
    }

    override fun onDestroy() {
        rootView = null
        super.onDestroy()
//        val refWatcher: RefWatcher? = App.getRefWatcher(App.getMyContext()) //1
//        refWatcher?.watch(this)
    }


}