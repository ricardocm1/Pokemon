package com.android.pokemon.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.android.pokemon.R
import com.android.pokemon.databinding.TitleBinding

class Title @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    var binding: TitleBinding? = null

    var text: String? = null
        set(value) {
            field = value
            value?.let {
                binding?.tvTitle?.text = it
            }
        }

    init {
        binding = TitleBinding.inflate(LayoutInflater.from(context), this, true)
        LayoutInflater.from(context).inflate(R.layout.title, this, true)
        attrs?.let { attributeSet ->
            context.obtainStyledAttributes(attributeSet, R.styleable.Title).run {
                text = getString(R.styleable.Title_text)
                recycle()
            }
        }
    }

}