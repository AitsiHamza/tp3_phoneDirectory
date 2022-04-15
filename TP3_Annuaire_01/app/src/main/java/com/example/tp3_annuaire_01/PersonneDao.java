package com.example.tp3_annuaire_01;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PersonneDao {
    @Query("SELECT * FROM personne")
    List<Personne> getAll();

    @Query("SELECT * FROM personne WHERE first_name LIKE :first OR " +
            "last_name LIKE :last LIMIT 1")
    Personne findByName(String first, String last);

    @Update
    void update(Personne personne);

    @Insert(onConflict = REPLACE)
    void insert(Personne personne);

    @Delete
    void delete(Personne personne);

    @Query("DELETE FROM personne")
    void deleteAll();
}
