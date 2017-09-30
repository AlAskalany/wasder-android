package com.wasder.wasderapp.data;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import org.jetbrains.annotations.Contract;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/28/2017.
 */
@IgnoreExtraProperties
public class User {
	
	/**
	 * User display name
	 */
	@SuppressWarnings("WeakerAccess")
	public String displayName;
	/**
	 * User email
	 */
	@SuppressWarnings("WeakerAccess")
	public String email;
	/**
	 * User email verification flag
	 */
	@SuppressWarnings("WeakerAccess")
	public boolean emailVerified;
	/**
	 *
	 */
	@SuppressWarnings("WeakerAccess")
	public Uri photoUrl;
	/**
	 * User ID
	 */
	@SuppressWarnings("WeakerAccess")
	public String uid;
	
	/**
	 * Default constructor required for calls to DataSnapshot.getValue(User.class)
	 */
	public User() {
		
	}
	
	/**
	 * @param displayName User display name
	 * @param email       User email
	 * @param photoUrl    user photo
	 */
	@SuppressWarnings("WeakerAccess")
	public User(String displayName, String email, Uri photoUrl) {
		
		this.displayName = displayName;
		this.email = email;
		this.photoUrl = photoUrl;
	}
	
	/**
	 * @param displayName User display name
	 * @param email       User email
	 * @param photoUrl    User photo URL
	 * @return {@link User} New User
	 */
	@Exclude
	@Contract("_, _, _ -> !null")
	public static User newInstance(String displayName, String email, Uri photoUrl) {
		
		return new User(displayName, email, photoUrl);
	}
	
	/**
	 * Check if user email is verified
	 *
	 * @return {@link Boolean}
	 */
	@Exclude
	public boolean isEmailVerified() {
		
		return emailVerified;
	}
	
	/**
	 * Set {@link #emailVerified}
	 *
	 * @param emailVerified Is email verified?
	 */
	@Exclude
	public void setEmailVerified(boolean emailVerified) {
		
		this.emailVerified = emailVerified;
	}
	
	/**
	 * Get {@link #email}
	 *
	 * @return {@link String} User email
	 */
	@Exclude
	public String getEmail() {
		
		return email;
	}
	
	/**
	 * Set {@link #email}
	 *
	 * @param email User email
	 */
	@SuppressWarnings("WeakerAccess")
	@Exclude
	public void setEmail(String email) {
		
		this.email = email;
	}
	
	/**
	 * Get {@link #photoUrl}
	 *
	 * @return {@link Uri} User photo URL
	 */
	@Exclude
	public Uri getPhotoUrl() {
		
		return photoUrl;
	}
	
	/**
	 * Set {@link #photoUrl}
	 *
	 * @param photoUrl User photo URL
	 */
	@SuppressWarnings("WeakerAccess")
	@Exclude
	public void setPhotoUrl(Uri photoUrl) {
		
		this.photoUrl = photoUrl;
	}
	
	/**
	 * Get {@link #displayName}
	 *
	 * @return Display name
	 */
	@Exclude
	public String getDisplayName() {
		
		return displayName;
	}
	
	/**
	 * Set {@link #displayName}
	 *
	 * @param displayName User display name
	 */
	@SuppressWarnings("WeakerAccess")
	@Exclude
	public void setDisplayName(String displayName) {
		
		this.displayName = displayName;
	}
	
	/**
	 * Get user information
	 */
	@Exclude
	public void getUserProfileInfo() {
		
		FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
		if (user != null) {
			this.displayName = user.getDisplayName();
			this.email = user.getEmail();
			this.emailVerified = user.isEmailVerified();
			this.photoUrl = user.getPhotoUrl();
			this.uid = user.getUid();
		}
	}
	
	/**
	 * Update {@link #displayName}
	 *
	 * @param displayName User display name
	 */
	@Exclude
	public void updateDisplayName(String displayName) {
		
		final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
		if (user != null) {
			UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(displayName).build();
			user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
				@Override
				public void onComplete(@NonNull Task<Void> task) {
					
					//noinspection StatementWithEmptyBody
					if (task.isSuccessful()) {
						setDisplayName(user.getDisplayName());
						// TODO create toast informing user of name update success
					} else {
						// TODO inform user of name update failure
					}
				}
			});
		}
	}
	
	/**
	 * Update Photo URL
	 *
	 * @param photoUrl New photo URL
	 */
	@Exclude
	public void updatePhotoUrl(Uri photoUrl) {
		
		final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
		if (user != null) {
			UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setPhotoUri(photoUrl).build();
			user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
				@Override
				public void onComplete(@NonNull Task<Void> task) {
					
					//noinspection StatementWithEmptyBody
					if (task.isSuccessful()) {
						setPhotoUrl(user.getPhotoUrl());
						// TODO create toast informing user of photo url update success
					} else {
						// TODO inform user of photo url update failure
					}
				}
			});
		}
	}
	
	/**
	 * Update Email
	 *
	 * @param email New email
	 */
	@Exclude
	public void updateEmail(String email) {
		// TODO check email
		final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
		if (user != null) {
			user.updateEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
				@Override
				public void onComplete(@NonNull Task<Void> task) {
					
					//noinspection StatementWithEmptyBody
					if (task.isSuccessful()) {
						setEmail(user.getEmail());
						// TODO inform user of email update success
					} else {
						// TODO inform user of email update failure
					}
				}
			});
		}
	}
	
	/**
	 * Update password
	 *
	 * @param password New password
	 */
	@Exclude
	public void updatePassword(String password) {
		// TODO check email
		FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
		if (user != null) {
			user.updatePassword(password).addOnCompleteListener(new OnCompleteListener<Void>() {
				@Override
				public void onComplete(@NonNull Task<Void> task) {
					
					//noinspection StatementWithEmptyBody
					if (task.isSuccessful()) {
						// TODO inform user of password update success
						// TODO Sign-out the user and re-login
					} else {
						// TODO inform user of password update failure
					}
				}
			});
		}
	}
	
	/**
	 * Get profile information from different Sign-in providers
	 */
	@Exclude
	public void getProfileInfoFromProviders() {
		
		FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
		if (user != null) {
			for (UserInfo profile : user.getProviderData()) {
				@SuppressWarnings("UnusedAssignment") String providerId = profile.getProviderId();
				@SuppressWarnings("UnusedAssignment") String uid = profile.getUid();
				@SuppressWarnings("UnusedAssignment") String displayName = profile.getDisplayName();
				@SuppressWarnings("UnusedAssignment") String email = profile.getEmail();
				@SuppressWarnings("UnusedAssignment") Uri photoUrl = profile.getPhotoUrl();
			}
		}
	}
}
