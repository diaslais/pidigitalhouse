package com.renan.digitalspace.epic.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.renan.digitalspace.R
import com.renan.digitalspace.epic.model.EpicResponseModel
import com.renan.digitalspace.epic.repository.EpicRepository
import com.renan.digitalspace.epic.viewmodel.EpicViewModel
import com.squareup.picasso.Picasso


class EpicFragment : Fragment() {
    lateinit var imageId: String

    lateinit var yearId: String
    lateinit var monthId: String
    lateinit var dayId: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_epic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(
            this, EpicViewModel.EpicViewModelFactory(
                EpicRepository()
            )
        ).get(EpicViewModel::class.java)

        viewModel.getImageDay().observe(viewLifecycleOwner, {
            getImage(it, view)

            viewModel.getlastDay().observe(viewLifecycleOwner, {
                getLastDay(it, view)
            })
        })

        val navController = findNavController()

        view.findViewById<ImageButton>(R.id.btnBackEpic).setOnClickListener {
            navController.navigate(R.id.action_epicFragment_to_exploracaoFragment)
        }
    }


    fun getImage(it: List<EpicResponseModel>?, view: View) {
        imageId = it?.get(0)?.image.toString() + ".png"

    }

    fun getLastDay(it: List<String>?, view: View) {
        val respost = it?.size

        val lastday = respost?.minus(1)?.let { it1 -> it?.get(it1) }
        val dateList = lastday?.split('-')
        yearId = dateList?.get(0).toString()
        monthId = dateList?.get(1).toString()
        dayId = dateList?.get(2).toString()

        val txtEpic = view?.findViewById<TextView>(R.id.txtDate)
        txtEpic.text = "Última atualização de imagem: " + lastday.toString()

        val imgEpic = view?.findViewById<ImageView>(R.id.imgEpic)
        val picasso = Picasso.get()
        picasso.load("https://epic.gsfc.nasa.gov/archive/natural/${yearId}/${monthId}/${dayId}/png/${imageId}")
            .into(imgEpic)

    }


}