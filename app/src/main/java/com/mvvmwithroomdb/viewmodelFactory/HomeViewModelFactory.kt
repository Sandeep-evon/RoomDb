package com.mvvmwithroomdb.viewmodelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mvvmwithroomdb.repo.EmployeeRepo
import com.mvvmwithroomdb.viewmodel.HomeViewModel

class HomeViewModelFactory(val repo: EmployeeRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(repo) as T
    }
}