package sampleapp.dao;

import sampleapp.dao.custom.impl.FbPostDaoIMPL;

public class DaoFactory {
    private static DaoFactory daoFactory;
    private DaoFactory(){}

    public enum DaoType{
        FB_POSTS,// implement here new......................
    }

    public static DaoFactory getInstance(){
        return daoFactory = (daoFactory==null) ? new DaoFactory() : daoFactory;
    }
    public SuperDao getDao(DaoType type){
        switch (type){
            case FB_POSTS: return new FbPostDaoIMPL();
            default: return null;
        }
    }
}
