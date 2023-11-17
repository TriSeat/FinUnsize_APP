package listener;

import java.util.List;

import persistence.models.UserModel;

public interface OnUserFetchListener {
    void onUserFetchSuccess(List<UserModel> users);
    void onUserFetchError(Exception e);
}