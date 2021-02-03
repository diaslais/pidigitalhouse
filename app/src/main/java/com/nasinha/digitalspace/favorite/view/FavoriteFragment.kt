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
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.snackbar.Snackbar
import com.google.mlkit.nl.translate.Translation
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.favorite.adapter.FavoriteAdapter
import com.nasinha.digitalspace.favorite.adapter.IFavorite
import com.nasinha.digitalspace.favorite.db.AppDatabase
import com.nasinha.digitalspace.favorite.entity.FavoriteEntity
import com.nasinha.digitalspace.favorite.entity.UserEntity
import com.nasinha.digitalspace.favorite.repository.FavoriteRepository
import com.nasinha.digitalspace.favorite.viewmodel.FavoriteViewModel
import com.nasinha.digitalspace.favorite.viewmodel.FavoriteViewModelFactory
import com.nasinha.digitalspace.utils.AuthUtil
import com.nasinha.digitalspace.utils.AuthUtil.getUserId
import com.nasinha.digitalspace.utils.Constants.APP_KEY
import com.nasinha.digitalspace.utils.Constants.IMAGE
import com.nasinha.digitalspace.utils.Constants.SORT_PREFS
import com.nasinha.digitalspace.utils.Constants.SWITCH_PREFS
import com.nasinha.digitalspace.utils.Constants.VIDEO
import com.nasinha.digitalspace.utils.DrawerUtils.lockDrawer
import com.nasinha.digitalspace.utils.FavoriteUtils
import com.nasinha.digitalspace.utils.FavoriteUtils.getOrderPrefs
import com.nasinha.digitalspace.utils.FavoriteUtils.saveOrderPrefs
import com.nasinha.digitalspace.utils.FavoriteUtils.shareImageText
import com.nasinha.digitalspace.utils.FavoriteUtils.shareVideo
import com.nasinha.digitalspace.utils.TranslateUtils
import com.nasinha.digitalspace.utils.TranslateUtils.options


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

    private val englishPortugueseTranslator = Translation.getClient(options())

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

        backBtn()
        addViewModel()
        addRecyclerView()
        initialize()
        sortBtnListener()
        sharedPreferencesCheck()
//        addNewData()
//        addNewDataToUser()
    }

    private fun addNewData() {
        val favorite1 = FavoriteEntity(
            "https://www.youtube.com/embed/8XV2-pmiyAg?rel=0",
            "Moon Phases in 2021",
            "",
            "What will the Moon phase be on your birthday this year?  It is hard to predict because the Moon's appearance changes nightly.  As the Moon orbits the Earth, the half illuminated by the Sun first becomes increasingly visible, then decreasingly visible. The featured video animates images taken by NASA's Moon-orbiting Lunar Reconnaissance Orbiter to show all 12 lunations that appear this year, 2021. A single lunation describes one full cycle of our Moon, including all of its phases. A full lunation takes about 29.5 days, just under a month (moon-th). As each lunation progresses, sunlight reflects from the Moon at different angles, and so illuminates different features differently.  During all of this, of course, the Moon always keeps the same face toward the Earth. What is less apparent night-to-night is that the Moon's apparent size changes slightly, and that a slight wobble called a libration occurs as the Moon progresses along its elliptical orbit.   APOD online webinar January 12: Free registration, hosted by Amateur Astronomers Association of New York.",
            "",
            "2021-01-11",
            "video"
        )
        _favoriteViewModel.addFavorite(favorite1).observe(viewLifecycleOwner, {})
        val favorite2 = FavoriteEntity(
            "https://apod.nasa.gov/apod/image/2101/OldMan_Guerra_960_lines.jpg",
            "A Historic Brazilian Constellation",
            "",
            "The night sky is filled with stories. Cultures throughout history have projected some of their most enduring legends onto the stars above. Generations of people see these stellar constellations, hear the associated stories, and pass them down. Featured here is the perhaps unfamiliar constellation of the Old Man, long recognized by the Tupi peoples native to regions of South America now known as Brazil.  The Old Man, in more modern vernacular, may be composed of the Hyades star cluster as his head and the belt of Orion as part of one leg.  Tupi folklore relates that the other leg was cut off by his unhappy wife, causing it to end at the orange star now known as Betelgeuse. The Pleiades star cluster, on the far left, can be interpreted as a head feather. In the featured image, the hobbled Old Man is mirrored by a person posing in the foreground.  Folklore of the night sky is important for many reasons, including that it records cultural heritage and documents the universality of human intelligence and imagination.    APOD in world languages: Arabic, Catalan, Chinese (Beijing), Chinese (Taiwan), Croatian, Czech, Dutch, Farsi, French, German, Hebrew, Indonesian, Japanese, Korean, Montenegrin, Polish, Russian, Serbian, Slovenian,  Spanish, Taiwanese, Turkish, Turkish, and  Ukrainian  APOD online webinar January 12: Free registration, hosted by Amateur Astronomers Association of New York.",
            "",
            "2021-01-12",
            "image"
        )
        _favoriteViewModel.addFavorite(favorite2).observe(viewLifecycleOwner, {})
        val favorite3 = FavoriteEntity(
            "https://www.youtube.com/embed/V0UBZh6Hf-s?rel=0",
            "Found on the Moon: Candidate for Oldest Known Earth Rock",
            "",
            "Was the oldest known rock on Earth found on the Moon? Quite possibly.  The story opens with the Apollo 14 lunar mission. Lunar sample 14321, a large rock found in Cone crater by astronaut Alan Shepard, when analyzed back on Earth, was found to have a fragment that was a much better match to Earth rocks than other Moon rocks.  Even more surprising, that rock section has recently been dated back 4 billion years, making it older, to within measurement uncertainty, than any rock ever found on Earth.  A leading hypothesis now holds that an ancient comet or asteroid impact launched Earth rocks into the Solar System, some of which fell back to the Moon, became mixed with heated lunar soil and other rocks, cooled, and re-fragmented.  The video features an internal X-ray scan of 14321 showing multiple sections with markedly different chemistries. Moon rocks will continue to be studied to learn a more complete history of the Moon, the Earth, and the early Solar System.  Friday marks the 50th Anniversary of the Apollo 14 landing on the Moon.",
            "",
            "2021-02-03",
            "video"
        )
        _favoriteViewModel.addFavorite(favorite3).observe(viewLifecycleOwner, {})
    }

    private fun addNewDataToUser() {
        val userId = getUserId(requireActivity())
        val user3 = UserEntity("https://www.youtube.com/embed/V0UBZh6Hf-s?rel=0", userId!!)
        val user2 = UserEntity("https://www.youtube.com/embed/8XV2-pmiyAg?rel=0", userId!!)
        val user1 = UserEntity(
            "https://apod.nasa.gov/apod/image/2101/OldMan_Guerra_960_lines.jpg",
            userId
        )
        _favoriteViewModel.addUserFavorite(user3).observe(viewLifecycleOwner, {})
        _favoriteViewModel.addUserFavorite(user2).observe(viewLifecycleOwner, {})
        _favoriteViewModel.addUserFavorite(user1).observe(viewLifecycleOwner, {})
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
        if (_favoriteList.isEmpty()) {
            val userId = AuthUtil.getUserId(requireActivity().application)!!

            _favoriteViewModel.getUserWithFavorites(userId).observe(viewLifecycleOwner, {
                val favorites = it.map { userWithFavorites -> userWithFavorites.favorites[0] }
                addAllFavorites(favorites)
            })
        }
    }

    private fun addAllFavorites(favoriteList: List<FavoriteEntity>) {
        _favoriteList.addAll(favoriteList)
        Log.d("favorito", favoriteList.size.toString())
        sortCheckHandler(getOrderPrefs(requireActivity()))
        checkTranslationPrefs(favoriteList)
    }

    private fun checkTranslationPrefs(favoriteList: List<FavoriteEntity>) {
        val checkPrefs = TranslateUtils.getCheckPrefs(requireActivity())

        if (checkPrefs) {
            favoriteList.map { favorite ->
                val index = favoriteList.indexOf(favorite)

                if (!favorite.title.isNullOrEmpty() && favorite.titleBr.isNullOrEmpty()) {
                    lifecycle.addObserver(englishPortugueseTranslator)
                    englishPortugueseTranslator.translate(favorite.title!!)
                        .addOnSuccessListener { result ->
                            favorite.titleBr = result
                            _favoriteAdapter.notifyItemChanged(index)
                        }.addOnFailureListener {}
                }
            }
        }
    }

    private fun deleteOneFavoriteDb(position: Int, favorite: FavoriteEntity) {
        _favoriteViewModel.deleteFavoriteItem(
            favorite.image,
            getUserId(requireActivity())!!
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
        alertDialog.setMessage(getString(R.string.voce_quer_mesmo_item))
        alertDialog.setPositiveButton(getString(R.string.sim)) { _, _ ->
            deleteOneFavoriteDb(position, favorite)
            snackBarMessage(getString(R.string.Item_removido))
        }
        alertDialog.setNegativeButton(getString(R.string.nao)) { dialog, _ ->
            dialog.dismiss()
        }
        alertDialog.show()
    }

    override fun iFavoriteShare(favorite: FavoriteEntity, imageView: ImageView) {
        when (favorite.type) {
            IMAGE -> shareImageText(requireActivity(), _view, imageView, null)
            VIDEO -> shareVideo(_view, favorite.image)
        }
    }

    private fun sortBtnListener() {
        val sortBtn = _view.findViewById<CheckBox>(R.id.cbOrderFavorite)

        sortBtn.setOnCheckedChangeListener { _, isChecked ->
            sortCheckHandler(isChecked)
        }
    }

    private fun sortCheckHandler(isChecked: Boolean) {
        val sortBtn = _view.findViewById<CheckBox>(R.id.cbOrderFavorite)

        if (_favoriteList.isNotEmpty()) {
            if (isChecked) {
                _favoriteList.sortByDescending { FavoriteUtils.stringToDate(it.date) }
            } else {
                _favoriteList.sortBy { FavoriteUtils.stringToDate(it.date) }
            }
            saveOrderPrefs(requireActivity(), isChecked)
            sortBtn.isChecked = isChecked
            _favoriteAdapter.notifyDataSetChanged()
        }
    }

    private fun snackBarMessage(message: String) {
        Snackbar.make(_view, message, Snackbar.LENGTH_LONG).show()
    }
}