package com.example.finunsize.presentation.activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finunsize.R;

import java.time.format.DateTimeFormatter;
import java.util.List;

import persistence.models.EmployeeModel; // Supondo que o modelo de Employee seja nomeado EmployeeModel

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {

    private List<EmployeeModel> employeeList;
    private Context context;

    public EmployeeAdapter(Context context, List<EmployeeModel> employeeList) {
        this.context = context;
        this.employeeList = employeeList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employee_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        EmployeeModel employee = employeeList.get(position);

        holder.employeeName.setText(employee.getNome());
        holder.employeeRole.setText((CharSequence) employee.getCargo());
        holder.employeePhone.setText(employee.getTelefone());
        holder.employeeSalary.setText(String.valueOf(employee.getSalario()));

    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView employeeName;
        TextView employeeRole;
        TextView employeePhone;
        TextView employeeSalary;

        public ViewHolder(View itemView) {
            super(itemView);
            employeeName = itemView.findViewById(R.id.nome_funcionario);
            employeeRole = itemView.findViewById(R.id.cargo_funcionario);
            employeePhone = itemView.findViewById(R.id.telefone_funcionario);
            employeeSalary = itemView.findViewById(R.id.salario_funcionario);

        }
    }
}