package com.gorkemozdemir.tr.schoolregistration.Views;

import com.gorkemozdemir.tr.schoolregistration.models.Lesson;

import com.gorkemozdemir.tr.schoolregistration.services.LessonService;
import com.gorkemozdemir.tr.schoolregistration.services.TeacherService;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;

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

@Route("/LessonPage")
public class LessonPage extends VerticalLayout {


    private LessonService lessonService;
    Binder<Lesson> lessonBinder = new Binder<>();
    Integer itemIdForEdition2 = 0;
    Dialog dialog = new Dialog();
    Grid<Lesson> grid = new Grid<>(Lesson.class);
    HorizontalLayout horizontalLayout1 = new HorizontalLayout();

    public LessonPage(LessonService lessonService){


        this.lessonService = lessonService;


        Button btn = new Button("Yeni Ders Kaydı Aç", VaadinIcon.PLUS_CIRCLE.create());

        dialog.setHeight("700px");
        dialog.setWidth("500px");

        dialog.setModal(true);
        TextField txtDersadi = new TextField("Ders Adı Giriniz","İsim giriniz...");
        TextField txtYariyiladi = new TextField("Dersin Bulunduğu Yarıyılı Giriniz","Yarıyıl giriniz...");
        TextField txtKredi =new TextField("Dersin Kredisini Giriniz","Kredi giriniz...");


        lessonBinder.bind(txtDersadi,Lesson::getDersAd,Lesson::setDersAd);
        lessonBinder.bind(txtYariyiladi,Lesson::getDersYariyil,Lesson::setDersYariyil);

        lessonBinder.bind(txtKredi,Lesson::getDersKredi,Lesson::setDersKredi);


        FormLayout formLayout = new FormLayout();
        formLayout.add(txtDersadi,txtYariyiladi,txtKredi);
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSpacing(true);
        Button kaydet = new Button("Kaydet",VaadinIcon.PLUS.create());
        Button iptalEt = new Button("Iptal Et",VaadinIcon.CLOSE.create());

        iptalEt.addClickListener(buttonClickEvent -> {
            dialog.close();
        });


        kaydet.addClickListener(buttonClickEvent -> {
            Lesson lesson = new Lesson();
            try {
                lessonBinder.writeBean(lesson);

            } catch (ValidationException e) {
                e.printStackTrace();
            }
            lesson.setDersID(itemIdForEdition2);
            lessonService.save(lesson);

            refreshData();
            dialog.close();

        });
        horizontalLayout.add(kaydet,iptalEt);
        dialog.add(new H1("Yeni Ders Kaydı Oluştur"),formLayout,horizontalLayout);
        btn.addClickListener(buttonClickEvent -> {
            lessonBinder.readBean(new Lesson());
            dialog.open();
        });
        refreshData();
        //grid.removeColumnByKey("egitmenID");

        grid.setColumns("dersID","dersAd","dersYariyil","dersKredi");
        grid.addComponentColumn(item -> createRemoveButton(grid,item)).setHeader("İşlemler");

        Button button = new Button("Listele", VaadinIcon.LIST.create());
        horizontalLayout1.add(new H2("Ders Listesi"));
        horizontalLayout1.setWidth("100%");
        horizontalLayout1.getStyle().set("border","0px solid #9E9E9E");
        horizontalLayout1.setJustifyContentMode(JustifyContentMode.CENTER);
        add(horizontalLayout1,btn,grid);





        Button bt6 = new Button("Ana Sayfa",VaadinIcon.HOME.create(),event-> UI.getCurrent().navigate(""));

        bt6.setWidth("200px");
        bt6.setHeight("50px");










    }
    private void onDelete(ConfirmDialog.ConfirmEvent confirmEvent){

    }
    private HorizontalLayout createRemoveButton(Grid<Lesson> grid, Lesson item) {
        @SuppressWarnings("unchecked2")
        Button btnDelete = new Button("Sil");
        btnDelete.addClickListener(buttonClickEvent -> {
            ConfirmDialog dialog = new ConfirmDialog("Kayıt silinsin mi ?", "Bu eğitmeni sistemden silmek İstediğinize emin misiniz ?", "Sil",
                    confirmEvent -> {
                        lessonService.delete(item);
                        refreshData();
                    },
                    "Vazgeç", cancelEvent -> {
            });
            dialog.setConfirmButtonTheme("Error primary");
            dialog.open();
        });
        Button btnUpdate = new Button("Güncelle");
        btnUpdate.addClickListener(buttonClickEvent -> {
            itemIdForEdition2= item.getDersID();
            lessonBinder.readBean(new Lesson());
            dialog.open();


        });
        HorizontalLayout horizontalLayout5 = new HorizontalLayout();
        horizontalLayout5.add(btnDelete,btnUpdate);
        return horizontalLayout5;
    }

    private void refreshData(){
        List<Lesson> lessonList = new ArrayList<>();
        lessonList.addAll(lessonService.getAll());
        grid.setItems(lessonList);
    }
}
