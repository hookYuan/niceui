package cn.yuan.uitest.login

import cn.yuan.uitest.R
import cn.yuan.uitest.login.presenter.VerificationCodePresenter
import com.tuo.customview.VerificationCodeView
import kotlinx.android.synthetic.main.activity_verification_code.*
import yuan.core.mvp.BaseActivity
import yuan.core.title.StatusUtil
import yuan.core.tool.Kits

/**
 * 描述：验证码
 *
 * @author yuanye
 * @date 2019/7/19 14:04
 */
class VerificationCodeActivity : BaseActivity<VerificationCodePresenter>() {

    /**
     * 获取验证码的手机号
     */
    private var mPhone: String? = null

    override fun initData() {
        //状态栏
        StatusUtil.setStatusBarColor(mContext, getColor2(android.R.color.white))
        StatusUtil.darkMode(mContext, true)
        //设置手机号
        mPhone = intent.getStringExtra("phone")
        text_verification_subject.text = String.format(
                getString(R.string.login_verification_code_subject),
                Kits.Real.formatePhone(mPhone))
    }

    override fun setListener() {
        verification_code.setInputCompleteListener(
                object : VerificationCodeView.InputCompleteListener {
                    override fun deleteContent() {
                        //删除成功
                    }

                    override fun inputComplete() {
                        //输入完成
                        presenter.checkVerificationCode(verification_code.inputContent)
                    }
                })

//        text_retrieve_code.set
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_verification_code
    }

}
