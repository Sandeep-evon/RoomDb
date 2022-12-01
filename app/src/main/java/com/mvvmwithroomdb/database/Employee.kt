package com.mvvmwithroomdb.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity (tableName = "Employee_Record")
class Employee(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "Id")
    val empId: String,
    @ColumnInfo(name = "Name")
    val empName: String,
    @ColumnInfo(name = "Email")
    val empEmail: String,
    val createdAt: Date
)
