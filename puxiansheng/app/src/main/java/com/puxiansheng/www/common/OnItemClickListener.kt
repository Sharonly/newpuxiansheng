package com.puxiansheng.www.common

import android.view.View

interface OnItemClickListener {
    /**
     * item点击回调
     *
     * @param view
     * @param position
     */
    fun onItemClick(view: View?, position: Int)

    /**
     * 删除按钮回调
     *
     * @param position
     */
    fun onDeleteClick(position: Int)
    fun onReadClick(position: Int)
}