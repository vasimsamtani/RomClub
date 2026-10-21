package com.example.romclub;

import android.animation.TimeInterpolator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;

import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_DURATION_MS = 2100;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private Runnable navigateRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Logo & ambient glow animations
        animateScale(findViewById(R.id.logoCard), 0.4f, 1f, 1f, 750, new OvershootInterpolator(1.3f));
        animateScale(findViewById(R.id.ambientGlow), 0.2f, 1.1f, 0.25f, 900, null);

        // Staggered slide up elements
        animateSlideUp(findViewById(R.id.titleContainer), 250);
        animateSlideUp(findViewById(R.id.tvSlogan), 450);
        animateSlideUp(findViewById(R.id.tvSubtitle), 600);
        animateSlideUp(findViewById(R.id.badgeCard), 720);

        // Bottom progress indicator fade
        View bottomContent = findViewById(R.id.bottomContent);
        if (bottomContent != null) {
            bottomContent.setAlpha(0f);
            bottomContent.animate().alpha(1f).setStartDelay(750).setDuration(500).start();
        }

        // Seamless transition to DashboardActivity
        navigateRunnable = () -> {
            startActivity(new Intent(SplashActivity.this, DashboardActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        };
        handler.postDelayed(navigateRunnable, SPLASH_DURATION_MS);
    }

    private void animateScale(View view, float startScale, float endScale, float endAlpha, long duration, TimeInterpolator interpolator) {
        if (view == null) return;
        view.setScaleX(startScale);
        view.setScaleY(startScale);
        view.setAlpha(0f);
        var anim = view.animate().scaleX(endScale).scaleY(endScale).alpha(endAlpha).setDuration(duration);
        if (interpolator != null) anim.setInterpolator(interpolator);
        anim.start();
    }

    private void animateSlideUp(View view, long delay) {
        if (view == null) return;
        view.setTranslationY(36f);
        view.setAlpha(0f);
        view.animate()
                .translationY(0f)
                .alpha(1f)
                .setStartDelay(delay)
                .setDuration(550)
                .setInterpolator(new DecelerateInterpolator())
                .start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (navigateRunnable != null) {
            handler.removeCallbacks(navigateRunnable);
        }
    }
}
