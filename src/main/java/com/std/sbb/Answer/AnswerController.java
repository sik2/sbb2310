package com.std.sbb.Answer;

import com.std.sbb.Question.Question;
import com.std.sbb.Question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {
    private final QuestionService questionService;

    @PostMapping("/create/{id}")
    public String createAnswer (Model model, @PathVariable("id") Integer id, @RequestParam String content) {
        //답변 부모 질문객체를 받아온다.
        Question q = this.questionService.getQuestion(id);

        // TODO : 답변객체를 만든다.

        return "redirect:/question/detail/%d".formatted(id);
    }
}
