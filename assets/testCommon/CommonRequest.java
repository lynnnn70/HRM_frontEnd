public class CommonRequest<T extends CommonRequest<T, B>, B >{

    @JsonProperty(value = "meta", required = true)
    private RequestMetaData metadata;

    private B body;

    public CommonRequest(){}

    public RequestMetaData getMetadata(){
        return this.metadata;
    }

    public T setMetadata(RequestMetaData meta){
        this.metadata = metadata;
        return T;
    }

    public B getBody(){
        return this.body;
    }

    public T setBody(B body){
        this.body = body;
        return this;
    }

    
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("{meta:");
        sb.append(this.metadata);
        sb.append(",body:");
        sb.append(this.body);
        sb.append('}');
        return sb.toString();
    }

}