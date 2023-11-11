package listener;

import persistence.models.SaleModel;

import java.util.List;

public interface OnSaleFetchListener {
    void onSaleFetchSuccess(List<SaleModel> sales);
    void onSaleFetchError();
}
