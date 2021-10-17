package com.example.gb_libs_lesson5.ui.screens.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gb_libs_lesson5.R
import com.example.gb_libs_lesson5.databinding.FragmentIntoRepoBinding
import com.example.gb_libs_lesson5.databinding.FragmentUsersBinding

private const val LANGUAGE = "language"
private const val SIZE = "size"

class IntoRepoFragment : Fragment() {

    private var language: String? = null
    private var size: String? = null
    private var vb: FragmentIntoRepoBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            language = it.getString(LANGUAGE)
            size = it.getString(SIZE)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentIntoRepoBinding.inflate(inflater, container, false).also {
            vb = it
            vb!!.language.text = language
            vb!!.size.text = size
        }.root
    }

    companion object {

        @JvmStatic
        fun newInstance(language: String, size: String) =
            IntoRepoFragment().apply {
                arguments = Bundle().apply {
                    putString(LANGUAGE, language)
                    putString(SIZE, size)
                }
            }
    }
}