package cn.yuan.uitest.login

import android.app.Activity
import android.content.Intent
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import cn.yuan.uitest.R
import cn.yuan.uitest.login.presenter.ForgetPasswordPresenter
import cn.yuan.uitest.tool.open
import kotlinx.android.synthetic.main.activity_forget_password.*
import yuan.core.mvp.BaseActivity
import yuan.core.title.StatusUtil
import yuan.core.tool.Kits
import yuan.core.tool.RouteUtil

/**
 * 描述：忘记密码
 *
 * @author yuanye
 * @date 2019/7/19 13:35
 */
class ForgetPasswordActivity : BaseActivity<ForgetPasswordPresenter>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_forget_password
    }

    override fun initData() {
        //状态栏
        StatusUtil.setStatusBarColor(mContext, getColor2(android.R.color.white))
        StatusUtil.darkMode(mContext, true)

        setTextChange(edit_phone, image_clean_phone)
    }

    override fun setListener() {
        btn_forget_password_next.setOnClickListener {
            VerificationCodeActivity.start(
                    mContext,
                    edit_phone.text.toString(),
                    codeType = "1002",
                    listener = RouteUtil.OnActivityResultListener {
                        /*Kotlin中回调函数可以使用{}标识，参数放到->左边，表达式放右边*/
                        requestCode: Int, resultCode: Int, _: Intent? ->
                        if (resultCode == Activity.RESULT_OK && requestCode == RouteUtil.REQUESTCODE)
                        //结束当前Activity
                            finish()
                    }
            )
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
                btn_forget_password_next.isEnabled = Kits.Real.isMobile(edit.text.toString())
            }
        })

        image.setOnClickListener {
            edit.setText("")
        }
    }
}

