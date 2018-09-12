package com.example.android.miwok;
//@param ننشا هذا من شان نخلي ال adapter يعرض تنتين تنتين

import android.app.Activity;

import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;


public class WordAdapter extends ArrayAdapter<Word> {
    private int mColor;


    public WordAdapter(Activity context, ArrayList<Word> androidFlavors, int colors) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, androidFlavors);

        mColor = colors;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.single_layout, parent, false);

        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Word currentAndroidFlavor = getItem(position);


        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.miowk_word);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        nameTextView.setText(currentAndroidFlavor.getMiowkTranslation());


        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView numberTextView = (TextView) listItemView.findViewById(R.id.english_word);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        numberTextView.setText(currentAndroidFlavor.getdefultTranslation());

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.small_image);
        // Get the image resource ID from the current AndroidFlavor object and
        // set the image to iconView
        if (currentAndroidFlavor.hasImage()) {
            iconView.setImageResource(currentAndroidFlavor.getImageResourseId());
            iconView.setVisibility(View.VISIBLE);
        } else {
            iconView.setVisibility(View.GONE);
        }
        //set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);
        //Find color that resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColor);
        //Set background color of text container Viewvscbfrhfnbkgh bbv
        textContainer.setBackgroundColor(color);

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;

    }
}

