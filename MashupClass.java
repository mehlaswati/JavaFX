import javafx.beans.property.SimpleStringProperty;

public  class MashupClass {
//    private final SimpleStringProperty id1;
    private final SimpleStringProperty title1;
    private final SimpleStringProperty summary1;
    private final SimpleStringProperty rating1;
    private final SimpleStringProperty tags1;
    private final SimpleStringProperty apiused;
    private final SimpleStringProperty year1;



    public MashupClass(String title1,String summary1, String rating1, String tags1, String apiused, String year1) {

        this.title1 = new SimpleStringProperty(title1);
        this.summary1 = new SimpleStringProperty(summary1);
        this.rating1 = new SimpleStringProperty(rating1);



        this.tags1 = new SimpleStringProperty(tags1);
        this.apiused = new SimpleStringProperty(apiused);
        this.year1 = new SimpleStringProperty(year1);
//        this.updated = new SimpleStringProperty(year);

//

    }




    public String getTitle1() {
        return title1.get();
    }
    public void setTitle1(String t1) {
        title1.set(t1);
    }

    public String getSummary1() {
        return summary1.get();
    }
    public void setSummary1(String s1) {
        summary1.set(s1);
    }

    public String getRating1() {
        return rating1.get();
    }
    public void setRating1(String r1) {
        rating1.set(r1);
    }

    public String getTags1() {
        return tags1.get();
    }
    public void setTags1(String r1) {
        tags1.set(r1);
    }

    public String getYear1() {
        return year1.get();
    }
    public void setYear1(String r1) {
        year1.set(r1);
    }

    public String getApiused() {
        return apiused.get();
    }
    public void setApiused(String r1) {
        apiused.set(r1);
    }


}