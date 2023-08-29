package com.example.DevHomeWork13.Controller;

import com.example.DevHomeWork13.entity.Note;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/note")
public class NoteController {

    private Map<Long, Note> notes = new HashMap<>();
    private long nextId = 1;

    @GetMapping("/list")
    public String listNotes(Model model) {
        List<Note> noteList = new ArrayList<>(notes.values());
        model.addAttribute("notes", noteList);
        return "list";
    }

    @GetMapping("/id")
    public String editNoteForm(@RequestParam long id, Model model) {
        if (!notes.containsKey(id)) {
            throw new IllegalArgumentException("Note with id " + id + " not found");
        }
        Note note = notes.get(id);
        model.addAttribute("note", note);
        return "edit";
    }

    @PostMapping("/edit")
    public String editNoteSubmit(@ModelAttribute Note editedNote) {
        if (!notes.containsKey(editedNote.getId())) {
            throw new IllegalArgumentException("Note with id " + editedNote.getId() + " not found");
        }
        Note existingNote = notes.get(editedNote.getId());
        existingNote.setTitle(editedNote.getTitle());
        existingNote.setContent(editedNote.getContent());
        return "redirect:/note/list";
    }

    @PostMapping("/delete")
    public String deleteNote(@RequestParam long id) {
        if (!notes.containsKey(id)) {
            throw new IllegalArgumentException("Note with id " + id + " not found");
        }
        notes.remove(id);
        return "redirect:/note/list";
    }

}
