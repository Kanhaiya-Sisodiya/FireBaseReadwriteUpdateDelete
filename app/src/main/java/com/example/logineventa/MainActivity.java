package com.example.logineventa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText edRollno, edPassword;
    Button btnSignin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edPassword=findViewById(R.id.edPassword);
        edRollno=findViewById(R.id.edRollNo);
        btnSignin=findViewById(R.id.btnSignIn);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Users");

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String rollno = edRollno.getText().toString();
                String password = edPassword.getText().toString();

                if(rollno.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter Roll number", Toast.LENGTH_SHORT).show();
                }
                else if(password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
                }

//                else {
//                    User user= new User(Integer.parseInt(rollno), password);
//                    myRef.child(rollno).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            Toast.makeText(MainActivity.this, "Added", Toast.LENGTH_SHORT).show();
//                            edPassword.setText("");
//                            edRollno.setText("");
//                        }
//                    });
//                }

                else {

                    myRef.child(rollno).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if(task.isSuccessful()){
                                if(task.getResult().exists()) {
                                    DataSnapshot dataSnapshot = task.getResult();
                                    Toast.makeText(MainActivity.this, dataSnapshot.child("password").getValue().toString(), Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(MainActivity.this, "Data does not exist", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(MainActivity.this, "Fail to read data", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

//                    myRef.addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//                            // This method is called once with the initial value and again
//                            // whenever data at this location is updated.
//
//                            // this method is call to get the realtime
//                            // updates in the data.
//                            // this method is called when the data is
//                            // changed in our Firebase console.
//                            // below line is for getting the data from
//                            // snapshot of our database.
//                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
//                                User user = dataSnapshot1.getValue(User.class);
//                                Toast.makeText(MainActivity.this, String.valueOf(user.getRollNumber()), Toast.LENGTH_SHORT).show();
//
////                                if(rollno.equalsIgnoreCase(String.valueOf(user.getRollNumber()))&&password.equalsIgnoreCase(user.getPassword())){
////                                    Toast.makeText(MainActivity.this, "sign in", Toast.LENGTH_SHORT).show();
////                                }
////                                else {
////                                    Toast.makeText(MainActivity.this, "Wrong credentials", Toast.LENGTH_SHORT).show();
////                                }
//                            }
//
//                        }
//
//                        @Override
//                        public void onCancelled(DatabaseError error) {
//                            // Failed to read value
//                            // calling on cancelled method when we receive
//                            // any error or we are not able to get the data.
//                            Toast.makeText(MainActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
//                        }
//                    });
                }

            }
        });
    }
}