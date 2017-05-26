package pt.iul.iscte.daam.fitmeet.data;

import com.google.firebase.database.DatabaseReference;

import java.util.Date;

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

    public void newEvent(long id, String description, String title, Date date, String location, int numberOfLikes, String graphicURL, String difficulty, User owner) {
        Event newEvent = new Event(numberOfLikes, description, title, date,location, numberOfLikes ,graphicURL, difficulty, owner,
            19, 10, "public");
        database.child(Long.toString(id)).setValue(newEvent);
    }

    public void newMatchmakingRequest(long duracaoMax, long duracaoMin, long horaMax, long horaMin, long id, String local, long raio, long tipo, String user, boolean sanitized) {
        MatchmakingRequest newMatchmakingRequest = new MatchmakingRequest(duracaoMax, duracaoMin, horaMax, horaMin, id, local, raio, tipo,user, sanitized );
        database.child(Long.toString(id)).setValue(newMatchmakingRequest);
    }




    public DatabaseReference getDatabase() { return database;}

}
