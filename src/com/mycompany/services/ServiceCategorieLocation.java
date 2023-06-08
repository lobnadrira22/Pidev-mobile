/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.CategorieLocation;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author LENOVO
 */
public class ServiceCategorieLocation {
     // creer un attribut de type de la classe en question (static)
    public static ServiceCategorieLocation instance = null;

  
    ArrayList<CategorieLocation> categorie ;
     private ConnectionRequest req;
     
      public static ServiceCategorieLocation getinstance(){
        if(instance == null){
            instance = new ServiceCategorieLocation();    
        }
        return instance;
    }
     public ServiceCategorieLocation(){
         req=new ConnectionRequest();
     }
     //ajout
     public void ajoutCategorie(CategorieLocation cat){
         
           String url = Statics.BASE_URL+"/addcategorieJSON/new?nom="+cat.getNom()+"&description="+cat.getDescription();
           req.setUrl(url);
           req.addResponseListener((e) -> {
               String str=new String(req.getResponseData());
               System.out.println("data == "+str);
           });
           //ajouter la requete a la file d'attente 
           //addtoqueueandwait : attend la reponse
           NetworkManager.getInstance().addToQueueAndWait(req);
           
     }
     
     
     
     
        //Parse
      public ArrayList<CategorieLocation> parsecategorie(String jsonText) {
        try {
            categorie = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> categorieListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            System.out.println("map="+categorieListJson);

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) categorieListJson.get("root");
            System.out.println("map="+list);


            for (Map<String, Object> obj : list) {
                CategorieLocation ca = new CategorieLocation();
                float id = Float.parseFloat(obj.get("id").toString());

                System.out.println(id);

                ca.setId((int)id);

                ca.setNom(obj.get("nom").toString());
                //ca.setDescription(obj.get("description").toString());

                categorie.add(ca);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return categorie;
    }
 
       //affichage
       public ArrayList<CategorieLocation> getAllCategories(){
        String url = Statics.BASE_URL+"allcategorieJSON";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                categorie = parsecategorie(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);


        return categorie;
    }
}
