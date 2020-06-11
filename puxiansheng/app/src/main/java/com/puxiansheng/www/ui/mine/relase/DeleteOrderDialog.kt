package com.puxiansheng.www.ui.mine.relase

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.InfoItem
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.www.ui.mine.history.BrowsingHistoryTransferInOrdersViewModel
import com.puxiansheng.www.databinding.DialogDeleteOrderBinding
import com.puxiansheng.www.ui.mine.favor.FavoriteInfoListViewModel
import com.puxiansheng.www.ui.mine.favor.FavoriteTransferInOrdersViewModel
import com.puxiansheng.www.ui.mine.favor.FavoriteTransferOutOrdersViewModel
import com.puxiansheng.www.ui.mine.history.BrowsingHistoryInfoListViewModel
import com.puxiansheng.www.ui.mine.history.BrowsingHistoryTransferOutOrdersViewModel
import kotlinx.coroutines.launch


class DeleteOrderDialog(private var dialogtitle: String, private var type: Int, private var shopId: Long?) : DialogFragment() {

    private lateinit var privateInviewModel: ReleasedTransferInOrdersViewModel
    private lateinit var privateOutviewModel: ReleasedTransferOutOrdersViewModel

    private lateinit var favorInViewModel: FavoriteTransferInOrdersViewModel
    private lateinit var favorOutViewModel: FavoriteTransferOutOrdersViewModel
    private lateinit var favorInfoViewModel: FavoriteInfoListViewModel

    private lateinit var historyInViewModel: BrowsingHistoryTransferInOrdersViewModel
    private lateinit var historyOutViewModel: BrowsingHistoryTransferOutOrdersViewModel
    private lateinit var historyInfoViewModel: BrowsingHistoryInfoListViewModel


    var listener: OnDissListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onStart() {
        super.onStart()

        dialog?.let {
            it.window?.let { window ->
                window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                window.setGravity(Gravity.CENTER)
                window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DialogDeleteOrderBinding.inflate(inflater).apply {
        privateInviewModel = ViewModelProvider(requireActivity())[ReleasedTransferInOrdersViewModel::class.java]
        privateOutviewModel = ViewModelProvider(requireActivity())[ReleasedTransferOutOrdersViewModel::class.java]

        favorInViewModel =
            ViewModelProvider(requireActivity())[FavoriteTransferInOrdersViewModel::class.java]
        favorOutViewModel =
            ViewModelProvider(requireActivity())[FavoriteTransferOutOrdersViewModel::class.java]
        favorInfoViewModel =
            ViewModelProvider(requireActivity())[FavoriteInfoListViewModel::class.java]

        historyInViewModel =
            ViewModelProvider(requireActivity())[BrowsingHistoryTransferInOrdersViewModel::class.java]
        historyOutViewModel =
            ViewModelProvider(requireActivity())[BrowsingHistoryTransferOutOrdersViewModel::class.java]
        historyInfoViewModel =
            ViewModelProvider(requireActivity())[BrowsingHistoryInfoListViewModel::class.java]

        title.text = dialogtitle
        buttonCancel.setOnClickListener { dismiss() }
        buttonOk.setOnClickListener {
            lifecycleScope.launch {
                when (type) {
                    Order.Type.TRANSFER_IN_PRIVATE.value() -> {
                        privateInviewModel.deleteTransferInOrderFromRemote(shopId.toString())
                            ?.let { rst ->
                                if (rst.code == API.CODE_SUCCESS)
                                  Toast.makeText(requireContext(), rst.data, Toast.LENGTH_SHORT).show()
                                  dismiss()
                                  listener?.onDiss()
                            }
                    }
                    Order.Type.TRANSFER_OUT_PRIVATE.value() -> {
                        privateOutviewModel.deleteTransferOutOrderFromRemote(shopId.toString())
                            ?.let { rst ->
                                if (rst.code == API.CODE_SUCCESS)
                                    Toast.makeText(requireContext(), rst.data, Toast.LENGTH_SHORT).show()
                                  dismiss()
                                listener?.onDiss()
                            }
                    }

                    Order.Type.TRANSFER_IN_FAVORITE.value() -> {
                        favorInViewModel.deleteFavorTransferInOrderFromRemote(shopID = shopId.toString())
                            ?.let { rst ->
                                if (rst.code == API.CODE_SUCCESS)
                                    Toast.makeText(requireContext(), rst.msg, Toast.LENGTH_SHORT)
                                        .show()
                                dismiss()
                                listener?.onDiss()
                            }
                    }

                    Order.Type.TRANSFER_OUT_FAVORITE.value() -> {
                        favorOutViewModel.deleteFavorTransferOutOrderFromRemote(shopID = shopId.toString())
                            ?.let { rst ->
                                if (rst.code == API.CODE_SUCCESS)
                                    Toast.makeText(requireContext(), rst.msg, Toast.LENGTH_SHORT)
                                        .show()
                                dismiss()
                                listener?.onDiss()
                            }
                    }


                    InfoItem.Type.ARTICLE_FAVOR.value() -> {
                        favorInfoViewModel.deleteFavorInfoFromRemote(shopId.toString())
                            ?.let { rst ->
                                if (rst.code == API.CODE_SUCCESS)
                                    Toast.makeText(requireContext(), rst.msg, Toast.LENGTH_SHORT)
                                        .show()
                                dismiss()
                                listener?.onDiss()
                            }
                    }

                    Order.Type.TRANSFER_OUT_HISTORY.value() -> {
                        historyOutViewModel.deleteHistoryTransferOutOrderFromRemote()?.let { rst ->
                            if (rst.code == API.CODE_SUCCESS)
                                Toast.makeText(requireContext(), rst.msg, Toast.LENGTH_SHORT)
                                    .show()
                            dismiss()
                            listener?.onDiss()
                        }
                    }


                    Order.Type.TRANSFER_IN_HISTORY.value() -> {
                        historyInViewModel.deleteHistoryTransferInOrderFromRemote()?.let { rst ->
                            if (rst.code == API.CODE_SUCCESS) {
                                Toast.makeText(requireContext(), rst.msg, Toast.LENGTH_SHORT)
                                    .show()
                            }
                            dismiss()
                            listener?.onDiss()
                        }
                    }

                    InfoItem.Type.ARTICLE_HISTORY.value() -> {
                        historyInfoViewModel.deleteHistoryInfoFromRemote()?.let { rst ->
                            if (rst.code == API.CODE_SUCCESS)
                                Toast.makeText(requireContext(), rst.msg, Toast.LENGTH_SHORT)
                                    .show()
                            dismiss()
                            listener?.onDiss()
                        }
                    }
                }
            }
        }
    }.root

//    fun setOnDismissListener(listener: DialogInterface.OnDismissListener) {
//        this.mOnClickListener = listener
//    }

    interface OnDissListener {
        fun onDiss()
    }


//    override fun dismiss() {
//        super.dismiss()
//        listener?.onDiss()
//        //mOnClickListener?.onDismiss(dialog)
//    }


}