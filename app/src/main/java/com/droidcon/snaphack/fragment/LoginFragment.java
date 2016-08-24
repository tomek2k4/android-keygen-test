package com.droidcon.snaphack.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.droidcon.snaphack.MainActivity;
import com.droidcon.snaphack.R;
import com.droidcon.snaphack.manager.KeyManager;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginFragment extends Fragment {

    @InjectView(R.id.username)
    EditText username;
    @InjectView(R.id.password)
    EditText password;
    @InjectView(R.id.enter)
    Button enter;

    MainActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @OnClick(R.id.enter)
    public void onEnter() {
        enter.setEnabled(false);
        final String usernameString = username.getText().toString();
//        if(usernameString.equals("student") && password.getText().toString().equals("student")){
            enter.setEnabled(true);
            try {
                new KeyManager(getActivity()).save(usernameString+"Key");
            } catch (NoSuchProviderException e) {
                Snackbar.make(getView(), e.getMessage(), Snackbar.LENGTH_LONG);
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                Snackbar.make(getView(), e.getMessage(), Snackbar.LENGTH_LONG);
                e.printStackTrace();
            } catch (InvalidAlgorithmParameterException e) {
                Snackbar.make(getView(), e.getMessage(), Snackbar.LENGTH_LONG);
                e.printStackTrace();
            }
            mainActivity.loggedIn();
//        }else{
//            enter.setEnabled(true);
//            Snackbar.make(getView(), "Login Failed. ", Snackbar.LENGTH_LONG);
//        }

//        new ServiceManager(getActivity()).login(usernameString, password.getText().toString(), new Callback<LoginResponse>() {
//            @Override
//            public void success(LoginResponse loginResponse, Response response) {
//                enter.setEnabled(true);
//                new KeyManager(getActivity()).save(loginResponse.getKey() + usernameString);
//                mainActivity.loggedIn();
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                enter.setEnabled(true);
//                Snackbar.make(getView(), "Login Failed. " + error.getLocalizedMessage(), Snackbar.LENGTH_LONG);
//            }
//        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }
}
