package com.kanikash.gridimagesearch.models;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.kanikash.gridimagesearch.R;
import com.kanikash.gridimagesearch.activities.SearchActivity;

/**
 * Created by kanikash on 2/1/15.
 */
public class SearchFilterFragment extends DialogFragment {

    private String imageSize;
    private String colorFilter;
    private String typeFilter;
    private String siteFilter;

    private EditText etSite;
    private static Bundle args;

    public SearchFilterFragment() {

    }

    public static SearchFilterFragment newInstance(String imSize, String imColor, String imType, String imSite) {
        SearchFilterFragment filter = new SearchFilterFragment();
        args = new Bundle();
        args.putString("imSize", imSize);
        args.putString("imType", imType);
        args.putString("imColor", imColor);
        args.putString("imSite", imSite);
        filter.setArguments(args);
        return filter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_filters, container);
        //this.getDialog().requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
       //this.getDialog().getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.dialog_title);
        //setStyle(STYLE_NORMAL, android.R.style.Theme_Holo_Wallpaper);
        getDialog().setTitle("Advanced Filters");
        //LayoutInflater actionBarInflator = (LayoutInflater) this.getSys
        //setStyle(STYLE_NO_TITLE,0);
        TextView tvTitle = (TextView) this.getDialog().findViewById(android.R.id.title);
        tvTitle.setTextColor(getResources().getColor(android.R.color.darker_gray));
        tvTitle.setGravity(Gravity.CENTER_VERTICAL);

        Spinner spSize = (Spinner) view.findViewById(R.id.spSize);
        spSize.setSelection(((ArrayAdapter) spSize.getAdapter()).getPosition(this.args.get("imSize")));
        spSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                imageSize = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner spColorFilter = (Spinner) view.findViewById(R.id.spColorFilter);
        spColorFilter.setSelection(((ArrayAdapter) spColorFilter.getAdapter()).getPosition(this.args.get("imColor")));
        spColorFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                colorFilter = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner spType = (Spinner) view.findViewById(R.id.spType);
        spType.setSelection(((ArrayAdapter) spType.getAdapter()).getPosition(this.args.get("imType")));
        spType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                typeFilter = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        etSite = (EditText) view.findViewById(R.id.etSite);
        etSite.setText(this.args.getString("imSite"));


        Button cancelButton = (Button) view.findViewById(R.id.btnCancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        Button saveButton = (Button) view.findViewById(R.id.btnSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                siteFilter = etSite.getText().toString();
                ((SearchActivity)getActivity()).setAdditionalFilter(imageSize, colorFilter, typeFilter, siteFilter);
                dismiss();
            }
        });



        return view;
    }


}
