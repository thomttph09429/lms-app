package com.poly.lmsapp.ui.home;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.base.BaseActivity;
import com.poly.lmsapp.commons.network.Client;
import com.poly.lmsapp.commons.utils.Utils;
import com.poly.lmsapp.model.BasePageResponse;
import com.poly.lmsapp.model.BaseResponse;
import com.poly.lmsapp.model.Repository;
import com.poly.lmsapp.ui.account.AccountFragment;
import com.poly.lmsapp.ui.repository.RepositoryFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity {

    private BottomNavigationView navigation;
    private Fragment activeFragment;

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_home);
    }

    @Override
    public void createView() {
        setShowBack(false);
        initViews();
        initActions();
    }


    private void initActions() {

        activeFragment =RepositoryFragment.getInstance();
        loadFragment(activeFragment);
        navigation.setOnItemSelectedListener(item -> {

            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.nav_home:
                    fragment =  RepositoryFragment.getInstance();
                    loadFragment(fragment);
                    return true;
                case R.id.nav_account:
                    fragment =AccountFragment.getInstance();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        });

    }

    private void initViews() {
        navigation = findViewById(R.id.navigation);

    }


    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}