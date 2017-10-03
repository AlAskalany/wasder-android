package com.wasder.wasderapp;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;


/**
 * Created by Ahmed AlAskalany on 10/3/2017.
 * Project: wasder-android
 * Package: ${PACKAGE_NAME}
 */

public class MyJobService extends JobService {
	
	@Override
	public boolean onStartJob(JobParameters job) {
		// Do some work here
		
		return false; // Answers the question: "Is there still work going on?"
	}
	
	@Override
	public boolean onStopJob(JobParameters job) {
		
		return false; // Answers the question: "Should this job be retried?"
	}
}
