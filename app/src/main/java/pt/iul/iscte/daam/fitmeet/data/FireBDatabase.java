package pt.iul.iscte.daam.fitmeet.data;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bruno on 18/03/2017.
 */

public class FireBDatabase {

    private DatabaseReference database;

    public FireBDatabase(String ref){
        com.google.firebase.database.FirebaseDatabase databaseFirebase = com.google.firebase.database.FirebaseDatabase.getInstance();
        database = databaseFirebase.getReference(ref);
    }

    public void writeNewUser(long id, String name, String email, String avatarUrl) {
        User newUser = new User(id, name, email, avatarUrl);
        database.child(Long.toString(id)).setValue(newUser);
    }

    public void newEvent(long id, String description, String title, Date date, String location, int numberOfLikes, String graphicURL, Enum<Difficulty> difficulty, User owner) {
        Event newEvent = new Event(id, description, title,date,location, numberOfLikes ,graphicURL, difficulty, owner);
        database.child(Long.toString(id)).setValue(newEvent);
    }


    public DatabaseReference getDatabase() { return database;}

}
