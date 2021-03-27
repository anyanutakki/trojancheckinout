package cs310.trojancheckinout;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.content.Intent;

import com.google.firebase.firestore.FirebaseFirestore;

import cs310.trojancheckinout.models.User;
import androidx.appcompat.app.AppCompatActivity;

public class StudentSignUpActivity  extends AppCompatActivity {

    private EditText firstNameEdit;
    private EditText lastNameEdit;
    private EditText emailEdit;
    private EditText passwordEdit;
    private EditText studentIDEdit;
    private Button submitBtn;
    private Spinner majors;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String studentID;
    private String major;

    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_signup);

        firstNameEdit = findViewById(R.id.firstname_edit);
        lastNameEdit = findViewById(R.id.lastname_edit);
        emailEdit = findViewById(R.id.email_address_edit);
        passwordEdit = findViewById(R.id.password_edit);
        studentIDEdit = findViewById(R.id.student_id_edit);
        submitBtn = findViewById(R.id.submitButton);
        majors = findViewById(R.id.major_spinner);

        db = FirebaseFirestore.getInstance();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.majors, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        majors.setAdapter(adapter);

        firstNameEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                firstName = firstNameEdit.getText().toString();
                if(firstName.length() <= 0){
                    firstNameEdit.setError("Enter FirstName");
                }
                return true;
            }
        });

        lastNameEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                lastName = lastNameEdit.getText().toString();
                if(lastName.length() <= 0){
                    lastNameEdit.setError("Enter LastName");
                }
                return true;
            }
        });

        emailEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                email = emailEdit.getText().toString();
                if(email.length() <= 0){
                    emailEdit.setError("Enter Email Address");
                }
                if(!email.contains("@usc.edu")){
                    emailEdit.setError("Enter USC Email Address");
                }
                return true;
            }
        });

        passwordEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                password = passwordEdit.getText().toString();
                if(password.length() <= 0){
                    passwordEdit.setError("Enter Password");
                }
                return true;
            }
        });

        studentIDEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                studentID = studentIDEdit.getText().toString();
                if(studentID.length() <= 0){
                    studentIDEdit.setError("Enter StudentID");
                }
                return true;
            }
        });


        majors.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                major = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //majors.setError("Enter Major");
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firstName = firstNameEdit.getText().toString();
                lastName = lastNameEdit.getText().toString();
                email = emailEdit.getText().toString();
                password = passwordEdit.getText().toString();
                major = majors.getSelectedItem().toString();
                studentID = studentIDEdit.getText().toString();

                User user = new User(firstName, lastName, email, password, studentID, major);
                db.collection("users").document(email).set(user);


                Intent intent = new Intent(StudentSignUpActivity.this, MainActivity.class);
                startActivityForResult(intent, 0);
            }
        });

    }
}