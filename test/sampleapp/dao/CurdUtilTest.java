package sampleapp.dao;


import sampleapp.bo.BoFactory;
import sampleapp.bo.SuperBo;
import sampleapp.bo.custom.FbPostBo;
import sampleapp.bo.custom.impl.FbPostBoIMPL;
import sampleapp.dao.custom.FBPostDao;
import sampleapp.dao.custom.impl.FbPostDaoIMPL;
import sampleapp.dto.PostDTO;
import sampleapp.entty.PostFB;

import java.sql.SQLException;
import java.util.ArrayList;

class CurdUtilTest {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
       FbPostBo bo= (FbPostBo) BoFactory.getInstance().getBo(BoFactory.BoType.FB_POSTS);
        ArrayList<PostDTO> allPost = bo.getAllPost("");
        System.out.println(allPost);
    }
}
