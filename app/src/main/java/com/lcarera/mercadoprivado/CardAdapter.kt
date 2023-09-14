import android.icu.text.NumberFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lcarera.mercadoprivado.R
import data.CardItem

class CardAdapter() : RecyclerView.Adapter<CardAdapter.ViewHolder>() {
    private var items = listOf<CardItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.title.text = item.title
        val currencyFormat = NumberFormat.getCurrencyInstance()
        holder.price.text = currencyFormat.format(item.price)
        holder.location.text = item.location
        Glide.with(holder.itemView.context)
            .load(item.thumbnailUrl)
            .placeholder(R.drawable.ic_search)
            .error(R.mipmap.ic_launcher)
            .into(holder.thumbnail)
    }

    override fun getItemCount(): Int = items.size
    fun setItems(items: List<CardItem>) {
        this.items = items
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val price: TextView = itemView.findViewById(R.id.price)
        val location: TextView = itemView.findViewById(R.id.location)
        val thumbnail: ImageView = itemView.findViewById(R.id.thumbnail)

    }
}