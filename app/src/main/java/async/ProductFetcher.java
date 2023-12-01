package async;

import android.os.Handler;
import android.os.HandlerThread;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import exception.ProductFetchException;
import listener.OnProductFetchListener;
import persistence.models.InfoProductModel;
import persistence.models.ProductModel;
import persistence.models.SupplierModel;
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
                try {
                    String result = Connection.connectHttp("product/" + barcode);

                    if (result != null) {
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
                        SupplierModel fornecedor = parseSupplierModel(productJson.getJSONObject("fornecedor"));
                        LocalDate data_cadastro = LocalDate.parse(productJson.getString("data_cadastro"));
                        String url_image = productJson.optString("url_image", null);

                        ProductModel product = new ProductModel(cod_barras, nome, quantidade, informacoes, validade,
                                descricao, varejo, atacado, fornecedor, data_cadastro, url_image);

                        if (listener != null) {
                            listener.onProductFetchSuccess(product);
                        }

                    } else {
                        throw new ProductFetchException("Error fetching product");
                    }

                } catch (JSONException | ProductFetchException e) {
                    e.printStackTrace();
                    if (listener != null) {
                        listener.onProductFetchError(e);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private SupplierModel parseSupplierModel (JSONObject infoJson) throws JSONException{
        UUID id_fornecedor = UUID.fromString(infoJson.getString("id_fornecedor"));
        String nome = infoJson.getString("nome");
        String id_endereco = infoJson.getString("id_endereco");
        String descricao = infoJson.getString("descricao");
        String url_image = infoJson.getString("url_image");
        String cnpj = infoJson.getString("cnpj");

        return new SupplierModel(id_fornecedor, nome, id_endereco, descricao, url_image, cnpj);
    }

    private InfoProductModel parseInfoProductModel(JSONObject infoJson) throws JSONException {
        UUID id_item_produto = UUID.fromString(infoJson.getString("id_item_produto"));
        String marca = infoJson.getString("marca");
        String categoria = infoJson.getString("categoria");
        String tipo = infoJson.getString("tipo");

        return new InfoProductModel(id_item_produto, marca, categoria, tipo);
    }
}
