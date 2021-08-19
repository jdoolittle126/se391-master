package edu.neit.jonathandoolittle.w1_lab_1

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import edu.neit.jonathandoolittle.w1_lab_1.placeholder.PlaceholderContent.PlaceholderItem
import edu.neit.jonathandoolittle.w1_lab_1.databinding.FragmentCandyOrderBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class CandyOrderListRecyclerViewAdapter(
    private val values: ArrayList<CandyOrder>,
    mListener: CandyOrderFragment.OnListFragmentInteractionListener?
) : RecyclerView.Adapter<CandyOrderListRecyclerViewAdapter.ViewHolder>() {

    private var mListener: CandyOrderFragment.OnListFragmentInteractionListener? = mListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentOrdersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        //val mItem = values[position]
        holder.idView.text =  values[position].firstName + " " + values[position].lastName
        holder.contentView.text = "Number Of Bars = " + Integer.toString(values[position].numberOfBars)
        holder.itemView?.setOnClickListener(View.OnClickListener {
            mListener?.onListFragmentInteraction(item)
        })
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentOrdersBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content
        var mItem: Order? = null

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}