package com.example.goethe_institut;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ModifyMaterial#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModifyMaterial extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button btnBuscar, btnAtras;
    MaterialAdministration materialAdministration;
    EditText materialCodigo;
    MaterialInformationSearch materialInformationSearch;

    public ModifyMaterial() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ModifyMaterial.
     */
    // TODO: Rename and change types and number of parameters
    public static ModifyMaterial newInstance(String param1, String param2) {
        ModifyMaterial fragment = new ModifyMaterial();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_modify_material, container, false);

        btnBuscar = v.findViewById(R.id.btnBuscar);
        btnAtras = v.findViewById(R.id.btnAtras);
        materialCodigo = v.findViewById(R.id.materialCodigo);


        btnAtras.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                materialAdministration = new MaterialAdministration();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.lilaContainer, materialAdministration).commit();


            }
        });

        btnBuscar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                int id = 0;
                if(!materialCodigo.getText().toString().equals("")){
                    try {
                        id = Integer.parseInt(materialCodigo.getText().toString());
                    }catch (NumberFormatException e){
                        Toast.makeText(v.getContext(), "Debe ingresar un código existente", Toast.LENGTH_SHORT).show();
                    }

                    DataBaseHelper db = new DataBaseHelper(v.getContext());
                    if(db.selectMaterial(id) != null){
                        materialInformationSearch = new MaterialInformationSearch();
                        materialInformationSearch.setMaterial(db.selectMaterial(id));
                        materialInformationSearch.setMaterials(db.selectMaterials());
                        getFragmentManager().beginTransaction().replace(R.id.materialForm, materialInformationSearch).commit();

                    }
                    else{
                        Toast.makeText(v.getContext(), "No se encontro el material con dicho código", Toast.LENGTH_SHORT).show();

                    }

                    //TODO BUSCAR EL ID EN LA BASE DE DATOS
                    //TODO SI EXISTE EL CODIGO CREAR UN CONSTRUCTOR PARA EL FRAGMENT
                    //SI NO EXISTE LAZAR EXCEPTION


                }

            }
        });

        return v;
    }
}