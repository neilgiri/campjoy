package org.campjoy.identitree.starter.fragments;

import java.io.IOException;
import java.io.InputStream;

import org.campjoy.identitree.starter.FragmentActivityBase;
import org.campjoy.identitree.starter.FragmentBase;
import org.campjoy.identitree.starter.R;
import org.campjoy.identitree.starter.model.QuestionModel;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class QuestionFragment extends FragmentBase {

	private TextView firstTextView;
	private TextView secondTextView;
	
	private ImageView firstImageView;
	private ImageView secondImageView;
	
	private String firstText = "";
	private String secondText = "";
	
	private String firstImagePath = "";
	private String secondImagePath = "";
	
	private int id;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_question, container, false);
		
		firstTextView = (TextView) view.findViewById(R.id.text_first_question);
		secondTextView = (TextView) view.findViewById(R.id.text_second_question);

		firstImageView = (ImageView) view.findViewById(R.id.image_first_question);
		secondImageView = (ImageView) view.findViewById(R.id.image_second_question);
		
		RelativeLayout firstLayout = (RelativeLayout) view.findViewById(R.id.relativeLayout1);
		RelativeLayout secondLayout = (RelativeLayout) view.findViewById(R.id.relativeLayout2);
		
		Bundle bundle = this.getArguments(); 
		id = bundle.getInt("ID");
		
		final QuestionModel model = new QuestionModel(getActivity().getApplicationContext());
		
		firstText = model.getQuestions().get(id).getChoice1().getText();
		secondText = model.getQuestions().get(id).getChoice2().getText();
		
		firstTextView.setText(firstText);
		secondTextView.setText(secondText);
		
		firstImagePath = String.valueOf((id + 1)) + "a.png";
		secondImagePath = String.valueOf((id + 1)) + "b.png";
		
		InputStream ims = null;
		try {
			ims = getActivity().getAssets().open(firstImagePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    Drawable d = Drawable.createFromStream(ims, null);
	    firstImageView.setImageDrawable(d);
	    
	    InputStream ims1 = null;
		try {
			ims1 = getActivity().getAssets().open(secondImagePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	    Drawable d1 = Drawable.createFromStream(ims1, null);
	    secondImageView.setImageDrawable(d1);
		
		firstLayout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String stringNextId = model.getQuestions().get(id).getChoice1().getNextId();
				int nextId = 2;
				
				if(!stringNextId.equals("")) {
					nextId = Integer.parseInt(stringNextId);
				} else {
					// TODO INSERT TREEINFO SCREEN CALL HERE
				}
				
				nextId--;
				
				QuestionFragment questionFragment = new QuestionFragment();
				Bundle bundle = new Bundle();
				bundle.putInt("ID", nextId);
				questionFragment.setArguments(bundle);
				
				((FragmentActivityBase) getActivity()).startFragment(questionFragment, String.valueOf(nextId));
			}
		});
		
		secondLayout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String stringNextId = model.getQuestions().get(id).getChoice2().getNextId();
				int nextId = Integer.parseInt(stringNextId);
				nextId--;
				
				QuestionFragment questionFragment = new QuestionFragment();
				Bundle bundle = new Bundle();
				bundle.putInt("ID", nextId);
				questionFragment.setArguments(bundle);
				
				((FragmentActivityBase) getActivity()).startFragment(questionFragment, String.valueOf(nextId));
			}
		});
		
		return view;
	}
}
