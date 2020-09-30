package org.portionwise.ui.screen.rations

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import org.portionwise.R
import org.portionwise.models.Member

class MembersListViewAdapter (private val members : MutableList<Member>) : BaseAdapter() {
    override fun getView(pos: Int, view: View?, parent: ViewGroup?): View {
        val (itemView, viewHolder) = if(view == null) {
            val v = LayoutInflater
                .from(parent!!.context)
                .inflate(R.layout.fragment_ration_profile_members_list_item, parent, false)
            val h = ViewHolder(v)
            v.tag = h

            Pair(v, h)
        } else {
            Pair(view, view.tag as ViewHolder)
        }

        val item = getItem(pos)
        viewHolder.nameView.text = item.name
        viewHolder.factorView.text = "X${item.factor}"

        return itemView
    }

    override fun getItem(pos: Int): Member {
        return members[pos]
    }

    override fun getItemId(pos: Int): Long {
        return pos.toLong()
    }

    override fun getCount(): Int {
        return members.size
    }

    inner class ViewHolder(view: View) {
        val nameView = view.findViewById<TextView>(R.id.member_name)
        val factorView = view.findViewById<TextView>(R.id.member_factor)
    }
}