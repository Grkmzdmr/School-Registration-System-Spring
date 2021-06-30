package com.gorkemozdemir.tr.schoolregistration.Views;

import com.gorkemozdemir.tr.schoolregistration.models.Lesson;
import com.gorkemozdemir.tr.schoolregistration.models.Student;
import com.gorkemozdemir.tr.schoolregistration.services.DepartmantService;
import com.gorkemozdemir.tr.schoolregistration.services.LessonService;
import com.gorkemozdemir.tr.schoolregistration.services.StudentService;
import com.vaadin.flow.component.UI;
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

@Route("/DepartmantsReport")
public class DepartmantsReport extends VerticalLayout {
    StudentService studentService;
    DepartmantService departmantService;

    private String gelenBolum;
    HorizontalLayout horizontalLayout = new HorizontalLayout();
    HorizontalLayout horizontalLayout1 = new HorizontalLayout();
    Grid<Student> studentGrid = new Grid<>(Student.class);

    public DepartmantsReport(DepartmantService departmantService, StudentService studentService){

        this.studentService = studentService;
        this.departmantService = departmantService;
        List<String> departmantList = new ArrayList<>();
        departmantList.addAll(departmantService.bolumAd());
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setLabel("Bölümler");
        comboBox.setItems(departmantList);
        Button btn = new Button("Listele", VaadinIcon.LIST.create());
        add(comboBox,btn);
        btn.addClickListener(buttonClickEvent -> {




            gelenBolum = comboBox.getValue();



            ;
            List<Student> students = new ArrayList<>();

            students.addAll(studentService.findStudentByDepartmant(departmantService.findDepartmantByBolumAd(gelenBolum)));
            studentGrid.setItems(students);









        });

        studentGrid.removeColumnByKey("ogrenciID");
        studentGrid.setSelectionMode(Grid.SelectionMode.NONE);
        studentGrid.setColumns("ogrenciAd","ogrenciSoyad","ogrenciNo","email");
        horizontalLayout.add(new H2("Bölüme Göre Öğrenci Raporu"));
        horizontalLayout.setWidth("100%");
        horizontalLayout.getStyle().set("border","0px solid #9E9E9E");
        horizontalLayout.setJustifyContentMode(JustifyContentMode.CENTER);

        horizontalLayout1.setWidth("100%");
        horizontalLayout1.getStyle().set("border","0px solid #9E9E9E");
        horizontalLayout1.add(comboBox);

        add(horizontalLayout,horizontalLayout1,studentGrid);
    }

    }

