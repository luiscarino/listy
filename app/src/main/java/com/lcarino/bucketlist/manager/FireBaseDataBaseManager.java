package com.lcarino.bucketlist.manager;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.lcarino.bucketlist.event.ResultEvent;
import com.lcarino.bucketlist.model.ListEntry;
import com.lcarino.bucketlist.ui.list.model.Entry;
import com.lcarino.bucketlist.ui.list.model.Inspiration;
import com.lcarino.bucketlist.model.List;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import javax.inject.Inject;

import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * A simple manager to handle transactions with Fire Base DB.
 * @author Luis Carino.
 */

public class FireBaseDataBaseManager  implements DataBaseManager {


    public static final String LIST_ENTRIES = "entries";
    public static final String INSPIRATIONS = "inspirations";
    public static final String LISTS = "lists";
    public static final String USERS = "users";

    private DatabaseReference databaseReference;
    private EventBus eventBus;

    @Inject
    public FireBaseDataBaseManager(EventBus eventBus, DatabaseReference databaseReference){
        this.eventBus = eventBus;
        this.databaseReference = databaseReference;
    }


    public void fetchInspirations() {
        databaseReference.child(INSPIRATIONS).addListenerForSingleValueEvent(new ValueEventListener() {
            ArrayList<Inspiration> inspirationList = new ArrayList<>();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren()) {
                    Inspiration inspiration = new Inspiration(child.getKey(),
                            child.child("title").getValue().toString(),
                            child.child("imageURL").getValue().toString());
                    inspirationList.add(inspiration);
                }

                postInspirationResult(new InspirationsResultEvent(inspirationList));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                postInspirationResult(new InspirationsResultEvent());
            }
        });
    }

    private void postInspirationResult(InspirationsResultEvent inspirationsResultEvent){
        eventBus.post(inspirationsResultEvent);
    }


    public void fetchList(String listId) {
        databaseReference.child(LISTS).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren()) {

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void addList(List list) {

    }

    @Override
    public void removeList(List list) {

    }

    @Override
    public RealmList<List> fetchLists() {
        return null;
    }





    public void fetchListEntries() {
        databaseReference.child(LIST_ENTRIES).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void insertEntry(Entry entry) {
        Task<Void> task = databaseReference.child(LIST_ENTRIES).push().setValue(entry);
        task.addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                eventBus.post(new AddResultEvent(task.isSuccessful()));
            }
        });
    }

    @Override
    public void add(ListEntry listEntry) {

    }

    @Override
    public void remove(ListEntry listEntry) {

    }

    @Override
    public void update(String id, String newValue) {

    }

    public static class InspirationsResultEvent  extends ResultEvent<ArrayList<Inspiration>>{

        public InspirationsResultEvent() {
            super();
        }

        public InspirationsResultEvent(ArrayList<Inspiration> payload) {
            super(payload);
        }
    }

    public static class AddResultEvent extends ResultEvent<Boolean> {
        public AddResultEvent(Boolean payload) {
            super(payload);
        }
    }

    @Override
    public RealmResults<ListEntry> getListEntries() {
        return null;
    }
}


//    /// CRUD Create Read Update Delete
//    private DatabaseReference databaseReference;
//
//@StringDef({LIST_ENTRIES, INSPIRATIONS, LISTS, USERS})
//public @interface URLPath{}
//    public static final String LIST_ENTRIES = "entries";
//    public static final String INSPIRATIONS = "inspirations";
//    public static final String LISTS = "lists";
//    public static final String USERS = "users";
//
//    @Inject
//    public FireBaseDataBaseManager(DatabaseReference databaseReference) {
//        this.databaseReference = databaseReference;
//    }
//
//
//    public void create(Inspiration item, final String path) {
//        databaseReference.child(path).push().setValue(item);
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // ListItemModel value = dataSnapshot.getValue(ListItemModel.class);
//                Log.d(TAG, dataSnapshot.getValue().toString());
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.e(TAG, databaseError.getMessage());
//            }
//        });
//    }
//
//    public void read(final String path) {
//        databaseReference.child(path).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                ArrayList<Inspiration> inspirations = new ArrayList<>();
//                for(DataSnapshot child : dataSnapshot.getChildren()) {
//                    Inspiration inspiration = new Inspiration(child.getKey(),
//                            child.child("title").getValue().toString(),
//                            child.child("imageURL").getValue().toString());
//                    inspirations.add(inspiration);
//                }
//
//                Log.d("","");
////                Log.d(getClass().getSimpleName(), inspirations.title);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }
//
//
//    public void update(String id, Object object) {
//
//    }
//
//
//    public void delete(String id) {
//
//    }