package async;

import android.os.Handler;
import android.os.HandlerThread;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import persistence.models.InfoProductModel;
import persistence.models.ProductModel;
import request.Connection;


public class ProductFetcher {

    private Handler handler;
    private OnProductFetchListener listener;

    public ProductFetcher(Handler handler, OnProductFetchListener listener) {
        this.handler = handler;
        this.listener = listener;
    }

    public void fetchProduct(String barcode) {
        HandlerThread handlerThread = new HandlerThread("ProductFetcherThread");
        handlerThread.start();

        Handler handler = new Handler(handlerThread.getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String result = Connection.connectHttp("products/" + barcode);

                if (result != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        JSONObject productJson = jsonObject.getJSONObject("data");

                        String cod_barras = productJson.getString("cod_barras");
                        String nome = productJson.getString("nome");
                        int quantidade = productJson.getInt("quantidade");
                        InfoProductModel informacoes = parseInfoProductModel(productJson.getJSONObject("informacoes"));
                        LocalDate validade = LocalDate.parse(productJson.getString("validade"));
                        String descricao = productJson.getString("descricao");
                        BigDecimal varejo = new BigDecimal(productJson.getString("varejo"));
                        BigDecimal atacado = new BigDecimal(productJson.getString("atacado"));
                        LocalDate data_cadastro = LocalDate.parse(productJson.getString("data_cadastro"));
                        String url_image = productJson.optString("url_image", null);
                        String cnpj = productJson.getString("cnpj");

                        ProductModel product = new ProductModel(cod_barras, nome, quantidade, informacoes, validade,
                                descricao, varejo, atacado, data_cadastro, url_image, cnpj);

                        if (listener != null) {
                            listener.productFetchSuccess(product);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        if (listener != null) {
                            listener.productFetchError();
                        }
                    }

                } else {
                    if (listener != null) {
                        listener.productFetchError();
                    }
                }
            }
        });
    }

    private InfoProductModel parseInfoProductModel(JSONObject infoJson) throws JSONException {
        UUID id_item_produto = UUID.fromString(infoJson.getString("id_item_produto"));
        String marca = infoJson.getString("marca");
        String categoria = infoJson.getString("categoria");
        String tipo = infoJson.getString("tipo");

        return new InfoProductModel(id_item_produto, marca, categoria, tipo);
    }

    public interface OnProductFetchListener {
        void productFetchSuccess(ProductModel product);
        void productFetchError();
    }
}
