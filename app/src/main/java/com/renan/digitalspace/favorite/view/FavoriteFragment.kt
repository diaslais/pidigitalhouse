package com.renan.digitalspace.favorite.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.renan.digitalspace.R
import com.renan.digitalspace.favorite.adapter.FavoriteAdapter
import com.renan.digitalspace.favorite.adapter.IFavorite
import com.renan.digitalspace.favorite.db.AppDatabase
import com.renan.digitalspace.favorite.entity.FavoriteEntity
import com.renan.digitalspace.favorite.repository.FavoriteRepository
import com.renan.digitalspace.favorite.viewmodel.FavoriteViewModel
import com.renan.digitalspace.favorite.viewmodel.FavoriteViewModelFactory

class FavoriteFragment : Fragment(), IFavorite {
    private lateinit var _view: View
    private lateinit var _favoriteViewModel: FavoriteViewModel
    private lateinit var _list: RecyclerView
    private lateinit var _navController: NavController
    private lateinit var _favoriteAdapter: FavoriteAdapter
    private lateinit var iFavorite: IFavorite

    private var _favoriteList = mutableListOf<FavoriteEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iFavorite = this
    }

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

//        criado view model
        _favoriteViewModel = ViewModelProvider(
            this,
            FavoriteViewModelFactory(
                FavoriteRepository(
                    AppDatabase.getDatabase(view.context).favoriteDao()
                )
            )
        ).get(FavoriteViewModel::class.java)

//        criado recycler view
        _list = view.findViewById(R.id.recyclerViewFavorite)
        val manager = LinearLayoutManager(view.context)

//        passagem da lista de favoritos pro adapter
        _favoriteAdapter = FavoriteAdapter(_favoriteList, iFavorite) {
            Toast.makeText(this.context, it.date, Toast.LENGTH_LONG).show()
        }

//        passagem de informações pro recyclerview
        _list.apply {
            setHasFixedSize(true)

            layoutManager = manager
            adapter = _favoriteAdapter
        }

//        activeAll()
        initalize()
//        addFavoriteInitializer()
//        deleteAll()
    }

    private fun initalize() {
        _favoriteViewModel.getAllFavorite().observe(viewLifecycleOwner, {
            _favoriteAdapter.addFavorites(it)
        })
    }

    private fun addFavorite(favorite: FavoriteEntity) {
        _favoriteViewModel.addFavorite(favorite).observe(viewLifecycleOwner, {
            _favoriteAdapter.addFavorite(it)
        })
    }

    private fun activeAll() {
        _favoriteViewModel.updateActiveAll(true, false).observe(viewLifecycleOwner, {})
    }


    private fun backBtn() {
        val btnBackView = _view.findViewById<ImageButton>(R.id.ibBackFavorite)

        btnBackView.setOnClickListener {
            _navController.navigate(R.id.action_favoriteFragment_to_exploracaoFragment)
        }
    }

    private fun addFavoriteInitializer() {
        addFavorite(
            FavoriteEntity(
                0,
                "https://www.nasa.gov/sites/default/files/styles/image_card_4x3_ratio/public/thumbnails/image/iss064e007861.jpg",
                "Relaxing Inside the Space Station's Window to the World",
                "2020-12-03",
                true
            )
        )
        addFavorite(
            FavoriteEntity(
                0,
                "https://www.nasa.gov/sites/default/files/styles/image_card_4x3_ratio/public/thumbnails/image/herbig-haro-jet.jpg",
                "Awakening Newborn Stars",
                "2020-12-02",
                true
            )
        )
        addFavorite(
            FavoriteEntity(
                0,
                "https://www.nasa.gov/sites/default/files/styles/image_card_4x3_ratio/public/thumbnails/image/pia20176_main.jpg",
                "Earth May Be Surrounded by Hairy Dark Matter",
                "2020-12-01",
                true
            )
        )
    }

    private fun deleteAll() {
        _favoriteViewModel.deleteAll().observe(viewLifecycleOwner, {
            _favoriteAdapter.deleteAll()
        })
    }

    override fun changedFavorite(position: Int, favorite: FavoriteEntity) {

        _favoriteViewModel.updateOneFavorite(favorite).observe(viewLifecycleOwner, {
            _favoriteAdapter.updateOne(position)
        })
    }
}