package pt.iul.iscte.daam.fitmeet.firebase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import pt.iul.iscte.daam.fitmeet.R;
import pt.iul.iscte.daam.fitmeet.data.FireBDatabase;
import pt.iul.iscte.daam.fitmeet.data.User;

/**
 * Created by jdandrade on 25/03/2017.
 */

public class FirebaseTestActivity extends AppCompatActivity {

  private Button button;
    private DatabaseReference mUserReference;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.firebase_test);

    // Set up the toolbar.
    ActionBar ab = getSupportActionBar();
    ab.setTitle("firebase test");
    ab.setDisplayHomeAsUpEnabled(true);
    ab.setDisplayShowHomeEnabled(true);

    setupViews();
    tabordenzTestingFirebase();

      mUserReference = new FireBDatabase("Users").getDatabase();

  }

  @Override
  public void onStart() {
    super.onStart();

    ValueEventListener userListener = new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
          //Test para a key 1 dos users
          User user = dataSnapshot.child("1").getValue(User.class);
          Toast.makeText(FirebaseTestActivity.this, user.getEmail() + " " + user.getName()+" ", Toast.LENGTH_SHORT).show();
         if (user != null) {
              System.out.println(user);
          }
      }
      @Override
      public void onCancelled(DatabaseError databaseError) {
        System.out.println("loadUser:onCancelled " + databaseError.toException());

        Toast.makeText(FirebaseTestActivity.this, "Failed to load user.",
                Toast.LENGTH_SHORT).show();
      }
    };
    mUserReference.addValueEventListener(userListener);
  }

  private void setupViews() {
    button = (Button) findViewById(R.id.firebase_button);
  }

  /**
   * Testa aqui neste metodo Tabordenz.
   */
  private void tabordenzTestingFirebase() {
    button.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference("message");
        FireBDatabase db = new FireBDatabase("Users");
        //db.getDatabase().setValue("Hello, World!");
        db.writeNewUser(1, "Bruno", "boss@b.pt","url");
        db.writeNewUser(2, "Bruno1", "boss@b.pt1","url1");
        //db.getDatabase().setValue("Hello world!");
        //db.getDatabase().push()

        Toast.makeText(FirebaseTestActivity.this, "BOOP", Toast.LENGTH_SHORT).show();
      }
    });
  }

  @Override public boolean onSupportNavigateUp() {
    onBackPressed();
    return true;
  }
}
