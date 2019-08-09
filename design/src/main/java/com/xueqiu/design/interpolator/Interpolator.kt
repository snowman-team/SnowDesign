package com.xueqiu.design.interpolator

import android.view.animation.PathInterpolator

class EaseInterpolator : PathInterpolator(.38f, 0f, .25f, 1f)

class EaseInInterpolator : PathInterpolator(.42f, 0f, 1f, 1f)

class EaseOutInterpolator : PathInterpolator(0f, 0f, .58f, 1f)

class EaseInOutInterpolator : PathInterpolator(.42f, 0f, .58f, 1f)

class ShowCurveGraphInterpolator : PathInterpolator(0f, 0f, .1f, 1f)

class ShowCurveHighInterpolator : PathInterpolator(.18f, .88f, .4f, 1.3f)

class ShowCurveLowInterpolator : PathInterpolator(.18f, .88f, .3f, 1.1f)