package com.example.goethe_institut;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudentInformation#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentInformation extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    EditText nombre, apellido, direccion, telefono;
    Spinner cursos;
    Button btnModificar;
    Persona person;
    Integer position;



    public StudentInformation() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudentInformation.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentInformation newInstance(String param1, String param2) {
        StudentInformation fragment = new StudentInformation();
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_student_information, container, false);

        //EditText nombre, apellido, direccion, telefono;
        nombre = v.findViewById(R.id.nombre);
        apellido = v.findViewById(R.id.apellido);
        direccion = v.findViewById(R.id.direccion);
        telefono = v.findViewById(R.id.telefono);

        btnModificar = v.findViewById(R.id.btnModificar);

        Spinner spinner = v.findViewById(R.id.spnCursos);

        HashMap<Integer, String> map = new HashMap<Integer, String>();

        List<String> coursesList = new ArrayList<>();
        coursesList.add("A1.1");
        coursesList.add("A1.2");
        coursesList.add("A2.1");
        coursesList.add("A2.2");
        coursesList.add("B1.1");
        coursesList.add("B1.2");
        coursesList.add("B2.1");
        coursesList.add("B2.2");
        coursesList.add("C1.1");
        coursesList.add("C1.2");
        coursesList.add("C2.1");
        coursesList.add("C2.2");



        try {
            nombre.setText(this.person.getName());
            apellido.setText(this.person.getLastName());
            direccion.setText(this.person.getDirection());
            telefono.setText(this.person.getTelefono());

            map.put(0,"A1.1");
            map.put(1,"A1.2" );
            map.put(2, "A2.1");
            map.put(3,"A2.2");
            map.put(4,"B1.1");
            map.put(5,"B1.2");
            map.put(6,"B2.1");
            map.put(7, "B2.2");
            map.put(8,"C1.1");
            map.put(9,"C1.2");
            map.put(10,"C2.1");
            map.put(11,"C2.2");

            Vector<String> values = getValues(map);


            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            spinner.setAdapter(adapter);

        }catch (Exception e){
            Toast.makeText(getContext(), "Ocurrio un error", Toast.LENGTH_SHORT).show();
        }
        DataBaseHelper db = new DataBaseHelper(v.getContext());


        btnModificar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Persona p = new Persona(person.getCi(), nombre.getText().toString(), apellido.getText().toString(), direccion.getText().toString(), telefono.getText().toString(), null, null, 0, 0);
                db.updatePerson(p);

                db.updatePersonCourse(person.getCi(), coursesList.indexOf(spinner.getSelectedItem()));
                Toast.makeText(v.getContext(), "Modificación realizada", Toast.LENGTH_SHORT).show();
                RelativeLayout newStudentForm;
                newStudentForm = v.findViewById(R.id.newStudentForm);
                newStudentForm.setVisibility(View.GONE);
            }
        });




        return v;
    }

    public void setPerson(Persona p){
       this.person = p;

    }

    public void setPosition(Integer pos){
        System.out.println("POSITION: " + pos);
        this.position = pos;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Vector<String> getValues(HashMap<Integer, String> map){
        Vector<String> values = new Vector<>();
        values.add(map.get(position));
        map.remove(position);

        map.forEach((key, value) ->{
            values.add(value);
        });

        return values;
    }
}