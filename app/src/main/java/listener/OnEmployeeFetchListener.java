package listener;


import java.util.List;

import persistence.models.EmployeeModel;

public interface OnEmployeeFetchListener {
    void onEmployeeFetchSuccess(List<EmployeeModel> employees);
    void onEmployeeFetchError();
}