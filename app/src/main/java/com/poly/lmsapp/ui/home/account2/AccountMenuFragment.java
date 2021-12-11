package com.poly.lmsapp.ui.home.account2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.utils.PersonSingleton;
import com.poly.lmsapp.commons.utils.RenderImage;
import com.poly.lmsapp.model.MenuDraw;
import com.poly.lmsapp.model.User;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AccountMenuFragment extends Fragment {
    private ImageView mIvAvatar;
    private RecyclerView mRvMenu;
    private TextView tvName;
    private User user;
    private static AccountMenuFragment accountMenuFragment;

    private ArrayList<MenuDraw> listMenu = new ArrayList<>();

    public static AccountMenuFragment getInstance() {
        if (accountMenuFragment == null) accountMenuFragment = new AccountMenuFragment();
        return accountMenuFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_menu, container, false);


        mIvAvatar = view.findViewById(R.id.iv_avatar);
        mRvMenu = view.findViewById(R.id.rv_menu);
        tvName = view.findViewById(R.id.tv_name);listMenu.clear();
        listMenu.add(new MenuDraw(R.drawable.ic_detail, "Thông tin tài khoản"));
        listMenu.add(new MenuDraw(R.drawable.ic_update, "Cập nhật thông tin tài khoản"));
        listMenu.add(new MenuDraw(R.drawable.ic_password, "Đổi mật khẩu"));
        listMenu.add(new MenuDraw(R.drawable.ic_exit, "Đăng xuất"));
        user = PersonSingleton.getInstance().getUser();
        if (user != null) {
            tvName.setText(user.getName());
            RenderImage.loadImageNetwork(user.getAvatar(), mIvAvatar);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AccountMenuAdapter accountMenuAdapter = new AccountMenuAdapter(listMenu, R.layout.item_account_menu);
//        mRvMenu.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mRvMenu.setAdapter(accountMenuAdapter);
    }

    public TextView getTvName() {
        return tvName;
    }

    public void setTvName(TextView tvName) {
        this.tvName = tvName;
    }

    public ImageView getmIvAvatar() {
        return mIvAvatar;
    }

    public void setmIvAvatar(ImageView mIvAvatar) {
        this.mIvAvatar = mIvAvatar;
    }
}