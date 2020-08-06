package com.example.tp1article.repository;

import android.content.AsyncQueryHandler;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.tp1article.bo.Article;
import com.example.tp1article.dal.AppDatabase;
import com.example.tp1article.dal.ArticleDao;

import java.util.List;

/**
 * Le repository permet de gerer l'asynchronisme
 * implémente les méthodes de l'interface InterfaceArticleRepository et nom de ArticleDao pour respecter l'indépendance des couches.
 * Ce qui ne serait pas le cas si on l'implémentait avec ArticleDao on serait en contact dire avec la bdd
 */
public class ArticleBddRepository implements InterfaceArticleRepository {
    //On déclare articleDao pr récupérer grâce au singleton une instance de celle-ci
    private ArticleDao articleDao = null;

    //Grâce au constructeur on récupère le context de l'app et on fait la connexion à la bdd qu'une seule fois
    //Au lieu de la demander ds tte les méthodes
    public ArticleBddRepository(Context context){
        //Instance de ma bdd.
        AppDatabase maBaseDeDonnees = AppDatabase.getInstance(context);
        //Instance de la dao utilisateur.
        articleDao = maBaseDeDonnees.getArticleDao();
    }

    @Override
    public void insert(Article article) {
        //Je créé un nouveau thread
        new AsyncTask<Article,Void,Void>(){
            //ce que le new thread doit faire
            @Override
            protected Void doInBackground(Article... articles) {
                //Le thread doit insérer ds la table un nouvelle article
                articleDao.insert(articles[0]);
                return null;
            }
        }.execute(article);
    }

    LiveData<List<Article>> resultat = null;
    @Override
    public LiveData<List<Article>> getArticles() {
         return  articleDao.getArticles();
    }

    Article articleGet = null;
    @Override
    public Article get(int id) {
        //1er param ceux dont à besoin le traitement, 2ème donnée en cours qu'on veut récup ex barre de progression, 3ème la donnée qu'on veut récupérer à la sortie du ttt
        new AsyncTask<Integer,Void, Article>(){
        //paramètre variadique ...
            @Override
            protected Article doInBackground(Integer... integers) {
                return articleDao.get(integers[0]);
            }

            @Override
            protected void onPostExecute(Article article) {
                super.onPostExecute(article);
                articleGet = article;
            }
        }.execute();

        return articleGet;
    }

    @Override
    public void update(Article article) {
        new AsyncTask<Article, Void, Void>(){

            @Override
            protected Void doInBackground(Article... articles) {
                articleDao.update(articles[0]);
                return null;
            }
        }.execute(article);
    }

    @Override
    public void delete(Article article) {
        new AsyncTask<Article, Void, Void>() {
            @Override
            protected Void doInBackground(Article... articles) {
                articleDao.delete(articles[0]);
                return null;
            }
        }.execute(article);
    }

    @Override
    public void delete() {
        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                articleDao.delete();
                return null;
            }
        }.execute();

    }
}
