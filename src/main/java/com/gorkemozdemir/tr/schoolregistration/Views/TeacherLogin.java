package com.gorkemozdemir.tr.schoolregistration.Views;

import com.gorkemozdemir.tr.schoolregistration.models.Teacher;
import com.gorkemozdemir.tr.schoolregistration.services.TeacherService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import javax.validation.constraints.Null;

@Route("/TeacherLogin")
public class TeacherLogin extends VerticalLayout {
    private final TeacherService teacherService;
    HorizontalLayout horizontalLayout2 = new HorizontalLayout();
    HorizontalLayout horizontalLayout = new HorizontalLayout();
    HorizontalLayout horizontalLayout1 = new HorizontalLayout();
    public TeacherLogin(TeacherService teacherService){

        this.teacherService = teacherService;
        H1 text = new H1("EĞİTMEN GİRİŞ EKRANI");
        H2 text1 = new H2("LÜTFEN GİRİŞ YAPMAK İÇİN BİLGİLERİNİZİ GİRİNİZ");
        horizontalLayout1.setWidth("100%");
        horizontalLayout1.getStyle().set("border","0px solid #9E9E9E");
        horizontalLayout.setWidth("100%");
        horizontalLayout.getStyle().set("border","0px solid #9E9E9E");
        horizontalLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        horizontalLayout1.setJustifyContentMode(JustifyContentMode.CENTER);
        horizontalLayout2.setWidth("100%");
        horizontalLayout2.getStyle().set("border","0px solid #9E9E9E");
        horizontalLayout2.setJustifyContentMode(JustifyContentMode.CENTER);
        horizontalLayout2.add(text1);
        horizontalLayout1.add(text);

        LoginForm component = new LoginForm();
        component.addLoginListener(loginEvent -> {
            Teacher result = teacherService.login(loginEvent.getUsername(),loginEvent.getPassword());
            if (result.getEgitmenID() != 0){
                VaadinSession.getCurrent().getSession().setAttribute("LoggedInTeacherId",result.getEgitmenID());
                UI.getCurrent().getPage().setLocation("/TeachersPage");


            }else{
                component.setError(true);
            }
        });
        horizontalLayout.add(component);
        setJustifyContentMode(JustifyContentMode.CENTER);
        add(horizontalLayout1,horizontalLayout2,horizontalLayout);
    }
}
