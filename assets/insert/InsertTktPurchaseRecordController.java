@RestController
@RequestMapping("/api/free/")
@Configuration
public class InsertTktPurchaseRecordController{

    private static Logger log = LoggerFactory.getLogger(InsertTktPurchaseRecordController.class);
    public static final String API_INSERT_TKT_PURCHASE_RECORD = "rpcInsertTktPurchaseRecord";

    private String tktInsertSpName;
    private String tktInsertTktId;
    private String tktInsertPnrNo;
    private String tktInsertEquipId;
    private String tktInsertFirDaeTime;
    private String tktInsertDepartDate;
    private String tktInsertDepartStation;
    private String tktInsertArrivalStation;
    private String tktInsertRtnCode;

    @Autiwired
    private JdbcTemplate JdbcTemplate;

    private SimpleJdbcCall tktPurchaseInsert;

// consumesm用於指定Controller方法能夠處理的請求類型(Request Content Type)
// produces 用於指定Controller方法能夠處理的響應類型(Response Content Type)
//起手式: RequestBody換成自己的 this方法再寫 傳入值(httpReq, req)
    @PostMapping(path = "insertTktPurchaseRecord", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse<?,?>> insertTktPurchaseRecord(@RequestBody InsertTktPurchaseRecordRequest req, HttpServletRequest httpReq){
        return TxLogUtil.runAndLogTx(API_INSERT_TKT_PURCHASE_RECORD, this::insertPurchaseRecord, httpReq, req);

    }

    public InsertTktPurchaseRecordResponse insertPurchaseRecord(HttpServletRequest httpReq, InsertTktPurchaseRecordRequest req){
        InsertTktPurchaseRecord insertTktPurchase = req.getBody();
        InsertTktPurchaseRecordErrorCode error = InsertTktPurchaseRecordErrorCode.NO_ERROR;

        //Validate
        boolean ValidateFlag = Validate(insertTktPurchase)
    }

















}