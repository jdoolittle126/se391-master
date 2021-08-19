package edu.neit.jonathandoolittle.w5_group_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import edu.neit.jonathandoolittle.w5_group_project.placeholder.PlaceholderContent

class MainActivity : AppCompatActivity(), BlankFragment.OnFragmentInteractionListener, ItemFragment.OnListFragmentInteractionListener  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun sendBlankFragmentValue(value: String) {
        Toast.makeText(this, "Value from the blank fragment is $value", Toast.LENGTH_LONG).show()
    }

    override fun ListFragmentClicked(item: PlaceholderContent.PlaceholderItem) {
        var myBlankFragment : BlankFragment = this.supportFragmentManager.findFragmentById(R.id.topFragment) as BlankFragment;
        myBlankFragment.LoadFragmentData(item.toString());

    }
}