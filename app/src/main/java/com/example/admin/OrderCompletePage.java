package com.example.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.admin.RajAdapters.OrderCompleteCancelAdapters;
import com.example.admin.RajAdapters.OrderStatusAdapters;
import com.example.admin.RajModel.OrderStatusModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OrderCompletePage extends AppCompatActivity {

    RecyclerView recview;
    Button ordercompletebtn;

    OrderCompleteCancelAdapters adpt;
    ArrayList<OrderStatusModel> list;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_complete_page);

//        ordercompletebtn=findViewById(R.id.ordercompletebtn);
        recview=findViewById(R.id.completerecview);



        list=new ArrayList<>();
        reference= FirebaseDatabase.getInstance().getReference();
        recview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adpt=new OrderCompleteCancelAdapters(list,getApplicationContext());
        recview.setAdapter(adpt);
        reference.child("OrderCompleteCancel").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
//                    emptycartimg.setVisibility(View.GONE);
                    list.clear();
                    for(DataSnapshot snapshot: dataSnapshot.getChildren())
                    {
                        OrderStatusModel data=snapshot.getValue(OrderStatusModel.class);
                        list.add(data);
                    }
                }
                else
                {
                    list.clear();
                    Toast.makeText(getApplicationContext(), "Cart's Empty", Toast.LENGTH_SHORT).show();
                }
                adpt.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
        adpt.notifyDataSetChanged();
    }
}