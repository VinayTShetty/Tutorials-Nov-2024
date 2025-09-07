package com.androidtutorials.data.remote.dto

// ✅ Network DTOs mirror the API JSON structure.
// Keeping DTOs separate avoids leaking API-specific shapes into Domain.
data class UserDto(
    val id: Int,
    val name: String,
    val email: String,
    val company: CompanyDto?
)

data class CompanyDto(
    val name: String?
)
