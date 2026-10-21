package com.example.romclub;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class RomAdapter extends RecyclerView.Adapter<RomAdapter.RomViewHolder> {

    public interface OnRomClickListener {
        void onRomClick(RomModel rom);
        void onDownloadClick(RomModel rom);
        void onWebsiteClick(RomModel rom);
        void onGitHubClick(RomModel rom);
        void onCopyLinkClick(RomModel rom);
    }

    private List<RomModel> roms = new ArrayList<>();
    private final OnRomClickListener listener;

    public RomAdapter(OnRomClickListener listener) {
        this.listener = listener;
    }

    public void updateData(List<RomModel> newRoms) {
        this.roms = newRoms != null ? newRoms : new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rom, parent, false);
        return new RomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RomViewHolder holder, int position) {
        RomModel rom = roms.get(position);

        holder.tvRomTitle.setText(rom.getTitle());
        holder.tvTypeBadge.setText(rom.getType());
        if (rom.getType().equalsIgnoreCase("Official")) {
            holder.tvTypeBadge.setBackgroundResource(R.drawable.bg_badge_official);
            holder.tvTypeBadge.setTextColor(holder.itemView.getContext().getColor(R.color.accent_green));
        } else {
            holder.tvTypeBadge.setBackgroundResource(R.drawable.bg_badge_unofficial);
            holder.tvTypeBadge.setTextColor(holder.itemView.getContext().getColor(R.color.accent_orange));
        }

        holder.tvCategoryBadge.setText(rom.getCategory());
        holder.tvAndroidVersion.setText("Android " + rom.getAndroidVersion());

        if (rom.isGappsIncluded()) {
            holder.tvGappsBadge.setVisibility(View.VISIBLE);
            holder.tvGappsBadge.setText("GApps Included");
            holder.tvGappsBadge.setBackgroundResource(R.drawable.bg_badge_official);
            holder.tvGappsBadge.setTextColor(holder.itemView.getContext().getColor(R.color.accent_green));
        } else {
            holder.tvGappsBadge.setVisibility(View.VISIBLE);
            holder.tvGappsBadge.setText("Vanilla");
            holder.tvGappsBadge.setBackgroundResource(R.drawable.bg_search_bar);
            holder.tvGappsBadge.setTextColor(holder.itemView.getContext().getColor(R.color.text_secondary));
        }

        holder.tvMaintainer.setText("by " + rom.getMaintainer());
        holder.tvFileSize.setText(rom.getFileSize());
        holder.tvReleaseDate.setText("Updated: " + rom.getReleaseDate());

        // Domain Preview
        String domain = extractDomain(rom.getWebsiteUrl(), rom.getGithubUrl(), rom.getDownloadUrl());
        holder.tvRomDomain.setText(domain);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onRomClick(rom);
        });

        holder.btnDownloadRom.setOnClickListener(v -> {
            if (listener != null) listener.onDownloadClick(rom);
        });

        holder.btnRomWeb.setOnClickListener(v -> {
            if (listener != null) listener.onWebsiteClick(rom);
        });

        holder.btnRomGitHub.setOnClickListener(v -> {
            if (listener != null) listener.onGitHubClick(rom);
        });

        holder.btnRomCopy.setOnClickListener(v -> {
            if (listener != null) listener.onCopyLinkClick(rom);
        });
    }

    private String extractDomain(String webUrl, String gitHubUrl, String downloadUrl) {
        String target = (webUrl != null && !webUrl.isEmpty()) ? webUrl :
                ((gitHubUrl != null && !gitHubUrl.isEmpty()) ? gitHubUrl : downloadUrl);
        if (target == null || target.isEmpty()) return "romclub";
        try {
            Uri uri = Uri.parse(target);
            String host = uri.getHost();
            if (host != null) {
                return host.replace("www.", "");
            }
        } catch (Exception ignored) {
        }
        return "official source";
    }

    @Override
    public int getItemCount() {
        return roms.size();
    }

    static class RomViewHolder extends RecyclerView.ViewHolder {
        TextView tvRomTitle, tvTypeBadge, tvCategoryBadge, tvAndroidVersion, tvGappsBadge;
        TextView tvMaintainer, tvFileSize, tvReleaseDate, tvRomDomain;
        MaterialButton btnDownloadRom, btnRomWeb, btnRomGitHub, btnRomCopy;

        public RomViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRomTitle = itemView.findViewById(R.id.tvRomTitle);
            tvTypeBadge = itemView.findViewById(R.id.tvTypeBadge);
            tvCategoryBadge = itemView.findViewById(R.id.tvCategoryBadge);
            tvAndroidVersion = itemView.findViewById(R.id.tvAndroidVersion);
            tvGappsBadge = itemView.findViewById(R.id.tvGappsBadge);
            tvMaintainer = itemView.findViewById(R.id.tvMaintainer);
            tvFileSize = itemView.findViewById(R.id.tvFileSize);
            tvReleaseDate = itemView.findViewById(R.id.tvReleaseDate);
            tvRomDomain = itemView.findViewById(R.id.tvRomDomain);
            btnDownloadRom = itemView.findViewById(R.id.btnDownloadRom);
            btnRomWeb = itemView.findViewById(R.id.btnRomWeb);
            btnRomGitHub = itemView.findViewById(R.id.btnRomGitHub);
            btnRomCopy = itemView.findViewById(R.id.btnRomCopy);
        }
    }
}
