public class ParamOutput{

    @Schema(description = "回應代碼 Format: 最長5碼")
    private String code;

    @Schema(description = "回應訊息 Format: 最長256碼")
    private String msg;

    @Schema(description = "參數內容")
    private List<Param> result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Param> getResult() {
        return result;
    }

    public void setResult(List<Param> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append("{code:'").append(code).append('\'');
        sb.append(", msg:'").append(msg).append('\'');
        sb.append(", result:").append(result);
        sb.append("}");
        return sb.toString();
    }

}