package pt.iul.iscte.daam.fitmeet.data;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bruno on 18/03/2017.
 */

public class Database {

    private DatabaseReference database;

    public Database(String ref){
        FirebaseDatabase databaseFirebase = FirebaseDatabase.getInstance();
        database = databaseFirebase.getReference(ref);
    }

    public void writeNewUser(long id, String name, String email, String avatarUrl) {
        User newUser = new User(id, name, email, avatarUrl);
        database.child(Long.toString(id)).setValue(newUser);

    }

    public DatabaseReference getDatabase() { return database;}

}
