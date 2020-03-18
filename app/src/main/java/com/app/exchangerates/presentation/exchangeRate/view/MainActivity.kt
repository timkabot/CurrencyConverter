package com.app.exchangerates.presentation.exchangeRate.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.app.exchangerates.R
import com.app.exchangerates.presentation.exchangeRate.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), IMainScreen {
    private val mainViewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        observeExchangedValue()
        initListeners()
    }

    private fun initListeners() {
        exchangeButton.setOnClickListener{notifyAboutChange()}
        from_currency_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                mainViewModel.updateCurrency(from_currency_spinner.selectedItem.toString())
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                mainViewModel.updateCurrency(from_currency_spinner.selectedItem.toString())
            }
        }
    }

    private fun notifyAboutChange() {
        mainViewModel.exchange(from_currency_spinner.selectedItem.toString(), to_currency_spinner.selectedItem.toString(), cdn_input.text.toString())
    }

    override fun observeExchangedValue() {
        mainViewModel.exchangedEvents.observeForever {
            local_currency_amount.setText(it)
        }
    }
}
