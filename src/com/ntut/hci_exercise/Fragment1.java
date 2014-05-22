package com.ntut.hci_exercise;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Fragment1 extends Fragment {
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment1, container, false);
		Button btn1 = (Button)rootView.findViewById(R.id.button1);
		btn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Fragment f2 = getFragmentManager().findFragmentById(R.layout.fragment2);
				TextView tv = (TextView)f2.getView().findViewById(R.id.textview1);
				tv.setText("This is button11111111111111111");
				FragmentTransaction transaction = getFragmentManager().beginTransaction();
				transaction.replace(R.layout.fragment2, f2);
				transaction.addToBackStack(null);
				transaction.commit();
			}
		});
	    return rootView;
    }
}