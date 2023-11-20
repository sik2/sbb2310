package com.std.sbb.Answer;

import com.std.sbb.Question.Question;
import com.std.sbb.Question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {
    private final QuestionService questionService;
    private final AnswerService answerService;

    @PostMapping("/create/{id}")
    public String createAnswer (Model model, @PathVariable("id") Integer id, @RequestParam String content) {
        //답변 부모 질문객체를 받아온다.
        Question q = this.questionService.getQuestion(id);

        Answer answer = this.answerService.create(q, content);

        return "redirect:/question/detail/%d".formatted(id);
    }
}
