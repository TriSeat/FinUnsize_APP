package listener;

import persistence.models.CashierModel;

public interface OnCashierFetchListener {
    void onCashierFetchSuccess(CashierModel cashier);
    void onCashierFetchError();
}