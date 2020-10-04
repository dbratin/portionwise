package org.portionwise.models

data class RationProject(
    val id: Int,
    val name: String,
    val description: String,
    val profile: RationProfile
)