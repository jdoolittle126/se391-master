package edu.neit.jonathandoolittle.w1_lab_1

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * A [Fragment] that holds a list of [CandyOrder]s
 * Use the [CandyOrderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CandyOrderFragment : Fragment() {

    private var columnCount = 1
    private lateinit var fragmentView : RecyclerView
    private var fragmentListener: CandyListInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_candy_order_list, container, false)

        // Set the adapter & view
        if (view is RecyclerView) {
            fragmentView = view
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = CandyOrderListRecyclerViewAdapter( MainActivity.candyOrdersCompanion, fragmentListener)
            }
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is CandyListInteractionListener) {
            fragmentListener = context
        } else {
            throw RuntimeException("$context must implement CandyListInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        fragmentListener = null
    }

    /**
     * Notifies the adapter that the list has changes.
     * This function forces a full refresh, which is technically
     * not best practice, but for the scope of this project
     * it is practical to do so.
     */
    fun onOrderAdded() {
        fragmentView.adapter?.notifyDataSetChanged()
    }

    /**
     * Provides a listener for when [CandyOrder]s are clicked from the list
     */
    interface CandyListInteractionListener {
        /**
         * Triggered when a [CandyOrder] is clicked on in the list.
         *
         * @param candyOrder The order that was clicked
         */
        fun onListItemClicked(candyOrder: CandyOrder)
    }

    companion object {
        /**
         * Factory create CandyOrderFragment
         *
         * @return A new instance of fragment CandyOrderFragment.
         */
        @JvmStatic
        fun newInstance() = CandyOrderFragment().apply {}
    }
}