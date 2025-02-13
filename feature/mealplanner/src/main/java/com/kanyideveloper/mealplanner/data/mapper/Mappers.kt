/*
 * Copyright 2023 Joel Kanyi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kanyideveloper.mealplanner.data.mapper

import com.joelkanyi.shared.core.data.local.FavoriteEntity
import com.joelkanyi.shared.core.data.local.MealEntity
import com.joelkanyi.shared.core.data.local.MealPlanEntity
import com.joelkanyi.shared.core.data.network.model.MealsResponse
import com.joelkanyi.shared.core.domain.CoreMeal
import com.kanyideveloper.core.model.OnlineMeal
import com.kanyideveloper.mealplanner.model.MealPlan
import java.util.UUID

internal fun MealPlan.toEntity(): MealPlanEntity {
    return MealPlanEntity(
        mealTypeName = mealTypeName,
        meals = meals,
        mealDate = date,
        id = id ?: UUID.randomUUID().toString()
    )
}

internal fun MealPlanEntity.toMealPlan(): MealPlan {
    return MealPlan(
        mealTypeName = mealTypeName,
        meals = meals ?: emptyList(),
        date = mealDate,
        id = id
    )
}

internal fun MealsResponse.Meal.toOnlineMeal(): OnlineMeal {
    return OnlineMeal(
        name = strMeal,
        imageUrl = strMealThumb,
        mealId = idMeal
    )
}

internal fun OnlineMeal.toGeneralMeal(): CoreMeal {
    return CoreMeal(
        name = name,
        imageUrl = imageUrl,
        onlineMealId = mealId,
        cookingTime = 0,
        category = "",
        cookingDifficulty = "",
        ingredients = emptyList(),
        cookingDirections = emptyList(),
        favorite = false,
        servingPeople = 0,
        localMealId = null
    )
}

internal fun MealEntity.toMeal(): CoreMeal {
    return CoreMeal(
        name = name,
        imageUrl = imageUrl,
        cookingTime = cookingTime ?: 0,
        category = category,
        cookingDifficulty = cookingDifficulty ?: "",
        ingredients = ingredients ?: emptyList(),
        cookingDirections = cookingInstructions ?: emptyList(),
        favorite = isFavorite,
        servingPeople = servingPeople ?: 0,
        localMealId = id
    )
}

internal fun FavoriteEntity.toMeal(): CoreMeal {
    return CoreMeal(
        name = mealName,
        imageUrl = mealImageUrl,
        localMealId = localMealId,
        onlineMealId = onlineMealId,
        favorite = isFavorite,
        cookingTime = 0,
        category = "",
        cookingDifficulty = "",
        ingredients = emptyList(),
        cookingDirections = emptyList(),
        servingPeople = 0
    )
}

/*
internal fun MealDetailsResponse.Meal.toMeal(): Meal {
    return Meal(
        name = strMeal,
        imageUrl = strMealThumb,
        cookingTime = 0,
        category = strCategory,
        cookingDifficulty = "",
        ingredients = listOf(
            strIngredient1,
            strIngredient2,
            strIngredient3,
            strIngredient4,
            strIngredient5,
            strIngredient6,
            strIngredient7,
            strIngredient8,
            strIngredient9,
            strIngredient10,
            strIngredient11,
            strIngredient12,
            strIngredient13,
            strIngredient14,
            strIngredient15,
            strIngredient16,
            strIngredient17,
            strIngredient18,
            strIngredient19,
            strIngredient20
        ).filter { !it.isNullOrEmpty() },
        cookingDirections = strInstructions.stringToList(),
        favorite = false,
        servingPeople = 0,
        onlineMealId = idMeal
    )
}
*/
