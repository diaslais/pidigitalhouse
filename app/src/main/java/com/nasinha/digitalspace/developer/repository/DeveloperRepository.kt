package com.nasinha.digitalspace.developer.repository

import com.nasinha.digitalspace.developer.dao.DeveloperDao
import com.nasinha.digitalspace.developer.entity.DeveloperEntity

class DeveloperRepository(private val developerDao: DeveloperDao) {
    suspend fun addDeveloper(developer: DeveloperEntity) = developerDao.addDeveloper(developer)
    suspend fun getAll() = developerDao.getAll()
    suspend fun deleteAll() = developerDao.deleteAll()
    fun setDevelopers(callback: (developers: List<DeveloperEntity>) -> Unit) {
        val developersDataSet = setDevelopersList()
        callback.invoke(developersDataSet)
    }

    private fun setDevelopersList(): List<DeveloperEntity> {
        return listOf(
            DeveloperEntity(
                1,
                "https://avatars3.githubusercontent.com/u/53455525?s=400&u=48e7cf6b27239f95a546f14b3f5284612a64d300&v=4",
                "Lais Dias",
                "",
                "bio",
                "",
                "https://github.com/diaslais"
            ), DeveloperEntity(
                2,
                "https://avatars0.githubusercontent.com/u/67712448?s=400&u=a7e6476cdc9e1dc46599e7f89be03ec0d1f17e52&v=4",
                "Mariana Marcelli",
                "",
                "bio",
                "",
                "https://github.com/MarianaMarcelli"
            ), DeveloperEntity(
                3,
                "https://avatars0.githubusercontent.com/u/52797828?s=460&u=b419f032286ae7d7f90f6a8a38e4b2e6510d3710&v=4",
                "Paulo Silva",
                "",
                "bio",
                "",
                "https://github.com/paulo4fs"
            ), DeveloperEntity(
                4,
                "https://avatars1.githubusercontent.com/u/41019069?s=400&u=a155e71b95b525e9bcce7b108afa45d2c41a755f&v=4",
                "Renan Damasceno",
                "",
                "bio",
                "",
                "https://github.com/renandamasceno"
            )
        )
    }
}