package com.interviewtracker.dao;

import com.interviewtracker.model.Note;
import java.util.List;

public interface NotesDAO {
    boolean addNote(Note note);
    boolean updateNote(Note note);
    boolean deleteNote(int noteId);
    Note getNoteById(int noteId);
    List<Note> getAllNotesByUser(int userId);
}
