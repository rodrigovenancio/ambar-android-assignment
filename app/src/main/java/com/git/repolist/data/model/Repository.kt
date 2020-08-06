package com.git.repolist.data.model

import com.google.gson.annotations.SerializedName

data class Repository(
    val id: String,
    val name: String,
    @SerializedName("full_name")
    val fullName: String? = null,
    @SerializedName("html_url")
    val htmlUrl: String? = null,
    val owner: Owner? = null
)