package async;

import java.util.ArrayList;
import java.util.List;

import exception.UserFetchException;
import listener.OnUserFetchListener;
import persistence.models.UserModel;

public class UserFetcher {

    private OnUserFetchListener listener;

    public UserFetcher(OnUserFetchListener listener) {
        this.listener = listener;
    }

    public void fetchUsers() {
        try {
            List<UserModel> users; // Método que obtém os usuários
            users = retrieveUsersFromDataSource();

            if (listener != null) {
                listener.onUserFetchSuccess(users);
            }
        } catch (UserFetchException e) {
            if (listener != null) {
                listener.onUserFetchError(e);
            }
        }
    }

    // Simulação de busca de usuários em um banco de dados ou API
    private List<UserModel> retrieveUsersFromDataSource() throws UserFetchException {
        // Simulando a obtenção de usuários de uma fonte de dados (exemplo: uma lista estática)
        List<UserModel> users = new ArrayList<>();
        users.add(new UserModel("Alice"));
        users.add(new UserModel("Bob"));
        // Simulando uma exceção caso ocorra um erro na obtenção dos usuários
        if (users.isEmpty()) {
            throw new UserFetchException("Failed to fetch users");
        }
        return users;
    }
}