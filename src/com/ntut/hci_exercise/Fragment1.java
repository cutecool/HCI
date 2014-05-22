package com.ntut.hci_exercise;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

public class Fragment1 extends Fragment {
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment1, container, false);
		Button btn1 = (Button)rootView.findViewById(R.id.button1);
		btn1.setOnClickListener(clickListener(String.valueOf(btn1.getText())));
		
		Button btn2 = (Button)rootView.findViewById(R.id.button2);
		btn2.setOnClickListener(clickListener(String.valueOf(btn2.getText())));
		
		Button btn3 = (Button)rootView.findViewById(R.id.button3);
		btn3.setOnClickListener(clickListener(String.valueOf(btn3.getText())));
		
		Button btn4 = (Button)rootView.findViewById(R.id.button4);
		btn4.setOnClickListener(clickListener(String.valueOf(btn4.getText())));
		
	    return rootView;
    }
	
	public OnClickListener clickListener(final String name) {
		OnClickListener listener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				WindowManager wm = getActivity().getWindowManager();
				Display d = wm.getDefaultDisplay();
				
				if (d.getHeight() > d.getWidth()) {
					Fragment f2 = new Fragment2();
					Bundle bundle = new Bundle();
					bundle.putString("text", name + "!!!!!!!!!!!!!!!!!!");
					f2.setArguments(bundle);
					FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
					transaction.replace(R.id.container, f2);
					transaction.addToBackStack(null);
					transaction.commit();
				} else {
					Fragment2 f2 = (Fragment2)getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment2);
					f2.setTextView(name + "!!!!!!!!!!!!!!!!!!");
				}
			}
		};
		return listener;
	}
}