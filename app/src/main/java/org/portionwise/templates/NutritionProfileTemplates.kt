package org.portionwise.templates

import org.portionwise.models.NutritionProfile

class NutritionProfileTemplates{
    companion object {
        fun zero(): NutritionProfile {
            return NutritionProfile (calories = 0f, protein = 0f, fat = 0f, carbohydrate = 0f)
        }
        fun normal(): NutritionProfile {
            return NutritionProfile (calories = 2000f, protein = 100f, fat = 100f, carbohydrate = 200f)
        }

        fun intensive(): NutritionProfile {
            return NutritionProfile (calories = 3000f, protein = 150f, fat = 200f, carbohydrate = 400f)
        }

        fun light(): NutritionProfile {
            return NutritionProfile (calories = 1800f, protein = 50f, fat = 100f, carbohydrate = 100f)
        }
    }
}