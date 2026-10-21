package com.example.romclub;

import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;

public class GuideDetailActivity extends AppCompatActivity {

    public static final String EXTRA_GUIDE = "extra_guide_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_detail);

        GuideModel guide;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            guide = getIntent().getSerializableExtra(EXTRA_GUIDE, GuideModel.class);
        } else {
            guide = (GuideModel) getIntent().getSerializableExtra(EXTRA_GUIDE);
        }

        if (guide == null) {
            Toast.makeText(this, "Failed to load guide", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(guide.getTitle());
        toolbar.setNavigationOnClickListener(v -> finish());

        ((TextView) findViewById(R.id.tvGuideDetailTitle)).setText(guide.getTitle());
        ((TextView) findViewById(R.id.tvGuideDetailSubtitle)).setText(guide.getSubtitle());
        ((TextView) findViewById(R.id.tvGuideDetailDifficulty)).setText(guide.getDifficulty());
        ((TextView) findViewById(R.id.tvGuideDetailTime)).setText(guide.getEstimatedTime());

        View cardWarning = findViewById(R.id.cardWarning);
        if (guide.getWarning() != null && !guide.getWarning().isEmpty()) {
            cardWarning.setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.tvWarningText)).setText(guide.getWarning());
        } else {
            cardWarning.setVisibility(View.GONE);
        }

        LinearLayout stepsContainer = findViewById(R.id.layoutStepsContainer);
        stepsContainer.removeAllViews();
        int stepNumber = 1;
        for (String stepText : guide.getSteps()) {
            stepsContainer.addView(createStepCard(stepNumber++, stepText));
        }
    }

    private View createStepCard(int stepNumber, String stepText) {
        float density = getResources().getDisplayMetrics().density;

        MaterialCardView card = new MaterialCardView(this);
        card.setCardBackgroundColor(getColor(R.color.surface_card));
        card.setRadius(16 * density);
        card.setStrokeColor(getColor(R.color.surface_stroke));
        card.setStrokeWidth(1);

        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        cardParams.bottomMargin = (int) (12 * density);
        card.setLayoutParams(cardParams);

        LinearLayout content = new LinearLayout(this);
        content.setOrientation(LinearLayout.HORIZONTAL);
        int pad = (int) (16 * density);
        content.setPadding(pad, pad, pad, pad);

        TextView tvNum = new TextView(this);
        tvNum.setText(String.valueOf(stepNumber));
        tvNum.setTextColor(getColor(R.color.accent_green));
        tvNum.setTextSize(16);
        tvNum.setTypeface(null, android.graphics.Typeface.BOLD);
        tvNum.setBackgroundResource(R.drawable.bg_badge_official);
        tvNum.setGravity(Gravity.CENTER);
        int size = (int) (32 * density);
        LinearLayout.LayoutParams numParams = new LinearLayout.LayoutParams(size, size);
        numParams.rightMargin = (int) (14 * density);
        tvNum.setLayoutParams(numParams);

        TextView tvDesc = new TextView(this);
        tvDesc.setText(stepText);
        tvDesc.setTextColor(getColor(R.color.text_primary));
        tvDesc.setTextSize(14);
        tvDesc.setLineSpacing(4 * density, 1.0f);
        tvDesc.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));

        content.addView(tvNum);
        content.addView(tvDesc);
        card.addView(content);
        return card;
    }
}
