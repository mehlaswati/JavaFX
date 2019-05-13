import com.mongodb.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.util.*;
import java.util.regex.Pattern;

public class mongo_api extends Application {
    private static TableView table = new TableView();
    private TableView table_mashup = new TableView();
    public static final ObservableList<ApiClass> data =
            FXCollections.observableArrayList(
            );
    public static final ObservableList<MashupClass> data_mashup =
            FXCollections.observableArrayList(
            );
    final HBox hb = new HBox();

    public static ArrayList<String> listofTableColumns_Seq ;

    public static  ArrayList<ArrayList<String>> listOLists = new ArrayList<ArrayList<String>>();
    public static  ArrayList<ArrayList<String>> listOLists_ofMashups = new ArrayList<ArrayList<String>>();
    public static ArrayList<String> singleList = new ArrayList<String>();




    public static void doQuery_forMashup(String prot_selected,String rati_num_selected,String ratingOPtion,String cate_selected,String tagsSelected,String year_selected,String keyWordSelected){



        DB db = (new MongoClient()).getDB("WebServices");

        DBCollection dbCollection = db.getCollection("mashup");
        DBCursor cursor1;
//        BasicDBObject b0 = new BasicDBObject();
        if(keyWordSelected.length()<=0){
            BasicDBObject andQuery = new BasicDBObject();
            List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
            obj.add(new BasicDBObject((new BasicDBObject("tags", new BasicDBObject("$regex", Pattern.quote(tagsSelected))))));
            obj.add(new BasicDBObject((new BasicDBObject("updated", new BasicDBObject("$regex", Pattern.quote(year_selected))))));
            String op = "$"+ratingOPtion;
            obj.add(new BasicDBObject((new BasicDBObject("rating", new BasicDBObject(op, rati_num_selected)))));
            andQuery.put("$and", obj);

            System.out.println(andQuery.toString());
            cursor1 = dbCollection.find(andQuery);
        }


        else {
            BasicDBObject andQuery = new BasicDBObject();
            List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
            List<BasicDBObject> obj_keyword = new ArrayList<BasicDBObject>();
            obj.add(new BasicDBObject((new BasicDBObject("title", new BasicDBObject("$regex", Pattern.quote(keyWordSelected))))));
            obj.add(new BasicDBObject((new BasicDBObject("summary", new BasicDBObject("$regex", Pattern.quote(keyWordSelected))))));
            obj.add(new BasicDBObject((new BasicDBObject("description", new BasicDBObject("$regex", Pattern.quote(keyWordSelected))))));
            andQuery.put("$or", obj);

            System.out.println(andQuery.toString());
            cursor1 = dbCollection.find(andQuery);

        }

        ArrayList<String> list_of_valueTodisplay = new ArrayList<>();
        list_of_valueTodisplay.add("title");
        list_of_valueTodisplay.add("api_url");
        list_of_valueTodisplay.add("updated");
        list_of_valueTodisplay.add("rating");
//        list_of_valueTodisplay.add("category");
        list_of_valueTodisplay.add("tags");
        list_of_valueTodisplay.add("summary");

        while (cursor1.hasNext()){
            int counter = 0;
//                System.out.println(cursor1.next());
            ArrayList<String> tempList = new ArrayList<String>();
            Map<String, String> numberMapping = new HashMap<>();
            numberMapping = cursor1.next().toMap();
            for (Map.Entry<String, String> entry : numberMapping.entrySet()) {


                String key = entry.getKey();
                Object value = entry.getValue();


                if(list_of_valueTodisplay.contains(key)){
                    System.out.println("key is"+key);
                    System.out.println("value is"+value);
                    if(value == null){
                        tempList.add("null");
                    }else {
                        tempList.add(value.toString());
                    }
//                    counter++;
                }

            }
            listOLists_ofMashups.add(tempList);



        }


        for (ArrayList list : listOLists_ofMashups) {
//            data.add(new ApiClass("1","WS2","2","3"));
            data_mashup.add(new MashupClass(list.get(0).toString(),list.get(1).toString(),list.get(2).toString(),list.get(3).toString(),list.get(4).toString(),list.get(5).toString()));
        }




    }
    public static void doQuery(String prot_selected,String rati_num_selected,String ratingOPtion,String cate_selected,String tagsSelected,String year_selected,String keyWordSelected){


        DB db = (new MongoClient()).getDB("WebServices");

        DBCollection dbCollection = db.getCollection("api");

        DBCursor cursor1;
        if(keyWordSelected.length()<=0){
            BasicDBObject andQuery1 = new BasicDBObject();
            List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
            obj.add(new BasicDBObject((new BasicDBObject("protocols", new BasicDBObject("$regex", Pattern.quote(prot_selected))))));
            obj.add(new BasicDBObject((new BasicDBObject("tags", new BasicDBObject("$regex", Pattern.quote(tagsSelected))))));
            obj.add(new BasicDBObject((new BasicDBObject("updated", new BasicDBObject("$regex", Pattern.quote(year_selected))))));
            String op = "$"+ratingOPtion;
            obj.add(new BasicDBObject((new BasicDBObject("rating", new BasicDBObject(op, rati_num_selected)))));
            obj.add(new BasicDBObject((new BasicDBObject("category", new BasicDBObject("$regex", Pattern.quote(cate_selected))))));
            andQuery1.put("$and", obj);
//        finalQuery.put("$and"

            System.out.println(andQuery1.toString());
            cursor1= dbCollection.find(andQuery1);
        }else {
            BasicDBObject andQuery = new BasicDBObject();
            List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
            List<BasicDBObject> obj_keyword = new ArrayList<BasicDBObject>();
            obj.add(new BasicDBObject((new BasicDBObject("title", new BasicDBObject("$regex", Pattern.quote(keyWordSelected))))));
            obj.add(new BasicDBObject((new BasicDBObject("summary", new BasicDBObject("$regex", Pattern.quote(keyWordSelected))))));
            obj.add(new BasicDBObject((new BasicDBObject("description", new BasicDBObject("$regex", Pattern.quote(keyWordSelected))))));
            andQuery.put("$or", obj);

//        finalQuery.put("$and"

            System.out.println(andQuery.toString());
             cursor1 = dbCollection.find(andQuery);

        }



        ArrayList<String> list_of_valueTodisplay = new ArrayList<>();
        list_of_valueTodisplay.add("title");
        list_of_valueTodisplay.add("protocols");
        list_of_valueTodisplay.add("updated");
        list_of_valueTodisplay.add("rating");
        list_of_valueTodisplay.add("category");
        list_of_valueTodisplay.add("tags");
        list_of_valueTodisplay.add("summary");

//        listofTableColumns_Seq = new ArrayList<>();
        int counter = 0;
        while (cursor1.hasNext()){

//                System.out.println(cursor1.next());
            ArrayList<String> tempList = new ArrayList<String>();
            Map<String, String> numberMapping = new HashMap<>();
            numberMapping = cursor1.next().toMap();

            for (Map.Entry<String, String> entry : numberMapping.entrySet()) {


                String key = entry.getKey();
                Object value = entry.getValue();

                if(list_of_valueTodisplay.contains(key)){


                    if(value == null){
                        tempList.add("null");
                    }else {
                        tempList.add(value.toString());
                    }



                }



            }
            listOLists.add(tempList);



        }


        for (ArrayList list : listOLists) {
//            data.add(new ApiClass("1","WS2","2","3"));
            data.add(new ApiClass(list.get(0).toString(),list.get(1).toString(),list.get(2).toString(),list.get(3).toString()
                    ,list.get(4).toString(),list.get(5).toString(),list.get(6).toString()));
        }


    }





    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new Group());
        stage.setTitle("Table View Sample");
        stage.setWidth(1400);
        stage.setHeight(900);


        table.setEditable(true);
        table_mashup.setEditable(true);


        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "gt",
                        "lt",
                        "eq"
                );

        final ComboBox comboBox = new ComboBox(options);
        comboBox.setPromptText("Select Rating Criteria");


        HBox h_box_forInput = new HBox();
        TextField protocols = new TextField ();
        protocols.setPromptText("Enter Protcol");
        TextField category = new TextField ();
        category.setPromptText("category");
        TextField addRating_api = new TextField ();
        addRating_api.setPromptText("rating");

        TextField tags = new TextField ();
        tags.setPromptText("tags");
//        TextField used_api = new TextField ("API Used for Mashup");

        TextField updates_year = new TextField ();
        updates_year.setPromptText("updated year");
        TextField keyword = new TextField();
        keyword.setPromptText("Enter Keyword");


//        titleCol, summaryCol, ratingCol,year,category_col,prot,tag)

        TableColumn titleCol = new TableColumn("title ");
        titleCol.setMinWidth(100);
        titleCol.setCellValueFactory(
                new PropertyValueFactory<ApiClass, String>("title"));

        TableColumn year = new TableColumn("Date");
        year.setMinWidth(300);
        year.setCellValueFactory(
                new PropertyValueFactory<ApiClass, String>("updated"));

        TableColumn summaryCol = new TableColumn("summary");
        summaryCol.setMinWidth(100);
        summaryCol.setCellValueFactory(
                new PropertyValueFactory<ApiClass, String>("summary"));

        TableColumn ratingCol = new TableColumn("rating");
        ratingCol.setMinWidth(100);
        ratingCol.setCellValueFactory(
                new PropertyValueFactory<ApiClass, String>("rating"));


        TableColumn tag = new TableColumn("tags");
        tag.setMinWidth(100);
        tag.setCellValueFactory(
                new PropertyValueFactory<ApiClass, String>("tags"));

        TableColumn category_col = new TableColumn("category");
        category_col.setMinWidth(100);
        category_col.setCellValueFactory(
                new PropertyValueFactory<ApiClass, String>("category"));

        TableColumn prot = new TableColumn("protocol");
        prot.setMinWidth(100);
        prot.setCellValueFactory(
                new PropertyValueFactory<ApiClass, String>("protocol"));




        TableColumn m_titleCol = new TableColumn("title");
        m_titleCol.setMinWidth(100);
        m_titleCol.setCellValueFactory(
                new PropertyValueFactory<MashupClass, String>("title1"));

        TableColumn m_summaryCol = new TableColumn("summary");
        m_summaryCol.setMinWidth(100);
        m_summaryCol.setCellValueFactory(
                new PropertyValueFactory<MashupClass, String>("summary1"));

        TableColumn m_ratingCol = new TableColumn("rating");
        m_ratingCol.setMinWidth(100);
        m_ratingCol.setCellValueFactory(
                new PropertyValueFactory<MashupClass, String>("rating1"));


        TableColumn m_tagcol = new TableColumn("tags");
        m_tagcol.setMinWidth(100);
        m_tagcol.setCellValueFactory(
                new PropertyValueFactory<MashupClass, String>("tags1"));

        TableColumn m_year = new TableColumn("Date");
        m_year.setMinWidth(40);
        m_year.setCellValueFactory(
                new PropertyValueFactory<MashupClass, String>("year1"));

        TableColumn m_apiUsed = new TableColumn("API Used");
        m_apiUsed.setMinWidth(100);
        m_apiUsed.setCellValueFactory(
                new PropertyValueFactory<MashupClass, String>("apiused"));




        table.getColumns().addAll(titleCol, summaryCol, ratingCol,prot,tag,category_col,year );
        table_mashup.getColumns().addAll(m_titleCol, m_summaryCol, m_ratingCol,m_tagcol,m_year,m_apiUsed);
//        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);

        Button b = new Button("Find");

        Button mashupButton = new Button("MAshup Find");

        mashupButton.setOnAction((event) -> {
            String prot_selected = protocols.getText();
            String rati_num_selected = addRating_api.getText();
            String ratingOPtion = comboBox.getValue().toString();
            String cate_selected = category.getText();
            String tagsSelected = tags.getText();
            String year_selected = updates_year.getText();
            String keywordSelected = keyword.getText();
//            String used_API = used_api.getText();


            doQuery_forMashup(prot_selected,rati_num_selected,ratingOPtion,cate_selected,tagsSelected,year_selected,keywordSelected);

            table_mashup.setItems(data_mashup);

        });

        b.setOnAction((event) -> {
            String prot_selected = protocols.getText();
            String rati_num_selected = addRating_api.getText();
            String ratingOPtion = comboBox.getValue().toString();
            String cate_selected = category.getText();
            String tagsSelected = tags.getText();
            String year_selected = updates_year.getText();
            String keywordSelected = keyword.getText();

//            System.out.println(protocols.getText());
//            System.out.println(comboBox.getValue());
            doQuery(prot_selected,rati_num_selected,ratingOPtion,cate_selected,tagsSelected,year_selected,keywordSelected);

//            maketable(titleCol, summaryCol, ratingCol,prot,tag,category_col,year);
            table.setItems(data);

        });


        Button clearButton = new Button("Clear");

        clearButton.setOnAction((event) -> {
            System.out.println(data);
            table.getItems().clear();
//            table.setItems(data);
            data.clear();
            table_mashup.getItems().clear();
            data_mashup.clear();
            System.out.println(data);


        });


        Label api_label = new Label("API:");
        Label mashup_label = new Label("Mashup");

        HBox hb1 = new HBox();
        hb1.getChildren().addAll(api_label,keyword);

        h_box_forInput.getChildren().addAll(protocols,category,addRating_api,comboBox,tags, updates_year,b,clearButton,mashupButton);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(h_box_forInput,hb1,table,mashup_label,table_mashup);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args)  {

        Application.launch(args);

    }
}




