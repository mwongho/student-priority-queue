package za.co.mwongho.abn.util;

import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class Validator {

  private Validator() {}

  /**
   * Validate event list not empty
   *
   * @param events
   */
  public static void validateEventList(@NonNull List<String> events) {
    if (events.isEmpty()) {
      throw new IllegalArgumentException("Events list empty");
    }
  }

  /**
   * Validate header event line not blank and numeric
   *
   * @param headerEvent
   */
  public static void validateHeader(@NonNull String headerEvent) {
    boolean b = StringUtils.isNumeric(headerEvent);
    if (StringUtils.isBlank(headerEvent) || !StringUtils.isNumeric(headerEvent)) {
      throw new IllegalArgumentException("Invalid header");
    }
  }
}
