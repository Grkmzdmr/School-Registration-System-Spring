package com.gorkemozdemir.tr.schoolregistration.Views;

import com.gorkemozdemir.tr.schoolregistration.services.SuperuserService;
import com.gorkemozdemir.tr.schoolregistration.services.TeacherService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("/TeachersPage")
public class TeachersPage extends VerticalLayout {
    private TeacherService teacherService;
    HorizontalLayout horizontalLayout2 = new HorizontalLayout();
    HorizontalLayout horizontalLayout = new HorizontalLayout();
    HorizontalLayout horizontalLayout1 = new HorizontalLayout();

    public TeachersPage(TeacherService teacherService){

        this.teacherService = teacherService;
        horizontalLayout1.setJustifyContentMode(JustifyContentMode.CENTER);
        horizontalLayout2.setWidth("100%");
        horizontalLayout2.getStyle().set("border","0px solid #9E9E9E");
        horizontalLayout2.setJustifyContentMode(JustifyContentMode.CENTER);
        horizontalLayout.setWidth("100%");
        horizontalLayout.getStyle().set("border","0px solid #9E9E9E");
        horizontalLayout.setJustifyContentMode(JustifyContentMode.CENTER);

        H1 text = new H1("Eğitmen Sistemi");
        H2 text1 = new H2("Lütfen Yapmak İstediğiniz İşlemi Seçiniz...");
        horizontalLayout1.setWidth("100%");
        horizontalLayout1.getStyle().set("border","0px solid #9E9E9E");
        horizontalLayout2.add(text1);
        horizontalLayout1.add(text);
        Button bt6 = new Button("Öğrencilerim", VaadinIcon.USER.create(), event-> UI.getCurrent().getPage().setLocation("/MyStudents"));
        Button btn1 = new Button("Verdiğim Dersler",VaadinIcon.BOOK.create(),event-> UI.getCurrent().getPage().setLocation("/TMyLessons"));

        horizontalLayout.add(bt6,btn1);
        add(horizontalLayout1,horizontalLayout2,horizontalLayout);


    }

}
