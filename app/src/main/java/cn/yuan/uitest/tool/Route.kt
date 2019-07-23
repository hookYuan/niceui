package cn.yuan.uitest.tool

import android.app.Activity
import androidx.fragment.app.Fragment
import yuan.core.mvp.BaseContract
import yuan.core.mvp.Presenter
import yuan.core.tool.RouteUtil
import yuan.core.tool.RouteUtil.REQUESTCODE

/**
 * 描述：Activity跳转扩展函数
 *
 * @author yuanye
 * @date 2019/7/19 13:51
 */

/**
 * Activity扩展函数
 */
fun <T : Activity> Activity.open(
    clazz: Class<T>,
    params: RouteUtil.RouteParam? = null,
    finishSelf: Boolean = false
) {
    RouteUtil.open(this, clazz, params, finishSelf)
}

/**
 * Fragment扩展函数
 */
fun <T : Fragment> Fragment.open(clazz: Class<T>, params: RouteUtil.RouteParam? = null, finishSelf: Boolean = false) {
    RouteUtil.open(activity, clazz, params, finishSelf)
}

/**
 * Presenter扩展函数
 */
fun <T : Presenter<V>, V : BaseContract.View> Presenter<V>.open(
    clazz: Class<T>,
    params: RouteUtil.RouteParam? = null,
    finishSelf: Boolean = false
) {
    RouteUtil.open(getContext(), clazz, params, finishSelf)
}

/**
 * Activity扩展函数
 */
fun <T : Activity> Activity.openResult(
    clazz: Class<T>,
    params: RouteUtil.RouteParam? = null,
    requestCode: Int = REQUESTCODE,
    listener: RouteUtil.OnActivityResultListener
) {
    RouteUtil.openResult(this, clazz, params, requestCode, listener)
}

/**
 * Activity扩展函数
 */
fun <T : Fragment> Fragment.openResult(
    clazz: Class<T>,
    params: RouteUtil.RouteParam? = null,
    requestCode: Int = REQUESTCODE,
    listener: RouteUtil.OnActivityResultListener
) {
    RouteUtil.openResult(activity, clazz, params, requestCode, listener)
}