package com.gorkemozdemir.tr.schoolregistration.Views;

import com.gorkemozdemir.tr.schoolregistration.models.Departmant;
import com.gorkemozdemir.tr.schoolregistration.models.Student;
import com.gorkemozdemir.tr.schoolregistration.models.Teacher;
import com.gorkemozdemir.tr.schoolregistration.models.Term;
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

@Route("/Terms")
public class TermsView extends VerticalLayout {
    private TermService termService;
    Grid<Term> grid = new Grid<>(Term.class);
    HorizontalLayout horizontalLayout1 = new HorizontalLayout();
    Binder<Term> termBinder = new Binder<>();
    Dialog dialog = new Dialog();

    Integer itemIdForEdition5 = 0;

    public TermsView(TermService termService) {
        this.termService = termService;



        Button btn = new Button("Yeni Dönem Kaydı Aç", VaadinIcon.PLUS_CIRCLE.create());

        dialog.setHeight("700px");
        dialog.setWidth("500px");

        dialog.setModal(true);
        TextField txtDonemAdi = new TextField("Dönem Yılı Giriniz","Yıl giriniz...");


        termBinder.bind(txtDonemAdi,Term::getDonem,Term::setDonem);


        FormLayout formLayout = new FormLayout();
        formLayout.add(txtDonemAdi);
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSpacing(true);
        Button kaydet = new Button("Kaydet",VaadinIcon.PLUS.create());
        Button iptalEt = new Button("Iptal Et",VaadinIcon.CLOSE.create());

        iptalEt.addClickListener(buttonClickEvent -> {
            dialog.close();
        });


        kaydet.addClickListener(buttonClickEvent -> {
            Term term = new Term();
            try {
                termBinder.writeBean(term);

            } catch (ValidationException e) {
                e.printStackTrace();
            }
            term.setDonemId(itemIdForEdition5);
            termService.save(term);

            refreshData();
            dialog.close();

        });
        horizontalLayout.add(kaydet,iptalEt);
        dialog.add(new H1("Yeni Dönem Kaydı Oluştur"),formLayout,horizontalLayout);
        btn.addClickListener(buttonClickEvent -> {
            termBinder.readBean(new Term());
            dialog.open();
        });
        refreshData();
        //grid.removeColumnByKey("donemId");
        grid.setSelectionMode(Grid.SelectionMode.NONE);
        grid.setColumns("donemId","donem");
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
        List<Term> termList = new ArrayList<>();
        termList.addAll(termService.getAll());
        grid.setItems(termList);
    }

    private void onDelete(ConfirmDialog.ConfirmEvent confirmEvent){

    }
    private HorizontalLayout createRemoveButton(Grid<Term> grid, Term item) {
        @SuppressWarnings("unchecked4")
        Button btnDelete = new Button("Sil");
        btnDelete.addClickListener(buttonClickEvent -> {
            ConfirmDialog dialog = new ConfirmDialog("Kayıt silinsin mi ?", "Bu dönemi sistemden silmek İstediğinize emin misiniz ?", "Sil",
                    confirmEvent -> {
                        termService.delete(item);
                        refreshData();
                    },
                    "Vazgeç", cancelEvent -> {
            });
            dialog.setConfirmButtonTheme("Error primary");
            dialog.open();
        });
        Button btnUpdate = new Button("Güncelle");
        btnUpdate.addClickListener(buttonClickEvent -> {
            itemIdForEdition5= item.getDonemId();
            termBinder.readBean(new Term());
            dialog.open();


        });
        HorizontalLayout horizontalLayout5 = new HorizontalLayout();
        horizontalLayout5.add(btnDelete,btnUpdate);
        return horizontalLayout5;
    }

}



