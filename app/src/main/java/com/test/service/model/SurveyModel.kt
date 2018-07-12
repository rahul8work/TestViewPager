package com.test.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SurveyModel(
        @SerializedName("id")
        @Expose
        var id: String? = null,
        @SerializedName("title")
        @Expose
        var title: String? = null,
        @SerializedName("description")
        @Expose
        var description: String? = null,
        @SerializedName("access_code_prompt")
        @Expose
        var accessCodePrompt: Any? = null,
        @SerializedName("is_active")
        @Expose
        var isActive: Boolean? = false,
        @SerializedName("cover_image_url")
        @Expose
        var coverImageUrl: String? = null,
        @SerializedName("cover_background_color")
        @Expose
        var coverBackgroundColor: Any? = null,
        @SerializedName("type")
        @Expose
        var type: String? = null,
        @SerializedName("created_at")
        @Expose
        var createdAt: String? = null,
        @SerializedName("active_at")
        @Expose
        var activeAt: String? = null,
        @SerializedName("inactive_at")
        @Expose
        var inactiveAt: Any? = null,
        @SerializedName("survey_version")
        @Expose
        var surveyVersion: Int? = 0,
        @SerializedName("short_url")
        @Expose
        var shortUrl: String? = null,
        var isShown: Boolean? = false
)