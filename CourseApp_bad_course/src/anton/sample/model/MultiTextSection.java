package anton.sample.model;

import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;

/**
 * User: Sedkov Anton
 * Date: 07.06.2021
 */
public class MultiTextSection extends Section implements Serializable {
    static final long serialVersionUID = 1L;

    private List<String> values;

    public MultiTextSection() {
    }

    public MultiTextSection(List<String> values) {
        this.values = values;
    }

    public List<String> getValues() {
        return values;
    }

    public void addValue(String value) {
        values.add(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MultiTextSection that = (MultiTextSection) o;

        return values != null ? values.equals(that.values) : that.values == null;
    }

    @Override
    public int hashCode() {
        return values != null ? values.hashCode() : 0;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MultiTextSection.class.getSimpleName() + "[", "]")
                .add("values=" + values)
                .toString();
    }
}
