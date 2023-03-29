package main.domain.classes.enumerations;

public enum colors {
    NULL("null",0),
    VERMELL("vermell",1),      //Vermell
    VERD("verd",2),      //Verd
    BLAU("blau",3),       //Blau
    GROC("groc",4),      //groc
    MAGENTA("magenta",5),      //magenta
    CIAN("cian",6),      //cian
    MARRO("marro",7),      //marro
    GRIS("gris",8),  //gris
    BLANC("blanc",9),      //blanc
    NEGRE("negre",10);     //negre


    private String nom_color;
    private int id_color;

    private colors(String nom_color, int id_color){
        this.nom_color = nom_color;
        this.id_color = id_color;
    }

    public String get_nom_color(){
        return nom_color;
    }

    public int get_id_color() {
        return id_color;
    }
}