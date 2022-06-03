package sampleapp.bo.custom.impl;

import sampleapp.bo.custom.FbPostBo;
import sampleapp.dao.DaoFactory;
import sampleapp.dao.custom.FBPostDao;
import sampleapp.dto.PostDTO;
import sampleapp.entty.PostFB;

import java.sql.SQLException;
import java.util.ArrayList;

public class FbPostBoIMPL implements FbPostBo {
    FBPostDao dao = (FBPostDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.FB_POSTS);
    @Override
    public boolean savePost(PostDTO dto) throws ClassNotFoundException, SQLException, InterruptedException {
        return dao.save(new PostFB(dto.getTime(),dto.getPost_by(),dto.getMassage(),dto.getImgURL()));
    }

    @Override
    public boolean updatePost(PostDTO dto) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean deletePost(Integer id) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public PostDTO grtPost(Integer id) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ArrayList<PostDTO> getAllPost(String txt) throws ClassNotFoundException, SQLException {
        ArrayList<PostFB> all = dao.getAll(txt);
        ArrayList<PostDTO> list = new ArrayList<>();
        for (PostFB p: all) {
            list.add(new PostDTO(p.getTime(),p.getPost_by(),p.getMassage(), p.getImgURL()));
        }
        return list;
    }
}
