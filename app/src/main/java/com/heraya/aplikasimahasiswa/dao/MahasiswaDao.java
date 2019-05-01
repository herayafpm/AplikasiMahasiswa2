package com.heraya.aplikasimahasiswa.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.heraya.aplikasimahasiswa.domain.Mahasiswa;

import java.util.ArrayList;
import java.util.List;

public class MahasiswaDao {
    private Context ctx;
    MahasiswaDBHelper dbHelper;
    SQLiteDatabase db;

    public MahasiswaDao(Context ctx) {
        this.ctx = ctx;
    }
    public List<Mahasiswa> semuaData(){
        List<Mahasiswa> dataMahasiswa = new ArrayList<>();
        dbHelper = new MahasiswaDBHelper(ctx);
        db = dbHelper.getReadableDatabase();
        String[] DaftarKolom = {
          SkemaDatabaseKampus.TableMahasiswa.ColumnNim,
          SkemaDatabaseKampus.TableMahasiswa.ColumnNama,
          SkemaDatabaseKampus.TableMahasiswa.ColumnAlamat,
          SkemaDatabaseKampus.TableMahasiswa.ColumnJK,
          SkemaDatabaseKampus.TableMahasiswa.ColumnAgama,
          SkemaDatabaseKampus.TableMahasiswa.ColumnTglLhr
        };
        String urutan = SkemaDatabaseKampus.TableMahasiswa.ColumnNim+" DESC";
        Cursor c = db.query(SkemaDatabaseKampus.TableMahasiswa.TableName,DaftarKolom,null,null,null,null,urutan);
        if(c.moveToFirst()){
            do{
                Mahasiswa m = new Mahasiswa();
                m.setNim(c.getString(0));
                m.setNama(c.getString(1));
                m.setAlamat(c.getString(2));
                m.setJk(c.getString(3));
                m.setAgama(c.getString(4));
                m.setTgllhr(c.getString(5));
                dataMahasiswa.add(m);
            }while (c.moveToNext());
        }
        c.close();
        return dataMahasiswa;
    }
    public int insertData(Mahasiswa m){
        try {
            dbHelper = new MahasiswaDBHelper(ctx);
            db = dbHelper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(SkemaDatabaseKampus.TableMahasiswa.ColumnNim,m.getNim());
            cv.put(SkemaDatabaseKampus.TableMahasiswa.ColumnNama,m.getNama());
            cv.put(SkemaDatabaseKampus.TableMahasiswa.ColumnAlamat,m.getAlamat());
            cv.put(SkemaDatabaseKampus.TableMahasiswa.ColumnJK,m.getJk());
            cv.put(SkemaDatabaseKampus.TableMahasiswa.ColumnAgama,m.getAgama());
            cv.put(SkemaDatabaseKampus.TableMahasiswa.ColumnTglLhr,m.getTgllhr());
            db.insertOrThrow(SkemaDatabaseKampus.TableMahasiswa.TableName,null,cv);
            db.close();
            return 1;
        }
        catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    public void updateData(Mahasiswa m,String nim){
        dbHelper = new MahasiswaDBHelper(ctx);
        db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(SkemaDatabaseKampus.TableMahasiswa.ColumnNama,m.getNama());
        cv.put(SkemaDatabaseKampus.TableMahasiswa.ColumnAlamat,m.getAlamat());
        cv.put(SkemaDatabaseKampus.TableMahasiswa.ColumnJK,m.getJk());
        cv.put(SkemaDatabaseKampus.TableMahasiswa.ColumnAgama,m.getAgama());
        cv.put(SkemaDatabaseKampus.TableMahasiswa.ColumnTglLhr,m.getTgllhr());
        db.update(SkemaDatabaseKampus.TableMahasiswa.TableName,cv,SkemaDatabaseKampus.TableMahasiswa.ColumnNim+"='"+nim+"'",null);
        db.close();
    }
    public void deleteData(String nim){
        dbHelper = new MahasiswaDBHelper(ctx);
        db = dbHelper.getWritableDatabase();
        db.delete(SkemaDatabaseKampus.TableMahasiswa.TableName,SkemaDatabaseKampus.TableMahasiswa.ColumnNim+" = '"+nim+"'",null);
        db.close();
    }
}
