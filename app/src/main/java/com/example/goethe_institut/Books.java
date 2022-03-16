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
 * Use the {@link Books#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Books extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArrayList<ModeloItemImagen> lista;
    Button btnAtras, btnAnadir;
    RecyclerView rvMaterials;
    AdaptadorImagen adaptador;
    BookAdd bookAdd;
    ArrayList<Book> idList = new ArrayList<>();

    Books books;
    BookModification bookModification;


    public Books() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Books.
     */
    // TODO: Rename and change types and number of parameters
    public static Books newInstance(String param1, String param2) {
        Books fragment = new Books();
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
        View v = inflater.inflate(R.layout.fragment_books, container, false);

        rvMaterials = v.findViewById(R.id.rvMaterials);

        lista = new ArrayList<>();
        adaptador = new AdaptadorImagen(lista);
        loadBooks();

        rvMaterials.setAdapter(adaptador);
        rvMaterials.setLayoutManager(new LinearLayoutManager (v.getContext(), RecyclerView.VERTICAL, false));

        btnAtras = v.findViewById(R.id.btnAtras);
        btnAnadir = v.findViewById(R.id.btnAnadir);

        rvMaterials.addOnItemTouchListener(new Toques(v.getContext(), rvMaterials, new Toques.ClickListener(){

            @Override
            public void onClick(View view, int position) {
                System.out.println("Me diste click");
                //TODO LLEVAR A UN FRAGMENT PARA MODIFICAR
                bookModification = new BookModification();
                bookModification.setBook(idList.get(position));
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.lilaContainer, bookModification).commit();
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
                        //TODO ELIMINAR LIBRO
                        String id = idList.get(position).getCodigo();
                        DataBaseHelper db = new DataBaseHelper(getContext());
                        db.deleteBook(id);
                        books = new Books();
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.lilaContainer, books).commit();



                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();


            }
        }));

        btnAnadir.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                bookAdd = new BookAdd();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.lilaContainer, bookAdd).commit();
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

    public void loadBooks(){

        DataBaseHelper db = new DataBaseHelper(getContext());
        for(Book m: db.selectBooks()){
            lista.add(new ModeloItemImagen(m.getImage(), m.getTitulo(), "Autor: " + m.getAutor() + "\nCodigo: " + m.getCodigo() + "\nCantidad: " + m.getCantidad()));
            idList.add(m);
        }



    }
}