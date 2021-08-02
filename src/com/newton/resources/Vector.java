package com.newton.resources;

import com.newton.exceptions.EmptyVectorException;
import com.newton.exceptions.RankOutOfBoundsException;
import com.newton.interfaces.IVector;

public class Vector implements IVector {
    private Integer capacity;
    private Integer first_element_rank;
    private Integer first_empty_rank;
    private Object[] vector;

    public Vector(Integer capacity) {
        this.capacity = capacity;
        this.first_element_rank = 0;
        this.first_empty_rank = 0;
        this.vector = new Object[this.capacity];
    }

    @Override
    public Object elementAtRank(Integer r) throws EmptyVectorException, RankOutOfBoundsException {
        if (this.isEmpty()) {
            throw new EmptyVectorException("The vector is empty");
        }
        if (r >= this.first_empty_rank || r < 0) {
            throw new RankOutOfBoundsException("The rank doesn't exists");
        }

        return this.vector[r];
    }

    @Override
    public Object replaceAtRank(Integer r, Object o) throws EmptyVectorException, RankOutOfBoundsException {
        if (this.isEmpty()) {
            throw new EmptyVectorException("The vector is empty");
        }
        if (r >= this.first_empty_rank || r < 0) {
            throw new RankOutOfBoundsException("The rank doesn't exists");
        }

        Object removed_element = this.vector[r];
        this.vector[r] = o;

        return removed_element;
    }

    @Override
    public void insertAtRank(Integer r, Object o) throws RankOutOfBoundsException {
        if (r > this.first_empty_rank || r < 0) {
            throw new RankOutOfBoundsException("The rank doesn't exists");
        }
        if (this.size() == this.capacity) {
            this.realloc();
        }

        if (r == this.capacity - 1) {
            this.realloc();
        }

        if (this.vector[r] != null) {
            this.rightShift(this.vector[r], r);
            this.vector[r] = o;
        } else {
            this.vector[r] = o;
        }

        this.first_empty_rank++;
    }

    public void rightShift(Object relocated, Integer rank) {
        if (rank > this.size()) {
            this.realloc();
        };

        if (this.vector[rank] != null) {
            //Recebe o valor atual do vetor para passar para o proximo rank
            Object save_actual_element = this.vector[rank];
            //Altera o valor atual do vetor para o valor recebido
            this.vector[rank] = relocated;
            //Realoca o proximo elemento, até chegar em um espaço vazio
            this.rightShift(save_actual_element, ++rank);
        } else {
            this.vector[rank] = relocated;
        }
    }

    @Override
    public Object removeAtRank(Integer r) throws EmptyVectorException, RankOutOfBoundsException {
        if (this.isEmpty()) {
            throw new EmptyVectorException("The vector is empty");
        }
        if (r >= this.first_empty_rank || r < 0) {
            throw new RankOutOfBoundsException("The rank doesn't exists");
        }

        Object removed_element = this.vector[r];
        this.vector[r] = null;
        this.leftShit(r);

        return removed_element;
    }

    public void leftShit(Integer rank) {
        Integer next_rank = rank + 1;

        if (this.vector[next_rank] != null) {
            this.vector[rank] = this.vector[next_rank];
            this.vector[next_rank] = null;
            this.leftShit(next_rank);
        }
    }

    @Override
    public Integer size() {
        return this.first_empty_rank;
    }

    @Override
    public Boolean isEmpty() {
        return (first_empty_rank == 0);
    }

    @Override
    public void print() {
        for (int index = 0; index < this.capacity; index++) {
            if (this.vector[index] == null) {
                System.out.print(" - ");
            } else {
                System.out.print(" " + this.vector[index] + " ");
            }
        }
        System.out.println("\n");
    }

    public void realloc() {
        Object[] new_vector = new Object[this.capacity * 2];
        int original_size = this.size();

        for (int index = 0; index < original_size; index++) {
            new_vector[index] = this.vector[(this.first_element_rank + index) % this.capacity];
        }

        this.first_element_rank = 0;
        this.first_empty_rank = original_size;
        this.capacity = this.capacity * 2;
        this.vector = new_vector;
    }
}
