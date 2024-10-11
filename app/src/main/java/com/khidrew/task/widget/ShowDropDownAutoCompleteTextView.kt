package com.khidrew.task.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent

/**
 * Drop down auto complete custom class
 */

class ShowDropDownAutoCompleteTextView : androidx.appcompat.widget.AppCompatAutoCompleteTextView {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_UP -> {
                showDropDown()
                performClick()
            }
        }
        return super.onTouchEvent(event)
    }

}