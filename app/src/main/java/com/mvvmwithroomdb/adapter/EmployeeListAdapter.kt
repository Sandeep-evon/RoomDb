package com.mvvmwithroomdb.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mvvmwithroomdb.R
import com.mvvmwithroomdb.database.Employee
import com.mvvmwithroomdb.databinding.SubitemEmpBinding

class EmployeeListAdapter(private val data:List<Employee>) : RecyclerView.Adapter<EmployeeListAdapter.EmpoyeeViewHolder>() {
    inner class EmpoyeeViewHolder(val binding: SubitemEmpBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmpoyeeViewHolder {
        var binding = SubitemEmpBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return EmpoyeeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmpoyeeViewHolder, position: Int) {
        holder.binding.ref = data[position]
    }

    override fun getItemCount(): Int {
        return data.size
    }
}