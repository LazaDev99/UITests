package environment;

public class ConfigProvider extends ConfigManager
{
    //basic page config
    public static String getBasicPageUrl () { return getValue("BASIC_PAGE_URL"); }
    public static String getFillFormUrl () { return  getValue("FILL_FORM_URL"); }

    //fill form input config
    public static String getFillFormFullNameID() { return  getValue("FILL_FORM_FULL_NAME_ID");}
    public static String getFillFormEmailCSS() { return  getValue("FILL_FORM_EMAIL_CSS");}
    public static String getFillFormCurrentAddressXPath() { return  getValue("FILL_FORM_CURRENT_ADDRESS_XPATH");}
    public static String getFillFormPermanentAddressXPath() { return  getValue("FILL_FORM_PERMANENT_ADDRESS_XPATH");}
    public static String getFillFormSubmitCSS() { return  getValue("FILL_FORM_SUBMIT_CSS");}

    //fill form output config
    public static String getOutputFillFormFullNameID() { return  getValue("FILL_FORM_OUTPUT_NAME_ID");}
    public static String getOutputFillFormEmailCSS() { return  getValue("FILL_FORM_OUTPUT_EMAIL_ID");}
    public static String getOutputFillFormCurrentAddressXPath() { return  getValue("FILL_FORM_OUTPUT_CURRENT_ADDRESS_XPATH");}
    public static String getOutputFillFormPermanentAddressXPath() { return  getValue("FILL_FORM_OUTPUT_PERMANENT_ADDRESS_XPATH");}

    //button test config
    public static String getButtonTestUrl() { return  getValue("BUTTON_TEST_URL"); }
    public static String getButtonID() { return  getValue("BUTTON_ID"); }

    //table test config
    public static String getTableTestUrl() { return getValue("TABLE_TEST_URL");}
    public static String getTableCompanyXpath() { return getValue("TABLE_COMPANY_CELLS_XPATH");}

    //checkBox test config
    public static String getCheckBoxTestUrl() { return  getValue("CHECK_BOX_URL"); }
    public static String getCheckBoxLabelCSS() { return  getValue("CHECK_BOX_LABEL_CSS"); }
    public static String getCheckBoxIputID() { return  getValue("CHECK_BOX_INPUT"); }

    //login test config
    public static String getLoginTestUrl() { return  getValue("LOGIN_TEST_URL"); }
    public static String getLoginTestSuccessfullID() { return  getValue("LOGIN_TEST_SUCCESSFUL_ID"); }
}
