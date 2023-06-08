/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.CategorieLocation;
import com.mycompany.services.ServiceCategorieLocation;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class ListCategorie extends Form{
    public ListCategorie(Form previous, Resources res){
                this.getAllStyles().setBgImage(res.getImage("logoo.jpg"));

        setTitle("Liste des categories");
        setLayout(BoxLayout.y());
        
        ArrayList<CategorieLocation> catgs = ServiceCategorieLocation.getinstance().getAllCategories();
        for (CategorieLocation ca :catgs){
                
          AddElement(ca);
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_KEYBOARD_ARROW_LEFT, ev->previous.show());

    }
    
   
    public void AddElement(CategorieLocation ca){
    
       Label ll = new Label(ca.getNom());
       Label l2 = new Label(ca.getDescription());
      
        add(ll);
        add(l2);
      


    }
    
}
