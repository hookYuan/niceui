package yuan.niceui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import yuan.niceui.R
import yuan.niceui.main.adapter.MainAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var data = listOf<String>(
            "给初学者的RxJava2.0教程（七）: Flowable",
            "Android之View的诞生之谜",
            "Android之自定义View的死亡三部曲之Measure",
            "Using ThreadPoolExecutor in Android ",
            "Kotlin 泛型定义与 Java 类似，但有着更多特性支持。",
            "Android异步的姿势，你真的用对了吗？",
            "Android 高质量录音库。",
            "Android 边缘侧滑效果，支持多种场景下的侧滑退出。"
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MainAdapter(data)
    }
}
