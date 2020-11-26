package org.portionwise.ui.screen.rations

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.portionwise.R
import org.portionwise.models.MealComponent

class ShoppingListRecyclerViewAdapter(
    private val values: List<MealComponent>
) : RecyclerView.Adapter<ShoppingListRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_shopping_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.componentName.text = item.foodStuff.name
        holder.amount.text = "%.2f%s".format(item.amount, holder.ctx.resources.getString(R.string.gram_label))
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val componentName: TextView = view.findViewById(R.id.component_name)
        val amount: TextView = view.findViewById(R.id.amount)
        val ctx = view.context

        override fun toString(): String {
            return super.toString() + " '" + componentName.text + "'"
        }
    }
}