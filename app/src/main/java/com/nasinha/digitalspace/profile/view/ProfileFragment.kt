package com.nasinha.digitalspace.profile.view

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.authentication.viewmodel.AuthenticatorViewModel
import com.nasinha.digitalspace.profile.viewmodel.ProfileViewModel
import com.nasinha.digitalspace.utils.AuthUtil
import com.nasinha.digitalspace.utils.AuthUtil.getUserEmail
import com.nasinha.digitalspace.utils.AuthUtil.getUserId
import com.nasinha.digitalspace.utils.AuthUtil.getUserImage
import com.nasinha.digitalspace.utils.AuthUtil.getUserName
import com.nasinha.digitalspace.utils.AuthUtil.getUserProvider
import com.nasinha.digitalspace.utils.AuthUtil.hideKeyboard
import com.nasinha.digitalspace.utils.AuthUtil.saveUserImage
import com.nasinha.digitalspace.utils.AuthUtil.validadeEmail
import com.nasinha.digitalspace.utils.AuthUtil.validateName
import com.nasinha.digitalspace.utils.Constants
import com.nasinha.digitalspace.utils.Constants.PICK_IMAGE_REQUEST_CODE
import com.nasinha.digitalspace.utils.Constants.READ_STORAGE_PERMISSION_CODE
import com.nasinha.digitalspace.utils.DrawerUtils.lockDrawer
import com.squareup.picasso.Picasso

class ProfileFragment : Fragment() {
    private lateinit var _view: View
    private lateinit var _authenticatorViewModel: AuthenticatorViewModel
    private lateinit var _profileViewModel: ProfileViewModel
    private lateinit var _navController: NavController
    private var _selectedImageUri: Uri? = null
    private var _userId: String? = null
    private var _userImageUrl: String? = null
    private var _userName: String? = null
    private var _userEmail: String? = null
    private var _userProvider: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lockDrawer(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _view = view
        _navController = findNavController()

        profileViewModel()
        authenticatorViewModel()
        initViewModel()
        getUserInfo()
        imageListener()
        editBtnsListener()
        confirmNameBtnListener()
        confirmEmailBtnListener()
        backBtnHandler()
    }

    private fun profileViewModel() {
        _profileViewModel = ViewModelProvider(this).get(
            ProfileViewModel::class.java
        )
    }

    private fun authenticatorViewModel() {
        _authenticatorViewModel = ViewModelProvider(this).get(
            AuthenticatorViewModel::class.java
        )
    }

    private fun initViewModel() {
        _profileViewModel.stateUserName.observe(viewLifecycleOwner, { state ->
            state?.let { result ->
                hideKeyboard(_view)
                if (result) {
                    snackBarMessage(getString(R.string.nome_atualizado))
                    nameTextInputVisibilityToggle()
                    nameEditBtnToggle()
                    _userName = getUserName(requireActivity())
                }
            }
        })

        _profileViewModel.stateUserEmail.observe(viewLifecycleOwner, { state ->
            hideKeyboard(_view)
            state?.let {
                if (it) {
                    snackBarMessage(getString(R.string.email_atualizado_verificar))
                    emailTextInputVisibilityToggle()
                    navigateHome(it)
                }
            }
        })

        _profileViewModel.stateUserImage.observe(viewLifecycleOwner, { state ->
            state?.let {
                snackBarMessage("Imagem atualizada")
                saveUserImage(_view.context, state)
                hideConfirmImageButton()
            }
        })

        _profileViewModel.stateLoading.observe(viewLifecycleOwner, { state ->
            state?.let {
                showLoading(it)
            }
        })

        _profileViewModel.error.observe(viewLifecycleOwner, { e ->
            snackBarMessage(e)
        })
    }

    private fun getUserInfo() {
        _userId = getUserId(requireActivity())
        _userName = getUserName(requireActivity())
        _userEmail = getUserEmail(requireActivity())
        _userProvider = getUserProvider(requireActivity())
        _userImageUrl = getUserImage(requireActivity())
    }

    private fun imageListener() {
        val imageView = _view.findViewById<ImageView>(R.id.ivImageProfile)
        val imageOverlayView = _view.findViewById<ImageView>(R.id.ivImageOverlayProfile)
        if (_userImageUrl.isNullOrEmpty() || _userImageUrl == "null") {
            Picasso.get().load(R.drawable.user_placeholder).into(imageView)
        } else {
            Picasso.get().load(_userImageUrl).into(imageView)
        }

        imageOverlayView.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    _view.context,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                showImagePicker()
            } else {
                checkReadExternalStoragePermission(
                    _view,
                    READ_STORAGE_PERMISSION_CODE
                )
            }
        }
    }

    private fun showImagePicker() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST_CODE)
    }

    private fun checkReadExternalStoragePermission(view: View, requestCode: Int) {
        val permission = Manifest.permission.READ_EXTERNAL_STORAGE
        val listPermissionsNeeded: MutableList<String> = ArrayList()
        val result = ContextCompat.checkSelfPermission(view.context, permission)

        if (result != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(permission)

            requestPermissions(
                listPermissionsNeeded.toTypedArray(),
                requestCode
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == READ_STORAGE_PERMISSION_CODE
            && grantResults.contains(PackageManager.PERMISSION_GRANTED)
        ) {
            showImagePicker()
        } else {
            Snackbar.make(_view, getString(R.string.permissao_negada), Snackbar.LENGTH_SHORT)
                .show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE_REQUEST_CODE && data!!.data != null) {
            _selectedImageUri = data.data

            val profileImageView = _view.findViewById<ImageView>(R.id.ivImageProfile)
            Picasso.get().load(_selectedImageUri).fit().centerCrop().into(profileImageView)

            val confirmPhotoBtn = _view.findViewById<MaterialButton>(R.id.mbConfirmPhotoProfile)

            showConfirmImageButtonToggle()

            confirmPhotoBtn.setOnClickListener {
                _profileViewModel.updateUserPhoto(_view, _selectedImageUri)
            }
        }
    }

    private fun editBtnsListener() {
        val nameTextInputView = _view.findViewById<TextInputEditText>(R.id.tietNameProfile)
        val emailTextInputView = _view.findViewById<TextInputEditText>(R.id.tietEmailProfile)

        nameTextInputView.setText(_userName)
        emailTextInputView.setText(_userEmail)

        val editPhotoBtn = _view.findViewById<MaterialButton>(R.id.mbConfirmPhotoProfile)
        val editNameBtn = _view.findViewById<ImageButton>(R.id.ibEditNameProfile)
        val editEmailBtn = _view.findViewById<ImageButton>(R.id.ibEditEmailProfile)

        editPhotoBtn.setOnClickListener {

        }
        editNameBtn.setOnClickListener {
            nameTextInputVisibilityToggle()
            nameEditBtnToggle()
        }

        if (getUserProvider(_view.context) == Constants.PASSWORD) {
            editEmailBtn.isActivated = true

            editEmailBtn.setOnClickListener {
                emailTextInputVisibilityToggle()
                emailEditBtnToggle()
            }
        } else {
            editEmailBtn.isEnabled = false
        }
    }

    private fun nameTextInputVisibilityToggle() {
        val nameTextInputView = _view.findViewById<TextInputEditText>(R.id.tietNameProfile)
        nameTextInputView.isEnabled = !nameTextInputView.isEnabled
    }

    private fun emailTextInputVisibilityToggle() {
        val emailTextInputView = _view.findViewById<TextInputEditText>(R.id.tietEmailProfile)
        emailTextInputView.isEnabled = !emailTextInputView.isEnabled
    }

    private fun confirmNameBtnListener() {
        val confirmNameBtn = _view.findViewById<ImageButton>(R.id.ibConfirmNameProfile)

        confirmNameBtn.setOnClickListener {
            val nameTextInputView = _view.findViewById<TextInputEditText>(R.id.tietNameProfile)
            hideKeyboard(_view)

            when {
                !validateName(nameTextInputView.text.toString()) -> {
                    snackBarMessage(_view.context.getString(R.string.error_vazio))
                }
                (nameTextInputView.text.toString() == _userName) -> {
                    nameTextInputVisibilityToggle()
                    nameEditBtnToggle()
                }
                else -> {
                    _profileViewModel.updateUserName(
                        _view,
                        nameTextInputView.text.toString()
                    )
                }
            }
        }
    }

    private fun confirmEmailBtnListener() {
        val confirmEmailBtn = _view.findViewById<ImageButton>(R.id.ibConfirmEmailProfile)

        confirmEmailBtn.setOnClickListener {
            val emailTextInputView = _view.findViewById<TextInputEditText>(R.id.tietEmailProfile)
            hideKeyboard(_view)
            when {
                !validadeEmail(_view.context, emailTextInputView.text.toString()) -> {
                    snackBarMessage(getString(R.string.campo_invalido))
                }
                (emailTextInputView.text.toString() == _userEmail) -> {
                    emailTextInputVisibilityToggle()
                    emailEditBtnToggle()
                    snackBarMessage(getString(R.string.email_confirmado))
                }
                else -> {
                    _profileViewModel.updateUserEmail(
                        _view,
                        emailTextInputView.text.toString()
                    )
                }
            }
        }
    }

    private fun nameEditBtnToggle() {
        val confirmNameBtn = _view.findViewById<ImageButton>(R.id.ibConfirmNameProfile)
        val editNameBtn = _view.findViewById<ImageButton>(R.id.ibEditNameProfile)

        when {
            confirmNameBtn.isVisible -> {
                confirmNameBtn.visibility = View.GONE
                editNameBtn.visibility = View.VISIBLE
            }
            else -> {
                confirmNameBtn.visibility = View.VISIBLE
                editNameBtn.visibility = View.GONE
            }
        }
    }

    private fun emailEditBtnToggle() {
        val confirmEmailBtn = _view.findViewById<ImageButton>(R.id.ibConfirmEmailProfile)
        val editEmailBtn = _view.findViewById<ImageButton>(R.id.ibEditEmailProfile)

        when {
            confirmEmailBtn.isVisible -> {
                confirmEmailBtn.visibility = View.GONE
                editEmailBtn.visibility = View.VISIBLE
            }
            else -> {
                confirmEmailBtn.visibility = View.VISIBLE
                editEmailBtn.visibility = View.GONE
            }
        }
    }

    private fun showConfirmImageButtonToggle() {
        val confirmPhotoBtn = _view.findViewById<MaterialButton>(R.id.mbConfirmPhotoProfile)
        confirmPhotoBtn.visibility = View.VISIBLE
    }

    private fun hideConfirmImageButton() {
        val confirmPhotoBtn = _view.findViewById<MaterialButton>(R.id.mbConfirmPhotoProfile)
        confirmPhotoBtn.visibility = View.GONE
    }

    private fun navigateHome(isUpdated: Boolean) {
        if (isUpdated) {
            Handler(Looper.getMainLooper()).postDelayed({
                AuthUtil.hideKeyboard(_view)
                _authenticatorViewModel.signOutUser(requireActivity())
                _navController.navigate(R.id.action_profileFragment_to_loginFragment)
            }, 2000)
        }
    }

    private fun snackBarMessage(message: String) {
        Snackbar.make(
            _view,
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun backBtnHandler() {
        val backBtn = _view.findViewById<ImageButton>(R.id.ibBackProfile)
        backBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun showLoading(status: Boolean) {
        val loadingBtn = _view.findViewById<ProgressBar>(R.id.pbLoadProfile)
        when {
            status -> {
                loadingBtn.visibility = View.VISIBLE
            }
            else -> {
                loadingBtn.visibility = View.GONE
            }
        }
    }
}