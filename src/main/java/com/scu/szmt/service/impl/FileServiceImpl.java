package com.scu.szmt.service.impl;

import com.scu.szmt.dao.MyFile;
import com.scu.szmt.mapper.FileMapper;
import com.scu.szmt.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    FileMapper fileMapper;
    @Override
    public List<MyFile> findFilesByCid(int id) {
        return fileMapper.findFilesByCid(id);
    }

    @Override
    public MyFile findFileById(int id) {
        return fileMapper.findFileById(id);
    }

    @Override
    public int uploadFile(MyFile myFile) {
        return fileMapper.uploadFile(myFile);
    }

    @Override
    public void deletFileByCid(int cid) {
        fileMapper.deletFileByCid(cid);
    }

    @Override
    public void removeFileById(int id) {
        fileMapper.removeFileById(id);
    }
}
