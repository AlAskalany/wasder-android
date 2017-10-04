package com.wasder.wasderapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.amplitude.api.Amplitude;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.wasder.wasderapp.R;

import java.util.Arrays;

public class SplashActivity extends AppCompatActivity {
	
	private static final int RC_SIGN_IN = 9001;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		Amplitude.getInstance().initialize(this, "937ae55b73eb164890021fe9b2d4fa63").enableForegroundTracking(getApplication());
		Amplitude.getInstance().logEvent("Started_Splash_Activity");
		findViewById(R.id.splash_logo);
	}
	
	@Override
	public void onStart() {
		
		super.onStart();
		int SPLASH_DISPLAY_LENGTH = 1000;
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				
				if (shouldStartSignIn()) {
					startSignIn();
					return;
				}
				/* Create an Intent that will start the Menu-Activity. */
				Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
				SplashActivity.this.startActivity(mainIntent);
				SplashActivity.this.finish();
			}
		}, SPLASH_DISPLAY_LENGTH);
	}
	
	private boolean shouldStartSignIn() {
		// TODO implement viewModel
		return (/*!mViewModel.getIsSigningIn() && */FirebaseAuth.getInstance().getCurrentUser() == null);
	}
	
	private void startSignIn() {
		// Sign in with FirebaseUI
		Intent intent = AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI
				.EMAIL_PROVIDER).build(), new AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build(), new AuthUI.IdpConfig.Builder
				(AuthUI.GOOGLE_PROVIDER).build(), new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build(), new AuthUI.IdpConfig.Builder
				(AuthUI.TWITTER_PROVIDER).build())).setIsSmartLockEnabled(false).setTheme(R.style.AppTheme_NoActionBar).build();
		
		startActivityForResult(intent, RC_SIGN_IN);
	}
}
