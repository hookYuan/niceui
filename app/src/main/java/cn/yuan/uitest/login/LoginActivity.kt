package cn.yuan.uitest.login

import android.text.Editable
import android.text.InputType
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.ImageView
import cn.yuan.uitest.R
import cn.yuan.uitest.login.presenter.LoginPresenter
import cn.yuan.uitest.tool.open
import kotlinx.android.synthetic.main.activity_login.*
import yuan.core.mvp.BaseActivity
import yuan.core.title.StatusUtil

/**
 * 描述：登录Activity
 *
 * @author yuanye
 * @date 2019/7/16 15:28
 */
class LoginActivity : BaseActivity<LoginPresenter>() {

    override fun initData() {
        //状态栏
        StatusUtil.setStatusBarColor(mContext, getColor2(android.R.color.white))
        StatusUtil.darkMode(mContext, true)
    }

    override fun setListener() {
        setTextChange(edit_username, image_clean_username)
        setTextChange(edit_password, image_clean_password)
        //设置密码可以见与隐藏
        image_show_eyes.setOnCheckedChangeListener { _: CompoundButton, select: Boolean ->
            if (select) {
                edit_password.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                /*kotlin中  or相当于Java中的|   and相当于java中的&*/
                edit_password.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD or InputType.TYPE_CLASS_TEXT
            }
        }

        //登录
        btn_login.setOnClickListener {
            presenter.login(edit_username.text.toString(), edit_password.text.toString())
        }

        //注册
        titleBar.setRightOnClickListener {
            open(RegisterActivity::class.java, null, true)
        }

        //忘记密码
        text_forget_password.setOnClickListener {
            open(ForgetPasswordActivity::class.java,null, false)
        }

        //免密登录
        text_no_password.setOnClickListener {
            open(ForgetPasswordActivity::class.java,null, true)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
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
                btn_login.isEnabled =
                    !TextUtils.isEmpty(edit_username.text.toString()) && !TextUtils.isEmpty(edit_password.text.toString())
            }
        })

        image.setOnClickListener {
            edit.setText("")
        }
    }
}
