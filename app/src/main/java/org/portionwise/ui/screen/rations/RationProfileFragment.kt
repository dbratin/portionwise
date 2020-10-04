package org.portionwise.ui.screen.rations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import org.portionwise.R
import org.portionwise.dummy.DummyContent
import org.portionwise.models.Member
import org.portionwise.models.RationProject

/**
 * A simple [Fragment] subclass.
 * Use the [RationProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RationProfileFragment : Fragment() {

    private var rationProjectId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            rationProjectId = it.getInt(MealScheduleFragment.ARG_RATION_PROJECT_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ration_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val project: RationProject = DummyContent.RATIONS[rationProjectId]

        view.findViewById<TextView>(R.id.days_number).text = "${project.profile.days}"
        view.findViewById<TextView>(R.id.calories).text = "${project.profile.nutritionProfile.calories}"
        view.findViewById<TextView>(R.id.fat).text = "${project.profile.nutritionProfile.fat}g"
        view.findViewById<TextView>(R.id.protein).text = "${project.profile.nutritionProfile.protein}g"
        view.findViewById<TextView>(R.id.carbohydrate).text = "${project.profile.nutritionProfile.carbohydrate}g"

        view.findViewById<ListView>(R.id.members_list).adapter = MembersListViewAdapter(project.profile.members)
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