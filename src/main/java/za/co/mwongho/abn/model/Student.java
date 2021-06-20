package za.co.mwongho.abn.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class Student {
  private int id;
  private String name;
  private double cgpa;

  public Student(@NonNull final int id, @NonNull final String name, @NonNull final double cgpa) {
    this.id = id;
    this.name = name;
    this.cgpa = cgpa;
  }
}
