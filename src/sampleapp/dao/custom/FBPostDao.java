package sampleapp.dao.custom;

import sampleapp.dao.CurdDao;
import sampleapp.entty.PostFB;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FBPostDao extends CurdDao<PostFB, Integer> {
    public ArrayList<PostFB> getAllForPaginate(String txt,int from,int to) throws ClassNotFoundException, SQLException;
    public int getPostCount(String txt) throws ClassNotFoundException, SQLException;

}
