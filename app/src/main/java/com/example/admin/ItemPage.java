package com.example.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.RajAdapters.ItemAdapters;
import com.example.admin.RajModel.ItemoModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ItemPage extends AppCompatActivity {

    RecyclerView recview;
    ProgressBar progressBar33;
    TextView categorytopicname;
    ImageView back;

    ArrayList<ItemoModel> list;
    DatabaseReference reference;
    ItemAdapters adpt;
    Button insertredirectbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_page);


        recview=findViewById(R.id.recitem);
        categorytopicname=findViewById(R.id.toolbarname);
        back=findViewById(R.id.btn_backitem);
        progressBar33=findViewById(R.id.progressBar33);
        insertredirectbtn=findViewById(R.id.iteminsertbtn);

        String data=getIntent().getStringExtra("catname22");
        String userkey=getIntent().getStringExtra("userkey");


        categorytopicname.setText(data);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        list=new ArrayList<>();

        reference= FirebaseDatabase.getInstance().getReference();
        recview.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
//        recview.setNestedScrollingEnabled(false);
        adpt=new ItemAdapters(list,getApplicationContext());
        recview.setAdapter(adpt);


        Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();
        reference.child("Shopitemdata").child(userkey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists())
                {
                    list.clear();
                    for(DataSnapshot snapshot: dataSnapshot.getChildren())
                    {

                        ItemoModel data=snapshot.getValue(ItemoModel.class);
                        list.add(data);
                        progressBar33.setVisibility(View.GONE);
//                    list.clear();

                    }
//                adpt.notifyAll();
                }else
                {
                    progressBar33.setVisibility(View.GONE);
                    //IIIIIIIIIIIIIIIIIIIMAGE EMPTY WALA JUBDSADBI
                    Toast.makeText(ItemPage.this, "Producyt is empty", Toast.LENGTH_SHORT).show();
                }


                adpt.notifyDataSetChanged();

//                adpt=new Cmodel33333(list,);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }

        });

        insertredirectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),InsertItem2222.class);
                i.putExtra("categoryname",data);
                i.putExtra("categorukey",userkey);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

    }
}