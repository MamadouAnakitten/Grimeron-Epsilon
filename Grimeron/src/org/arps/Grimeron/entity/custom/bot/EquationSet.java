/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.arps.Grimeron.entity.custom.bot;

import org.arps.Grimeron.entity.Tile;

/**
 *
 * @author richa_000
 */
public class EquationSet {
    
    public enum GFunction
    {
        Fx("(K ^ -enemiesOnPath) / weight"), Ax("K ^ (-enemiesOnPath/weight)"), Ox("K/weight");
        
        private String desc;
        
        private GFunction(String desc)
        {
            this.desc = desc;
        }
        
        @Override
        public String toString()
        {
            return desc;
        }
    }
    
    private float weight = 0;
    private int pathsize = 0;
    private int recursions = 0;
    private int enemiesOnPath = 0;
    
    Tile source;
    
    public GFunction functionType;
    
    public EquationSet()
    {
        functionType = GFunction.Ox;
    }
    
    public EquationSet(Tile source, float weight, int enemies, int pathsize, int recursions)
    {
        functionType = GFunction.Ox;
    }
    
    public EquationSet(Tile source, float weight, int enemies, int pathsize, int recursions, GFunction functionType)
    {
        functionType = GFunction.Ox;
    }
    
    public float evaluateWeight()
    {
        switch(functionType)
        {
            case Ox:
                return ((pathsize/recursions) / weight);
            case Fx:
                return (float) (Math.pow((pathsize/recursions), enemiesOnPath) / weight);
            case Ax:
                return (float) (Math.pow((pathsize/recursions), (-enemiesOnPath/weight)));
        }
        return 0;
    }

    public float getWeight() 
    {
        return weight;
    }

    public void setWeight(float weight) 
    {
        this.weight = weight;
    }

    public int getPathsize() {
        return pathsize;
    }

    public void setPathsize(int pathsize) 
    {
        this.pathsize = pathsize;
    }

    public int getRecursions() 
    {
        return recursions;
    }

    public void setRecursions(int recursions) 
    {
        this.recursions = recursions;
    }

    public int getEnemiesOnPath() 
    {
        return enemiesOnPath;
    }

    public void setEnemiesOnPath(int enemiesOnPath) 
    {
        this.enemiesOnPath = enemiesOnPath;
    }
    
    public void append(EquationSet set)
    {
        if(set.getRecursions() == this.recursions)
        {
            this.pathsize = this.pathsize + set.getPathsize();
        }
        else
        {
            int finalPathsize = (set.getPathsize() / set.getRecursions()) * this.recursions;
            this.pathsize = this.pathsize + set.getPathsize();
        }
    }
}
