package com.gorkemozdemir.tr.schoolregistration.Views;

import com.gorkemozdemir.tr.schoolregistration.models.Lesson;
import com.gorkemozdemir.tr.schoolregistration.models.Student;
import com.gorkemozdemir.tr.schoolregistration.models.Teacher;
import com.gorkemozdemir.tr.schoolregistration.models.Term;
import com.gorkemozdemir.tr.schoolregistration.services.LessonService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route("/ReportPage")
public class Report extends VerticalLayout {
    LessonService lessonService;
    Grid<Student> studentGrid = new Grid<>(Student.class);
    public Report(LessonService lessonService){
        this.lessonService = lessonService;
        List<String> lessonlist = new ArrayList<>();
        lessonlist.addAll(lessonService.dersad());
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setLabel("Dersler");
        comboBox.setItems(lessonlist);
        Button btn = new Button("Listele",VaadinIcon.LIST.create());
        add(comboBox,btn);
        btn.addClickListener(buttonClickEvent -> {
            Student student = new Student();
            Lesson lesson = new Lesson();






            


        });
    }
}
