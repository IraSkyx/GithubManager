/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_logic;

/**
 *
 * @author Adrien
 */
public abstract class Follow {
    abstract void AddFollow(Follow follow);
    abstract void DeleteFollow(Follow follow);
}