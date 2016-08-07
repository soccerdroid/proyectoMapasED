/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasehashmaps;

import java.util.HashMap;

/**
 *
 * @author Administrador
 */
public class ClaseHashMaps {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        HashMap<String,String>mapa=new HashMap<>();
        mapa.put("@usuario1","Hola #hashtag1");
        mapa.put("@usuario2","Hohffshla #hashtag1");
        mapa.put("@usuario3","Holda #hashtag2");
        System.out.println(mapa);
        HashMap<String,Integer> resultado=trendTopics(mapa);
        System.out.println(resultado);
    }
    public static HashMap<String,Integer> trendTopics(HashMap<String,String>mapa){
        HashMap<String,Integer> resultado=new HashMap<>();
        
        if(mapa.size()>0){
        for(String s:mapa.keySet()){
            String tweet=mapa.get(s);
            String[] tweetSeparado=tweet.split(" ");
            for( String c:tweetSeparado){
                if(c.startsWith("#")){
                    if(resultado.keySet().contains(c)){
                        resultado.put(c, resultado.get(c)+1);
                    }
                    else{
                        resultado.put(c,1);
                    }
                    
                }
            }
        }
        }
        return resultado;
    }
}
