package com.afv.redhat.forms_validator.validators;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import com.afv.redhat.forms_validator.R;
import com.afv.redhat.forms_validator.TextInputLayoutWithValidator;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by agagancarczyk on 16/01/2017.
 *
 * ContactDetailsValidator class contains contact details fields validators for SPB project.
 */
public class ContactDetailsValidator {

    private static ContactDetailsValidator instance=null;                           //ContactDetailsValidator Instance
    private List<EditTextValidator> contactReason =new ArrayList<>();               //Contact Reason List
    private List<EditTextValidator> contactSurname = new ArrayList<>();             //Contact Surname List
    private List<EditTextValidator> contactFirstname = new ArrayList<>();           //Contact First Name List
    private List<EditTextValidator> contactPhone = new ArrayList<>();               //Contact Phone List
    private List<EditTextValidator> contactEmail = new ArrayList<>();               //Contact Email List
    private List<EditTextValidator> contactEmailConfirmation = new ArrayList<>();   //Contact Email Confirmation List
    private List<EditTextValidator> contactMembershipNumber = new ArrayList<>();    //Contact Membership Number List
    private List<EditTextValidator> contactRecord = new ArrayList<>();              //Contact Record List
    private List<EditTextValidator> contactMessage = new ArrayList<>();             //Contact Message List

    /**
     * Method returns a new instance of ContactDetailsValidator.
     *
     * @param ctx   Context
     *
     * @return New instance of ContactDetailsValidator
     */
    public static ContactDetailsValidator getInstance(Context ctx){
        if (instance==null){
            instance=new ContactDetailsValidator(ctx);
        }
        return instance;
    }
    Context mCtx;

    /**
     * ContactDetailsValidator method.
     *
     * @param ctx   Context
     */
    public ContactDetailsValidator(Context ctx){
        mCtx=ctx;
        contactReason.add(new RequireValidator(mCtx));
        contactSurname.add(new RequireValidator(mCtx));
        contactFirstname.add(new RequireValidator(mCtx));
        contactPhone.add(new RequireValidator(mCtx));
        contactEmail.add(new RequireValidator(mCtx));
        contactEmailConfirmation.add(new RequireValidator(mCtx));
        contactMembershipNumber.add(new RequireValidator(mCtx));
        contactRecord.add(new RequireValidator(mCtx));
        contactMessage.add(new RequireValidator(mCtx));
    }

    /**
     * Resource binding method.
     *
     * @param fragment  Fragment
     */
    public void bind(final Fragment fragment){
        bindField(R.id.contact_reason, contactReason, fragment);
        bindField(R.id.contact_surname, contactSurname, fragment);
        bindField(R.id.contact_firstname, contactFirstname, fragment);
        bindField(R.id.contact_phone, contactPhone, fragment);
        bindField(R.id.contact_email, contactEmail, fragment);
        bindField(R.id.contact_email_confirmation, contactEmailConfirmation, fragment);
        bindField(R.id.contact_membership_number, contactMembershipNumber, fragment);
        bindField(R.id.contact_record, contactRecord, fragment);
        bindField(R.id.contact_message, contactMessage, fragment);
    }

    /**
     * Field finding method.
     *
     * @param res       Resource
     * @param fragment  Fragment
     * @return Fragment View
     */
    private TextInputLayoutWithValidator getField(int res, Fragment fragment){
        return (TextInputLayoutWithValidator) fragment.getView().findViewById(res);
    }

    /**
     * Method binds field with validator.
     *
     * @param res           Resource
     * @param validators    Validators
     * @param fragment      Fragment
     * @return Field
     */
    private boolean bindField(int res, List<EditTextValidator> validators, Fragment fragment){
        TextInputLayoutWithValidator layout=getField(res, fragment);
        if (layout !=null){
            layout.addValidators(validators);
            return true;
        }else{
            Log.v("SPB", "Failed to find field");
            return false;
        }

    }
}