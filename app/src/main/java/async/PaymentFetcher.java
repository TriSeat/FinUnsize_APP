package async;

import android.os.Handler;
import android.os.HandlerThread;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import listener.OnPaymentFetchListener;

import persistence.models.PaymentModel;
import request.Connection;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PaymentFetcher {

    private Handler handler;
    private OnPaymentFetchListener listener;

    public PaymentFetcher(Handler handler, OnPaymentFetchListener listener) {
        this.handler = handler;
        this.listener = listener;
    }

    public void fetchPayments() {
        HandlerThread handlerThread = new HandlerThread("PaymentFetcherThread");
        handlerThread.start();

        Handler handler = new Handler(handlerThread.getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String result = Connection.connectHttp("payments");

                if (result != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");

                        List<PaymentModel> payments = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject paymentJson = jsonArray.getJSONObject(i);

                            UUID idPayment = UUID.fromString(paymentJson.getString("idPayment"));
                            String nome = paymentJson.getString("nome");
                         //   String cnpj = paymentJson.getString("cnpj");


                            PaymentModel payment = new PaymentModel(idPayment, nome);
                            payments.add(payment);
                        }

                        if (listener != null) {
                            listener.onPaymentFetchSuccess(payments);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        if (listener != null) {
                            listener.onPaymentFetchError();
                        }
                    }

                } else {
                    if (listener != null) {
                        listener.onPaymentFetchError();
                    }
                }
            }
        });
    }
}
