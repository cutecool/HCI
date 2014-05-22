package com.ntut.hci_exercise;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

public class Fragment2 extends Fragment {
	View v;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.fragment2, container, false);
		
		WindowManager wm = getActivity().getWindowManager();
		Display d = wm.getDefaultDisplay();
		
		if (d.getHeight() > d.getWidth()) {
			Bundle bundle = getArguments();
			setTextView(bundle.getString("text"));
		}
		return v;
    }
	
	public void setTextView(String text) {
		TextView tv = (TextView)v.findViewById(R.id.textview1);
		tv.setText(text);
	}
}