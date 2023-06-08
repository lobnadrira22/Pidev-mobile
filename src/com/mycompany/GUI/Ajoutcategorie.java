/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.CategorieLocation;
import com.mycompany.services.ServiceCategorieLocation;

/**
 *
 * @author LENOVO
 */
public class Ajoutcategorie extends Form {
    

    public Ajoutcategorie(Form previous, Resources res){
                this.getAllStyles().setBgImage(res.getImage("logoo.jpg"));

        
        setTitle("Ajout categorie");
        setLayout(BoxLayout.y());
        TextField tfnom = new TextField("","Nom de la catégorie");
        TextField tfdescription = new TextField("","Description");
        
        Button btnadd =new Button("Ajouter");
        
        
        btnadd.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent evt) {
                
               
                if((tfnom.getText().length()==0)||(tfdescription.getText().length()==0)){
                    Dialog.show("Alert","Veuillez remplir tous les champs ","ok",null);
                }
                else{
                    CategorieLocation ct = new CategorieLocation(tfnom.getText(), tfdescription.getText());
                   
                   // if(ServiceAcademie.getinstance().ajoutAcademie(ac1))
                       ServiceCategorieLocation.getinstance().ajoutCategorie(ct);
                        Dialog.show("Alert","La catégorie a été ajoutée avec succès ","ok",null);
                  
                   
                }
            }

      
        });
        addAll(tfnom,tfdescription,btnadd);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_LEFT, ev->previous.show());
     
        
    }

    
}
