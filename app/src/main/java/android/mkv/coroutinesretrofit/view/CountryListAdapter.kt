package android.mkv.coroutinesretrofit.view

import android.annotation.SuppressLint
import android.mkv.coroutinesretrofit.databinding.ItemCountryBinding
import android.mkv.coroutinesretrofit.model.Country
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CountryListAdapter(
    private var list: ArrayList<Country>
) : RecyclerView.Adapter<CountryListAdapter.VH>() {
    class VH(val binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val data = list[position]
        holder.binding.apply {
            name.text = data.countryName
            capital.text = data.capital
            imageView.loadImage(data.flag ?: "")
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateCountries(newList: ArrayList<Country>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}