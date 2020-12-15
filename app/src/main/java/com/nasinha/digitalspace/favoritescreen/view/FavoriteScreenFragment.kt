package com.nasinha.digitalspace.favoritescreen.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.nasinha.digitalspace.R
import com.squareup.picasso.Picasso

class FavoriteScreenFragment : Fragment() {
    private lateinit var _view: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _view = view
        closeHandler()
        argumentsHandler()
    }

    private fun closeHandler() {
        val closeBtn = _view.findViewById<ImageButton>(R.id.ibBackFavoriteScreen)
        closeBtn.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_favoriteScreenFragment_to_favoriteFragment)
        }
    }

    private fun argumentsHandler() {
        val imageArgument = arguments?.getString("image")!!
        val titleArgument = arguments?.getString("title")
        val textArgument = arguments?.getString("text")
        val dateArgument = arguments?.getString("date")!!

        val image = _view.findViewById<ImageView>(R.id.ivImageFavoriteScreen)
        val title = _view.findViewById<TextView>(R.id.tvTitleFavoriteScreen)
        val text = _view.findViewById<TextView>(R.id.tvTextFavoriteScreen)
        val date = _view.findViewById<TextView>(R.id.tvDateFavoriteScreen)

        title.text = if (titleArgument.isNullOrEmpty()) "" else titleArgument
        text.text = if (textArgument.isNullOrEmpty()) "" else textArgument
        date.text = dateArgument

        Picasso.get().load(imageArgument).into(image)
    }
}