package com.renan.digitalspace.favorite.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.renan.digitalspace.R
import com.renan.digitalspace.favorite.adapter.FavoriteAdapter
import com.renan.digitalspace.favorite.model.FavoriteModel
import com.renan.digitalspace.favorite.repository.FavoriteRepository
import com.renan.digitalspace.favorite.viewmodel.FavoriteViewModel
import com.renan.digitalspace.favorite.viewmodel.FavoriteViewModelFactory

class FavoriteFragment : Fragment() {
    private lateinit var _view: View
    private lateinit var _favoriteViewModel: FavoriteViewModel
    private lateinit var _list: RecyclerView
    private lateinit var _navController: NavController

    private lateinit var _favoriteAdapter: FavoriteAdapter

    private var _favoriteList = mutableListOf<FavoriteModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _view = view

        _navController = findNavController()

        backBtn()

        _list = view.findViewById(R.id.recyclerViewFavorite)

        val manager = LinearLayoutManager(view.context)

        _favoriteList = mutableListOf()
        _favoriteAdapter = FavoriteAdapter(_favoriteList) {
            val bundle = bundleOf(
                "id" to it.id,
                "image" to it.image,
                "title" to it.title,
                "date" to it.date,
                "active" to it.active
            )
        }

        _list.apply {
            setHasFixedSize(true)

            layoutManager = manager
            adapter = _favoriteAdapter
        }

        _favoriteViewModel = ViewModelProvider(
            this,
            FavoriteViewModelFactory(FavoriteRepository())
        ).get(FavoriteViewModel::class.java)

        _favoriteViewModel.getDataFavorite().observe(viewLifecycleOwner, {
            showList(it)
        })
    }

    private fun backBtn() {
        val btnBackView = _view.findViewById<ImageButton>(R.id.ibBackFavorite)

        btnBackView.setOnClickListener {
            _navController.navigate(R.id.action_favoriteFragment_to_exploracaoFragment)
        }
    }

    private fun showList(lista: MutableList<FavoriteModel>) {
        lista.let {
            _favoriteList.addAll(it)
        }
        _favoriteAdapter.notifyDataSetChanged()

    }
}