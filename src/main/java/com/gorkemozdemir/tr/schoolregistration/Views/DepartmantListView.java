package com.gorkemozdemir.tr.schoolregistration.Views;

import com.gorkemozdemir.tr.schoolregistration.models.Departmant;
import com.gorkemozdemir.tr.schoolregistration.models.Lesson;
import com.gorkemozdemir.tr.schoolregistration.models.Teacher;
import com.gorkemozdemir.tr.schoolregistration.services.DepartmantService;
import com.gorkemozdemir.tr.schoolregistration.services.TeacherService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;
@Route("/Departmants")
public class DepartmantListView extends VerticalLayout {
    private DepartmantService departmantService;
    Grid<Departmant> grid = new Grid<>(Departmant.class);
    public DepartmantListView(DepartmantService departmantService){
        this.departmantService = departmantService;



        Binder<Departmant> departmantBinder = new Binder<>();
        Button btn = new Button("Bölüm Ekle", VaadinIcon.USER.create());
        Button bt6 = new Button("Ana Sayfa",VaadinIcon.HOME.create(),event-> UI.getCurrent().navigate(""));
        btn.setWidth("200px");
        btn.setHeight("50px");
        bt6.setWidth("200px");
        bt6.setHeight("50px");

        Dialog dialog = new Dialog();
        dialog.setModal(true);
        dialog.setWidth("500px");
        dialog.setHeight("700px");
        Button bt5 = new Button("Sil",VaadinIcon.CLOSE.create());
        TextField txtBölümAd = new TextField("Bölüm Adı","Bölüm adı giriniz...");
        TextField txtBölümYarıyıl = new TextField("Bölüm Yarıyıl Sayısı","Yarıyıl sayısı giriniz...");
        TextField txtDersID = new TextField("Ders id giriniz","id giriniz...");





        departmantBinder.bind(txtBölümAd,Departmant::getBolumAd,Departmant::setBolumAd);
        departmantBinder.bind(txtBölümYarıyıl,Departmant::getBolumYariyil,Departmant::setBolumYariyil);



        FormLayout formLayout = new FormLayout();
        formLayout.add(txtBölümAd,txtBölümYarıyıl);
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSpacing(true);
        Button kaydet = new Button("Bölüm Kaydet",VaadinIcon.PLUS.create());
        Button iptalEt = new Button("İşlemi İptal Et",VaadinIcon.CLOSE.create());

        iptalEt.addClickListener(buttonClickEvent -> {
            dialog.close();
        });


        kaydet.addClickListener(buttonClickEvent -> {
            Departmant departmant = new Departmant();
            try {
                departmantBinder.writeBean(departmant);

            } catch (ValidationException e) {
                e.printStackTrace();
            }
            departmantService.save(departmant);

            refreshData();
            dialog.close();

        });
        horizontalLayout.add(kaydet,iptalEt);
        dialog.add(new H1("Yeni Bölüm Kaydı"),formLayout,horizontalLayout);
        btn.addClickListener(buttonClickEvent -> {
            departmantBinder.readBean(new Departmant());
            dialog.open();
        });
        refreshData();
        grid.removeColumnByKey("bolumID");

        grid.setColumns("bolumAd","bolumYariyil");

        HorizontalLayout layout = new HorizontalLayout();
        layout.setWidth("100%");
        layout.getStyle().set("border","0px solid #9E9E9E");
        H1 a = new H1("Bölüm Listesi");

        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layout.add(a);

        add(bt6,layout,btn,grid);



    }
    private void refreshData(){
        List<Departmant> departmantList = new ArrayList<>();
        departmantList.addAll(departmantService.getAll());
        grid.setItems(departmantList);
    }

}
