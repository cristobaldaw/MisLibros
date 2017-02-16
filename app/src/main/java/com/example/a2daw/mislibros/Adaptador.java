package com.example.a2daw.mislibros;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by 2DAW on 14/02/2017.
 */

public class Adaptador extends CursorAdapter {
    public Adaptador(Context contexto, Cursor cursor) {
        super(contexto, cursor);
    }
    @Override
    public View newView(Context contexto, Cursor cursor, ViewGroup viewgroup) {
        return LayoutInflater.from(contexto).inflate(R.layout.fila_libro, viewgroup, false);
    }

    @Override
    public void bindView(View view, Context contexto, Cursor cursor) {
        ImageView img_libro = (ImageView) view.findViewById(R.id.img_libro);
        TextView txt_titulo = (TextView) view.findViewById(R.id.txt_titulo);
        TextView txt_autor = (TextView) view.findViewById(R.id.txt_autor);
        RatingBar rt_nota = (RatingBar) view.findViewById(R.id.rt_nota);
        String titulo_bd = cursor.getString(cursor.getColumnIndex("titulo"));
        String autor_bd = cursor.getString(cursor.getColumnIndex("autor"));
        Float nota_bd = cursor.getFloat(cursor.getColumnIndex("nota"));
        txt_titulo.setText(titulo_bd);
        txt_autor.setText(autor_bd);
        rt_nota.setRating(nota_bd);
    }

}
