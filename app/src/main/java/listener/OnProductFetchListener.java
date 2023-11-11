package listener;


import persistence.models.ProductModel;

public interface OnProductFetchListener {
    void onProductFetchSuccess(ProductModel product);
    void onProductFetchError();
}
