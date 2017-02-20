package com.example.a2daw.mislibros;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
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
        completarLibro();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_superior, menu);
        MenuItem guardar = menu.findItem(R.id.btn_anadir);
        MenuItem eliminar = menu.findItem(R.id.btn_eliminar);
        MenuItem editar = menu.findItem(R.id.btn_editar);
        guardar.setVisible(false);
        eliminar.setVisible(true);
        editar.setVisible(true);
        return true;
    }

   @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.btn_eliminar:
                DialogEliminar();
                return true;
            case R.id.btn_editar:
                Editar();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

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
            values.put("anio", txt_anio.getText().toString());
            values.put("ebook", chk_ebook.isChecked());
            values.put("leido", chk_leido.isChecked());
            values.put("nota", rt_nota.getRating());
            values.put("resumen", txt_resumen .getText().toString());

            conex.editar(bd, values, libro_id);

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Libro editado correctamente", Toast.LENGTH_LONG).show();
        }
    }

    public void DialogEliminar() {
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("Eliminar");
        dialogo1.setMessage("¿Está seguro de que desea eliminar este libro?");
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                Eliminar();
            }
        });
        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                // No hace nada
            }
        });
        dialogo1.show();
    }

    public void Eliminar() {
        Libros_bd conex = new Libros_bd(this);
        SQLiteDatabase bd = conex.getWritableDatabase();
        Intent i = getIntent();
        long libro_id = i.getLongExtra("libro_id", 0);
        conex.eliminar(bd, libro_id);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Se ha borrado el libro", Toast.LENGTH_LONG).show();
    }

    public void completarLibro() {
        Libros_bd conex = new Libros_bd(this);
        SQLiteDatabase bd = conex.getWritableDatabase();
        Intent i = getIntent();
        long libro_id = i.getLongExtra("libro_id", 0);
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

                String titulo_bd = cursor.getString(cursor.getColumnIndexOrThrow("titulo"));
                String autor_bd = cursor.getString(cursor.getColumnIndexOrThrow("autor"));
                String editorial_bd = cursor.getString(cursor.getColumnIndexOrThrow("editorial"));
                String isbn_bd = cursor.getString(cursor.getColumnIndexOrThrow("isbn"));
                String paginas_bd = cursor.getString(cursor.getColumnIndexOrThrow("paginas"));
                String anio_bd = cursor.getString(cursor.getColumnIndexOrThrow("anio"));
                String resumen_bd = cursor.getString(cursor.getColumnIndexOrThrow("resumen"));
                Integer ebook_bd = cursor.getInt(cursor.getColumnIndexOrThrow("ebook"));
                Integer leido_bd = cursor.getInt(cursor.getColumnIndexOrThrow("leido"));
                Float rating_bd = cursor.getFloat(cursor.getColumnIndexOrThrow("nota"));

                txt_titulo.setText(titulo_bd);
                txt_autor.setText(autor_bd);
                txt_editorial.setText(editorial_bd);
                txt_isbn.setText(isbn_bd);
                txt_paginas.setText(paginas_bd);
                txt_anio.setText(anio_bd);
                txt_resumen.setText(resumen_bd);
                rt_nota.setRating(rating_bd);

                boolean ebook = (ebook_bd == 0) ? false : true;
                boolean leido = (leido_bd == 0) ? false : true;
                chk_ebook.setChecked(ebook);
                chk_leido.setChecked(leido);

                cursor.moveToNext();
            }
        }
        cursor.close();
    }
}
