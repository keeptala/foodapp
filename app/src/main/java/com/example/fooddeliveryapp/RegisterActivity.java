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

        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

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

                            // TO DO
                            // fix the user already register  if the  phone number matches  on that we have already in the database


                            checkCredentials();
                        } else if (edtTxtPassword.getText().toString().equals("") || !edtTxtPassword.getText().toString().matches(regex)){
                            mDialog.dismiss();
                            txtWarnPassword.setVisibility(View.VISIBLE);
                            txtWarnPassword.setText("weak password !");
                            Toast.makeText(RegisterActivity.this, "password must have @least one uppercase, lowercase and a symbol ", Toast.LENGTH_SHORT).show();
                            txtWarnEmail.setVisibility(View.GONE);
                        }
                        else {
                            txtWarnPassword.setVisibility(View.GONE);
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



    }

    private void checkCredentials() {
        String name = edtTextUserName.getText().toString();
        String phone = edtRegisterPhone.getText().toString();

        if (name.isEmpty() || name.length() < 4){
            txtWarnUserName.setVisibility(View.VISIBLE);
            txtWarnUserName.setText("Username not valid");
        } else if (phone.isEmpty() || !phone.matches("^[0-9]{10,}$")){
            txtWarnEmail.setVisibility(View.VISIBLE);
            txtWarnEmail.setText("phone number not valid");
            txtWarnUserName.setVisibility(View.GONE);
        } else {

        }
    }

    private void txtOnClickTxt() {
        txtGetToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }



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