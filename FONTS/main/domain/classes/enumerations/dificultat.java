package main.domain.classes;

public enum dificultat {
    FACIL("facil",1),
    NORMAL("normal",2),
    DIFICIL("dificil",3);

    private String difcultat;
    private int num_dificultat;

    private dificultat(String difcultat, int num_dificultat){
        this.difcultat = difcultat;
        this.num_dificultat = num_dificultat;
    }

    public String get_difcultat(){
        return difcultat;
    }

    public int get_num_dificultat(){
        return num_dificultat;
    }
}


