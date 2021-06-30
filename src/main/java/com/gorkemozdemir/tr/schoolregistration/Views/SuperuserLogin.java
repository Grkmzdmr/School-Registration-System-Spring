package com.gorkemozdemir.tr.schoolregistration.Views;

import com.gorkemozdemir.tr.schoolregistration.models.Student;
import com.gorkemozdemir.tr.schoolregistration.models.Superuser;
import com.gorkemozdemir.tr.schoolregistration.services.StudentService;
import com.gorkemozdemir.tr.schoolregistration.services.SuperuserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
@Route("SuperuserLogin")
public class SuperuserLogin extends VerticalLayout {
    private final SuperuserService superuserService;
    HorizontalLayout horizontalLayout2 = new HorizontalLayout();
    HorizontalLayout horizontalLayout = new HorizontalLayout();
    HorizontalLayout horizontalLayout1 = new HorizontalLayout();
    public SuperuserLogin(SuperuserService superuserService){

        this.superuserService = superuserService;
        H1 text = new H1("YÖNETİCİ GİRİŞ EKRANI");

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
            Superuser result = superuserService.login(loginEvent.getUsername(),loginEvent.getPassword());
            if (result.getSpID() != 0){
                VaadinSession.getCurrent().getSession().setAttribute("LoggedInSuperUserId",result.getSpID());
                UI.getCurrent().getPage().setLocation("/Superuser");


            }else{
                component.setError(true);
            }
        });
        horizontalLayout.add(component);
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        add(horizontalLayout1,horizontalLayout2,horizontalLayout);
    }
}


