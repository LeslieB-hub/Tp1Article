package com.example.tp1article.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tp1article.R;
import com.example.tp1article.bo.Article;
import com.example.tp1article.repository.ArticleBddRepository;
import com.example.tp1article.repository.InterfaceArticleRepository;

public class DisplayArticle extends AppCompatActivity {
    Article articleSend = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_article);
        //récupérer le intent
        Intent intent = getIntent();
        articleSend = intent.getParcelableExtra(listeArticleActivity.KEY);

        Log.i("shop", String.valueOf(articleSend));

        //Afficher l'article ds la vue
        TextView tvName = findViewById(R.id.tv_nameD);
        tvName.setText(articleSend.getName());
        TextView tvPrice = findViewById(R.id.tv_priceD);
        tvPrice.setText(String.valueOf(articleSend.getPrice()));
        TextView tvDescription = findViewById(R.id.tv_descriptionD);
        tvDescription.setText(articleSend.getDescription());

        RatingBar barRating = findViewById(R.id.tv_ratingD);
        barRating.setRating(articleSend.getRating());

    }

    public void onClickDelete(View view) {
        InterfaceArticleRepository repoArticle = new ArticleBddRepository(this);
        repoArticle.delete(articleSend);
        Toast.makeText(DisplayArticle.this, "Votre article " + articleSend.getName() + " a été supprimé.", Toast.LENGTH_LONG).show();
        //redigige vers la page d'accueil après modification
        Intent intentMain = new Intent(DisplayArticle.this, MainActivity.class);
        startActivity(intentMain);
    }

    public void onClickModify(View view) {
        //passer l'article à modifier dans le controleur ModifyArticleActivity
        Intent intentModify = new Intent(DisplayArticle.this, ModifyArticleActivity.class);
        intentModify.putExtra("articleModify", articleSend);
        startActivity(intentModify);

    }

    public void onClickReturnList(View view) {
        Intent intentList = new Intent(DisplayArticle.this, listeArticleActivity.class);
        startActivity(intentList);
    }

    //ICI : ON LIE L’ACTION BARRE À L'ACTIVITÉ
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //on décompresse le xml du menu
        getMenuInflater().inflate(R.menu.mon_menu, menu);
        return true;
    }

    //ICI : ON DÉFINIT LES ACTIONS DE L’ACTION BARRE
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                Toast.makeText(this,"Préférences", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_recherche:
                Toast.makeText(this,"Recherche", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}