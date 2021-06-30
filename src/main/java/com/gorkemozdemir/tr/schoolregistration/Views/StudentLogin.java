package com.gorkemozdemir.tr.schoolregistration.Views;

import com.gorkemozdemir.tr.schoolregistration.models.Student;
import com.gorkemozdemir.tr.schoolregistration.models.Teacher;
import com.gorkemozdemir.tr.schoolregistration.services.StudentService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route("/StudentLogin")
public class StudentLogin extends VerticalLayout {
    private final StudentService studentService;
    HorizontalLayout horizontalLayout2 = new HorizontalLayout();
    HorizontalLayout horizontalLayout = new HorizontalLayout();
    HorizontalLayout horizontalLayout1 = new HorizontalLayout();
    public StudentLogin(StudentService studentService){

        this.studentService = studentService;
        H1 text = new H1("ÖĞRENCİ GİRİŞ EKRANI");

        H2 text1 = new H2("LÜTFEN GİRİŞ YAPMAK İÇİN BİLGİLERİNİZİ GİRİNİZ");
        horizontalLayout1.setWidth("100%");
        horizontalLayout1.getStyle().set("border","0px solid #9E9E9E");
        horizontalLayout.setWidth("100%");
        horizontalLayout.getStyle().set("border","0px solid #9E9E9E");
        horizontalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        horizontalLayout1.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        horizontalLayout2.setWidth("100%");
        horizontalLayout2.getStyle().set("border","0px solid #9E9E9E");
        horizontalLayout2.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        horizontalLayout2.add(text1);
        horizontalLayout1.add(text);


        LoginForm component = new LoginForm();
        component.addLoginListener(loginEvent -> {
            Student result = studentService.login(loginEvent.getUsername(),loginEvent.getPassword());
            if (result.getOgrenciID() != 0){
                VaadinSession.getCurrent().getSession().setAttribute("LoggedInStudentId",result.getOgrenciID());
                UI.getCurrent().getPage().setLocation("/StudentsPage");


            }else{
                component.setError(true);
            }
        });
        horizontalLayout.add(component);
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        add(horizontalLayout1,horizontalLayout2,horizontalLayout);
    }
}
