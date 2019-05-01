package com.heraya.aplikasimahasiswa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.heraya.aplikasimahasiswa.R;
import com.heraya.aplikasimahasiswa.domain.Mahasiswa;

import java.util.List;

public class MahasiswaAdapter extends ArrayAdapter<Mahasiswa> {
    public MahasiswaAdapter(Context context, int resource, List<Mahasiswa> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position,View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.lv_daftar_mahasiswa,parent,false);
        }
        TextView tvNama = convertView.findViewById(R.id.tvNama);
        TextView tvNim = convertView.findViewById(R.id.tvNim);
        TextView tvAlamat = convertView.findViewById(R.id.tvAlamat);
        TextView tvJK = convertView.findViewById(R.id.tvJK);
        TextView tvAgama = convertView.findViewById(R.id.tvAgama);
        TextView tvTglLhr = convertView.findViewById(R.id.tvTglLhr);
        Mahasiswa m = getItem(position);
        tvNama.setText(m.getNama());
        tvNim.setText(m.getNim());
        tvAlamat.setText(m.getAlamat());
        tvJK.setText(m.getJk());
        tvAgama.setText(m.getAgama());
        tvTglLhr.setText(m.getTgllhr());

        return convertView;
    }
}
