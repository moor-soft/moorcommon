package moorcommon.utils;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationResult {

    private List<String> messages = new ArrayList<>();
    private boolean isSuccessful = false;
}
