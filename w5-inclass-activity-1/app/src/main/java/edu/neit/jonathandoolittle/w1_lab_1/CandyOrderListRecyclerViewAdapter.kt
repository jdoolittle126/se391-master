package edu.neit.jonathandoolittle.w1_lab_1

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import edu.neit.jonathandoolittle.w1_lab_1.databinding.FragmentCandyOrderBinding

/**
 * A [RecyclerView.Adapter] that can display a [CandyOrder]. Displays the data as
 *
 *     "First Name" "Last Name" "Number of Bars = X"
 *
 */
class CandyOrderListRecyclerViewAdapter(
    private val values: ArrayList<CandyOrder>,
    listener: CandyOrderFragment.CandyListInteractionListener?
    ) : RecyclerView.Adapter<CandyOrderListRecyclerViewAdapter.ViewHolder>() {

    private var adapterListener: CandyOrderFragment.CandyListInteractionListener? = listener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentCandyOrderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text =  values[position].customerFirstName + " " + values[position].customerLastName
        holder.contentView.text = "Number Of Bars = " + Integer.toString(values[position].candyBarQuantity)
        holder.itemView?.setOnClickListener(View.OnClickListener {
            adapterListener?.onListItemClicked(item)
        })
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentCandyOrderBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content
        var mItem: CandyOrder? = null

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}