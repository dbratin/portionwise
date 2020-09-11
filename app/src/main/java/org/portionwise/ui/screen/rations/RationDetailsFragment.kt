package org.portionwise.ui.screen.rations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import org.portionwise.R

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class RationDetailsFragment : Fragment() {

    private lateinit var detailsPagerAdapter: RationDetailsPagerAdapter
    private lateinit var pagerView: ViewPager2

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ration_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailsPagerAdapter = RationDetailsPagerAdapter(this)
        pagerView = view.findViewById(R.id.ration_details_view)
        pagerView.adapter = detailsPagerAdapter
    }
}