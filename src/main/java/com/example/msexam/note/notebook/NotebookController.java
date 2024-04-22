package com.example.msexam.note.notebook;

import com.example.msexam.note.note.Note;
import com.example.msexam.note.note.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class NotebookController {

    private final NotebookService notebookService;
    private final NoteService noteService;

    @PostMapping("/books/write")
    public String write () {
        Notebook notebook = this.notebookService.saveDefault();
        Note note = this.noteService.saveDefault();
        notebook.addNote(note);
        this.notebookService.save(notebook);

        return "redirect:/";
    }
    @PostMapping("/groups/{notebookId}/books/write")
    public String groupWrite (@PathVariable int notebookId) {
         Notebook parent = this.notebookService.getNotebook(notebookId);
        Notebook child = this.notebookService.saveDefault();

        Note note = this.noteService.saveDefault();
        child.addNote(note);
        this.notebookService.save(child);

        parent.addChild(child);
        this.notebookService.save(parent);

        return "redirect:/";
    }

    @GetMapping("/books/{id}")
    public String detail (@PathVariable int id, Model model) {

        Notebook notebook = this.notebookService.getNotebook(id);
        int noteId = notebook.getNoteList().getFirst().getId();

        return "redirect:/books/%d/notes/%d".formatted(id, noteId);
    }
}