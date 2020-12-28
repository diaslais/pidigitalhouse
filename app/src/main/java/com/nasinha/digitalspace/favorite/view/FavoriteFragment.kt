package com.nasinha.digitalspace.favorite.view

import android.app.AlertDialog
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.authentication.AppUtil
import com.nasinha.digitalspace.authentication.viewmodel.AuthenticatorViewModel
import com.nasinha.digitalspace.favorite.adapter.FavoriteAdapter
import com.nasinha.digitalspace.favorite.adapter.IFavorite
import com.nasinha.digitalspace.favorite.db.AppDatabase
import com.nasinha.digitalspace.favorite.entity.FavoriteEntity
import com.nasinha.digitalspace.favorite.repository.FavoriteRepository
import com.nasinha.digitalspace.favorite.utils.FavoriteUtils
import com.nasinha.digitalspace.favorite.viewmodel.FavoriteViewModel
import com.nasinha.digitalspace.favorite.viewmodel.FavoriteViewModelFactory
import kotlinx.coroutines.launch


class FavoriteFragment : Fragment(), IFavorite {
    private lateinit var _view: View
    private lateinit var _favoriteViewModel: FavoriteViewModel
    private lateinit var _list: RecyclerView
    private lateinit var _navController: NavController
    private lateinit var _favoriteAdapter: FavoriteAdapter
    private lateinit var iFavorite: IFavorite

    private var _image: Bitmap? = null

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
        addViewModel()
        addRecyclerView()
        initialize()
//        deleteAll()
    }

    private fun backBtn() {
        val btnBackView = _view.findViewById<ImageButton>(R.id.ibBackFavorite)

        btnBackView.setOnClickListener {
            _navController.navigate(R.id.action_favoriteFragment_to_explorationFragment)
        }
    }

    private fun addViewModel() {
        _favoriteViewModel = ViewModelProvider(
            this,
            FavoriteViewModelFactory(
                FavoriteRepository(
                    AppDatabase.getDatabase(_view.context).favoriteDao()
                )
            )
        ).get(FavoriteViewModel::class.java)
    }

    private fun addRecyclerView() {
        _list = _view.findViewById(R.id.recyclerViewFavorite)
        val manager = LinearLayoutManager(_view.context)

        _favoriteAdapter = FavoriteAdapter(_favoriteList, iFavorite) {
            val bundle = bundleOf(
                "image" to it.image,
                "title" to it.title,
                "text" to it.text,
                "date" to FavoriteUtils.dateModifier(it.date)
            )
            _navController.navigate(R.id.action_favoriteFragment_to_favoriteScreenFragment, bundle)
        }

        _list.apply {
            setHasFixedSize(true)
            layoutManager = manager
            adapter = _favoriteAdapter
        }
    }

    private fun initialize() {
        val sortBtn = _view.findViewById<CheckBox>(R.id.cbOrderFavorite)
        val prefs = _view.context.getSharedPreferences(APP_NAME, MODE_PRIVATE)
        val prefsChecked = prefs.getBoolean(SAVED_PREFS, false)

        if (_favoriteList.isEmpty()) {
            val userId = AppUtil.getUserId(requireActivity().application)!!

            _favoriteViewModel.getAllFavorite(userId).observe(viewLifecycleOwner, {
                addAllFavorites(it)
                sortBtnHandler(sortBtn, prefsChecked, prefs)
            })
        }
        sortBtnHandler(sortBtn, prefsChecked, prefs)
    }

    private fun addAllFavorites(list: List<FavoriteEntity>) {
        _favoriteList.addAll(list)
        _favoriteAdapter.notifyDataSetChanged()
    }


    private fun deleteAll() {
        _favoriteViewModel.deleteAll().observe(viewLifecycleOwner, {
            _favoriteList.clear()
            _favoriteAdapter.notifyDataSetChanged()
        })
    }

    private fun deleteOneFavoriteDb(position: Int, favorite: FavoriteEntity) {
        _favoriteViewModel.deleteOne(favorite).observe(viewLifecycleOwner, {
            _favoriteList.removeAt(position)
            _favoriteAdapter.notifyItemRemoved(position)
        })
    }

    override fun iFavoriteDelete(
        position: Int,
        favorite: FavoriteEntity,
        cardView: MaterialCardView
    ) {
        val alertDialog = AlertDialog.Builder(_view.context)
        alertDialog.setTitle(getString(R.string.excluir_favorito))
        alertDialog.setMessage(getString(R.string.voce_quer_mesmo))
        alertDialog.setPositiveButton(getString(R.string.sim)) { _, _ ->
            deleteOneFavoriteDb(position, favorite)
            Toast.makeText(_view.context, getString(R.string.Item_removido), Toast.LENGTH_SHORT)
                .show()
        }
        alertDialog.setNegativeButton(getString(R.string.nao)) { dialog, _ ->
            dialog.dismiss()
        }
        alertDialog.show()
    }

    override fun iFavoriteShare(favorite: FavoriteEntity) {
        lifecycleScope.launch {
            _image = FavoriteUtils.getBitmapFromView(_view, favorite.image)
            activity?.let { FavoriteUtils.checkPermissions(it, _view, _image) }
        }
    }

    private fun sortBtnHandler(sortBtn: CheckBox, prefsChecked: Boolean, prefs: SharedPreferences) {
        sortBtn.isChecked = prefsChecked

        sortCheckHandler(sortBtn.isChecked, prefs)

        sortBtn.setOnCheckedChangeListener { _, isChecked ->
            sortCheckHandler(isChecked, prefs)
        }
    }

    private fun sortCheckHandler(isChecked: Boolean, prefs: SharedPreferences) {
        if (isChecked) {
            _favoriteList.sortByDescending { FavoriteUtils.stringToDate(it.date) }
            prefs.edit().putBoolean(SAVED_PREFS, isChecked).apply()
        } else {
            _favoriteList.sortBy { FavoriteUtils.stringToDate(it.date) }
            prefs.edit().putBoolean(SAVED_PREFS, isChecked).apply()
        }
        _favoriteAdapter.notifyDataSetChanged()
    }

    companion object {
        const val APP_NAME = "SharedPreferences"
        const val SAVED_PREFS = "SAVED_PREFS"
    }
}