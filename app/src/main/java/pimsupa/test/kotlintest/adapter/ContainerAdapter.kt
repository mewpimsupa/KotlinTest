package pimsupa.test.kotlintest.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.single_list.view.*
import pimsupa.sss.mrworkorder.utils.BaseRecyclerAdapter
import pimsupa.test.kotlintest.R
import pimsupa.test.kotlintest.model.ContainerModel


class ContainerAdapter(private val datalist: MutableList<ContainerModel>,val callback: (String) -> Unit) :
        BaseRecyclerAdapter<ContainerModel>(datalist) {

    fun removeAt(position: Int, callback: (MutableList<ContainerModel>) -> Unit) {
        datalist.removeAt(position)
        callback(datalist)
        notifyItemRemoved(position)
    }



    override fun getItemViewType(position: Int): Int {
        if (datalist.get(position) == null) return VIEW_TYPE_LOADING
        else return VIEW_TYPE_ITEM
    }


    companion object {
        const val VIEW_TYPE_ITEM = 0
        const val VIEW_TYPE_LOADING = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == VIEW_TYPE_ITEM) {
            val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.single_list, parent, false)
            return ViewHolder(itemView)
        }
        else {
            val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.single_list, parent, false)
            return ViewHolder(itemView)
        }

    }

    inner class ViewHolder(view: View) : BaseViewHolder<ContainerModel>(view) {

        override fun bindOnclick(view: View, data: ContainerModel, position: Int) {
            view.image.setOnClickListener {
                callback(data.containerPrefix+data.containerNo)
            }
        }

        override fun bindtext(view: View, data: ContainerModel, position: Int) {
            view.text_conno.text = data.containerPrefix+data.containerNo
            view.text_iso.text = data.iso
            view.image.setImageResource(data.image)
        }
    }


}
