package com.afv.redhat.forms_validator.Interfaces;

/**
 * ValidatableComponent class.
 */
public interface ValidatableComponent {

    enum Status{                        //List of status constants
        inactive,active,error,complete
    }
    CharSequence getText();

    /**
     * Method triggers a render of a status.
     */
    void renderStatus();

    /**
     * Method triggers a render of a status.
     *
     * @param status Status can be inactive, active, error or complete
     */
    void renderStatus(Status status);

    /**
     * Method sets listener.
     *
     * @param listener  ValidatableComponentListener
     */
    void setValidatableComponentListener(ValidatableComponentListener listener);
}
