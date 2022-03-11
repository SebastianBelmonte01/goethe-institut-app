package com.example.goethe_institut;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String MATERIAL = "MATERIAL";
    public static final String PERSONA = "PERSONA";
    public static final String LIBROS = "LIBROS";
    public static final String CURSO = "CURSO";
    public static final String PERSONA_MATERIAL = "PERSONA_MATERIAL";
    public static final String RESERVA = "RESERVA";
    public static final String PERSONA_CURSO = "PERSONA_CURSO";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "goethe.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String courseTable = "CREATE TABLE " + CURSO + " (ID INTEGER PRIMARY KEY, NOMBRE TEXT, COSTO NUMERIC)";
        String personTable = "CREATE TABLE " + PERSONA + " (CI TEXT, NOMBRE TEXT, APELLIDO TEXT, DIRECCION TEXT, TELEFONO TEXT, FECHANACIMIENTO TEXT, USUARIO TEXT, PASSWORD INT, ADMINISTRADOR BOOL)";
        String materialTable = "CREATE TABLE " + MATERIAL + " (ID INTEGER PRIMARY KEY, TIPO TEXT, COSTO NUMERIC, " +
                "CANTIDAD INTEGER, DESCRIPCION TEXT, IMAGEN  BLOB)";
        String booksTable = "CREATE TABLE " + LIBROS + " (ID TEXT PRIMARY KEY, TITULO TEXT, AUTOR TEXT, CANTIDAD INTEGER, IMAGEN BLOB)";

        //MIDLE TABLES
        /*
                String materialPersonTable = "CREATE TABLE " + PERSONA_MATERIAL + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, CANTIDAD INTEGER, FOREIGN KEY (PERSONA_CI) REFERENCES PERSONA (CI), FOREIGN KEY (MATERIAL_ID) REFERENCES MATERIAL (ID))";
        String reservationTable = "CREATE TABLE " + RESERVA + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, ESTADO TEXT, FOREIGN KEY(PERSONA_CI) REFERENCES PERSONA(CI), FOREIGN KEY(LIBROS_ID) REFERENCES LIBROS(ID))";
        String personCoursesTable = "CREATE TABLE " + PERSONA_CURSO + " (ID INTEGER  PRIMARY KEY AUTOINCREMENT, FOREIGN KEY(PERSONA_CI) REFERENCES PERSONA (CI), FOREIGN KEY(CURSO_ID) REFERENCES CURSO(ID))";
         */

        //TABLE CREATION
        db.execSQL(courseTable);
        db.execSQL(materialTable);
        db.execSQL(personTable);
        db.execSQL(booksTable);


        //MIDLE TABLES CREATION
        /*

        db.execSQL(materialPersonTable);
        db.execSQL(reservationTable);
        db.execSQL(personCoursesTable);

         */


        //CONSTANT VALUES
        String insertValuesToCourseA11 = "INSERT INTO " + CURSO +  " (ID, NOMBRE, COSTO) VALUES (" + 0 + ", 'A1.1', " + 2300.50 + ")";
        db.execSQL(insertValuesToCourseA11);

        String insertValuesToCourseA12 = "INSERT INTO " + CURSO +  " (ID, NOMBRE, COSTO) VALUES (" + 1 +", 'A1.2', " + 2300.50 + ")";
        db.execSQL(insertValuesToCourseA12);

        String insertValuesToCourseA21 = "INSERT INTO " + CURSO +  " (ID, NOMBRE, COSTO) VALUES (" + 2 +", 'A2.1', " + 2300.50 + ")";
        db.execSQL(insertValuesToCourseA21);

        String insertValuesToCourseA22 = "INSERT INTO " + CURSO +  " (ID, NOMBRE, COSTO) VALUES (" + 3 +", 'A2.2', " + 2300.50 + ")";
        db.execSQL(insertValuesToCourseA22);

        String insertValuesToCourseB11 = "INSERT INTO " + CURSO + " (ID, NOMBRE, COSTO) VALUES (" + 4 +", 'B1.1', " + 2300.50 + ")";
        db.execSQL(insertValuesToCourseB11);

        String insertValuesToCourseB12 = "INSERT INTO " + CURSO + " (ID, NOMBRE, COSTO) VALUES (" + 5 + ", 'B1.2', " + 2300.50 + ")";
        db.execSQL(insertValuesToCourseB12);

        String insertValuesToCourseB21 = "INSERT INTO " + CURSO +  " (ID, NOMBRE, COSTO) VALUES (" + 6 +", 'B2.1', " + 2300.50 + ")";
        db.execSQL(insertValuesToCourseB21);

        String insertValuesToCourseB22 = "INSERT INTO " + CURSO +  " (ID, NOMBRE, COSTO) VALUES (" + 7 +", 'B2.2', " + 2300.50 + ")";
        db.execSQL(insertValuesToCourseB22);

        String insertValuesToCourseC11 = "INSERT INTO " + CURSO + " (ID, NOMBRE, COSTO) VALUES (" + 8 +", 'C1.1', " + 2300.50 + ")";
        db.execSQL(insertValuesToCourseC11);

        String insertValuesToCourseC12 = "INSERT INTO " + CURSO + " (ID, NOMBRE, COSTO) VALUES (" + 9 + ", 'C1.2', " + 2300.50 + ")";
        db.execSQL(insertValuesToCourseC12);

        String insertValuesToCourseC21 = "INSERT INTO " + CURSO +  " (ID, NOMBRE, COSTO) VALUES (" + 10 +", 'C2.1', " + 2300.50 + ")";
        db.execSQL(insertValuesToCourseC21);

        String insertValuesToCourseC22 = "INSERT INTO " + CURSO +  " (ID, NOMBRE, COSTO) VALUES (" + 11 +", 'C2.2', " + 2300.50 + ")";
        db.execSQL(insertValuesToCourseC22);

        //INSERT INTO PERSONA
        String insertIntoPersona = "INSERT INTO " + PERSONA +  "(CI, NOMBRE, APELLIDO, DIRECCION, TELEFONO, FECHANACIMIENTO, USUARIO, PASSWORD, ADMINISTRADOR) VALUES ('18928912', 'Sebastian', 'Belmonte', 'Av.Direccion', '270000', '23-12-2001', 'usuario',"+0000+","+1+")";
        db.execSQL(insertIntoPersona);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean isLoged(String username, int password){

        String query = "SELECT * FROM " + PERSONA + " WHERE USUARIO = '" + username + "' AND PASSWORD = " + password;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{});

        if(cursor.moveToFirst()){
            return true;
        }


        return false;
    }
}
