package anton.sample.model;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * User: Sedkov Anton
 * Date: 07.06.2021
 */
public class TextWithTitleSection extends Section implements Serializable {
    static final long serialVersionUID = 1L;

    private String title;
    private String comment;

    public TextWithTitleSection() {
    }

    public TextWithTitleSection(String title, String comment) {
        this.title = title;
        this.comment = comment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextWithTitleSection that = (TextWithTitleSection) o;

        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        return comment != null ? comment.equals(that.comment) : that.comment == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TextWithTitleSection.class.getSimpleName() + "[", "]")
                .add("title='" + title + "'")
                .add("comment='" + comment + "'")
                .toString();
    }
}
