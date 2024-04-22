package com.example.msexam.note.note;

import com.example.msexam.note.notebook.Notebook;
import com.example.msexam.note.notebook.NotebookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books/{notebookId}/notes")
public class NoteController {

    private final NotebookService notebookService;
    private final NoteService noteService;

    @GetMapping("/{id}")
    public String detail (@PathVariable int id,
                          @PathVariable int notebookId, Model model) {
        Note note = this.noteService.getNote(id);
        Notebook targetNotebook = this.notebookService.getNotebook(notebookId);
        List<Notebook> notebookList = this.notebookService.getList();

        model.addAttribute("notebookList", notebookList);
        model.addAttribute("targetNotebook", targetNotebook);
        model.addAttribute("noteList", targetNotebook.getNoteList());
        model.addAttribute("targetNote", note);

        return "main";
    }

    @PostMapping("/write")
    public String write (@PathVariable int notebookId) {

        Notebook notebook = this.notebookService.getNotebook(notebookId);
        Note note = this.noteService.saveDefault();
        notebook.addNote(note);
        this.notebookService.save(notebook);

        return "redirect:/books/%d".formatted(notebookId);
    }
    @PostMapping("/{id}/update")
    public String update (@PathVariable int notebookId,
                          @PathVariable int id, String title, String content) {
        Note note = this.noteService.getNote(id);

        this.noteService.update(note, title, content);

        return "redirect:/books/%d/notes/%d".formatted(notebookId, id);

    }
    @PostMapping("/{id}/delete")
    public String delete (@PathVariable int notebookId,
                          @PathVariable int id) {
        Note note = this.noteService.getNote(id);
        this.noteService.delete(note);

        return "redirect:/books/%d".formatted(notebookId);
    }
}
