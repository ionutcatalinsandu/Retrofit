package com.example.torridas.retrofit;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitTest extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText editText;
    private ProgressDialog progressDialog;
    private ManyResults manyResults;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_test);

        Button buttonSearch = (Button) findViewById(R.id.btn_cautare);
        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        recyclerView.setVisibility(View.INVISIBLE);
        editText = (EditText) findViewById(R.id.input);
        progressDialog = new ProgressDialog(RetrofitTest.this);

        NetworkBroadCastReciever networkBroadCastReciever = new NetworkBroadCastReciever();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        this.registerReceiver(networkBroadCastReciever, filter);


        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = editText.getText().toString();
                query = query.replace(' ','+');

                // nu m-am asigurat in cazul situatiile unde primesc null
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                //

                progressDialog.setMessage("Searching..");
                progressDialog.show();
                Retrofit retrofit = new Retrofit.Builder().baseUrl(Strings.GitHub_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create()).build();

                GitHubAPI gitHubAPI = retrofit.create(GitHubAPI.class); // muta-l
                Call<ManyResults> call = gitHubAPI.getMeRepos(query);
                call.enqueue(new Callback<ManyResults>() {
                    @Override
                    public void onResponse(Call<ManyResults> call, Response<ManyResults> response) {
                            manyResults = response.body();
                            progressDialog.dismiss();
                            Toast toast =  Toast.makeText(RetrofitTest.this, "Found: " + manyResults
                                    .getResults().size() + " items.", Toast.LENGTH_SHORT);
                            toast.show();
                            CostumRecyclerAdapter costumRecyclerAdapter = new CostumRecyclerAdapter(manyResults.getResults());
                            recyclerView.setLayoutManager(new GridLayoutManager(RetrofitTest.this, 2));
                            recyclerView.setAdapter(costumRecyclerAdapter);
                            recyclerView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onFailure(Call<ManyResults> call, Throwable t) {

                    }
                });

            }
        });
    }
}
