package za.co.mwongho.abn.util;

import lombok.NonNull;
import za.co.mwongho.abn.model.EventType;
import za.co.mwongho.abn.model.QueueEvent;
import za.co.mwongho.abn.model.Student;

public class StudentEventParser {

  public static final String DELIMETER = " ";

  private StudentEventParser() {}

  /**
   * Parse String to QueueEvent String format
   *
   * @param eventString
   * @return
   */
  public static QueueEvent parse(@NonNull final String eventString) {

    final String[] split = eventString.split(DELIMETER);

    // Validate split sizes
    if (split.length != 1 && split.length != 4) {
      throw new IllegalArgumentException("Invalid Event entry");
    }

    final EventType eventType = EventType.valueOf(split[0]);

    QueueEvent queueEvent = QueueEvent.builder().eventType(eventType).build();

    if (eventType.equals(EventType.ENTER)) {
      final Student student =
          new Student(Integer.parseInt(split[3]), split[1], Double.parseDouble(split[2]));
      queueEvent.setStudent(student);
    }

    return queueEvent;
  }
}
