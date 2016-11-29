package com.example.nos.secureflomessaging;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import com.example.nos.secureflomessaging.data.User;
import com.example.nos.secureflomessaging.webservices.WebServiceTask;
import com.example.nos.secureflomessaging.webservices.WebServiceUtils;

import org.json.JSONObject;

import java.util.regex.Pattern;

public class UserLoginActivity extends AppCompatActivity {
    private UserLoginTask mUserLoginTask = null;
    private EditText mUserView;
    private EditText mPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        initViews();
    }

    private void initViews(){
        mUserView = (EditText) findViewById(R.id.user);
        mPasswordView = (EditText) findViewById(R.id.password);
    }

    public void attemptLogin(View view) {
        if (mUserLoginTask != null) {
            return;
        }

        mUserView.setError(null);
        mPasswordView.setError(null);

        String user = mUserView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_incorrect_password));
            focusView = mPasswordView;
            cancel = true;
        }

        if (TextUtils.isEmpty(user)) {
            mUserView.setError(getString(R.string.error_field_required));
            focusView = mUserView;
            cancel = true;
        } else if (!isUserValid(user)) {
            mUserView.setError(getString(R.string.error_invalid_email));
            focusView = mUserView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            mUserLoginTask = new UserLoginTask(user, password, view.getId() == R.id.email_sign_in_button);
            mUserLoginTask.execute((Void) null);
        }
    }




    private boolean isPasswordValid(String password){
            return password.length() > 4;
    }
    private boolean isUserValid(String user){
        return user.length() > 4;
    }

    private void showProgress(final boolean isShow){
        findViewById(R.id.login_progress).setVisibility(isShow ? View.VISIBLE : View.GONE);
        findViewById(R.id.login_form).setVisibility(isShow ? View.GONE : View.VISIBLE);
    }



    private class UserLoginTask extends WebServiceTask{
        private final ContentValues contentValues = new ContentValues();
        private boolean mIsLogin;

        UserLoginTask(String user, String password, boolean isLogin){
            super(UserLoginActivity.this);
            contentValues.put(Constants.USERNAME, user);
            contentValues.put(Constants.PASSWORD, password);
            contentValues.put(Constants.GRANT_TYPE, Constants.CLIENT_CREDENTIALS);
            mIsLogin = isLogin;

        }

        @Override
        public void showProgress() {
            UserLoginActivity.this.showProgress(true);
        }

        @Override
        public void hideProgress() {
            UserLoginActivity.this.showProgress(false);
        }

        @Override
        public boolean performRequest() {
            JSONObject obj = WebServiceUtils.requestJSONObject(mIsLogin ? Constants.LOGIN_URL : Constants.LOGIN_URL,
                    WebServiceUtils.METHOD.POST, contentValues, true);
            mUserLoginTask = null;
            if(!hasError(obj)){
                if(mIsLogin){
                    User user = new User();
                    user.setId(obj.optLong(Constants.ID));
                    user.setUser(contentValues.getAsString(Constants.USERNAME));
                    user.setPassword(contentValues.getAsString(Constants.PASSWORD));
                    RESTServiceApplication.getInstance().setUser(user);
                    RESTServiceApplication.getInstance().setAccessToken(
                            obj.optJSONObject(Constants.ACCESS).optString(Constants.ACCESS_TOKEN));
                    return true;
                } else{
                    mIsLogin = true;
                    performRequest();
                    return true;
                }
            }
            return false;
        }

        @Override
        public void performSuccessfulOperation() {
            Intent intent = new Intent(UserLoginActivity.this, Inbox.class);
        }
    }
}
