package com.heraya.aplikasimahasiswa.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.heraya.aplikasimahasiswa.MainActivity;
import com.heraya.aplikasimahasiswa.R;
import com.heraya.aplikasimahasiswa.adapter.MahasiswaAdapter;
import com.heraya.aplikasimahasiswa.dao.MahasiswaDao;
import com.heraya.aplikasimahasiswa.domain.Mahasiswa;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class TampilMahasiswaFragment extends Fragment {
    private EditText etTglLhr;
    Context ctx;
    private SimpleDateFormat formatter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fr_tampil_mahasiswa,container,false);
        final ListView lvMhs = rootView.findViewById(R.id.lvDaftarMahasiswa);
        final MahasiswaDao dataMahasiswa = new MahasiswaDao(getContext());
        final MahasiswaAdapter adapter = new MahasiswaAdapter(getContext(),R.layout.lv_daftar_mahasiswa,dataMahasiswa.semuaData());
        final SwipeRefreshLayout swRefresh = rootView.findViewById(R.id.swRefresh);
        adapter.notifyDataSetChanged();
        swRefresh.setColorSchemeResources(R.color.colorAccent,R.color.colorPrimaryDark);
        swRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swRefresh.setRefreshing(false);
                    }
                },1000);
            }
        });
        lvMhs.setAdapter(adapter);
        lvMhs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final MahasiswaAdapter ma = new MahasiswaAdapter(getContext(),position,dataMahasiswa.semuaData());
                final Mahasiswa m = ma.getItem(position);
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                final View fr_edit = LayoutInflater.from(getContext()).inflate(R.layout.fr_edit_mahasiswa,parent,false);
                builder.setTitle("Options")
                        .setPositiveButton("Ubah", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final EditText etNim = fr_edit.findViewById(R.id.etNIM);
                                final EditText etNama = fr_edit.findViewById(R.id.etNama);
                                final EditText etAlamat = fr_edit.findViewById(R.id.etAlamat);
                                final RadioGroup rdjk = fr_edit.findViewById(R.id.rdJK);
                                final Spinner spAgama = fr_edit.findViewById(R.id.spAgama);
                                etNim.setText(m.getNim());
                                etNama.setText(m.getNama());
                                etAlamat.setText(m.getAlamat());
                                Log.d("Tag",m.getAgama());
                                if(m.getJk().equals("Laki - Laki")){
                                    rdjk.check(R.id.rdLk);
                                }else{
                                    rdjk.check(R.id.rdPr);
                                }
                                final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(fr_edit.getContext(), R.array.agama, android.R.layout.simple_spinner_item);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spAgama.setAdapter(adapter);
                                if (m.getAgama() != null) {
                                    int spinnerPosition = adapter.getPosition(m.getAgama());
                                    spAgama.setSelection(spinnerPosition);
                                }
                                etTglLhr = fr_edit.findViewById(R.id.etTglLhr);
                                etTglLhr.setText(m.getTgllhr());
                                formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                                etTglLhr.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        showCalenderDialog();
                                    }
                                });
                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                builder.setView(fr_edit)
                                        .setPositiveButton("Ubah", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                String nim = etNim.getText().toString();
                                                String nama = etNama.getText().toString();
                                                String alamat = etAlamat.getText().toString();
                                                int jk = rdjk.getCheckedRadioButtonId();
                                                RadioButton gender = fr_edit.findViewById(jk);
                                                String agama = spAgama.getSelectedItem().toString();
                                                String tglLhr = etTglLhr.getText().toString();
                                                String txtjk = gender.getText().toString();
                                                if(TextUtils.isEmpty(nim)||TextUtils.isEmpty(nama)||TextUtils.isEmpty(alamat)||agama.equals("--Pilih--")||TextUtils.isEmpty(tglLhr)){
                                                    Toast.makeText(getContext(),"Semua data harus diisi",Toast.LENGTH_SHORT).show();
                                                }
                                                else {
                                                    MahasiswaDao md = new MahasiswaDao(getContext());
                                                    Mahasiswa m = new Mahasiswa(nim,nama,alamat,txtjk,agama,tglLhr);
                                                    md.updateData(m,nim);
                                                    Toast.makeText(getContext(),"Data Telah Diubah",Toast.LENGTH_SHORT).show();
                                                    dialog.dismiss();
                                                }
                                            }
                                        })
                                        .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                AlertDialog dialog1 = builder.create();
                                dialog1.show();
                            }
                        })
                        .setNegativeButton("Hapus", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                                builder1.setTitle("Konfirmasi")
                                        .setMessage("Anda yakin ingin menghapusnya ? ")
                                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                MahasiswaDao md = new MahasiswaDao(getContext());
                                                md.deleteData(m.getNim());
                                                dialog.dismiss();
                                                Toast.makeText(getContext(),m.getNama()+" telah dihapus",Toast.LENGTH_SHORT).show();
                                            }
                                        }).setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                AlertDialog dialog1 = builder1.create();
                                dialog1.show();
                            }
                        })
                        .setNeutralButton("Batal", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
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
