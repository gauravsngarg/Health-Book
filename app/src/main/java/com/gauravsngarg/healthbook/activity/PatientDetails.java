package com.gauravsngarg.healthbook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.gauravsngarg.healthbook.R;
import com.gauravsngarg.healthbook.model.Patient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PatientDetails extends AppCompatActivity {

    private static final String TAG = "TAG";

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;
    private String mUserId;
    private Patient mPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mPatient = new Patient();

        if(mDatabase == null){
            Log.d(TAG, "database is null");
        }

        if (mFirebaseUser == null) {
            // Not logged in, launch the Log In activity
            loadLogInView();
        }else{
            //Retrieve Patient Details and Show in Activity
            mUserId = mFirebaseUser.getUid();


            final TextView textView = (TextView) findViewById(R.id.tv_patientDetails);

            // Use Firebase to populate the list.
            mDatabase.child("users").child(mUserId).child("items").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                    mPatient.setAge((Integer) dataSnapshot.child("age").getValue());
//                    mPatient.setBloodGroup((String) dataSnapshot.child("bloodgroup").getValue());
//                    mPatient.setDob((String) dataSnapshot.child("dob").getValue());
//                    mPatient.setPatientName((String) dataSnapshot.child("name").getValue());
//                    mPatient.setPhsID((String) dataSnapshot.child("phs_id").getValue());
                    /*List<Medi
                    cine> medicines = new ArrayList<>();
                    medicines.add(new Medicine(
                            (String) dataSnapshot.child("medicine").child("med_name").getValue(),
                                    (String) dataSnapshot.child("med_salt").getValue()));
                    mPatient.setMedicines(medicines);*/

                    Toast.makeText(PatientDetails.this, "age: " , Toast.LENGTH_SHORT).show();
//                    textView.setText(mPatient.getAge());
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
    }
    private void loadLogInView() {
        Intent intent = new Intent(this, LogInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
