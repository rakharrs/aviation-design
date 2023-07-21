package helper;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Misc {
    public static String generateToken() {
        return UUID.randomUUID().toString();
    }

    public static Timestamp toTimestamp(String datetimeLocalString) throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date parsedDate = format.parse(datetimeLocalString);
        Timestamp timestamp = new Timestamp(parsedDate.getTime());
        return timestamp;
    }
}
