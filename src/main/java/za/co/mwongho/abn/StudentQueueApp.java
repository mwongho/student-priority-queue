package za.co.mwongho.abn;

import lombok.extern.slf4j.Slf4j;
import za.co.mwongho.abn.model.Student;
import za.co.mwongho.abn.service.PriorityStudentQueue;
import za.co.mwongho.abn.service.StudentQueue;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class StudentQueueApp {

  private StudentQueue studentQueue;

  public StudentQueueApp() {
    this.studentQueue = new PriorityStudentQueue(PriorityStudentQueue.DEFAULT_COMPARATOR);
  }

  public static void main(String[] args) {
    StudentQueueApp app = new StudentQueueApp();
    List<String> events = app.getEvents();
    List<Student> remainingStudents = app.getStudents(events);
    if (remainingStudents.isEmpty()) {
      log.info("EMPTY");
      return;
    }
    remainingStudents.stream().forEach(student -> log.info(student.getName()));
  }

  private List<String> getEvents() {
    List<String> events = new ArrayList<>();
    events.add("12");
    events.add("ENTER John 3.75 50");
    events.add("ENTER Mark 3.8 24");
    events.add("ENTER Shafaet 3.7 35");
    events.add("SERVED");
    events.add("SERVED");
    events.add("ENTER Samiha 3.85 36");
    events.add("SERVED");
    events.add("ENTER Ashley 3.9 42");
    events.add("ENTER Maria 3.6 46");
    events.add("ENTER Anik 3.95 49");
    events.add("ENTER Dan 3.95 50");
    events.add("SERVED");
    return events;
  }

  private List<Student> getStudents(final List<String> events) {
    return this.studentQueue.getStudents(events);
  }
}
