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

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG  = "RegisterActivity";
    private EditText edtTextUserName, edtRegisterPhone, edtTxtPassword;
    private Button btnGetToLogin;
    private TextView txtGetToLogin, txtWarnUserName, txtWarnEmail, txtWarnPassword;
    private RelativeLayout parent;
    private CheckBox checkBoxShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initializeRegister();
        txtOnClickTxt();

        checkBoxShow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    edtTxtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    edtTxtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnGetToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(RegisterActivity.this);
                mDialog.setMessage("Loading, Please be patient :)");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //check if the user already has an account registered
                        if (snapshot.child(edtRegisterPhone.getText().toString()).exists()){
                            mDialog.dismiss();

                            if (!edtTextUserName.getText().toString().equals("")){
                                txtWarnUserName.setVisibility(View.GONE);
                                if (!edtRegisterPhone.getText().toString().equals("")){
                                    txtWarnEmail.setVisibility(View.GONE);
                                    if (!edtTxtPassword.getText().toString().equals("")){
                                        txtWarnPassword.setVisibility(View.GONE);
                                        Toast.makeText(RegisterActivity.this, "You are already registered using this phone: "+edtRegisterPhone.getText().toString(), Toast.LENGTH_SHORT).show();
                                    }else{
                                        txtWarnPassword.setVisibility(View.VISIBLE);
                                        txtWarnPassword.setText("Please enter your password !");
                                    }
                                }else {
                                    txtWarnEmail.setVisibility(View.VISIBLE);
                                    txtWarnEmail.setText("Please enter your phone number !");
                                }
                            } else {
                                txtWarnUserName.setVisibility(View.VISIBLE);
                                txtWarnUserName.setText("Please enter your username !");
                                Toast.makeText(RegisterActivity.this, " Fill all the forms ! ", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            User user = new User(edtTextUserName.getText().toString(), edtTxtPassword.getText().toString());
                            table_user.child(edtRegisterPhone.getText().toString()).setValue(user);
                            Toast.makeText(RegisterActivity.this, "You have Registered successfully", Toast.LENGTH_SHORT).show();
                            Intent register = new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(register);
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

//        btnGetToLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Register();
//                        if (ValidateData()){
////            showSnackbar();
//        } else{
//            Toast.makeText(RegisterActivity.this, " Please fill all the fields ", Toast.LENGTH_SHORT).show();
//        }
//            }
//        });

    }

//    private void Register() {
//        Log.d(TAG, "Register: Started");
//        if (ValidateData()){
//            showSnackbar();
//        } else{
//            Toast.makeText(RegisterActivity.this, " Please fill all the fields ", Toast.LENGTH_SHORT).show();
//        }
//    }

//    private void showSnackbar() {
//        Log.d(TAG, "showSnackbar: Started");
//        txtWarnPassword.setVisibility(View.GONE);
//        txtWarnEmail.setVisibility(View.GONE);
//        txtWarnUserName.setVisibility(View.GONE);
//        txtWarnPassRepeat.setVisibility(View.GONE);
//
//        Snackbar.make(parent, "User Registered", Snackbar.LENGTH_INDEFINITE)
//                .setAction("Login", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent3 = new Intent(RegisterActivity.this, LoginActivity.class);
//                        startActivity(intent3);
//                    }
//                }).show();
//    }
//
//    private boolean ValidateData() {
//
//        Log.d(TAG, "ValidateData: Started");
//        if (edtTextUserName.getText().toString().equals("")){
//            txtWarnUserName.setVisibility(View.VISIBLE);
//            txtWarnUserName.setText("Please Enter Your Username!");
//            return false;
//        }
//
//        if (edtRegisterPhone.getText().toString().equals("")){
//            txtWarnEmail.setVisibility(View.VISIBLE);
//            txtWarnEmail.setText("Please Enter Your Email!");
//            return false;
//        }
//
//        if (edtTxtPassword.getText().toString().equals("")){
//            txtWarnPassword.setVisibility(View.VISIBLE);
//            txtWarnPassword.setText("Please Enter You Password!");
//            return false;
//        }
//
//        if (edtTxtpassRepeat.getText().toString().equals("Please repeat your Password")){
//            txtWarnPassRepeat.setVisibility(View.VISIBLE);
//            return false;
//        }
//
//        if (!edtTxtPassword.getText().toString().equals(edtTxtpassRepeat.getText().toString())){
//            txtWarnPassRepeat.setVisibility(View.VISIBLE);
//            txtWarnPassRepeat.setText("The Passwords don't match !");
//            return false;
//        }
//
//        return true;
//    }

    private void txtOnClickTxt() {
        txtGetToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }


 /*   private void btnOnClickBtn() {
        btnGetToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }*/

    private void initializeRegister() {
        checkBoxShow = findViewById(R.id.checkBoxShow);
        txtWarnPassword = findViewById(R.id.txtWarnPassword);
        txtWarnEmail = findViewById(R.id.txtWarnEmail);
        txtWarnUserName = findViewById(R.id.txtWarnUserName);
        edtTxtPassword = findViewById(R.id.edtTxtPassword);
        edtRegisterPhone = findViewById(R.id.edtRegisterPhone);
        edtTextUserName = findViewById(R.id.edtTxtUserName);
        parent = findViewById(R.id.parent);
        btnGetToLogin = findViewById(R.id.btnGetToLogin);
        txtGetToLogin = findViewById(R.id.btnGetToLoginTwo);
    }

}