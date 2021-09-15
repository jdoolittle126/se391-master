package edu.neit.jonathandoolittle.candylab

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

/**
 *
 * Class Description - TODO
 *
 * Class Logic - TODO
 *
 * <pre>
 *  Class Usage - TODO
 * </pre>
 *
 * @author Jonathan Doolittle
 * @version 0.1 - 9/15/2021
 *
 */

@Dao
interface CandyOrderDao {

    @Insert(onConflict = REPLACE)
    fun insert(vararg orders: CandyOrder)

    @Delete
    fun delete(vararg orders: CandyOrder)

    @Query("SELECT * FROM CandyOrder")
    fun getAll(): List<CandyOrder>

    @Query("SELECT COUNT(*) FROM CandyOrder")
    fun getCount(): Int

    @Query("SELECT * FROM CandyOrder WHERE id = :id")
    fun getById(id: Int): CandyOrder

    @Query("SELECT * FROM CandyOrder WHERE totalPrice > :price")
    fun getWherePriceGreatThan(price: Float): List<CandyOrder>

}