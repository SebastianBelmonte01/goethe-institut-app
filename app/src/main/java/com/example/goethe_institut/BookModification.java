package com.example.goethe_institut;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookModification#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookModification extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText bookCodigo, bookTitle, bookAuthor, bookCantidad, bookDireccionImagen;
    Button btnAtras, btnModificar;
    Books books;

    Book book;



    public BookModification() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookModification.
     */
    // TODO: Rename and change types and number of parameters
    public static BookModification newInstance(String param1, String param2) {
        BookModification fragment = new BookModification();
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
        View v = inflater.inflate(R.layout.fragment_book_modification, container, false);

        bookTitle = v.findViewById(R.id.bookTitle);
        bookAuthor = v.findViewById(R.id.bookAuthor);
        bookCantidad = v.findViewById(R.id.bookCantidad);
        bookDireccionImagen = v.findViewById(R.id.bookDireccionImagen);
        btnAtras = v.findViewById(R.id.btnAtras);
        btnModificar = v.findViewById(R.id.btnModificar);

        System.out.println(book.toString());
        bookTitle.setText(book.getTitulo());
        bookAuthor.setText(book.getAutor());
        bookCantidad.setText(book.getCantidad() + "");


        btnAtras.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                books = new Books();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.lilaContainer, books).commit();
            }
        });

        btnModificar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(!bookTitle.getText().toString().equals("") &&
                        !bookAuthor.getText().toString().equals("") && !bookCantidad.getText().toString().equals("") &&
                        !bookDireccionImagen.getText().toString().equals("")){

                    DataBaseHelper db = new DataBaseHelper(getContext());
                    db.updateBook(new Book(book.getCodigo(),bookTitle.getText().toString(),
                            bookAuthor.getText().toString(), Integer.parseInt(bookCantidad.getText().toString()), bookDireccionImagen.getText().toString(), null));


                }
                else if(!bookTitle.getText().toString().equals("") &&
                        !bookAuthor.getText().toString().equals("") && !bookCantidad.getText().toString().equals("") &&
                        bookDireccionImagen.getText().toString().equals("")){

                    DataBaseHelper db = new DataBaseHelper(getContext());
                    db.updateBook(new Book(book.getCodigo(),bookTitle.getText().toString(),
                            bookAuthor.getText().toString(), Integer.parseInt(bookCantidad.getText().toString()), null, null));

                }

            }
        });



        return v;
    }

    public void setBook(Book b){
        this.book = b;
    }
}