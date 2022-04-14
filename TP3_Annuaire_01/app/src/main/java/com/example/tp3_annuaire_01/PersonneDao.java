package com.example.tp3_annuaire_01;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PersonneDao {
    @Query("SELECT * FROM personne")
    List<Personne> getAll();

    @Query("SELECT * FROM personne WHERE id IN (:personneIds)")
    List<Personne> loadAllByIds(int[] personneIds);

    @Query("SELECT * FROM personne WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    Personne findByName(String first, String last);
/*
    @Query("SELECT * FROM personne WHERE phone_number LIKE :phone LIMIT 1")
    Personne findByPhone(String phone);
*/
    @Insert
    void insert(Personne personne);

    @Delete
    void delete(Personne personne);
}
