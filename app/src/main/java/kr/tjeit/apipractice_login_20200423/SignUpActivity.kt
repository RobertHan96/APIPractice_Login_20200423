package kr.tjeit.apipractice_login_20200423

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_up.*
import kr.tjeit.apipractice_login_20200423.utils.ServerUtil
import org.json.JSONObject

class SignUpActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        signUpRequestBtn.setOnClickListener {
            val inputId = signUpIdEdt.text.toString()
            val inputPw = signUpPwEdt.text.toString()
            val inputName = signUpNameEdt.text.toString()
            val inputPhone = signUpPhoneEdt.text.toString()

            ServerUtil.putRequestSignUp(mContext, inputId, inputPw, inputName, inputPhone, object : ServerUtil.JsonResponseHandler {

                override fun onResponse(json: JSONObject) {
                    Log.d("서버응답JSON", json.toString())
                    val code = json.getInt("code")
                    if (code == 200) {
//                        현재화면을 닫고 이전 화면으로 돌아가는 코드
                        runOnUiThread {
                            Toast.makeText(mContext, "회원 가입 성공!", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    } else {
                        runOnUiThread{
                            Toast.makeText(mContext, "계정 정보 중복 : 다른 아이디를 사용해주세요.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            })


        }
    }

    override fun setValues() {

    }

}
