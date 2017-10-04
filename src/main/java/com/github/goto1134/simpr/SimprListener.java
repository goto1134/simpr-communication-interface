package com.github.goto1134.simpr;

/**
 * Created by Andrew
 * on 17.09.2017.
 */
interface SimprListener {
    int getConditionValue(int tableIndex, int conditionIndex);

    int performEvent(int tableIndex, int event);
}
