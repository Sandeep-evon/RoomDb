package com.mvvmwithroomdb.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.mvvmwithroomdb.R
import com.mvvmwithroomdb.database.EmployeeDataBase
import com.mvvmwithroomdb.databinding.ActivityHomeBinding
import com.mvvmwithroomdb.receiver.ConnectivityReceiver
import com.mvvmwithroomdb.repo.EmployeeRepo
import com.mvvmwithroomdb.viewmodel.HomeViewModel
import com.mvvmwithroomdb.viewmodelFactory.HomeViewModelFactory

class HomeActivity : BaseActivity() , ConnectivityReceiver.ConnectivityReceiverListener {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_home)
        val dao = EmployeeDataBase.getInstance(this).getEmployeeDao()
        viewModel = ViewModelProvider(this,HomeViewModelFactory(EmployeeRepo(dao))).get(HomeViewModel::class.java)
        binding.ref = viewModel
        viewModel.error.observe(this, Observer {
            Toast.makeText(this,it.toString(),Toast.LENGTH_LONG).show()
        })
    }

    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectivityReceiverListener = this
    }

    override fun onPause() {
        super.onPause()
        ConnectivityReceiver.connectivityReceiverListener = null
    }


    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        if (!isConnected) {
            snackBar = Snackbar.make(findViewById(R.id.rootLayout), "You are offline", Snackbar.LENGTH_LONG) //Assume "rootLayout" as the root layout of every activity.
            snackBar?.duration = BaseTransientBottomBar.LENGTH_LONG
            snackBar?.show()
           // displayMessage("You are offline")
        } else {
            snackBar?.dismiss()
        }
    }
}