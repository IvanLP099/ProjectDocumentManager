package com.injector.document_manager.ui.fragments.documento;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.injector.document_manager.R;
import com.injector.document_manager.databinding.ActivityNavigationDrawerBinding;
import com.injector.document_manager.ui.activities.NavigationDrawer;

public class DocumentosFragment extends Fragment {

    private DocumentosViewModel mViewModel;
    private ActivityNavigationDrawerBinding binding;

    private ExtendedFloatingActionButton btnAddActionsButtons;
    private FloatingActionButton btnRegisterDocument, btnImportDocument;
    private TextView txtRegisterDocument, txtImportDocument;

    View view;
    public static DocumentosFragment newInstance() {
        return new DocumentosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_documento,container,false);


        btnAddActionsButtons = view.findViewById(R.id.btn_actions);
        btnRegisterDocument = view.findViewById(R.id.btn_registerDocument);
        btnImportDocument = view.findViewById(R.id.btn_importDocument);

        txtImportDocument = view.findViewById(R.id.txt_import);
        txtRegisterDocument = view.findViewById(R.id.txt_registrar);

        btnAddActionsButtons.shrink();
        btnRegisterDocument.hide();
        btnImportDocument.hide();
        txtRegisterDocument.setVisibility(View.GONE);
        txtImportDocument.setVisibility(View.GONE);

        // Establecer listener de clic para el botón que contiene los flotantes
        btnAddActionsButtons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mostrar u ocultar los botones secundarios
                /*if (btnRegisterDocument.getVisibility() == View.VISIBLE) {
                    btnRegisterDocument.setVisibility(View.GONE);
                    btnImportDocument.setVisibility(View.GONE);
                } else {
                    btnRegisterDocument.setVisibility(View.VISIBLE);
                    btnImportDocument.setVisibility(View.VISIBLE);
                }*/
                expandirBotones(v);
            }
        });

        // Establecer listeners de clic para los botones flotantes secundarios
        btnRegisterDocument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción para navegar hacia el fragmento de registro de documentos
                //NavHostFragment.findNavController(DocumentosFragment.this)
                    //    .navigate(R.id.action_nav_documento_to_nav_agregar_documento);
                NavController navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment_content_navigation_drawer);
                navController.navigate(R.id.action_nav_documento_to_nav_agregar_documento);
            }

        });

        btnImportDocument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acciones cuando se presiona btnImportDocument
                // Por ejemplo, abrir una nueva actividad, fragmento, etc.
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DocumentosViewModel.class);
        // TODO: Use the ViewModel
    }

    public void expandirBotones(View view){
        boolean clicked = btnRegisterDocument.getVisibility() == View.VISIBLE;
        if(!clicked){
            btnRegisterDocument.show();
            btnImportDocument.show();
            txtRegisterDocument.setVisibility(view.VISIBLE);
            txtImportDocument.setVisibility(view.VISIBLE);
            btnAddActionsButtons.extend();
            //Toast.makeText(NavigationDrawer.this, "¡Hola, mundo!", Toast.LENGTH_SHORT).show();
            clicked = true;


        }else{
            btnRegisterDocument.hide();
            btnImportDocument.hide();
            txtRegisterDocument.setVisibility(view.GONE);
            txtImportDocument.setVisibility(view.GONE);
            btnAddActionsButtons.shrink();
            clicked = false;

        }

    }

}