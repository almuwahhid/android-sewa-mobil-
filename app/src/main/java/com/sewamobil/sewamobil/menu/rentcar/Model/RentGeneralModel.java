package com.sewamobil.sewamobil.menu.rentcar.Model;

public class RentGeneralModel {
    String title = "", konten = "";
    int image;

    public RentGeneralModel(String title, String konten, int image) {
        this.title = title;
        this.konten = konten;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKonten() {
        return konten;
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
