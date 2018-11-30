package com.bitleet.saraf.ghurao;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private EditText emailET,passET;
    private Button loginBtn, regBtn;
    private TextView statusTV;
    private Context context;
    private AuthenticationCompleteListener listener;
    private FirebaseAuth auth;
    private FirebaseUser user;


    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        listener = (AuthenticationCompleteListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        emailET = view.findViewById(R.id.emailInput);
        passET = view.findViewById(R.id.passInput);
        loginBtn = view.findViewById(R.id.loginBtn);
        regBtn = view.findViewById(R.id.regBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailET.getText().toString();
                String pass = passET.getText().toString();
                if (email.isEmpty() || pass.isEmpty()){
                    emailET.setError("Valid Email Required");
                    emailET.requestFocus();
                    passET.setError("Strong Password Required");
                    passET.requestFocus();
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailET.setError("Please enter a valid email");
                    emailET.requestFocus();
                    return;
                }
                if (pass.length() < 6) {
                    passET.setError("Minimum length of password should be 6.");
                    passET.requestFocus();
                }else {
                    auth.signInWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        listener.onAuthComplete();
                                    /*user = auth.getCurrentUser();
                                    statusTV.setText("Logged in by: " + user.getEmail());*/
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            statusTV.setText(e.getMessage());
                        }
                    });
                }
            }
        });
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailET.getText().toString();
                String pass = passET.getText().toString();
                if (email.isEmpty() || pass.isEmpty()){
                    emailET.setError("Please enter a valid Email address");
                    emailET.requestFocus();
                    passET.setError("Please enter a strong Password");
                    passET.requestFocus();
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailET.setError("Please enter a valid email");
                    emailET.requestFocus();
                    return;
                }
                if (pass.length() < 6) {
                    passET.setError("Minimum length of password should be 6.");
                    passET.requestFocus();
                }else {
                    auth.createUserWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        listener.onAuthComplete();
                                    /*user = auth.getCurrentUser();
                                    statusTV.setText("Logged in by: " + user.getEmail());*/
                                    }
//                                else {
//                                    Toast.makeText(context, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                                }
                                }
                            })

                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    statusTV.setText(e.getMessage());
                                }
                            });
                }
            }
        });
        statusTV = view.findViewById(R.id.statusTV);
        return view;
    }

    interface AuthenticationCompleteListener{
        void onAuthComplete();
    }

}
