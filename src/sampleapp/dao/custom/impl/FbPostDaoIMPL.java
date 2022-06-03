package sampleapp.dao.custom.impl;

import sampleapp.dao.CurdUtil;
import sampleapp.dao.Status;
import sampleapp.dao.custom.FBPostDao;
import sampleapp.db.DBConnection;
import sampleapp.dto.PostDTO;
import sampleapp.entty.PostFB;

import java.sql.*;
import java.util.ArrayList;

public class FbPostDaoIMPL implements FBPostDao {

    @Override
    public boolean save(PostFB p) throws ClassNotFoundException, SQLException, InterruptedException {
        Connection connection = DBConnection.getInstance().getConnection();
        Status status;
        try {
            connection.setAutoCommit(false);
            status = CurdUtil.execute("INSERT INTO post values(?,?,?,?)", null, p.getTime(), p.getPost_by(), p.getMassage());
            Thread.sleep(100);

            if (status.isSave()){

                    int post_id = status.getGeneratedKe();
                    boolean isAdd = addPost_url(post_id, p.getImgURL());
                    if (isAdd) {
                        connection.commit();
                        return true;
                    }
            }
            connection.rollback();
            return false;
        }finally {
            status=null;
            System.gc();
            connection.setAutoCommit(true);
        }
    }

    @Override
    public boolean delete(Integer integer) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean update(Integer integer) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public PostFB get(Integer integer) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ArrayList<PostFB> getAll(String txt) throws ClassNotFoundException, SQLException {
        ResultSet rst = CurdUtil.execute("SELECT * FROM post WHERE post_by LIKE '%"+txt+"%' OR massage LIKE '%"+txt+"%'");
        ArrayList<PostFB> list = new ArrayList<>();
        for (int i = 0; rst.next(); i++) {
            ResultSet rst1 = CurdUtil.execute("SELECT url FROM post_url WHERE post_id=?",rst.getInt(1));
            ArrayList<String> url = new ArrayList<>();
            while (rst1.next()){
                url.add(rst1.getString(1));
            }
            list.add(new PostFB(rst.getString(2),rst.getString(3),rst.getString(4),url));
        }
        return list;
    }

    private static boolean addPost_url(int post_id, ArrayList<String> imgURL) throws SQLException, ClassNotFoundException {
        for (int i = 0; i < imgURL.size(); i++) {
            Status status = CurdUtil.execute("INSERT INTO post_url VALUES(?,?,?)",null,post_id,imgURL.get(i));
            System.out.println(status.isSave());
            if(!status.isSave())return false;
        }
        return true;
    }
}
