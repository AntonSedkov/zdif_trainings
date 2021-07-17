package anton.sample.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

/**
 * User: Sedkov Anton
 * Date: 22.06.2021
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    @Override
    public LocalDate unmarshal(String string) {
        return LocalDate.parse(string);
    }

    @Override
    public String marshal(LocalDate localDate) {
        return localDate.toString();
    }
}
