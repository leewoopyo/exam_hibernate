package com.kopo.exam_hibernate;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kopo.exam_hibernate.service.ExamService;


//@Controller 어노테이션을 적어서(자동으로 적혀져 있음)
//해당 클래스가 controller임을 선언함
@Controller
public class ExamController {
	
	//service를 사용하고자  @Autowired 어노테이션을 통해 service를 선언했다.
	//각 영역 마다 불러올 영역을 Autowired해서 서로 묶었다. 
	//결국 Controller -> Service -> Repository -> JdbcTemplate이 서로 묶여있는 형태가 된다.
	@Autowired
	private ExamService service;
	
	//로그 객체를 선언
	private static final Logger logger = LoggerFactory.getLogger(ExamController.class);
	
	//해당경로('프로젝트/공백')로 URL이동하면 해당 컨트롤러 메소드로 매핑된다.
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		//로그 표시 후 
		logger.info("index.jsp start");
		//index.jsp파일로 이동한다. 
		return "index";
	}
	
	//URL에 '/menu'를 더 적으면 해당 컨트롤러 메소드로 매핑된다.
	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String menu() {
		//로그 표시 후
		logger.info("menu.jsp start");
		//menu.jsp파일로 이동한다.
		return "menu";
	}
	//URL에 '/home'를 더 적으면 해당 컨트롤러 메소드로 매핑된다.
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		//로그를 입력하고
		logger.info("Welcome home! The client locale is {}.", locale);
		//date형의 객체를 선언한다.(현제 시간)
		Date date = new Date();
		//date형을 출력할 포맷을 설정한다.
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		//포맷 적용한 date형 변수를 String형을 문자열에 넣는다.
		String formattedDate = dateFormat.format(date);
		//key값을 serverTime으로 해서 
		//시간이 적혀있는 문자열을 value로하고 model에 담는다.
		model.addAttribute("serverTime", formattedDate );
		//home.jsp로 이동한다.
		return "home";
	}
	
	//URL에 '/allsetDB'를 더 적으면 해당 컨트롤러 메소드로 매핑된다.
	@RequestMapping(value = "/allsetDB", method = RequestMethod.GET)
	public String allsetDB(Model model) {
		//로그 표시하고 
		logger.info("allsetDB.jsp start");
		//작업 메시지 표시할 변수 선언
		String ret=null;
		try {
			//위에서 선언한 service의 allsetDB메소드 요청한다.
			service.allsetDB();
			//요청이 성공하면 위 메시지 를 담고
			ret = "DB allset 성공";
		}catch(Exception e) {
			e.printStackTrace();
			//위에서 예외처리가 났다면 아래 문구를 넣는다.
			ret = "DB allset 실패" + e;
		}
		//위 처리에 따라 넣어진 메시지 값을 value로 하고 
		//'msg'라는 key값을 가진 model에 값을 넣는다.
		model.addAttribute("msg",ret);
		//allsetDB.jsp페이지로 이동한다. 
		return "allsetDB";
	}
	
	//URL에 '/allviewDB'를 더 적으면 해당 컨트롤러 메소드로 매핑된다.
	@RequestMapping(value = "/allviewDB", method = RequestMethod.GET)
	public String allviewDB(Model model) {
		//로그 표시하고
		logger.info("allviewDB.jsp start");
		
		try {
			//위에서 선언한 service의 selectAll메소드 요청한다.
			//selectAll메소드를 통해 나온 리턴값을 value로 해서  
			//'list'란 key값으로 model에 담는다.
			model.addAttribute("list",service.selectAll());
		}catch(Exception e) {
			e.printStackTrace();
		}
		//allviewDB.jsp로 간다.
		return "allviewDB";
	}
	
	//URL에 '/createDB'를 더 적으면 해당 컨트롤러 메소드로 매핑된다.
	@RequestMapping(value = "/createDB", method = RequestMethod.GET)
	public String createDB(Model model) {
		//로그 문구가 뜨고
		logger.info("createDB.jsp start");
		//메시지를 담을 변수 선언
		String ret = null;
		try {
			//위에서 선언한 service의 createDB메소드 요청한다.
			service.createDB();
			//메소드 성공 시 결과 메시지를 변수에 담는다.
			ret = "DB create 성공";
		}catch(Exception e) {
			e.printStackTrace();
			//메소드가 예외처리 날 시 메시지를 변수에 담는다.
			ret = "DB create 실패" + e;
		}
		// 위 처리에 따라 넣어진 메시지 값을 value로 하고 
		// 'msg'라는 key값을 가진 model에 값을 넣는다.
		model.addAttribute("msg",ret);
		//createDB.jsp로 이동한다.
		return "createDB";
	}
	
	//URL에 '/dropDB'를 더 적으면 해당 컨트롤러 메소드로 매핑된다.
	@RequestMapping(value = "/dropDB", method = RequestMethod.GET)
	public String dropDB(Model model) {
		//로그 문구가 뜨고
		logger.info("dropDB.jsp start");
		//메시지를 담을 변수 선언
		String ret = null;
		try {
			//위에서 선언한 service의 dropDB메소드 요청한다.
			service.dropDB();
			//메소드 성공 시 결과 메시지를 변수에 담는다.
			ret = "DB drop 성공";
		}catch(Exception e) {
			e.printStackTrace();
			//예외처리 시 아래 메시지를 변수에 담는다.
			ret = "DB drop 실패" + e;
		}
		// 위 처리에 따라 넣어진 메시지 값을 value로 하고 
		// 'msg'라는 key값을 가진 model에 값을 넣는다.
		model.addAttribute("msg",ret);
		//dropDB.jsp로 이동한다.
		return "dropDB";
	}
	
	//oneviewDB으로 갈 controller 메소드
	//URL에 oneviewDB를 치고, 뒤에 다시 /하고 파라메터 값을 적는다.
	@RequestMapping(value = "/oneviewDB/{studentid}", method = RequestMethod.GET)
	//Pathvariable 어노테이션으로 studentid 값을 studentid라는 이름의 매개변수로 만든다.
	public String oneviewDB(@PathVariable("studentid") int studentid, Model model) {
		//로그 출력하고(로그에 파라미터 값도 포함되도록 함)
		logger.info("oneviewDB.jsp start studentid = " + studentid);
		
		try {
			//위에서 선언한 service의 selectOne()메소드 요청한다.
			//매개변수로 선언한 studentid를 인자로 하여 selectOne()에 넣는다.
			//selectOne메소드를 통해 나온 리턴값을 value로 해서  
			//'list'란 key값으로 model에 담는다.
			model.addAttribute("list",service.selectOne(studentid));
		}catch(Exception e) {
			e.printStackTrace();
		}
		//oneviewDB.jsp로 이동한다.
		return "oneviewDB";
	}	
}

