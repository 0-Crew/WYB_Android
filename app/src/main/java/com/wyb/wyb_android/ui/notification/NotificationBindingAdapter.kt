package com.wyb.wyb_android.ui.notification

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.wyb.wyb_android.R
import com.wyb.wyb_android.util.Constants
import com.wyb.wyb_android.widget.WYBCompactTextButton
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("initialName", "textNotiType")
fun setInitialName(textView: TextView, name: String, type: String) {
    textView.text = if (type == "following" || type == "followed") name.first().toString() else ""
}

@BindingAdapter("imgNotiType")
fun setNotiIcon(imageView: ImageView, type: String) {
    when (type) {
        "cheer" -> imageView.setImageResource(R.drawable.ic_alarm_cheer)
        "congrats" -> imageView.setImageResource(R.drawable.ic_alarm_celeb)
        else -> imageView.setImageResource(0)
    }
}

@BindingAdapter("notiContent", "sentUser")
fun setNotiContent(textView: TextView, content: String, user: String) {
    val notiContent = user + content
    val spannableStr = SpannableString(notiContent)
    spannableStr.setSpan(
        StyleSpan(Typeface.BOLD),
        0,
        user.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    textView.text = spannableStr
}

@BindingAdapter("notiBtnType", "notiBtnVisibility")
fun setBtnAttr(button: WYBCompactTextButton, type: String, visibility: Boolean) {
    button.isVisible = visibility
    if (!visibility) return
    if (type == "cheer") {
        button.setTvTitle(button.context.getString(R.string.notification_btn_cheer))
    } else {
        button.setTvTitle(button.context.getString(R.string.notification_btn_celeb))
    }
}

@BindingAdapter("notiCreatedDate")
fun setNotiCreatedDate(textView: TextView, date: String) {
    val diffTime = getDiffTime(date)
    textView.text = getTimeString(diffTime)
}

@BindingAdapter("notiVisibleDate")
fun setNotiVisibility(layout: LinearLayout, date: String) {
    val diffTime = getDiffTime(date)
    layout.isVisible = (diffTime / Constants.HOUR / Constants.MIN / Constants.SEC) < 8
}

private fun getDiffTime(date: String): Long {
    val sdf = SimpleDateFormat(Constants.ISO_DATE_FORMAT, Locale.getDefault())
    sdf.timeZone = TimeZone.getTimeZone("Etc/UTC")
    val createdDate = sdf.parse(date) ?: return -1
    val createdTime = createdDate.time
    val currentTime = System.currentTimeMillis()
    return (currentTime - createdTime) / 1000
}

private fun getTimeString(diffTime: Long): String {
    return when {
        diffTime < Constants.SEC -> "방금 전"
        (diffTime / Constants.SEC) < 10 -> "${diffTime / Constants.SEC}분 전"
        (diffTime / Constants.SEC) < Constants.MIN -> "${(diffTime / Constants.SEC) - (diffTime / Constants.SEC) % 10}분 전"
        (diffTime / Constants.MIN / Constants.SEC) < Constants.HOUR -> "${diffTime / Constants.MIN / Constants.SEC}시간 전"
        (diffTime / Constants.HOUR / Constants.MIN / Constants.SEC) < 8 -> "${diffTime / Constants.HOUR / Constants.MIN / Constants.SEC}일 전"
        else -> ""
    }
}
