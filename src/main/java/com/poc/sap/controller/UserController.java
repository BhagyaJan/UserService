package com.poc.sap.controller;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.poc.sap.model.Jobs;
import com.poc.sap.model.RequestForm;
import com.poc.sap.model.Users;
import com.poc.sap.services.JobService;
import com.poc.sap.services.MessageProducer;
import com.poc.sap.services.UserService;

@RestController
@RequestMapping("/v1/sap")
public class UserController {
	private final MessageProducer producerService;
	@Autowired
	private UserService userService;

	@Autowired
	private JobService jobService;

	@Autowired
	private RequestForm requestForm;

	public UserController(MessageProducer producerService) {
		this.producerService = producerService;
	}

	@GetMapping("/hello")
	public String hello() {
		return "Hello!...";
	}

	@GetMapping("/start")
	public ModelAndView start() {
		ModelAndView modelAndView = new ModelAndView("index");
		return modelAndView;
	}

	@GetMapping("/request")
	public ModelAndView request(Model model) {
		ModelAndView modelAndView = new ModelAndView("request");
		modelAndView.addObject(requestForm);
		return modelAndView;
	}

	@PostMapping("/newJob")
	public ModelAndView submitForm(@ModelAttribute("requestForm") RequestForm requestForm) {
		System.out.println(requestForm);
		ObjectMapper mapper = new ObjectMapper();
		Users user = null;
		try {
			user = mapper.readValue(requestForm.getBody(), Users.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Users userCreated = userService.addUser(user);
		Jobs job = new Jobs();
		job.setExecution(LocalTime.now().toString());
		job.setSubmitted(LocalTime.now().toString());
		if (LocalTime.now().getSecond() % 2 == 0) {
			job.setFavorite(true);
		} else {
			job.setFavorite(false);
		}
		job.setJob_name(requestForm.getJobName());
		job.setUser(userCreated);
		jobService.addJobs(job);
		List<Jobs> jobs = jobService.getAllJobs();
		ModelAndView modelAndView = new ModelAndView("all-jobs");
		modelAndView.addObject("jobs", jobs);
		return modelAndView;
	}
			

	@PostMapping("/createMessage")
	public void create(@RequestBody Users user) {
		// producerService.sendMessage(message);

		Gson objGson = new GsonBuilder().setPrettyPrinting().create();
		String userMessageJson = objGson.toJson(user);
		System.out.println("1. Convert Person object to Json  " + userMessageJson);
		String message = userMessageJson.substring(userMessageJson.indexOf(",") + 1);
		producerService.sendMessage("{" + message);
	}

	@PostMapping("/request")
	public Users newRequest(Users user) {
		Users userCreated = userService.addUser(user);
		Jobs job = new Jobs();
		job.setExecution(LocalTime.now().toString());
		job.setSubmitted(LocalTime.now().toString());
		if (LocalTime.now().getSecond() % 2 == 0) {
			job.setFavorite(true);
		} else {
			job.setFavorite(false);
		}
		job.setJob_name("creation");
		job.setStatus("Completed");
		job.setUser(userCreated);
		jobService.addJobs(job);
		return userCreated;
	}

	@PostMapping("/addUser")
	public Users addUser(@RequestBody Users user) {
		Users userCreated = userService.addUser(user);
		Jobs job = new Jobs();
		job.setExecution(LocalTime.now().toString());
		job.setSubmitted(LocalTime.now().toString());
		if (LocalTime.now().getSecond() % 2 == 0) {
			job.setFavorite(true);
		} else {
			job.setFavorite(false);
		}
		job.setJob_name("creation");
		job.setStatus("Completed");
		job.setUser(userCreated);
		jobService.addJobs(job);
		return userCreated;
	}

	@PostMapping("/addJob")
	public void addJob(Jobs jobs) {
		Jobs job = new Jobs();
		job.setExecution(LocalTime.now().toString());
		job.setSubmitted(LocalTime.now().toString());
		job.setFavorite(false);
		job.setJob_name("creation");
		job.setStatus("Completed");
		jobService.addJobs(job);
	}

	@GetMapping("/user/{id}")
	public Optional<Users> findUser(@PathVariable int id) {
		return userService.getUserDetail(id);
	}

	@GetMapping("/alljobs")
	public ModelAndView gatAllJobs() {
		List<Jobs> jobs = jobService.getAllJobs();
		ModelAndView modelAndView = new ModelAndView("all-jobs");
		modelAndView.addObject("jobs", jobs);
		return modelAndView;
	}

	@GetMapping("/job/favorites")
	public ModelAndView gatAllFavorites() {
		List<Jobs> jobs = jobService.getFavorities();
		ModelAndView modelAndView = new ModelAndView("all-jobs");
		modelAndView.addObject("jobs", jobs);
		return modelAndView;
	}

	@GetMapping("/job/{id}")
	public ModelAndView gatJobDetail(@PathVariable int id) {
		Optional<Jobs> jobs = jobService.getJobDetails(id);
		Jobs info = null;
		if (jobs.isPresent()) {
			info = jobs.get();
		}
		ModelAndView modelAndView = new ModelAndView("job-info");
		modelAndView.addObject("jobInfo", info);
		return modelAndView;
	}

	
	@PostMapping("/job/{isFavorite}/{id}")
	public ModelAndView updateFAvorite(@PathVariable boolean isFavorite,@PathVariable int id) {
		 jobService.UpdateFavorite(isFavorite, id);
		List<Jobs> jobs = jobService.getAllJobs();
		ModelAndView modelAndView = new ModelAndView("all-jobs");
		modelAndView.addObject("jobs", jobs);
		return modelAndView;
	}
	
	/*
	 * @GetMapping("/job/{id}") public Optional<Jobs> gatJobDetail(@PathVariable int
	 * id) { return jobService.getJobDetails(id); }
	 */
}
