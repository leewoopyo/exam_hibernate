package com.kopo.exam_hibernate.dao;

import java.util.List;

import com.kopo.exam_hibernate.domain.ExamRIO;

//인터페이스로 만들어서 메소드의 이름을 정의한다.
public interface ExamRepo {
	
	//데이터의 갯수를 구하는 메소드	
	Long count();
	//학번을 조건으로 특정 데이터 조회
	ExamRIO selectOne(int studentid);
	//전체 데이터 조회
	List<ExamRIO> selectAll();
	//페이지네이션 기능이 있는 전체 데이터 조회
	List<ExamRIO> selectAllByPagination(int page, int itemSizePerPage);
	//데이터 삽입
	void createOne(ExamRIO exam);
	//데이터 수정 
	void updateOne(ExamRIO exam);
	//데이터 삭제 
	void daleteOne(ExamRIO exam);
	//전체 데이터 삭제 
	int deleteAll();
	//테이블 생성 
	void createDB() throws Exception;
	//테이블 삭제
	void dropDB() throws Exception;
}
