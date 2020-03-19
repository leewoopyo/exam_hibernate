package com.kopo.exam_hibernate.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kopo.exam_hibernate.dao.ExamRepo;
import com.kopo.exam_hibernate.domain.ExamRIO;
import com.kopo.exam_hibernate.dto.ExamSIO;


//@Service 어노테이션으로 해당 클래스는 service의 역활을 하는 클래스임을 선언한다.
//ExamServiceImpl클래스는 ExamService인터페이스를 상속 받았다.
//때문에 ExamService에 정의된 메소드를 override해서 각 메소드를 재정의 해주어야 한다.
@Service
public class ExamServiceImpl implements ExamService {
	
	//@Autowired 어노테이션으로 레파지토리를 가져와 사용하기 위해
	//스프링 특수 변수로서 레파지토리(ExamRepo)를 선언한다.
	@Autowired
	private ExamRepo repo; 
	
	//테이블을 생성하는 메소드
	@Override
	public void createDB() throws Exception{
		//레파지토리(repo)의 createDB()메소드를 불러와서(DB요청)
		//테이블을 생성한다.
		repo.createDB();
	}
	
	//테이블을 삭제하는 메소드 
	@Override
	public void dropDB() throws Exception{
		//레파지토리(repo)의 dropDB()메소드를 불러와서(DB요청)
		//테이블을 삭제한다.
		repo.dropDB();		
	}
	
	//몇명의 학생들의 데이터를  통시에 넣는 메소드이다.  
	@Override
	public void allsetDB() throws Exception{
		//자기 자신 클래스(this)의 insert 메소드를 실행 한다.
		//insert()메소드에 들어갈 인자는 ExamSIO형태의 데이터이며
		//ExamSIO데이터르 넣을 때 new를 통해 이름,학번,각 성적 데이터를 가진 객체를 만들어서 넣는다.
		
		this.insert(new ExamSIO("학생1",209901,95,100,95));
		this.insert(new ExamSIO("학생2",209902,90,90,100));
		this.insert(new ExamSIO("학생3",209903,85,80,95));
		this.insert(new ExamSIO("학생4",209904,75,100,85));
		this.insert(new ExamSIO("학생5",209905,85,70,75));
		this.insert(new ExamSIO("학생6",209906,95,80,95));
		this.insert(new ExamSIO("학생7",209907,85,100,85));
		this.insert(new ExamSIO("학생8",209908,75,90,65));
		this.insert(new ExamSIO("학생9",209909,85,80,95));
	}
	
	//ExamSIO를 매개변수로 가지고 데이터를 삽입하는 메소드이다.
	@Override
	public void insert(ExamSIO examSIO) throws Exception{
		//레파지토리(repo)의 createOne()메소드를 불러와서(DB요청)
		//ExamRIO형의 데이터를 인자로 넣는다.
		//ExamRIO형의 데이터를 넣을 때 new를 통해 객체를 만들고 
		//넣을 데이터로서 매개변수로 받은 ExamSIO의 데이터를 get메소드를 통해
		//이름 학번 각 점수를 추출하여 데이터를 넣어준다.
		repo.createOne(new ExamRIO(examSIO.getName(),examSIO.getStudentid(),
				examSIO.getKor(),examSIO.getEng(),
				examSIO.getMat()));
	}
	
	//학번(studentid)을 조건으로 해서 데이터 조회
	@Override
	public ExamSIO selectOne(int studentid) throws Exception{
		//ExamRIO형의 변수를 하나 만들고
		ExamRIO exam = null;
		try {
			//레파지토리(repo)의 selectOne()메소드를 불러와서(DB요청)
			//인자로 studentid를 넣어서 해당하는 데이터를 exam변수에 담는다.
			exam = repo.selectOne(studentid);
		}catch(Exception e) {
			e.printStackTrace();
		}
		//담은 데이터를 다시 ExamSIO객체를 만들어서 각 데이터를 넣고  리턴한다.
		return new ExamSIO(exam.getName(), exam.getStudentid(), exam.getKor(), exam.getEng(), exam.getMat());
	}
	
	//전체 데이터 조회
	@Override
	public List<ExamSIO> selectAll() throws Exception{
		
		//List<ExamRIO>형태의 변수를 하나 만든다.
		List<ExamRIO> exams = null;
		try {
			//레파지토리(repo)의 selectAll()메소드를 불러와서(DB요청)
			//리턴된 데이터를 exams에 담는다.
			exams = repo.selectAll();
		}catch(Exception e) {
			e.printStackTrace();
		}
		//List<ExamSIO>형의 변수를 하나 생성하고
		List<ExamSIO> examScores = new ArrayList<ExamSIO>();
		//for문을 써서 list갯수 만큼 반복하면서,
		for(int i=0;i < exams.size();i++) {
			//exams에 담겼던 모든 데이터들을 다시 ExamSIO객체를 생성해서 거기에 담아 examScores리스트에 담는다.
			examScores.add(new ExamSIO(exams.get(i).getName(),
					exams.get(i).getStudentid(),
					exams.get(i).getKor(),
					exams.get(i).getEng(),
					exams.get(i).getMat()
					));
		}
		//그렇게 담겨진 리스트를 리턴한다.
		return examScores;
	}
	
	@Override
	public List<ExamSIO> selectAllByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void update(int studentid, ExamSIO examSIO) throws Exception{
		// TODO Auto-generated method stub
	}

	@Override
	public void update(ExamSIO examSIO) throws Exception{
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(int studentid) throws Exception{
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(ExamSIO examSIO) throws Exception{
		// TODO Auto-generated method stub
	}
}
