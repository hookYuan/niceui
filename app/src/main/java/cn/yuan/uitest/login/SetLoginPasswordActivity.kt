/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.yuan.uitest.login

import android.content.Context
import android.text.Editable
import android.text.InputType
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.SwitchCompat
import cn.yuan.uitest.R
import cn.yuan.uitest.login.presenter.SetLoginPasswordPresenter
import kotlinx.android.synthetic.main.activity_set_login_password.*
import yuan.core.mvp.BaseActivity
import yuan.core.title.StatusUtil
import yuan.core.tool.RouteUtil

/**
 * 描述：设置登录密码
 *
 * @author yuanye
 * @date 2019/7/22 11:14
 */
class SetLoginPasswordActivity : BaseActivity<SetLoginPasswordPresenter>() {

    companion object {
        /**
         * 启动设置登录密码
         *
         * @param listener Activity 回调函数
         */
        fun start(
                context: Context,
                listener: RouteUtil.OnActivityResultListener
        ) {
            RouteUtil.openResult(
                    context,
                    SetLoginPasswordActivity::class.java,
                    listener
            )
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_set_login_password
    }

    override fun initData() {
        //状态栏
        StatusUtil.setStatusBarColor(mContext, getColor2(android.R.color.white))
        StatusUtil.darkMode(mContext, true)
    }

    override fun setListener() {
        setTextChange(edit_password, image_clean_password, image_show_eyes)
        setTextChange(edit_confirm_password, image_clean_confirm_password, image_confirm_show_eyes)
        btn_confirm_password.setOnClickListener {
            //比较两次输入是否相等
            if (edit_password.text.toString() != edit_confirm_password.text.toString()) {
                showToast(getString(R.string.login_inconsistent_passwords_hint))
            } else {
                presenter.setLoginPassword(edit_password.text.toString())
            }
        }
    }

    /**
     * EditText 删除监听
     *
     * @param edit 输入框
     * @param image 删除按钮
     * @param showImage 显示隐藏
     */
    private fun setTextChange(edit: EditText, image: ImageView, showImage: SwitchCompat) {
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
                btn_confirm_password.isEnabled =
                        !TextUtils.isEmpty(edit_password.text.toString())
                                && !TextUtils.isEmpty(edit_confirm_password.text.toString())
            }
        })

        image.setOnClickListener {
            edit.setText("")
        }

        //设置密码可以见与隐藏
        showImage.setOnCheckedChangeListener { _: CompoundButton, select: Boolean ->
            if (select) {
                edit.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                /*kotlin中  or相当于Java中的|   and相当于java中的&*/
                edit.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD or InputType.TYPE_CLASS_TEXT
            }
        }
    }
}
