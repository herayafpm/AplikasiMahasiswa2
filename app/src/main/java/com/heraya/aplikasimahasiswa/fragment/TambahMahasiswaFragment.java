package com.heraya.aplikasimahasiswa.fragment;

import android.app.ActivityOptions;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.heraya.aplikasimahasiswa.MainActivity;
import com.heraya.aplikasimahasiswa.R;
import com.heraya.aplikasimahasiswa.dao.MahasiswaDao;
import com.heraya.aplikasimahasiswa.domain.Mahasiswa;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TambahMahasiswaFragment extends Fragment {
    private EditText etTglLhr;
    private SimpleDateFormat formatter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fr_form_mahasiswa,container,false);
        final EditText etNim = rootView.findViewById(R.id.etNIM);
        final EditText etNama = rootView.findViewById(R.id.etNama);
        final EditText etAlamat = rootView.findViewById(R.id.etAlamat);
        final RadioGroup rdjk = rootView.findViewById(R.id.rdJK);
        final Spinner spAgama = rootView.findViewById(R.id.spAgama);
        etTglLhr = rootView.findViewById(R.id.etTglLhr);
        formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        etTglLhr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalenderDialog();
            }
        });
        Button btnSimpan = rootView.findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String nim = etNim.getText().toString();
                 String nama = etNama.getText().toString();
                 String alamat = etAlamat.getText().toString();
                int jk = rdjk.getCheckedRadioButtonId();
                RadioButton gender = rootView.findViewById(jk);
                 String agama = spAgama.getSelectedItem().toString();
                 String tglLhr = etTglLhr.getText().toString();
                 String txtjk = gender.getText().toString();
                if(TextUtils.isEmpty(nim)||TextUtils.isEmpty(nama)||TextUtils.isEmpty(alamat)||agama == "--Pilih--"||TextUtils.isEmpty(tglLhr)){
                    Toast.makeText(getContext(),"Semua data harus diisi",Toast.LENGTH_SHORT).show();
                }
                else {
                    MahasiswaDao md = new MahasiswaDao(getContext());
                    Mahasiswa m = new Mahasiswa(nim,nama,alamat,txtjk,agama,tglLhr);
                    int i = md.insertData(m);
                    if(i == 0){
                        Toast.makeText(getContext(),"Gunakan NIM yang lain!",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Intent intent = new Intent(getContext(),MainActivity.class);
                        getActivity().finish();
                        Toast.makeText(getContext(),"Data berhasil ditambahkan",Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                }
            }
        });
        Button btnBatal = rootView.findViewById(R.id.btnBatal);
        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),MainActivity.class);
                getActivity().finish();
                startActivity(intent);
            }
        });
        return rootView;
    }
    public void showCalenderDialog(){
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year,month,dayOfMonth);
                etTglLhr.setText(formatter.format(newDate.getTime()));
            }
        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}
