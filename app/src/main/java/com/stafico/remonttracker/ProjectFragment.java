package com.stafico.remonttracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class ProjectFragment extends Fragment {

    private static final String ARG_TITLE = "title";
    private static final String ARG_DESCRIPTION = "description";

    private String projectTitle;
    private String projectDescription;

    public ProjectFragment() {
        // Обов'язковий порожній конструктор
    }

    // Фабричний метод для створення нового фрагмента з параметрами
    public static ProjectFragment newInstance(String title, String description) {
        ProjectFragment fragment = new ProjectFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_DESCRIPTION, description);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            projectTitle = getArguments().getString(ARG_TITLE);
            projectDescription = getArguments().getString(ARG_DESCRIPTION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project, container, false);

        TextView titleView = view.findViewById(R.id.projectTitle);
        TextView descView = view.findViewById(R.id.projectDescription);

        titleView.setText(projectTitle);
        descView.setText(projectDescription);

        return view;
    }
}
