package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

//extended adapter is created accepting object type EarthQuake
public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    // constructor is set for Adapter
    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> objects) {
        super(context, 0, objects);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            //new View is created which recycles old view
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.mag_cty_date, parent, false);
        }

        //new Earthquake object is created to set items id
        Earthquake currentCity = (Earthquake) getItem(position);
        Date dateObject = new Date(String.valueOf(currentCity.getDate()));

        //layout is set for items
        TextView magnitude = listItemView.findViewById(R.id.magnitude);
        TextView city = listItemView.findViewById(R.id.city);
        TextView date = listItemView.findViewById(R.id.date);
        String formattedDate = formatDate(dateObject);
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();

        date.setText(formattedDate);

        //feed the View with get methods
        magnitude.setText(currentCity.getMagnitude() + "");
        city.setText(currentCity.getCity());
        date.setText(currentCity.getDate() + "");
        String formattedTime = formatDate(dateObject);
        // Display the time of the current earthquake in that TextView
        date.setText(formattedTime);


        int magnitudeColor = getMagnitudeColor(currentCity.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


//        return super.getView(position, convertView, parent);
        return listItemView;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);// gets approx from float n sets color accordingly
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy"+"\n"+"h:mm a");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
//    private String formatTime(Date dateObject) {
//        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
//        return timeFormat.format(dateObject);
//    }

}
