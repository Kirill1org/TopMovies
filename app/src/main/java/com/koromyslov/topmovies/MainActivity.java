package com.koromyslov.topmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;

    private final RVAdapter.OnItemClickListener clickListener = weatherCity -> {
        Toast.makeText(this, "HI", Toast.LENGTH_SHORT).show();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rv=findViewById(R.id.rv);
        rv.setAdapter(new RVAdapter(this, getFilmList(), clickListener));
        rv.setLayoutManager(new LinearLayoutManager(this));


    }

    public static List<Film> getFilmList(){
        List<Film> filmList = new ArrayList<>();
        filmList.add(new Film("https://upload.wikimedia.org/wikipedia/en/b/b0/Avatar-Teaser-Poster.jpg","AVATAR",
                "https://cdn3.iconfinder.com/data/icons/charts-and-diagrams-v2/64/ten_percent_rating_rate-512.png",
                "10 December, 2009","Джейк Салли — бывший морской пехотинец, прикованный к инвалидному креслу. " +
                "Несмотря на немощное тело, Джейк в душе по-прежнему остается воином. Он получает задание совершить путешествие в несколько " +
                "световых лет к базе землян на планете " +
                "Пандора, где корпорации добывают редкий минерал, имеющий огромное значение для выхода Земли из энергетического кризиса."));
        filmList.add(new Film("https://upload.wikimedia.org/wikipedia/en/b/b0/Avatar-Teaser-Poster.jpg","AVATAR",
                "https://cdn3.iconfinder.com/data/icons/charts-and-diagrams-v2/64/ten_percent_rating_rate-512.png",
                "10 December, 2009","Джейк Салли — бывший морской пехотинец, прикованный к инвалидному креслу. " +
                "Несмотря на немощное тело, Джейк в душе по-прежнему остается воином. Он получает задание совершить путешествие в несколько " +
                "световых лет к базе землян на планете " +
                "Пандора, где корпорации добывают редкий минерал, имеющий огромное значение для выхода Земли из энергетического кризиса."));
        filmList.add(new Film("https://upload.wikimedia.org/wikipedia/en/b/b0/Avatar-Teaser-Poster.jpg","AVATAR",
                "https://cdn3.iconfinder.com/data/icons/charts-and-diagrams-v2/64/ten_percent_rating_rate-512.png",
                "10 December, 2009","Джейк Салли — бывший морской пехотинец, прикованный к инвалидному креслу. " +
                "Несмотря на немощное тело, Джейк в душе по-прежнему остается воином. Он получает задание совершить путешествие в несколько " +
                "световых лет к базе землян на планете " +
                "Пандора, где корпорации добывают редкий минерал, имеющий огромное значение для выхода Земли из энергетического кризиса."));
    return filmList;
    }
}
