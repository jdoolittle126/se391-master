package com.android.neit.basicbankaccountprogram

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
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

        // When you click a list item, it loads the account
        lsResults?.let {
            it.onItemClickListener =
                OnItemClickListener { _, _, pos, _ ->
                   setBankAccount(it.adapter.getItem(pos) as BankAccount)
                }
        }
    }

    /**
     * Displays the number of accounts stored in
     * the local database
     *
     * @param view The caller of this action
     */
    fun GetCountOfAccounts(view: View?) {
        val myNewDb = AppDatabase.getAppDatabase(getApplicationContext())
        val NumberOfAccounts: Int
        NumberOfAccounts = myNewDb?.BankAccountDao()?.getAccountCount()!!
        Results?.setText("Number Of Accounts = " + NumberOfAccounts.toString())
    }

    /**
     * Populates the list with all created
     * accounts
     *
     * @param view The caller of this action
     */
    fun getAllAccounts(view: View) {
        val myNewDb = AppDatabase.getAppDatabase(applicationContext)
        val listOfAccounts = myNewDb?.BankAccountDao()?.getAllAccounts()
        setAdapterList(listOfAccounts)
    }

    /**
     * Loads the given list into the adapter, if is
     * is well formed
     *
     * @param accounts The list to display
     */
    private fun setAdapterList(accounts: List<BankAccount>?) {
        lsResults?.let{
            if(accounts != null){
                it.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, accounts)
            }
        }
    }

    /**
     * Populates the adapter with all of the accounts
     * associated with the name in the name text field
     *
     * @param view The caller of this action
     */
    fun getAccountByName(view: View) {
        val myNewDb = AppDatabase.getAppDatabase(applicationContext)
        var listOfAccounts = myNewDb?.BankAccountDao()?.getAccountByName(Name?.text.toString())
        setAdapterList(listOfAccounts)
    }

    /**
     * Adds a bank account, based on the data in the
     * name and type text field
     *
     * @param view The caller of this action
     */
    fun addBankAccount(view: View) {
        val myNewDb = AppDatabase.getAppDatabase(applicationContext)
        val account = getAccount()
        account?.let {
            account._id = 0
            myNewDb?.BankAccountDao()?.addBankAccount(it)
        }
    }

    /**
     * Updates the account loaded into the
     * form by ID, if it exists
     *
     * @param view The caller of this action
     */
    fun updateBankAccount(view: View) {
        val myNewDb = AppDatabase.getAppDatabase(applicationContext)
        val account = getAccount()
        account?.let {
            myNewDb?.BankAccountDao()?.updateBankAccount(it)
        }
    }

    /**
     * Gets the account associated with the loaded
     * ID
     *
     * @return The account
     */
    private fun getAccount(): BankAccount? {
        val id = ID?.text.toString().toIntOrNull()
        return BankAccount(id ?: 0, Name?.text.toString(), Type?.text.toString())
    }

    /**
     * Takes a bank account, and loads it into the data entry form
     * for easy updating
     *
     * @param bankAccount The account
     */
    private fun setBankAccount(bankAccount: BankAccount) {
        ID?.setText(bankAccount._id.toString())
        Name?.setText(bankAccount._name)
        Type?.setText(bankAccount._type)
    }

}