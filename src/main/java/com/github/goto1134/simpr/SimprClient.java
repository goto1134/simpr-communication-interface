package com.github.goto1134.simpr;

/**
 * SIMPR Win32 message listener
 */
interface SimprClient {

    /**
     * Requests condition value
     *
     * @param tableIndex     index table
     * @param conditionIndex index of condition in table
     * @return value of condition
     */
    boolean getConditionValue(int tableIndex, int conditionIndex);

    /**
     * Notifies to execute event
     *
     * @param tableIndex index of event table
     * @param event      event id
     * @return is execution performed successfully
     */
    boolean performEvent(int tableIndex, int event);

    /**
     * Fires when end state is reached by SIMPR program
     */
    void onEndStateReached();
}
