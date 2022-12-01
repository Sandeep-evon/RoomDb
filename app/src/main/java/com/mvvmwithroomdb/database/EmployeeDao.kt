package com.mvvmwithroomdb.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface EmployeeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(employee: Employee):Long

    @Update
    suspend fun update(employee: Employee): Int

    @Delete
    suspend fun delete(employee: Employee): Int

    @Query("select * from Employee_Record")
    fun getEmployees(): LiveData<List<Employee>>

    /*@Query("Select * from Employee_Record where Id = :empId")
    fun getEmployee(empId:String):LiveData<Employee>*/
}
