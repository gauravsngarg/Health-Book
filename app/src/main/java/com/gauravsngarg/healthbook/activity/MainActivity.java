package com.gauravsngarg.healthbook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.gauravsngarg.healthbook.R;
import com.gauravsngarg.healthbook.model.Item;
import com.gauravsngarg.healthbook.model.Medicine;
import com.gauravsngarg.healthbook.model.Patient;
import com.gauravsngarg.healthbook.model.Prescription;
import com.gauravsngarg.healthbook.model.Vaccine;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "GAURAV TAG" ;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;
    private String mUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize Firebase Auth and Database Reference
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        PrepareDB();

        if(mDatabase == null){
            Log.d(TAG, "database is null");
        }

        if (mFirebaseUser == null) {
            // Not logged in, launch the Log In activity
            loadLogInView();
        } else {
            mUserId = mFirebaseUser.getUid();

            // Set up ListView
            final ListView listView = (ListView) findViewById(R.id.listView);
            final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
            listView.setAdapter(adapter);

            // Add items via the Button and EditText at the bottom of the view.
            final EditText text = (EditText) findViewById(R.id.todoText);
            final Button button = (Button) findViewById(R.id.addButton);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Item item = new Item(text.getText().toString());
                    String id = mDatabase.push().getKey();
                    Log.d(TAG, " database start");
//                  mDatabase.child("users").child(mUserId).child("items").setValue(item);
                    //       mDatabase.child("users").child(mUserId).child("items").push().setValue(PrepareDB());
                    //**********************************
                    //mDatabase.child("users").child(mUserId).child("items").push();
                    //DatabaseReference ref = mDatabase.child("users").child(mUserId).child("items").getRef();


                    List<Medicine> medicines = new ArrayList<>();
                    medicines.add(new Medicine("Thyronorm", "Thyro","00", "00"));
                    medicines.add(new Medicine("Crocin", "Paracetamol","00", "00"));

                    mDatabase.child("users").child(mUserId).child("items").push().setValue(medicines);

                    String id1 = mDatabase.child("users").child(mUserId).child("items").push().getKey();

                    DatabaseReference ref = mDatabase.child("users").child(mUserId).child("items").child(id1).getRef();

                    List<String> medicineId = new ArrayList<>();
                    medicineId.add("0");
                    medicineId.add("1");

                    List<Prescription> prescriptions = new ArrayList<>();
                    prescriptions.add(new Prescription("http://photo URL", "123", "31-12-1991",medicineId));
                    prescriptions.add(new Prescription("http://photo URL", "123", "31-12-1991",medicineId));

                    ref.setValue(prescriptions);

                    /*mDatabase.child("users").child(mUserId).child("items").push().setValue(medicines);

                    List<Vaccine> vaccines = new ArrayList<>();
                    vaccines.add(new Vaccine("HPT"));

                    Patient patient = new Patient(26, "O+", "31-12-1991","gaurav","12345");
*/


                    text.setText("");

                    Intent intent = new Intent(MainActivity.this, PatientDetails.class);
                    startActivity(intent);


                }
            });

            // Use Firebase to populate the list.

            Log.d(TAG, " database start");
            mDatabase.child("users").child(mUserId).child("items").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    adapter.add((String) dataSnapshot.child("title").getValue());
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    adapter.remove((String) dataSnapshot.child("title").getValue());
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            // Delete items when clicked
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    mDatabase.child("users").child(mUserId).child("items")
                            .orderByChild("title")
                            .equalTo((String) listView.getItemAtPosition(position))
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.hasChildren()) {
                                        DataSnapshot firstChild = dataSnapshot.getChildren().iterator().next();
                                        firstChild.getRef().removeValue();
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                }
            });
        }
    }

    public Patient PrepareDB() {
        List<Medicine> medicines = new ArrayList<>();
        medicines.add(new Medicine("Thyronorm", "Thyro","00", "00"));
        medicines.add(new Medicine("Crocin", "Paracetamol","00", "00"));

        List<String> medicineId = new ArrayList<>();
        medicineId.add("0");
        medicineId.add("1");

        List<Prescription> prescriptions = new ArrayList<>();
        prescriptions.add(new Prescription("http://photo URL", "123", "31-12-1991",medicineId));

        List<Vaccine> vaccines = new ArrayList<>();
        vaccines.add(new Vaccine("HPT"));

        Patient patient = new Patient(26, "O+", "31-12-1991","gaurav","12345");

        return patient;
    }

    private void loadLogInView() {
        Intent intent = new Intent(this, LogInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            mFirebaseAuth.signOut();
            loadLogInView();
        }

        return super.onOptionsItemSelected(item);
    }
}