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
 * Use the {@link AddMaterial#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddMaterial extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button btnEnviar, btnAtras;
    MaterialAdministration materialAdministration;
    EditText materialCodigo, materialTipo, materialCosto, materialCantidad, materialDescripcion, materialDireccionImagen;


    public AddMaterial() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EliminarMaterial.
     */
    // TODO: Rename and change types and number of parameters
    public static AddMaterial newInstance(String param1, String param2) {
        AddMaterial fragment = new AddMaterial();
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

        View v = inflater.inflate(R.layout.fragment_add_material, container, false);
        btnEnviar = v.findViewById(R.id.btnEnviar);
        btnAtras = v.findViewById(R.id.btnAtras);

        materialCodigo = v.findViewById(R.id.materialCodigo);
        materialTipo = v.findViewById(R.id.materialTipo);
        materialCosto = v.findViewById(R.id.materialCosto);
        materialCantidad = v.findViewById(R.id.materialCantidad);
        materialDescripcion = v.findViewById(R.id.materialDescripcion);
        materialDireccionImagen = v.findViewById(R.id.materialDireccionImagen);




        btnEnviar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                if(!materialCodigo.getText().toString().equals("") && !materialTipo.getText().toString().equals("") && !materialCosto.getText().toString().equals("") &&
                !materialCantidad.getText().toString().equals("") && !materialCantidad.getText().toString().equals("") && !materialDescripcion.getText().toString().equals("") && !materialDireccionImagen.getText().toString().equals("")){
                   try {
                       Material material = new Material(Integer.parseInt(materialCodigo.getText().toString()), materialTipo.getText().toString(), Double.parseDouble(materialCosto.getText().toString()), Integer.parseInt(materialCantidad.getText().toString()), materialDireccionImagen.getText().toString(), materialDescripcion.getText().toString(), null);
                       DataBaseHelper db = new DataBaseHelper(v.getContext());
                       db.insertMaterial(material);

                   }
                   catch (Exception e){
                       Toast.makeText(getContext(),"Error de tipo " + e, Toast.LENGTH_SHORT).show();
                   }
                }

                //    EditText materialCodigo, materialTipo, materialCosto, materialCantidad, materialDescripcion, materialDireccionImagen;

                Intent intent = new Intent(v.getContext() , PrincipalMenu.class);
                startActivity(intent);

            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                materialAdministration = new MaterialAdministration();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.lilaContainer, materialAdministration).commit();
            }
        });



        return v;
    }
}