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
import java.util.Date;

import pt.iul.iscte.daam.fitmeet.R;
import pt.iul.iscte.daam.fitmeet.data.Difficulty;
import pt.iul.iscte.daam.fitmeet.data.FireBDatabase;
import pt.iul.iscte.daam.fitmeet.data.User;

/**
 * Created by jdandrade on 25/03/2017.
 */

public class FirebaseTestActivity extends AppCompatActivity {

  private Button button;
  private Button buttonCipras;
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
    ciprasTest();

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
          if (user != null) {
            Toast.makeText(FirebaseTestActivity.this, user.getEmail() + " " + user.getName()+" ", Toast.LENGTH_SHORT).show();

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
    buttonCipras = (Button) findViewById(R.id.firebase_cipras);
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
        db.writeNewUser(1, "patoide", "patoadsdaside@b.pt","url");
        db.writeNewUser(2, "Bsaddasdsadsaruno1", "bosasdaasddassddass@b.pt1","url1");
        //db.getDatabase().setValue("Hello world!");
        //db.getDatabase().push()
          db = new FireBDatabase("Events");
          db.newEvent(1, "Correr com os patos faz bem.", "Corrida dos patos", new Date(), "Mafra", 2, "none", Difficulty.ADVANCED.toString(), new User(1, "Bruno", "boss@b.pt","url"));

        Toast.makeText(FirebaseTestActivity.this, "BOOP", Toast.LENGTH_SHORT).show();
      }
    });
  }

  private void ciprasTest() {
    buttonCipras.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference("message");
        FireBDatabase db = new FireBDatabase("MatchmakingRequest");


          db.newMatchmakingRequest(30,60,20,21,1,"Cascais", 15, "Running","Bas Dost", false);
          db.newMatchmakingRequest(30,60,20,21,2,"Estoril", 15, "Trail","Gui", false);
          db.newMatchmakingRequest(30,60,20,21,3,"Estoril", 15, "Trail","Tomas", false);
          db.newMatchmakingRequest(30,60,20,21,4,"Cascais", 15, "Running","Ruiz", false);
          db.newMatchmakingRequest(30,60,19,21,5,"Estoril", 15, "Running", "Ze", false);

//        db.newMatchmakingRequest(45,69,19,22,3,"Cascais", 20, 1,"Castanhos", false);
        //db.newMatchmakingRequest(1,"test",1,"Bas Dost", false);
//        db.newMatchmakingRequest(2,"Cascais",1,"Zw", false);
//        db.newMatchmakingRequest(3,"Estoril",2,"zas", false);
//        db.newMatchmakingRequest(4,"Cascais",3,"Hugens Dost", false);
//        db.newMatchmakingRequest(5,"Lisboa",2,"Gaston", false);
//        db.newMatchmakingRequest(6,"Estoril",1,"Ginaasdi", false);

        //db.getDatabase().setValue("Hello, World!");
        //db.writeNewUser(1, "Bruno", "boss@b.pt","url");
        //db.writeNewUser(2, "Bruno1", "boss@b.pt1","url1");
        //db.getDatabase().setValue("Hello world!");
        //db.getDatabase().push()
        //db = new FireBDatabase("Events");
        //db.newEvent(1, "Correr com os patos faz bem.", "Corrida dos patos", new Date(), "Mafra", 2, "none", Difficulty.ADVANCED, new User(1, "Bruno", "boss@b.pt","url"));

        Toast.makeText(FirebaseTestActivity.this, "CIPRAS", Toast.LENGTH_SHORT).show();
      }
    });
  }

  @Override public boolean onSupportNavigateUp() {
    onBackPressed();
    return true;
  }
}
