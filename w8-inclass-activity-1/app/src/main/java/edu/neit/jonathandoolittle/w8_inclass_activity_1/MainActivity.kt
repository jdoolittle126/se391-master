package edu.neit.jonathandoolittle.w8_inclass_activity_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import edu.neit.jonathandoolittle.w8_inclass_activity_1.ui.theme.W8inclassactivity1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Greeting("NEIT Student")
//            W8inclassactivity1Theme {
//                // A surface container using the 'background' color from the theme
//                Surface(color = MaterialTheme.colors.background) {
//                    Greeting("Android")
//                }
//            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Column() {
        Text(text = "Hello $name!")
        Text(text = "Welcome to Android")
        Text(text = "I hope you have fun!")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    W8inclassactivity1Theme {
        Greeting("NEIT Student")
    }
}