package com.rifki.myflexiblefragment

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.Profile
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class DetailCategoryFragment : Fragment() {

    private lateinit var tvCategoryName: TextView
    private lateinit var tvCategoryDescription: TextView
    private lateinit var btnProfile: Button
    private lateinit var btnShowDialog: Button

    var description: String? = null

    internal var optionDialogListener: OptionDialogFragment.OnOptionDialogListener = object : OptionDialogFragment.OnOptionDialogListener {
        override fun onOptionChoosen(text: String?) {
            Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        var EXTRA_NAME = "extra_name"
        var EXTRA_DESCRIPTION = "extra_description"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(view) {
            tvCategoryName = findViewById(R.id.tv_category_name)
            tvCategoryDescription = findViewById(R.id.tv_category_description)
            btnProfile = findViewById(R.id.btn_profile)
            btnShowDialog = findViewById(R.id.btn_show_dialog)
        }

        if (savedInstanceState != null) {
            val descFromBundle = savedInstanceState.getString(EXTRA_DESCRIPTION)
            description = descFromBundle
        }

        if (arguments != null) {
            val categoryName = arguments?.getString(EXTRA_NAME)
            tvCategoryName.text = categoryName
            tvCategoryDescription.text = description
        }

        btnShowDialog.setOnClickListener {
            val optionDialogFragment = OptionDialogFragment()

            val fragmentFragment = childFragmentManager
            optionDialogFragment.show(fragmentFragment, OptionDialogFragment::class.java.simpleName)
        }

        btnProfile.setOnClickListener {
            val intent = Intent(requireActivity(), ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}