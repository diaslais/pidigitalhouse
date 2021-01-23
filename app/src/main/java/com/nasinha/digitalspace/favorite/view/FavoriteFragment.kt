package com.nasinha.digitalspace.favorite.view

import android.app.AlertDialog
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
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
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.favorite.adapter.FavoriteAdapter
import com.nasinha.digitalspace.favorite.adapter.IFavorite
import com.nasinha.digitalspace.favorite.db.AppDatabase
import com.nasinha.digitalspace.favorite.entity.FavoriteEntity
import com.nasinha.digitalspace.favorite.repository.FavoriteRepository
import com.nasinha.digitalspace.favorite.viewmodel.FavoriteViewModel
import com.nasinha.digitalspace.favorite.viewmodel.FavoriteViewModelFactory
import com.nasinha.digitalspace.utils.AuthUtil
import com.nasinha.digitalspace.utils.Constants.APP_KEY
import com.nasinha.digitalspace.utils.Constants.IMAGE
import com.nasinha.digitalspace.utils.Constants.SORT_PREFS
import com.nasinha.digitalspace.utils.Constants.SWITCH_PREFS
import com.nasinha.digitalspace.utils.DrawerUtils.lockDrawer
import com.nasinha.digitalspace.utils.FavoriteUtils
import kotlinx.coroutines.launch


class FavoriteFragment : Fragment(), IFavorite {
    private lateinit var _view: View
    private lateinit var _favoriteViewModel: FavoriteViewModel
    private lateinit var _listRecyclerView: RecyclerView
    private lateinit var _navController: NavController
    private lateinit var _favoriteAdapter: FavoriteAdapter
    private lateinit var iFavorite: IFavorite
    private lateinit var _prefs: SharedPreferences
    private var _translateChecked = false
    private var _sortChecked = false

    private var _favoriteList = mutableListOf<FavoriteEntity>()

    val options = TranslatorOptions.Builder()
        .setSourceLanguage(TranslateLanguage.ENGLISH)
        .setTargetLanguage(TranslateLanguage.PORTUGUESE)
        .build()

    private val englishPortugueseTranslator = Translation.getClient(options)

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
        lockDrawer(requireActivity())
        _view = view

        _navController = findNavController()

        sharedPreferencesCheck()
        backBtn()
        addViewModel()
        addRecyclerView()
        initialize()
    }

    private fun sharedPreferencesCheck() {
        _prefs = _view.context.getSharedPreferences(APP_KEY, MODE_PRIVATE)

        _translateChecked = _prefs.getBoolean(SWITCH_PREFS, false)
        _sortChecked = _prefs.getBoolean(SORT_PREFS, false)
    }

    private fun backBtn() {
        val btnBackView = _view.findViewById<ImageButton>(R.id.ibBackFavorite)

        btnBackView.setOnClickListener {
            requireActivity().onBackPressed()
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
        _listRecyclerView = _view.findViewById(R.id.recyclerViewFavorite)
        val manager = LinearLayoutManager(_view.context)

        _favoriteAdapter = FavoriteAdapter(_favoriteList, iFavorite, _translateChecked) {
            val bundle = bundleOf(IMAGE to it.image)
            _navController.navigate(R.id.action_favoriteFragment_to_favoriteScreenFragment, bundle)
        }

        _listRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = manager
            adapter = _favoriteAdapter
        }
    }

    private fun initialize() {
        val sortBtn = _view.findViewById<CheckBox>(R.id.cbOrderFavorite)

        if (_favoriteList.isEmpty()) {
            val userId = AuthUtil.getUserId(requireActivity().application)!!

            _favoriteViewModel.getUserWithFavorites(userId).observe(viewLifecycleOwner, {
                val favorites = it.map { userWithFavorites -> userWithFavorites.favorites[0] }
                addAllFavorites(favorites)
                sortBtnHandler(sortBtn, _sortChecked)
            })
        }
        sortBtnHandler(sortBtn, _sortChecked)
    }

    private fun addAllFavorites(list: List<FavoriteEntity>) {
        _favoriteList.addAll(list)
        Log.d("favorito", list.size.toString())
        _favoriteAdapter.notifyDataSetChanged()
        checkTranslationPrefs()
    }

    private fun checkTranslationPrefs() {
        val prefs = requireActivity().getSharedPreferences(
            APP_KEY,
            MODE_PRIVATE
        )
        val checkPrefs = prefs?.getBoolean(SWITCH_PREFS, false)

        if (checkPrefs == true) {
            _favoriteList.map { favorite ->
                val index = _favoriteList.indexOf(favorite)

                if (!favorite.title.isNullOrEmpty() && favorite.titleBr.isNullOrEmpty()) {
                    englishPortugueseTranslator.translate(favorite.title!!)
                        .addOnSuccessListener { result ->
                            _favoriteViewModel.updateTitleBr(favorite.image, result)
                                .observe(viewLifecycleOwner, {
                                    favorite.titleBr = result
                                    _favoriteAdapter.notifyItemChanged(index)
                                })
                        }.addOnFailureListener { _ ->
                            favorite.title = favorite.title
                        }
                }

                if (!favorite.text.isNullOrEmpty() && favorite.textBr.isNullOrEmpty()) {
                    englishPortugueseTranslator.translate(favorite.text!!)
                        .addOnSuccessListener { result ->
                            _favoriteViewModel.updateTextBr(favorite.image, result)
                                .observe(viewLifecycleOwner, {
                                    favorite.textBr = result
                                    _favoriteAdapter.notifyItemChanged(index)
                                })
                        }.addOnFailureListener { _ ->
                            favorite.text = favorite.text
                        }
                }
            }
        }
    }

    private fun deleteOneFavoriteDb(position: Int, favorite: FavoriteEntity) {
        _favoriteViewModel.deleteFavoriteItem(
            favorite.image,
            AuthUtil.getUserId(requireActivity())!!
        ).observe(viewLifecycleOwner, {
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
            val imageBitmap = FavoriteUtils.getBitmapFromView(_view, favorite.image)
            val text = favorite.text.toString()
            activity?.let { FavoriteUtils.shareImageText(it, _view, imageBitmap, text) }
        }
    }

    private fun sortBtnHandler(sortBtn: CheckBox, prefsChecked: Boolean) {
        sortBtn.isChecked = prefsChecked

        sortCheckHandler(sortBtn.isChecked)

        sortBtn.setOnCheckedChangeListener { _, isChecked ->
            sortCheckHandler(isChecked)
        }
    }

    private fun sortCheckHandler(isChecked: Boolean) {
        if (isChecked) {
            _favoriteList.sortByDescending { FavoriteUtils.stringToDate(it.date) }
            _prefs.edit().putBoolean(SORT_PREFS, isChecked).apply()
        } else {
            _favoriteList.sortBy { FavoriteUtils.stringToDate(it.date) }
            _prefs.edit().putBoolean(SORT_PREFS, isChecked).apply()
        }
        _favoriteAdapter.notifyDataSetChanged()
    }

}