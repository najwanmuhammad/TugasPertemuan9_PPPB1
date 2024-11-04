package com.example.tugaspertemuan9

import SectionsPagerAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.example.tugaspertemuan9.databinding.FragmentLoginBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        with(binding) {
            txtSignup.setOnClickListener {
                activity?.let { activity ->
                    val sectionsPagerAdapter = SectionsPagerAdapter(activity)
                    val viewPager: ViewPager2 = activity.findViewById(R.id.view_pager)
                    viewPager.adapter = sectionsPagerAdapter
                    viewPager.currentItem = 1
                } ?: run {
                    Toast.makeText(activity, "Error: Activity tidak ditemukan!", Toast.LENGTH_SHORT).show()
                }
            }

            buttonLogin.setOnClickListener {
                val email = inputEmail.text.toString()
                val password = inputPassword.text.toString()

                val userData = mapOf(
                    "Email" to email,
                    "Password" to password,
                )

                var canSubmit = true

                var notInputed = arrayListOf<String>()

                userData.forEach { key, value ->
                    if (value.length == 0) {
                        notInputed.add(key)
                        canSubmit = false
                    }
                }

                if (notInputed.size > 0) {
                    val notInputedString = notInputed.joinToString()
                    Toast.makeText(activity, notInputedString + " harus diisi!", Toast.LENGTH_SHORT).show()
                }

                if (canSubmit) {
                    val emailData = (activity as MainActivity).getEmail()
                    val passwordData = (activity as MainActivity).getPassword()
                    if ((email == emailData) && (password == passwordData)) {
                        Toast.makeText(activity, "Login Berhasil", Toast.LENGTH_SHORT).show()
                        (activity as MainActivity).redirectToDashboard()
                    } else {
                        Toast.makeText(activity, "Email atau Password salah", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}