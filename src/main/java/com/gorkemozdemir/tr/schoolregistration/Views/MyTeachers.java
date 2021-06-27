package com.gorkemozdemir.tr.schoolregistration.Views;

import com.gorkemozdemir.tr.schoolregistration.models.Student;
import com.gorkemozdemir.tr.schoolregistration.models.Teacher;

import com.gorkemozdemir.tr.schoolregistration.models.Term;
import com.gorkemozdemir.tr.schoolregistration.services.TeacherService;
import com.gorkemozdemir.tr.schoolregistration.services.TermService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;
@Route("/MyTeachers")
public class MyTeachers extends VerticalLayout {
    private TeacherService teacherService;
    private TermService termService;
    Grid<Term> grid = new Grid<>(Term.class);
    Grid<Teacher> teacherGrid = new Grid<>(Teacher.class);
    HorizontalLayout horizontalLayout = new HorizontalLayout();
    public MyTeachers(TeacherService teacherService,TermService termService){
        this.termService = termService;
        this.teacherService = teacherService;
        refreshData();
        teacherGrid.removeColumnByKey("egitmenID");
        teacherGrid.setSelectionMode(Grid.SelectionMode.NONE);
        List<String> termList = new ArrayList<>();
        termList.addAll(termService.donemad());
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setLabel("Dönemler");

        comboBox.setItems(termList);
        Button button = new Button("Listele", VaadinIcon.LIST.create());
        teacherGrid.setColumns("egitmenAd","egitmenSoyad","email","password");
        horizontalLayout.add(new H2("Eğitmen Listesi"));
        horizontalLayout.setWidth("100%");
        horizontalLayout.getStyle().set("border","0px solid #9E9E9E");
        horizontalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        add(horizontalLayout,comboBox,button,teacherGrid);


    }
    private void refreshData(){
        List<Teacher> teachers = new ArrayList<>();
        teachers.addAll(teacherService.getAll());
        teacherGrid.setItems(teachers);
    }
}
