package pimsupa.test.kotlintest

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text_show.text = "before"



        /**id คือ button_welcome เซ็ตค่าเมื่อคลิกปุ่มให้โชว์ toast**/
        button_welcome.setOnClickListener {
            Toast.makeText(this, "welcome", Toast.LENGTH_SHORT).show()
        }

        /**เมื่อคลิกปุ่มนี้ ให้เอาค่าใน edittext โชว์ใน textview**/
        button_assign.setOnClickListener {
            //เรียก .toString() เพราะต้องการ String จาก edittext ถ้าใช้ edittext_assign.text เฉยๆ จะได้ค่าเป็น view มาแทน
            val textFromEdittext = edittext_assign.text.toString()
            text_show.text = textFromEdittext
        }


        /**set ให้เมื่อedittext มี5 ตัวอักษร ให้โชว์ error**/
        edittext_assign.doAfterTextChanged {
            checkText(edittext_assign.text.toString())
        }

    }

    private fun checkText(str:String){
        if (str.length == 5) {
            edittext_assign.error = "5 Character!"
        } else {
            edittext_assign.error = null
        }
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "resumeeee", Toast.LENGTH_SHORT).show()
    }

}