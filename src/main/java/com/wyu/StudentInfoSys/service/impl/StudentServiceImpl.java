package com.wyu.StudentInfoSys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyu.StudentInfoSys.entity.Infor;
import com.wyu.StudentInfoSys.entity.Student;
import com.wyu.StudentInfoSys.mapper.StudentMapper;
import com.wyu.StudentInfoSys.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentMapper studentMapper;
	
	@Override
	/*查找某些学生
	 * param Student*/
	public List<Student> getStudent(Student student) {
		// TODO Auto-generated method stub
		List<Student> students = new ArrayList<Student>();
	    students = studentMapper.getStudent(student);
	    long id= 1;
	    for(Student student2:students) {
	    	student2.setId(id);
	    	id++;
	    }
		return students;
	}

	@Override
	/*查找所有学生*/
	public List<Student> selectStudents() {
//		return null;
		// TODO Auto-generated method stub
		List<Student> students = new ArrayList<Student>();
		students = studentMapper.selectStudents("DESC");
		return students;
	}

	@Override
	/*增加学生
	 * param Student*/
	public boolean addStudent(Student student) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			studentMapper.saveStudent(student);
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("错误");
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	/*删除学生
	 * param 学号*/
	public boolean deleteStudent(String studentNumber) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			studentMapper.deleteStudent(studentNumber);
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("错误");
			e.printStackTrace();
		}
		return flag;
	}

	/*修改学生信息
	 * param Student*/
	@Override
	public boolean updateStudent(Student student) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			studentMapper.updateStudent(student);
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}

	/*分页查询
	 * param1 页数
	 * param2 页大小*/
	@Override
	public PageInfo<Student> getStudentPage(int pageNum, int pageSize,String way) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum,pageSize,true);
		List<Student> students = studentMapper.selectStudents(way);
		PageInfo<Student> pageInfo = new PageInfo<Student>(students);

		long len = pageInfo.getTotal();
		if(way.equals("")||way == null) {
			long id = (pageNum - 1) * pageSize + 1;
			for(Student student:students) {
				student.setId(id++);
			}
		}else {
			long id = len-(pageNum - 1) * pageSize;
			for(Student student:students) {
				student.setId(id--);
			}
		}
		System.out.println(pageInfo.getList());
		return pageInfo;
	}

	/*统计学生信息*/
	@Override
	public List<Infor> allInfors() {
		// TODO Auto-generated method stub
		Infor infor = new Infor();
		infor.setColumnName("-1");
		infor.setNum(-1);
		List<Infor> infors = new ArrayList<Infor>();
		infors.addAll(studentMapper.departInfors());
		infors.add(infor);
		infors.addAll(studentMapper.classInfors());
		infors.addAll(studentMapper.sexInfors());
		infors.addAll(studentMapper.stuInfors());
		return infors;
	}

	/*选出系部种类*/
	@Override
	public List<Infor> departInfors() {
		// TODO Auto-generated method stub
		return studentMapper.departInfors();
	}
	
	

	
	
	

}
