package com.flabbergast.herokuapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import org.json.*;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        ListView mListView = (ListView) findViewById(R.id.listView1);
        ArrayList<Quiz> kvizovi = new ArrayList<Quiz>();
        try {
            /**Loads parsed data from API https://iosquiz.herokuapp.com/api/quizzes
            into linked list kvizovi*/
            kvizovi = parseData(loadDataTest());
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        QuizListAdapter adapter = new QuizListAdapter(findViewById(android.R.id.content).getRootView(),this, R.layout.quiz_layout, kvizovi);
        mListView.setAdapter(adapter);
    }





    public String loadData() throws IOException {
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        URL url = new URL("https://iosquiz.herokuapp.com/api/quizzes");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        // Request setup
        con.setRequestMethod("GET");
        con.setConnectTimeout(15000);
        con.setReadTimeout(15000);

        con.setDoOutput(true);

        int status = con.getResponseCode();

        if(status > 299) {
            reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            while((line = reader.readLine()) != null) {
                responseContent.append(line);
            }
        } else {
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            while((line = reader.readLine()) != null) {
                responseContent.append(line);
            }
        }

        //TextView msg = findViewById(R.id.test1);
        //msg.setText("Test");
        String str = con.getInputStream().toString();
        con.disconnect();
        return str;
    }

    public String loadDataTest() throws IOException{
        //This is just a test string, API example
        String data = "{\"quizzes\":[{\"id\":4,\"title\":\"Basic sports knowledges\",\"description\":\"This is a quiz for beginners in sport.\",\"category\":\"SPORTS\",\"level\":1,\"image\":\"https://www.publicdomainpictures.net/pictures/300000/velka/football-strategy.jpg\",\"questions\":[{\"id\":31,\"question\":\"Which team won the NBA title in 2011?\",\"answers\":[\"Dallas Mavericks\",\"Miami Heat\",\"Houston Rockets\",\"Boston Celtics\"],\"correct_answer\":0},{\"id\":32,\"question\":\"What is the perimeter of the football ball (in cm)?\",\"answers\":[\"56-58\",\"60-62\",\"68-70\",\"74-76\"],\"correct_answer\":2},{\"id\":33,\"question\":\"Who was the most famous Croatian basketball player in the NBA?\",\"answers\":[\"Dario Saric\",\"Drazen Petrovic\",\"Bojan Bogdanovic\",\"Dino Radja\"],\"correct_answer\":1},{\"id\":34,\"question\":\"The first season of the Formula 1 race was in...\",\"answers\":[\"1948\",\"1950\",\"1952\",\"1954\"],\"correct_answer\":1},{\"id\":35,\"question\":\"What year did the Mediterranean Games take place in Split?\",\"answers\":[\"1977.\",\"1978.\",\"1979.\",\"1980.\"],\"correct_answer\":2},{\"id\":36,\"question\":\"Which country did not participate in the first Olympic games?\",\"answers\":[\"Denmark\",\"Hungary\",\"Switzerland\",\"Russia\"],\"correct_answer\":3},{\"id\":37,\"question\":\"What year did the first basketball match take place?\",\"answers\":[\"1890\",\"1891\",\"1893\",\"1895\"],\"correct_answer\":1},{\"id\":38,\"question\":\"How many throwing disciplines are there in the athletic decal?\",\"answers\":[\"2\",\"3\",\"4\",\"5\"],\"correct_answer\":1},{\"id\":39,\"question\":\"Where did the boxer Mate Parlov win gold at the Olympics?\",\"answers\":[\"Rome 1960.\",\"Tokyo 1964.\",\"Mexico City 1968.\",\"MĂĽnchen 1972.\"],\"correct_answer\":3},{\"id\":40,\"question\":\"The Croatian national team won the silver medal at the 2018. World Cup in...?\",\"answers\":[\"Germany\",\"France\",\"Russia\",\"Poland\"],\"correct_answer\":2}]},{\"id\":5,\"title\":\"Intermediate sports knowledge\",\"description\":\"Quiz for serious sport experts.\",\"category\":\"SPORTS\",\"level\":2,\"image\":\"https://www.publicdomainpictures.net/pictures/10000/velka/yacht-at-the-sea-871287485176pSjW.jpg\",\"questions\":[{\"id\":41,\"question\":\"Italian Football Club A.S. Bari plays their matches at?\",\"answers\":[\"Stadio Enzo Ricci\",\"Stadio San Paolo\",\"Stadio San Nicola\",\"Stadio Delle Alpi\"],\"correct_answer\":2},{\"id\":42,\"question\":\"The best scorer in the English Football League is?\",\"answers\":[\"Thierry Henry\",\"Andrew Cole\",\"Alan Shearer\",\"Gary Lineker\"],\"correct_answer\":2},{\"id\":43,\"question\":\"Bjorn Borg won Wimbledon _ times in a row.\",\"answers\":[\"3\",\"7\",\"9\",\"5\"],\"correct_answer\":3},{\"id\":44,\"question\":\"In what year did the Croatian men's basketball team win silver at the OG?\",\"answers\":[\"1992.\",\"1996.\",\"2000.\",\"2004.\"],\"correct_answer\":0},{\"id\":45,\"question\":\"The height of the tennis net at the edges is?\",\"answers\":[\"92 cm\",\"107 cm\",\"124 cm\",\"147 cm\"],\"correct_answer\":1},{\"id\":46,\"question\":\"_ won the NBA title in 2008.\",\"answers\":[\"Orlando Magic\",\"Boston Celtics\",\"Dallas Mavericks\",\"San Antonio Spurs\"],\"correct_answer\":1},{\"id\":47,\"question\":\"Which club won UEFA Champions League 2010?\",\"answers\":[\"Inter\",\"Real Madrid\",\"Bayern\",\"Barcelona\"],\"correct_answer\":0},{\"id\":48,\"question\":\"The best golfer is?\",\"answers\":[\"Kevin Durant\",\"Robert Fischer\",\"Mario Andretti\",\"Tiger Woods\"],\"correct_answer\":3},{\"id\":49,\"question\":\"The best scorer in the Spanish Football League is?\",\"answers\":[\"Zarra\",\"Hugo Sanchez\",\"Blanco Raul\",\"Cesar\"],\"correct_answer\":0},{\"id\":50,\"question\":\"Width of the goal in handball is?\",\"answers\":[\"3 m\",\"3,2 m\",\"3,4 m\",\"3,6 m\"],\"correct_answer\":0}]},{\"id\":6,\"title\":\"Basic science knowledge\",\"description\":\"Quiz for science lovers.\",\"category\":\"SCIENCE\",\"level\":1,\"image\":\"https://www.publicdomainpictures.net/pictures/240000/velka/striding-edge-and-helvellyn-1508577364c7J.jpg\",\"questions\":[{\"id\":51,\"question\":\"What is the normal temperature of the human body(in C)?\",\"answers\":[\"35\",\"36\",\"37\",\"38\"],\"correct_answer\":2},{\"id\":52,\"question\":\"In chemistry silver is denoted by _\",\"answers\":[\"S\",\"Ag\",\"Sr\",\"Se\"],\"correct_answer\":1},{\"id\":53,\"question\":\"Which is not magnetic storage media?\",\"answers\":[\"hard disc\",\"floopy disc\",\"zip disc\",\"compact disc\"],\"correct_answer\":3},{\"id\":54,\"question\":\"Color recognition disorder is called?\",\"answers\":[\"astigmatism\",\"myopia\",\"amblyopia\",\"daltonism\"],\"correct_answer\":3},{\"id\":55,\"question\":\"Extension \\\"xslx\\\" points to?\",\"answers\":[\"sound\",\"text\",\"spreadsheet\",\"presentation\"],\"correct_answer\":2},{\"id\":56,\"question\":\"The magnetic field strength measuring unit is?\",\"answers\":[\"Tesla\",\"Weber\",\"Ohm\",\"Watt\"],\"correct_answer\":0},{\"id\":57,\"question\":\"The color of Emerald is _.\",\"answers\":[\"blue\",\"red\",\"yellow\",\"green\"],\"correct_answer\":3},{\"id\":58,\"question\":\"The chemical name of sulfuric acid is?\",\"answers\":[\"H2SO4\",\"H2S\",\"SO2\",\"H2SO2\"],\"correct_answer\":0},{\"id\":59,\"question\":\"Science that studies plants is called?\",\"answers\":[\"botany\",\"ecology\",\"forestry\",\"agronomy\"],\"correct_answer\":0},{\"id\":60,\"question\":\"Anosmia is loss of the sense of?\",\"answers\":[\"vision\",\"hearing\",\"scents\",\"contact\"],\"correct_answer\":2}]}]}\n";
        return data;
    }

    public ArrayList<Quiz> parseData(String jsonString) throws JSONException {
        JSONObject obj = new JSONObject(jsonString);
        JSONArray arr = obj.getJSONArray("quizzes");
        ArrayList<Quiz> kvizovi = new ArrayList();

        for (int i = 0; i < arr.length(); i++) {
            //Parse through the quizes
            ArrayList<Question> pitanja = new ArrayList<Question>();

            JSONArray nizPitanja = arr.getJSONObject(i).getJSONArray("questions");



            //for a test this will be deactivated because questions will be parsed in quizActivity

            /**
            for (int j = 0; j < nizPitanja.length(); j++){
                //Parse through the questions
                Question pitanje = new Question(nizPitanja.getJSONObject(j).getInt("id"),
                        nizPitanja.getJSONObject(j).getString("question"),
                        nizPitanja.getJSONObject(j).getJSONArray("answers"),
                        nizPitanja.getJSONObject(j).getInt("correct_answer"));
                pitanja.add(pitanje);
            }

            Quiz kviz = new Quiz(arr.getJSONObject(i).getInt("id"),
                    arr.getJSONObject(i).getString("title"),
                    arr.getJSONObject(i).getString("description"),
                    arr.getJSONObject(i).getString("category"),
                    arr.getJSONObject(i).getInt("level"),
                    arr.getJSONObject(i).getString("image"),
                    pitanja);
            kvizovi.add(kviz);
        }*/

            Quiz kviz = new Quiz(arr.getJSONObject(i).getInt("id"),
                    arr.getJSONObject(i).getString("title"),
                    arr.getJSONObject(i).getString("description"),
                    arr.getJSONObject(i).getString("category"),
                    arr.getJSONObject(i).getInt("level"),
                    arr.getJSONObject(i).getString("image"),
                    arr.getJSONObject(i).getString("questions"));
            kvizovi.add(kviz);
        }

        //TextView msg = findViewById(R.id.test1);
        //msg.setText(kvizovi.get(2).getCategory());
        return kvizovi;
    }


}