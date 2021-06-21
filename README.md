## java.util.PriorityQueue

### Senario

There are a number of students in a school who wait to be served. The queue serves the students based on priority
criteria.

Criteria:

1. The student having the highest Cumulative Grade Point Average (CGPA) is served first.
2. Any students having the same CGPA will be served by name in ascending case-sensitive alphabetical order.
3. Any students having the same CGPA and name will be served in ascending order of the id.

___

### Time complexity

In computer science, the time complexity is the computational complexity that describes the amount of computer time it
takes to run an algorithm.
https://en.wikipedia.org/wiki/Time_complexity

Logarithmic time - O(log n)
<br>
Linear time - O(n)
<br>
Constant time - O(1)

#### List

- get()  O(1)
- add()  O(n)

#### ForEach

- O(n)

#### za.co.mwongho.abn.util.StudentEventParser.parse

- O(1)

  public static QueueEvent parse(@NonNull final String eventString) { ... }

#### za.co.mwongho.abn.service.PriorityStudentQueue.parseEvents

- O(n)

#### java.util.stream.Stream<T>.sorted()

O(log(n))

    return this.priorityQueue.stream()
        .sorted(this.priorityQueue.comparator())
        .collect(Collectors.toList());

#### PriorityQueue

- O(log(n)) time for the enqueing and dequeing methods (offer, poll, remove() and add)


