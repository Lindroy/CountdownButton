package com.lindroid.countdownbutton

import android.annotation.SuppressLint
import android.content.Context
import android.os.CountDownTimer
import android.support.v7.widget.AppCompatButton
import android.util.AttributeSet

/**
 * @author Lin
 * @date 2019/3/25
 * @function 自定义倒计时按钮
 * @Description
 */
class CountdownButton : AppCompatButton {
    /**倒计时的总时间**/
    var millisInFuture = 60 * 1000
    /**倒计时的时间间隔**/
    var countDownInterval = 1 * 1000
    /**倒计时是否在进行**/
    private var isTicking = false
    /**倒计时是否要开始**/
    private var isStarted = false
    private var countdownTimer: CountDownTimer? = null
    /**正常按钮文字**/
    private var buttonText = ""
    /**倒计时按钮文字**/
    var tickText = ""
    /**倒计时结束时的按钮文字**/
    var finishText = ""

    private var finishedListener: (() -> Unit)? = null

    private var tickListener: ((interval: Int) -> Unit)? = null

    private var startListener: (() -> Unit)? = null

    private var stopListener: (() -> Unit)? = null

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, android.R.attr.buttonStyle)
    @SuppressLint("Recycle")
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val typedArray = context?.obtainStyledAttributes(
                attrs,
                R.styleable.CountdownButton,
                defStyleAttr,
                0
        )
        typedArray?.let {
            tickText = it.getString(R.styleable.CountdownButton_tickText)
                    ?: context.getString(R.string.countdown_button_tick_text)
            finishText = it.getString(R.styleable.CountdownButton_finishedText)
                    ?: context.getString(R.string.countdown_button_finished_text)
            millisInFuture = it.getInt(R.styleable.CountdownButton_millisInFuture, millisInFuture)
            countDownInterval = it.getInt(R.styleable.CountdownButton_countDownInterval, countDownInterval)
            it.recycle()
        }
        //取消全部英文字母大写
        isAllCaps = false

    }

    fun start() {
        isStarted = true
        updateCounting()
    }

    fun stop() {
        isStarted = false
        updateCounting()
    }

    private fun updateCounting() {
        if (isTicking == isStarted) {
            return
        }
        when (isStarted) {
            //倒计时开启
            true -> {
                startListener?.invoke()
                isEnabled = false
                buttonText = text.toString()
                if (countdownTimer == null) {
                    countdownTimer = object : CountDownTimer(
                            (millisInFuture - 1000).toLong(), //millisInFuture-1000是为了跳过0秒
                            countDownInterval.toLong()
                    ) {
                        /**
                         * Callback fired on regular interval.
                         * @param millisUntilFinished The amount of time until finished.
                         */
                        override fun onTick(millisUntilFinished: Long) {
                            //转换成double类型后再除于1000，是为了得到小数，避免出现漏秒的情况
                            val time = (Math.round(millisUntilFinished.toDouble() / 1000)).toInt()
                            text = String.format(tickText, time)
                            tickListener?.invoke(time)
                        }

                        /**
                         * Callback fired when the time is up.
                         */
                        override fun onFinish() {
                            //倒计时结束
                            isTicking = false
                            isEnabled = true
                            text = finishText
                            finishedListener?.invoke()
                        }
                    }.start()
                } else {
                    countdownTimer!!.start()
                }
            }
            //倒计时结束
            false -> {
                stopListener?.invoke()
                isEnabled = true
                text = buttonText
                countdownTimer?.cancel()
            }
        }
        isTicking = isStarted
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stop()
        finishedListener = null
        tickListener = null
    }

    /**
     * 倒计时完成的监听
     */
    fun setOnFinishedListener(listener: () -> Unit) {
        finishedListener = listener
    }

    /**
     * 倒计时进行中的监听
     * @param listener:参数interval为经过优化处理的整数
     */
    fun setOnTickListener(listener: (interval: Int) -> Unit) {
        tickListener = listener
    }

    /**
     * 倒计时开始的监听
     */
    fun setOnStartListener(listener: () -> Unit) {
        startListener = listener
    }

    /**
     * 倒计时结束的监听
     */
    fun setOnStopListener(listener: () -> Unit) {
        stopListener = listener
    }

}