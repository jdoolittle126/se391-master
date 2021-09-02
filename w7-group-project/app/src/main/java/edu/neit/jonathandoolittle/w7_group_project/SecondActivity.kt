package edu.neit.jonathandoolittle.w7_group_project

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
        var url = "http://www.geoplugin.net/json.gp?ip=64.17.241.5"
        LongOperation().execute(url)
    }

    companion object {
        const val TIMEOUT = 5000;
    }


    private inner class LongOperation : AsyncTask<String, Void, String>() {
        var City = findViewById<TextView>(R.id.txtCity)
        var Latitude = findViewById<TextView>(R.id.txtLatitude)
        var Longitude = findViewById<TextView>(R.id.txtLongitude)

        override fun doInBackground(vararg p0: String?): String {
            return readJSONFeed(p0[0].toString())
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            var jsonResponse : JSONObject = JSONObject(result)
            City.text = jsonResponse.getString("geoplugin_city")
            Latitude.text = jsonResponse.getString("geoplugin_latitude")
            Longitude.text = jsonResponse.getString("geoplugin_longitude")
        }

    }
}