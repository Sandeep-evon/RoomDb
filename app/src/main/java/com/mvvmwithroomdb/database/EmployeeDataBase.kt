package com.mvvmwithroomdb.database
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Employee::class], version = 1, exportSchema = false)
abstract class EmployeeDataBase: RoomDatabase() {
    abstract fun getEmployeeDao():EmployeeDao

    companion object{
        @Volatile
        private var Instance: EmployeeDataBase? = null
        public fun getInstance(context: Context):EmployeeDataBase{
            synchronized(this){
                if(Instance == null){
                    Instance = Room.databaseBuilder(context.applicationContext,
                    EmployeeDataBase::class.java,"Employee_Database").build()
                }
                return Instance!!
            }
        }
    }
}