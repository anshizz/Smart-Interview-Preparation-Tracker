package com.interviewtracker.daoimpl;

import com.interviewtracker.dao.NotesDAO;
import com.interviewtracker.model.Note;
import com.interviewtracker.utility.DBConnection;

import java.util.ArrayList;
import java.util.List;

public class NotesDAOImpl implements NotesDAO {
    @Override public boolean addNote(Note note) { return false; }
    @Override public boolean updateNote(Note note) { return false; }
    @Override public boolean deleteNote(int noteId) { return false; }
    @Override public Note getNoteById(int noteId) { return null; }
    @Override public List<Note> getAllNotesByUser(int userId) { return new ArrayList<>(); }
}
