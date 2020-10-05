package org.portionwise.ui.screen.rations

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import org.portionwise.R
import org.portionwise.models.MealMenu

class MealSheduleDayContentViewAdapter(private val meals : List<MealMenu>) : BaseAdapter() {
    override fun getView(pos: Int, view: View?, parent: ViewGroup?): View {
        val (itemView, viewHolder) = if(view == null) {
            val v = LayoutInflater
                .from(parent!!.context)
                .inflate(R.layout.fragment_meal_schedule_day_content, parent, false)
            val h = ViewHolder(v)
            v.tag = h

            Pair(v, h)
        } else {
            Pair(view, view.tag as MealSheduleDayContentViewAdapter.ViewHolder)
        }

        val item = getItem(pos)
        viewHolder.day.text = item.name
        viewHolder.dishes.text = item.dishes.joinToString { d -> d.name }

        return itemView
    }

    override fun getItem(pos: Int): MealMenu {
        return meals[pos]
    }

    override fun getItemId(pos: Int): Long {
        return pos.toLong()
    }

    override fun getCount(): Int {
        return meals.size
    }

    inner class ViewHolder(view: View) {
        val day = view.findViewById<TextView>(R.id.meal_name)
        val dishes = view.findViewById<TextView>(R.id.dishes_enum)
    }
}