package com.example.goethe_institut;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class PrincipalMenu extends AppCompatActivity {
    ArrayList<ModeloItem> lista;
    Adaptador adaptador;
    RecyclerView rvlista;
    Button btnExit;
    TextView tvTitle;

    StudentRegistration studentRegistration;
    MaterialAdministration materialAdministration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_menu);

        tvTitle = (TextView) findViewById(R.id.tvTitle);
        lista = new ArrayList<>();
        adaptador = new Adaptador(lista);
        rvlista = findViewById(R.id.rvData);
        cargaData();
        rvlista.setAdapter(adaptador);

        rvlista.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));





        studentRegistration = new StudentRegistration();

        rvlista.addOnItemTouchListener(new Toques(getApplicationContext(), rvlista, new Toques.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                switch (position) {
                    case 0:
                            rvlista.setVisibility(View.GONE);
                            getSupportFragmentManager().beginTransaction().replace(R.id.lilaContainer, studentRegistration).commit();
                            tvTitle.setText("Registrar Alumno");
                            tvTitle.setTextSize(25);
                            break;

                    case 1: rvlista.setVisibility(View.GONE);
                            materialAdministration = new MaterialAdministration();
                            getSupportFragmentManager().beginTransaction().replace(R.id.lilaContainer, materialAdministration).commit();
                            tvTitle.setText("Administrar Materiales");
                            tvTitle.setTextSize(25);
                            break;

                    case 2: break;
                    case 3: break;

                }
                System.out.println(position);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }


    void cargaData(){
        lista.add(new ModeloItem(R.drawable.menuestudiante
                , "Registrar Nuevos Estudiantes","Usted puede registrar nuevos estudiantes."));
        lista.add(new ModeloItem(R.drawable.menumaterial
                , "Administrar Materiales","Puede ver la cantidad de materiales que se encuentran en almacen."));
        lista.add(new ModeloItem(R.drawable.menubiblioteca
                , "Ver Biblioteca","Usted puede administrar los libros que se encuentran en la biblioteca."));
        lista.add(new ModeloItem(R.drawable.menucall
                , "Buscar Alumno","Puede buscar a un alumno para poder contactarse en caso de ser necesario."));
    }
}