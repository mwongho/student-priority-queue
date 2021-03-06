package za.co.mwongho.abn.service;

import lombok.NonNull;
import org.apache.commons.lang3.stream.Streams;
import za.co.mwongho.abn.model.EventType;
import za.co.mwongho.abn.model.QueueEvent;
import za.co.mwongho.abn.model.Student;
import za.co.mwongho.abn.util.StudentEventParser;
import za.co.mwongho.abn.util.Validator;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * The queue serves students based on the priority criteria. 1. The student having the highest
 * Cumulative Grade Point Average (CGPA) is served first. 2. Any students having the same CGPA will
 * be served by name in ascending case-sensitive alphabetical order. 3. Any students having the same
 * CGPA and name will be served in ascending order of the id.
 */
public class PriorityStudentQueue implements StudentQueue {

  // Order by cgpa dsc, name asc, id asc
  public static final Comparator<Student> DEFAULT_COMPARATOR =
      Comparator.comparing(Student::getCgpa)
          .reversed()
          .thenComparing(Student::getName)
          .thenComparing(Student::getId);

  private final Comparator<Student> comparator;
  private PriorityQueue<Student> priorityQueue;

  public PriorityStudentQueue(@NonNull final Comparator<Student> comparator) {
    this.comparator = comparator;
  }

  /**
   * Process all the given events and return all the students yet to be served in the priority
   * order.
   *
   * @param events
   * @return Students yet to be served in the priority order
   */
  @Override
  public List<Student> getStudents(@NonNull final List<String> events) {

    // Validate events not null or empty
    Validator.validateEventList(events);

    String header = events.remove(0);
    // Validate header not null and int
    Validator.validateHeader(header);

    int eventCount = Integer.parseInt(header);

    this.priorityQueue = new PriorityQueue<>(eventCount, this.comparator);

    List<QueueEvent> queueEvents = parseEvents(events);

    if (queueEvents.isEmpty()) {
      return Collections.EMPTY_LIST;
    }

    for (QueueEvent queueEvent : queueEvents) {
      if (queueEvent.getEventType().equals(EventType.ENTER)) {
        this.priorityQueue.offer(queueEvent.getStudent());
      }
      if (queueEvent.getEventType().equals(EventType.SERVED)) {
        this.priorityQueue.poll();
      }
    }

    // Sort queue according to comparator before returning list
    return this.priorityQueue.stream()
        .sorted(this.priorityQueue.comparator())
        .collect(Collectors.toList());
  }

  /**
   * Parse String to StudentEvent
   *
   * @param events
   * @return
   */
  private List<QueueEvent> parseEvents(final List<String> events) {
    return Streams.stream(events).map(StudentEventParser::parse).collect(Collectors.toList());
  }
}
