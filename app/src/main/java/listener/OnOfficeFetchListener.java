package listener;

import java.util.List;

import persistence.models.OfficeModel;

public interface OnOfficeFetchListener {
    void onOfficeFetchSuccess(List<OfficeModel> offices);
    void onOfficeFetchError();
}
