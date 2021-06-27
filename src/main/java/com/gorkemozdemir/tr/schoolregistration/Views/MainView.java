package com.gorkemozdemir.tr.schoolregistration.Views;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.Component;
import jdk.jfr.ValueDescriptor;
import org.jboss.jandex.Main;

import javax.swing.*;
import java.awt.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;


@Route
public class MainView extends VerticalLayout {
    public MainView(){
        VerticalLayout verticalLayout = new VerticalLayout();
        HorizontalLayout layout = new HorizontalLayout();
        HorizontalLayout layout1 = new HorizontalLayout();
        HorizontalLayout layout2 = new HorizontalLayout();
        layout.setWidth("100%");
        layout.getStyle().set("border","0px solid #9E9E9E");
        layout1.setWidth("100%");
        layout1.getStyle().set("border","0px solid #9E9E9E");
        layout2.setWidth("100%");
        layout2.getStyle().set("border","0px solid #9E9E9E");
        Image image = new Image("https://edirnejethaber.com/wp-content/uploads/2020/04/trakyauniversitesibalkan.jpg", "Trakya Üniversite");
        image.setHeight("400px");
        image.setWidth("750px");
        Button btn = new Button("Öğrenci Giriş", VaadinIcon.USER.create(),event-> UI.getCurrent().navigate("StudentLogin"));
        Button bt1 = new Button("Eğitmen Giriş",VaadinIcon.GLASSES.create(),event-> UI.getCurrent().navigate("TeacherLogin"));
        Button bt3 = new Button("Yönetici Giriş",VaadinIcon.EYE.create(),event-> UI.getCurrent().navigate("SuperuserLogin"));
        H1 t = new H1("OKUL ");
        H1 t1 = new H1("SİSTEMİNE HOŞ GELDİNİZ");



        layout.add(bt1,btn,bt3);

        layout.setPadding(false);
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.setJustifyContentMode(JustifyContentMode.CENTER);

        layout1.add(t,t1);
        layout1.setPadding(false);
        layout1.setMargin(true);
        layout1.setSpacing(true);
        layout1.setJustifyContentMode(JustifyContentMode.CENTER);

        layout2.add(image);
        layout2.setJustifyContentMode(JustifyContentMode.CENTER);

        verticalLayout.add(layout,layout1,layout2);
        add(verticalLayout);
    }
}
