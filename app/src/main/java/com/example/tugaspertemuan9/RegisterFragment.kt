package com.example.tugaspertemuan9

import SectionsPagerAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.example.tugaspertemuan9.databinding.FragmentRegisterBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
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
        binding = FragmentRegisterBinding.inflate(inflater, container, false)

        with(binding) {
            txtLogin.setOnClickListener {
                activity?.let { activity ->
                    val sectionsPagerAdapter = SectionsPagerAdapter(activity)
                    val viewPager: ViewPager2 = activity.findViewById(R.id.view_pager)
                    viewPager.adapter = sectionsPagerAdapter
                    viewPager.currentItem = 0
                } ?: run {
                    Toast.makeText(activity, "Error: Activity tidak ditemukan!", Toast.LENGTH_SHORT).show()
                }
            }
            buttonSignup.setOnClickListener {
                val name = inputName.text.toString()
                val email = inputEmail.text.toString()
                val nim = inputNim.text.toString()
                val password = inputPassword.text.toString()
                val confirmPassword = inputConfirmpassword.text.toString()

                val userData = mapOf(
                    "Nama" to name,
                    "Email" to email,
                    "NIM" to nim,
                    "Password" to password,
                    "Konfirmasi Password" to confirmPassword
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

                if (password != confirmPassword) {
                    Toast.makeText(activity, "Konfirmasi Password harus sama dengan Password!", Toast.LENGTH_SHORT).show()
                    canSubmit = false
                }

                if (canSubmit) {
                    activity?. let { activity ->
                        (activity as MainActivity).receiveUserData(email, password, nim, name)

                        val sectionsPagerAdapter = SectionsPagerAdapter(activity)
                        val viewPager: ViewPager2 = activity.findViewById(R.id.view_pager)
                        viewPager.adapter = sectionsPagerAdapter
                        viewPager.currentItem = 0
                        Toast.makeText(activity, "Registrasi berhasil", Toast.LENGTH_SHORT).show()
                    } ?: run {
                        Toast.makeText(activity, "Error: Activity tidak ditemukan!", Toast.LENGTH_SHORT).show()
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
         * @return A new instance of fragment RegisterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegisterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}