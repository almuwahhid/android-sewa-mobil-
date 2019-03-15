package com.sewamobil.sewamobil.menu.rentcar.Model;

import java.io.Serializable;

public class RentCarModel implements Serializable {
    String id, name, id_jenis, nama_jenis;
    int status = 0;
    float price;

    public RentCarModel(String id, String name, String id_jenis, String nama_jenis, float price, int status) {
        this.id = id;
        this.name = name;
        this.id_jenis = id_jenis;
        this.nama_jenis = nama_jenis;
        this.price = price;
        this.status = status;
    }

    public RentCarModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_jenis() {
        return id_jenis;
    }

    public void setId_jenis(String id_jenis) {
        this.id_jenis = id_jenis;
    }

    public String getNama_jenis() {
        return nama_jenis;
    }

    public void setNama_jenis(String nama_jenis) {
        this.nama_jenis = nama_jenis;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
