package com.example.msexam.note.note;

import com.example.msexam.note.notebook.Notebook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Integer> {
    List<Note> findByNotebook (Notebook notebook);
}
