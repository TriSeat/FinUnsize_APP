package listener;

import java.util.List;

import persistence.models.ProjectionModel;

public interface OnProjectionFetchListener {
    void onProjectionFetchSuccess(List<ProjectionModel> projections);
    void onProjectionFetchError();
}
