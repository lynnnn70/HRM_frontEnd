public enum CommonErrorCode{

    OK(0),

    NO_ERROR(10),

    INVALID_OUTPUT_DATA(com.thsrc.meig.webapi.CommonErrorCode.INVALID_OUTPUT_DATA.getValue()),

    INVALID_INPUT_DATA(-10),

    CALL_ECPS_API_FAIL(-11),

    private int value;

    public int getValue(){
        return value;
    }

    CommonErrorCode(int value){
        this.value = value;
    }

}