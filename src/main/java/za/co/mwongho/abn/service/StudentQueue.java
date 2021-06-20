package za.co.mwongho.abn.service;

import za.co.mwongho.abn.model.Student;

import java.util.List;

public interface StudentQueue {

  List<Student> getStudents(final List<String> events);
}
