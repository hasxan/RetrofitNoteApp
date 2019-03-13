package com.example.hassansardar.retrofitnoteapp.activity.editor;

import com.example.hassansardar.retrofitnoteapp.api.ApiClient;
import com.example.hassansardar.retrofitnoteapp.api.ApiInterface;
import com.example.hassansardar.retrofitnoteapp.model.Note;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorPresenter {

    private EditorView view;

    public EditorPresenter(EditorView view) {
        this.view = view;
    }

    void saveNote(final String title, final String note, final int color) {
        view.showProgress();

        ApiInterface apiInterface = ApiClient.getApiClient().
                create(ApiInterface.class);
        Call<Note> call = apiInterface.saveNote(title, note, color);
        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(Call<Note> call, Response<Note> response) {
                view.hideProgress();
                if (response.isSuccessful() && response.body() != null) {
                    Boolean success = response.body().getSuccess();
                    if (success) {
                        view.onAddSuccess(response.body().getMessage());
//                        Toast.makeText(EditorActivity.this, response.body().getMessage(),
//                                Toast.LENGTH_SHORT).show();
//                        finish();
                    } else {
                        view.onAddError(response.body().getMessage());
//                        Toast.makeText(EditorActivity.this,response.body().getMessage(),
//                                Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Note> call, Throwable t) {
                view.hideProgress();
                view.onAddError(t.getLocalizedMessage());

            }
        });
    }

}
