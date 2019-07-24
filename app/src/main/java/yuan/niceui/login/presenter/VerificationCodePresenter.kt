package yuan.niceui.login.presenter

import android.app.Activity
import android.content.Intent
import yuan.niceui.main.MainActivity
import yuan.niceui.login.SetLoginPasswordActivity
import yuan.niceui.login.VerificationCodeActivity
import yuan.core.mvp.BaseContract
import yuan.core.mvp.Presenter
import yuan.core.tool.RouteUtil
import yuan.niceui.app.tool.MAIN_MAINACTIVITY

/**
 * 描述：
 *
 * @author yuanye
 * @date 2019/7/19 14:37
 */
class VerificationCodePresenter : Presenter<BaseContract.View>() {

    /**
     * 校验验证码
     *
     * @param verificationCode 验证码
     * @param codeType 验证码类型
     */
    fun checkVerificationCode(verificationCode: String, codeType: String) {
        if (verificationCode.length == 4) {
            when (codeType) {
                "1001" -> {
                    val loginSuccessActivityClazz = Class.forName(MAIN_MAINACTIVITY)
                    RouteUtil.open(context, loginSuccessActivityClazz)
                    run {
                        //结束当前Activity和注册页面
                        VerificationCodeActivity::class.java.cast(context).setResult(Activity.RESULT_OK)
                        VerificationCodeActivity::class.java.cast(context).finish()
                    }
                }
                "1002" -> {
                    SetLoginPasswordActivity.start(context,
                        listener = RouteUtil.OnActivityResultListener { requestCode: Int, resultCode: Int, _: Intent? ->
                            if (resultCode == Activity.RESULT_OK && requestCode == RouteUtil.REQUESTCODE)
                            /*这里的run为作用域函数，非线程*/
                                run {
                                    //结束当前Activity
                                    VerificationCodeActivity::class.java.cast(context).setResult(Activity.RESULT_OK)
                                    VerificationCodeActivity::class.java.cast(context).finish()
                                }
                        })
                }
            }
        }
    }
}
