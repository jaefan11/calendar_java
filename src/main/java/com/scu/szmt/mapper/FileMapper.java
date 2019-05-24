package com.scu.szmt.mapper;

import com.scu.szmt.dao.MyFile;

import java.util.List;

public interface FileMapper {
    public List<MyFile> findFilesByCid(int id);
    public  MyFile findFileById(int id);
    public int uploadFile(MyFile myFile);
    public void deletFileByCid(int cid);
    public void removeFileById(int id);
}
