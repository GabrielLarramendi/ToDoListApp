package dominando.android.larratodolist.adapters

import android.graphics.Color
import android.graphics.Paint
import android.provider.CalendarContract.Colors
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import dominando.android.larratodolist.R
import dominando.android.larratodolist.model.Item

class ItemAdapter(private val items:MutableList<Item>): RecyclerView.Adapter<ItemAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
        v.setBackgroundColor(Color.RED)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.isBoxChecked.isChecked = item.isChecked
        holder.itemEditText.setText(item.text)

        holder.isBoxChecked.setOnCheckedChangeListener {_, isChecked ->
            item.isChecked = isChecked

            if(isChecked) {
                holder.itemEditText.paintFlags = holder.itemEditText.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                holder.itemEditText.setBackgroundColor(Color.GREEN)
                holder.itemEditText.setTextColor(Color.BLACK)
                holder.containerItem.setBackgroundColor(Color.GREEN)
            } else {
                holder.itemEditText.paintFlags = holder.itemEditText.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                holder.itemEditText.setBackgroundColor(Color.RED)
                holder.itemEditText.setTextColor(Color.BLACK)
                holder.containerItem.setBackgroundColor(Color.RED)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var isBoxChecked: CheckBox = itemView.findViewById(R.id.item_checkbox)
        val itemEditText: EditText = itemView.findViewById(R.id.item_editText)
        val containerItem: CardView = itemView.findViewById(R.id.containerItem)
    }
}