package model;

public class TermData {
    private int id;
    private String term;
    private String description;
    public TermData(){
    }
    public TermData(int id,String term,String description){
        this.id = id;
        this.term = term;
        this.description = description;
    }
    public TermData(String term,String description){
        this.id = id;
        this.term = term;
        this.description = description;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getTerm() {return term;}
    public void setTerm(String term) {this.term = term;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
}
