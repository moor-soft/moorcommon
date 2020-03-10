package moorcommon.utils;

import lombok.Data;

import java.util.List;

@Data
public class EmailSMSData {

    private String nickname;
    private String subject;
    private String body;
    private List<String> to;
    private Long emailId;
    private Long smsId;

}