package com.androidtutorials.data.mapper

import com.androidtutorials.data.local.UserEntity
import com.androidtutorials.data.model.UserDto
import com.androidtutorials.domain.model.User


fun convertDtoToEntity(userDto: UserDto): UserEntity {
    return UserEntity(userDto.id, userDto.name, userDto.email)
}

fun convertEntityToDomain(userEntity: UserEntity): User {
    return User(
        userEntity.id,
        userEntity.name,
        userEntity.email
    )
}