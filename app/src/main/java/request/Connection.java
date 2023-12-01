package request;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import integration.AuthResponse;
import persistence.models.UserModel;
import retrofit2.Call;

public class Connection {

    private static final String BASE_URL = "https://finunsize.onrender.com";

    public static String connectHttp(String endpoint) throws IOException {
        String apiUrl = BASE_URL + endpoint;
        return connectHttp(apiUrl);
    }

    public static String connectHttp(String apiUrl, String token) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            setAuthorizationHeader(connection, token);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                return readApiResponse(connection);
            } else {
                throw new IOException("Erro na resposta da API: " + responseCode);
            }
        } finally {
            connection.disconnect();
        }
    }

    private static void setAuthorizationHeader(HttpURLConnection connection, String token) {
        if (token != null && !token.isEmpty()) {
            connection.setRequestProperty("Authorization", "Bearer " + token);
        }
    }

    private static String readApiResponse(HttpURLConnection connection) throws IOException {
        StringBuilder response = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        }
        return response.toString();
    }
}