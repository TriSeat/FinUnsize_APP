package async;

        import java.util.List;

        import exception.UserFetchException;
        import listener.OnUserFetchListener;
        import persistence.models.UserModel;
        import request.Connection;
        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;
        import retrofit2.Retrofit;
        import retrofit2.converter.gson.GsonConverterFactory;

public class UserFetcher {

    private OnUserFetchListener listener;
    private static final String BASE_URL = "https://finunsize.onrender.com/user/"; // Substitua pela URL da sua API

    public UserFetcher(OnUserFetchListener listener) {
        this.listener = listener;
    }

    /*public void fetchUsers() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Connection service = retrofit.create(Connection.class);

        Call<List<UserModel>> call = service.getUsers();
        call.enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                if (response.isSuccessful()) {
                    List<UserModel> users = response.body();
                    if (listener != null) {
                        listener.onUserFetchSuccess(users);
                    }
                } else {
                    if (listener != null) {
                        listener.onUserFetchError(new UserFetchException("Failed to fetch users"));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {
                if (listener != null) {
                    listener.onUserFetchError(new UserFetchException("Failed to fetch users: " + t.getMessage()));
                }
            }
        });
    }*/
}
