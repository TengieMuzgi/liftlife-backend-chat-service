package com.liftlife.liftlifechatservice.message;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Sinks;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Repository
public class MessageRepository {
    private final DatabaseReference ref;

    //TODO: buffering sink because it only sends data one time
    private final Sinks.Many<Message> sink;


    public MessageRepository() throws IOException {
        this.sink = Sinks.many().multicast().onBackpressureBuffer();

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.getApplicationDefault())
                .setDatabaseUrl("https://liftlife-b89d1-default-rtdb.europe-west1.firebasedatabase.app")
                .build();

        FirebaseApp.initializeApp(options);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        ref = database.getReference("messages");
    }

    @PostConstruct
    private void init(){
        ChildEventListener childEventListener = ref.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                sink.tryEmitNext(dataSnapshot.getValue(Message.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    public Sinks.Many<Message> getSink() {
        return sink;
    }

    //TODO: try to make CRUD methods return something according to standards
    /*TODO: we will need mechanism to differentiate create and update methods
       or prevent overwriting existing nodes (might come out in research)*/
    public void insert(String id, Message messagesToSave) {
        ref.child(id).setValueAsync(messagesToSave);
    }
}
