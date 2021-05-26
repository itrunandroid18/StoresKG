package it.kimoterru.storesit.ui.home


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import it.kimoterru.storesit.R

class HomeFragment : Fragment(R.layout.fragment_home) {
    var viewModel: HomeViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        initObservers()
    }

    override fun onResume() {
        super.onResume()
        viewModel?.getHomeScreen()
    }

    private fun initObservers() {
        viewModel?.homeResponseLiveData?.observe(viewLifecycleOwner, {
            println(it)
        })
    }
}