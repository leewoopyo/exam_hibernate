package com.kopo.exam_hibernate.dao;

import java.sql.Statement;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kopo.exam_hibernate.domain.ExamRIO;

//@Transactional 어노테이션으로  여러쿼리문이 있을 때(트랜젝션으로 묶임)
//트랜젝션으로 묶인 쿼리중 하나라도 실패하면 rollback하고 모두 성공했을 때만 commit을 시켜준다. 
//@Repository 어노테이션을 통해 해당 클래스가 Repository임을 명시한다. 
//ExamRepo를 구현(implements)했기 때문에 메소드를 @override해서 재정의 한다.
@Transactional
@Repository
public class ExamRepoImpl implements ExamRepo{
	
	//hibernate는 SessionFactory클래스를 @Autowired하여 선언한다.
	//SessionFactory를 통해서 hibernate가 불러와진다. 
	@Autowired
	private SessionFactory sessionFactory;
	
	//로그 객체 
	private static final Logger logger = LoggerFactory.getLogger(ExamRepoImpl.class);

	//SessionFactory로부터 통신 연결된 식별자 값이 세션인데
	//이 세션 값을 갖기 위해서 getsession메소드를 만들었다.
	//이 세션값을 가지고 hibernate작업을 한다. 
	private Session getSession() {
		logger.info("getSession().start");
		Session ss = null;
		try {
			ss = sessionFactory.getCurrentSession();
			//System.out.println("111");
		}catch(org.hibernate.HibernateException he) {
			ss = sessionFactory.openSession();
			//System.out.println("222");
		}
		return ss;
	}
	
	//데이터의 갯수가 몇개인지 구하는 메소드 
	@Override
	public Long count() {
		//로그 표시
		logger.info("count().start");
		//hql문을 작성한다. DB의 테이블명(examtable)이 아닌 ExamRIO클래스명으로
		//테이블을 읽을 수 있다.(hibernate의 특징)
		String hql = "select count(*) from ExamRIO";
		//세션값.createQuery를 통해 hql문을 넣어서 쿼리문을 생성한다.
		Query query = getSession().createQuery(hql);
		//query.uniqueResult() 를 통해 결과값을 나오게 한다. 
		Long totalCount = (Long) query.uniqueResult();
		//결과값을 리턴한다.
		return totalCount;
	}

	//학번을 조건으로 특정 데이터 조회하는 메소드 
	@Override
	public ExamRIO selectOne(int studentid) {
//		ExamRIO exam = (ExamRIO)getSession().get(ExamRIO.class, id); 
		//hql문은 조회 시 "select *" 을 생략하는 것이 가능한 듯 하다.
		//where조건으로 ExamRIO중에 studentid를 조건으로 데이터를 찾는다.
		String hql = "From ExamRIO e where e.studentid = " + studentid;
		//세션값.createQuery에 hql문을 담아 쿼리를 생성하고 query에 담는다.
		Query query = getSession().createQuery(hql);
		//그리고 query.uniqueResult()를 통해 그 값을 가져와 리턴 할 수 있다.
		return (ExamRIO) query.uniqueResult();
	}
	
	//전체 데이터 조회
	//@SuppressWarnings는 컴파일러가 정적분석을 진행할 때 오류가 아니라고 마킹해주는 역할을 하고 있다.
	//컴파일러가 일반적으로 경고하는 내용 중"이건 하지마"하고 제외시킬 때 사용한다.
	//unchecked는 검증되지 않은 연산자 관련 경고를 억제 하는 역활이다.
	@SuppressWarnings("unchecked")
	@Override
	public List<ExamRIO> selectAll() {
		//hql문은 조회 시 "select *" 을 생략하는 것이 가능한 듯 하다.
		//전체 데이터를 출력한다. 
		String hql = "from ExamRIO";
		//세션값.createQuery에 hql문을 담아 쿼리를 생성하고 query에 담는다.
		Query query = getSession().createQuery(hql);
		//query.list로 query실행 결과를 list로 리턴할 수 있다. 
		return query.list();	
	}

	//전체 데이터 조회(페이지네이션 기능)
	//@SuppressWarnings는 컴파일러가 정적분석을 진행할 때 오류가 아니라고 마킹해주는 역할을 하고 있다.
	//컴파일러가 일반적으로 경고하는 내용 중"이건 하지마"하고 제외시킬 때 사용한다.
	//unchecked는 검증되지 않은 연산자 관련 경고를 억제 하는 역활이다.
	//page : 몇번째 페이지 , itemSizePerPage : 페이지의 크기
	@SuppressWarnings("unchecked")
	@Override
	public List<ExamRIO> selectAllByPagination(int page, int itemSizePerPage) {
		//studentid 기준으로 정렬하면서 전체 출력하는 hql문을 만든다.
		String hql = "from ExamRIO order by studentid";
		//세션값.createQuery에 hql문을 담아 쿼리를 생성하고 query에 담는다.
		Query query = getSession().createQuery(hql);
		//아래 두줄은 페이지네이션 하는데 필요한 메소드이다.
		query.setFirstResult((page-1) * itemSizePerPage);
		query.setMaxResults(itemSizePerPage);
		//query.list로 query실행 결과를 list로 리턴할 수 있다. 
		return query.list();
	}
	
	//데이터의 삽입
	@Override
	public void createOne(ExamRIO exam) {
		//세션값.save()에 매개변수인 exam을 인자로 넣으면 자동으로 insert가 된다. 
		getSession().save(exam);
		//getSession().flush();
	}
	
	//데이터의 수정
	@Override
	public void updateOne(ExamRIO exam) {
		//세션값.saveOrUpdate()에 매개변수인 exam을 인자로 넣으면 자동으로 update가 된다.
		getSession().saveOrUpdate(exam);
	}

	//데이터의 삭제
	@Override
	public void daleteOne(ExamRIO exam) {
		//세션값.delete()에 매개변수인 exam을 인자로 넣으면 자동으로 delete가 된다.
		getSession().delete(exam);
	}

	//전체 데이터의 삭제
	@Override
	public int deleteAll() {
		// 해당 테이블의 전체 데이터를 삭제하는 hql문 작성
		String hql = "delete from ExamRIO";
		//세션값.createQuery()에 hql을 넣어 쿼리 생성 
		Query query = getSession().createQuery(hql);
		//query.executeUpdate() 쿼리를 실행하고 결과값을 리턴한다.
		return query.executeUpdate();
	}
	
	//테이블 생성의 경우(createDB) DDL이기 때문에 hibernate에서는 사용되지 않는다.
	//테이블 생성
	@Override
	public void createDB() throws Exception {
		//statement 변수 생성
		Statement stmt;
		//try {
			//getSession()메소드를 통해 나온 세션값을 SessionImpl로 형변환 해서
			//세션값.connection().createStatement() 메소드를 통해 나온 값을
			//stmt에 넣는다. 
			//세션값을 import시켜서 jdbc처리 한것(DDL문장 처리)
			stmt = ((SessionImpl)getSession()).connection().createStatement();
			//stmt.execute()메소드로 테이블 생성을 한다.
			//이름, 학번, 각 성적 필드를 만든다.
			stmt.execute("create table examtable(" +
					"name varchar(20)," +
					"studentid int not null primary key," +
					"kor int," + "eng int," +
					"mat int)" +
					"DEFAULT CHARSET=utf8;");
			//Statement 객체를 닫는다.
			stmt.close();
		//}catch(Exception e) {
			//e.printStackTrace();
		//}
	}

	//테이블 삭제
	@Override
	public void dropDB() throws Exception {
		//statement 변수 생성
		Statement stmt;
		//try {
			//getSession()메소드를 통해 나온 세션값을 SessionImpl로 형변환 해서
			//세션값.connection().createStatement() 메소드를 통해 나온 값을
			//stmt에 넣는다. 
			//세션값을 import시켜서 jdbc처리 한것(DDL문장 처리)
			stmt = ((SessionImpl)getSession()).connection().createStatement();
			//stmt.execute()메소드로 테이블 삭제를 한다.
			stmt.execute("drop table examtable;");
			//처리가 끝난 Statement객체는 close()메소드로 닫는다.
			stmt.close();
		//}catch(Exception e) {
			//e.printStackTrace();
		//}
	}
}
