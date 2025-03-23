package com.example.challenge_asgard.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.challenge_asgard.R;

public class LoginPagerAdapter extends RecyclerView.Adapter<LoginViewHolder> {
    private static final int NUM_PAGES = 2;
    private static final int LOGIN_PAGE = 0;
    private static final int REGISTER_PAGE = 1;

    private Context context;
    private View loginView;
    private View registerView;

    public LoginPagerAdapter(Context context) {
        this.context = context;

        // Inflate views for login and registration
        loginView = LayoutInflater.from(context).inflate(R.layout.fragment_login, null);
        registerView = LayoutInflater.from(context).inflate(R.layout.fragment_register, null);
    }

    @NonNull
    @Override
    public LoginViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == LOGIN_PAGE) {
            view = loginView;
        } else {
            view = registerView;
        }

        // Configure the view to match parent dimensions
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(layoutParams);

        return new LoginViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoginViewHolder holder, int position) {
        // No additional binding needed as views are already inflated
        // and references are kept in the activity
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    // Methods to access the views directly from LoginActivity
    public View getLoginView() {
        return loginView;
    }

    public View getRegisterView() {
        return registerView;
    }
}

class LoginViewHolder extends RecyclerView.ViewHolder {
    public LoginViewHolder(@NonNull View itemView) {
        super(itemView);
        // References to individual UI elements are managed in LoginActivity
    }
}