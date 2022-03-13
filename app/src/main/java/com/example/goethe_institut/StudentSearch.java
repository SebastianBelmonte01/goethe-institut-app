package com.example.goethe_institut;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudentSearch#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentSearch extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    StudentInformation studentInformation;
    Button btnAtras, btnBuscar;
    EditText etCedula;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public StudentSearch() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudentSearch.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentSearch newInstance(String param1, String param2) {
        StudentSearch fragment = new StudentSearch();
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
        View v = inflater.inflate(R.layout.fragment_student_search, container, false);
        btnAtras = v.findViewById(R.id.btnAtras);
        btnBuscar = v.findViewById(R.id.btnBuscar);
        etCedula = v.findViewById(R.id.etCedula);

        DataBaseHelper db = new DataBaseHelper(v.getContext());


        btnBuscar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                if(etCedula.getText().toString() == ""){
                    Toast.makeText(v.getContext(),"Debe ingresar un CI", Toast.LENGTH_SHORT).show();
                }
                else{
                    Persona p = db.searchPerson(etCedula.getText().toString());
                    if(p == null){
                        Toast.makeText(v.getContext(),"No existe el estudiante buscado", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        System.out.println("HOLA");
                        studentInformation = new StudentInformation();
                        studentInformation.setPerson(p);
                        studentInformation.setPosition(db.searchCourse(etCedula.getText().toString()));
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction().replace(R.id.studentInformation, studentInformation );
                        fragmentTransaction.commit();
                    }

                }

            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(v.getContext(), PrincipalMenu.class);
                startActivity(intent);
            }
        });
        //getSupportFragmentManager().beginTransaction().replace(R.id.busquedaPrincipal, studentSearch).commit();

        // Inflate the layout for this fragment
        return v;
    }
}