package sampleapp.bo;

import sampleapp.bo.custom.impl.FbPostBoIMPL;

public class BoFactory {
    private static BoFactory boFactory;
    private BoFactory(){}

    public enum BoType{
        FB_POSTS,// implement here new......................
    }

    public static BoFactory getInstance(){
        return boFactory = (boFactory==null) ? new BoFactory() : boFactory;
    }
    public SuperBo getBo(BoType type){
        switch (type){
            case FB_POSTS: return new FbPostBoIMPL();
            default: return null;
        }
    }
}
