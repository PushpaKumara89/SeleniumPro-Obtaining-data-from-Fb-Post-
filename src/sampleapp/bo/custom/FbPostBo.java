package sampleapp.bo.custom;

import sampleapp.bo.SuperBo;
import sampleapp.dto.PostDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FbPostBo extends SuperBo {
    public boolean savePost(PostDTO dto) throws ClassNotFoundException, SQLException, InterruptedException;
    public boolean updatePost(PostDTO dto) throws ClassNotFoundException, SQLException;
    public boolean deletePost(Integer id) throws ClassNotFoundException, SQLException;
    public PostDTO grtPost(Integer id) throws ClassNotFoundException, SQLException;
    public ArrayList<PostDTO> getAllPost(String txt) throws ClassNotFoundException, SQLException;
}
