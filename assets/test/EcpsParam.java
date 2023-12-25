public class EcpsParam{
    private String code;

    private String title;

    private List<String> values;

    public EcpsParam(){

    }

    public String getCode(){
        return this.code;
    }

    public void setCode(String code){
        this.code = code;
    }

    public String getTitle(){
        rturn this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public List<String> getValues(){
        return this.values;
    }

    public void setValues(List<String> values){
        this.values = values;
    }

    public toString(){
        StringBuilder sb = new StringBuilder("EcpsParam{");
        sb.append("code='").append(this.code).append('\'');
        sb.append(", title='").append(this.title).append('\'');
        sb.append(", values=").append(this.values);
        sb.append('}');
        return sb.toString();
    }
}