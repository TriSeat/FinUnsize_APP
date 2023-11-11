package listener;


import java.util.List;

import persistence.models.PaymentModel;

public interface OnPaymentFetchListener {
    void onPaymentFetchSuccess(List<PaymentModel> payments);
    void onPaymentFetchError();
}