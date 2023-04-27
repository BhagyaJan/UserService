package com.poc.sap.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.sap.model.Jobs;
import com.poc.sap.repo.JobsRepo;
import com.poc.sap.services.JobService;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	private JobsRepo jobsRepo;
	public List<Jobs> getAllJobs() {
		return jobsRepo.findAll();
	}

	public Optional<Jobs> getJobDetails(int id) {
		return jobsRepo.findById(id);
	}

	public void addJobs(Jobs job) {
		jobsRepo.save(job);
		
	}

	public List<Jobs> getFavorities() {
		return jobsRepo.getFavorities();
	}

	public void UpdateFavorite(boolean isFavorite, int id) {	
		 jobsRepo.updateFavoriate(isFavorite, id);
	}

}
