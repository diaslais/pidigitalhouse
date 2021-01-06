package com.nasinha.digitalspace.profile.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.google.android.material.textfield.TextInputEditText
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.authentication.AppUtil
import com.nasinha.digitalspace.exploration.utils.DrawerUtils.lockDrawer

class ProfileFragment : Fragment() {
    private lateinit var _view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lockDrawer(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _view = view

        setUserInfo()
        backBtnHandler()
    }

    private fun setUserInfo() {
        val name = _view.findViewById<TextInputEditText>(R.id.tietNameProfile)
        val email = _view.findViewById<TextInputEditText>(R.id.tietEmailProfile)
        val nameText = AppUtil.getUserName(requireActivity())
        val emailText = AppUtil.getUserEmail(requireActivity())

        if (!nameText.isNullOrEmpty()) {
            name.setText(nameText)
        }

        if (!emailText.isNullOrEmpty()) {
            email.setText(emailText)
        }
    }

    private fun backBtnHandler() {
        val backBtn = _view.findViewById<ImageButton>(R.id.ibBackProfile)
        backBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}