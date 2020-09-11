package org.portionwise.models

import java.util.*

data class MealMenu(val name: String, val dishes: MutableList<Dish> = LinkedList())