package listener;

import java.util.List;

import persistence.models.PurchaseItemModel;

public interface OnPurchaseItemFetchListener {
    void onPurchaseItemFetchSuccess(List<PurchaseItemModel> purchaseItems);
    void onPurchaseItemFetchError();
}
