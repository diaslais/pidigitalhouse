package com.renan.digitalspace.dialogs.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.DialogFragment
import com.renan.digitalspace.R

class ChangePasswordFragment : DialogFragment() {
    private lateinit var _view: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _view = view
        closeBtn()
    }

    private fun closeBtn() {
        val btnClose = _view.findViewById<ImageButton>(R.id.ibCloseDialogPassword)
        btnClose.setOnClickListener {
            dismiss()
        }
    }
}