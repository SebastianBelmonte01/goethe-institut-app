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
 * Use the {@link BookAdd#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookAdd extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText bookCodigo, bookTitle, bookAuthor, bookCantidad, bookDireccionImagen;
    Button btnAtras, btnEnviar;

    public BookAdd() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookAdd.
     */
    // TODO: Rename and change types and number of parameters
    public static BookAdd newInstance(String param1, String param2) {
        BookAdd fragment = new BookAdd();
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
        View v = inflater.inflate(R.layout.fragment_book_add, container, false);
        bookCodigo = v.findViewById(R.id.bookCodigo);
        bookTitle = v.findViewById(R.id.bookTitle);
        bookAuthor = v.findViewById(R.id.bookAuthor);
        bookCantidad = v.findViewById(R.id.bookCantidad);
        bookDireccionImagen = v.findViewById(R.id.bookDireccionImagen);
        btnAtras = v.findViewById(R.id.btnAtras);
        btnEnviar = v.findViewById(R.id.btnEnviar);


        btnAtras.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.lilaContainer, new Books());
            }
        });

        btnEnviar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(!bookCodigo.getText().toString().equals("") && !bookTitle.getText().toString().equals("") &&
                !bookAuthor.getText().toString().equals("") && !bookCantidad.getText().toString().equals("") &&
                !bookDireccionImagen.getText().toString().equals("")){
                    try{
                        Book book = new Book(bookCodigo.getText().toString(),bookTitle.getText().toString(), bookAuthor.getText().toString(), Integer.parseInt(bookCantidad.getText().toString()), bookDireccionImagen.getText().toString(), null);
                        DataBaseHelper db = new DataBaseHelper(v.getContext());
                        db.insertBook(book);
                    }catch (Exception e){
                        Toast.makeText(getContext(),"Error de tipo " + e, Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        return v;
    }
}