package com.puxiansheng.www.ui.mine.setting

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.DialogFragment
import com.puxiansheng.www.common.LiveDataBus
import com.puxiansheng.www.databinding.DialogChangeIconBinding
import java.io.File


class ChangeIconDialog() : DialogFragment() {
    private lateinit var binding: DialogChangeIconBinding
    var imageUri: Uri? = null
    private val sdPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/pxs"
//    private val sdPath =MediaStore.EXTRA_OUTPUT + "/pxs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = true
    }

    override fun onStart() {
        super.onStart()
        dialog?.let {
            it.window?.let { window ->
                window.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                window.setGravity(Gravity.BOTTOM)
                window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DialogChangeIconBinding.inflate(inflater).apply {
        binding = this
        binding.lifecycleOwner = viewLifecycleOwner
        binding.btSelectPhoto.setOnClickListener {
            dismiss()
            if (ContextCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    1
                )
            } else {
                val intent = Intent("android.intent.action.GET_CONTENT")
                intent.type = "image/*"
                requireActivity().startActivityForResult(intent, 102)
            }

        }


        binding.btTakePicture.setOnClickListener {
            dismiss()

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE) // 启动系统相机
            val file = File("$sdPath/Head.jpg")
            val cropImageUri = FileProvider.getUriForFile(
                requireActivity(),
                "${requireContext().packageName}.fileProvider",
                file
            )
            intent.putExtra(MediaStore.EXTRA_OUTPUT, cropImageUri) // 为像片指定存储路径
            requireActivity().startActivityForResult(intent, 101)
            LiveDataBus.get().with("Test", Uri::class.java)?.value = cropImageUri
        }

        binding.btCancel.setOnClickListener { dismiss() }

    }.root


}