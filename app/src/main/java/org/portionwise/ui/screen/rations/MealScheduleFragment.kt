package org.portionwise.ui.screen.rations

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.portionwise.R
import org.portionwise.dummy.DummyContent

/**
 * A fragment representing a list of Items.
 */
class MealScheduleFragment : Fragment() {

    private var rationProjectId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            rationProjectId = it.getInt(ARG_RATION_PROJECT_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_meal_schedule, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = MealScheduleViewAdapter(DummyContent.getRationMealSchedule(DummyContent.RATIONS[rationProjectId]).days)
            }
        }
        return view
    }

    companion object {

        const val ARG_RATION_PROJECT_ID = "rationProjectId"

        @JvmStatic
        fun newInstance(rationProjectId: Int) =
            MealScheduleFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_RATION_PROJECT_ID, rationProjectId)
                }
            }
    }
}