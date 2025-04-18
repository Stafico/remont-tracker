package com.stafico.remonttracker.bluetooth;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.stafico.remonttracker.R;
import com.stafico.remonttracker.databinding.ListItemBinding;

public class RcAdapter extends ListAdapter<ListItem, RcAdapter.ItemHolder> {

    private Listener listener;

    public RcAdapter(RcAdapter.Listener listener) {
        super(new ItemComparator());
        this.listener = listener;
    }

    public static class ItemHolder extends RecyclerView.ViewHolder {
        private final ListItemBinding binding;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            binding = ListItemBinding.bind(itemView);
        }

        public void setData(ListItem item, Listener listener) {
            binding.tvName.setText(item.getName());
            binding.tvMac.setText(item.getMac());
            itemView.setOnClickListener(v -> {
                listener.onClick(item);
            });
        }

        public static ItemHolder create(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false);
            return new ItemHolder(view);
        }
    }

    public static class ItemComparator extends DiffUtil.ItemCallback<ListItem> {
        @Override
        public boolean areItemsTheSame(@NonNull ListItem oldItem, @NonNull ListItem newItem) {
            return oldItem.getMac().equals(newItem.getMac());
        }

        @Override
        public boolean areContentsTheSame(@NonNull ListItem oldItem, @NonNull ListItem newItem) {
            return oldItem.getName().equals(newItem.getName())
                    && oldItem.getMac().equals(newItem.getMac());
        }
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ItemHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        holder.setData(getItem(position), listener);
    }

    // Для передачі натискання
    public interface Listener {
        void onClick(ListItem item);
    }
}
