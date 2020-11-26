package org.portionwise.ui.screen.rations

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import org.portionwise.R
import org.portionwise.calcs.ApportionmentBuilder
import org.portionwise.calcs.ShoppingListBuilder
import org.portionwise.dummy.DummyContent

/**
 * A fragment representing a list of Items.
 */
class ShoppingListFragment : Fragment() {

    private var rationProjectId: Int = 0

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
        val view = inflater.inflate(R.layout.fragment_shopping_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
                layoutManager = LinearLayoutManager(context)
                val apportionment = ApportionmentBuilder()
                    .withMealSchedule(DummyContent.getRationMealSchedule(DummyContent.RATIONS[rationProjectId]))
                    .build()

                adapter = ShoppingListRecyclerViewAdapter(ShoppingListBuilder().withApportionment(apportionment).build().components)
            }
        }
        return view
    }

    companion object {

        private const val ARG_RATION_PROJECT_ID = "rationProjectId"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            ShoppingListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_RATION_PROJECT_ID, columnCount)
                }
            }
    }
}