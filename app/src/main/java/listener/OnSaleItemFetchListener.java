package listener;


import java.util.List;

import persistence.models.SaleItemModel;

public interface OnSaleItemFetchListener {
    void onSaleItemFetchSuccess(List<SaleItemModel> saleItems);
    void onSaleItemFetchError();
}
