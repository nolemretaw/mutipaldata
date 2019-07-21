package com.lanou.controller;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

import com.lanou.config.TimeConfig;
import com.lanou.service.cluster.ClusterEmpService;
import com.lanou.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.lanou.bean.Emp;
import com.lanou.service.master.EmpService;

import javax.annotation.Resource;

@Controller
@RequestMapping("/emp")
public class EmpController {

	@Autowired
	private TimeConfig timeConfig;

	@Autowired
	private ThreadPoolTaskScheduler threadPoolTaskScheduler;

	private ScheduledFuture<?> future1;

	private ScheduledFuture<?> future2;

	@Bean
	public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
		return new ThreadPoolTaskScheduler();
	}

	@Resource(name = "empService")
	private EmpService empService;

	@Resource(name = "clusterEmpService")
	private ClusterEmpService clusterEmpService;

	@RequestMapping("/test")
	@ResponseBody
	public String sayhello(){
		return "hello";
	}

	/**跳转到templates目录下的thymeleaf引擎页面*/
	@RequestMapping("/listEmp")
	public String listEmp(Model model) {
		List<Emp> emps = empService.findAllEmp();
		model.addAttribute("emps",emps);
		return "show";
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public boolean deleteEmpById(@PathVariable("id") int id){
		empService.deleteEmpById(id);
		System.out.println(id);
		return true;
	}
	
	
	@RequestMapping(value="/updateEmp",method=RequestMethod.PUT)
	@ResponseBody
	public boolean updateEmp(@RequestBody Emp emp) {
		empService.update(emp);
		return true;
	}
	
	@RequestMapping(value="/addEmp",method=RequestMethod.POST)
	@ResponseBody
	public boolean addEmp(@RequestBody Emp emp) {
		System.out.println("添加员工:"+emp);
		empService.addEmp(emp);
		return true;
	}

	@RequestMapping(value = "/mysql2Postgres",method = RequestMethod.GET)
	@ResponseBody
	public String mysql2Postgres(){
		Runnable runnable = ()->{List<Emp> empList = clusterEmpService.findAllEmp();
			for (Emp emp:empList
			) {
				System.out.println(emp);
				empService.addEmp(emp);
			}
			System.out.println("转存至PostgresDB");};
		future1 = threadPoolTaskScheduler.schedule(runnable, new Trigger() {
			@Override
			public Date nextExecutionTime(TriggerContext triggerContext) {
				return new CronTrigger(timeConfig.getCorn1()).nextExecutionTime(triggerContext);
			}
		});
		return "转存至PostgresDB";
	}

	@RequestMapping("/stopCron1")
	public String stopCron1(){
		if (future1 != null){
			future1.cancel(true);
		}
		System.out.println("mysql2Postgres was closed");
		return "mysql2Postgres was closed";
	}

}
