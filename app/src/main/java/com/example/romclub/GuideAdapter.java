package com.example.romclub;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GuideAdapter extends RecyclerView.Adapter<GuideAdapter.GuideViewHolder> {

    public interface OnGuideClickListener {
        void onGuideClick(GuideModel guide);
    }

    private List<GuideModel> guides = new ArrayList<>();
    private final OnGuideClickListener listener;

    public GuideAdapter(OnGuideClickListener listener) {
        this.listener = listener;
    }

    public void updateData(List<GuideModel> newGuides) {
        this.guides = newGuides != null ? newGuides : new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GuideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_guide, parent, false);
        return new GuideViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GuideViewHolder holder, int position) {
        GuideModel guide = guides.get(position);

        holder.tvGuideTitle.setText(guide.getTitle());
        holder.tvGuideSubtitle.setText(guide.getSubtitle());
        holder.tvGuideDifficulty.setText(guide.getDifficulty());
        holder.tvGuideTime.setText(guide.getEstimatedTime());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onGuideClick(guide);
        });
    }

    @Override
    public int getItemCount() {
        return guides.size();
    }

    static class GuideViewHolder extends RecyclerView.ViewHolder {
        TextView tvGuideTitle, tvGuideSubtitle, tvGuideDifficulty, tvGuideTime;

        public GuideViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGuideTitle = itemView.findViewById(R.id.tvGuideTitle);
            tvGuideSubtitle = itemView.findViewById(R.id.tvGuideSubtitle);
            tvGuideDifficulty = itemView.findViewById(R.id.tvGuideDifficulty);
            tvGuideTime = itemView.findViewById(R.id.tvGuideTime);
        }
    }
}
