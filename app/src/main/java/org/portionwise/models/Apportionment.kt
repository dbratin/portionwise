package org.portionwise.models

data class Apportionment (
    val meals: MutableList<Meal> = ArrayList()
)
