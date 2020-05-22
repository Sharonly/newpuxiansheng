package com.puxiansheng.www.wxapi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.puxiansheng.www.ui.main.MainActivity
import com.puxiansheng.www.login.WechatAPI
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.modelmsg.SendAuth
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler

@Suppress("EXPERIMENTAL_API_USAGE")
class WXEntryActivity : Activity(), IWXAPIEventHandler {
    private var api: IWXAPI? = null
    private val RETURN_MSG_TYPE_LOGIN = 1 //login
    private val RETURN_MSG_TYPE_SHARE = 2 //share

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WechatAPI.instance?.handleIntent(intent, this)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        WechatAPI.instance?.handleIntent(intent, this)
    }

    override fun onReq(baseReq: BaseReq) {

    }

    override fun onResp(baseResp: BaseResp) {
        val intent = Intent(this, MainActivity::class.java)

        when (baseResp.errCode) {
            BaseResp.ErrCode.ERR_OK -> if (baseResp.type == RETURN_MSG_TYPE_LOGIN) {
                val code = (baseResp as SendAuth.Resp).code
                intent.putExtra("authCode", code.toString())
                //sendBroadcast(intent)
            }
            BaseResp.ErrCode.ERR_AUTH_DENIED, BaseResp.ErrCode.ERR_USER_CANCEL -> {
            }
            else -> {
            }
        }
        startActivity(intent)
        finish()
    }
}