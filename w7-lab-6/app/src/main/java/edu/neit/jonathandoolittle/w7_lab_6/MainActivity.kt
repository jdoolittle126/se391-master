package edu.neit.jonathandoolittle.w7_lab_6

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
import org.json.JSONArray




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

        var textViewDivision = findViewById<TextView>(R.id.textViewDivision)
        var textViewData = findViewById<TextView>(R.id.textViewPlayerInfo)

        override fun run() {
            var url = "http://52.152.227.167/jsonfootballservice/footballteams/GetFootballTeam"
            val result : String = readJSONFeed(url)
            mHandler.post {
                val jsonResponse = JSONObject(result)
                val jsonPlayers = jsonResponse.optJSONArray("roster")
                var myString = ""

                for(i in 0 until jsonPlayers.length()) {
                    val player = jsonPlayers[i] as JSONObject
                    myString += "NAME: ${player.get("name")} WEIGHT: ${player.get("weight")} POSITION: ${player.get("position")}\n"
                }


                textViewDivision.text = jsonResponse.getString("Division")
                textViewData.text = myString
            }
        }

    }

    companion object {
        const val TIMEOUT = 5000;
    }

}