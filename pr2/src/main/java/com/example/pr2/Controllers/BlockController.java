package com.example.pr2.Controllers;

import com.example.pr2.Models.*;
import com.example.pr2.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
public class BlockController {
    @Autowired
    private PostRepos postRepos;

    @Autowired
    private PrepodRepos prepodRepos;

    @Autowired
    private StudentRepos studentRepos;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UniversityRepository universityRepository;

    @GetMapping("/")
    public String Main(Model model){
        return "Home";
    }

    @GetMapping("/univer")
    public String GetUniver(Model model){
        Iterable<University> universityIterable = universityRepository.findAll();
        model.addAttribute("university",universityIterable);
        return "university-main";
    }
    @GetMapping("/adress")
    public String GetAdress(Model model){
        Iterable<Address> addresses = addressRepository.findAll();
        model.addAttribute("address",addresses);
        return "adress-main";
    }

    @GetMapping("/block")
    public String GetBlog(Model model){
        Iterable<Post> posts = postRepos.findAll();
        model.addAttribute("posts", posts);
        return "block-main";

    }
    @GetMapping("/student")
    public String GetStudent(Model model){
        Iterable<Student> students = studentRepos.findAll();
        model.addAttribute("student",students);
        return "student-main";
    }
    @GetMapping("/prepod")
    public String GetPrepod(Model model){
        Iterable<Prepod> prepods = prepodRepos.findAll();
        model.addAttribute("prepods",prepods);
        return "prepod-main";
    }
    @GetMapping("/block/add")
    public String blogAdd(Post post, Model model){
        return "block-add";
    }
    @GetMapping("/student/add")
    public String studentAdd(Student student, Model model){
        model.addAttribute("univ", universityRepository.findAll());
        return "student-add";
    }
    @GetMapping("/prepod/add")
    public String prepodAdd(Prepod prepod, Model model){
        return "prepod-add";
    }

    @GetMapping("/univer/add")
    public String univerAdd(University university, Model model){
        model.addAttribute("adddress", addressRepository.findAll());
        System.out.println(addressRepository.findAll().iterator().next().getAddreses());
        return "university-add";
    }
    @GetMapping("/adress/add")
    public String addressAdd(Address address, Model model){
        return "adress-add";
    }

    @PostMapping("/univer/add")
    public String univerAdd(@ModelAttribute("university")@Validated University university, BindingResult bindingResult,
                            @RequestParam Long adress_id, Model model){
        model.addAttribute("adddress", addressRepository.findAll());
        if(bindingResult.hasErrors()){
            return "university-add";
        }
        Address address;
        address = addressRepository.findById(adress_id).get();
        university.setAddress(address);
        universityRepository.save(university);
        return "redirect:/univer";
    }
    @PostMapping("/adress/add")
    public String addressAdd(@ModelAttribute("address")@Validated Address address, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "adress-add";
        }
        addressRepository.save(address);
        return "redirect:/adress";
    }

    @PostMapping("/block/add")
    public String blogPostAdd(@ModelAttribute("post")@Validated Post post, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "block-add";
        }
        postRepos.save(post);
        return "redirect:/block";
    }
    @PostMapping("/student/add")
    public Object studentPostAdd(@ModelAttribute("student")@Validated Student student, BindingResult bindingResult,
                                 @RequestParam Long univer_id, Model model){
        model.addAttribute("univ", universityRepository.findAll());
        if(bindingResult.hasErrors()){
            return "student-add";
        }
        University university;
        university = universityRepository.findById(univer_id).get();
        student.setUniversity(university);
        studentRepos.save(student);
        return "redirect:/student";
    }
    @PostMapping("/prepod/add")
    public Object prepodPostAdd(@ModelAttribute("prepod")@Validated Prepod prepod, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "prepod-add";
        }
        prepodRepos.save(prepod);
        return "redirect:/prepod";
    }
    @GetMapping("/block/filter")
    public String blogFilter(Model model){
        return "block-filter";
    }
    @GetMapping("/student/filter")
    public String studentFilter(Model model){
        return "student-filter";
    }
    @GetMapping("/prepod/filter")
    public String prepodFilter(Model model){
        return "prepod-filter";
    }
    @GetMapping("/univer/filter")
    public String univerFilter(Model model){
        return "university-filter";
    }

    @PostMapping("/univer/filter/result")
    public String collegeResult(@RequestParam String titleuniversity, Model model){
        List<University> result = universityRepository.findByTitleuniversity(titleuniversity);
        model.addAttribute("result", result);
        return "university-filter";
    }

    @PostMapping("/block/filter/result")
    public String blogResult(@RequestParam String title, Model model){
        List<Post> result = postRepos.findByTitleContains(title);
        model.addAttribute("result", result);
        return "block-filter";
    }
    @PostMapping("/student/filter/result")
    public String studentResult(@RequestParam String surname, Model model){
        List<Student> result = studentRepos.findBysurnameContains(surname);
        model.addAttribute("result", result);
        return "student-filter";
    }
    @PostMapping("/prepod/filter/result")
    public String prepodResult(@RequestParam String surname, Model model){
        List<Prepod> result = prepodRepos.findBysurname(surname);
        model.addAttribute("result", result);
        return "prepod-filter";
    }
    //ADDRESS
    @GetMapping("/adress/{id}/edit")
    public String adressEdit(@PathVariable("id") long id, Model model){
        Address res = addressRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Неверный id: "+id));
        model.addAttribute("adress", res);
        return "adress-edit";
    }
    @PostMapping("/adress/{id}/edit")
    public String adressUpdate(@PathVariable("id") long id, @ModelAttribute("address")@Validated Address address, BindingResult bindingResult){
        address.setId(id);
        if(bindingResult.hasErrors()){
            return "adress-edit";
        }
        addressRepository.save(address);
        return "redirect:/adress";
    }

    @PostMapping("/adress/{id}/remove")
    public String adressDelete(@PathVariable("id")long id, Model model){
        Address address = addressRepository.findById(id).orElseThrow();
        addressRepository.delete(address);
        return "redirect:/adress";
    }

    //COLLEGE
    @GetMapping("/univer/{id}")
    public String univerViewing(@PathVariable(value = "id") long id, Model model){
        Optional<University> universityOptional = universityRepository.findById(id);
        ArrayList<University> res = new ArrayList<>();
        universityOptional.ifPresent(res::add);
        model.addAttribute("univer",res);
        if(!universityRepository.existsById(id)){
            return "redirect:/univer";
        }
        return "university-detail";
    }
    @GetMapping("/univer/{id}/edit")
    public String univerEdit(@PathVariable("id") long id, Model model){
        University res = universityRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Неверный id: "+id));
        model.addAttribute("addresses", addressRepository.findAll());
        model.addAttribute("univer", res);
        return "university-edit";
    }
    @PostMapping("/univer/{id}/edit")
    public String univerUpdate(@PathVariable("id") long id, @ModelAttribute("address")@Validated University university, BindingResult bindingResult,
                                @RequestParam Long addresss_id, Model model){
        university.setId(id);
        if(bindingResult.hasErrors()){
            return "university-edit";
        }
        Address address;
        address = addressRepository.findById(addresss_id).get();
        university.setAddress(address);
        universityRepository.save(university);
        return "redirect:/univer";
    }

    @PostMapping("/univer/{id}/remove")
    public String univerDelete(@PathVariable("id")long id, Model model){
        University university = universityRepository.findById(id).orElseThrow();
        universityRepository.delete(university);
        return "redirect:/univer";
    }

    @GetMapping("/prepod/{id}")
    public String prepodViewing(@PathVariable(value = "id") long id, Model model){
        Optional<Prepod> prepodOptional = prepodRepos.findById(id);
        ArrayList<Prepod> res = new ArrayList<>();
        prepodOptional.ifPresent(res::add);
        model.addAttribute("prepod",res);
        if(!prepodRepos.existsById(id)){
            return "redirect:/prepods";
        }
        return "prepod-detail";
    }
    @GetMapping("/prepod/{id}/edit")
    public String prepodEdit(@PathVariable("id") long id, Model model){
        Prepod res = prepodRepos.findById(id).orElseThrow(() -> new IllegalArgumentException("Неверный id: "+id));
        model.addAttribute("prepod", res);
        return "prepod-edit";
    }
    @PostMapping("/prepod/{id}/edit")
    public String prepodUpdate(@PathVariable("id") long id, @ModelAttribute("prepod")@Validated Prepod prepod, BindingResult bindingResult){
        prepod.setId(id);
        if(bindingResult.hasErrors()){
            return "prepod-edit";
        }
        prepodRepos.save(prepod);
        return "redirect:/prepod";
    }

    @PostMapping("/prepod/{id}/remove")
    public String prepodDelete(@PathVariable("id")long id, Model model){
        Prepod prepod = prepodRepos.findById(id).orElseThrow();
        prepodRepos.delete(prepod);
        return "redirect:/prepod";
    }




    @GetMapping("/student/{id}")
    public String studentViewing(@PathVariable(value = "id") long id, Model model){
        Optional<Student> studentOptional = studentRepos.findById(id);
        ArrayList<Student> res = new ArrayList<>();
        studentOptional.ifPresent(res::add);
        model.addAttribute("student",res);
        if(!studentRepos.existsById(id)){
            return "redirect:/student";
        }
        return "student-detail";
    }
    @GetMapping("/student/{id}/edit")
    public String studentEdit(@PathVariable("id") long id, Model model){
        Student res = studentRepos.findById(id).orElseThrow(() -> new IllegalArgumentException("Неверный id: "+id));
        model.addAttribute("univ", universityRepository.findAll());
        model.addAttribute("student", res);
        return "student-edit";
    }
    @PostMapping("/student/{id}/edit")
    public String studentUpdate(@PathVariable("id") long id, @ModelAttribute("student") @Validated Student student, BindingResult bindingResult,
                                @RequestParam Long universss_id, Model model){
        model.addAttribute("univ", universityRepository.findAll());
        student.setId(id);
        if(bindingResult.hasErrors()){
            return "student-edit";
        }
        University university;
        university = universityRepository.findById(universss_id).get();
        student.setUniversity(university);
        studentRepos.save(student);
        return "redirect:/student";
    }

    @PostMapping("/student/{id}/remove")
    public String studentDelete(@PathVariable("id")long id, Model model){
        Student student = studentRepos.findById(id).orElseThrow();
        studentRepos.delete(student);
        return "redirect:/student";
    }

    @GetMapping("/block/{id}")
    public String blockViewing(@PathVariable(value = "id") long id, Model model){
        Optional<Post> postOptional = postRepos.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        postOptional.ifPresent(res::add);
        model.addAttribute("post",res);
        if(!postRepos.existsById(id)){
            return "redirect:/block";
        }
        return "block-detail";
    }
    @GetMapping("/block/{id}/edit")
    public String blockEdit(@PathVariable("id") long id, Model model){
        Post res = postRepos.findById(id).orElseThrow(() -> new IllegalArgumentException("Неверный id: "+id));
        model.addAttribute("post", res);
        return "block-edit";
    }
    @PostMapping("/block/{id}/edit")
    public String blockUpdate(@PathVariable("id") long id, @ModelAttribute("post")@Validated Post post, BindingResult bindingResult){
        post.setId(id);
        if(bindingResult.hasErrors()){
            return "block-edit";
        }
        postRepos.save(post);
        return "redirect:/block";
    }

    @PostMapping("/block/{id}/remove")
    public String blockDelete(@PathVariable("id")long id, Model model){
        Post post = postRepos.findById(id).orElseThrow();
        postRepos.delete(post);
        return "redirect:/block";
    }

}
