package com.me.ckdassist.presentation.ui.recipe_list

import com.me.ckdassist.presentation.ui.recipe_list.FoodCategory.*

enum class FoodCategory(val value: String){
    BREAD("Bread"),
    CHICKEN("Chicken"),
    BEEF("Beef"),
    NOODLES("Noodles"),
    STARCH("Starch"),
    SOUP("Soup"),
    DESSERT("Dessert"),
    VEGETARIAN("Vegetarian"),
    MILK("Milk"),
    VEGAN("Vegan"),
    PIZZA("Pizza"),
    DONUT("Donut"),
}

fun getAllFoodCategories(): List<FoodCategory>{
    return listOf(BREAD, CHICKEN, BEEF, NOODLES, STARCH, SOUP, DESSERT, VEGETARIAN, MILK, VEGAN, PIZZA, DONUT)
}

fun getFoodCategory(value: String): FoodCategory? {
    val map = FoodCategory.values().associateBy(FoodCategory::value)
    return map[value]
}