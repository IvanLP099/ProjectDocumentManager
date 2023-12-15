package com.injector.document_manager.ui.fragments.documento;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.injector.document_manager.R;

public class AgregarDocumentoFragment extends Fragment {

    private AgregarDocumentoViewModel mViewModel;

    public static AgregarDocumentoFragment newInstance() {
        return new AgregarDocumentoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_agregar_documento, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AgregarDocumentoViewModel.class);
        // TODO: Use the ViewModel
    }

}