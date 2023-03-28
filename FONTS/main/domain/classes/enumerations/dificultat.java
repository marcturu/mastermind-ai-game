package main.domain.classes;

public enum dificultat {
    FACIL("facil",1,4,1000,14),
    NORMAL("normal",2,6,1000,10),
    DIFICIL("dificil",3,8,1000,6);

    private String difcultat;
    private int num_dificultat;
    private int num_colors;
    private int temps_max;
    private int num_max_rondes;

    private dificultat(String difcultat, int num_dificultat, int num_colors, int temps_max, int num_max_rondes){
        this.difcultat = difcultat;
        this.num_dificultat = num_dificultat;
        this.num_colors = num_colors;
        this.temps_max = temps_max;
        this.num_max_rondes = num_max_rondes;
    }

    public String get_difcultat(){
        return difcultat;
    }

    public int get_num_dificultat(){
        return num_dificultat;
    }

    public int get_num_colors(){
        return num_colors;
    }

    public int get_temps_max(){
        return temps_max;
    }

    public int get_num_max_rondes(){
        return num_max_rondes;
    }
}


