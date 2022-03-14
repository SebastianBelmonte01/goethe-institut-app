package com.example.goethe_institut;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String MATERIAL = "MATERIAL";
    public static final String PERSONA = "PERSONA";
    public static final String LIBROS = "LIBROS";
    public static final String CURSO = "CURSO";
    public static final String PERSONA_MATERIAL = "PERSONA_MATERIAL";
    public static final String RESERVA = "RESERVA";
    public static final String PERSONA_CURSO = "PERSONA_CURSO";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "goethe.db", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String courseTable = "CREATE TABLE " + CURSO + " (ID INTEGER PRIMARY KEY, NOMBRE TEXT, COSTO NUMERIC)";
        String personTable = "CREATE TABLE " + PERSONA + " (CI TEXT, NOMBRE TEXT, APELLIDO TEXT, DIRECCION TEXT, TELEFONO TEXT, FECHANACIMIENTO TEXT, USUARIO TEXT, PASSWORD INT, ADMINISTRADOR BOOL)";
        String materialTable = "CREATE TABLE " + MATERIAL + " (ID INTEGER PRIMARY KEY, TIPO TEXT, COSTO NUMERIC, " +
                "CANTIDAD INTEGER, DESCRIPCION TEXT, IMAGEN  BLOB)";
        String booksTable = "CREATE TABLE " + LIBROS + " (ID TEXT PRIMARY KEY, TITULO TEXT, AUTOR TEXT, CANTIDAD INTEGER, IMAGEN BLOB)";

        //MIDLE TABLES

        //String materialPersonTable = "CREATE TABLE " + PERSONA_MATERIAL + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, CANTIDAD INTEGER, FOREIGN KEY (PERSONA_CI) REFERENCES PERSONA (CI), FOREIGN KEY (MATERIAL_ID) REFERENCES MATERIAL (ID))";
        //String reservationTable = "CREATE TABLE " + RESERVA + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, ESTADO TEXT, FOREIGN KEY(PERSONA_CI) REFERENCES PERSONA(CI), FOREIGN KEY(LIBROS_ID) REFERENCES LIBROS(ID))";

        //TABLE CREATION
        db.execSQL(courseTable);
        db.execSQL(materialTable);
        db.execSQL(personTable);
        db.execSQL(booksTable);


        //MIDLE TABLES CREATION
        /*

        db.execSQL(materialPersonTable);
        db.execSQL(reservationTable);

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



        String personCoursesTable = "CREATE TABLE " + PERSONA_CURSO + " (ID INTEGER  PRIMARY KEY AUTOINCREMENT, PERSONA_CI TEXT, CURSO_ID INTEGER)";
        String insertPersonCourses = "INSERT INTO " + PERSONA_CURSO + " (PERSONA_CI, CURSO_ID) values ('1', " + 10 + ")";
        db.execSQL(personCoursesTable);
        db.execSQL(insertPersonCourses);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {



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

    public void insertPersona(Persona persona){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("CI", persona.getCi());
        cv.put("NOMBRE",persona.getName());
        cv.put("APELLIDO", persona.getLastName());
        cv.put("DIRECCION", persona.getDirection());
        cv.put("TELEFONO", persona.getTelefono());
        cv.put("FECHANACIMIENTO", persona.getBornDate());
        cv.put("USUARIO", persona.getUsuario());
        cv.put("PASSWORD", persona.getPassword());
        cv.put("ADMINISTRADOR", persona.getAdmin());

        db.insert(PERSONA, null, cv);

    }

    public void insertPersonCourses(String personCi, int courseId){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("PERSONA_CI", personCi);
        cv.put("CURSO_ID", courseId);

        db.insert(PERSONA_CURSO, null, cv);
    }

    public Persona searchPerson(String ci) {
        String query = "SELECT P.CI, P.NOMBRE, P.APELLIDO, P.DIRECCION, P.TELEFONO, P.FECHANACIMIENTO, P.USUARIO, P.PASSWORD, P.ADMINISTRADOR FROM " + PERSONA  + " P, " + PERSONA_CURSO + " PC, " + CURSO + " C  WHERE P.CI = '" + ci + "' AND '" + ci + "' = PC.PERSONA_CI AND PC.CURSO_ID = C.ID" ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor.moveToFirst()) {
            return new Persona(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), null, 0, 0);
        }
        return  null;
    }
    public Integer searchCourse(String ci){
        String query = "SELECT C.ID FROM " + PERSONA  + " P, " + PERSONA_CURSO + " PC, " + CURSO + " C  WHERE P.CI = '" + ci + "' AND '" + ci + "' = PC.PERSONA_CI AND PC.CURSO_ID = C.ID" ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor.moveToFirst()) {
            return cursor.getInt(0);
        }
        return  -1;
    }
    public void updatePerson(Persona persona){
        String query = "UPDATE " + PERSONA + " SET NOMBRE = '"+ persona.getName() + "', APELLIDO = '" + persona.getLastName() + "', DIRECCION = '" + persona.getDirection() +"', TELEFONO = '" + persona.getTelefono() + "' WHERE CI = '" + persona.getCi() + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
    }

    public void updatePersonCourse(String ci, int idCourse){
        String query = "UPDATE " + PERSONA_CURSO + " SET  CURSO_ID = " + idCourse + " WHERE PERSONA_CI = '" + ci + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
    }

    public void insertMaterial(Material material){
        String route = Environment.getExternalStorageDirectory().getPath()+"/Download/" + material.getImageDirection();
        System.out.println(route);
        Bitmap bitmap = BitmapFactory.decodeFile(route);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
        byte[] bytesImage = byteArrayOutputStream.toByteArray();
        ContentValues cv = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();

        cv.put("ID", material.getId());
        cv.put("TIPO", material.getType());
        cv.put("COSTO", material.getCost());
        cv.put("CANTIDAD", material.getCantidad());
        cv.put("DESCRIPCION", material.getDescription());
        cv.put("IMAGEN", bytesImage);

        db.insert(MATERIAL, null, cv);


    }

    public ArrayList<Material> selectMaterials (){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + MATERIAL;
        ArrayList<Material> materials = new ArrayList<Material>();

        Cursor cursor = db.rawQuery(query, null);

                /*
                String materialTable = "CREATE TABLE " + MATERIAL + " (ID INTEGER PRIMARY KEY, TIPO TEXT, COSTO NUMERIC, " +
                "CANTIDAD INTEGER, DESCRIPCION TEXT, IMAGEN  BLOB)";

        Drawable drawable = new BitmapDrawable(getResources(),bitmap);
        logo.setImageDrawable(drawable);

         */

        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                System.out.println(id + " IDDDD");
                String type = cursor.getString(1);
                double cost = cursor.getDouble(2);
                int cantidad = cursor.getInt(3);
                String description = cursor.getString(4);
                byte[] bytesImage = cursor.getBlob(5);

                Bitmap image = BitmapFactory.decodeByteArray(bytesImage, 0, bytesImage.length);
                materials.add(new Material(id, type, cost, cantidad, null, description, image));


            }while(cursor.moveToNext());
            cursor.close();
            db.close();
        }


        return materials;

    }

    public Material selectMaterial(int id){
        String query = "SELECT * FROM " + MATERIAL + " WHERE ID = " + id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            return new Material(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), null,  cursor.getString(4), null);
        }
        return null;

    }

    /**
     *
     * @param material object of Material Type
     * @param changeImage if its true, the image path has to be changed, else has not  to be changed
     */
    public void updateMaterial(Material material, boolean changeImage){
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues cv = new ContentValues();


        if(changeImage){
            String route = Environment.getExternalStorageDirectory().getPath()+"/Download/" + material.getImageDirection();
            System.out.println(route);
            Bitmap bitmap = BitmapFactory.decodeFile(route);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
            byte[] bytesImage = byteArrayOutputStream.toByteArray();

            cv.put("TIPO", material.getType());
            cv.put("COSTO", material.getCost());
            cv.put("CANTIDAD", material.getCantidad());
            cv.put("DESCRIPCION", material.getDescription());
            cv.put("IMAGEN", bytesImage);



        }
        else{

            cv.put("TIPO", material.getType());
            cv.put("COSTO", material.getCost());
            cv.put("CANTIDAD", material.getCantidad());
            cv.put("DESCRIPCION", material.getDescription());

        }
       db.update(MATERIAL, cv, "ID = " + material.getId(), null);


    }



}
