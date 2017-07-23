package cn.kanejin.view.categorypager.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.kanejin.view.categorypager.R;

/**
 * Created by Kane on 23/07/2017.
 */

public class DemoCategoryFragment extends Fragment {

    private static final String TAG = DemoCategoryFragment.class.getSimpleName();

    private TextView mCategoryTitle;

    private String title;

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);

        title = args.getString("title");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_demo_category, null);

        this.mCategoryTitle = (TextView) rootView.findViewById(R.id.category_title);
        mCategoryTitle.setText(title);

        // Inflate the layout for this fragment
        return rootView;
    }
}
