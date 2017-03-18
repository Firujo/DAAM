package pt.iul.iscte.daam.fitmeet.data;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Bruno on 18/03/2017.
 */

public class Database {

    FirebaseDatabase database;

    public void Database(){
        database = FirebaseDatabase.getInstance();
    }

    public void write(Object obj){
        DatabaseReference myRef = database.getReference();
        myRef.setValue(obj);
    }


}
