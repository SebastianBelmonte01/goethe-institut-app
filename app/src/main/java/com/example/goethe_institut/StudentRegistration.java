package com.example.goethe_institut;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudentRegistration#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentRegistration extends Fragment {

    Button btnEnviar, btnAtras;
    EditText ci, nombre, lastName, direccion, telefono, fechaNacimiento;
    Spinner curso;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StudentRegistration() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudentRegistration.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentRegistration newInstance(String param1, String param2) {
        StudentRegistration fragment = new StudentRegistration();
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

        View v = inflater.inflate(R.layout.fragment_student_registration, container, false);
        String [] values =
                {"A1.1","A1.2","A2.1","A2.2","B1.1","B1.2","B2.1","B2.2","C1.1","C1.2","C2.1","C2.2"};
        Spinner spinner = v.findViewById(R.id.spnCursos);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        // Inflate the layout for this fragment

        btnEnviar = (Button) v.findViewById(R.id.btnEnviar);
        btnAtras = v.findViewById(R.id.btnAtras);
        //EditText ci, nombre, lastName, direccion, telefono, fechaNacimiento, curso;

        ci = v.findViewById(R.id.ci);
        nombre = v.findViewById(R.id.nombre);
        lastName = v.findViewById(R.id.apellido);
        direccion = v.findViewById(R.id.direccion);
        telefono = v.findViewById(R.id.telefono);
        fechaNacimiento = v.findViewById(R.id.fechaNacimiento);
        curso = v.findViewById(R.id.spnCursos);


        DataBaseHelper db = new DataBaseHelper(v.getContext());



        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ci!=null && nombre!=null && lastName!=null && direccion!=null && telefono!=null && fechaNacimiento!=null && curso != null){
                    //System.out.println("LE DISTE CLICK A LA OPCION: " + curso.getSelectedItemPosition());
                    Persona persona = new Persona(ci.getText().toString(), nombre.getText().toString(), lastName.getText().toString(), direccion.getText().toString(), telefono.getText().toString(), fechaNacimiento.getText().toString(), "estudiante", 1234, 0 );
                    db.insertPersona(persona);
                    db.insertPersonCourses(persona.getCi(), curso.getSelectedItemPosition());
                    Intent intent = new Intent(v.getContext(), PrincipalMenu.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(v.getContext(), "Debe ingresar datos en todos los campos", Toast.LENGTH_SHORT);
                }


            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(v.getContext() , PrincipalMenu.class);
                startActivity(intent);
            }
        });



        return v;
    }
}