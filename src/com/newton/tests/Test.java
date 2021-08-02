package com.newton.tests;

import com.newton.exceptions.EmptyVectorException;
import com.newton.exceptions.RankOutOfBoundsException;
import com.newton.interfaces.ITest;
import com.newton.resources.Vector;

public class Test implements ITest {

    @Override
    public void executeTestOne() {
        Vector vector = new Vector(10);

        //Adiciona 5 elementos e imprime
        vector.insertAtRank(0, 0);
        vector.insertAtRank(1, 1);
        vector.insertAtRank(2, 2);
        vector.insertAtRank(3, 3);
        vector.insertAtRank(4, 4);
        vector.print();

        //Adiciona um elemento em um espaço já ocupado e imprime
        vector.insertAtRank(2, 20);
        vector.print();

        //Adiciona mais 4 elementos, deixando o vector cheio e imprime
        vector.insertAtRank(6, 6);
        vector.insertAtRank(7, 7);
        vector.insertAtRank(8, 8);
        vector.insertAtRank(9, 9);
        vector.print();

        //Tenta adicionar mais um elemento em um espaço ocupado e com o vector cheio, e imprime
        vector.insertAtRank(9, 21);
        vector.print();

        //Remove um elemento e imprime
        Object removed_element = vector.removeAtRank(4);
        System.out.println("Removed element: " + removed_element + "\n");
        vector.print();

        //Substitui um elemento e imprime
        removed_element = vector.replaceAtRank(7, 22);
        System.out.println("Removed element: " + removed_element + "\n");
        vector.print();

        //Imprime o elemento de um rank específico
        Integer specific_rank = 5;
        System.out.println("Element at rank " + specific_rank + ": " + vector.elementAtRank(specific_rank) + "\n");
    }

    @Override
    public void executeTestTwo() {
        Vector vector = new Vector(2);

        //Verifica as exceções de vector vazio
        try {
            vector.elementAtRank(1);
        } catch (EmptyVectorException e) {
            System.out.println(e);
        }

        try {
            vector.replaceAtRank(1, 10);
        } catch (EmptyVectorException e) {
            System.out.println(e);
        }

        try {
            vector.removeAtRank(1);
        } catch (EmptyVectorException e) {
            System.out.println(e);
        }

        //Verifica as exceções de rank inválido
        vector.insertAtRank(0,0);

        try {
            vector.elementAtRank(1);
        } catch (RankOutOfBoundsException e) {
            System.out.println(e);
        }

        try {
            vector.replaceAtRank(1, 10);
        } catch (RankOutOfBoundsException e) {
            System.out.println(e);
        }

        try {
            vector.insertAtRank(2, 10);
        } catch (RankOutOfBoundsException e) {
            System.out.println(e);
        }

        try {
            vector.removeAtRank(1);
        } catch (RankOutOfBoundsException e) {
            System.out.println(e);
        }
    }
}
