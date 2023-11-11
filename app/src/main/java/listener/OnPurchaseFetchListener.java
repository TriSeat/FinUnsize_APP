package listener;


import java.util.List;

import persistence.models.PurchaseModel;

public interface OnPurchaseFetchListener {
    void onPurchaseFetchSuccess(List<PurchaseModel> purchases);
    void onPurchaseFetchError();
}
