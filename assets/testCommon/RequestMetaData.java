public class RequestMetaData {
    
    @JsonProperty(required = true )
    private ClientSource source;

    public RequestMetaData() {
    }

    public ClientSource getSource() {
        return this.source;
    }

    public void setSource(ClientSource source) {
        this.source = source;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("{source:");
        sb.append(this.source);
        sb.append('}');
        return sb.toString();
    }
}
