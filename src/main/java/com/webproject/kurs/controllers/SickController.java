package com.webproject.kurs.controllers;

import com.webproject.kurs.models.PostSick;
import com.webproject.kurs.repo.PostSickRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class SickController {

    @Autowired
    private PostSickRepository postSickRepository;

    @GetMapping("/blogSick")
    public String blogMain(Model model) {
        Iterable<PostSick> postsSick = postSickRepository.findAll();
        model.addAttribute("postsSick", postsSick);
        return "blog-main-sick";
    }

    @GetMapping("/blogSick/add")
    public String blogSickAdd(Model model) {
        return "blog-add-sick";
    }

    @PostMapping("/blogSick/add")
    public String blogSickPostAdd(@RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model){
        PostSick postSick = new PostSick(title, anons, full_text);
        postSickRepository.save(postSick);
        return "redirect:/blogSick";
    }

    @GetMapping("/blogSick/{id}")
    public String blogSickDetails(@PathVariable(value = "id") long id, Model model) {
        if (!postSickRepository.existsById(id)) {
            return "redirect:/blogSick";
        }
        Optional<PostSick> postSick = postSickRepository.findById(id);
        ArrayList<PostSick> res = new ArrayList<>();
        postSick.ifPresent(res::add);
        model.addAttribute("postSick", res);
        return "blog-details-sick";
    }

    @GetMapping("/blogSick/{id}/edit")
    public String blogSickEdit(@PathVariable(value = "id") long id, Model model) {
        if (!postSickRepository.existsById(id)) {
            return "redirect:/blogSick";
        }
        Optional<PostSick> postSick = postSickRepository.findById(id);
        ArrayList<PostSick> res = new ArrayList<>();
        postSick.ifPresent(res::add);
        model.addAttribute("postSick", res);
        return "blog-edit-sick";
    }

    @PostMapping("/blogSick/{id}/edit")
    public String blogSickPostUpdate(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model){
        PostSick postSick = postSickRepository.findById(id).orElseThrow();
        postSick.setTitle(title);
        postSick.setAnons(anons);
        postSick.setFull_text(full_text);
        postSickRepository.save(postSick);
        return "redirect:/blogSick";
    }

    @PostMapping("/blogSick/{id}/remove")
    public String blogSickPostRemove(@PathVariable(value = "id") long id, Model model){
        PostSick postSick = postSickRepository.findById(id).orElseThrow();
        postSickRepository.delete(postSick);
        return "redirect:/blogSick";
    }
}
