package com.example.visitor;

import android.content.Intent;
import android.os.Bundle;
import android.view.PixelCopy;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class AddVisitor extends AppCompatActivity {
    EditText et1,et2,et3,et4;
    Button btn1,btn2;
    String getFname,getLname,getMeet,getPurpose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_visitor);

        et1=(EditText) findViewById(R.id.fname);
        et2=(EditText) findViewById(R.id.lname);
        et3=(EditText) findViewById(R.id.purpose);
        et4=(EditText) findViewById(R.id.meet);
        btn1=(Button) findViewById(R.id.addd);
        btn2=(Button) findViewById(R.id.back);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFname=et1.getText().toString();
                getLname=et2.getText().toString();
                getMeet=et3.getText().toString();
                getPurpose=et4.getText().toString();

               if(getFname.isEmpty() || getLname.isEmpty() || getPurpose.isEmpty() || getMeet.isEmpty())
               {
                   Toast.makeText(getApplicationContext(),"all the field are mandatory",Toast.LENGTH_LONG).show();
               }
               else 
               {
                   callApi();
               }


            }

            private void callApi() {
                String apiUrl="https://log-app-demo-api.onrender.com/addvisitor";
                JSONObject data=new JSONObject();
                try {
                    data.put("firstname",getFname);
                    data.put("lastname",getLname);
                    data.put("purpose",getPurpose);
                    data.put("whomToMeet",getMeet);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                JsonObjectRequest request=new JsonObjectRequest( Request.Method.POST,
                        apiUrl,
                        data,
                        response -> Toast.makeText(getApplicationContext(),"Successfully Added",Toast.LENGTH_LONG).show(),
                        error -> Toast.makeText(getApplicationContext(),"Something Went Wrong",Toast.LENGTH_LONG).show()
                         );
                RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
                queue.add(request);
            }
        });
        
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent obj=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(obj);
            }
        });


    }
}