package com.example.proyprueba2.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.proyprueba2.R
import kotlinx.android.synthetic.main.fragment_compara.*

class ComparaFragment:Fragment() {

    val header : MutableList<String> = ArrayList()
    val body : MutableList<MutableList<String>> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val season1 : MutableList<String> = ArrayList()
        season1.add("Winter is Coming")
        season1.add("Winter is ")
        season1.add("Winter is dade")
        season1.add("Winter is todo bien")
        season1.add("The Wolf and the Lion")
        season1.add("A golden Crown")

        val season2 : MutableList<String> = ArrayList()
        season2.add("Aquí empieza el segundo manual")
        season2.add("Winter is ")
        season2.add("Winter is dade")
        season2.add("Winter is todo bien")
        season2.add("The Wolf and the Lion")
        season2.add("A golden Crown")

        val season3 : MutableList<String> = ArrayList()
        season3.add("Aquí empieza el tercer manual")
        season3.add("Winter is ")
        season3.add("Winter is dade")
        season3.add("Winter is todo bien")
        season3.add("The Wolf and the Lion")
        season3.add("A golden Crown")

        header.add("Season 1")
        header.add("Season 2")
        header.add("Season 3")

        body.add(season1)
        body.add(season2)
        body.add(season3)

        expandableListView.setAdapter(ExpandableListViewAdapter(requireActivity(),header,body))

        return inflater.inflate(R.layout.fragment_compara, container, false)

    }

    }

