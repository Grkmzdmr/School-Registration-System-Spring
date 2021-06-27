package com.gorkemozdemir.tr.schoolregistration.Views;

import com.gorkemozdemir.tr.schoolregistration.models.Departmant;
import com.gorkemozdemir.tr.schoolregistration.models.Lesson;
import com.gorkemozdemir.tr.schoolregistration.models.Term;
import com.gorkemozdemir.tr.schoolregistration.services.DepartmantService;
import com.gorkemozdemir.tr.schoolregistration.services.TermService;
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

@Route("/DepartmantPage")
public class DepartmantPage extends VerticalLayout {
    private DepartmantService departmantService;
    Dialog dialog = new Dialog();
    Integer itemIdForEdition4 = 0;
    Binder<Departmant> departmantBinder = new Binder<>();
    Grid<Departmant> grid = new Grid<>(Departmant.class);
    HorizontalLayout horizontalLayout1 = new HorizontalLayout();

    public DepartmantPage(DepartmantService departmantService) {
        this.departmantService = departmantService;



        Button btn = new Button("Yeni Bölüm Kaydı Aç", VaadinIcon.PLUS_CIRCLE.create());

        dialog.setHeight("700px");
        dialog.setWidth("500px");

        dialog.setModal(true);
        TextField txtBolumAdi = new TextField("Bölüm Adı Giriniz","Bölüm giriniz...");
        TextField txtBolumYariyil = new TextField("Bölüm Yarıyıl Sayısını Giriniz","Yarıyıl sayısı giriniz...");



        departmantBinder.bind(txtBolumAdi,Departmant::getBolumAd,Departmant::setBolumAd);
        departmantBinder.bind(txtBolumYariyil,Departmant::getBolumYariyil,Departmant::setBolumYariyil);



        FormLayout formLayout = new FormLayout();
        formLayout.add(txtBolumAdi,txtBolumYariyil);
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSpacing(true);
        Button kaydet = new Button("Kaydet",VaadinIcon.PLUS.create());
        Button iptalEt = new Button("Iptal Et",VaadinIcon.CLOSE.create());

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
            departmant.setBolumID(itemIdForEdition4);
            departmantService.save(departmant);

            refreshData();
            dialog.close();

        });
        horizontalLayout.add(kaydet,iptalEt);
        dialog.add(new H1("Yeni Bölüm Kaydı Oluştur"),formLayout,horizontalLayout);
        btn.addClickListener(buttonClickEvent -> {
            departmantBinder.readBean(new Departmant());
            dialog.open();
        });
        refreshData();
        grid.removeColumnByKey("bolumID");
        grid.setSelectionMode(Grid.SelectionMode.NONE);
        grid.setColumns("bolumAd","bolumYariyil");
        grid.addComponentColumn(item -> createRemoveButton(grid,item)).setHeader("İşlemler");
        horizontalLayout1.add(new H2("Bölüm Listesi"));
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
    private HorizontalLayout createRemoveButton(Grid<Departmant> grid, Departmant item) {
        @SuppressWarnings("unchecked3")
        Button btnDelete = new Button("Sil");
        btnDelete.addClickListener(buttonClickEvent -> {
            ConfirmDialog dialog = new ConfirmDialog("Kayıt silinsin mi ?", "Bu bölümü sistemden silmek İstediğinize emin misiniz ?", "Sil",
                    confirmEvent -> {
                        departmantService.delete(item);
                        refreshData();
                    },
                    "Vazgeç", cancelEvent -> {
            });
            dialog.setConfirmButtonTheme("Error primary");
            dialog.open();
        });
        Button btnUpdate = new Button("Güncelle");
        btnUpdate.addClickListener(buttonClickEvent -> {
            itemIdForEdition4= item.getBolumID();
            departmantBinder.readBean(new Departmant());
            dialog.open();


        });
        HorizontalLayout horizontalLayout5 = new HorizontalLayout();
        horizontalLayout5.add(btnDelete,btnUpdate);
        return horizontalLayout5;
    }

    private void refreshData(){
        List<Departmant> departmants = new ArrayList<>();
        departmants.addAll(departmantService.getAll());
        grid.setItems(departmants);
    }

}
