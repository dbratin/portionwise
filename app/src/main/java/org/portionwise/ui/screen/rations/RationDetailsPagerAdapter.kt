package org.portionwise.ui.screen.rations

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.portionwise.ErrorFragment
import org.portionwise.R

class RationDetailsPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    val tabs = listOf(
        Pair(R.string.ration_profile_tab, RationProfileFragment::class),
        Pair(R.string.meal_schedule_tab, MealScheduleFragment::class)
    )

    override fun getItemCount(): Int = tabs.size

    override fun createFragment(position: Int): Fragment {
        return tabs.takeIf { tabs.size > position }
            ?.let { tabs[position].second.java.newInstance() }
            ?:ErrorFragment()
    }

    fun tabName(ctx: Context, position: Int): String {
        return tabs.takeIf { tabs.size > position } ?. let { ctx.getString(tabs[position].first) } ?: "Unknown"
    }

}