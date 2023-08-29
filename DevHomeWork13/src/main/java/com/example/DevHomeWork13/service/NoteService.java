package com.example.DevHomeWork13.service;


import com.example.DevHomeWork13.entity.Note;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public
class NoteService {
    private Map<Long, Note> notes = new HashMap<>();
    private long nextId = 1;

    public List<Note> listAll() {
        return new ArrayList<>(notes.values());
    }

    public Note add(Note note) {
        note.setId(nextId);
        notes.put(nextId, note);
        nextId++;
        return note;
    }

    public void deleteById(long id) {
        if (!notes.containsKey(id)) {
            throw new NoSuchElementException("Note with id " + id + " not found");
        }
        notes.remove(id);
    }

    public void update(Note note) {
        long id = note.getId();
        if (!notes.containsKey(id)) {
            throw new NoSuchElementException("Note with id " + id + " not found");
        }
        notes.put(id, note);
    }

    public Note getById(long id) {
        if (!notes.containsKey(id)) {
            throw new NoSuchElementException("Note with id " + id + " not found");
        }
        return notes.get(id);
    }
}
