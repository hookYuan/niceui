package yuan.niceui.login.presenter

import yuan.niceui.login.LoginActivity
import yuan.core.mvp.Presenter
import yuan.core.tool.RouteUtil
import yuan.niceui.app.tool.MAIN_MAINACTIVITY

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
        val loginSuccessActivityClazz = Class.forName(MAIN_MAINACTIVITY)
        RouteUtil.open(context, loginSuccessActivityClazz, true)
    }
}