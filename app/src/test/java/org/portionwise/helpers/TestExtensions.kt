package org.portionwise.helpers

import org.portionwise.models.*

fun <T> T.builder(filler: T.() -> Unit): T {
    this.filler()
    return this
}

fun Apportionment.repeating(times: Int, filler: Apportionment.() -> Unit) {
    for (i in 0 until times step 1) {
        filler()
    }
}

fun Apportionment.meal(name: String, filler: Meal.() -> Unit) {
    meals.add(Meal(name).apply(filler))
}

fun MealSchedule.day(dayRange: IntRange, filler: MutableList<MealMenu>.() -> Unit) {
    dayRange.forEach { day ->
        if(day > days.size)
            days.add(mutableListOf<MealMenu>().apply(filler))
        else
            days[day - 1] = mutableListOf<MealMenu>().apply(filler)
    }
}

fun MutableList<MealMenu>.meal(name: String, filler: MealMenu.() -> Unit) {
    add(MealMenu(name).apply(filler))
}

fun Meal.item(name: FoodStuff, amount: Float) {
    components.add(MealComponent(name, amount))
}

fun MealMenu.dish(name: String, filler: Dish.() -> Unit) {
    dishes.add(Dish(name).apply(filler))
}

fun Dish.item(name: FoodStuff, amount: Float) {
    components.add(MealComponent(name, amount))
}

fun RationProfile.member(name: String, factor: Float) {
    members.add(Member(name, factor))
}

fun RationProfile.nutritionProfile(calories: Float, protein: Float, fat: Float, carbohydrate: Float) {
    nutritionProfile = NutritionProfile(
        calories = calories,
        protein = protein,
        fat = fat,
        carbohydrate = carbohydrate
    )
}