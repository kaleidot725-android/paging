package kaleidot725.sample.ui.core

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jp.kaleidot725.sample.R
import kaleidot725.sample.data.entity.Item
import kotlinx.android.synthetic.main.recycler_view_item.view.*

class ItemRecyclerView : RecyclerView {
    private val itemRecyclerAdapter = ItemRecyclerAdapter()

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    init {
        this.layoutManager = LinearLayoutManager(context).apply {
            orientation = VERTICAL
        }
        this.adapter = itemRecyclerAdapter
    }

    fun update(dataSet: List<Item>) {
        itemRecyclerAdapter.update(dataSet)
    }
}


class ItemRecyclerAdapter() : RecyclerView.Adapter<ItemHolder>() {
    private val dataSet: MutableList<Item> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_item, parent, false) as View
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.title.text = dataSet[position].title
    }

    override fun getItemCount() = dataSet.size

    fun update(dataSet: List<Item>) {
        this.dataSet.clear()
        this.dataSet.addAll(dataSet)
        this.notifyItemRangeChanged(0, this.dataSet.count())
    }
}

class ItemHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    val title = view.title
}



