package com.injector.document_manager.ui.fragments.terminos_politicas;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.injector.document_manager.R;

public class TerminosPoliticasFragment extends Fragment {

    private TerminosPoliticasViewModel mViewModel;

    public static TerminosPoliticasFragment newInstance() {
        return new TerminosPoliticasFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_terminos_politicas, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TerminosPoliticasViewModel.class);
        // TODO: Use the ViewModel
    }

}