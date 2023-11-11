package listener;

import persistence.models.EmployeeCashierModel;

public interface OnEmployeeCashierFetchListener {
    void onEmployeeCashierFetchSuccess(EmployeeCashierModel employeeCashier);
    void onEmployeeCashierFetchError();
}