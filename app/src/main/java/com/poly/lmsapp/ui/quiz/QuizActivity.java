package com.poly.lmsapp.ui.quiz;

import android.graphics.Color;
import android.util.Log;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.base.BaseActivity;
import com.poly.lmsapp.model.Quiz;

import java.util.ArrayList;

public class QuizActivity extends BaseActivity {
    private RecyclerView rvQuiz;
    private final ArrayList<Quiz> listQuiz = new ArrayList<>();

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_quiz);
    }

    @Override
    public void createView() {
        initView();
        super.createView();

    }

    private void initView() {
        rvQuiz = findViewById(R.id.rv_quiz);
        setToolbarTitle("Danh sách quiz");
        setTbColor(Color.BLACK);
    }

    @Override
    public void fetchData() {
        listQuiz.add(new Quiz(0, "12/12/2021", "Quiz 1"));
        listQuiz.add(new Quiz(1, "13/12/2021", "Quiz 2"));
        listQuiz.add(new Quiz(2, "14/12/2021", "Quiz 3"));
        listQuiz.add(new Quiz(3, "15/12/2021", "Quiz 4"));
        QuizAdapter adapter = new QuizAdapter(listQuiz, R.layout.item_quiz);
        rvQuiz.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        rvQuiz.setAdapter(adapter);
    }
}