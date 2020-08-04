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

    private lateinit var listViewAdapter: ExpandableListViewAdapter
    private lateinit var chapterList : List<String>
    private lateinit var topicList: HashMap<String,List<String>>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        listViewAdapter= ExpandableListViewAdapter(requireActivity(), chapterList,topicList)
        eListView.setAdapter(listViewAdapter)
        showList()

        return inflater.inflate(R.layout.fragment_compara, container, false)

    }

    private fun showList() {
        chapterList=ArrayList()
        topicList= HashMap()

        (chapterList as ArrayList<String>).add("Chapter 1")
        (chapterList as ArrayList<String>).add("Chapter 2")
        (chapterList as ArrayList<String>).add("Chapter 3")
        (chapterList as ArrayList<String>).add("Chapter 4")
        (chapterList as ArrayList<String>).add("Chapter 5")

        val topic1 : MutableList<String> = ArrayList()
        topic1.add("Topic 1")
        topic1.add("Topic 2")
        topic1.add("Topic 3")

        val topic2 : MutableList<String> = ArrayList()
        topic2.add("Topic 1")
        topic2.add("Topic 2")
        topic2.add("Topic 3")
        val topic3 : MutableList<String> = ArrayList()
        topic3.add("Topic 1")
        topic3.add("Topic 2")
        topic3.add("Topic 3")
        val topic4 : MutableList<String> = ArrayList()
        topic4.add("Topic 1")
        topic4.add("Topic 2")
        topic4.add("Topic 3")
        val topic5 : MutableList<String> = ArrayList()
        topic5.add("Topic 1")
        topic5.add("Topic 2")
        topic5.add("Topic 3")

        topicList[chapterList[0]] = topic1
        topicList[chapterList[1]] = topic2
        topicList[chapterList[2]] = topic3
        topicList[chapterList[3]] = topic4
        topicList[chapterList[4]] = topic5

    }

}