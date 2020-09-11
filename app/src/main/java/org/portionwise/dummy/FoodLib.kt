package org.portionwise.dummy

import org.portionwise.models.FoodStuff
import org.portionwise.models.NutritionProfile
import org.portionwise.templates.NutritionProfileTemplates

object FoodLib : HashMap<String, FoodStuff>() {
    init {
        put("Oats", FoodStuff("Oats", NutritionProfile(calories = 300f, protein = 10f, fat = 20f, carbohydrate = 70f)))
        put("Coffee", FoodStuff("Coffee", NutritionProfile(calories = 50f, protein = 0f, fat = 0f, carbohydrate = 0f)))
        put("Milk", FoodStuff("Milk", NutritionProfile(calories = 400f, protein = 20f, fat = 70f, carbohydrate = 10f)))
        put("Bread", FoodStuff("Bread", NutritionProfile(calories = 200f, protein = 10f, fat = 10f, carbohydrate = 80f)))
        put("Butter", FoodStuff("Butter", NutritionProfile(calories = 700f, protein = 10f, fat = 90f, carbohydrate = 0f)))
        put("Cookies", FoodStuff("Cookies", NutritionProfile(calories = 300f, protein = 10f, fat = 30f, carbohydrate = 60f)))
        put("Beef", FoodStuff("Beef", NutritionProfile(calories = 500f, protein = 70f, fat = 30f, carbohydrate = 0f)))
        put("Salt", FoodStuff("Salt", NutritionProfile(calories = 500f, protein = 70f, fat = 30f, carbohydrate = 0f)))
        put("Toast", FoodStuff("Toast", NutritionProfile(calories = 500f, protein = 70f, fat = 30f, carbohydrate = 0f)))
        put("Sugar", FoodStuff("Sugar", NutritionProfile(calories = 10f, protein = 0f, fat = 0f, carbohydrate = 100f)))
        put("Soup Mix", FoodStuff("Soup Mix", NutritionProfile(calories = 200f, protein = 10f, fat = 10f, carbohydrate = 80f)))
        put("Water", FoodStuff("Water", NutritionProfileTemplates.zero()))
        put("Spices Mix", FoodStuff("Spices Mix", NutritionProfileTemplates.zero()))
        put("Tea", FoodStuff("Tea", NutritionProfileTemplates.zero()))
    }
}