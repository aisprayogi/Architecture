package edu.cs4730.roomdemo;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/*
 * This is a simple example of how the room architecture to create a simple database.
 * It create a score Score  Plain Old Java Object (entity), a data access object (dao), and roomDatabase.
 *
 * In MainActivity it opens the database, then in threads data can be added and displayed.
 *
 */

public class MainActivity extends AppCompatActivity {

    AppDatabase db;
    TextView logger;
    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logger = (TextView) findViewById(R.id.logger);

        //open(create) the database.
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();


        findViewById(R.id.btn_display).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread myThread = new Thread() {
                    public void run() {
                        logthis("Starting display of score=3012");
                        List<Score> scores = db.ScoreDao().loadScore(3012);
                        if (scores != null) {
                            for (Score score : scores) {
                                String data = "id=" + score.getId() + " name=" + score.getName() + " score=" + score.getScore();
                                logthis(data);
                            }
                        } else {
                            logthis("no data loadScore ");

                        }

                        scores = db.ScoreDao().loadAllScores();//myDR.getScores().getValue();
                        logthis("Starting display of all data");
                        if (scores != null) {
                            for (Score score : scores) {
                                String data = "id=" + score.getId() + " name=" + score.getName() + " score=" + score.getScore();
                                logthis(data);
                            }
                        } else {
                            logthis("There is no data!!!");
                        }

                    }
                };
                myThread.start();
            }
        });

        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread myThread = new Thread() {
                    public void run() {
                        logthis("Inserting data");
                        db.ScoreDao().insertAll(generateScores());
                    }
                };
                myThread.start();
            }
        });
    }


    //this just generates some simple data to be inserted into the database.
    public List<Score> generateScores() {
        final String[] FIRST = new String[]{
                "Jim", "Fred", "Allyson", "Danny", "Shaya"};
        final int[] SECOND = new int[]{
                3012, 56, 256, 1001, 2048};

        List<Score> scores = new ArrayList<Score>();
        for (int i = 0; i < FIRST.length; i++) {
            Score score = new Score(i, FIRST[i], SECOND[i]);
            //score.setId(i);
            //score.setName(FIRST[i]);
            //score.setScore(SECOND[i]);
            scores.add(score);
            logthis("adding data item " + i);
        }
        return scores;
    }

    //simple logger function to both the debug and logger textview.
    void logthis(String item) {
        if (item != null && item.compareTo("") != 0) {
            Log.d(TAG, item);
            logger.append(item + "\n");
        }
    }
}