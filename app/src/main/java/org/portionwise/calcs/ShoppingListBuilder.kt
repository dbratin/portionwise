package org.portionwise.calcs

import org.portionwise.models.Apportionment
import org.portionwise.models.MealComponent
import org.portionwise.models.ShoppingList
import java.util.stream.Collectors.toList

class ShoppingListBuilder {

    var apportionment: Apportionment? = null

    fun withApportionment(apportionment: Apportionment): ShoppingListBuilder {
        this.apportionment = apportionment
        return this
    }

    fun build(): ShoppingList {
        return apportionment
            ?.let {
                ShoppingList(
                    MealComponentListReducer
                        .reduce(it.meals.stream().flatMap { meal -> meal.components.stream() })
                        .collect(toList())
                )
            }
            ?: ShoppingList(listOf())
    }
}