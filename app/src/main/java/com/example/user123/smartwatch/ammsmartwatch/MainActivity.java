package com.example.user123.smartwatch.ammsmartwatch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class MainActivity extends Activity {


    ListView listView ;

    //spectacles name data
    String[] itemname ={
            "spectacle no 1",
            "spectacle no 2",
            "spectacle no 3",
            "spectacle no 4",
            "spectacle no 5",
            "spectacle no 6",
            "spectacle no 7",
            "spectacle no 8",
            "spectacle no 9",
            "spectacle no 10",

    };

    //locations data
    Locations[] locations = {
            new Locations(57.9,2.9,"Aberdeen"),
            new Locations(13.45, 100.30,"Bangkok"),
            new Locations(53.5,8.49, "Bremen"),
            new Locations(34.35,58.22,"Buenos Aires"),
            new Locations(60.10,20.0,"Helsinki"),
            new Locations(52.30,104.20,"Irkutsk"),
            new Locations(13.45, 100.30,"Bangkok"),
            new Locations(53.5,8.49, "Bremen"),
            new Locations(34.35,58.22,"Buenos Aires"),
            new Locations(52.30,104.20,"Irkutsk")
    };

    //pictures of spectacles
    Integer[] imgid={
            R.mipmap.pic1,
            R.mipmap.pic2,
            R.mipmap.pic3,
            R.mipmap.pic4,
            R.mipmap.pic5,
            R.mipmap.pic6,
            R.mipmap.pic7,
            R.mipmap.pic8,
            R.mipmap.pic9,
            R.mipmap.pic10,
    };


    //method to generate random dates
    private String[] generateDate(){
        String[] times = new String[10];
        for(int i=0;i<10;i++){
            long offset = Timestamp.valueOf("2016-01-01 00:00:00").getTime();
            long end = Timestamp.valueOf("2017-01-01 00:00:00").getTime();
            long diff = end - offset + 1;
            Timestamp rand = new Timestamp(offset + (long)(Math.random() * diff));

            String S = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(rand);
            times[i] = S;
        }

        return times;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] times = generateDate();

        SpectacleListAdaptor adapter = new SpectacleListAdaptor(this, itemname, imgid, times, locations);


        listView  =( ListView)findViewById(R.id.list);
        listView .setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
//                // TODO Auto-generated method stub
                Locations loc = locations[position];
//                String Slecteditem= itemname[+position];
//                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent();
                Bundle b = new Bundle();

                b.putParcelable("MAP_DATA", loc);
                intent.putExtras(b);
                intent.setClass(MainActivity.this, MapsActivity.class);
                startActivity(intent);

            }
        });
    }
}
