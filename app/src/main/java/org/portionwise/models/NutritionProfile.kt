package org.portionwise.models

data class NutritionProfile(
    var calories: Float,
    var protein: Float,
    var fat: Float,
    var carbohydrate: Float
) {
    companion object {
        val BASIC_MASS: Float = 100f
    }
}
