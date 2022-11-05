class Animal {
    private String jenis;
    private String habitat;
    private int kaki;
    private final String kaum = "Fauna";

    private boolean x;

    public boolean isX() {
        return x;
    }

    public void setX(boolean x) {
        this.x = x;
    }

    public String getKaum(){
        return this.kaum;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public int getKaki() {
        return kaki;
    }

    public void setKaki(int kaki) {
        this.kaki = kaki;
    }
}
