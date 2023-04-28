package idusw.springboot.controller;

import idusw.springboot.domain.Member;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class RestApiController {

    @GetMapping("/")
    public String readList() {
        String result = "";

        return result;
    }
    @GetMapping("/{id}")
    public String readOne(@PathVariable Long id, Model model) {
        String result = "";

        return result;
    }
    @GetMapping("/register-form")
    public String getCreate(Model model) { // register-form 호출
        String result = "";

        return result;
    }
    @PostMapping("/{id}")
    public String createOne(@RequestBody Member member, Model model) { // @RequestBody
        String result = "";

        return result;
    }
    @PutMapping("/{id}")
    public String updateOne(@PathVariable Long id, Model model) {
        String result = "";

        return result;
    }
    @DeleteMapping("/{id}")
    public String deleteOne(@PathVariable Long id, Model model) {
        String result = "";

        return result;
    }
}
