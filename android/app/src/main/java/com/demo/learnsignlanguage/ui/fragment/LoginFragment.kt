package com.demo.learnsignlanguage.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.demo.learnsignlanguage.databinding.FragmentLoginBinding
import com.demo.learnsignlanguage.network.NetworkResult
import com.demo.learnsignlanguage.ui.activity.MainActivity
import com.demo.learnsignlanguage.ui.viewmodel.LoginViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {
    @Inject lateinit var signInIntent : Intent
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private val viewModel by viewModels<LoginViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        initResultLauncher()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGg.setOnClickListener {
            resultLauncher.launch(signInIntent)
        }
    }

    private fun initResultLauncher() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == AppCompatActivity.RESULT_OK) {
                    val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                    try {
                        val googleSignInAccount = task.getResult(ApiException::class.java)
                        googleSignInAccount?.apply {
                            idToken?.let { idToken ->
                                signInWithGoogle(idToken)
                            }
                        }
                    } catch (e: ApiException) {
                        print(e.message)
                    }
                }
            }
    }

    private fun signInWithGoogle(idToken: String) {
        viewModel.signInWithGoogle(idToken).observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Loading -> binding.progressBar.show()
                is NetworkResult.Success -> {
                    val isNewUser = response.data
                    if(isNewUser){
                        Log.d("LoginFragment","New User")
                        createUser()
                    } else{
                        Log.d("LoginFragment","Old")
                        binding.progressBar.hide()
                        startActivity(Intent(activity, MainActivity::class.java))
                    }

                }

                is NetworkResult.Failure -> {

                    binding.progressBar.hide()
                }
            }
        }
    }

    private fun createUser() {
        viewModel.createUser().observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Loading -> binding.progressBar.show()
                is NetworkResult.Success -> {
                    Log.d("LoginFragment",response.data.toString())
                    startActivity(Intent(requireActivity(), MainActivity::class.java))
                    binding.progressBar.hide()
                }

                is NetworkResult.Failure -> {
                    Log.d("LoginFragment",response.error)
                    binding.progressBar.hide()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}