package com.me.ckdassist.network.response

import com.google.gson.annotations.SerializedName
import com.me.ckdassist.network.model.RecipeDto

data class RecipeSearchResponse(

        @SerializedName("count")
        var count: Int,

        @SerializedName("results")
        var recipes: List<RecipeDto>,
)