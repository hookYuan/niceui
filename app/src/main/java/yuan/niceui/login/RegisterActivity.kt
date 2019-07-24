package yuan.niceui.login

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import yuan.niceui.R
import yuan.niceui.login.presenter.RegisterPresenter
import yuan.niceui.app.tool.open
import kotlinx.android.synthetic.main.activity_register.*
import yuan.core.mvp.BaseActivity
import yuan.core.title.StatusUtil
import yuan.core.tool.Kits

/**
 * 描述：登录/注册
 *
 * @author yuanye
 * @date 2019/7/19 13:33
 */
class RegisterActivity : BaseActivity<RegisterPresenter>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun initData() {
        //状态栏
        StatusUtil.setStatusBarColor(mContext, getColor2(android.R.color.white))
        StatusUtil.darkMode(mContext, true)
    }

    override fun setListener() {

        setTextChange(edit_phone, image_clean_phone)

        //验证码获取成功
        btn_verification_code.setOnClickListener {
            presenter.getRegisterVerificationCode(edit_phone.text.toString())
        }

        //密码登录
        text_login_password.setOnClickListener {
            open(LoginActivity::class.java, null, true)
        }
    }

    /**
     * EditText 删除监听
     */
    private fun setTextChange(edit: EditText, image: ImageView) {
        edit.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
                //输入后的监听
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //输入后的监听
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //输入文字产生变化的监听
                if (!TextUtils.isEmpty(edit.text.toString())) {
                    image.visibility = View.VISIBLE
                } else {
                    image.visibility = View.GONE
                }
                //校验手机号
                btn_verification_code.isEnabled = Kits.Real.isMobile(edit.text.toString())
            }
        })

        image.setOnClickListener {
            edit.setText("")
        }
    }
}
