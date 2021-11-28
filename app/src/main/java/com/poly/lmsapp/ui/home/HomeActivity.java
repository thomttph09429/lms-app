package com.poly.lmsapp.ui.home;

import android.content.Intent;
import android.os.Build;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.base.BaseActivity;
import com.poly.lmsapp.commons.network.Client;
import com.poly.lmsapp.commons.utils.FilePicker;
import com.poly.lmsapp.commons.utils.PersonSingleton;
import com.poly.lmsapp.commons.utils.Utils;
import com.poly.lmsapp.model.BaseResponse;
import com.poly.lmsapp.model.User;
import com.poly.lmsapp.ui.home.account.AccountFragment;
import com.poly.lmsapp.ui.home.recent_class.RecentClassFragment;
import com.poly.lmsapp.ui.home.repository.RepositoryFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        fetchData();
    }

    @Override
    public void fetchData() {
        Client.getInstance().getInfo().enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();
                if(baseResponse != null && baseResponse.getError().getCode() == 0){
                    User user = (User) Utils.jsonDecode(baseResponse.getData(),User.class  );
                    PersonSingleton.getInstance().setUser(user);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
    }

    private void initActions() {

        activeFragment = RepositoryFragment.getInstance();
        loadFragment(activeFragment);
        navigation.setOnItemSelectedListener(item -> {

            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.nav_home:
                    fragment = RepositoryFragment.getInstance();
                    loadFragment(fragment);
                    return true;
                case R.id.nav_recent_class:
                    fragment = RecentClassFragment.getInstance();
                    loadFragment(fragment);
                    return true;
                case R.id.nav_account:
                    fragment = AccountFragment.getInstance();
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        FilePicker.callBackPicker(requestCode, resultCode, data);
        FilePicker.convertBase64(FilePicker.getFile());
        super.onActivityResult(requestCode, resultCode, data);
    }
}