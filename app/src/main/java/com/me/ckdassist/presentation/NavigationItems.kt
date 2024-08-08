package com.me.ckdassist.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem (val route: String, val icon: ImageVector?, var title: String) {
    object Info: NavigationItem("Info", Icons.Rounded.Info, "Info")
    object History: NavigationItem("History", Icons.Rounded.List, "History")
    object Profile: NavigationItem("Profile", Icons.Rounded.Info, "Profile")
}








