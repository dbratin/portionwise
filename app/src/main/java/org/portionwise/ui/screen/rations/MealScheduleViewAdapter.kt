package org.portionwise.ui.screen.rations

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.portionwise.R
import org.portionwise.models.MealMenu

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MealScheduleViewAdapter(
    private val days: List<MutableList<MealMenu>>
) : RecyclerView.Adapter<MealScheduleViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_meal_schedule_day, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dayNumber.text = "${position + 1}"
        holder.mealsList.adapter = MealSheduleDayContentViewAdapter(days[position])
        setListHeight(holder.mealsList)
    }

    override fun getItemCount(): Int = days.size

    private fun setListHeight(listView: ListView) {
        val adapter = listView.adapter
        var totalHeight = listView.paddingTop + listView.paddingBottom
        for (i in 0 until adapter.count) {
            val listItem: View = adapter.getView(i, null, listView)
            (listItem as? ViewGroup)?.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            listItem.measure(0, 0)
            totalHeight += listItem.measuredHeight
        }

        val params: ViewGroup.LayoutParams = listView.layoutParams
        params.height = totalHeight + listView.dividerHeight * (adapter.count - 1)
        listView.layoutParams = params
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dayNumber: TextView = view.findViewById(R.id.day_number)
        val mealsList: ListView = view.findViewById(R.id.meals_list)

        override fun toString(): String {
            return super.toString() + " '" + dayNumber.text + "'"
        }
    }
}