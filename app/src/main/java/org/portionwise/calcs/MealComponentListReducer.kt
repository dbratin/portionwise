package org.portionwise.calcs

import org.portionwise.models.MealComponent
import java.util.stream.Stream

class MealComponentListReducer {
    companion object {

        fun reduce(mealComponentsStream: Stream<MealComponent>): Stream<MealComponent> {
            return mealComponentsStream
                .reduce(HashMap<String, MealComponent>(),
                    { map, item -> add(map, item) },
                    { map1, map2 -> merge(map1, map2) })
                .values.stream()
        }

        private fun add(
            map: HashMap<String, MealComponent>,
            component: MealComponent
        ): HashMap<String, MealComponent> {
            if (map.containsKey(component.foodStuff.name)) {
                map[component.foodStuff.name] = MealComponent(component.foodStuff, map[component.foodStuff.name]!!.amount + component.amount)
            } else {
                map[component.foodStuff.name] = component
            }

            return map
        }

        private fun merge(
            map1: HashMap<String, MealComponent>,
            map2: HashMap<String, MealComponent>
        ): HashMap<String, MealComponent> {
            for (item in map2.values) {
                add(map1, item)
            }
            return map1
        }
    }
}