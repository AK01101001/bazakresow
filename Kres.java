package com.example.bazakresow;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "kresy")


public class Kres {
    private enum Kategoria{
        naturalne,
        sztuczne
    }
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int id;


    @ColumnInfo(name = "nazwa")
    private String nazwa;

    @ColumnInfo(name = "opis")
    private String opis;
    @ColumnInfo(name = "rok")
    private int rok;





    @Ignore
    public Kres() {
    }

    public Kres(String nazwa, String opis, int rok) {
        id = 0;
        this.nazwa = nazwa;
        this.opis = opis;
        this.rok = rok;

    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getRok() {
        return rok;
    }

    public void setRok(int rok) {
        this.rok = rok;
    }

    public int getId() {
        return id;
    }
}
