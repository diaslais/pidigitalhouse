package com.nasinha.digitalspace.developer.repository

import com.nasinha.digitalspace.developer.dao.DeveloperDao
import com.nasinha.digitalspace.developer.entity.DeveloperEntity

class DeveloperRepository(private val developerDao: DeveloperDao) {
    suspend fun addDeveloper(developer: DeveloperEntity) = developerDao.addDeveloper(developer)
    suspend fun getAll() = developerDao.getAll()
    suspend fun deleteAll() = developerDao.deleteAll()
}