package com.android.neit.basicbankaccountprogram

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var ID: EditText? = null
    var Name: EditText? = null
    var Type: EditText? = null
    var AddAccount: Button? = null
    var UpdateAccount: Button? = null
    var GetAllAccounts: Button? = null
    var GetAccountByName: Button? = null
    var GetCountOfAccounts: Button? = null
    var Results: TextView? = null
    var lsResults: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ID = findViewById<View?>(R.id.txtID) as EditText?
        Name = findViewById<View?>(R.id.txtName) as EditText?
        Type = findViewById<View?>(R.id.txtType) as EditText?
        AddAccount = findViewById<View?>(R.id.btnAdd) as Button?
        UpdateAccount = findViewById<View?>(R.id.btnUpdate) as Button?
        GetAllAccounts = findViewById<View?>(R.id.btnGetAllAccounts) as Button?
        GetAccountByName = findViewById<View?>(R.id.btnGetAccountsByName) as Button?
        GetCountOfAccounts = findViewById<View?>(R.id.btnGetCountOfAccounts) as Button?
        Results = findViewById<View?>(R.id.tvResults) as TextView?
        lsResults = findViewById<View?>(R.id.lsResults) as ListView?
    }

    fun GetCountOfAccounts(view: View?) {
        val myNewDb = AppDatabase.getAppDatabase(getApplicationContext())
        val NumberOfAccounts: Int
        NumberOfAccounts = myNewDb?.BankAccountDao()?.getAccountCount()!!
        Results?.setText("Number Of Accounts = " + NumberOfAccounts.toString())
    }

    fun getAllAccounts(view: View) {
        val myNewDb = AppDatabase.getAppDatabase(applicationContext)
        val listOfAccounts = myNewDb?.BankAccountDao()?.getAllAccounts()

        lsResults?.let{
            if(listOfAccounts != null){
                it.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listOfAccounts)
            }
        }

    }

    fun getAccountByName(view: View) {
        try {
            val myNewDb = AppDatabase.getAppDatabase(applicationContext)
            var search = myNewDb?.BankAccountDao()?.getAccountByName(Name?.text.toString())

            lsResults?.let{
                if(search != null){
                    it.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, search)
                }
            }


        } catch(e: Exception) {
            Log.d("TEst", e.toString())
        }
    }

    fun addBankAccount(view: View) {
        val myNewDb = AppDatabase.getAppDatabase(applicationContext)
        val account = getAccount()
        account?.let {
            account._id = 0
            myNewDb?.BankAccountDao()?.addBankAccount(it)
        }
    }

    fun updateBankAccount(view: View) {
        try {
            val myNewDb = AppDatabase.getAppDatabase(applicationContext)
            val account = getAccount()
            account?.let {
                myNewDb?.BankAccountDao()?.updateBankAccount(it)
            }

        } catch(e: Exception) {

        }
    }

    private fun getAccount(): BankAccount? {
        val id = ID?.text.toString().toIntOrNull()
        return BankAccount(id ?: 0, Name?.text.toString(), Type?.text.toString())
    }

    private fun setBankAccount(bankAccount: BankAccount) {
        ID?.setText(bankAccount._id.toString())
        Name?.setText(bankAccount._name)
        Type?.setText(bankAccount._type)
    }



}