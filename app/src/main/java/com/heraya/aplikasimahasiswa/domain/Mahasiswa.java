package com.heraya.aplikasimahasiswa.domain;

public class Mahasiswa {
    private String nim,nama,alamat,jk,agama,tgllhr;

    public Mahasiswa() {
    }

    public Mahasiswa(String nim, String nama, String alamat, String jk, String agama, String tgllhr) {
        this.nim = nim;
        this.nama = nama;
        this.alamat = alamat;
        this.jk = jk;
        this.agama = agama;
        this.tgllhr = tgllhr;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getTgllhr() {
        return tgllhr;
    }

    public void setTgllhr(String tgllhr) {
        this.tgllhr = tgllhr;
    }
}
