package com.example.msexam.note.notebook;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotebookService {

    private final NotebookRepository notebookRepository;

    public Notebook saveDefault() {
        Notebook notebook = new Notebook();
        notebook.setName("새노트");
        return this.notebookRepository.save(notebook);
    }
    public List<Notebook> getList () {
        return this.notebookRepository.findAll();
    }
    public Notebook getNotebook (int id) {
        Optional<Notebook> notebook = this.notebookRepository.findById(id);
        if (notebook.isPresent()) {
            return notebook.get();
        }
        else {
            throw new RuntimeException("notebook not found");
        }
    }
    public Notebook save (Notebook notebook) {
       return this.notebookRepository.save(notebook);
    }
}
