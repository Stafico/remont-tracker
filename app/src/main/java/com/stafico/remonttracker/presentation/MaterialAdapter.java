package com.stafico.remonttracker.presentation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stafico.remonttracker.domain.model.MaterialModel;
import com.stafico.remonttracker.R;

import java.util.List;

public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.MaterialViewHolder> {

    private final List<MaterialModel> materials;

    public MaterialAdapter(List<MaterialModel> materials) {
        this.materials = materials;
    }

    @NonNull
    @Override
    public MaterialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_material, parent, false);
        return new MaterialViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialViewHolder holder, int position) {
        MaterialModel material = materials.get(position);

        holder.nameInput.setText(material.getName());
        holder.priceInput.setText(material.getPrice());

        holder.nameInput.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                material.setName(s.toString());
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        holder.priceInput.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                material.setPrice(s.toString());
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }

    @Override
    public int getItemCount() {
        return materials.size();
    }

    public static class MaterialViewHolder extends RecyclerView.ViewHolder {
        EditText nameInput, priceInput;

        public MaterialViewHolder(@NonNull View itemView) {
            super(itemView);
            nameInput = itemView.findViewById(R.id.editText_material_name);
            priceInput = itemView.findViewById(R.id.editText_material_price);
        }
    }
}
