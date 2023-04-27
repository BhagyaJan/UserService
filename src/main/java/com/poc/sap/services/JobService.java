package com.poc.sap.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.poc.sap.model.Jobs;
import com.poc.sap.model.Users; 
@Service
public interface JobService {
	public List<Jobs> getAllJobs();
	public Optional<Jobs> getJobDetails(int id);
	public void addJobs(Jobs job);
	public List<Jobs> getFavorities();
	public void UpdateFavorite(boolean isFavorite, int id);
}
