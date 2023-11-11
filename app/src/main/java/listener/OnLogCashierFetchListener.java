package listener;

import java.util.List;

import persistence.models.LogCashierModel;

public interface OnLogCashierFetchListener {
    void onLogCashierFetchSuccess(List<LogCashierModel> logCashiers);
    void onLogCashierFetchError();
}
