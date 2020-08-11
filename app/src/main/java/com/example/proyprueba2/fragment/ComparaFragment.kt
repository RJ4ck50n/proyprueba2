package com.example.proyprueba2.fragment
import ExpandableFaqsListAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.proyprueba2.R
import com.example.proyprueba2.fragment.Faq

class ComparaFragment : Fragment() {
    lateinit var faqsListView: ExpandableListView
    lateinit var listAdapter: ExpandableFaqsListAdapter
    lateinit var faqsView: View


    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        faqsView = inflater?.inflate(R.layout.fragment_compara, container, false)

        faqsListView = faqsView?.findViewById(R.id.rvFaqs) as ExpandableListView

        val faqsListParent= listOf("Numbers","Fruit")
        val fruitsList=listOf("Apple","Orange","Banana")
        val madera=listOf("Miguel","Angel","David")
        val listchild= HashMap<String, List<String>>()

        listchild.put(faqsListParent[0], madera)
        listchild.put(faqsListParent[1], fruitsList)

        listAdapter = ExpandableFaqsListAdapter(activity!!, faqsListParent,listchild )
        faqsListView.setAdapter(listAdapter)

// If you want to change parent item icon (up/down/expanded/collapse)
// effect. Add onGroupClick Listener like below.

        faqsListView.setOnGroupClickListener { expandableListView, view, i, l ->
            if (expandableListView.isGroupExpanded(i)) {
                //var imgView = view.findViewById<ImageView>(R.id.imgIonNextFaq)
               // imgView.setImageDrawable(ContextCompat.getDrawable(this@ComparaFragment.context!!, R.drawable.supermercadosperuanos))
            } else {
                //var imgView = view.findViewById<ImageView>(R.id.imgIonNextFaq)
                //imgView.setImageDrawable(ContextCompat.getDrawable(this@ComparaFragment.context!!, R.drawable.jack_foto))
            }
            return@setOnGroupClickListener false
        }


        return faqsView
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}