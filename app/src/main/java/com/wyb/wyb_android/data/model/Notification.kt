package com.wyb.wyb_android.data.model

data class Notification(
    val id: Int,
    val notificationId: Int,
    val userId: Int,
    val receiverUserId: Int,
    val isDeleted: Boolean,
    val updatedAt: String,
    val createdAt: String,
    val isButtonClicked: Boolean,
    val isButtonEnabled: Boolean,
    val content: String,
    val buttonText: String?,
    val notificationName: String,
    val notiText: String,
    val sentUser: OtherProfile
)