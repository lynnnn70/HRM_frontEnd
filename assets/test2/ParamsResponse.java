public class ParamsResponse extends EcpApiResponse<ParamOutput>{

    public ParamsResponse(){}

    public ParamsResponse(CommonErrorCode error, String message){
        setCode(error.getValue());
        setMessage(message);
    }
}