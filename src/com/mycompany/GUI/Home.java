/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author LENOVO
 */
public class Home extends Form {
    public Home(Resources res){
        this.getAllStyles().setBgImage(res.getImage("logoo.jpg"));
        setTitle("Home");
        setLayout(BoxLayout.y());
        add(new Label("Choisir une option "));
        Button b1 = new Button("Ajouter une catégorie");
        Button b2 = new Button("Liste des catégories");
        
        b1.addActionListener(l -> new Ajoutcategorie(this,res).show());
        b2.addActionListener(l -> new ListCategorie(this,res).show());
        addAll(b1,b2);
                
    }
}
