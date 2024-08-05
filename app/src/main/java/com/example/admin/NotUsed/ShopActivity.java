package com.example.admin.NotUsed;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
/*import androidx.appcompat.widget.AbsActionBarView;*/

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.admin.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class ShopActivity extends AppCompatActivity {

    ImageView shop_back;
    EditText edt_PName,edt_P_Price,edt_PDescription;
    Button btn_AddProduct,btn_ShowProduct;
    FirebaseDatabase database;
    FirebaseStorage storage;
    ImageButton btn_ImageUrl;
    Uri u;
    ProgressBar progressBar;
    ActivityResultLauncher launcher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        shop_back = findViewById(R.id.shop_back);

        btn_ImageUrl = findViewById(R.id.img_choose_btn_shop);
        edt_PName = findViewById(R.id.edt_PName);
        edt_P_Price = findViewById(R.id.edt_P_Price);
        edt_PDescription = findViewById(R.id.edt_PDescription);

        btn_AddProduct = findViewById(R.id.btn_AddProduct);
        btn_ShowProduct = findViewById(R.id.btn_ShowProduct);
        progressBar = findViewById(R.id.P_bar_shop);


        launcher = registerForActivityResult(new ActivityResultContracts.GetContent()
                ,new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri uri) {
                        btn_ImageUrl.setImageURI(uri);
//                        progressBar.setVisibility(View.VISIBLE);
                        u=uri;

                        



                    }
                });

        btn_ImageUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher.launch("image/*");
//                btn_ImageUrl.setImageURI(u);
            }

        });

        shop_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btn_AddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertProduct();
            }

        });
        btn_ShowProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),ProductsActivity.class));
            }
        });
    }



    private void insertProduct() {

        database=FirebaseDatabase.getInstance();
        storage=FirebaseStorage.getInstance();
            // Ensure Firebase database instance is initialized
            if (database == null) {
                // Handle Firebase initialization error
                Toast.makeText(getApplicationContext(), "Database not initialized", Toast.LENGTH_SHORT).show();
                return;
            }

            // Ensure Firebase storage instance is initialized
            if (storage == null) {
                // Handle Firebase initialization error
                Toast.makeText(getApplicationContext(), "Storage not initialized", Toast.LENGTH_SHORT).show();
                return;
            }

            // Ensure an image URI is selected
            if (u == null) {
                // Handle file selection error
                Toast.makeText(getApplicationContext(), "Please select an image", Toast.LENGTH_SHORT).show();
                return;
            }


            // Reference to the Firebase Storage where the image will be uploaded
//        StorageReference reference = storage.getReference().child("Community_Image");


        StorageReference reference = storage.getReference().child("Shop_Image");

            // Upload the image to Firebase Storage
            reference.putFile(u).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressBar.setVisibility(View.GONE);
                            // Once the image is uploaded successfully, get its download URL
                            reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    // Get product details from EditText fields
                                    String productName = edt_PName.getText().toString();
                                    String productPrice = edt_P_Price.getText().toString();
                                    String productDescription = edt_PDescription.getText().toString();

                                    // Create a map to store product details
                                    Map<String, Object> map = new HashMap<>();
                                    map.put("ItemImage", uri.toString());
                                    map.put("P_name", productName);
                                    map.put("P_price", productPrice);
                                    map.put("P_desc", productDescription);

                                    // Push the product details to the Firebase Realtime Database
                                    FirebaseDatabase.getInstance().getReference().child("Shop").push().setValue(map)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    // Hide progress bar when operation is successful
                                                    progressBar.setVisibility(View.GONE);
                                                    // Show success message
                                                    Toast.makeText(getApplicationContext(), "Product added successfully", Toast.LENGTH_SHORT).show();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    // Hide progress bar when operation fails
                                                    progressBar.setVisibility(View.GONE);
                                                    // Show error message
                                                    Toast.makeText(getApplicationContext(), "Failed to add product: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Hide progress bar when image upload fails
                            progressBar.setVisibility(View.GONE);
                            // Show error message
                            Toast.makeText(getApplicationContext(), "Failed to upload image: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }

}

