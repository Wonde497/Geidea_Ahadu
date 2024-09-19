package net.geidea.payment.customviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import net.geidea.payment.databinding.FragmentAmountBinding
import net.geidea.payment.transaction.view.CardReadActivity
import net.geidea.payment.utils.FirebaseDatabaseSingleton
import net.geidea.payment.utils.FirebaseDatabaseSingleton.getCurrentTime
import net.geidea.utils.CurrencyConverter


class AmountFragment : Fragment() {

    private lateinit var binding: FragmentAmountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAmountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        attachKeyboardListener()
    }

    private fun attachKeyboardListener() {
        binding.keyboardLayout.setOnInteractionListener(onKeyValue = {
            binding.amount.text = CurrencyConverter.convertWithoutSAR(it.toLong())
        }, onConfirm = { amount, _ ->
            if (amount > 0) {
                val logAmt = binding.amount.text
                FirebaseDatabaseSingleton.setLog( "New Transaction Amount - $logAmt")
                CardReadActivity.startTransaction(requireContext(), amount)
            }
        })
    }

    override fun onStop() {
        super.onStop()
        binding.amount.text = "0.00"
        binding.keyboardLayout.resetValues()
    }

}