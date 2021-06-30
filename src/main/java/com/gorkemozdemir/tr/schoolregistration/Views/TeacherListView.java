package com.gorkemozdemir.tr.schoolregistration.Views;


import com.gorkemozdemir.tr.schoolregistration.models.Student;
import com.gorkemozdemir.tr.schoolregistration.models.Teacher;
import com.gorkemozdemir.tr.schoolregistration.services.StudentService;
import com.gorkemozdemir.tr.schoolregistration.services.TeacherService;
import com.gorkemozdemir.tr.schoolregistration.services.TermService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;


import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route("/Teachers")
public class TeacherListView extends VerticalLayout {
    private TermService termService;
    private TeacherService teacherService;
    Dialog dialog = new Dialog();
    Grid<Teacher>grid = new Grid<>(Teacher.class);
    Binder<Teacher> teacherBinder = new Binder<>();
    Integer itemIdForEdition = 0;
    HorizontalLayout horizontalLayout1 = new HorizontalLayout();

    public TeacherListView(TeacherService teacherService,TermService termService){


        this.termService = termService;

        this.teacherService = teacherService;

        Button btn = new Button("Yeni Eğitmen Kaydı Aç", VaadinIcon.PLUS_CIRCLE.create());

        dialog.setHeight("700px");
        dialog.setWidth("500px");

        dialog.setModal(true);
        TextField txtEğitmenAdi = new TextField("Eğitmen Adı Giriniz","İsim giriniz...");
        TextField txtEğitmenSoyadi = new TextField("Eğitmen Soyadı Giriniz","Soyisim giriniz...");

        TextField txtEğitmenMail =new TextField("Eğitmen Maili Giriniz","Mail adresi giriniz...");
        TextField txtEğitmenPassword = new TextField("Eğitmen Şifresi Giriniz","Şifre giriniz...");

        teacherBinder.bind(txtEğitmenAdi,Teacher::getEgitmenAd,Teacher::setEgitmenAd);
        teacherBinder.bind(txtEğitmenSoyadi,Teacher::getEgitmenSoyad,Teacher::setEgitmenSoyad);

        teacherBinder.bind(txtEğitmenMail,Teacher::getEmail,Teacher::setEmail);
        teacherBinder.bind(txtEğitmenPassword,Teacher::getPassword,Teacher::setPassword);

        FormLayout formLayout = new FormLayout();
        formLayout.add(txtEğitmenAdi,txtEğitmenSoyadi,txtEğitmenMail,txtEğitmenPassword);
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSpacing(true);
        Button kaydet = new Button("Kaydet",VaadinIcon.PLUS.create());
        Button iptalEt = new Button("Iptal Et",VaadinIcon.CLOSE.create());



        iptalEt.addClickListener(buttonClickEvent -> {
            dialog.close();
        });


        kaydet.addClickListener(buttonClickEvent -> {
            Teacher teacher = new Teacher();
            try {
                teacherBinder.writeBean(teacher);

            } catch (ValidationException e) {
                e.printStackTrace();
            }
            teacher.setEgitmenID(itemIdForEdition);
            teacherService.save(teacher);

            refreshData();
            dialog.close();

        });
        horizontalLayout.add(kaydet,iptalEt);
        dialog.add(new H1("Yeni Eğitmen Kaydı Oluştur"),formLayout,horizontalLayout);
        btn.addClickListener(buttonClickEvent -> {
            teacherBinder.readBean(new Teacher());
            dialog.open();
        });
        refreshData();
        grid.removeColumnByKey("egitmenID");

        grid.setColumns("egitmenAd","egitmenSoyad","email","password");
        grid.addComponentColumn(item -> createRemoveButton(grid,item)).setHeader("İşlemler");





        horizontalLayout1.add(new H2("Eğitmen Listesi"));
        horizontalLayout1.setWidth("100%");
        horizontalLayout1.getStyle().set("border","0px solid #9E9E9E");
        horizontalLayout1.setJustifyContentMode(JustifyContentMode.CENTER);
        add(horizontalLayout1,btn,grid);





        Button bt6 = new Button("Ana Sayfa",VaadinIcon.HOME.create(),event-> UI.getCurrent().navigate(""));

        bt6.setWidth("200px");
        bt6.setHeight("50px");










    }
    private void refreshData(){
        List<Teacher> teacherList = new ArrayList<>();
        teacherList.addAll(teacherService.getAll());
        grid.setItems(teacherList);
    }
    private void onDelete(ConfirmDialog.ConfirmEvent confirmEvent){

    }
    private HorizontalLayout createRemoveButton(Grid<Teacher> grid,Teacher item) {
        @SuppressWarnings("unchecked")
        Button btnDelete = new Button("Sil");
        btnDelete.addClickListener(buttonClickEvent -> {
            ConfirmDialog dialog = new ConfirmDialog("Kayıt silinsin mi ?", "Bu eğitmeni sistemden silmek İstediğinize emin misiniz ?", "Sil",
                    confirmEvent -> {
                        teacherService.delete(item);
                        refreshData();
                    },
                    "Vazgeç", cancelEvent -> {
            });
            dialog.setConfirmButtonTheme("Error primary");
            dialog.open();
        });
        Button btnUpdate = new Button("Güncelle");
        btnUpdate.addClickListener(buttonClickEvent -> {
            itemIdForEdition = item.getEgitmenID();
            teacherBinder.readBean(new Teacher());
            dialog.open();


        });
        HorizontalLayout horizontalLayout3 = new HorizontalLayout();
        horizontalLayout3.add(btnDelete,btnUpdate);
        return horizontalLayout3;
    }



}



