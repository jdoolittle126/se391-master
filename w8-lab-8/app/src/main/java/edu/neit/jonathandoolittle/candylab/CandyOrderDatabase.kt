package edu.neit.jonathandoolittle.candylab

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities=[CandyOrder::class] ,version=1)
abstract class CandyOrderDatabase: RoomDatabase() {
    abstract fun candyOrderDao(): CandyOrderDao

    companion object {
        var INSTANCE: CandyOrderDatabase? = null

        fun getDatabase(context: Context): CandyOrderDatabase? {
            if (INSTANCE == null){
                synchronized(CandyOrderDatabase::class){
                    if (INSTANCE == null){
                        INSTANCE = Room.databaseBuilder(context.applicationContext, CandyOrderDatabase::class.java, "CandyOrderDatabase")
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}