package com.example.crimeintent

import android.app.DatePickerDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.crimeintent.databinding.FragmentCrimeFragmentBinding
import java.util.*
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.ScheduledThreadPoolExecutor
import java.util.concurrent.TimeUnit

class CrimeFragment : Fragment() {

    companion object {
        fun newInstance() = CrimeFragment()
    }

    private lateinit var viewModel: FragmentCrimeViewModel
    private val pool:ScheduledThreadPoolExecutor by lazy {
        ScheduledThreadPoolExecutor(2)
    }
    private lateinit var task : ScheduledFuture<*>
    lateinit var binding: FragmentCrimeFragmentBinding
    lateinit var datePickerDialog: DatePickerDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCrimeFragmentBinding.inflate(inflater,container,false)
        binding.data = FragmentCrimeViewModel().also {
            task = pool.scheduleAtFixedRate({
                    it.date()
            },0,1,TimeUnit.SECONDS)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            pool.shutdown()
            datePickerDialog = DatePickerDialog(binding.root.context)
            datePickerDialog.show()
            datePickerDialog.setOnDateSetListener { _, i, i2, i3 ->
                val date = Date(i,i2,i3-1)
                binding.button.text = "$date"
            }
        }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FragmentCrimeViewModel::class.java)
        // TODO: Use the ViewModel

    }

    override fun onDestroyView() {
        super.onDestroyView()
        task.cancel(true)
    }
}