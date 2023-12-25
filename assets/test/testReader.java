//讀ParamFile
public List<EcpsParamResponse> readParamFile(String filePath){
    List<EcpsParamResponse> param = new ArrayList<>();
    try(
        //InputStreamReader字節流(文字)轉換為字符流，提供了讀取字符的方法，可方便處理文本數據
        //StandardCharsets指定字符的編碼方式
        //read 方法來讀取字符數據。能夠以字符的形式處理文本數據，而不需要直接處理字節
        
        InputStreamReader isr = new InputStreamReader(Files.newInputStream(Paths.get(filePath)), StandardCharsets.UTF_8);
        //ICsvListReader用於讀取 CSV 文件的行（records）並將它們轉換為 List 的形式
        ICsvListReader listReader = new ICsvListReader(isr, CsvPreference.STANDARD_PREFERENCE){

            for(int i = 0; i< 6; i++){
                listReader.read();
            }

            List<String> csvRow;
            csvRow = listReader.read();
            while(csvRow != null){
                try{
                    EcpsParamResponse res = generateNewDataRow(csvRow);
                    param.add(res)
                }catch(Exception e){
                    log.warn("parse data error data:{}", csvRow);
                }
                return param;
            }
        }
       
    )
}
private EcpsParamResponse generateResponse(List<String> csvRow) throws JsonMappingException{
    EcpsParamResponse res = new EcpsParamResponse();
    res.setCode(csvRow.get(0));
    res.setMsg(csvRow.get(1));
    res.setResult(setEcpsParamResult(csvRow));
    return res;
}

private List<EcpsParam> setEcpsParamResult(List<String> csvRow) throws JsonMappingException{
    ArrayList<EcpsParam> result = new ArrayList<>();

    if(csvRow.get(2) == null){
        return result;
    }
    EcpsParam firstEcpsParam = setEcpsParam()
}

private EcpsParam setEcpsParam(String code, String title, String valueString) throws JsonProcessingException{
    EcpsParam ecpsParam = new EcpsParam();
    ecpsParam.setCode(code);
    ecpsParam.setTitle(title);
    List<String> values;
    if(valueString != null){
        valueString = "["+ valueString +"]"
        values = objectMapper.readValue(valuesString, new TyprReference<List<String>>()){    
        }
    }else{
        values = new ArrayList<>();
    }
    ecpsParam.setValues(values);
    return ecpsParam;
}
