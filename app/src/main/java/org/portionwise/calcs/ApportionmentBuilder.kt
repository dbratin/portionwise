package org.portionwise.calcs

import org.portionwise.models.*
import java.util.stream.Collectors.toList

class ApportionmentBuilder {
    private var shedule: MealSchedule? = null
    private var profile: RationProfile? = null

    fun withMealSchedule(schedule: MealSchedule): ApportionmentBuilder {
        this.shedule = schedule
        return this
    }

    fun withMealProfile(profile: RationProfile): ApportionmentBuilder {
        this.profile = profile
        return this
    }

    fun build(): Apportionment {
        val commonFactor = profile
            ?.let {
                it.members.stream()
                    .map { m -> m.factor }
                    .reduce(0f) { a, f -> a + f }
            }
            ?: 1f

        return Apportionment().apply {
            meals.addAll(shedule
                ?.let {
                    it.days.stream()
                        .flatMap { e -> e.stream() }
                        .map { menu ->
                            Meal(
                                menu.name,
                                MealComponentListReducer
                                    .reduce(menu.dishes.stream()
                                        .flatMap { d -> d.components.stream() }
                                        .map { component ->
                                            MealComponent(
                                                component.foodStuff,
                                                commonFactor * component.amount
                                            )
                                        })
                                    .collect(toList())
                            )
                        }.collect(toList())
                }
                ?: mutableListOf()
            )
        }
    }

}
