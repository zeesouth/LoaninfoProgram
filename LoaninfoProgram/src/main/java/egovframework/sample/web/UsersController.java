package egovframework.sample.web;

import java.lang.reflect.Member;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.sample.service.UsersService;
import egovframework.sample.service.UsersVO;

@Controller
@RequestMapping("/users/*")

public class UsersController {
private static final Logger logger = LoggerFactory.getLogger(UsersController.class);
	
	@Inject
	UsersService service;
	
	// 회원가입 get
	@RequestMapping(value = "/insertUser", method = RequestMethod.GET)
	public void getinsertUser() throws Exception {
		logger.info("get insertUser");
	}
	
	// 회원가입 post
	@RequestMapping(value = "/insertUser", method = RequestMethod.POST)
	public String postRegister(UsersVO vo) throws Exception {
		logger.info("post insertUser");
		
		service.insertUser(vo);
		
		return null;
	}
}
