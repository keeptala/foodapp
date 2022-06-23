package com.example.fooddeliveryapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Model.User;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private TextView txtGoToRegister, txtWarnEmail, txtWarnPassword;
    private EditText edtLoginPhone, edtLoginPassword;
    private RelativeLayout parent;
    private Button btnLoginButton;
    private CheckBox checkboxShowPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeGoTORegister();
        getToRegister();

        checkboxShowPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    edtLoginPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    edtLoginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table_user = database.getReference("User");


        btnLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProgressDialog mDialog = new ProgressDialog(LoginActivity.this);
                    mDialog.setMessage("loading , be patient :) ");
                    mDialog.show();

                    table_user.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            //check if user exists on the database

                                    if (snapshot.child(edtLoginPhone.getText().toString()).exists() && !edtLoginPhone.getText().toString().equals("")) {
                                        //getting user's information
                                        txtWarnEmail.setVisibility(View.GONE);
                                        mDialog.dismiss();
                                        User user = snapshot.child(edtLoginPhone.getText().toString()).getValue(User.class);
                                        if (user.getPassword().equals(edtLoginPassword.getText().toString()) && !edtLoginPassword.getText().toString().equals("")) {
                                            txtWarnPassword.setVisibility(View.GONE);
                                            Toast.makeText(LoginActivity.this, "login successful", Toast.LENGTH_SHORT).show();
                                            Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
                                            startActivity(homeIntent);

                                        } else {
                                            txtWarnPassword.setVisibility(View.VISIBLE);
                                            txtWarnPassword.setText("Enter a non empty and correct password !");


                                        }
                                    } else {
                                        mDialog.dismiss();
                                        txtWarnEmail.setVisibility(View.VISIBLE);
                                        txtWarnEmail.setText("Enter a non empty and correct phone number!");
                                        Toast.makeText(LoginActivity.this, "You have to fill in the your correct number and Password !", Toast.LENGTH_SHORT).show();

                                    }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
            }
        });





    }

//    private boolean checkLogin() {
//        if (!edtLoginPhone.getText().toString().equals("") && !edtLoginPassword.getText().toString().equals("")){
//            txtWarnEmail.setVisibility(View.GONE);
//            txtWarnPassword.setVisibility(View.GONE);
//            return false;
//        } else {
//            Toast.makeText(LoginActivity.this, " Fill all the forms ! ", Toast.LENGTH_SHORT).show();
//            txtWarnEmail.setVisibility(View.VISIBLE);
//            txtWarnEmail.setText("Please enter your phone number !");
//
//            txtWarnPassword.setVisibility(View.VISIBLE);
//            txtWarnPassword.setText("Please enter your password !");
//            return false;
//        }
//    }


    private void getToRegister() {
        txtGoToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, TermsActivity.class);
                startActivity(intent);
            }
        });
    }

//
//    private void Login() {
//        Log.d(TAG, "Login: Started");
//
//        if (validateData()){
//          showSnackBar();
//        } else {
//            Toast.makeText(LoginActivity.this, "You have to fill in your phone number and password", Toast.LENGTH_SHORT).show();
//        }
//    }

//    private void showSnackBar(){
//        Log.d(TAG, "showSnackBar: Started");
//        txtWarnEmail.setVisibility(View.GONE);
//        txtWarnPassword.setVisibility(View.GONE);
//
////        Snackbar.make(parent, "Logged in", Snackbar.LENGTH_INDEFINITE)
////                .setAction("Register", new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////                        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
////                        startActivity(intent);
////                    }
////                }).show();
//    }




//    private boolean validateData(){
//        Log.d(TAG, "validateData: Started");
//        if (edtLoginPhone.getText().toString().equals("")){
//            txtWarnEmail.setVisibility(View.VISIBLE);
//            txtWarnEmail.setText("Please Enter Your Phone number !");
//            return false;
//        }
//
//        if (edtLoginPassword.getText().toString().equals("")){
//            txtWarnPassword.setVisibility(View.VISIBLE);
//            txtWarnPassword.setText("Please Enter Your Password !");
//            return false;
//        }
//        return true;
//    }

    private void initializeGoTORegister() {

        txtGoToRegister = findViewById(R.id.txtGoToRegister);
        parent = findViewById(R.id.parent);
        txtWarnEmail = findViewById(R.id.txtWarnEmail);
        txtWarnPassword = findViewById(R.id.txtWarnPassword);
        edtLoginPhone = findViewById(R.id.edtLoginPhone);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);
        btnLoginButton = findViewById(R.id.addToCartBtn);
        checkboxShowPass = findViewById(R.id.checkboxShowPass);

    }
}