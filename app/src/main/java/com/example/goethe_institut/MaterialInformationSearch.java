package com.example.goethe_institut;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MaterialInformationSearch#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MaterialInformationSearch extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText materialTipo, materialCosto, materialCantidad,materialDescripcion, materialDireccionImagen;
    Button btnModificar;

    private Material material;
    private ArrayList<Material> materials;


    public void setMaterial(Material material) {
        this.material = material;
    }

    public void setMaterials(ArrayList<Material> materials) {
        this.materials = materials;
    }

    public MaterialInformationSearch() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MaterialInformationSearch.
     */
    // TODO: Rename and change types and number of parameters
    public static MaterialInformationSearch newInstance(String param1, String param2) {
        MaterialInformationSearch fragment = new MaterialInformationSearch();
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
        View v = inflater.inflate(R.layout.fragment_material_information_search, container, false);
        materialTipo = v.findViewById(R.id.materialTipo);
        materialCosto = v.findViewById(R.id.materialCosto);
        materialCantidad = v.findViewById(R.id.materialCantidad);
        materialDescripcion = v.findViewById(R.id.materialDescripcion);
        materialDireccionImagen = v.findViewById(R.id.materialDireccionImagen);

        materialTipo.setText(material.getType());
        materialCosto.setText(material.getCost() + "");
        materialCantidad.setText(material.getCantidad() + "");
        materialDescripcion.setText(material.getDescription());

        ScrollView scrollView = v.findViewById(R.id.scrollview);


        btnModificar = v.findViewById(R.id.btnModificar);

        btnModificar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //TODO ESTE ES EL CASO DONDE TODOS LOS CAMPOS ESTAN COMPLETOS

                if(!materialTipo.getText().toString().equals("") && !materialCosto.getText().toString().equals("") && !materialCantidad.getText().toString().equals("")
                        && !materialDescripcion.getText().toString().equals("") && !materialDireccionImagen.getText().toString().equals("")){

                    try {
                        Material materialUpdate = new Material(material.getId(), materialTipo.getText().toString(), Double.parseDouble(materialCosto.getText().toString()), Integer.parseInt(materialCantidad.getText().toString()), materialDireccionImagen.getText().toString(), materialDescripcion.getText().toString(), null);
                        DataBaseHelper db = new DataBaseHelper(v.getContext());
                        /**
                         * db.updateMaterial(Material material, boolean changeImage)
                         */
                        db.updateMaterial(materialUpdate, true);
                        Toast.makeText(getContext(), "Se realizo la modificación con éxito", Toast.LENGTH_SHORT).show();
                        scrollView.setVisibility(View.GONE);

                    }
                    catch (Exception e){
                        Toast.makeText(getContext(),"Error de tipo " + e, Toast.LENGTH_SHORT).show();
                    }

                }
                //TODO ESTE ES EL CASO DONDE NO SE QUIERE CAMBIAR LA IMAGEN
                else if (!materialTipo.getText().toString().equals("") && !materialCosto.getText().toString().equals("") && !materialCantidad.getText().toString().equals("")
                        && !materialDescripcion.getText().toString().equals("") && materialDireccionImagen.getText().toString().equals("")){
                    try {
                        Material materialUpdate = new Material(material.getId(), materialTipo.getText().toString(), Double.parseDouble(materialCosto.getText().toString()), Integer.parseInt(materialCantidad.getText().toString()), materialDireccionImagen.getText().toString(), materialDescripcion.getText().toString(), null);
                        DataBaseHelper db = new DataBaseHelper(v.getContext());
                        /**
                         * db.updateMaterial(Material materialUpdate, boolean changeImage)
                         */
                        db.updateMaterial(materialUpdate, false);
                        Toast.makeText(getContext(), "Se realizo la modificación con éxito", Toast.LENGTH_SHORT).show();
                        scrollView.setVisibility(View.GONE);



                    }
                    catch (Exception e){
                        Toast.makeText(getContext(),"Error de tipo " + e, Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        return v;
    }
}