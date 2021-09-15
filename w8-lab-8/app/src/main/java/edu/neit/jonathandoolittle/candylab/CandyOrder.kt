package edu.neit.jonathandoolittle.candylab

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 *
 * Data class for a candy order. Each order is for a single type of candy bar,
 * and for a single customer. Each order has two shipping options, expedited or
 * standard, and has a quantity of 1 or more
 *
 * @author Jonathan Doolittle
 * @version 0.1 - 7/27/2021
 *
 * @constructor Creates a new [CandyOrder]
 * @property id This entity's unique identifier
 * @property customerFirstName The customer's first name
 * @property customerLastName The customer's last name
 * @property candyBarType The type of candy bar, represented as a [CandyBarType]
 * @property candyBarQuantity The number of candy bars being ordered
 * @property expeditedShipping True if the order's shipping is expedited
 */
@Parcelize
@Entity
data class CandyOrder(@PrimaryKey(autoGenerate = true) val id: Int = 0,
                      val customerFirstName: String,
                      val customerLastName: String,
                      val candyBarType: CandyBarType,
                      val candyBarQuantity: Int,
                      val totalPrice: Float = 0.0f,
                      val expeditedShipping: Boolean) : Parcelable {}
