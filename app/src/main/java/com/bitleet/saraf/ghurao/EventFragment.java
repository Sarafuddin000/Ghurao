package com.bitleet.saraf.ghurao;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.bitleet.saraf.ghurao.pojo.Event;
import com.google.firebase.auth.FirebaseAuth;



public class EventFragment extends Fragment {

    private FragmentManager manager;
    private ListView eventList;
    private FloatingActionButton addBtn;
    private Button logoutBtn;
    private FirebaseAuth auth;


    public EventFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        auth = FirebaseAuth.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        eventList  = view.findViewById(R.id.eventList);
        addBtn  = view.findViewById(R.id.addBtn);

        final Fragment fragment = null;



        logoutBtn  = view.findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(auth!=null){
                    auth.signOut();
                    logout();
                }
            }
        });


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Event event = new Event("1","23/11/2018","test",15000);

            }
        });
        return view;
    }

    private void logout() {
        FragmentTransaction fr = getFragmentManager().beginTransaction();
        fr.replace(R.id.fragmentContainer, new LoginFragment());
        fr.commit();
    }

}