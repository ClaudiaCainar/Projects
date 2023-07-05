package controller;

import model.Operations;
import model.Polynomial;
import view.Application;
import java.awt.event.*;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Controller {
    private Application app;

    public Controller(Application app) {
        this.app = app;
    }

    public ActionListener plusListener()
    {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Polynomial p1 = new Polynomial();
                Polynomial p2 = new Polynomial();
                Polynomial p3 = new Polynomial();

                p1 = p1.parsePolinom(app.getTextP1().getText());
                p2 = p2.parsePolinom(app.getTextP2().getText());
                p3 = Operations.adunare(p1, p2);
                app.getTextP3().setText(p3.toString());
            }
        };
    }

    public ActionListener minusListener()
    {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Polynomial p1 = new Polynomial();
                Polynomial p2 = new Polynomial();
                Polynomial p3 = new Polynomial();

                p1 = p1.parsePolinom(app.getTextP1().getText());
                p2 = p2.parsePolinom(app.getTextP2().getText());
                p3 = Operations.scadere(p1, p2);
                app.getTextP3().setText(p3.toString());
            }
        };
    }

    public ActionListener inmultListener()
    {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Polynomial p1 = new Polynomial();
                Polynomial p2 = new Polynomial();
                Polynomial p3 = new Polynomial();

                p1 = p1.parsePolinom(app.getTextP1().getText());
                p2 = p2.parsePolinom(app.getTextP2().getText());
                p3 = Operations.inmultire(p1, p2);
                app.getTextP3().setText(p3.toString());
            }
        };
    }

    public ActionListener impartListener()
    {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Polynomial p1 = new Polynomial();
                Polynomial p2 = new Polynomial();
                ArrayList<Polynomial> p3 = new ArrayList<>();

                p1 = p1.parsePolinom(app.getTextP1().getText());
                p2 = p2.parsePolinom(app.getTextP2().getText());
                p3 = Operations.impartire(p1, p2);

                Polynomial rez = new Polynomial();
                Polynomial rest = new Polynomial();
                rez = p3.get(0);
                rest = p3.get(1);

                app.getTextP3().setText(rez.toString() + "rest:" + rest.toString());
            }
        };
    }

    public ActionListener derivListener()
    {
        return new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                Polynomial p1 = new Polynomial();
                Polynomial p2 = new Polynomial();

                p1 = p1.parsePolinom(app.getTextP1().getText());
                p2 = Operations.derivare(p1);
                app.getTextP3().setText(p2.toString());
            }
        };
    }

    public ActionListener integrListener()
    {
        return new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                Polynomial p1 = new Polynomial();
                Polynomial p2 = new Polynomial();

                p1 = p1.parsePolinom(app.getTextP1().getText());
                p2 = Operations.integrare(p1);
                app.getTextP3().setText(p2.toString());
            }
        };
    }
}
