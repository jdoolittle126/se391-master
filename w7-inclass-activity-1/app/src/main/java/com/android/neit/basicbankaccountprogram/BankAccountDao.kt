package com.android.neit.basicbankaccountprogram

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE

@Dao
interface BankAccountDao {
    @Query("SELECT count(*) FROM BankAccount")
    fun getAccountCount(): Int

    @Query("SELECT * FROM BankAccount")
    fun getAllAccounts(): List<BankAccount>

    @Query("SELECT * FROM BankAccount WHERE name LIKE :name")
    fun getAccountByName(name: String): List<BankAccount>

    @Insert(onConflict = IGNORE)
    fun addBankAccount(bankAccount: BankAccount)

    @Update
    fun updateBankAccount(bankAccount: BankAccount)

}