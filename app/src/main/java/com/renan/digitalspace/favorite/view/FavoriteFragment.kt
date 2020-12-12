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
import com.google.android.material.card.MaterialCardView
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
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
        addViewModel(view)

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

    private fun backBtn() {
        val btnBackView = _view.findViewById<ImageButton>(R.id.ibBackFavorite)

        btnBackView.setOnClickListener {
            _navController.navigate(R.id.action_favoriteFragment_to_explorationFragment)
        }
    }

    fun addViewModel(view: View) {
        _favoriteViewModel = ViewModelProvider(
            this,
            FavoriteViewModelFactory(
                FavoriteRepository(
                    AppDatabase.getDatabase(view.context).favoriteDao()
                )
            )
        ).get(FavoriteViewModel::class.java)
    }

    private fun initalize() {
        _favoriteViewModel.getAllFavorite().observe(viewLifecycleOwner, {
            addAll(it)
        })
    }

    private fun activeAll() {
        _favoriteViewModel.updateActiveAll(true, false).observe(viewLifecycleOwner, {})
    }


    // lista de dados mocados para teste
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

//    modificadores do adapter

    private fun addAll(list: List<FavoriteEntity>) {
        _favoriteList.addAll(list)
        _favoriteAdapter.notifyDataSetChanged()
    }

    private fun deleteAll() {
        _favoriteViewModel.deleteAll().observe(viewLifecycleOwner, {
            _favoriteList.clear()
            _favoriteAdapter.notifyDataSetChanged()
        })
    }

    fun addFavorite(favorite: FavoriteEntity) {
        _favoriteViewModel.addFavorite(favorite).observe(viewLifecycleOwner, {
            _favoriteList.add(favorite)
            _favoriteAdapter.notifyDataSetChanged()
        })
    }

    fun deleteOneFavorite(position: Int) {
        _favoriteList.removeAt(position)
        _favoriteAdapter.notifyItemRemoved(position)
    }

    override fun changedFavorite(
        position: Int,
        favorite: FavoriteEntity,
        cardView: MaterialCardView
    ) {
        var removeIs = true

        val btnFavorite = cardView.findViewById<ImageButton>(R.id.ibFavoriteButton)
        btnFavorite.isEnabled = false
        btnFavorite.setImageResource(R.drawable.ic_outline_star_border_24)

        val snackbar =
            Snackbar.make(_view, getString(R.string.item_removido), Snackbar.LENGTH_SHORT)
                .setAction(getString(R.string.desfazer)) {

                    removeIs = false

                    btnFavorite.isEnabled = true
                    btnFavorite.setImageResource(R.drawable.ic_baseline_star_24)


                }.addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
                    override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                        super.onDismissed(transientBottomBar, event)

                        if (removeIs) {
                            _favoriteViewModel.deleteOne(favorite).observe(viewLifecycleOwner, {
                                deleteOneFavorite(position)
                            })
                        }
                    }
                })
        snackbar.show()
    }
}