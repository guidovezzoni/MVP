package com.guidovezzoni.mvplibrary.mvp.mainactivity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.guidovezzoni.mvplibrary.R;
import com.guidovezzoni.mvplibrary.adapter.ForecastAdapter;
import com.guidovezzoni.mvplibrary.model.Forecast;
import com.guidovezzoni.mvplibrary.model.List_;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {
    private MainActivityContract.Presenter mPresenter;

    private Toolbar mToolbar;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;

    private FloatingActionMenu mFabMenu;
    private FloatingActionButton mFabMenuLondon;
    private FloatingActionButton mFabMenuNY;

    @Override
    public MainActivityContract.Presenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new MainActivityPresenterImplementation();

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);


        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.viewNeedsData();
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.list_recyclerView);

        mFabMenu = (FloatingActionMenu) findViewById(R.id.fab_menu);

        mFabMenuLondon = (FloatingActionButton) findViewById(R.id.fab_menu_london);
        mFabMenuLondon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeRefreshLayout.setRefreshing(true);
                mFabMenu.close(true);
                mPresenter.viewPressedLondon();
            }
        });

        mFabMenuNY = (FloatingActionButton) findViewById(R.id.fab_menu_newyork);
        mFabMenuNY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeRefreshLayout.setRefreshing(true);
                mFabMenu.close(true);
                mPresenter.viewPressedNY();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.attachView(this);
        mSwipeRefreshLayout.setRefreshing(true);
        mPresenter.viewNeedsData();
    }

    /**
     * this method is called from presenter when data to show is available
     *
     * @param forecast downloaded data
     */
    @Override
    public void dataRequestSuccessful(Forecast forecast) {
        mRecyclerView.setAdapter(new ForecastAdapter(this, forecast.list));
        mToolbar.setTitle(forecast.city.name);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    /**
     * called from presenter when an error happens
     * @param message
     */
    @Override
    public void dataRequestError(String message) {
        mRecyclerView.setAdapter(new ForecastAdapter(this, new ArrayList<List_>()));
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        mToolbar.setTitle(R.string.data_unavailable);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    /**
     * network request was cancelled, f.i. due to a config change
     */
    @Override
    public void dataRequestCancelled() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
