package com.example.demo.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.print.attribute.standard.SheetCollate;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.dto.Group;
import com.example.demo.dto.Member;
import com.example.demo.dto.Schedule;
import com.example.demo.dto.UserVO;
import com.example.demo.dto.addMem;
import com.example.demo.repository.GroupRepository;
import com.example.demo.service.GroupService;
import com.example.demo.service.MemberService;
import com.example.demo.service.ScheduleService;
import com.example.demo.utils.CalMonth;
import com.example.demo.utils.GoogleUtils;
import com.example.demo.utils.TokenUtils;

import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/schedule")
@Log4j2
public class ScheduleController {
	@Resource(name = "scheduleService")
	private ScheduleService scheduleService;
	private CalMonth calMonth=new CalMonth();
	@Resource(name = "memberService")
	private MemberService memberService;
	@Resource(name = "groupService")
	private GroupService groupService;

	@PostMapping()
	@ApiOperation(value = "Test Shedule", tags = "ScheduleCreate")
	private Schedule insert(@RequestBody Schedule schedule) throws ParseException {		
		if(scheduleService.findBySidExists(schedule.getSid())){
			scheduleService.deleteAllBySid(schedule.getSid());
		}
		List<String>months=calMonth.getMonthList(schedule.getRstart(), schedule.getRend());
        
		String key="";
		System.out.println(schedule.toString());
		if(months.size()>1) {			
			for(int i=0;i<months.size();i++) {
				String month=months.get(i);
				Schedule newSchedule=new Schedule();
				newSchedule.setStatus(schedule.getStatus());
				newSchedule.setRstart(schedule.getRstart());
				newSchedule.setRend(schedule.getRend());
				newSchedule.setDescription(schedule.getDescription());
				newSchedule.setTitle(schedule.getTitle());
				newSchedule.setSid(schedule.getSid());
				newSchedule.setGroup(schedule.getGroup());
				newSchedule.setColor(schedule.getColor());
				newSchedule.setMembers(schedule.getMembers());
				newSchedule.setAllDay(schedule.isAllDay());
				newSchedule.setEditable(schedule.isEditable());
				if(i==0) {			
					newSchedule.setKey("head");
					newSchedule.setStart(schedule.getRstart());
					newSchedule.setEnd(calMonth.getDate(month,1));
					schedule=scheduleService.createSchedule(newSchedule);
					key=schedule.getId().toString();
				}else if(i==months.size()-1){				
					newSchedule.setKey(key);
					newSchedule.setStart(calMonth.getDate(month+"01",-1));
					newSchedule.setEnd(schedule.getRend());
					scheduleService.createSchedule(newSchedule);
				}else {				
					newSchedule.setKey(key);
					newSchedule.setStart(calMonth.getDate(month+"01",-1));
					newSchedule.setEnd(calMonth.getDate(month,1));
					scheduleService.createSchedule(newSchedule);
				}
			}
		}else {
			if(schedule.getRstart().equals(schedule.getRend())) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		    	String stringDate=sdf.format(schedule.getRstart());
		    	schedule.setRend(calMonth.addDay(stringDate, 33));
		    	schedule.setEnd(calMonth.addDay(stringDate, 33));
			}
			scheduleService.createSchedule(schedule);
		}	
		return schedule;
	}
	@GetMapping("/{id}")
	@ApiOperation(value = "Test Shedule", tags = "selectScheduleByUserId")
	private List<Schedule> getScheduleByUserId(@PathVariable(value="id") String id) {		
		return scheduleService.findByIdOrKey(id, id);
	}
	
	@GetMapping("/getschedule/{group}/{month}/{userid}")
	@ApiOperation(value = "Test Shedule", tags = "selectByStartTimeAndUid")
	private List<Schedule>  getFindByStartTimeAndUid(@PathVariable(value="group") String group,@PathVariable(value = "month") int month,@PathVariable(value = "userid") String userid) throws ParseException {		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    	String startDate=sdf.format(new Date());
    	String date=startDate.substring(0,6);
    	startDate=sdf.format(calMonth.getMonth(startDate, month));
    	date=startDate.substring(0,6);
    	int IntSDate=Integer.parseInt(date); 
    	String sDate=(IntSDate-1)+"";
    	String eDate=(IntSDate+1)+"";
    	List<Schedule> schedules= scheduleService.findByStartTimeBetweenAndGroup(calMonth.getDateUTC(sDate+"01",-1), calMonth.getDateUTC(eDate,1), group);
    	Group newGroup=groupService.findByGroupName(group);
    	boolean isMaster=false;
    	if(newGroup.getGroupMaster().equals(userid)) {
    		isMaster=true;
    	}else {
    		isMaster=false;
    	}
    	Map<String,Schedule> map=new HashMap<>();
    	for(Schedule sch:schedules) {
    		sch.setStart(sch.getRstart());
    		sch.setEnd(sch.getRend());
    		sch.setEditable(isMaster);
    		sch.setAllDay(true);    		
    		map.put(sch.getSid(), sch);
    	}  
    	List<Schedule> newSchedules = new ArrayList<>(map.values());
    	return newSchedules;
	}
	@GetMapping("/getTodaySchedule/{group}")
	@ApiOperation(value = "Test Shedule", tags = "selectByStartTimeAndUid")
	private List<Schedule>  getTodaySchedule(@PathVariable(value="group") String group) throws ParseException {		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    	String startDate=sdf.format(new Date());
    	String date=startDate.substring(0,6);    	
    	date=startDate.substring(0,6);
    	int IntSDate=Integer.parseInt(date);     		
    	String eDate=(IntSDate+2)+"";
    	List<Schedule> schedules= scheduleService.findByRendBetweenAndGroup(new Date(), calMonth.getDateUTC(eDate,1), group);
    	Map<String,Schedule> map=new HashMap<>();
    	for(Schedule sch:schedules) {
    		sch.setStart(sch.getRstart());
    		sch.setEnd(sch.getRend());
    		map.put(sch.getSid(), sch);    	
    	}  
    	List<Schedule> newSchedules = new ArrayList<>(map.values());
    	return newSchedules;
	}
	@GetMapping("/getmyschedule/{group}/{month}/{userid}")
	@ApiOperation(value = "Test Shedule", tags = "selectByStartTimeAndUid")
	private List<Schedule>  getmyschedule(@PathVariable(value="group") String group,@PathVariable(value = "month") int month,@PathVariable(value="userid") String userid) throws ParseException {		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    	String startDate=sdf.format(new Date());
    	String date=startDate.substring(0,6);
    	startDate=sdf.format(calMonth.getMonth(startDate, month));
    	date=startDate.substring(0,6);
    	int IntSDate=Integer.parseInt(date); 
    	String sDate=(IntSDate-1)+"";
    	String eDate=(IntSDate+1)+"";
    	List<Schedule> schedules= scheduleService.findByStartTimeBetweenAndGroup(calMonth.getDateUTC(sDate+"01",-1), calMonth.getDateUTC(eDate,1), group);
    	Map<String,Schedule> map=new HashMap<>();
    	for(Schedule sch:schedules) {
    		sch.setStart(sch.getRstart());
    		sch.setEnd(sch.getRend());
    		map.put(sch.getSid(), sch);
    	}
    	Member member=memberService.findByUserId(userid);
    	if(member!=null) {
    		for(String groupName:member.getGroups()) {
        		schedules= scheduleService.findByStartBetweenAndGroupAndMembers(calMonth.getDateUTC(sDate+"01",-1), calMonth.getDateUTC(eDate,1), groupName,userid);        	
            	for(Schedule sch:schedules) {
            		sch.setStart(sch.getRstart());
            		sch.setEnd(sch.getRend());
            		sch.setAllDay(true);
            		sch.setEditable(false);            		
            		map.put(sch.getSid(), sch);
            	}
        	}
    	}    	
    	List<Schedule> newSchedules = new ArrayList<>(map.values());
    	
    	return newSchedules;
	}
	@GetMapping("/getmyTodaySchedule/{group}/{userid}")
	@ApiOperation(value = "Test Shedule", tags = "selectByStartTimeAndUid")
	private List<Schedule>  getmyTodaySchedule(@PathVariable(value="group") String group,@PathVariable(value="userid") String userid) throws ParseException {		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    	String startDate=sdf.format(new Date());
    	String date=startDate.substring(0,6);    	
    	date=startDate.substring(0,6);
    	int IntSDate=Integer.parseInt(date);     		
    	String eDate=(IntSDate+2)+"";
    	List<Schedule> schedules= scheduleService.findByRendBetweenAndGroup(new Date(), calMonth.getDateUTC(eDate,1), group);
    	Map<String,Schedule> map=new HashMap<>();
    	for(Schedule sch:schedules) {
    		sch.setStart(sch.getRstart());
    		sch.setEnd(sch.getRend());
    		map.put(sch.getSid(), sch);    	
    	}
    	Member member=memberService.findByUserId(userid);
    	if(member!=null) {
    		for(String groupName:member.getGroups()) {
        		schedules=  scheduleService.findByRendBetweenAndGroupAndMembers(new Date(), calMonth.getDateUTC(eDate,1), groupName,userid);        	
            	for(Schedule sch:schedules) {
            		sch.setStart(sch.getRstart());
            		sch.setEnd(sch.getRend());
            		sch.setAllDay(true);
            		sch.setEditable(false);            		
            		map.put(sch.getSid(), sch);
            	}
        	}
    	}    	
    	List<Schedule> newSchedules = new ArrayList<>(map.values());
    	return newSchedules;
	}
	@DeleteMapping("/{sid}")
	@ApiOperation(value = "Test Shedule", tags = "deleteAllByIdOrKey")
	private String deleteAllByIdOrKey(@PathVariable(value="sid") String sid) {
		scheduleService.deleteAllBySid(sid);
		return "member delete success";
	}
	
	@PostMapping("/addMem")
	private String addMem(@RequestBody addMem addMem) throws Exception {
		List<String>memberList=addMem.getMembers();
		String sid=addMem.getSid();
		List<Schedule> list=scheduleService.findBySid(sid);
		
		for(Schedule schedule:list) {
			Group group=groupService.findByGroupName(schedule.getGroup());
			for(String member:memberList) {
				if(!group.getGroupMember().contains(member))
					throw new Exception();
			}
			schedule.setMembers(memberList);
			scheduleService.createSchedule(schedule);
		}
		
		return "success";
		
	}

}
