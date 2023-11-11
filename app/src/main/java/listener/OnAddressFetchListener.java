package listener;

import persistence.models.AddressModel;

public interface OnAddressFetchListener {
    void onAddressFetchSuccess(AddressModel address);
    void onAddressFetchError();
}