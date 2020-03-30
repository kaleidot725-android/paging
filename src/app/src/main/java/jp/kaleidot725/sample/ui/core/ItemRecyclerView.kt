package kaleidot725.sample.ui.core

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import jp.kaleidot725.sample.R
import kaleidot725.sample.data.entity.Item
import kotlinx.android.synthetic.main.recycler_view_item.view.*

class ItemRecyclerView : RecyclerView {
    private val itemRecyclerAdapter = ItemRecyclerAdapter()

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    init {
        this.layoutManager = LinearLayoutManager(context).apply { orientation = VERTICAL }
        this.adapter = itemRecyclerAdapter
        this.setHasFixedSize(true)
    }

    fun submitList(dataSet: PagedList<Item>) {
        Log.v("TAG", "submit ${dataSet}")
        itemRecyclerAdapter.submitList(dataSet)
    }
}


private val diffCallback = object : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item) =
        oldItem.id == newItem.id // check uniqueness

    override fun areContentsTheSame(oldItem: Item, newItem: Item) =
        oldItem == newItem // check contents
}

class ItemRecyclerAdapter() : PagedListAdapter<Item, ItemHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_item, parent, false) as View
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.title.text = getItem(position)?.title
    }
}

class ItemHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    val title = view.title
}



