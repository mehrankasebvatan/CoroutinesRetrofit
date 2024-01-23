package android.mkv.coroutinesretrofit.view

import android.mkv.coroutinesretrofit.databinding.ActivityMainBinding
import android.mkv.coroutinesretrofit.viewmodel.ListViewModel
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ListViewModel
    private val adapter = CountryListAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        viewModel.refresh()
        observeViewModel()
        event()
    }

    private fun event() {
        binding.apply {
            loRefresh.setOnRefreshListener {
                viewModel.refresh()
                loRefresh.isRefreshing = false
            }
        }
    }


    private fun observeViewModel() {
        viewModel.countries.observe(this) { countries ->
            binding.apply {
                adapter.updateCountries(countries)
                rvData.adapter = adapter
                rvData.visibility = View.VISIBLE
            }

        }

        viewModel.loading.observe(this) { isLoading ->
            binding.apply {
                loading.visibility = if (isLoading) View.VISIBLE else View.GONE

                if (isLoading) {
                    rvData.visibility = View.GONE
                    txtError.visibility = View.GONE
                }
            }
        }

        viewModel.countryLoadError.observe(this) { error ->
            binding.txtError.visibility = if (error.isNullOrEmpty()) View.GONE else View.VISIBLE
        }

    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[ListViewModel::class.java]
    }

}