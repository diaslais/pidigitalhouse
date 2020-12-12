package com.nasinha.digitalspace.planetsmenu.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nasinha.digitalspace.R

class BottomsheetFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottomsheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textBottomString = arguments?.getString("text")
        val titleBottomString = arguments?.getString("title")

        val textBottomSheet = view.findViewById<TextView>(R.id.tvTextBottomsheet)
        val titleBottomSheet = view.findViewById<TextView>(R.id.tvTitleBottomsheet)

        textBottomSheet.text = textBottomString
        titleBottomSheet.text = titleBottomString
    }
}