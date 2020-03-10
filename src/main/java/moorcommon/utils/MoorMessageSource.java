package moorcommon.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MoorMessageSource {

    @Autowired
    private MessageSource messageSource;

    public List<String> convert(List<String> messages) {
        List<String> convertedMessages = new ArrayList<>();
        if (messages == null)
            return null;
        for (String message : messages) {
            convertedMessages.add(messageSource.getMessage(message, null, Utils.getLocale()));
        }
        return convertedMessages;
    }
}
