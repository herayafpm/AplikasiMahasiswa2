package com.heraya.aplikasimahasiswa.dao;

import android.provider.BaseColumns;

public class SkemaDatabaseKampus {
    public static final String DBName = "Kampus.db";
    public static final int DBVer = 1;
    private SkemaDatabaseKampus() {
    }
    public abstract class TableMahasiswa implements BaseColumns{
        public static final String TableName = "Mahasiswa";
        public static final String ColumnNim = "Nim";
        public static final String ColumnNama = "Nama";
        public static final String ColumnAlamat = "Alamat";
        public static final String ColumnJK = "JK";
        public static final String ColumnAgama = "Agama";
        public static final String ColumnTglLhr = "TglLhr";
    }
}
