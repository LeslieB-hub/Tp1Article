package com.example.tp1article.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tp1article.R;
import com.example.tp1article.bo.Article;

import java.util.List;

public class ArticleAdapter extends ArrayAdapter<Article> {

    public ArticleAdapter(@NonNull Context context, int resource, @NonNull List<Article> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View newLigne, @NonNull ViewGroup parent) {
        Article article = getItem(position);
        //if Pour décompressé le xml une fois
        if (newLigne == null){
            // On décompresse le fichier style_ligne_article.xml
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            newLigne = li.inflate(R.layout.style_ligne_article, parent, false);

        }
        // On met les données dans la ligne
        TextView tvName = newLigne.findViewById(R.id.tv_name);
        TextView tvDescription = newLigne.findViewById(R.id.tv_description);
        RatingBar barRating = newLigne.findViewById(R.id.tv_rating);
        TextView tvPrice = newLigne.findViewById(R.id.tv_price);


        tvName.setText(article.getName());
        tvDescription.setText(article.getDescription());
        barRating.setRating(article.getRating());

        String price = String.valueOf(article.getPrice());
        tvPrice.setText(price + " €");

        // On retourne la ligne
        return newLigne;
    }

}
