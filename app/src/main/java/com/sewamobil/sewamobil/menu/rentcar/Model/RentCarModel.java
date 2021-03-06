package com.sewamobil.sewamobil.menu.rentcar.Model;

import java.io.Serializable;

public class RentCarModel implements Serializable {
    String id_kendaraan, id_model, nama_model, plat_nomor, merk_kendaraan, tipe_kendaraan, tahun_pembuatan, isi_silinder, nomor_rangka, nomor_mesin, tarif_kendaraan, foto_kendaraan;
    boolean available;
    String[] photos;

    public RentCarModel(String id_kendaraan, String id_model, String nama_model, String plat_nomor, String merk_kendaraan, String tipe_kendaraan, String tahun_pembuatan, String isi_silinder, String nomor_rangka, String nomor_mesin, String tarif_kendaraan, String foto_kendaraan, boolean available) {
        this.id_kendaraan = id_kendaraan;
        this.id_model = id_model;
        this.nama_model = nama_model;
        this.plat_nomor = plat_nomor;
        this.merk_kendaraan = merk_kendaraan;
        this.tipe_kendaraan = tipe_kendaraan;
        this.tahun_pembuatan = tahun_pembuatan;
        this.isi_silinder = isi_silinder;
        this.nomor_rangka = nomor_rangka;
        this.nomor_mesin = nomor_mesin;
        this.tarif_kendaraan = tarif_kendaraan;
        this.foto_kendaraan = foto_kendaraan;
        this.available = available;
    }

    public String getFoto_kendaraan() {
        return foto_kendaraan;
    }

    public void setFoto_kendaraan(String foto_kendaraan) {
        this.foto_kendaraan = foto_kendaraan;
    }

    public String getId_kendaraan() {
        return id_kendaraan;
    }

    public void setId_kendaraan(String id_kendaraan) {
        this.id_kendaraan = id_kendaraan;
    }

    public String getId_model() {
        return id_model;
    }

    public void setId_model(String id_model) {
        this.id_model = id_model;
    }

    public String getNama_model() {
        return nama_model;
    }

    public void setNama_model(String nama_model) {
        this.nama_model = nama_model;
    }

    public String getPlat_nomor() {
        return plat_nomor;
    }

    public void setPlat_nomor(String plat_nomor) {
        this.plat_nomor = plat_nomor;
    }

    public String getMerk_kendaraan() {
        return merk_kendaraan;
    }

    public void setMerk_kendaraan(String merk_kendaraan) {
        this.merk_kendaraan = merk_kendaraan;
    }

    public String getTipe_kendaraan() {
        return tipe_kendaraan;
    }

    public void setTipe_kendaraan(String tipe_kendaraan) {
        this.tipe_kendaraan = tipe_kendaraan;
    }

    public String getTahun_pembuatan() {
        return tahun_pembuatan;
    }

    public void setTahun_pembuatan(String tahun_pembuatan) {
        this.tahun_pembuatan = tahun_pembuatan;
    }

    public String getIsi_silinder() {
        return isi_silinder;
    }

    public void setIsi_silinder(String isi_silinder) {
        this.isi_silinder = isi_silinder;
    }

    public String getNomor_rangka() {
        return nomor_rangka;
    }

    public void setNomor_rangka(String nomor_rangka) {
        this.nomor_rangka = nomor_rangka;
    }

    public String getNomor_mesin() {
        return nomor_mesin;
    }

    public void setNomor_mesin(String nomor_mesin) {
        this.nomor_mesin = nomor_mesin;
    }

    public String getTarif_kendaraan() {
        return tarif_kendaraan;
    }

    public void setTarif_kendaraan(String tarif_kendaraan) {
        this.tarif_kendaraan = tarif_kendaraan;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String[] getPhotos() {
        return photos;
    }

    public void setPhotos(String[] photos) {
        this.photos = photos;
    }
}
