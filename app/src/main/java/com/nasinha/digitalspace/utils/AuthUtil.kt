package com.nasinha.digitalspace.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.nasinha.digitalspace.utils.Constants.APP_KEY
import com.nasinha.digitalspace.utils.Constants.EMPTY_STRING
import com.nasinha.digitalspace.utils.Constants.UIID_KEY
import com.nasinha.digitalspace.utils.Constants.USER_EMAIL
import com.nasinha.digitalspace.utils.Constants.USER_NAME

object AuthUtil {

    fun saveUserId(context: Context, uiid: String?) {
        val preferences: SharedPreferences =
            context.getSharedPreferences(APP_KEY, Context.MODE_PRIVATE)
        preferences.edit().putString(UIID_KEY, uiid).apply()
    }

    fun saveUserName(context: Context, name: String?) {
        val preferences: SharedPreferences =
            context.getSharedPreferences(APP_KEY, Context.MODE_PRIVATE)

        if (!name.isNullOrEmpty()) {
            val newString = name.split(" ").joinToString(" ") { it.capitalize() }
            preferences.edit().putString(USER_NAME, newString).apply()
        } else {
            preferences.edit().putString(USER_NAME, name).apply()
        }
    }

    fun saveUserEmail(context: Context, email: String?) {
        val preferences: SharedPreferences =
            context.getSharedPreferences(APP_KEY, Context.MODE_PRIVATE)
        preferences.edit().putString(USER_EMAIL, email).apply()
    }

    fun getUserId(context: Context): String? {
        val preferences = context.getSharedPreferences(APP_KEY, Context.MODE_PRIVATE)
        return preferences.getString(UIID_KEY, EMPTY_STRING)
    }

    fun getUserName(context: Context): String? {
        val preferences = context.getSharedPreferences(APP_KEY, Context.MODE_PRIVATE)
        return preferences.getString(USER_NAME, EMPTY_STRING)
    }

    fun getUserEmail(context: Context): String? {
        val preferences = context.getSharedPreferences(APP_KEY, Context.MODE_PRIVATE)
        return preferences.getString(USER_EMAIL, EMPTY_STRING)
    }

    fun clearUserInfo(context: Context) {
        val preferences = context.getSharedPreferences(APP_KEY, Context.MODE_PRIVATE)
        preferences.edit().putString(UIID_KEY, EMPTY_STRING).apply()
        preferences.edit().putString(USER_NAME, EMPTY_STRING).apply()
        preferences.edit().putString(USER_EMAIL, EMPTY_STRING).apply()
    }

    fun validateNameEmailPassword(name: String, email: String, password: String): Boolean {
        return when {
            name.isEmpty() || email.isEmpty() || password.isEmpty() -> {
                false
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                false
            }
            password.length < 6 -> {
                false
            }
            else -> true
        }
    }

    fun validateEmailPassword(email: String, password: String): Boolean {
        return when {
            email.isEmpty() || password.isEmpty() -> {
                false
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                false
            }
            password.length < 6 -> {
                false
            }
            else -> true
        }
    }

    fun hideKeyboard(view: View) {
        val imm: InputMethodManager =
            view.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}