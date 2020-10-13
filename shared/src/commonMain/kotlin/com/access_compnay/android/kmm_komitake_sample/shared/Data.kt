package com.access_compnay.android.kmm_komitake_sample.shared

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepositoryData(
    val id: Int,
    @SerialName("node_id") val nodeId: String,
    val name: String,
    @SerialName("full_name") val fullName: String,
    val `private`: Boolean,
    @SerialName("html_url") val htmlUrl: String,
    @SerialName("default_branch") val defaultBranch: String,
    val description: String? = null,
    val fork: Boolean = false,
    val owner: OwnerData? = null,
)

@Serializable
data class OwnerData(
    val id: Int,
    val login: String,
    val type: String,
    val url: String,
    @SerialName("avatarUrl") val avatarUrl: String? = null,
)
