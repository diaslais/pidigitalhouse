package com.nasinha.digitalspace.developer.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.developer.adapter.DeveloperAdapter
import com.nasinha.digitalspace.developer.adapter.IDeveloper
import com.nasinha.digitalspace.developer.entity.DeveloperEntity
import com.nasinha.digitalspace.developer.repository.DeveloperRepository
import com.nasinha.digitalspace.developer.viewmodel.DeveloperViewModel
import com.nasinha.digitalspace.developer.viewmodel.DeveloperViewModelFactory
import com.nasinha.digitalspace.utils.DrawerUtils.lockDrawer

class DeveloperFragment : Fragment(), IDeveloper {
    private lateinit var _view: View
    private lateinit var _developerViewModel: DeveloperViewModel
    private lateinit var _list: RecyclerView
    private lateinit var _developerAdapter: DeveloperAdapter
    private lateinit var iDeveloper: IDeveloper

    private var _developerList = mutableListOf<DeveloperEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iDeveloper = this
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_developer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lockDrawer(requireActivity())
        _view = view

        backBtn()
        addViewModel()
        addRecyclerView()
        initialize()
    }

    private fun backBtn() {
        val returnBtn = _view.findViewById<ImageButton>(R.id.ibBackDeveloper)
        returnBtn.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_developerFragment_to_explorationFragment)
        }
    }

    private fun addViewModel() {
        _developerViewModel = ViewModelProvider(
            this,
            DeveloperViewModelFactory(
                DeveloperRepository(
                    com.nasinha.digitalspace.developer.db.AppDatabase.getDatabase(_view.context)
                        .developerDao()
                )
            )
        ).get(DeveloperViewModel::class.java)
    }

    private fun addRecyclerView() {
        _list = _view.findViewById(R.id.recyclerViewDeveloper)
        val manager = LinearLayoutManager(_view.context)

        _developerAdapter = DeveloperAdapter(_developerList, iDeveloper)

        _list.apply {
            setHasFixedSize(true)
            layoutManager = manager
            adapter = _developerAdapter
        }
    }

    private fun initialize() {
        _developerViewModel.getAllDeveloper().observe(viewLifecycleOwner, {
            if (it.isEmpty()) {
                setAllDevelopersDb()
            }
            addAllDevelopers(it)
        })
    }

    private fun setAllDevelopersDb() {
        _developerViewModel.setAllDevelopers().observe(viewLifecycleOwner, {
            it.forEach { developer ->
                addDeveloperDb(developer)
            }
        })
    }

    private fun addDeveloperDb(developer: DeveloperEntity) {
        _developerViewModel.addDeveloper(developer).observe(viewLifecycleOwner, {
            _developerList.add(developer)
            _developerAdapter.notifyDataSetChanged()
        })
    }

    private fun addAllDevelopers(list: List<DeveloperEntity>) {
        _developerList.addAll(list)
        _developerAdapter.notifyDataSetChanged()
    }

    override fun linkedinDeveloper(linkedin: String) {
        linkHandler(linkedin)
    }

    override fun githubDeveloper(github: String) {
        linkHandler(github)
    }

    private fun linkHandler(uri: String) {
        val linkUri = Uri.parse(uri)
        val intent = Intent(Intent.ACTION_VIEW, linkUri)
        startActivity(intent)
    }
}