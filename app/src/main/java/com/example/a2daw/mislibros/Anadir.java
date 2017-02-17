package com.example.a2daw.mislibros;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

/**
 * Created by 2DAW on 10/02/2017.
 */

public class Anadir extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anadir);

        Button btn_guardar = (Button) findViewById(R.id.btn_guardar);
        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertarLibro();
            }
        });
    }


    public void insertarLibro() {
        Libros_bd libros_bd = new Libros_bd(this);
        SQLiteDatabase bd = libros_bd.getWritableDatabase();

        ContentValues values = new ContentValues();
        EditText txt_titulo = (EditText) findViewById(R.id.txt_titulo);
        EditText txt_autor = (EditText) findViewById(R.id.txt_autor);
        EditText txt_editorial = (EditText) findViewById(R.id.txt_editorial);
        EditText txt_isbn = (EditText) findViewById(R.id.txt_isbn);
        EditText txt_paginas = (EditText) findViewById(R.id.txt_paginas);
        EditText txt_anio = (EditText) findViewById(R.id.txt_anio);
        CheckBox chk_ebook = (CheckBox) findViewById(R.id.chk_ebook);
        CheckBox chk_leido = (CheckBox) findViewById(R.id.chk_leido);
        RatingBar rt_nota  = (RatingBar) findViewById(R.id.rt_nota);
        EditText txt_resumen = (EditText) findViewById(R.id.txt_resumen);

        if (txt_titulo.getText().toString().trim().length() == 0 || txt_autor.getText().toString().trim().length() == 0) {
            Toast.makeText(getApplicationContext(), "Debe introducir título y autor", Toast.LENGTH_LONG).show();
        } else {
            values.put("titulo", txt_titulo.getText().toString());
            values.put("autor", txt_autor.getText().toString());
            values.put("editorial", txt_editorial.getText().toString());
            values.put("isbn", txt_isbn.getText().toString());
            values.put("paginas", txt_paginas.getText().toString());
            values.put("anio", txt_anio.getText().toString());
            values.put("ebook", chk_ebook.isChecked());
            values.put("leido", chk_leido.isChecked());
            values.put("nota", rt_nota.getRating());
            values.put("resumen", txt_resumen .getText().toString());

            libros_bd.anadir(bd, values);

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Libro añadido con éxito", Toast.LENGTH_LONG).show();
        }


    }



}