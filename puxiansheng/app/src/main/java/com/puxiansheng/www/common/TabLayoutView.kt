package com.puxiansheng.www.common


import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.puxiansheng.www.R

class TabLayoutView : TabLayout {
    private var tabTitles = mutableListOf<String>()
    constructor(context: Context) : super(context, null)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun selectTab(tab: Tab?) {
        val tabSelect =
            tab?.customView?.findViewById(R.id.tab_item_textview) as TextView
        tabSelect.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
        tabSelect.text = tab.text

    }
    override fun addOnTabSelectedListener(listener: OnTabSelectedListener) {
        super.addOnTabSelectedListener(listener)
    }


    fun setTitles(tabs: List<String>){
        tabTitles = tabs as MutableList<String>
    }


    private fun initTabView(tabs:TabLayout) {
        for (i in 0 until tabs.tabCount) {
            val tab: TabLayout.Tab? = tabs.getTabAt(i)
            if (tab != null) {
                tab.customView = getTabView(i)
            }
        }
        tabs.getTabAt(tabs.selectedTabPosition)?.let { updateTabTextView(it, true) }

        tabs.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                updateTabView(tab, true)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                updateTabView(tab, false)
            }

            private fun updateTabView(tab: TabLayout.Tab, isSelect: Boolean) {
                if (isSelect) { //选中加粗
                    val tabSelect =
                        tab.customView?.findViewById(R.id.tab_item_textview) as TextView
                    tabSelect.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                    tabSelect.text = tab.text
                } else {
                    val tabUnSelect =
                        tab.customView?.findViewById(R.id.tab_item_textview) as TextView
                    tabUnSelect.typeface = Typeface.defaultFromStyle(Typeface.NORMAL)
                    tabUnSelect.text = tab.text
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }


    private fun getTabView(currentPosition: Int): View? {
        val view: View = LayoutInflater.from(context).inflate(R.layout.tab_item, null)
        val textView =
            view.findViewById<View>(R.id.tab_item_textview) as TextView
        textView.text =tabTitles[currentPosition]
        return view
    }
    private fun updateTabTextView(tab: TabLayout.Tab, isSelect: Boolean) {
        if (isSelect) { //选中加粗
            val tabSelect =
                tab.customView?.findViewById(R.id.tab_item_textview) as TextView
            tabSelect.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
            tabSelect.text = tab.text
        } else {
            val tabUnSelect =
                tab.customView?.findViewById(R.id.tab_item_textview) as TextView
            tabUnSelect.typeface = Typeface.defaultFromStyle(Typeface.NORMAL)
            tabUnSelect.text = tab.text
        }
    }

}