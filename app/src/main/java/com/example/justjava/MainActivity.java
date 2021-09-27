package com.example.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {
    int numberOfCoffees = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
//    public int SetNoOfCoffee(int a){
//        EditText quantity=findViewById(R.id.quantity_text_view);
//        a=Integer.parseInt(quantity.getText().toString());
//        quantity.setText(a);
//        if(a<0||a>5){
//            Toast.makeText(MainActivity.this, "invalid input", Toast.LENGTH_SHORT).show();
//
//        }
//        return a;
//    }

    public void increment(View view) {
        if(numberOfCoffees<10){
        numberOfCoffees++;}
        if(numberOfCoffees>=10){
            Toast.makeText(MainActivity.this, "Max limit of coffee =10", Toast.LENGTH_SHORT).show();
        }
//        SetNoOfCoffee(numberOfCoffees);
        display(numberOfCoffees);
    }

    public void decrement(View view) {
        if(numberOfCoffees<=0){
            Toast.makeText(MainActivity.this, "oder cant be negative", Toast.LENGTH_SHORT).show();
        }
        if(numberOfCoffees>0){
        numberOfCoffees--;}
        display(numberOfCoffees);
    }

    public String SetName(){
        EditText Name= findViewById(R.id.Name);
        String n=Name.getText().toString();
        return n;
    }


    private void display(int number) {
        EditText quantityTextView = (EditText) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void submitOrder(View view) {
        display(numberOfCoffees);
        CheckBox box = findViewById(R.id.checkbox);
        CheckBox Choco = findViewById(R.id.Choco);
        boolean isChecked = box.isChecked();

        Log.v("MainActivity", "is checked " + isChecked);
//            displayPrice(calculatePrice(numberOfCoffees) );
        String priceMessage = "Total: ";
        createOrderSummary(SetName(), numberOfCoffees, calculatePrice(numberOfCoffees,box.isChecked(),Choco.isChecked()), box.isChecked(),Choco.isChecked());
//        displayMessage(priceMessage, calculatePrice(numberOfCoffees));
    }
    private int calculatePrice(int quantity, boolean a,boolean b) {
        int price = quantity * 5;
        if(a&b){price+=(3*quantity)+quantity;}

        else if (b) {
            price += (2*quantity)+quantity;
        }
        else if(a){
            price+=(1*quantity)+quantity;
        }
        else
            {price+=quantity;}
        return (price);
    }
    //        private void displayPrice(int number) {
//        TextView priceTextView = (TextView) findViewById(R.id.Price);
//        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
//    }
    private void createOrderSummary(String name, int quntity, int price, boolean box,boolean b) {

        String a = "name = "+name + "\nwipped Cream = "+box;
        a+="\nChocolate = "+b;
        a += "\nNo of coffee: " + quntity;
        a += "\nTotal:" + NumberFormat.getCurrencyInstance().format(price);

        TextView Summary = (TextView) findViewById(R.id.Summary);
        Summary.setText(a);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
//        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for \t"+name);
        intent.putExtra(Intent.EXTRA_TEXT,a);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
//        send(name,a);
    }

//    public void send(String name,String a){
//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
////        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
//        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for \t"+name);
//        intent.putExtra(Intent.EXTRA_TEXT,a);
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
//    }
}
/**
 * This method displays the given text on the screen.
 * //
 */
//    private void displayMessage(String message, int number) {
//        TextView priceTextView = (TextView) findViewById(R.id.Price);
//        priceTextView.setText(message + "\t" + NumberFormat.getCurrencyInstance().format(number) + "\n" + "Thank You!!");
//    }
