public class EcpApiInput{
    
    private EcpsHeader header;

    public EcpsHeader getHeader(){
        return header;
    }

    public void setHeader(EcpsHeader header){
        this.header = header;
    }

    public EcpApiInput() {
    }

       @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append("{header:").append(header);
        sb.append("}");
        return sb.toString();
    }

}