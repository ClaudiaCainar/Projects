package model;

import java.util.*;
import java.lang.Integer;
import java.lang.String;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial{
    private HashMap<Integer, Float> polinom;

    public Polynomial(){
        polinom = new HashMap<>();
    }

    public Polynomial(Polynomial p1)
    {
        polinom = new HashMap<>(p1.getTerms());
    }

    public void addTerm(int key, float coef)
    {
        polinom.put(key,coef);
    }

    public int getKey()
    {
        if(polinom.isEmpty())
            return 0;
        else
            return polinom.keySet().stream().max(Integer::compare).get();
            //returneaza cea mai mare cheie a polinomului
    //keySet returneaza o multime care contine cheile(puterile) polinomului
    //stream e apelat pe multimea de chei
    //max returneaza cheia maxima in multimea de chei, comparand valorile cu "compare"
    //get ia cheia maxima, pe care o "reface" ca pe un int
    }

    public boolean esteGol()
    {
        boolean gol = true;
        for(int i = 0; i <= this.getKey(); i++)
            if(this.getCoef(i) != 0)
            {
                gol = false;
                break;
            }
        return gol;
    }

    public float getCoef(int key)
    {
        if(polinom.containsKey(key))
            return polinom.get(key);
        else
            return 0;
    }

    public Map<Integer,Float> getTerms()
    {
        Map<Integer,Float> termeni = new HashMap<>();
        for(int i: this.polinom.keySet())
        {
            float coef = this.polinom.get(i);
            if(coef != 0)
                termeni.put(i,coef);
        }
        return termeni;
    }

    @Override
    public String toString() {
        StringBuilder poliString = new StringBuilder();
        TreeMap<Integer, Float> sort = new TreeMap<>(polinom);
        if(polinom.size() == 0)
            return "0";
        for(Map.Entry<Integer,Float> i : sort.descendingMap().entrySet())
        {
            float coef = i.getValue();
            if(coef == 0)
                continue;
            if(coef > 0)
            {
                poliString.append("+");
                if (coef == 1) {
                    if (i.getKey() == 0) {
                        poliString.append("1");
                    } else if (i.getKey() == 1) {
                        poliString.append("x");
                    } else {
                        poliString.append("x^");
                        poliString.append(i.getKey());
                    }
                }
                else
                {
                    if (i.getKey() == 0) {
                        poliString.append(coef);
                    }
                    else if (i.getKey() == 1) {
                        poliString.append(coef);
                        poliString.append("x");
                    }
                    else {
                        poliString.append(coef);
                        poliString.append("x^");
                        poliString.append(i.getKey());
                    }
                }
            }
            else
            {
                if (coef == -1) {
                    if (i.getKey() == 0) {
                        poliString.append("-1");
                    } else if (i.getKey() == 1) {
                        poliString.append("-x");
                    } else {
                        poliString.append("-x^");
                        poliString.append(i.getKey());
                    }
                }
                else
                {
                    if (i.getKey() == 0) {
                        poliString.append(coef);
                    }
                    else if (i.getKey() == 1) {
                        poliString.append(coef);
                        poliString.append("x");
                    }
                    else {
                        poliString.append(coef);
                        poliString.append("x^");
                        poliString.append(i.getKey());
                    }
                }
            }

        }
        return poliString.toString();
    }
    public Polynomial parsePolinom(String poliString) {
        Polynomial p = new Polynomial();
        Pattern pattern = Pattern.compile("([+-]?)(\\d*\\.?\\d*)?\\s*\\*?\\s*x\\s*(\\^\\s*(\\d+))?");
        //([+-]?) -> match-uieste semnul coeficientului - group(1)
        //(\\d*\\.?\\d*)? -> match-uieste coeficientul - group(2)
        //\\s*\\*?\\s* -> match-uieste o * care poate avea langa ea oricate spatii - group(3)
        //\\s*(\\^\\s*(\\d+))? -> match-uieste puterea si semnul ^ care o precede - group(4)

        Matcher match = pattern.matcher(poliString);

        while(match.find())
        {
            float coef = 1.0f;
            String semn = match.group(1);
            if(semn != null && semn.equals("-"))
            {
                coef = (-1) * coef;
            }
            if(match.group(2) != null && !match.group(2).isEmpty())
                coef *= Float.parseFloat(match.group(2));
            int key = 0;
            if(match.group(4) != null && !match.group(4).isEmpty())
            {
                key = Integer.parseInt(match.group(4));
            }
            p.addTerm(key, coef);
        }

        return p;
    }

}
