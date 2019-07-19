package cn.yuan.uitest.login.presenter

import android.app.Activity
import cn.yuan.uitest.MainActivity
import cn.yuan.uitest.login.RegisterActivity
import cn.yuan.uitest.login.VerificationCodeActivity
import yuan.core.mvp.Presenter
import yuan.core.tool.RouteUtil

/**
 * 描述：
 *
 * @author yuanye
 * @date 2019/7/19 14:37
 */
class VerificationCodePresenter : Presenter<VerificationCodeActivity>() {

    /**
     * 校验验证码
     */
    fun checkVerificationCode(verificationCode: String) {
        if (verificationCode.length == 4) {
            RouteUtil.open(context, MainActivity::class.java)
            run {
                //结束当前Activity和注册页面
                VerificationCodeActivity::class.java.cast(context).finish()
                VerificationCodeActivity::class.java.cast(context).setResult(Activity.RESULT_OK)
            }
        }
    }
}
