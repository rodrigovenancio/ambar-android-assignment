package com.git.repolist.data.model

data class Repository(
    val id: String,
    val name: String,
    val fullName: String? = null,
    val htmlUrl: String? = null,
    val owner: Owner? = null
)