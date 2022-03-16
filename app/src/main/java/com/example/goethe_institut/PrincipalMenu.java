package com.example.goethe_institut;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.jar.Manifest;

public class PrincipalMenu extends AppCompatActivity {
    ArrayList<ModeloItem> lista;
    Adaptador adaptador;
    RecyclerView rvlista;
    TextView tvTitle;
    ImageView logo;


    StudentRegistration studentRegistration;
    MaterialAdministration materialAdministration;
    StudentSearch studentSearch;
    Books books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_menu);


         // TODO IMPORTANT
        /**
         * @android.Manifest.permission.WRITE_EXTERNAL_STORAGE helps us to get the permission from Manifest file
         */
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);



        tvTitle = (TextView) findViewById(R.id.tvTitle);
        lista = new ArrayList<>();
        adaptador = new Adaptador(lista);
        rvlista = findViewById(R.id.rvData);
        cargaData();
        rvlista.setAdapter(adaptador);

        //TODO


        logo = findViewById(R.id.logo);

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

                            /*
                                TODO CAMBIA EL UNA IMAGEN MEDIANTE LA RUTA
                            String route = Environment.getExternalStorageDirectory().getPath()+"/Download/UCB.png";
                            System.out.println(route);

                            Bitmap bitmap = BitmapFactory.decodeFile(route);
                            Drawable drawable = new BitmapDrawable(getResources(),bitmap);
                            logo.setImageDrawable(drawable);
                             */
                            tvTitle.setTextSize(25);
                            break;

                    case 1: rvlista.setVisibility(View.GONE);
                            materialAdministration = new MaterialAdministration();
                            getSupportFragmentManager().beginTransaction().replace(R.id.lilaContainer, materialAdministration).commit();
                            tvTitle.setText("Administrar Materiales");
                            tvTitle.setTextSize(25);
                            break;

                    case 2: rvlista.setVisibility(View.GONE);
                            books = new Books();
                            getSupportFragmentManager().beginTransaction().replace(R.id.lilaContainer, books).commit();
                            tvTitle.setText("Administrar Libros");
                            tvTitle.setTextSize(25);
                            break;
                    case 3: rvlista.setVisibility(View.GONE);
                            studentSearch = new StudentSearch();
                            getSupportFragmentManager().beginTransaction().replace(R.id.lilaContainer, studentSearch).commit();
                            tvTitle.setText("Busqueda Estudiante");
                            tvTitle.setTextSize(25);
                            break;
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