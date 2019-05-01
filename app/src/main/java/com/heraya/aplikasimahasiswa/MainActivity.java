package com.heraya.aplikasimahasiswa;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.heraya.aplikasimahasiswa.adapter.MahasiswaAdapter;
import com.heraya.aplikasimahasiswa.dao.MahasiswaDao;
import com.heraya.aplikasimahasiswa.domain.Mahasiswa;
import com.heraya.aplikasimahasiswa.fragment.DashboardFragment;
import com.heraya.aplikasimahasiswa.fragment.TambahMahasiswaFragment;
import com.heraya.aplikasimahasiswa.fragment.TampilMahasiswaFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.flEmpty, new DashboardFragment()).commit();
//        insertDummy();
    }

    private void insertDummy() {
        MahasiswaDao md = new MahasiswaDao(this);
        Mahasiswa m = new Mahasiswa("17.12.0100", "Heraya Fitra", "Banjarnegara", "Laki", "Islam", "07/01/2000");
        md.insertData(m);
    }

    private void loadFragmentaddback(Fragment fr) {
        getSupportFragmentManager().beginTransaction().replace(R.id.flEmpty, fr).addToBackStack(null).commit();
    }

    public void btnTampil(View view) {
        loadFragmentaddback(new TampilMahasiswaFragment());
    }

    public void btnTambah(View view) {
        loadFragmentaddback(new TambahMahasiswaFragment());
    }

    public void btnKeluar(View view) {
        finish();
    }
}
