package listener;

import java.util.List;

import persistence.models.TypeExpanseModel;

public interface OnTypeExpanseFetchListener {
    void onTypeExpanseFetchSuccess(List<TypeExpanseModel> typeExpanses);
    void onTypeExpanseFetchError();
}
