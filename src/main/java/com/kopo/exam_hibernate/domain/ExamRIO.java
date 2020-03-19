package com.kopo.exam_hibernate.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//해당 클래스가 DB table과 매핑이 되는 클래스이다.
//@Entity 어노테이션을 통해서  hiberante에 해당클래스가 테이블 정보임을 알린다.
//그리고 @Table(name="examtable") 을 써줌으로서
//해당 클래스는 examtable테이블과 연결이 된다는 것을 표시한다.
@Entity
@Table(name="examtable")
public class ExamRIO {
	
	//@Id는 키 필드라는 정보이다. 즉, table의 studentid콜론에 PK설정을 했으면
	//여기에 @Id를 적어준다.
	//그리고 	@Column(name="studentid")을 사용해서 해당 데이터가 
	//DB 테이블에서 studentid필드에 들어가는 데이터라는 것을 명시한다. 
	//(@GeneratedValue를 사용하면 auto_increment기능을 붙일 수 있다.)
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="studentid")
	private int studentid;	//학번
	
	//@Column(name="name")을 사용해서 해당 데이터가 
	//DB 테이블에서 name필드에 들어가는 데이터라는 것을 명시한다. 
	@Column(name="name")
	private String name;	//이름
	
	//@Column(name="kor")을 사용해서 해당 데이터가 
	//DB 테이블에서 kor필드에 들어가는 데이터라는 것을 명시한다. 
	@Column(name="kor")
	private int kor;	//국어점수 
	
	//@Column(name="eng")을 사용해서 해당 데이터가 
	//DB 테이블에서 eng필드에 들어가는 데이터라는 것을 명시한다. 
	@Column(name="eng")
	private int eng;	//영서점수
	
	//@Column(name="mat")을 사용해서 해당 데이터가 
	//DB 테이블에서 mat필드에 들어가는 데이터라는 것을 명시한다. 
	@Column(name="mat")
	private int mat;	//수학점수
	
	//constructor 선언 빈 생성자와 데이터가 같이 들어있는 생성자를 같이 만들어 준다.
	public ExamRIO() {
		super();
	}
	public ExamRIO(String name, int studentid, int kor, int eng, int mat) {
		super();
		this.name = name;
		this.studentid = studentid;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
	}
	
	//getter, setter 메소드 생성 : 데이터를 불러오고 적용할 때 사용한다. 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStudentid() {
		return studentid;
	}
	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMat() {
		return mat;
	}
	public void setMat(int mat) {
		this.mat = mat;
	}
	
	
	
	
	

}
