package org.portionwise.models

import java.util.*

data class Dish(
    val name: String,
    val components: MutableList<MealComponent> = LinkedList()
)