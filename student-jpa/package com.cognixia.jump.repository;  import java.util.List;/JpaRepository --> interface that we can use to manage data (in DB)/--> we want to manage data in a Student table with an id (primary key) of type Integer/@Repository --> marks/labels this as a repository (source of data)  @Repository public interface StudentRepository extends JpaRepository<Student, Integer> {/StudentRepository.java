package com.cognixia.jump.repository;

import java.util.List;

// JpaRepository --> interface that we can use to manage data (in DB)
//				 --> we want to manage data in a Student table with an id (primary key) of type Integer

// @Repository --> marks/labels this as a repository (source of data)

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> { 
	
	// select * from student
	public List<Student> findByMajors(String major); 
	
	@Query(" select s from Student s where s.major = ?1") 
	public List<Student> studentsInMajor(String major); 
	@Transactional 
	@Modifying
	@Query("UPDATE Student s SET s.major= :major WHERE a.id = :id ") 
	public int UpdateMajor(@Param(value="id") int id, @Param(value="major") String major);
}
