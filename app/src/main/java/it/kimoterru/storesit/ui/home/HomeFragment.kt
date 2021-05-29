package it.kimoterru.storesit.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.kimoterru.storesit.adapters.HomeListAdapter
import it.kimoterru.storesit.adapters.ProductClickListener
import it.kimoterru.storesit.databinding.FragmentHomeBinding
import it.kimoterru.storesit.network.Status
import it.kimoterru.storesit.network.models.HomeResponse

class HomeFragment : Fragment(), ProductClickListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var viewModel: HomeViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        initViews()
        initObservers()
    }

    override fun onResume() {
        super.onResume()
        viewModel?.getHomeScreen()
    }

    private fun initViews() {
        binding.recyclerNew.layoutManager = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)
        binding.recyclerNewIncome.layoutManager = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)
        binding.recyclerHotSales.layoutManager = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)
        binding.recyclerDiscount.layoutManager = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)
    }

    private fun initObservers() {
        viewModel?.homeResponseLiveData?.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> {
                    binding.progressBar.isVisible = true
                }
                Status.SUCCESS -> {
                    binding.progressBar.isVisible = false
                    displayData(it.data)
                }
                Status.ERROR -> {
                    binding.progressBar.isVisible = false
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun displayData(response: HomeResponse?) {
        binding.recyclerNew.adapter = HomeListAdapter(response?.newProductList ?: listOf(), this)
        binding.recyclerNewIncome.adapter = HomeListAdapter(response?.incomeProductList ?: listOf(), this)
        binding.recyclerHotSales.adapter = HomeListAdapter(response?.clearanceSaleProductList ?: listOf(), this)
        binding.recyclerDiscount.adapter = HomeListAdapter(response?.discountProductList ?: listOf(), this)

        if (response?.newProductList.isNullOrEmpty()) {
            binding.homeNew.isVisible = false
        }
        if (response?.incomeProductList.isNullOrEmpty()) {
            binding.homeNewIncome.isVisible = false
        }
        if (response?.clearanceSaleProductList.isNullOrEmpty()) {
            binding.homeHotSales.isVisible = false
        }
        if (response?.discountProductList.isNullOrEmpty()) {
            binding.homeDiscount.isVisible = false
        }
    }

    override fun onItemClick(id: Long) {
        Toast.makeText(this.context, "onClick" + id, Toast.LENGTH_LONG).show()
    }

    override fun onFavoriteClick(id: Long) {
        Toast.makeText(this.context, "onClick" + id, Toast.LENGTH_LONG).show()
    }

    override fun onAddToCartClick(id: Long) {
        Toast.makeText(this.context, "onClick" + id, Toast.LENGTH_LONG).show()
    }
}