package com.std.sbb.question;

import com.std.sbb.answer.AnswerForm;
import com.std.sbb.user.SiteUser;
import com.std.sbb.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {

    private final QuestionService questionService;
    private final UserService userService;

    @GetMapping("/list")
    public String list(Model model,  @RequestParam(value="page", defaultValue="0") int page) {
        Page<Question> paging = this.questionService.getList(page);
        model.addAttribute("paging", paging);

        return "question_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
        Question q = this.questionService.getQuestion(id);

        model.addAttribute("question", q);

        return "question_detail";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("questionForm", new QuestionForm());
        return "question_form";
    }

    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }

        SiteUser siteUser = this.userService.getUser(principal.getName());

        Question q = this.questionService.create( questionForm.getSubject(), questionForm.getContent(), siteUser);

        return "redirect:/question/list";
    }
}
