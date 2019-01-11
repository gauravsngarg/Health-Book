package com.gauravsngarg.healthbook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.gauravsngarg.healthbook.R;
import com.gauravsngarg.healthbook.model.Patient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class PatientDetails extends AppCompatActivity {

    private static final String TAG = "TAG GAURAV";


    private TextView mAge;
    private TextView mSex;
    private TextView mName;
    private TextView mPhsID;
    private TextView mBloodGroup;
    private ImageButton mProfImg;
    private ImageButton mCoverImg;


    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DocumentReference ref;
    private FirebaseFirestore db;
    private DatabaseReference mDatabase;
    private String mUserId;
    private Patient mPatient;
    private ChildEventListener mChildEventListener;
    private DatabaseReference userdetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);

        mAge = (TextView) findViewById(R.id.tv_age_value);
        mSex = (TextView) findViewById(R.id.tv_sex_value);
        mName = (TextView) findViewById(R.id.tv_patient_name);
        mPhsID = (TextView) findViewById(R.id.tv_phs_id_value);
        mBloodGroup = (TextView) findViewById(R.id.tv_bg_value);
        mProfImg = (ImageButton) findViewById(R.id.imb_prof_img);
        mCoverImg = (ImageButton) findViewById(R.id.imb_cover_img);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();


        if (mDatabase == null) {
            Log.d(TAG, "database is null");
        }

        if (mFirebaseUser == null) {
            // Not logged in, launch the Log In activity
            loadLogInView();
        } else {
            //Retrieve Patient Details and Show in Activity
            mUserId = mFirebaseUser.getUid();
            //userdetails = mDatabase.child("users1");
            mPatient = new Patient(26, "O+","31-12-1991","Gaurav", "12345");
            Toast.makeText(PatientDetails.this, "Start 1", Toast.LENGTH_SHORT).show();

            ref = db.collection("users").document("username");
            ref.get().addOnCompleteListener((new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()){
                        DocumentSnapshot doc = task.getResult();
                        Toast.makeText(PatientDetails.this, "Start 2", Toast.LENGTH_SHORT).show();
                        if(doc.exists()){
                            Log.d(TAG, "DocumentSnapshot data: " + doc.getData());
                        } else {
                            Log.d(TAG, "No such document");
                        }
                    }
                }
            }));
           // ref.collection("userdetails");

            //mDatabase.child("user3").child(mUserId).setValue(mPatient);

          //  attachDatabaseReadListener();

        }
    }

    private void attachDatabaseReadListener() {
        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Patient patient = dataSnapshot.getValue(Patient.class);

                    Toast.makeText(PatientDetails.this, patient.getDob() + patient.getPatientName(), Toast.LENGTH_SHORT).show();
                    int age;
                    String bloodGroup;
                    //Medicine disease;
                    String dob;
                    String patientName;
                    String phsID;

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
            };

            userdetails.addChildEventListener(mChildEventListener);
        }
    }

    private void loadLogInView() {
        Intent intent = new Intent(this, LogInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
