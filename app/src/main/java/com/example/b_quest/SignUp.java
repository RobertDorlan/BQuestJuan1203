package com.example.b_quest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    private AutoCompleteTextView mFirstName;
    private AutoCompleteTextView mLastName;
    private AutoCompleteTextView mUsername;
    private AutoCompleteTextView mEmail;
    private EditText mPhoneNumber;
    private EditText mPassword;
    private EditText mConfirmPassword;
    //public static final String TAG = "BQuest";

    //FirebaseAut variable
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    //Firestore variable
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        mFirstName = findViewById(R.id.name);
        mLastName = findViewById(R.id.lastName);
        mUsername = findViewById(R.id.userName);
        mEmail = findViewById(R.id.email);
        mPhoneNumber = findViewById(R.id.phone);
        mPassword = findViewById(R.id.password);
        mConfirmPassword = findViewById(R.id.confirmPassword);

        //executed when the cancel button is pressed
        Button cancelSignUp = findViewById(R.id.cancel_sign_up_button);
        cancelSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });

        // Executed when Sign Up button is pressed.
        Button signUp = findViewById(R.id.submitButton);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptRegistration();
            }
        });
    }

    private void attemptRegistration() {

        // Reset errors displayed in the form.
        mEmail.setError(null);
        mPassword.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password) || !isPasswordValid(password)) {
            mPassword.setError(getString(R.string.error_invalid_password));
            focusView = mPassword;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmail.setError(getString(R.string.error_field_required));
            focusView = mEmail;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmail.setError(getString(R.string.error_invalid_email));
            focusView = mEmail;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        } else {
            createFirebaseUser();
            Intent intent = new Intent(SignUp.this, MainActivity.class);
            startActivity(intent);
        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {

        String confirmPassword = mConfirmPassword.getText().toString();

        if (!confirmPassword.equals(password)) {
            Toast.makeText(getApplicationContext(), "Password does not match the confirmation", Toast.LENGTH_SHORT).show();
        } else if (password.length() < 8) {
            Toast.makeText(getApplicationContext(), "Password must be at least 8 character long", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    //creating new firebaseAuth credentials and user
    private void createFirebaseUser() {
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();

         mAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
           @Override
           public void onSuccess(AuthResult authResult) {
               Toast.makeText(getApplicationContext(), "Auth created", Toast.LENGTH_SHORT).show();

                String userAuthId = FirebaseAuth.getInstance().getCurrentUser().getUid();

                DocumentReference ref = db.collection("users").document();

                String firstName = mFirstName.getText().toString();
                String lastName = mLastName.getText().toString();
                String username = mUsername.getText().toString();
                String email = mEmail.getText().toString();
                String phoneNumber = mPhoneNumber.getText().toString();

                User user = new User();

                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setUsername(username);
                user.setEmail(email);
                user.setPhoneNumber(phoneNumber);
                user.setUserID(ref.getId());
                user.setUser_auth_id(userAuthId);

                ref.set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "New user created", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
           }
       });
    }
}
