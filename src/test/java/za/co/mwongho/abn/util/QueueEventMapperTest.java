package za.co.mwongho.abn.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import za.co.mwongho.abn.model.EventType;
import za.co.mwongho.abn.model.QueueEvent;
import za.co.mwongho.abn.model.Student;

@DisplayName("StudentEventMapper Test")
public class QueueEventMapperTest {

  private static QueueEvent buildStudentEvent(
      final EventType eventType, final int id, final String name, final double cgpa) {
    Student student = Student.builder().id(id).name(name).cgpa(cgpa).build();
    return QueueEvent.builder().eventType(eventType).student(student).build();
  }

  @Test
  public void success_mapEnter() {
    // GIVEN
    String stringEvent = "ENTER John 3.75 50";
    // WHEN
    QueueEvent actual = StudentEventParser.parse(stringEvent);
    // THEN
    QueueEvent expected = buildStudentEvent(EventType.ENTER, 50, "John", 3.75);
    Assertions.assertThat(actual).isNotNull().isEqualToComparingFieldByFieldRecursively(expected);
  }

  @Test
  public void success_mapServed() {
    // GIVEN
    String stringEvent = "SERVED";
    // WHEN
    QueueEvent expected = StudentEventParser.parse(stringEvent);
    // THEN
    Assertions.assertThat(expected).isNotNull().isEqualToComparingFieldByFieldRecursively(expected);
  }

  @Test
  public void fail_mapUnknown() {
    // GIVEN
    String stringEvent = "UNKNOWN";
    // WHEN
    Throwable thrown = Assertions.catchThrowable(() -> StudentEventParser.parse(stringEvent));
    // THEN
    Assertions.assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
  }
}
