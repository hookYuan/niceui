package cn.yuan.uitest.login.presenter

import android.app.Activity
import cn.yuan.uitest.login.SetLoginPasswordActivity
import cn.yuan.uitest.login.VerificationCodeActivity
import yuan.core.mvp.BaseContract
import yuan.core.mvp.Presenter

/**
 * 描述：设置登录密码
 *
 * @author yuanye
 * @date 2019/7/22 11:15
 */
class SetLoginPasswordPresenter : Presenter<BaseContract.View>() {

    /**
     * 设置登录密码
     */
    fun setLoginPassword(password: String) {
        //TODO 密码设置成功后
        run {
            //结束当前Activity和注册页面
            SetLoginPasswordActivity::class.java.cast(context).setResult(Activity.RESULT_OK)
            SetLoginPasswordActivity::class.java.cast(context).finish()
        }
    }
}
