package cn.yuan.uitest.login.presenter

import android.app.Activity
import android.content.Intent
import cn.yuan.uitest.login.RegisterActivity
import cn.yuan.uitest.login.VerificationCodeActivity
import yuan.core.mvp.Presenter
import yuan.core.tool.RouteUtil

/**
 * 描述：注册
 *
 * @author yuanye
 * @date 2019/7/19 14:07
 */
class RegisterPresenter : Presenter<RegisterActivity>() {

    /**
     * 联网获取验证
     */
    fun getRegisterVerificationCode(phone: String) {
        RouteUtil.openResult(
                context,
                VerificationCodeActivity::class.java,
                RouteUtil.buildParam().put("phone", phone),
                RouteUtil.REQUESTCODE
                /*Kotlin中回调函数可以使用{}标识，参数放到->左边，表达式放右边*/
        ) { requestCode: Int, resultCode: Int, _: Intent? ->
            if (resultCode == Activity.RESULT_OK && requestCode == RouteUtil.REQUESTCODE)
            /*这里的run为作用域函数，非线程*/
                run {
                    //结束当前Activity
                    RegisterActivity::class.java.cast(context).finish()
                }
        }
    }
}
















