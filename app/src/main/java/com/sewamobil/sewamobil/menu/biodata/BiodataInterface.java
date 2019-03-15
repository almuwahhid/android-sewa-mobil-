package com.sewamobil.sewamobil.menu.biodata;

import com.sewamobil.sewamobil.base.BaseViewInterface;
import com.sewamobil.sewamobil.menu.biodata.Model.UserModel;

public interface BiodataInterface {
    interface Presenter{
        void changeProfil(UserModel model);
    }

    interface View extends BaseViewInterface {
        void onChangeProfil(UserModel model);
    }
}
