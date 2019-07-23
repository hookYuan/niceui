package cn.yuan.uitest.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Handler
import cn.yuan.uitest.R
import cn.yuan.uitest.login.presenter.VerificationCodePresenter
import com.tuo.customview.VerificationCodeView
import kotlinx.android.synthetic.main.activity_verification_code.*
import yuan.core.mvp.BaseActivity
import yuan.core.title.StatusUtil
import yuan.core.tool.Kits
import yuan.core.tool.RouteUtil

/**
 * 描述：验证码
 *
 * @author yuanye
 * @date 2019/7/19 14:04
 */
class VerificationCodeActivity : BaseActivity<VerificationCodePresenter>() {

    companion object {
        /**
         * 验证码类型
         * 1001： 手机号注册验证码
         * 1002： 忘记密码验证码
         */
        val CODE_TYPE: String = "VERIFICATION_CODE_TYPE"
        /**
         * 获取验证码的手机
         */
        var CODE_PHONE: String = "VERIFICATION_CODE_PHONE"

        /**
         * 启动验证码方法
         *
         * @param phone 手机号
         * @param codeType 验证码类型
         * @param listener Activity 回调函数
         */
        fun start(
            context: Context, phone: String, codeType: String,
            listener: RouteUtil.OnActivityResultListener
        ) {
            RouteUtil.openResult(
                context,
                VerificationCodeActivity::class.java,
                RouteUtil.buildParam()
                    .put(CODE_TYPE, codeType)
                    .put(CODE_PHONE, phone), listener
            )
        }
    }

    /**
     * 获取验证码的手机号
     */
    private var mPhone: String? = null
    /**
     * 定时器线程
     */
    private var runnalble: Runnable? = null
    /**
     * 定时器Handler
     */
    private var mStartVideoHandler: Handler = Handler()
    /**
     * 验证码类型
     */
    private var mCodeType: String = "1001"

    override fun initData() {
        //状态栏
        StatusUtil.setStatusBarColor(mContext, getColor2(android.R.color.white))
        StatusUtil.darkMode(mContext, true)

        //取值
        mCodeType = intent.getStringExtra(CODE_TYPE)
        mPhone = intent.getStringExtra(CODE_PHONE)

        //设置手机号
        text_verification_subject.text = String.format(
            getString(R.string.login_verification_code_subject),
            Kits.Real.formatePhone(mPhone)
        )
    }

    override fun setListener() {
        verification_code.setInputCompleteListener(
            object : VerificationCodeView.InputCompleteListener {
                override fun deleteContent() {
                    //删除成功
                }

                override fun inputComplete() {
                    //输入完成
                    presenter.checkVerificationCode(verification_code.inputContent, mCodeType)
                }
            })

        timer()

        text_retrieve_code.setOnClickListener {
            timer()
        }

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_verification_code
    }

    /**
     * 秒倒计时
     */
    private var mSecond: Int = 120

    /**
     * 开启新线程进行倒计时
     */
    private fun timer() {
        mSecond = 120
        if (runnalble == null) {
            runnalble = Runnable {
                mSecond--
                runOnUiThread {
                    text_retrieve_code.text = String.format(
                        getString(R.string.login_verification_retrieve_code), mSecond.toString()
                    )
                }
                if (mSecond > 0) {
                    text_retrieve_code.setTextColor(getColor2(R.color.colorFont99))
                    text_retrieve_code.isClickable = false
                    mStartVideoHandler.postDelayed(runnalble, 1000)
                } else {
                    text_retrieve_code.setTextColor(getColor2(R.color.colorPrimary))
                    text_retrieve_code.isClickable = true
                }
            }
        }
        Thread(runnalble).start()
    }

}
