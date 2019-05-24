package com.scu.szmt.service.impl;

import com.scu.szmt.dao.Conference;
import com.scu.szmt.dao.Note;
import com.scu.szmt.mapper.NoteMapper;
import com.scu.szmt.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    NoteMapper noteMapper;
    @Override
    public void addNote(int uId, int cId) {
        noteMapper.addNote(uId, cId);
    }

    @Override
    public void deleteNoteByUid(int uId) {
        noteMapper.deleteNoteByUid(uId);
    }

    @Override
    public List<Note> findConInNote(int id) {
        return noteMapper.findConInNote(id);
    }

    @Override
    public void deleteNote(int id) {
        noteMapper.deleteNote(id);
    }
}
