package com.heraya.aplikasimahasiswa.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MahasiswaDBHelper extends SQLiteOpenHelper {
    public MahasiswaDBHelper(Context context) {
        super(context, SkemaDatabaseKampus.DBName, null, SkemaDatabaseKampus.DBVer);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQLCreateTable = "CREATE TABLE "+SkemaDatabaseKampus.TableMahasiswa.TableName+"(" +
                SkemaDatabaseKampus.TableMahasiswa.ColumnNim+" TEXT PRIMARY KEY,"+
                SkemaDatabaseKampus.TableMahasiswa.ColumnNama+" TEXT ,"+
                SkemaDatabaseKampus.TableMahasiswa.ColumnAlamat+" TEXT,"+
                SkemaDatabaseKampus.TableMahasiswa.ColumnJK+" TEXT ,"+
                SkemaDatabaseKampus.TableMahasiswa.ColumnAgama+" TEXT ,"+
                SkemaDatabaseKampus.TableMahasiswa.ColumnTglLhr+" TEXT"+
                ")";
        db.execSQL(SQLCreateTable);
        String insertDataAwal = "INSERT INTO "+SkemaDatabaseKampus.TableMahasiswa.TableName+" (" +
                SkemaDatabaseKampus.TableMahasiswa.ColumnNim+","+
                SkemaDatabaseKampus.TableMahasiswa.ColumnNama+","+
                SkemaDatabaseKampus.TableMahasiswa.ColumnAlamat+","+
                SkemaDatabaseKampus.TableMahasiswa.ColumnJK+","+
                SkemaDatabaseKampus.TableMahasiswa.ColumnAgama+","+
                SkemaDatabaseKampus.TableMahasiswa.ColumnTglLhr+") VALUES ("+
                "'17.12.0100','Heraya Fitra','Banjaregara','Laki - Laki','Islam','07/01/2000'"+
                ")";
        db.execSQL(insertDataAwal);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQLDrop = "DROP TABLE IF EXISTS "+SkemaDatabaseKampus.TableMahasiswa.TableName;
        db.execSQL(SQLDrop);
        onCreate(db);
    }
}
