package com.nasinha.digitalspace.utils

import android.app.Activity
import androidx.drawerlayout.widget.DrawerLayout
import com.nasinha.digitalspace.R

object DrawerUtils {
    fun unlockDrawer(activity: Activity){
        val drawerLayout = activity.findViewById<DrawerLayout>(R.id.drawer_layout)
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }
    fun lockDrawer(activity: Activity){
        val drawerLayout = activity.findViewById<DrawerLayout>(R.id.drawer_layout)
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }
}