package com.puxiansheng.www.ui.mine.suggest

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.puxiansheng.logic.api.API
import com.puxiansheng.www.R
import com.puxiansheng.www.databinding.FragmentUserSuggestBinding

class UserSuggestFragment : Fragment() {

    private lateinit var userSuggestViewModel: UserSuggestViewModel


    override fun onAttach(context: Context) {
        super.onAttach(context)
        userSuggestViewModel = ViewModelProvider(this)[UserSuggestViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentUserSuggestBinding.inflate(inflater).apply {
        buttonBack.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.homeNavHost).popBackStack()
        }


        inputSuggest.addTextChangedListener{ editable ->
            editable?.toString()?.let {
                userSuggestViewModel.suggesttion = it
            }}

        submitSuggest.setOnClickListener {
            if (userSuggestViewModel.suggesttion.isEmpty()) {
                Toast.makeText(requireContext(), "请输入建议！", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            userSuggestViewModel.submitSuggest()
        }


        userSuggestViewModel.toastMsg.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })

        userSuggestViewModel.suggestResult.observe(viewLifecycleOwner, Observer {
            if (it == API.CODE_SUCCESS) {
                Navigation.findNavController(requireActivity(), R.id.homeNavHost).navigateUp()
            }
        })

    }.root
}