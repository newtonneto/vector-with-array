package com.newton.interfaces;

import com.newton.exceptions.EmptyVectorException;
import com.newton.exceptions.RankOutOfBoundsException;

public interface IVector {
    Object elementAtRank(Integer r) throws EmptyVectorException, RankOutOfBoundsException;
    Object replaceAtRank(Integer r, Object o) throws EmptyVectorException, RankOutOfBoundsException;
    void insertAtRank(Integer r, Object o) throws RankOutOfBoundsException;
    Object removeAtRank(Integer r) throws EmptyVectorException, RankOutOfBoundsException;
    Integer size();
    Boolean isEmpty();
    void print();
}
