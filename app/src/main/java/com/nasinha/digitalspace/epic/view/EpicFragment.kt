package com.nasinha.digitalspace.epic.view


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.epic.model.EpicResponseModel
import com.nasinha.digitalspace.epic.repository.EpicRepository
import com.nasinha.digitalspace.epic.viewmodel.EpicViewModel
import com.nasinha.digitalspace.exploration.utils.DrawerUtils.lockDrawer
import com.squareup.picasso.Picasso


class EpicFragment : Fragment() {
    private lateinit var _view: View
    private lateinit var imageId: String

    private lateinit var yearId: String
    private lateinit var monthId: String
    private lateinit var dayId: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_epic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lockDrawer(requireActivity())
        _view = view

        val viewModel = ViewModelProvider(
            this, EpicViewModel.EpicViewModelFactory(
                EpicRepository()
            )
        ).get(EpicViewModel::class.java)


        viewModel.getImageDay().observe(viewLifecycleOwner, {
            try {
                getImage(it as List<EpicResponseModel>?, view)

                viewModel.getlastDay().observe(viewLifecycleOwner, {
                    getLastDay(it as List<String>?, view)
                })
            } catch (e: Exception) {
                showLoading(false)
                val imgEpic = _view.findViewById<ImageView>(R.id.imgEpic)
                val txtEpic = _view.findViewById<TextView>(R.id.txtDate)
                txtEpic.text = getString(R.string.epic_message)
                Picasso.get().load(R.drawable.epic_gatinho).into(imgEpic)
            }
        })


        val navController = findNavController()

        view.findViewById<ImageButton>(R.id.btnBackEpic).setOnClickListener {
            activity?.onBackPressed()
        }

        showLoading(true)
    }


    private fun getImage(it: List<EpicResponseModel>?, view: View) {
        showLoading(false)
        imageId = it?.get(0)?.image.toString() + ".png"
    }

    @SuppressLint("SetTextI18n")
    private fun getLastDay(it: List<String>?, view: View) {
        showLoading(false)
        val respost = it?.size

        val lastday = respost?.minus(1)?.let { it1 -> it[it1] }
        val dateList = lastday?.split('-')
        yearId = dateList?.get(0).toString()
        monthId = dateList?.get(1).toString()
        dayId = dateList?.get(2).toString()

        val imgEpic = view.findViewById<ImageView>(R.id.imgEpic)
        val txtEpic = view.findViewById<TextView>(R.id.txtDate)
        val picasso = Picasso.get()

        try {

            if (it?.get(0)?.isNotEmpty() == true) {

                txtEpic.text =
                    getString(R.string.updateMessage) + "${dayId}/${monthId}/${yearId}"


                picasso.load("https://epic.gsfc.nasa.gov/archive/natural/${yearId}/${monthId}/${dayId}/png/${imageId}")
                    .into(imgEpic)

            }


        } catch (exception: Exception) {
            txtEpic.text = getString(R.string.epic_message)
            picasso.load(R.drawable.epic_gatinho).into(imgEpic)

        }


    }

    private fun showLoading(isLoading: Boolean) {
        val progressBar = _view.findViewById<LinearLayout>(R.id.llProgressEpic)
        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}