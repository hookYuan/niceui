package cn.yuan.uitest.login.presenter

import cn.yuan.uitest.MainActivity
import cn.yuan.uitest.login.LoginActivity
import yuan.core.mvp.Presenter
import yuan.core.tool.RouteUtil

/**
 * 描述：
 *
 * @author yuanye
 * @date 2019/7/16 15:30
 */
class LoginPresenter : Presenter<LoginActivity>() {

    /**
     * 登录
     */
    fun login(username: String, password: String) {
        RouteUtil.open(context, MainActivity::class.java, true)
    }
}