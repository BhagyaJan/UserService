package com.poc.sap.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.poc.sap.model.Jobs;

@Transactional
@Repository
public interface JobsRepo extends JpaRepository<Jobs, Integer> {

	@Query(nativeQuery = true, value = "select * from  jobs where is_Favorite = true")
	public List<Jobs> getFavorities();

	@Modifying
	@Query(nativeQuery = true, value = "UPDATE JOBS set is_Favorite=?1 where id =?2")
	public void updateFavoriate(boolean isFavorite, int id);
}
