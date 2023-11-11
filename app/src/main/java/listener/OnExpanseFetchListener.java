
package listener;



import java.util.List;

import persistence.models.ExpanseModel;

public interface OnExpanseFetchListener {
    void onExpanseFetchSuccess(List<ExpanseModel> expanses);
    void onExpanseFetchError();
}

