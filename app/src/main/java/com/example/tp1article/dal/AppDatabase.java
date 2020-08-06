package com.example.tp1article.dal;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.tp1article.bo.Article;

/**
 * On veut qu'une instance de cette classe (on veut l'appeler qu'une fois) donc singleton
 * permet d'avoir qu'une seule connexion à la bdd et donc de ne pas surcharger celle-ci.
 * avec @database on met tte les entités qu'on a besoin pr construire la bdd
 */
@Database(entities = {Article.class}, exportSchema = false, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    /**
     * Permet de fournir une instance de la dao utilisateur aux couches supérieures.
     */
    public abstract ArticleDao getArticleDao();

    public static AppDatabase getInstance(Context context){
        //Si l'instance n'est pas créé
        if (INSTANCE == null){
            //On l'a créé
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "shop.db").build();
        }
        return INSTANCE;
    }

}
