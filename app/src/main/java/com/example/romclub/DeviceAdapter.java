package com.example.romclub;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder> {

    public interface OnDeviceClickListener {
        void onDeviceClick(DeviceModel device);
    }

    private List<DeviceModel> devices = new ArrayList<>();
    private DeviceModel currentSelectedDevice;
    private final OnDeviceClickListener listener;

    public DeviceAdapter(OnDeviceClickListener listener) {
        this.listener = listener;
    }

    public void updateData(List<DeviceModel> newDevices, DeviceModel selected) {
        this.devices = newDevices != null ? newDevices : new ArrayList<>();
        this.currentSelectedDevice = selected;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DeviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device, parent, false);
        return new DeviceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceViewHolder holder, int position) {
        DeviceModel device = devices.get(position);
        holder.tvDeviceName.setText(device.getDisplayName());
        holder.tvCodename.setText(device.getCodename());
        holder.tvChipset.setText(device.getChipset());

        if (device.isUserDevice()) {
            holder.tvUserDeviceBadge.setVisibility(View.VISIBLE);
        } else {
            holder.tvUserDeviceBadge.setVisibility(View.GONE);
        }

        boolean isSelected = currentSelectedDevice != null &&
                currentSelectedDevice.getCodename().equalsIgnoreCase(device.getCodename());

        if (isSelected) {
            holder.ivSelectedCheck.setVisibility(View.VISIBLE);
            holder.cardDevice.setStrokeColor(holder.itemView.getContext().getColor(R.color.accent_green));
            holder.cardDevice.setCardBackgroundColor(holder.itemView.getContext().getColor(R.color.surface_card_selected));
        } else {
            holder.ivSelectedCheck.setVisibility(View.GONE);
            holder.cardDevice.setStrokeColor(holder.itemView.getContext().getColor(R.color.surface_stroke));
            holder.cardDevice.setCardBackgroundColor(holder.itemView.getContext().getColor(R.color.surface_card));
        }

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDeviceClick(device);
            }
        });
    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

    static class DeviceViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView cardDevice;
        ImageView ivDeviceIcon, ivSelectedCheck;
        TextView tvDeviceName, tvCodename, tvChipset, tvUserDeviceBadge;

        public DeviceViewHolder(@NonNull View itemView) {
            super(itemView);
            cardDevice = itemView.findViewById(R.id.cardDevice);
            ivDeviceIcon = itemView.findViewById(R.id.ivDeviceIcon);
            ivSelectedCheck = itemView.findViewById(R.id.ivSelectedCheck);
            tvDeviceName = itemView.findViewById(R.id.tvDeviceName);
            tvCodename = itemView.findViewById(R.id.tvCodename);
            tvChipset = itemView.findViewById(R.id.tvChipset);
            tvUserDeviceBadge = itemView.findViewById(R.id.tvUserDeviceBadge);
        }
    }
}
