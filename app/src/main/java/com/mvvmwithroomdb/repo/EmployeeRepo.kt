package com.mvvmwithroomdb.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mvvmwithroomdb.database.Employee
import com.mvvmwithroomdb.database.EmployeeDao
import com.mvvmwithroomdb.database.EmployeeDataBase

class EmployeeRepo(private val dao: EmployeeDao) {
    val employees:LiveData<List<Employee>>
    get() = dao.getEmployees()
     suspend fun insertEmployee(employee: Employee){
        Log.e("Insert: "," "+dao.insert(employee))

    }
    suspend fun updateEmployee(employee: Employee){
        dao.update(employee)
    }
    suspend fun deleteEmployee(employee: Employee){
        dao.delete(employee)
    }
    fun getEmployee(){

    }
}