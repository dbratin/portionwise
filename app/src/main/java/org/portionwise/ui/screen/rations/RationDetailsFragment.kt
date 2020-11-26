package org.portionwise.ui.screen.rations

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.portionwise.MainActivity
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

        val tabLayout = view.findViewById<TabLayout>(R.id.tabs)
        TabLayoutMediator(tabLayout, pagerView) { tab, position ->
            tab.text = detailsPagerAdapter.tabName(view.context, position)
        }.attach()
    }
}