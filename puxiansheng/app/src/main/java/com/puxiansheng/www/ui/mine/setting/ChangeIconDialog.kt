package com.puxiansheng.www.ui.mine.setting

import android.Manifest
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.DialogFragment
import com.puxiansheng.logic.util.GlideImageEngine
import com.puxiansheng.www.common.LiveDataBus
import com.puxiansheng.www.databinding.DialogChangeIconBinding
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.internal.entity.CaptureStrategy
import java.io.File
import java.io.IOException


class ChangeIconDialog() : DialogFragment() {
    private lateinit var binding: DialogChangeIconBinding
    var imageUri: Uri? = null


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

//                Matisse.from(requireActivity())
//                    .choose(MimeType.ofImage())
//                    .countable(true)
//                    .maxSelectable(1)
//                    .capture(true)
//                    .captureStrategy(
//                        CaptureStrategy(
//                            false,
//                            "${requireActivity().packageName}.fileProvider"
//                        )
//                    )
//                    .gridExpectedSize(resources.displayMetrics.widthPixels / 3)
//                    .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
//                    .thumbnailScale(0.75f)
//                    .imageEngine(GlideImageEngine())
//                    .forResult(102)
            }
            dismiss()
        }
        binding.btTakePicture.setOnClickListener {
            dismiss()
            var outputImage = File(Environment.DIRECTORY_PICTURES, "pxs_icon.jpg")
            if (outputImage.exists()) {
                outputImage.delete()
            }

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                imageUri = Uri.fromFile(outputImage)
            } else {
                imageUri = FileProvider.getUriForFile(
                    requireActivity(), "${requireContext().packageName}.fileProvider",
                    outputImage
                )
            }
            Log.d("---imageicon--", "dialog  imageUri = " + imageUri)
            val intent = Intent("android.media.action.IMAGE_CAPTURE")
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
            requireActivity().startActivityForResult(intent, 101)

        }

        binding.btCancel.setOnClickListener { dismiss() }

    }.root


}