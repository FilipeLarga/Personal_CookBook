package filipelarga.personalcookbook_freerecipemanager.recyclerview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

import filipelarga.personalcookbook_freerecipemanager.R;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

public class ListRecipesFragment extends Fragment {

    private SectionedRecyclerViewAdapter sectionAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_recipes, container, false);
        sectionAdapter = new SectionedRecyclerViewAdapter();

        List<String> meatList = Arrays.asList("Frango", "Lasanha", "Picanha", "dab on the haters");
        List<String> fishList = Arrays.asList("Salmão", "Robalo", "Sardinhas", "dab on haters", "1", "2", "3", "4", "5", "6", "7", "1", "2", "3", "4", "5", "6", "7", "1", "2", "3", "4", "5", "6", "7", "1", "2", "3", "4", "5", "6", "7"  );
        sectionAdapter.addSection(new CategorySection("Carne", meatList));
        sectionAdapter.addSection(new CategorySection("Fish", fishList));

        RecyclerView recyclerView = view.findViewById(R.id.recyclerviwe);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(sectionAdapter);
        return view;
    }
}
