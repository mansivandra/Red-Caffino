package com.example.redcaffino;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.redcaffino.Common.Common;
import com.example.redcaffino.model.user;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

public class signin extends AppCompatActivity {
    EditText edtPhone,edtPassword;
    Button btnsignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        edtPhone=(EditText) findViewById(R.id.edtphone);
        edtPassword=(EditText) findViewById(R.id.edtpassword);
        btnsignIn=(Button) findViewById(R.id.btnsignin);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table_user = database.getReference("user");

        btnsignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


             final ProgressDialog mdialog=new ProgressDialog(signin.this);
                mdialog.setMessage("Please Waiting....");
                mdialog.show();

                table_user.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.child(edtPhone.getText().toString()).exists()) {

                            mdialog.dismiss();

                            user User = snapshot.child(edtPhone.getText().toString()).getValue(user.class);
                            if (User.getPassword().equals(edtPassword.getText().toString()))
                            {
                                Intent homeintent=new Intent(signin.this,Home.class);
                                Common.currentuser = User;
                                startActivity(homeintent);
                                finish();
                            }
                            else
                                {
                                Toast.makeText(signin.this, "Wrong password or name  !!!", Toast.LENGTH_SHORT).show();
                                }
                        }
                        else
                            {
                                //mdialog.dismiss();
                            Toast.makeText(signin.this, "User not exist in database", Toast.LENGTH_SHORT).show();
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