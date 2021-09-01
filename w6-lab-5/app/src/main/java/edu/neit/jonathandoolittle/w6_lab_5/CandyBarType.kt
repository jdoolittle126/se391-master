package edu.neit.jonathandoolittle.w6_lab_5

import edu.neit.jonathandoolittle.CandyLab

/**
 *
 * A list of the possible types of chocolate that can be ordered. Adding a
 * new item here will automatically add it to the order form.
 *
 * @author Jonathan Doolittle
 * @version 0.1 - 7/27/2021
 *
 * @property candyBarNameId The string resource ID for the name of this candy bar, as defined in strings.xml
 */
enum class CandyBarType(private val candyBarNameId: Int) {

    MilkChocolate(R.string.milk_chocolate),
    WhiteChocolate(R.string.white_chocolate),
    DarkChocolate(R.string.dark_chocolate),
    MilkChocolateWithAlmonds(R.string.milk_chocolate_w_almonds);

    override fun toString(): String {
        // Non-null assert
        return CandyLab.appContext!!.getString(candyBarNameId)
    }
}