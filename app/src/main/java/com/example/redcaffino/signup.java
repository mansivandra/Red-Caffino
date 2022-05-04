package com.example.redcaffino;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.redcaffino.model.user;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

public class signup extends AppCompatActivity {
    EditText edtPhone,edtPassword,edtname;
    Button btnsignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edtname=(EditText) findViewById(R.id.edtName);
        edtPassword=(EditText) findViewById(R.id.edtpassword);
        edtPhone=(EditText) findViewById(R.id.edtphone);

        btnsignup=(Button) findViewById(R.id.btnsignup);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table_user = database.getReference("user");

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog mdialog=new ProgressDialog(signup.this);
                mdialog.setMessage("Please Waiting....");
                mdialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.child(edtPhone.getText().toString()).exists())
                        {
                            mdialog.dismiss();
                            Toast.makeText(signup.this, "Phone Number Already Register...", Toast.LENGTH_SHORT).show();
                        }
                        else
                            {
                                mdialog.dismiss();
                                user User=new user(edtname.getText().toString(),edtPassword.getText().toString());
                                table_user.child(edtPhone.getText().toString()).setValue(User);
                                Toast.makeText(signup.this, "Sign Up Successfully...", Toast.LENGTH_SHORT).show();
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
}