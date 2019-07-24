package yuan.niceui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * 描述：Adapter
 *
 * @author yuanye
 * @date 2019/7/12 13:32
 */
class MainAdapter(var data: List<String>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(
            android.R.layout.activity_list_item, parent, false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textContent.text = data[position]
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textContent: TextView = view.findViewById(android.R.id.text1)
    }
}