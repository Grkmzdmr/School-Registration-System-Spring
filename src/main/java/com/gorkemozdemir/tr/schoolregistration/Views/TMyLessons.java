package com.gorkemozdemir.tr.schoolregistration.Views;


import com.gorkemozdemir.tr.schoolregistration.models.*;


import com.gorkemozdemir.tr.schoolregistration.services.*;
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

@Route("/TMyLessons")
public class TMyLessons extends VerticalLayout {
    private String gelendonem;
    private int gelenid;
    private int donemid;
    private TermService termService;
    private TeacherService teacherService;
    private LessonService lessonService;
    private DepartmantService departmantService;



    Grid<Lesson> lessonGrid = new Grid<>(Lesson.class);

    HorizontalLayout horizontalLayout = new HorizontalLayout();
    HorizontalLayout horizontalLayout1 = new HorizontalLayout();
   public TMyLessons(TermService termService,TeacherService teacherService,LessonService lessonService){

       this.lessonService = lessonService;
       this.teacherService = teacherService;
       this.termService = termService;
       if (VaadinSession.getCurrent().getSession().getAttribute("LoggedInTeacherId") == null){
           UI.getCurrent().getPage().setLocation("/TeacherLogin");
       }else{
           gelenid = Integer.parseInt(VaadinSession.getCurrent().getSession().getAttribute("LoggedInTeacherId").toString());

       }

       List<String> termList = new ArrayList<>();

       ComboBox<String> comboBox = new ComboBox<>();
       comboBox.setLabel("Dönemler");
       termList.addAll(termService.donemad());
       comboBox.setItems(termList);
       Button button = new Button("Listele", VaadinIcon.LIST.create());
       button.addClickListener(buttonClickEvent -> {

           Teacher teacher = new Teacher();
           Term term = new Term();


           teacher.setEgitmenID(gelenid);

           gelendonem = comboBox.getValue();





           List<Lesson> lessons = new ArrayList<>();
           lessons.addAll(lessonService.findByTeacherList1AndTermmmm(teacher,termService.findByDonem(gelendonem)));
           lessonGrid.setItems(lessons);





       });

       lessonGrid.removeColumnByKey("dersID");
       lessonGrid.setSelectionMode(Grid.SelectionMode.NONE);
       lessonGrid.setColumns("dersAd","dersKredi","dersYariyil");
       horizontalLayout.add(new H2("Verdiğim Derslerin Listesi"));
       horizontalLayout.setWidth("100%");
       horizontalLayout.getStyle().set("border","0px solid #9E9E9E");
       horizontalLayout.setJustifyContentMode(JustifyContentMode.CENTER);


       horizontalLayout1.setWidth("100%");
       horizontalLayout1.getStyle().set("border","0px solid #9E9E9E");
       horizontalLayout1.add(comboBox);
       add(horizontalLayout,horizontalLayout1,button,lessonGrid);


   }

   }


