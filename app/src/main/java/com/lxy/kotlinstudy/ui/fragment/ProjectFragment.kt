package com.lxy.kotlinstudy.ui.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.lxy.kotlinstudy.R
import com.lxy.kotlinstudy.net.modle.Children


class ProjectFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var data: Children? = null
    private var mParam2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            var string = arguments.getString(ARG_PARAM1)

        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_project, container, false)
    }




    companion object {

        private val ARG_PARAM1 = "data"

        fun newInstance(param2: String): ProjectFragment {
            val fragment = ProjectFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param2)
            fragment.arguments = args
            return fragment
        }
    }
}
