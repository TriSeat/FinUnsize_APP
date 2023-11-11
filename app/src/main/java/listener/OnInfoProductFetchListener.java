package listener;

import java.util.List;

import persistence.models.InfoProductModel;

public interface OnInfoProductFetchListener {
    void onInfoProductFetchSuccess(List<InfoProductModel> infoProducts);
    void onInfoProductFetchError();
}
