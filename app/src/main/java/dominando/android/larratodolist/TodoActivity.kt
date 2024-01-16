package dominando.android.larratodolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dominando.android.larratodolist.adapters.ItemAdapter
import dominando.android.larratodolist.model.Item

class TodoActivity : AppCompatActivity() {

    private var items = arrayListOf<Item>()
    private var adapter = ItemAdapter(items)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.todos_activity)

        val fab = findViewById<FloatingActionButton>(R.id.fabAddToDo)
        initRecyclerView()

        fab.setOnClickListener {
            addItem()
        }
    }

    private fun initRecyclerView() {
        val rvItem = findViewById<RecyclerView>(R.id.rvTodoItems)
        rvItem.adapter = adapter
        val layoutManager = GridLayoutManager(this, 1)
        initSwipeGesture()
        rvItem.layoutManager = layoutManager
    }

    private fun clickEnterAndCreateAnewItem() {

    }

    private fun addItem() {
        val item = Item("", false)
        items.add(item)
        adapter.notifyItemInserted(items.lastIndex)
    }

    private fun initSwipeGesture() {
        val swipe = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                items.removeAt(position)
                adapter.notifyItemRemoved(position)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipe)
        val rvItems = findViewById<RecyclerView>(R.id.rvTodoItems)
        itemTouchHelper.attachToRecyclerView(rvItems)
    }
}