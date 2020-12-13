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
import java.util.Collections.addAll

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
        _view = view

        backBtn()
        addViewModel()
        addRecyclerView()
        initialize()
//        deleteAll()
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
                addDeveloperInitializer()
            } else {
                addAllDevelopers(it)
            }
        })
    }

    private fun addDeveloperInitializer() {
        addDeveloper(
            DeveloperEntity(
                1,
                "https://avatars3.githubusercontent.com/u/53455525?s=400&u=48e7cf6b27239f95a546f14b3f5284612a64d300&v=4",
                "Lais Dias",
                "email",
                "bio",
                "",
                "https://github.com/diaslais"
            )
        )
        addDeveloper(
            DeveloperEntity(
                2,
                "https://avatars0.githubusercontent.com/u/67712448?s=400&u=a7e6476cdc9e1dc46599e7f89be03ec0d1f17e52&v=4",
                "Mariana Marcelli",
                "email",
                "bio",
                "",
                "https://github.com/MarianaMarcelli"
            )
        )
        addDeveloper(
            DeveloperEntity(
                3,
                "https://avatars0.githubusercontent.com/u/52797828?s=460&u=b419f032286ae7d7f90f6a8a38e4b2e6510d3710&v=4",
                "Paulo Silva",
                "email",
                "bio",
                "",
                "https://github.com/paulo4fs"
            )
        )
        addDeveloper(
            DeveloperEntity(
                4,
                "https://avatars1.githubusercontent.com/u/41019069?s=400&u=a155e71b95b525e9bcce7b108afa45d2c41a755f&v=4",
                "Renan Damasceno",
                "email",
                "bio",
                "",
                "https://github.com/renandamasceno"
            )
        )
    }

    private fun addAllDevelopers(list: List<DeveloperEntity>) {
        _developerList.addAll(list)
        _developerAdapter.notifyDataSetChanged()
    }

    private fun addDeveloper(developer: DeveloperEntity) {
        _developerViewModel.addDeveloper(developer).observe(viewLifecycleOwner, {
            _developerList.add(developer)
            _developerAdapter.notifyDataSetChanged()
        })
    }

    private fun deleteAll() {
        _developerViewModel.deleteAll().observe(viewLifecycleOwner, {
            _developerList.clear()
            _developerAdapter.notifyDataSetChanged()
        })
    }

    override fun linkedinDeveloper(linkedin: String) {
        val linkedinUri = Uri.parse(linkedin)
        val intent = Intent(Intent.ACTION_VIEW, linkedinUri)
        startActivity(intent)
    }

    override fun githubDeveloper(github: String) {
        val linkedinUri = Uri.parse(github)
        val intent = Intent(Intent.ACTION_VIEW, linkedinUri)
        startActivity(intent)
    }
}