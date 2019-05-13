import javafx.beans.property.SimpleStringProperty;

public final class ApiClass {

    private final SimpleStringProperty title;
    private final SimpleStringProperty summary;
    private final SimpleStringProperty rating;
    private final SimpleStringProperty protocol;
    private final SimpleStringProperty tags;
    private final SimpleStringProperty category;
    private final SimpleStringProperty updated;




    public ApiClass(String title, String summary, String rating, String tags, String category, String protocol, String year) {

        this.title = new SimpleStringProperty(title);
        this.summary = new SimpleStringProperty(summary);
        this.rating = new SimpleStringProperty(rating);



        this.tags = new SimpleStringProperty(tags);
        this.category = new SimpleStringProperty(category);
        this.protocol = new SimpleStringProperty(protocol);
        this.updated = new SimpleStringProperty(year);
    }
    public String getUpdated() {
        return updated.get();
    }
    public void setUpdated(String id2) {
        updated.set(id2);
    }

    public String getTags() {
        return tags.get();
    }
    public void setTags(String id2) {
        tags.set(id2);
    }

    public String getCategory() {
        return category.get();
    }
    public void setCategory(String id2) {
        category.set(id2);
    }

    public String getProtocol() {
        return protocol.get();
    }
    public void setProtocol(String id2) {
        protocol.set(id2);
    }


    public String getTitle() {
        return title.get();
    }
    public void setTitle(String title1) {
        title.set(title1);
    }

    public String getSummary() {
        return summary.get();
    }
    public void setSummary(String summary1) {
        summary.set(summary1);
    }

    public String getRating() {
        return rating.get();
    }
    public void setRating(String rating1) {
        rating.set(rating1);
    }





}