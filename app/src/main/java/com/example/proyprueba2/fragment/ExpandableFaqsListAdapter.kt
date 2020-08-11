import java.util.HashMap

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.example.proyprueba2.R
import com.example.proyprueba2.fragment.Faq
//import kotlinx.android.synthetic.main.item_billpayment_parent_list.view.*
import kotlinx.android.synthetic.main.layout_child.view.*
import kotlinx.android.synthetic.main.layout_group.view.*

class ExpandableFaqsListAdapter(val _context: Context,  var _listDataHeader: List<String>, val _listChild: HashMap<String, List<String>> // header titles
    // child data in format of header title, child title
) : BaseExpandableListAdapter() {
//var _listDataHeaderFiltered: ArrayList<Faq>
   // var _listDataHeaderOriginal = ArrayList<Faq>()
  // init {
  //      _listDataHeaderFiltered = _listDataHeader
   //     _listDataHeaderOriginal.addAll(_listDataHeader)
  //  }
override fun getChild(groupPosition: Int, childPosititon: Int): Any {
        return _listChild[_listDataHeader[groupPosition]]!![childPosititon]
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getChildView(groupPosition: Int, childPosition: Int,
                              isLastChild: Boolean, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView

        val childText = getChild(groupPosition, childPosition) as String

        if (convertView == null) {
            val infalInflater = this._context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = infalInflater.inflate(R.layout.layout_child, null)
        }

        convertView!!.txtFaqAnswer.text = childText

        /*val txtListChild = convertView!!
                .findViewById(R.id.lblListItem) as TextView
        txtListChild.text = childText
        */
        return convertView!!
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return _listChild[_listDataHeader[groupPosition]]!!.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return _listDataHeader[groupPosition]
    }

    override fun getGroupCount(): Int {
        return this._listDataHeader.size
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean,
                              convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val headerTitle = getGroup(groupPosition) as String
        if (convertView == null) {
            val infalInflater = this._context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = infalInflater.inflate(R.layout.layout_group, null)
        }

        if (groupPosition % 2 == 1) {
            convertView?.setBackgroundResource(R.color.colorPrimary)
        } else {
            convertView?.setBackgroundResource(R.color.colorPrimaryDark)
        }

        convertView!!.txtFaqs.text = headerTitle

        return convertView!!
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}