package com.nasinha.digitalspace.developer.adapter

import com.nasinha.digitalspace.developer.entity.DeveloperEntity

interface IDeveloper {
    fun linkedinDeveloper(linkedin: String)
    fun githubDeveloper(github: String)
}