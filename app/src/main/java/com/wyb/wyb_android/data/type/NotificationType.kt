package com.wyb.wyb_android.data.type

enum class NotificationType {
    CHEER,
    CONGRATS;

    override fun toString(): String {
        return when (this) {
            CHEER -> "cheer"
            CONGRATS -> "congrats"
        }
    }
}