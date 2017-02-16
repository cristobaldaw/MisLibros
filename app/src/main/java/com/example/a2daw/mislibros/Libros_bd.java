package com.example.a2daw.mislibros;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by 2DAW on 10/02/2017.
 */

public class Libros_bd extends SQLiteOpenHelper {
    public Libros_bd(Context contexto) {
        super(contexto, "librosdb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL("CREATE TABLE tbl_libro (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "titulo TEXT, " +
                "autor TEXT, " +
                "editorial TEXT, " +
                "isbn TEXT, " +
                "paginas TEXT, " +
                "anyo INTEGER, " +
                "ebook INTEGER, " +
                "leido INTEGER, " +
                "nota INTEGER, " +
                "resumen TEXT)");

        bd.execSQL("INSERT INTO tbl_libro VALUES(null, 'Titulo1', 'Autor 1', 'Editorial 1', '23165465789'," +
                " '365', '2008', '0','1', '5', 'Resumen 1')");

        bd.execSQL("INSERT INTO tbl_libro VALUES(null, 'Titulo 2', 'Autor 2', 'Editorial 2'," +
                " '12345475', '687', '1992', '1', '0', '2', 'Resumen 2')");
    }

    public void anadir(SQLiteDatabase bd, ContentValues values) {
        bd.insert("tbl_libro", null, values);
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}