package org.portionwise.dummy

import org.portionwise.models.*
import org.portionwise.templates.NutritionProfileTemplates
import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    val RATIONS: MutableList<RationProject> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val RATIONS_MAP: MutableMap<String, RationProject> = HashMap()

    private val COUNT = 25

    init {
        // Add some sample items.
        for (i in 1..COUNT) {
            addRation(createDummyRation(i))
        }
    }

    fun getRationMealSchedule(item: RationProject): MealSchedule {
        return MealSchedule(item.profile.days).apply {
            for (d in 1..item.profile.days) {
                days.add(
                    mutableListOf(
                        createBreakfast(),
                        createLunch(),
                        createDinner()
                    )
                )
            }
        }
    }

    private fun createDinner(): MealMenu {
        return MealMenu(
            name = "Dinner",
            dishes = mutableListOf(
                Dish(
                    name = "Beef on grill",
                    components = mutableListOf(
                        MealComponent(FoodLib["Beef"]!!, 200f),
                        MealComponent(FoodLib["Spices Mix"]!!, 10f),
                        MealComponent(FoodLib["Salt"]!!, 10f)
                    )
                ),
                Dish(
                    name = "Tea with cookies",
                    components = mutableListOf(
                        MealComponent(FoodLib["Coffee"]!!, 20f),
                        MealComponent(FoodLib["Sugar"]!!, 4f),
                        MealComponent(FoodLib["Cookies"]!!, 100f)
                    )
                )
            )
        )
    }

    private fun createLunch(): MealMenu {
        return MealMenu(
            name = "Lunch",
            dishes = mutableListOf(
                Dish(
                    name = "Soup",
                    components = mutableListOf(
                        MealComponent(FoodLib["Soup Mix"]!!, 200f),
                        MealComponent(FoodLib["Salt"]!!, 10f),
                        MealComponent(FoodLib["Water"]!!, 1500f)
                    )
                ),
                Dish(
                    name = "Coffee with cookies",
                    components = mutableListOf(
                        MealComponent(FoodLib["Coffee"]!!, 20f),
                          MealComponent(FoodLib["Sugar"]!!, 4f),
                        MealComponent(FoodLib["Cookies"]!!, 100f)
                    )
                )
            )
        )
    }

    private fun createBreakfast(): MealMenu {
        return MealMenu(
            name = "Breakfast",
            dishes = mutableListOf(
                Dish(
                    name = "Cereal",
                    components = mutableListOf(
                        MealComponent(FoodLib["Oats"]!!, 200f),
                        MealComponent(FoodLib["Sugar"]!!, 10f),
                        MealComponent(FoodLib["Milk"]!!, 200f)
                    )
                ),
                Dish(
                    name = "Coffee",
                    components = mutableListOf(
                        MealComponent(FoodLib["Coffee"]!!, 20f),
                        MealComponent(FoodLib["Sugar"]!!, 4f)
                    )
                ),

                Dish(
                    name = "Toast",
                    components = mutableListOf(
                        MealComponent(FoodLib["Bread"]!!, 100f),
                        MealComponent(FoodLib["Butter"]!!, 10f)
                    )
                )
            )
        )
    }


    private fun addRation(item: RationProject) {
        RATIONS.add(item)
        RATIONS_MAP[item.name] = item
    }

    private fun createDummyRation(position: Int): RationProject {
        return RationProject(
            id = position,
            name = "Ration #$position",
            description = "Item $position",
            profile = RationProfile(
                days = position % 3 + position % 5,
                nutritionProfile = NutritionProfileTemplates.normal(),
                members = mutableListOf(
                    Member("Vasiliy", 0.8f),
                    Member("Dmitriy", 1.1f),
                    Member("Valeria", 0.9f),
                    Member("Zaky", 1.2f)
                )
            )
        )
    }

}