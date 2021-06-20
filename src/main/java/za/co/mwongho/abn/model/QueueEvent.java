package za.co.mwongho.abn.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class QueueEvent {

  private @NonNull EventType eventType;
  private Student student;
}
