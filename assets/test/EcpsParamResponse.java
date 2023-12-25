public class EcpsParamResponse extends EcpsResponse{
    
    private List<param> result;

    public EcpsParamResponse(){
    }

    public List<EcpsParam> getResult(){
        return this.result ;
    }

    public void setResult(List<EcpsParam> result){
        this.result = result;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("{result:").append(this.result);
        sb.append("}");
        return sb.toString();
    }
}