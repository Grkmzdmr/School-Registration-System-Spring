package com.gorkemozdemir.tr.schoolregistration.Views;

import com.gorkemozdemir.tr.schoolregistration.models.Student;
import com.gorkemozdemir.tr.schoolregistration.models.Teacher;
import com.gorkemozdemir.tr.schoolregistration.services.StudentService;
import com.gorkemozdemir.tr.schoolregistration.services.TermService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
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


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Route("/Students")
public class StudentListView extends VerticalLayout {
    private TermService termService;
    private  StudentService studentService;
    Dialog dialog1 = new Dialog();
    Binder<Student> studentBinder = new Binder<>();
    Integer itemIdForEdition1 = 0;
    Grid<Student>grid = new Grid<>(Student.class);
    HorizontalLayout horizontalLayout1 = new HorizontalLayout();

    public StudentListView(StudentService studentService,TermService termService){
        this.termService=termService;
        this.studentService = studentService;

        Button btn = new Button("Yeni Öğrenci Kaydı Aç", VaadinIcon.PLUS_CIRCLE.create());

        dialog1.setHeight("700px");
        dialog1.setWidth("500px");

        dialog1.setModal(true);
        TextField txtOgrenciAdi = new TextField("Öğrenci Adı Giriniz","İsim giriniz...");
        TextField txtOgrenciSoyadi = new TextField("Öğrenci Soyadı Giriniz","Soyisim giriniz...");
        TextField txtOgrenciNo = new TextField("Öğrenci Numarası Giriniz","Numara giriniz...");
        TextField txtOgrenciMail =new TextField("Öğrenci Maili Giriniz","Mail adresi giriniz...");
        TextField txtOgrenciPassword = new TextField("Öğrenci Şifresi Giriniz","Şifre giriniz...");

        studentBinder.bind(txtOgrenciAdi,Student::getOgrenciAd,Student::setOgrenciAd);
        studentBinder.bind(txtOgrenciSoyadi,Student::getOgrenciAd,Student::setOgrenciSoyad);
        studentBinder.bind(txtOgrenciNo,Student::getOgrenciNo,Student::setOgrenciNo);
        studentBinder.bind(txtOgrenciMail,Student::getEmail,Student::setEmail);
        studentBinder.bind(txtOgrenciPassword,Student::getPassword,Student::setPassword);

        FormLayout formLayout = new FormLayout();
        formLayout.add(txtOgrenciAdi,txtOgrenciSoyadi,txtOgrenciNo,txtOgrenciMail,txtOgrenciPassword);
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSpacing(true);
        Button kaydet = new Button("Kaydet",VaadinIcon.PLUS.create());
        Button iptalEt = new Button("Iptal Et",VaadinIcon.CLOSE.create());
        List<String> termList = new ArrayList<>();





        iptalEt.addClickListener(buttonClickEvent -> {
            dialog1.close();
        });


        kaydet.addClickListener(buttonClickEvent -> {
            Student student = new Student();
            try {
                studentBinder.writeBean(student);

            } catch (ValidationException e) {
                e.printStackTrace();
            }
            student.setOgrenciID(itemIdForEdition1);
            studentService.save(student);

            refreshData();
            dialog1.close();

        });
        horizontalLayout.add(kaydet,iptalEt);
        dialog1.add(new H1("Yeni Öğrenci Kaydı Oluştur"),formLayout,horizontalLayout);
        btn.addClickListener(buttonClickEvent -> {
            studentBinder.readBean(new Student());
            dialog1.open();
        });
        refreshData();
        grid.removeColumnByKey("ogrenciID");
        grid.setSelectionMode(Grid.SelectionMode.NONE);
        grid.setColumns("ogrenciAd","ogrenciSoyad","ogrenciNo","email","password");
        grid.addComponentColumn(item -> createRemoveButton(grid,item)).setHeader("İşlemler");
        horizontalLayout1.add(new H2("Öğrenci Listesi"));
        horizontalLayout1.setWidth("100%");
        horizontalLayout1.getStyle().set("border","0px solid #9E9E9E");
        horizontalLayout1.setJustifyContentMode(JustifyContentMode.CENTER);
        add(horizontalLayout1,btn,grid);





        Button bt6 = new Button("Ana Sayfa",VaadinIcon.HOME.create(),event-> UI.getCurrent().navigate(""));

        bt6.setWidth("200px");
        bt6.setHeight("50px");

    }
    private void refreshData(){
        List<Student> studentlist = new ArrayList<>();
        studentlist.addAll(studentService.getAll());
        grid.setItems(studentlist);
    }
    private void onDelete(ConfirmDialog.ConfirmEvent confirmEvent){

    }
    private HorizontalLayout createRemoveButton(Grid<Student> grid,Student item) {
        @SuppressWarnings("unchecked1")
        Button btnDelete = new Button("Sil");
        btnDelete.addClickListener(buttonClickEvent -> {
            ConfirmDialog dialog = new ConfirmDialog("Kayıt silinsin mi ?", "Bu öğrenciyi sistemden silmek İstediğinize emin misiniz ?", "Sil",
                    confirmEvent -> {
                        studentService.delete(item);
                        refreshData();
                    },
                    "Vazgeç", cancelEvent -> {
            });
            dialog.setConfirmButtonTheme("Error primary");
            dialog.open();
        });
        Button btnUpdate = new Button("Güncelle");
        btnUpdate.addClickListener(buttonClickEvent -> {
             itemIdForEdition1= item.getOgrenciID();
            studentBinder.readBean(new Student());
            dialog1.open();


        });
        HorizontalLayout horizontalLayout5 = new HorizontalLayout();
        horizontalLayout5.add(btnDelete,btnUpdate);
        return horizontalLayout5;
    }


}
