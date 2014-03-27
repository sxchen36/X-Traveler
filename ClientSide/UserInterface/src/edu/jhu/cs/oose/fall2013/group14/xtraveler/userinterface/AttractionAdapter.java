package edu.jhu.cs.oose.fall2013.group14.xtraveler.userinterface;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.userinterface.R;
/**
 * Adapter used to define the content of the listview
 *
 */
public class AttractionAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final String[] attraction_names;
	private ArrayList<Bitmap> attraction_images;

	 private String[] attraction_ratings;

	 /**
	  * Constructor
	  * @param context The current context.
	  * @param values The objects to represent in the ListView
	  */
	public AttractionAdapter(Context context, String[] values) {
		super(context, R.layout.one_attraction, values);
		attraction_images =new ArrayList<Bitmap>();
		this.context = context;
		this.attraction_names = values;

	}

	/**
	 * Initialize attraction images
	 * @param images image list of attractions
	 */
	public void initiatemap(ArrayList<Bitmap> images) {
		attraction_images = images;
	}
	/**
	 * Initialize attraction ratings
	 * @param rat attraction rate list
	 */
	public void initiaterating(String[] rat) {
		attraction_ratings = rat;
		}


	/**
	 * Get row views for the list view in City.java
	 * @param position image position in attraction image list, automatically generated
	 * @param convertView View need to be converted
	 * @param parent parent viewgroup of current view
	 * @return View Row view
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowView = inflater.inflate(R.layout.one_attraction, parent, false);
//		rowView.setClickable(true);
//	    rowView.setFocusable(true);
		TextView textView = (TextView) rowView
				.findViewById(R.id.attractionname);
		textView.setText("12a1");
		ImageView imageView = (ImageView) rowView
				.findViewById(R.id.attractionima);
		Bitmap b = attraction_images.get(position);
		imageView.setImageBitmap(b);
		/* Set rating bar*/
		RatingBar ratingbar = (RatingBar) rowView
				.findViewById(R.id.attractionrate);
		ratingbar.setRating(Float.valueOf(attraction_ratings[position]));
		ratingbar.setFocusable(false);
		ratingbar.setClickable(false);
		textView.setText(attraction_names[position]);

		return rowView;
	}

}
