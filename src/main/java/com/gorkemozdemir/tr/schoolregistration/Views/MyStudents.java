package com.gorkemozdemir.tr.schoolregistration.Views;

import com.gorkemozdemir.tr.schoolregistration.models.Student;
import com.gorkemozdemir.tr.schoolregistration.models.Teacher;
import com.gorkemozdemir.tr.schoolregistration.models.Term;
import com.gorkemozdemir.tr.schoolregistration.services.StudentService;
import com.gorkemozdemir.tr.schoolregistration.services.TeacherService;
import com.gorkemozdemir.tr.schoolregistration.services.TermService;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.util.ArrayList;
import java.util.List;

@Route("/MyStudents")
public class MyStudents extends VerticalLayout {
    private String gelendonem;
    private int gelenid;
    private int donemid;
    private TermService termService;
    private TeacherService teacherService;
    Grid<Term> grid = new Grid<>(Term.class);
    private StudentService studentService;
    Grid<Student> studentGrid = new Grid<>(Student.class);
    HorizontalLayout horizontalLayout = new HorizontalLayout();
    HorizontalLayout horizontalLayout1 = new HorizontalLayout();
    public MyStudents(StudentService studentService,TermService termService,TeacherService teacherService){
        this.termService = termService;
        this.studentService = studentService;
        this.teacherService = teacherService;
        if (VaadinSession.getCurrent().getSession().getAttribute("LoggedInTeacherId") == null){
            UI.getCurrent().getPage().setLocation("/TeacherLogin");
        }else{
            gelenid = Integer.parseInt(VaadinSession.getCurrent().getSession().getAttribute("LoggedInTeacherId").toString());

        }
        List<Student> student = new ArrayList<>();
        List<String> termList = new ArrayList<>();
        termList.addAll(termService.donemad());
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setLabel("Dönemler");

        comboBox.setItems(termList);

        Button button = new Button("Listele", VaadinIcon.LIST.create());
        button.addClickListener(buttonClickEvent -> {
            Teacher teacher = new Teacher();
            Term term = new Term();

            teacher.setEgitmenID(gelenid);

            gelendonem = comboBox.getValue();


            ;
            List<Student> students = new ArrayList<>();
            students.addAll(studentService.findByTeachersAndTermssds(teacher,termService.findByDonem(gelendonem)));
            studentGrid.setItems(students);



        });

        studentGrid.removeColumnByKey("ogrenciID");
        studentGrid.setSelectionMode(Grid.SelectionMode.NONE);
        studentGrid.setColumns("ogrenciAd","ogrenciSoyad","ogrenciNo","email");
        horizontalLayout.add(new H2("Öğrencilerim"));
        horizontalLayout.setWidth("100%");
        horizontalLayout.getStyle().set("border","0px solid #9E9E9E");
        horizontalLayout.setJustifyContentMode(JustifyContentMode.CENTER);

        horizontalLayout1.setWidth("100%");
        horizontalLayout1.getStyle().set("border","0px solid #9E9E9E");
        horizontalLayout1.add(comboBox);
        add(horizontalLayout,horizontalLayout1,button,studentGrid);


    }
    private void refreshData(){
        List<Student> students = new ArrayList<>();
        students.addAll(studentService.getAll());
        studentGrid.setItems(students);
    }
}
