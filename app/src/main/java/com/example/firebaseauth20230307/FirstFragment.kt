package com.example.firebaseauth20230307

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.firebaseauth20230307.databinding.FragmentFirstBinding
import com.google.firebase.auth.FirebaseAuth

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*val currentUser = auth.currentUser
        if (currentUser != null) {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }*/

        binding.buttonLogin.setOnClickListener {
            val email = binding.edittextEmail.text.trim().toString()
            if (email.isEmpty()) {
                binding.edittextEmail.error = "No email"
                return@setOnClickListener
            }
            val password = binding.edittextPassword.text.trim().toString()
            if (password.isEmpty()) {
                binding.edittextPassword.error = "No password"
                return@setOnClickListener
            }
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("APPLE", "createUserWithEmail:success")
                        val user = auth.currentUser
                        //updateUI(user)
                        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("APPLE", "createUserWithEmail:failure", task.exception)
                        binding.textviewMessage.text =
                            "Authentication failed: " + task.exception?.message
                        //updateUI(null)
                    }
                }
        }

        binding.buttonRegister.setOnClickListener {
            val email = binding.edittextEmail.text.trim().toString()
            if (email.isEmpty()) {
                binding.edittextEmail.error = "No email"
                return@setOnClickListener
            }
            val password = binding.edittextPassword.text.trim().toString()
            if (password.isEmpty()) {
                binding.edittextPassword.error = "No password"
                return@setOnClickListener
            }
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("APPLE", "createUserWithEmail:success")
                        val user = auth.currentUser
                        //updateUI(user)
                        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("APPLE", "createUserWithEmail:failure", task.exception)
                        binding.textviewMessage.text =
                            "Registration failed: " + task.exception?.message
                        //updateUI(null)
                    }
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}