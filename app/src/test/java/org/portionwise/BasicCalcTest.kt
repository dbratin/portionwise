package org.portionwise

import junit.framework.Assert.fail
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.hasItem
import org.hamcrest.Matchers.hasSize
import org.junit.Test
import org.portionwise.calcs.ApportionmentBuilder
import org.portionwise.calcs.MealScheduleBuilder
import org.portionwise.calcs.ShoppingListBuilder
import org.portionwise.dummy.FoodLib
import org.portionwise.exceptions.NutritionMismatchException
import org.portionwise.helpers.*
import org.portionwise.models.*

class BasicCalcTest {



    @Test
    fun buildShoppingList() {
        val apportionment = Apportionment().builder {
            repeating(3) {
                meal("Brekfast") {
                    item(FoodLib["Oats"]!!, 200f)
                    item(FoodLib["Coffee"]!!, 10f)
                    item(FoodLib["Toast"]!!, 20f)
                }

                meal("Lunch") {
                    item(FoodLib["Soup Mix"]!!, 200f)
                    item(FoodLib["Salt"]!!, 10f)
                    item(FoodLib["Water"]!!, 1500f)
                    item(FoodLib["Bread"]!!, 500f)
                    item(FoodLib["Coffee"]!!, 10f)
                    item(FoodLib["Cookies"]!!, 100f)
                }

                meal("Dinner") {
                    item(FoodLib["Beef"]!!, 1000f)
                    item(FoodLib["Spices Mix"]!!, 10f)
                    item(FoodLib["Salt"]!!, 10f)
                    item(FoodLib["Tea"]!!, 10f)
                    item(FoodLib["Cookies"]!!, 100f)
                }
            }
        }

        val shoppingList = ShoppingListBuilder()
            .withApportionment(apportionment)
            .build()

        assertThat(shoppingList.components, hasSize(11))
        assertThat(shoppingList.components, hasItem(MealComponent(FoodLib["Oats"]!!, 600f)))
        assertThat(shoppingList.components, hasItem(MealComponent(FoodLib["Coffee"]!!, 60f)))
        assertThat(shoppingList.components, hasItem(MealComponent(FoodLib["Toast"]!!, 60f)))
        assertThat(shoppingList.components, hasItem(MealComponent(FoodLib["Soup Mix"]!!, 600f)))
        assertThat(shoppingList.components, hasItem(MealComponent(FoodLib["Water"]!!, 4500f)))
        assertThat(shoppingList.components, hasItem(MealComponent(FoodLib["Bread"]!!, 1500f)))
        assertThat(shoppingList.components, hasItem(MealComponent(FoodLib["Beef"]!!, 3000f)))
        assertThat(shoppingList.components, hasItem(MealComponent(FoodLib["Spices Mix"]!!, 30f)))
        assertThat(shoppingList.components, hasItem(MealComponent(FoodLib["Salt"]!!, 60f)))
        assertThat(shoppingList.components, hasItem(MealComponent(FoodLib["Tea"]!!, 30f)))
        assertThat(shoppingList.components, hasItem(MealComponent(FoodLib["Cookies"]!!, 600f)))
    }

    @Test
    fun buildApportionmentByMenu() {
        val schedule = MealSchedule(3).builder {
            day(1..3) {
                meal("Breakfast") {
                    dish("Cereal") {
                        item(FoodLib["Oats"]!!, 100f)
                        item(FoodLib["Sugar"]!!, 12f)
                        item(FoodLib["Milk"]!!, 200f)
                    }

                    dish("Coffee") {
                        item(FoodLib["Coffee"]!!, 20f)
                        item(FoodLib["Sugar"]!!, 4f)
                    }

                    dish("Toast") {
                        item(FoodLib["Bread"]!!, 100f)
                        item(FoodLib["Butter"]!!, 10f)
                    }
                }

                meal("Lunch") {
                    dish("Soup") {
                        item(FoodLib["Soup Mix"]!!, 200f)
                        item(FoodLib["Salt"]!!, 10f)
                        item(FoodLib["Water"]!!, 1500f)
                    }

                    dish("Coffee with cookies") {
                        item(FoodLib["Coffee"]!!, 20f)
                        item(FoodLib["Sugar"]!!, 4f)
                        item(FoodLib["Cookies"]!!, 100f)
                    }
                }
            }
        }

        val profile = RationProfile().builder {
            member("Vitaliy", 1f)
            member("Valeriy", 1.5f)
            member("Victoria", 0.9f)
            member("Valentina", 1.1f)
        }

        val apportionment = ApportionmentBuilder()
            .withMealSchedule(schedule)
            .withMealProfile(profile)
            .build()

        assertThat(apportionment.meals, hasSize(6))
        assertThat(apportionment.meals[0].components, hasSize(6))
        assertThat(apportionment.meals[1].components, hasSize(6))
        assertThat(apportionment.meals[2].components, hasSize(6))
        assertThat(apportionment.meals[3].components, hasSize(6))
        assertThat(apportionment.meals[4].components, hasSize(6))
        assertThat(apportionment.meals[5].components, hasSize(6))

        assertThat(
            apportionment.meals[0].components,
            hasItem(MealComponent(FoodLib["Oats"]!!, 450f))
        )
        assertThat(
            apportionment.meals[0].components,
            hasItem(MealComponent(FoodLib["Sugar"]!!, 72f))
        )
        assertThat(
            apportionment.meals[0].components,
            hasItem(MealComponent(FoodLib["Milk"]!!, 900f))
        )
        assertThat(
            apportionment.meals[0].components,
            hasItem(MealComponent(FoodLib["Coffee"]!!, 90f))
        )
        assertThat(
            apportionment.meals[0].components,
            hasItem(MealComponent(FoodLib["Bread"]!!, 450f))
        )
        assertThat(
            apportionment.meals[0].components,
            hasItem(MealComponent(FoodLib["Butter"]!!, 45f))
        )
    }

    @Test
    fun composeMealSchedule() {

        val profile = RationProfile().builder {
            nutritionProfile(
                calories = 2000f,
                protein = 100f,
                fat = 150f,
                carbohydrate = 200f
            )

            mealsPerDay = 3
            days = 3

            member("Vitaliy", 1f)
            member("Valeriy", 1.5f)
            member("Victoria", 0.9f)
            member("Valentina", 1.1f)
        }

        val builder = MealScheduleBuilder(profile)

        try {
            builder.validate()
            fail("Should fail")
        } catch (e: NutritionMismatchException) {
            //that's good
        }

        try {
            builder.build()
            fail("Should fail")
        } catch (e: NutritionMismatchException) {
            //that's good
        }

        builder.addMeal(days = 1..3, meal = MealMenu("Breakfast").builder {
            dish("Coffee") {
                item(FoodLib["Coffee"]!!, 10f)
                item(FoodLib["Sugar"]!!, 12f)
            }
        })

        builder.addDish(days = 1..3, meal = "Breakfast", dish =
            Dish("Cereal").builder {
                item(FoodLib["Oats"]!!, 100f)
                item(FoodLib["Sugar"]!!, 12f)
                item(FoodLib["Milk"]!!, 200f)
            }
        )

        builder.addDish(days = 1..3, meal = "Breakfast", dish =
            Dish("Toast").builder {
                item(FoodLib["Toast"]!!, 100f)
                item(FoodLib["Butter"]!!, 20f)
            }
        )

        try {
            builder.validate()
            fail("Should fail")
        } catch (e: NutritionMismatchException) {
            //that's good
        }

        builder.addMeal(days = 1..3, meal = MealMenu("Lunch").builder {
            dish("Soup") {
                item(FoodLib["Soup Mix"]!!, 200f)
                item(FoodLib["Salt"]!!, 10f)
                item(FoodLib["Water"]!!, 1500f)
                item(FoodLib["Bread"]!!, 500f)
            }

            dish("Coffee") {
                item(FoodLib["Coffee"]!!, 10f)
                item(FoodLib["Sugar"]!!, 12f)
                item(FoodLib["Cookies"]!!, 100f)
            }
        })

        builder.addMeal(days = 1..3, meal = MealMenu("Dinner").builder {
            dish("Soup") {
                item(FoodLib["Beef"]!!, 1000f)
                item(FoodLib["Spices Mix"]!!, 10f)
                item(FoodLib["Salt"]!!, 10f)
            }

            dish("Tea") {
                item(FoodLib["Tea"]!!, 10f)
                item(FoodLib["Cookies"]!!, 100f)
                item(FoodLib["Sugar"]!!, 12f)
            }
        })

        builder.validate()
    }
}