package pl.coderslab.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.model.Note;
import pl.coderslab.repository.NoteRepository;
import pl.coderslab.service.NoteService;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    NoteRepository noteRepository;


    @Override
    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }
}
