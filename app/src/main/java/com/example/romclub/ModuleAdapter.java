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

public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ModuleViewHolder> {

    public interface OnModuleClickListener {
        void onModuleClick(ModuleModel module);
        void onDownloadClick(ModuleModel module);
        void onGitHubClick(ModuleModel module);
        void onCopyLinkClick(ModuleModel module);
    }

    private List<ModuleModel> modules = new ArrayList<>();
    private final OnModuleClickListener listener;

    public ModuleAdapter(OnModuleClickListener listener) {
        this.listener = listener;
    }

    public void updateData(List<ModuleModel> newModules) {
        this.modules = newModules != null ? newModules : new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ModuleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_module, parent, false);
        return new ModuleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ModuleViewHolder holder, int position) {
        ModuleModel module = modules.get(position);

        holder.tvModuleName.setText(module.getName());
        holder.tvModuleVersion.setText(module.getVersion());
        holder.tvModuleAuthor.setText("by " + module.getAuthor());
        holder.tvModuleCategory.setText(module.getCategory());
        holder.tvCompatibility.setText(module.getCompatibility());
        holder.tvModuleDescription.setText(module.getDescription());

        // Domain preview
        String domain = extractDomain(module.getGithubUrl(), module.getDownloadUrl());
        holder.tvModuleDomain.setText(domain);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onModuleClick(module);
        });

        holder.btnDownloadModule.setOnClickListener(v -> {
            if (listener != null) listener.onDownloadClick(module);
        });

        holder.btnGitHub.setOnClickListener(v -> {
            if (listener != null) listener.onGitHubClick(module);
        });

        holder.btnModuleCopy.setOnClickListener(v -> {
            if (listener != null) listener.onCopyLinkClick(module);
        });
    }

    private String extractDomain(String gitHubUrl, String downloadUrl) {
        String target = (gitHubUrl != null && !gitHubUrl.isEmpty()) ? gitHubUrl : downloadUrl;
        if (target == null || target.isEmpty()) return "github.com";
        try {
            Uri uri = Uri.parse(target);
            String host = uri.getHost();
            if (host != null) {
                return host.replace("www.", "");
            }
        } catch (Exception ignored) {
        }
        return "github.com";
    }

    @Override
    public int getItemCount() {
        return modules.size();
    }

    static class ModuleViewHolder extends RecyclerView.ViewHolder {
        TextView tvModuleName, tvModuleVersion, tvModuleAuthor;
        TextView tvModuleCategory, tvCompatibility, tvModuleDescription, tvModuleDomain;
        MaterialButton btnDownloadModule, btnGitHub, btnModuleCopy;

        public ModuleViewHolder(@NonNull View itemView) {
            super(itemView);
            tvModuleName = itemView.findViewById(R.id.tvModuleName);
            tvModuleVersion = itemView.findViewById(R.id.tvModuleVersion);
            tvModuleAuthor = itemView.findViewById(R.id.tvModuleAuthor);
            tvModuleCategory = itemView.findViewById(R.id.tvModuleCategory);
            tvCompatibility = itemView.findViewById(R.id.tvCompatibility);
            tvModuleDescription = itemView.findViewById(R.id.tvModuleDescription);
            tvModuleDomain = itemView.findViewById(R.id.tvModuleDomain);
            btnDownloadModule = itemView.findViewById(R.id.btnDownloadModule);
            btnGitHub = itemView.findViewById(R.id.btnGitHub);
            btnModuleCopy = itemView.findViewById(R.id.btnModuleCopy);
        }
    }
}
