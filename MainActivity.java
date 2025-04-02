package com.example.bazakresow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.bazakresow.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayAdapter<Kres> adapter;
    private KresDatabase kresDatabase;
    List<Kres> kresy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        kresy = new ArrayList<Kres>();
        RoomDatabase.Callback callback = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }

            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
            }

        };
        kresDatabase = Room.databaseBuilder(
                getApplicationContext(),
                KresDatabase.class,"kresy_db")
                .addCallback(callback)
                .allowMainThreadQueries()
                .build();
        wczytaj();
        binding.button.setOnClickListener(view1 -> dodaj());
        binding.lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                usun(i);
            }
        });

    }

    private void usun(int i) {

                kresDatabase.zwrocDao().delete(kresy.get(i));
                kresy.remove(i);
                adapter.notifyDataSetChanged();

    }

    private void dodaj() {
        if (!binding.e1.getText().toString().equals("") && !binding.e2.getText().toString().equals("") && !binding.e3.getText().toString().equals("") && binding.spinner.getPrompt() != "") {
            Kres.Kategoria kat;
            Kres kres;
            switch (binding.spinner.getSelectedItem().toString()) {
                case "naturalne":
                    kat = Kres.Kategoria.naturalne;
                    kres = new Kres(binding.e1.getText().toString(), binding.e2.getText().toString(), Integer.parseInt(binding.e3.getText().toString()), kat);
                    break;
                case "sztuczne":
                    kat = Kres.Kategoria.sztuczne;
                    kres = new Kres(binding.e1.getText().toString(), binding.e2.getText().toString(), Integer.parseInt(binding.e3.getText().toString()), kat);
                    break;
                default:
                    kres = new Kres();
                    return;
            }


            ExecutorService executorService = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    kresDatabase.zwrocDao().insert(kres);

                    handler.post(
                            new Runnable() {
                                @Override
                                public void run() {

                                }
                            }
                    );
                }
            });
            wczytaj();
            adapter.notifyDataSetChanged();
        }
    }
        private void wczytaj()
        {
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    kresy = kresDatabase.zwrocDao().selectAll();
                    handler.post(
                            new Runnable() {
                                @Override
                                public void run() {
                                    adapter = new ArrayAdapter<Kres>(MainActivity.this,
                                            android.R.layout.simple_list_item_1,
                                            kresy);
                                    binding.lista.setAdapter(adapter);
                                }
                            }
                    );
                }
            });
        }

    }