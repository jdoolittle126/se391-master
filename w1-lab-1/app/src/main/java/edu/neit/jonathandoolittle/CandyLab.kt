package edu.neit.jonathandoolittle

import android.app.Application
import android.content.Context

/**
 * Allows access to application context statically. Code is based on a Kotlin interperation of
 * Rohit Ghatol and Peter Mortensen's answer from [https://stackoverflow.com/questions/2002288/static-way-to-get-context-in-android]
 * @author Jonathan Doolittle
 * @version 0.1 - 7/27/2021
 */
class CandyLab : Application() {

    companion object {
        var appContext: Context? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
}