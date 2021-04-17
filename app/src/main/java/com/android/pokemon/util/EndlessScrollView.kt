package com.android.pokemon.util

import androidx.core.widget.NestedScrollView

abstract class EndlessScrollView : NestedScrollView.OnScrollChangeListener {

    override fun onScrollChange(
        v: NestedScrollView?,
        scrollX: Int,
        scrollY: Int,
        oldScrollX: Int,
        oldScrollY: Int
    ) {
        (v?.getChildAt(v.childCount - 1))?.let { lastChild ->
            if ((scrollY >= (lastChild.measuredHeight - v.measuredHeight)) && scrollY > oldScrollY) {
                onLoadMore()
            }
        }
    }

    abstract fun onLoadMore()
}