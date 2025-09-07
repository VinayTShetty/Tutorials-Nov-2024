package com.androidtutorials.data.mapper

import com.androidtutorials.data.remote.dto.UserDto
import com.androidtutorials.domain.model.User

fun UserDto.toDomain(): User=User( id = id, name = name, email = email, companyname = company?.name ?: "N/A")