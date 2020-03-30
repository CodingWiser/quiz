package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;



import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity implements QuizRecyclerViewAdapter.OnIncrementListener {
    private RecyclerView quizRecyclerview;
    private Button button;
    List<QuizModel> list ;
    private QuizRecyclerViewAdapter.OnIncrementListener mListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quizRecyclerview = (RecyclerView) findViewById(R.id.recyclerView);
        button = (Button) findViewById(R.id.button);
        mListener = this;
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);
        quizRecyclerview.setLayoutManager(recyclerLayoutManager);
//        list = getPackages();

        String jsonFileString = Utils.getJsonFromAssets(getApplicationContext(), "Quiz.json");
        Log.i("data", jsonFileString);

        Gson gson = new Gson();
        Type listUserType = new TypeToken<List<QuizModel>>() { }.getType();
        list = new ArrayList<QuizModel>();
        list = gson.fromJson(jsonFileString, listUserType);
//        for (int i = 0; i < list.size(); i++) {
//            Log.i("data", "> Item " + i + "\n" + list.get(i));
//        }

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(quizRecyclerview.getContext(),
                        recyclerLayoutManager.getOrientation());
        quizRecyclerview.addItemDecoration(dividerItemDecoration);

        QuizRecyclerViewAdapter recyclerViewAdapter = new
                QuizRecyclerViewAdapter(mListener,list,this);
        quizRecyclerview.setAdapter(recyclerViewAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int score=0;
                int unansered=0;
                int ansered=0;
                for(int i=0;i<list.size();i++){

//                    Log.e("ITEM", "pos "+i+ list.get(i).getSelectedposition());
//                    Log.e("ITEM", "position"+ list.get(i).getSublist().get(list.get(i).getSelectedposition()));
                    if (list.get(i).isIschecked()){
                        ansered++;
                        if (list.get(i).getSublist().get(list.get(i).getSelectedposition()).equalsIgnoreCase(list.get(i).getAnswer())){
                            score++;
                        }
                    }else {
                        unansered++;
                    }

                }
//                Log.i("Score","s "+ score);

                final Dialog dialog = new Dialog(MainActivity.this);

                dialog.setContentView(R.layout.custom_dialog);
                dialog.setTitle("Custom Alert Dialog");

                TextView totalQstnTv = (TextView) dialog.findViewById(R.id.totalQstnTv);
                TextView scoreTv = (TextView) dialog.findViewById(R.id.scoreTv);
                TextView anseredTv = (TextView) dialog.findViewById(R.id.anseredTv);
                TextView unanseredTv = (TextView) dialog.findViewById(R.id.unanseredTv);
                totalQstnTv.setText(""+list.size());
                scoreTv.setText(""+score);
                unanseredTv.setText(""+unansered);
                anseredTv.setText(""+ansered);
                dialog.show();

            }
        });

    }

    @Override
    public void onNumberIncremented(int adapterPosition, int i) {
//        Log.e("ITEM", "main"+adapterPosition+" sub "+ i);
        list.get(adapterPosition).setSelectedposition(i);
        list.get(adapterPosition).setIschecked(true);

    }
}
