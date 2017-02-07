package com.afv.redhat.forms_validator.validators;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import com.afv.redhat.forms_validator.R;
import com.afv.redhat.forms_validator.TextInputLayoutWithValidator;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by briangallagher on 16/01/2017.
 *
 * PersonalDetailsValidator class contains personal details fields validators for SPB project.
 */
public class PersonalDetailsValidator {

    private static PersonalDetailsValidator instance=null;              //PersonalDetailsValidator Instance
    private List<EditTextValidator> title=new ArrayList<>();            //Person's Title List
    private List<EditTextValidator> surname = new ArrayList<>();        //Person's Surname List
    private List<EditTextValidator> firstname = new ArrayList<>();      //Person's First Name List
    private List<EditTextValidator> address1 = new ArrayList<>();       //Person's Address Line 1 List
    private List<EditTextValidator> address2 = new ArrayList<>();       //Person's Address Line 2 List
    private List<EditTextValidator> postalCode = new ArrayList<>();     //Person's Postal Code List
    private List<EditTextValidator> town = new ArrayList<>();           //Person's Town List
    private List<EditTextValidator> phone = new ArrayList<>();          //Person's Phone Number List
    private List<EditTextValidator> email = new ArrayList<>();          //Person's Emil List
    private List<EditTextValidator> emailConfirm = new ArrayList<>();

    /**
     * Method returns a new instance of PersonalDetailsValidator.
     *
     * @param ctx   Context
     *
     * @return New instance of ContactDetailsValidator
     */
    public static PersonalDetailsValidator getInstance(Context ctx){
        if (instance==null){
            instance=new PersonalDetailsValidator(ctx);
        }
        return instance;
    }
    Context mCtx;

    /**
     * PersonalDetailsValidator method.
     *
     * @param ctx   Context
     */
    public PersonalDetailsValidator(Context ctx){
        mCtx=ctx;

        surname.add(new RequireValidator(mCtx));
        firstname.add(new RequireValidator(mCtx));
        address1.add(new RequireValidator(mCtx));
        address2.add(new RequireValidator(mCtx));
        postalCode.add(new RequireValidator(mCtx));
        town.add(new RequireValidator(mCtx));
        phone.add(new RequireValidator(mCtx));
        email.add(new RequireValidator(mCtx));
        emailConfirm.add(new RequireValidator(mCtx));
    }

    /**
     * Resource binding method.
     *
     * @param fragment  Fragment
     */
    public void bind(final Fragment fragment){

        bindField(R.id.personal_title, title, fragment);
        bindField(R.id.personal_surname, surname, fragment);
        bindField(R.id.personal_firstname, firstname, fragment);
        bindField(R.id.personal_address1, address1, fragment);
        bindField(R.id.personal_address2, address2, fragment);
        bindField(R.id.personal_postalCode, postalCode, fragment);
        bindField(R.id.personal_town, town, fragment);
        bindField(R.id.personal_phone, phone, fragment);
        bindField(R.id.personal_email, email, fragment);
        bindField(R.id.personal_emailConfirm, emailConfirm, fragment);
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
