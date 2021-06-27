package com.gorkemozdemir.tr.schoolregistration.Views;
import com.gorkemozdemir.tr.schoolregistration.models.Superuser;
import com.gorkemozdemir.tr.schoolregistration.services.SuperuserService;
import com.gorkemozdemir.tr.schoolregistration.services.TeacherService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;

@Route("/Superuser")
public class SuperuserListView extends VerticalLayout {
    private SuperuserService superuserService;
    HorizontalLayout horizontalLayout2 = new HorizontalLayout();
    HorizontalLayout horizontalLayout = new HorizontalLayout();
    HorizontalLayout horizontalLayout1 = new HorizontalLayout();

    public SuperuserListView(SuperuserService superuserService){

        this.superuserService = superuserService;
        horizontalLayout1.setJustifyContentMode(JustifyContentMode.CENTER);
        horizontalLayout2.setWidth("100%");
        horizontalLayout2.getStyle().set("border","0px solid #9E9E9E");
        horizontalLayout2.setJustifyContentMode(JustifyContentMode.CENTER);
        horizontalLayout.setWidth("100%");
        horizontalLayout.getStyle().set("border","0px solid #9E9E9E");
        horizontalLayout.setJustifyContentMode(JustifyContentMode.CENTER);

        H1 text = new H1("Yönetici Sistemi");
        H2 text1 = new H2("Lütfen Yapmak İstediğiniz İşlemi Seçiniz...");
        horizontalLayout1.setWidth("100%");
        horizontalLayout1.getStyle().set("border","0px solid #9E9E9E");
        horizontalLayout2.add(text1);
        horizontalLayout1.add(text);
        Button bt6 = new Button("Eğitmenler",VaadinIcon.USER.create(),event-> UI.getCurrent().getPage().setLocation("/Teachers"));
        Button btn1 = new Button("Öğrenciler",VaadinIcon.BOOK.create(),event-> UI.getCurrent().getPage().setLocation("/Students"));
        Button btn5 = new Button("Bölümler",VaadinIcon.HOME.create(),event-> UI.getCurrent().getPage().setLocation("/DepartmantPage"));
        Button btn4 = new Button("Dersler",VaadinIcon.BOOKMARK.create(),event-> UI.getCurrent().getPage().setLocation("/LessonPage"));
        Button btn6 = new Button("Dönemler",VaadinIcon.DATE_INPUT.create(),event-> UI.getCurrent().getPage().setLocation("/Terms"));
        Button btn7 = new Button("Ders Kaydı Raporu",VaadinIcon.RECORDS.create(),event-> UI.getCurrent().getPage().setLocation("/ReportPage"));
        horizontalLayout.add(bt6,btn1,btn4,btn5,btn6,btn7);
        add(horizontalLayout1,horizontalLayout2,horizontalLayout);


    }




    }


