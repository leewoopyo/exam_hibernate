package com.kopo.exam_hibernate.service;

import java.util.List;

import com.kopo.exam_hibernate.dto.ExamSIO;



public interface ExamService {
	
	public void createDB() throws Exception;	//테이블 생성
	public void dropDB() throws Exception;		//테이블 삭제
	public void allsetDB() throws Exception;	//학생 데이터 삽입

	//건내준 인자를 기반으로 데이터 삽입
	public void insert (ExamSIO examSIO) throws Exception;	
	
	//해당 학번에 맞는 데이터를 하나 출력
	public ExamSIO selectOne(int studentid) throws Exception;
	//데이터 전체 출력
	public List<ExamSIO> selectAll() throws Exception; 	
	//해당 이름을 가진 데이터 전체 출력
	public List<ExamSIO> selectAllByName(String name) throws Exception;
	
	//해당 학번을 가진 데이터를 수정 
	public void update (int studentid, ExamSIO examSIO) throws Exception;
	//건내준 인자를 기반으로 데이터 수정
	public void update (ExamSIO examSIO) throws Exception;
	
	//학번 기반 데이터 삭제 
	public void delete(int studentid) throws Exception;
	//건내준 인자를 기반으로 데이터 삭제
	public void delete(ExamSIO examSIO) throws Exception;
}
