package model;

import java.lang.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Operations {

    public static Polynomial adunare(Polynomial p1, Polynomial p2)
    {
        Polynomial p3 = new Polynomial();
        for(Map.Entry<Integer,Float> monom1 : p1.getTerms().entrySet())
        {
                float coeficient = 0.0f;
                if(p2.getTerms().containsKey(monom1.getKey()) == true)
                {
                    coeficient = monom1.getValue() + p2.getTerms().get(monom1.getKey());
                    p3.addTerm(monom1.getKey(), coeficient);
                }
                else
                    p3.addTerm(monom1.getKey(), monom1.getValue());
        }
        for(Map.Entry<Integer,Float> monom2 : p2.getTerms().entrySet())
        {
            if(p1.getTerms().containsKey(monom2.getKey()) == false)
                p3.addTerm(monom2.getKey(), monom2.getValue());
        }
        return p3;
    }

    public static Polynomial scadere(Polynomial p1, Polynomial p2)
    {
        Polynomial p3 = new Polynomial();
        for(Map.Entry<Integer,Float> monom1 : p1.getTerms().entrySet())
        {
            float coeficient = 0.0f;
            if(p2.getTerms().containsKey(monom1.getKey()) == true)
            {
                coeficient = monom1.getValue() - p2.getTerms().get(monom1.getKey());
                p3.addTerm(monom1.getKey(), coeficient);
            }
            else
                p3.addTerm(monom1.getKey(), monom1.getValue());
        }
        for(Map.Entry<Integer,Float> monom2 : p2.getTerms().entrySet())
        {
            if(p1.getTerms().containsKey(monom2.getKey()) == false)
                p3.addTerm(monom2.getKey(), monom2.getValue());
        }
        return p3;
    }

    public static Polynomial derivare(Polynomial p1)
    {
        Polynomial p2 = new Polynomial();
        for(Map.Entry<Integer,Float> monom1 : p1.getTerms().entrySet())
        {
            float newCoeficient = 0.0f;
            int newKey;
            newCoeficient = p1.getCoef(monom1.getKey()) * monom1.getKey();
            newKey = monom1.getKey() - 1;
            p2.addTerm(newKey, newCoeficient);
        }
        return p2;
    }

    public static Polynomial integrare(Polynomial p)
    {
        Polynomial p1 = new Polynomial();
        for(Map.Entry<Integer,Float> monom1 : p.getTerms().entrySet())
        {
            float newCoeficient = 0.0f;
            int newKey;
            newCoeficient = p.getCoef(monom1.getKey()) / (monom1.getKey() + 1);
            newKey = monom1.getKey() + 1;
            p1.addTerm(newKey, newCoeficient);
        }
        return p1;
    }

    public static Polynomial inmultire(Polynomial p1, Polynomial p2)
    {
        Polynomial p3 = new Polynomial();
        for(Map.Entry<Integer,Float> monom1 : p1.getTerms().entrySet())
            for(Map.Entry<Integer,Float> monom2 : p2.getTerms().entrySet())
            {
                int key = monom1.getKey() + monom2.getKey();
                float coef = monom1.getValue() * monom2.getValue();

                if(p3.getTerms().containsKey(key))
                    coef += p3.getTerms().get(key);

                p3.addTerm(key,coef);
            }
        return p3;
    }

    public static ArrayList<Polynomial> impartire(Polynomial p1, Polynomial p2)
    {
        boolean gol = p2.esteGol();
        if(gol == true)
            throw new ArithmeticException("Impartire la 0");

        int p1key = p1.getKey();
        Polynomial rez = new Polynomial();
        Polynomial rest = new Polynomial(p1);

        while(p1.getKey() >= p2.getKey())
        {
            Polynomial prez = new Polynomial();
            int key = p1.getKey() - p2.getKey();
            float coef = p1.getCoef(p1.getKey()) / p2.getCoef(p2.getKey());
            prez.addTerm(key, coef);
            rez.addTerm(key, coef);

            Polynomial aux = new Polynomial();
            Polynomial prest = new Polynomial();
            aux = Operations.inmultire(prez, p2);
            prest = Operations.scadere(rest, aux);
            rest = new Polynomial(prest);
            if(rest.esteGol())
                break;
            p1 = new Polynomial(rest);
        }
        ArrayList<Polynomial> poliRezRest = new ArrayList<>();
        poliRezRest.add(rez);
        poliRezRest.add(rest);
        return poliRezRest;
    }

}
