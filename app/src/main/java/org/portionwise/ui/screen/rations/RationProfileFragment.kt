package org.portionwise.ui.screen.rations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import org.portionwise.R
import org.portionwise.models.Member

/**
 * A simple [Fragment] subclass.
 * Use the [RationProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RationProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

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
        view.findViewById<TextView>(R.id.days_number).text = "6"
        view.findViewById<TextView>(R.id.calories).text = "2000"
        view.findViewById<TextView>(R.id.fat).text = "200g"
        view.findViewById<TextView>(R.id.protein).text = "120g"
        view.findViewById<TextView>(R.id.carbohydrate).text = "250g"

        view.findViewById<ListView>(R.id.members_list).adapter = MembersListViewAdapter(
            mutableListOf(
                Member(name = "Vasiliy S.", factor = 0.9f),
                Member(name = "Dmitry G.", factor = 1.2f),
                Member(name = "Dmitry K.", factor = 1.1f),
                Member(name = "Sergey S.", factor = 1.0f)
            )
        )
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment RationProfileFragment.
         */
        @JvmStatic
        fun newInstance() =
            RationProfileFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}