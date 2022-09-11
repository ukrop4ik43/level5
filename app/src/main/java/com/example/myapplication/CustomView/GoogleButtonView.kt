package com.example.myapplication.CustomView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.myapplication.R
import com.example.myapplication.databinding.GoogleButtonLayoutBinding

class GoogleButtonView(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : ConstraintLayout(
    context, attrs, defStyleAttr, defStyleRes
) {
    private val binding: GoogleButtonLayoutBinding

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attrs,
        defStyleAttr,
        0
    )
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)
    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.google_button_layout, this, true)
        binding = GoogleButtonLayoutBinding.bind(this)
        initializeAttributes(attrs, defStyleAttr, defStyleRes)
    }

    private fun initializeAttributes(
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.GoogleButtonView,
            defStyleAttr,
            defStyleRes
        )
        val googleButtonText =
            typedArray.getString(R.styleable.GoogleButtonView_googleButtontext)
        binding.googleTV.text = googleButtonText ?: "GOOGLE"
        typedArray.recycle()
    }
}