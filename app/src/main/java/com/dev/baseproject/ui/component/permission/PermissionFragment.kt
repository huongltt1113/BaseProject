package com.dev.baseproject.ui.component.permission

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.dev.baseproject.R
import com.dev.baseproject.databinding.FragmentPermissionBinding
import com.dev.baseproject.ui.base.BaseFragmentBinding
import com.dev.baseproject.utils.AppConfig
import com.dev.baseproject.utils.Constants

class PermissionFragment : BaseFragmentBinding<FragmentPermissionBinding>() {

    private var isRecordPermission = false

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            //findNavControllerSafety()?.navigate()
            val bundle = Bundle()
            bundle.putBoolean(KEY_RECORD_PERMISSION, true)
            findNavControllerSafety()?.navigate(R.id.action_permissionFragment_to_recordPermissionFragment, bundle)
        }
    private val requestAudioPermissionLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            //findNavControllerSafety()?.navigate()
            findNavControllerSafety()?.navigate(R.id.action_recordPermissionFragment_to_homeFragment)
        }

    override fun getContentViewId() = R.layout.fragment_permission

    override fun initializeViews() {
        isRecordPermission = arguments?.containsKey(KEY_RECORD_PERMISSION) ?: false

        if(isRecordPermission){
            dataBinding.txtTitle.text = getString(R.string.record_permission)
            dataBinding.txtMsg.text = getString(R.string.label_record_permission)

            dataBinding.btnNext.text = getString(R.string.allow_record)
        }
    }

    override fun registerListeners() {
        dataBinding.btnNext.setOnClickListener {
            if(isRecordPermission){
                requestAudioPermissionLauncher.launch("android.permission.RECORD_AUDIO")
            } else {
                requestPermissionLauncher.launch("android.permission.POST_NOTIFICATIONS")
            }
        }

        dataBinding.btnNotNow.setOnClickListener {
            if(isRecordPermission){
                findNavControllerSafety()?.navigate(R.id.action_recordPermissionFragment_to_homeFragment)
            } else {
                val bundle = Bundle()
                bundle.putBoolean(KEY_RECORD_PERMISSION, true)
                findNavControllerSafety()?.navigate(R.id.action_permissionFragment_to_recordPermissionFragment, bundle)
            }
        }
    }

    override fun initializeData() {

    }

    companion object {
        const val KEY_RECORD_PERMISSION = "KEY_RECORD_PERMISSION"
    }
}