package org.portionwise.models

import org.portionwise.templates.NutritionProfileTemplates
import java.util.*

data class RationProfile(
    var mealsPerDay: Int = 3,
    var days: Int = 1,
    var nutritionProfile: NutritionProfile = NutritionProfileTemplates.normal(),
    val members: MutableList<Member> = LinkedList()
)
