package com.mvvmwithroomdb.database
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Employee::class], version = 2)
//@TypeConverters(Converters::class)
abstract class EmployeeDataBase: RoomDatabase() {
    abstract fun getEmployeeDao():EmployeeDao

    companion object{
        private var context:Context?=null
        val migration_1_2 = object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                //Toast.makeText(context,"Inside to migrate",Toast.LENGTH_LONG).show()
               Log.e("Print: "," Message")
                // Create the new table
                database.execSQL("CREATE TABLE Employee_Record_new (Id TEXT, Name TEXT, Email TEXT, PRIMARY KEY(Id))");
                // Copy the data
                database.execSQL("INSERT INTO Employee_Record_new (Id, Name, Email) SELECT Id, Name, Email FROM  Employee_Record");
                // Remove the old table
                database.execSQL("DROP TABLE Employee_Record");
                // Change the table name to the correct one
                database.execSQL("ALTER TABLE Employee_Record_new RENAME TO Employee_Record");
            }

        }
        @Volatile
        private var Instance: EmployeeDataBase? = null
        public fun getInstance(context: Context):EmployeeDataBase{
            synchronized(this){
                this.context = context
                if(Instance == null){
                    Instance = Room.databaseBuilder(context.applicationContext,
                    EmployeeDataBase::class.java,"Employee_Database")
                        .addMigrations(migration_1_2).build()
                }
                return Instance!!
            }
        }
    }
}

/*
*STEPS FOR MIGRATION
* 1) UPDATE THE VERSION
* 2) CREATE THE REF OF MIGRATION
* 3) OVERRIDE MIGRATE FRUNCTION AND EXECUTE QUERY TO ALTER TABLE
* 4) USE ADDMIGRATION WTIH ROOM.DATABASEBUILDER
*/