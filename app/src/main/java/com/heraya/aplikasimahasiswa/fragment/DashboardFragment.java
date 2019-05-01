package com.heraya.aplikasimahasiswa.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.heraya.aplikasimahasiswa.R;
import com.heraya.aplikasimahasiswa.adapter.MahasiswaAdapter;
import com.heraya.aplikasimahasiswa.dao.MahasiswaDao;
public class DashboardFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fr_dashboard,container,false);
        return rootView;
    }
}
