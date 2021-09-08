package edu.neit.jonathandoolittle.w7_group_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.TextView
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var mHandler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mHandler = Handler(Looper.getMainLooper())
    }

    fun readJSONFeed(URL: String): String {
        val stringBuilder = StringBuilder()
        val c: HttpURLConnection
        try {
            val u = URL(URL)
            c = u.openConnection() as HttpURLConnection
            c.requestMethod = "GET"
            c.setRequestProperty("Content-length", "0")
            c.useCaches = false
            c.allowUserInteraction = false
            c.connectTimeout = TIMEOUT
            c.readTimeout = TIMEOUT
            c.connect()
            val statusCode = c.responseCode
            if (statusCode == 200) {
                val reader = BufferedReader(InputStreamReader(c.inputStream))
                var line: String = ""
                while ({ line = reader.readLine(); line }() != null) {
                    stringBuilder.append(line)
                }
                reader.close()
            } else {
                Log.d("JSON", "Failed to download file")
            }
        } catch (e: Exception) {
            Log.d("readJSONFeed", e.localizedMessage)
        }

        return stringBuilder.toString()
    }

    fun getJSONData(view: View) {
        Thread(Task()).start()

    }

    private inner class Task : Runnable {
        var City = findViewById<TextView>(R.id.txtCity)
        var Latitude = findViewById<TextView>(R.id.txtLatitude)
        var Longitude = findViewById<TextView>(R.id.txtLongitude)
        override fun run() {
            var url = "http://www.geoplugin.net/json.gp?ip=64.17.241.5"
            val result : String = readJSONFeed(url)
            mHandler.post {
                var jsonResponse : JSONObject = JSONObject(result)
                City.text = jsonResponse.getString("geoplugin_city")
                Latitude.text = jsonResponse.getString("geoplugin_latitude")
                Longitude.text = jsonResponse.getString("geoplugin_longitude")
            }
        }

    }

    companion object {
        const val TIMEOUT = 5000;
    }


}