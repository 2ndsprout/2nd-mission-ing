package com.example.msexam.note;

import com.example.msexam.note.note.Note;
import com.example.msexam.note.note.NoteService;
import com.example.msexam.note.notebook.Notebook;
import com.example.msexam.note.notebook.NotebookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final NoteService noteService;
    private final NotebookService notebookService;

    @GetMapping("/")
    public String main (Model model) {
        List<Notebook> notebookList = this.notebookService.getList();
        if (notebookList.isEmpty()) {
            Notebook notebook = this.notebookService.saveDefault();
            Note note = this.noteService.saveDefault();
            notebook.addNote(note);
            this.notebookService.save(notebook);

            return "redirect:/";
        }
        model.addAttribute("notebookList", notebookList);
        model.addAttribute("targetNotebook",notebookList.getFirst());
        model.addAttribute("noteList", notebookList.getFirst().getNoteList());
        model.addAttribute("targetNote", notebookList.getFirst().getNoteList().getFirst());

        return "main";
    }


}
