package com.android.neit.basicbankaccountprogram

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "BankAccount")
class BankAccount {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var _id = 0

    @ColumnInfo(name = "name")
    var _name: String? = null

    @ColumnInfo(name = "type")
    var _type: String? = null

    // constructor
    constructor(id: Int, name: String, type: String) {
        this._id = id
        this._name = name
        this._type = type
    }

    override fun toString(): String {
        return "ID: $_id Name: $_name Type: $_type"
    }

  
}