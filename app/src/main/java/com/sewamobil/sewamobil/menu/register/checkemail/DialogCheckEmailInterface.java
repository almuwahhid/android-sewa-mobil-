package com.sewamobil.sewamobil.menu.register.checkemail;

public interface DialogCheckEmailInterface {
    interface Presenter{
        public void checkEmail(String email);
    }
    interface View{
        public void onCheckEmail(boolean isSuccess);
    }
}
