package sampleapp.dao;

public class Status {
    private boolean isSave;
    private int generatedKe;
    public Status(){}
    public Status(boolean isSave, int generatedKe) {
        this.setSave(isSave);
        this.setGeneratedKe(generatedKe);
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("delete");
    }

    public boolean isSave() {
        return isSave;
    }

    public void setSave(boolean save) {
        isSave = save;
    }

    public int getGeneratedKe() {
        return generatedKe;
    }

    public void setGeneratedKe(int generatedKe) {
        this.generatedKe = generatedKe;
    }
}
