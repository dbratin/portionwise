package org.portionwise.ui.screen.rations

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import org.portionwise.R
import org.portionwise.models.RationProject

class RationListViewAdapter(
    private val values: MutableList<RationProject>
) : RecyclerView.Adapter<RationListViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_ration_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.nameView.text = item.name
        holder.descriptionView.text = item.description
        holder.caloriesView.text = "%.2f".format(item.profile.nutritionProfile.calories)
        holder.daysView.text = "%d".format(item.profile.days)
        holder.membersNumberView.text = "%d".format(item.profile.members.size)

        holder.itemView.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_RationList_to_RationDetails)
        }
    }

    fun removeAt(position: Int) {
        values.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView = view.findViewById(R.id.name)
        val descriptionView: TextView = view.findViewById(R.id.description)
        val caloriesView: TextView = view.findViewById(R.id.calories)
        val daysView: TextView = view.findViewById(R.id.days)
        val membersNumberView: TextView = view.findViewById(R.id.members_number)

        override fun toString(): String {
            return super.toString() + " '" + nameView.text + "'"
        }
    }
}