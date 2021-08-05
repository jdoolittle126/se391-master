package edu.neit.jonathandoolittle.w3_inclass_activity_1

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickViewAllContacts(view: View) {
        val intent =  Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("content://contacts/people/")
        }
        startActivity(intent)
    }

    fun onClickOpenCamera(view: View) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivity(intent)
    }

    fun onClickGoToNewEnglandTech(view: View) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("geo:0,0?q=New+England+Institute+of+Technology")
        }
        startActivity(intent)
    }


}