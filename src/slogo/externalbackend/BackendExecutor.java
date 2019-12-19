package slogo.externalbackend;

interface BackendExecutor {
    /**
     * This method will carry out all backend commands that correspond to the model, and will be called by the
     * controller.
     */
    void executeModelCommands();
}
