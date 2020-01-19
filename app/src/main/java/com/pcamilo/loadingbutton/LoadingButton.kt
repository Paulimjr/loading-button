package com.pcamilo.loadingbutton

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import br.com.loadingbutton.R
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.layout_loading_button.view.*

/**
 * Loading button is a class to manage all events
 *
 * @author Paulo Cesar
 * @contact pauloc.sistemas@gmail.com
 * @date 202-19-01
 */
class LoadingButton(context: Context?, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    private var mIsLoading: Boolean = false
    private var mDefaultWidth: Int = 0
    private var mDefaultHeight: Int = 0
    private var defaultSizeProg = 250

    init {
        View.inflate(context, R.layout.layout_loading_button, this)
        val attributes = context?.obtainStyledAttributes(attrs, R.styleable.LoadingButton)
        customAttributes(attributes)
    }

    /**
     * Custom attributes to change the LoadingButton styles
     */
    private fun customAttributes(attributes: TypedArray?) {
        attributes?.let {

            mDefaultWidth = width
            mDefaultHeight = height

            //Loading attribute
            mIsLoading = it.getBoolean(R.styleable.LoadingButton_isLoading, false)

            // Change Button text
            it.getString(R.styleable.LoadingButton_buttonLabel)?.let { buttonLabel ->
                tv_loading_btn.text = buttonLabel
            }

            // Change Button color
            it.getColor(R.styleable.LoadingButton_buttonColor, Color.GRAY).let { color ->
                btnLoading.backgroundTintList = ColorStateList.valueOf(color)
            }

            // Change color progress bar
            it.getColor(R.styleable.LoadingButton_loadingColor, Color.WHITE).let { color ->
                progress_bar.indeterminateTintList = ColorStateList.valueOf(color)
            }

            it.recycle()
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (mDefaultHeight == 0 && mDefaultWidth == 0 && w != 0 && h != 0) {
            mDefaultHeight = height
            mDefaultWidth = width
        }
    }

    /**
     * Start or Stop loading
     *
     * @param isLoading the attribute allow you execute the loading on Button
     *      (True = Execute loading)
     *      (False = No execute loading)
     */
     fun isLoading(isLoading: Boolean) {
        if (isLoading) {
            progress_bar.visibility = View.VISIBLE
            tv_loading_btn.visibility = View.GONE
            contentMain.background = ContextCompat.getDrawable(context, R.drawable.shape_circle_loading_button)
            contentMain.layoutParams.width = defaultSizeProg.dp
            contentMain.layoutParams.height = defaultSizeProg.dp
            this.mIsLoading = false
        } else {
            tv_loading_btn.visibility = View.VISIBLE
            progress_bar.visibility = View.GONE
            contentMain.background = ContextCompat.getDrawable(context, R.drawable.shape_custom_loading_button)
            contentMain.layoutParams = LayoutParams(mDefaultWidth, mDefaultHeight)
            this.mIsLoading = true
        }
    }

    /**
     * Check if already loading
     *
     * @return if loading returns TRUE or FALSE
     */
    fun btnIsLoading() = this.mIsLoading

    private val Int.dp: Int
        get() = (this / Resources.getSystem().displayMetrics.density).toInt()
}