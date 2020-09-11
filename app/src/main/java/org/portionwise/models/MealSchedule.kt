package org.portionwise.models

data class MealSchedule constructor (val initialDays: Int = 7) {
    val days: ArrayList<MutableList<MealMenu>> = ArrayList(initialDays)
}
