package com.example.msexam.note.notebook;

import com.example.msexam.note.note.Note;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Notebook { // 그룹 노트북 관점, 일반 노트북 관점

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    private Notebook parent;

    @OneToMany(mappedBy = "notebook")
    private List<Note> noteList = new ArrayList<>();

    @OneToMany(mappedBy = "parent")
    List<Notebook> children = new ArrayList<>();

    public void addChild (Notebook child) {
        child.setParent(this);
        children.add(child);
    }
    public void addNote (Note note) {
        note.setNotebook(this);
        noteList.add(note);
    }
}
