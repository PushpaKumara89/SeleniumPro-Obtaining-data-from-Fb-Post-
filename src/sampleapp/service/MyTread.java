package sampleapp.service;

public class MyTread extends Thread{
    public static boolean exit =false;
    public MyTread() {
    }
    public boolean getExit(){
        return exit;
    }
    public MyTread(Runnable r) {
        super(r);
    }
}
