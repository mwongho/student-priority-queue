package za.co.mwongho.abn.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import za.co.mwongho.abn.model.Student;

import java.util.ArrayList;
import java.util.List;

@DisplayName("PriorityStudentQueue Test")
public class PriorityStudentQueueTest {

  StudentQueue studentQueue;

  @BeforeEach
  public void setup() {
    studentQueue = new PriorityStudentQueue(PriorityStudentQueue.DEFAULT_COMPARATOR);
  }

  @Test
  public void success() {
    // GIVEN
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
    // WHEN
    List<Student> actualList = this.studentQueue.getStudents(events);
    // THEN
    Assertions.assertThat(actualList)
        .isNotNull()
        .isNotEmpty()
        .hasSize(4)
        .extracting(Student::getName)
        .containsExactly("Dan", "Ashley", "Shafaet", "Maria");
  }

  @Test
  public void success_orderByCgpa() {
    // GIVEN
    List<String> events = new ArrayList<>();
    events.add("2");
    events.add("ENTER John 3.75 50");
    events.add("ENTER Mark 3.8 24");
    // WHEN
    List<Student> actualList = this.studentQueue.getStudents(events);
    // THEN
    Assertions.assertThat(actualList)
        .isNotNull()
        .isNotEmpty()
        .hasSize(2)
        .extracting(Student::getCgpa)
        .containsExactly(3.8, 3.75);
  }

  @Test
  public void success_orderByName() {
    // GIVEN
    List<String> events = new ArrayList<>();
    events.add("2");
    events.add("ENTER John 3.8 50");
    events.add("ENTER Adam 3.8 24");
    // WHEN
    List<Student> actualList = this.studentQueue.getStudents(events);
    // THEN
    Assertions.assertThat(actualList)
        .isNotNull()
        .isNotEmpty()
        .hasSize(2)
        .extracting(Student::getName)
        .containsExactly("Adam", "John");
  }

  @Test
  public void success_orderById() {
    // GIVEN
    List<String> events = new ArrayList<>();
    events.add("2");
    events.add("ENTER John 3.8 50");
    events.add("ENTER John 3.8 49");
    // WHEN
    List<Student> actualList = this.studentQueue.getStudents(events);
    // THEN
    Assertions.assertThat(actualList)
        .isNotNull()
        .isNotEmpty()
        .hasSize(2)
        .extracting(Student::getId)
        .containsExactly(49, 50);
  }

  @Test
  public void fail_emptyEvents() {
    // GIVEN
    List<String> events = new ArrayList<>();
    // WHEN
    Throwable thrown =
        Assertions.catchThrowable(
            () -> {
              this.studentQueue.getStudents(events);
            });
    // THEN
    Assertions.assertThat(thrown)
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Events list empty");
  }

  @Test
  public void fail_nullEvents() {
    // GIVEN
    List<String> events = null;
    // WHEN
    Throwable thrown =
        Assertions.catchThrowable(
            () -> {
              this.studentQueue.getStudents(events);
            });
    // THEN
    Assertions.assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void fail_emptyHeader() {
    // GIVEN
    List<String> events = new ArrayList<>();
    events.add("");
    // WHEN
    Throwable thrown =
        Assertions.catchThrowable(
            () -> {
              this.studentQueue.getStudents(events);
            });
    // THEN
    Assertions.assertThat(thrown)
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Invalid header");
  }

  @Test
  public void fail_alphaHeader() {
    // GIVEN
    List<String> events = new ArrayList<>();
    events.add("a");
    // WHEN
    Throwable thrown =
        Assertions.catchThrowable(
            () -> {
              this.studentQueue.getStudents(events);
            });
    // THEN
    Assertions.assertThat(thrown)
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Invalid header");
  }
}
