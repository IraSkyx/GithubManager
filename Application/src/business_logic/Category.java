/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_logic;

import java.util.ArrayList;

/**
 *
 * @author Adrien
 */
public class Category extends Follow{

    private String name;
    private final ArrayList<Follow> listOfFollows;
    
    public Category(String name) {
        this.name = name;
        this.listOfFollows = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    void AddFollow(Follow follow) {
        listOfFollows.add(follow);
    }

    @Override
    void DeleteFollow(Follow follow) {
        listOfFollows.remove(follow);
    }
    
}
