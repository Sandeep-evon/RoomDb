package com.mvvmwithroomdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvmwithroomdb.database.Employee
import com.mvvmwithroomdb.repo.EmployeeRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel(val repo: EmployeeRepo) : ViewModel() {
    val empId = MutableLiveData<String>()
    val empName = MutableLiveData<String>()
    val empEmail = MutableLiveData<String>()
    val error = MutableLiveData<String>()
    val employees: LiveData<List<Employee>>
        get() = repo.employees

    fun save() {

        if (empId.value?.isNotEmpty() == true) {
            if (empName.value?.isNotEmpty() == true) {
                if (empEmail.value?.isNotEmpty() == true) {
                    viewModelScope.launch(Dispatchers.IO) {
                        repo.insertEmployee(
                            Employee(
                                empId.value!!,
                                empName.value!!,
                                empEmail.value!!
                            )
                        )
                    }
                } else
                    error.value = "Please Enter Email."
            } else
                error.value = "Please Enter Name."
        } else
            error.value = "Please Enter Id."
    }

    fun update() {

        if (empId.value?.isNotEmpty() == true) {
            if (empName.value?.isNotEmpty() == true) {
                if (empEmail.value?.isNotEmpty() == true) {
                    viewModelScope.launch(Dispatchers.IO) {
                        repo.updateEmployee(
                            Employee(
                                empId.value!!,
                                empName.value!!,
                                empEmail.value!!
                            )
                        )
                    }
                } else
                    error.value = "Please Enter Email."
            } else
                error.value = "Please Enter Name."
        } else
            error.value = "Please Enter Id."

    }

    fun delete() {
        if (empId.value?.isNotEmpty() == true) {
            if (empName.value?.isNotEmpty() == true) {
                if (empEmail.value?.isNotEmpty() == true) {
                    viewModelScope.launch(Dispatchers.IO) {
                        repo.deleteEmployee(
                            Employee(
                                empId.value!!,
                                empName.value!!,
                                empEmail.value!!
                            )
                        )
                    }
                } else
                    error.value = "Please Enter Email."
            } else
                error.value = "Please Enter Name."
        } else
            error.value = "Please Enter Id."
    }
}