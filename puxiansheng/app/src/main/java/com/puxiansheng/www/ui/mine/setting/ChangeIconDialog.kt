package com.puxiansheng.www.ui.mine.setting

import android.Manifest
import android.content.Context
import android.content.Intent
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
import com.puxiansheng.www.databinding.DialogChangeIconBinding
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
                intent.setType("image/*")
                requireActivity().startActivityForResult(intent, 102)
            }
            dismiss()

        }
        binding.btTakePicture.setOnClickListener {
            dismiss()
            var outputImage = File(Environment.DIRECTORY_PICTURES, "pxs_icon.jpg")
            try {
                if (outputImage.exists()) {
                    outputImage.delete()
                }
            } catch (ex: IOException) {
                ex.printStackTrace()
            }
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                imageUri = Uri.fromFile(outputImage)
            } else {
                imageUri = FileProvider.getUriForFile(
                    requireActivity(),
                    "${requireContext().packageName}.fileProvider",
                    outputImage
                )
            }
            Log.d("---icon--","dialog  imageUri = "+imageUri)
            val intent = Intent("android.media.action.IMAGE_CAPTURE")
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
            requireActivity().startActivityForResult(intent, 101)
        }

        binding.btCancel.setOnClickListener { dismiss() }

    }.root


}