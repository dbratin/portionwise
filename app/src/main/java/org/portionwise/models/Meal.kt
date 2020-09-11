package org.portionwise.models

data class Meal(val name: String, val components: MutableList<MealComponent> = ArrayList())