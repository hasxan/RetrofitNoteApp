package com.example.hassansardar.retrofitnoteapp.activity.main;

import com.example.hassansardar.retrofitnoteapp.model.Note;

import java.util.List;

public interface MainView {

    void showLoading();
    void hideLoading();
    void onGetResult(List<Note> notes);
    void onErrorLoading(String message);
    
}
