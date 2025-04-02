package com.example.bazakresow;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "kresy")


public class Kres {
    @ColumnInfo(name = "kategoria")
    private Kategoria kategoria;
    public enum Kategoria{
        naturalne,
        sztuczne
    }

    public void setId(int id) {
        this.id = id;
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

    public Kres(String nazwa, String opis, int rok, Kategoria kategoria) {
        id = 0;
        this.nazwa = nazwa;
        this.opis = opis;
        this.rok = rok;
        this.kategoria = kategoria;

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

    public Kategoria getKategoria() {
        return kategoria;
    }

    public void setKategoria(Kategoria kategoria) {
        this.kategoria = kategoria;
    }

    @Override
    public String toString() {
        return "Kres{" +
                "kategoria=" + kategoria +
                ", id=" + id +
                ", nazwa='" + nazwa + '\'' +
                ", opis='" + opis + '\'' +
                ", rok=" + rok +
                '}';
    }
}
