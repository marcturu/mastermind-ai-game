package main.domain.classes;

public enum colors {
    VERMELL("vermell",1),      //Vermell
    VERD("verd",2),      //Verd
    BLAU("blau",3),       //Blau
    GROC("groc",4),      //groc
    MAGENTA("magenta",5),      //magenta
    CIAN("cian",6),      //cian
    BLANC("blanc",7),      //blanc
    NEGRE("negre",8),      //negre
    MARRO("marro",9),      //marro
    GRIS("gris",10);   //gris

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