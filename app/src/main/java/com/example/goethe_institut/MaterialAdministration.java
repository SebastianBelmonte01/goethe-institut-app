package com.example.goethe_institut;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MaterialAdministration#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MaterialAdministration extends Fragment {

    ArrayList<ModeloItemImagen> lista;
    AdaptadorImagen adaptador;
    RecyclerView rvlista;
    Button btnAtras, btnModificar, btnAnadir;


    AddMaterial addMaterial;
    ModifyMaterial modifyMaterial;

    ArrayList<Integer> idList = new ArrayList<>();

    MaterialAdministration mad;


    //AddMaterial

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MaterialAdministration() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MaterialAdministration.
     */
    // TODO: Rename and change types and number of parameters
    public static MaterialAdministration newInstance(String param1, String param2) {
        MaterialAdministration fragment = new MaterialAdministration();
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
        View v = inflater.inflate(R.layout.fragment_material_administration, container, false);
        lista = new ArrayList<>();
        adaptador = new AdaptadorImagen(lista);
        rvlista = v.findViewById(R.id.rvMaterials);
        loadMaterials();
        rvlista.setAdapter(adaptador);
        rvlista.setLayoutManager(new LinearLayoutManager(v.getContext(),RecyclerView.VERTICAL,false));

        btnAtras = v.findViewById(R.id.btnAtras);
        btnAnadir = v.findViewById(R.id.btnAnadir);
        btnModificar = v.findViewById(R.id.btnModificar);

        rvlista.addOnItemTouchListener(new Toques(v.getContext(), rvlista, new Toques.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("¿Está Seguro?");
                builder.setMessage("Está seguro de eliminar el material...");
                builder.setCancelable(false);
                builder.setPositiveButton("Sí", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //TODO CREAR UINA FUNCION PARA ELIMINAR
                        int id = idList.get(position);
                        System.out.println(id);
                        DataBaseHelper db = new DataBaseHelper(getContext());
                        db.deleteMaterial(id);
                        mad = new MaterialAdministration();
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.lilaContainer, mad).commit();



                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.show();



                System.out.println("LONG LONG CLICK EN " + position);

            }
        }));
        btnAtras.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(v.getContext() , PrincipalMenu.class);
                startActivity(intent);
            }
        });

        btnModificar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                modifyMaterial = new ModifyMaterial();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.lilaContainer, modifyMaterial).commit();



            }
        });

        btnAnadir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                addMaterial = new AddMaterial();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.lilaContainer, addMaterial).commit();
            }
        });




        return v;
    }

    void loadMaterials(){
        DataBaseHelper db = new DataBaseHelper(getContext());
        for(Material m : db.selectMaterials() ){
            lista.add(new ModeloItemImagen(m.getImage(), m.getType(), m.getDescription() +  "\n ID: " + m.getId() + "\n Cantidad: " + m.getCantidad() + "\n Costo " + m.getCost()));
            idList.add(m.getId());
        }
        db.selectMaterials();
    }
}