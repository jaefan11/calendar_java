package com.scu.szmt.service;

import com.scu.szmt.dao.Conference;
import com.scu.szmt.dao.Note;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoteService {
    public void addNote(@Param("uId")int uId, @Param("cId")int cId);
    public void deleteNoteByUid(int uId);
    public List<Note> findConInNote(int id);
    public void deleteNote(int id);
}
