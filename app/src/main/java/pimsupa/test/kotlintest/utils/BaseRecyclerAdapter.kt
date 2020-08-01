package pimsupa.sss.mrworkorder.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T>(protected val masterList: MutableList<T> = mutableListOf()) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = masterList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BaseViewHolder<T>).onBind(masterList[position], position)

    }

    abstract class BaseViewHolder<E>(val view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(data: E, position: Int) {
            bindtext(view, data, position)
            bindOnclick(view, data, position)
        }

        abstract fun bindOnclick(view: View, data: E, position: Int)
        abstract fun bindtext(view: View, data: E, position: Int)
    }

}