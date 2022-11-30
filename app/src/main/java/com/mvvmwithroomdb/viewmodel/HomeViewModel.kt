package com.mvvmwithroomdb.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvmwithroomdb.database.Employee
import com.mvvmwithroomdb.repo.EmployeeRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(val repo: EmployeeRepo):ViewModel() {
    val empId = MutableLiveData<String>("S101")
    val empName = MutableLiveData<String>("Sandeep Saini")
    val empEmail = MutableLiveData<String>("sandeep.myfindoc@gmail.com")
    val error = MutableLiveData<String>()
    fun save(){
        viewModelScope.launch(Dispatchers.IO) {
            if(empId.value?.isNotEmpty() == true){
                if(empName.value?.isNotEmpty() == true){
                    if(empEmail.value?.isNotEmpty() == true){
                        repo.insertEmployee(Employee(empId.value!!,empName.value!!,empEmail.value!!))
                    }else
                        error.value = "Please Enter Email."
                }else
                    error.value = "Please Enter Name."
            }else
                error.value = "Please Enter Id."
        }

        error.value = "Perform Insert Operation"
    }
    fun update(){
        error.value = "Perform Update Operation"
    }
    fun delete(){
        error.value = "Perform Delete Operation"
    }
    fun getAllEmployees(){
        error.value = "Perform Get Operation"
    }
}