package com.example.a2daw.mislibros;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

/**
 * Created by 2DAW on 16/02/2017.
 */

public class Editar_Eliminar extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_eliminar);

        Button btn_editar = (Button) findViewById(R.id.btn_editar);
        btn_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Editar();
            }
        });
    }

    public void Editar() {
        Libros_bd conex = new Libros_bd(this);
        SQLiteDatabase bd = conex.getWritableDatabase();
        Intent i = getIntent();
        long libro_id = i.getLongExtra("libro_id", 0);

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
            values.put("anyo", txt_anio.getText().toString());
            values.put("ebook", chk_ebook.isChecked());
            values.put("leido", chk_leido.isChecked());
            values.put("nota", rt_nota.getRating());
            values.put("resumen", txt_resumen .getText().toString());

            conex.editar(bd, values, libro_id);

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Libro editado con éxito", Toast.LENGTH_LONG).show();
        }
    }

    public void completarLibro() {
        Libros_bd conex = new Libros_bd(this);
        SQLiteDatabase bd = conex.getWritableDatabase();
        Intent i = getIntent();
        Long libro_id = i.getLongExtra("libro_id", 0);

        String datos_libro = "select * from tbl_libro where _id = " + libro_id;
        Cursor cursor = bd.rawQuery(datos_libro, null);

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

        if (cursor.moveToFirst()){
            while(!cursor.isAfterLast()){

                String titulo_b = cursor.getString(cursor.getColumnIndex("titulo"));
                String autor_b = cursor.getString(cursor.getColumnIndex("autor"));
                String editorial_b = cursor.getString(cursor.getColumnIndex("editorial"));
                String isbn_b = cursor.getString(cursor.getColumnIndex("isbn"));
                String paginas_b = cursor.getString(cursor.getColumnIndex("paginas"));
                String anyo_b = cursor.getString(cursor.getColumnIndex("anio"));
                String resumen_b = cursor.getString(cursor.getColumnIndex("resumen"));
                Integer ebook_b = cursor.getInt(cursor.getColumnIndex("ebook"));
                Integer leido_b = cursor.getInt(cursor.getColumnIndex("leido"));
                Float rating_b = cursor.getFloat(cursor.getColumnIndex("valoracion"));

                txt_titulo.setText(titulo_b);
                txt_autor.setText(autor_b);
                txt_editorial.setText(editorial_b);
                txt_isbn.setText(isbn_b);
                txt_paginas.setText(paginas_b);
                txt_anio.setText(anyo_b);
                txt_resumen.setText(resumen_b);
                rt_nota.setRating(rating_b);

                //(ebook_b == 0) ? sh_ebook.setChecked(false) : sh_ebook.setChecked(true);
                //(leido_b == 0) ? sh_leido.setChecked(false) : sh_leido.setChecked(true);

                cursor.moveToNext();
            }
        }
        cursor.close();
    }
}
