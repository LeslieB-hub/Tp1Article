package com.example.tp1article.dal;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tp1article.bo.Article;

import java.util.List;

/**
 * Cette classe permet de donner les consignes necessaires au framework Room
 * pour qu'il puisse créer la dao pour la table utilisateur.
 * Cette interface définit les méthodes CRUD que Room doit créer (requête sql) pour utiliser la bdd. Ces méthodes sont ds le dossier java
 * (create, read, update, delete)
 */
@Dao
public interface ArticleDao {
    //create insérer un article
    @Insert
    void insert(Article item);

    //create insérer des articles
    @Insert
    void insert(Article ... items);

    //read récupérer la liste des articles de la table Article. On créé la méthode qu'on appèle get
    //    LiveData is an observable data holder class. Unlike a regular observable, LiveData is lifecycle-aware, meaning it respects
    //    the lifecycle of other app components, such as activities, fragments, or services. This awareness ensures LiveData only updates
    //    app component observers that are in an active lifecycle state.
    @Query("SELECT * FROM Article")
    LiveData<List<Article>> getArticles();

    //read récupérer l'article choisi de la table Article.
    @Query("SELECT * FROM Article WHERE id = :itemId")
    Article get(int itemId);

    //update MAJ un article de la bdd
    @Update
    void update(Article article);

    //delete supprimer un article de la table article de la bdd
    @Delete
    void delete(Article article);

    //delete supprimer toute la table article de la bdd
    @Query("DELETE FROM Article")
    void delete();

}
