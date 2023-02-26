package com.wyb.wyb_android.ui.notification

import androidx.lifecycle.ViewModel
import com.wyb.wyb_android.data.model.Notification
import com.wyb.wyb_android.data.model.OtherProfile

class NotificationViewModel : ViewModel() {
    val notificationItems = listOf(
        Notification(20, 6, 49, 24, false, "2023-02-12T15:05:09.312Z", "2023-02-25T15:05:09.312Z", false, false, "님이 내 챌린지를 응원해요!", null, "cheer", "희원님이 내 챌린지를 응원해요!", OtherProfile(49, "희원")),
        Notification(20, 6, 49, 24, false, "2023-02-12T14:45:09.312Z", "2023-02-24T14:45:09.312Z", false, false, "님이 나의 보틀을 팔로우합니다.", null, "followed", "가니님이 나의 보틀을 팔로우합니다.", OtherProfile(49, "가니")),
        Notification(20, 6, 49, 24, false, "2023-02-12T14:35:09.312Z", "2023-02-24T14:35:09.312Z", false, false, "님이 내 챌린지 성공을 축하해요!", null, "congrats", "가니가니가님이 내 챌린지 성공을 축하해요!", OtherProfile(49, "가니가니가")),
        Notification(20, 6, 49, 24, false, "2023-02-12T14:15:09.312Z", "2023-02-24T14:15:09.312Z", false, false, "님의 보틀을 팔로우합니다.", null, "following", "밍찌님의 보틀을 팔로우합니다.", OtherProfile(49, "밍찌")),
        Notification(20, 6, 49, 24, false, "2023-02-12T12:10:09.312Z", "2023-02-23T12:10:09.312Z", false, true, "님이 새로운 챌린지를 시작했어요!", null, "cheer", "가니가니가님이 새로운 챌린지를 시작했어요!", OtherProfile(49, "가니가니가")),
        Notification(20, 6, 49, 24, false, "2023-02-12T09:10:09.312Z", "2023-02-22T09:10:09.312Z", false, false, "님이 내 챌린지를 응원해요!", null, "cheer", "희원님이 내 챌린지를 응원해요!", OtherProfile(49, "희원")),
        Notification(20, 6, 49, 24, false, "2023-02-11T13:10:09.312Z", "2023-02-21T13:10:09.312Z", false, false, "님이 내 챌린지를 응원해요!", null, "cheer", "희원님이 내 챌린지를 응원해요!", OtherProfile(49, "희원")),
        Notification(20, 6, 49, 24, false, "2023-02-10T13:10:09.312Z", "2023-02-20T13:10:09.312Z", false, true, "님이 챌린지를 성공했어요!", null, "congrats", "가니님이 챌린지를 성공했어요!", OtherProfile(49, "가니")),
        Notification(20, 6, 49, 24, false, "2023-02-09T13:10:09.312Z", "2023-02-09T13:10:09.312Z", false, false, "님이 내 챌린지를 응원해요!", null, "cheer", "희원님이 내 챌린지를 응원해요!", OtherProfile(49, "희원")),
        Notification(20, 6, 49, 24, false, "2023-02-08T13:10:09.312Z", "2023-02-08T13:10:09.312Z", false, false, "님이 내 챌린지를 응원해요!", null, "cheer", "희원님이 내 챌린지를 응원해요!", OtherProfile(49, "희원")),
        Notification(20, 6, 49, 24, false, "2023-02-05T13:10:09.312Z", "2023-02-05T13:10:09.312Z", false, false, "님이 내 챌린지를 응원해요!", null, "cheer", "희원님이 내 챌린지를 응원해요!", OtherProfile(49, "희원")),
        Notification(20, 6, 49, 24, false, "2023-02-04T13:10:09.312Z", "2023-02-04T13:10:09.312Z", false, false, "님이 내 챌린지를 응원해요!", null, "cheer", "희원님이 내 챌린지를 응원해요!", OtherProfile(49, "희원")),
        Notification(20, 6, 49, 24, false, "2023-02-03T09:10:09.312Z", "2023-02-03T09:10:09.312Z", false, false, "님이 내 챌린지를 응원해요!", null, "cheer", "희원님이 내 챌린지를 응원해요!", OtherProfile(49, "희원"))
    )
}