package org.portionwise.calcs

import org.portionwise.exceptions.InvalidDayRange
import org.portionwise.exceptions.NutritionMismatchException
import org.portionwise.models.*
import org.portionwise.templates.NutritionProfileTemplates
import java.util.*

class MealScheduleBuilder(private val profile: RationProfile) {

    private var schedule: MealSchedule = MealSchedule(profile.days)

    init {
        addDays(profile.days)
    }

    fun validate() {
        val expectedProfile = profile.nutritionProfile

        val matched = schedule.days.stream()
            .allMatch { meals ->
                val actualProfile = meals.stream()
                    .flatMap { mealMenu ->
                        mealMenu.dishes.stream()
                    }
                    .flatMap { dish ->
                        dish.components.stream()
                    }
                    .reduce(NutritionProfileTemplates.zero(),
                        { profile, component ->
                            profile.calories += component.foodStuff.nutrition.calories * component.amount / NutritionProfile.BASIC_MASS
                            profile.protein += component.foodStuff.nutrition.protein * component.amount / NutritionProfile.BASIC_MASS
                            profile.fat += component.foodStuff.nutrition.fat * component.amount / NutritionProfile.BASIC_MASS
                            profile.carbohydrate += component.foodStuff.nutrition.carbohydrate * component.amount / NutritionProfile.BASIC_MASS
                            return@reduce profile
                        },
                        { profile1, profile2 ->
                            profile1.calories += profile2.calories
                            profile1.protein += profile2.protein
                            profile1.fat += profile2.fat
                            profile1.carbohydrate += profile2.carbohydrate
                            return@reduce profile1
                        }
                    )

                println(actualProfile)

                return@allMatch matchesProfile(actualProfile, expectedProfile)
            }

        if(!matched)
            throw NutritionMismatchException()
    }

    fun build() {
        validate()
    }

    fun addDays(daysAmount: Int): MealScheduleBuilder {
        for (i in 1..daysAmount)
            schedule.days.add(mutableListOf())

        return this
    }

    fun addMeal(days: IntRange, meal: MealMenu): MealScheduleBuilder {
        if (days.first < 1)
            throw InvalidDayRange()

        if (days.first > schedule.days.size)
            return this

        val normalizedDays =
            if (days.last > schedule.days.size) days.first..schedule.days.size else days

        for (d in normalizedDays) {
            val mealList = schedule.days[d - 1]

            if (mealList.stream().noneMatch { m -> m.name == meal.name }) {
                mealList.add(meal.copy(dishes = LinkedList<Dish>().apply{ addAll(meal.dishes) }))
            }
        }

        return this
    }

    fun addDish(days: IntRange, meal: String, dish: Dish): MealScheduleBuilder {
        if (days.first < 1)
            throw InvalidDayRange()

        if (days.first > schedule.days.size)
            return this

        val normalizedDays =
            if (days.last > schedule.days.size) days.first..schedule.days.size else days

        for (d in normalizedDays) {
            schedule.days[d - 1].find { m -> m.name == meal }?.dishes?.add(dish)
        }

        return this
    }

    fun removeDish(days: IntRange, meal: String, dish: Dish): MealScheduleBuilder {
        if (days.first < 1)
            throw InvalidDayRange()

        if (days.first > schedule.days.size)
            return this

        val normalizedDays =
            if (days.last > schedule.days.size) days.first..schedule.days.size else days

        for (d in normalizedDays) {
            schedule.days[d - 1].find { m -> m.name == meal }?.dishes?.remove(dish)
        }

        return this
    }

    fun removeMeal(days: IntRange, meal: MealMenu): MealScheduleBuilder {
        if (days.first < 1)
            throw InvalidDayRange()

        if (days.first > schedule.days.size)
            return this

        val normalizedDays =
            if (days.last > schedule.days.size) days.first..schedule.days.size else days

        for (d in normalizedDays) {
            schedule.days[d - 1].remove(meal)
        }

        return this
    }

    fun removeDays(days: IntRange): MealScheduleBuilder {
        if (days.first < 1)
            throw InvalidDayRange()

        if (days.first > schedule.days.size)
            return this

        val toDelete = mutableListOf<MutableList<MealMenu>>()
        for ((i, meals) in schedule.days.withIndex()) {
            if (days.contains(i + 1)) toDelete.add(meals)
        }

        schedule.days.removeAll(toDelete)

        return this
    }

    private fun matchesProfile(actualProfile: NutritionProfile, expectedProfile: NutritionProfile): Boolean {
        return actualProfile.calories >= expectedProfile.calories
                && actualProfile.protein >= expectedProfile.protein
                && actualProfile.fat >= expectedProfile.fat
                && actualProfile.carbohydrate >= expectedProfile.carbohydrate
    }
}
