package com.gorkemozdemir.tr.schoolregistration.Views;

import com.gorkemozdemir.tr.schoolregistration.models.Lesson;
import com.gorkemozdemir.tr.schoolregistration.models.Student;
import com.gorkemozdemir.tr.schoolregistration.models.Teacher;
import com.gorkemozdemir.tr.schoolregistration.models.Term;
import com.gorkemozdemir.tr.schoolregistration.services.LessonService;
import com.gorkemozdemir.tr.schoolregistration.services.StudentService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route("/ReportPage")
public class Report extends VerticalLayout {
    LessonService lessonService;
    StudentService studentService;
    private String gelenDers;
    HorizontalLayout horizontalLayout = new HorizontalLayout();
    HorizontalLayout horizontalLayout1 = new HorizontalLayout();
    Grid<Student> studentGrid = new Grid<>(Student.class);
    public Report(LessonService lessonService,StudentService studentService){

        this.studentService = studentService;
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


            gelenDers = comboBox.getValue();



            ;
            List<Student> students = new ArrayList<>();
            //Lessonservice'de olan findlessonbydersad fonksiyonuna comboboxdan seçilen dersi parametre olarak gönderdim
            //Dönen Lesson değeri için Studentservice'den findbyLessons fonksiyonu ile veritabanında bağlantılı olan öğrencileri çekiyorum.
            students.addAll(studentService.findByLessons(lessonService.findLessonByDersAd(gelenDers)));
            studentGrid.setItems(students);



        });
        studentGrid.removeColumnByKey("ogrenciID");
        studentGrid.setSelectionMode(Grid.SelectionMode.NONE);
        studentGrid.setColumns("ogrenciAd","ogrenciSoyad","ogrenciNo","email");
        horizontalLayout.add(new H2("Derse Göre Öğrenci Raporu"));
        horizontalLayout.setWidth("100%");
        horizontalLayout.getStyle().set("border","0px solid #9E9E9E");
        horizontalLayout.setJustifyContentMode(JustifyContentMode.CENTER);

        horizontalLayout1.setWidth("100%");
        horizontalLayout1.getStyle().set("border","0px solid #9E9E9E");
        horizontalLayout1.add(comboBox);
        add(horizontalLayout,horizontalLayout1,studentGrid);
    }
}
