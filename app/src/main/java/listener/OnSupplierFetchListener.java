package listener;

import persistence.models.SupplierModel;

public interface OnSupplierFetchListener {
    void onSupplierFetchSuccess(SupplierModel supplier);
    void onSupplierFetchError();
}