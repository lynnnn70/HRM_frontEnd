@RestController
@RequestMapping("/api/free/")
@Configuration
public class InsertTktPurchaseRecordController {

	private static Logger log = LoggerFactory.getLogger(InsertTktPurchaseRecordController.class);
	public static final String API_INSERT_TKT_PURCHASE_RECORD = "rpcInsertTktPurchaseRecord";

	@Value("${meig.rpc.db.sp_cvs_api_tkt_issue.name:CvsApiTktIssue_F}")
	private String tktInsertSpName;
	@Value("${meig.rpc.db.sp_cvs_api_tkt_issue.p_tkt_id.name:p_TktID}")
	private String tktInsertTktId;
	@Value("${meig.rpc.db.sp_cvs_api_tkt_issue.p_pnr.name:p_PNR}")
	private String tktInsertPnrNo;
	@Value("${meig.rpc.db.sp_cvs_api_tkt_issue.p_ap_equip.name:p_APEquip}")
	private String tktInsertEquipId;
	@Value("${meig.rpc.db.sp_cvs_api_tkt_issue.p_fir_date_time.name:p_FirDateTime}")
	private String tktInsertFirDaeTime;
	@Value("${meig.rpc.db.sp_cvs_api_tkt_issue.p_depart_date.name:p_DepDateTime}")
	private String tktInsertDepartDate;
	@Value("${meig.rpc.db.sp_cvs_api_tkt_issue.p_depart_station.name:p_DepSt}")
    private String tktInsertDepartStation;
	@Value("${meig.rpc.db.sp_cvs_api_tkt_issue.p_arrival_station.name:p_ArvSt}")
    private String tktInsertArrivalStation;
	@Value("${meig.rpc.db.sp_cvs_api_tkt_issue.r_return_code.name:r_ReturnCode}")
	private String tktInsertRtnCode;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private SimpleJdbcCall tktPurchaseInsert;

	@PostMapping(path = "insertTktPurchaseRecord", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(operationId = "insertTicketPurchaseRecord",
    summary = "insert Ticket purchase record",
    description = "insert Ticket purchase record.",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = {
                    @Content(
                            examples = {
                                    @ExampleObject(name = "insert_tkt_purchase_record")
                            })}),
    responses = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(
                                    schema = @Schema(
                                            implementation = InsertTktPurchaseRecordResponse.class),
                                    examples = {
                                            @ExampleObject(name = "invalid_input_data"),
                                            @ExampleObject(name = "insert_fail"),
                                            @ExampleObject(name = "undefined_return_value"),
                                            @ExampleObject(name = "db_connect_fail"),
                                            @ExampleObject(name = "unknown_db_error"),
                                            @ExampleObject(name = "db_runtime_exception"),
                                            @ExampleObject(name = "success"),
                                    })})})
	public ResponseEntity<CommonResponse<?, ?>> insertTktPurchaseRecord(@RequestBody InsertTktPurchaseRecordRequest req,
			HttpServletRequest httpReq) {
		return TxLogUtil.runAndLogTx(API_INSERT_TKT_PURCHASE_RECORD, this::insertPurchaseRecord, httpReq, req);
	}

	public InsertTktPurchaseRecordResponse insertPurchaseRecord(HttpServletRequest httpReq,
			InsertTktPurchaseRecordRequest req) {
		InsertTktPurchaseRecord insertTktPurchase = req.getBody();
		InsertTktPurchaseRecordErrorCode error = InsertTktPurchaseRecordErrorCode.NO_ERROR;
		// validate
		boolean validateFlag = validate(insertTktPurchase);
		if (!validateFlag) {
			error = InsertTktPurchaseRecordErrorCode.INVALID_INPUT_DATA;
			return new InsertTktPurchaseRecordResponse(error, errorCode2Msg(error));
		}
		// execute sp
		int rtnCode = executeSpForInsertTktRecord(insertTktPurchase);
		log.info("insert TktPurchaseRecord, returns: {}", rtnCode);

		// returnCode check
		error = rtnCode2ErrorCode(rtnCode);

		// return
		InsertTktPurchaseRecordResponse resp = new InsertTktPurchaseRecordResponse(error, errorCode2Msg(error));
		return resp;

	}

	@PostConstruct
	public void initSpCscProductRenew() {
		log.info("initialize tktInsertPurchaseRecord:name={}", tktInsertSpName);
		tktPurchaseInsert = new SimpleJdbcCall(jdbcTemplate).withProcedureName(tktInsertSpName)
				.declareParameters(new SqlParameter(tktInsertPnrNo, OracleTypes.VARCHAR))
				.declareParameters(new SqlParameter(tktInsertTktId, OracleTypes.VARCHAR))
				.declareParameters(new SqlParameter(tktInsertEquipId, OracleTypes.VARCHAR))
				.declareParameters(new SqlParameter(tktInsertFirDaeTime, OracleTypes.VARCHAR))
				.declareParameters(new SqlParameter(tktInsertDepartDate, OracleTypes.VARCHAR))
				.declareParameters(new SqlParameter(tktInsertDepartStation, OracleTypes.VARCHAR))
				.declareParameters(new SqlParameter(tktInsertArrivalStation, OracleTypes.VARCHAR))
				.declareParameters(new SqlInOutParameter(tktInsertRtnCode, OracleTypes.NUMBER));
	}

	protected int executeSpForInsertTktRecord(InsertTktPurchaseRecord reqBody) {
		Map<String, Object> result = null;

		result = tktPurchaseInsert.execute(FluentMap.newMapWith(tktInsertPnrNo, reqBody.getPnrNo())
				.with(tktInsertTktId, reqBody.getTktId()).with(tktInsertEquipId, reqBody.getEquipId())
				.with(tktInsertFirDaeTime, reqBody.getFirDateTime())
				.with(tktInsertDepartDate, reqBody.getDepartDate() + "000000")
				.with(tktInsertDepartStation, reqBody.getDepartStation())
				.with(tktInsertArrivalStation, reqBody.getArrivalStation())
				.with(tktInsertRtnCode, null));

		if (log.isDebugEnabled()) {
			for (Map.Entry<String, Object> entry : result.entrySet()) {
				log.debug("result: {}={}", entry.getKey(), entry.getValue());
			}
		}

		// get return code
		final Object rtnCode = result.getOrDefault(tktInsertRtnCode, null);

		log.info("execute insert Tkt purchase record returns: {}", rtnCode);
		if (null != rtnCode) {
			try {
				return Integer.parseInt(rtnCode.toString());
			} catch (NumberFormatException nfe) {
				log.error("returnCode formatting error", nfe);
				return -99;
			}
		}
		return -99;

	}

	protected boolean validate(InsertTktPurchaseRecord insertTktPurchase) {
		final String pnrNo = insertTktPurchase.getPnrNo();
		final String tktId = insertTktPurchase.getTktId();
		final String equipId = insertTktPurchase.getEquipId();
		final String firDateTime = insertTktPurchase.getFirDateTime();
		final String departDate = insertTktPurchase.getDepartDate();
		final String departStation = insertTktPurchase.getDepartStation();
		final String arrivalStation = insertTktPurchase.getArrivalStation();

		if (pnrNo == null 
				|| tktId == null
				|| equipId == null
				|| firDateTime == null
				|| departDate == null
				|| departStation == null
		        || arrivalStation == null) {
		    log.warn("request param null");
			return false;			
		}
		
		if(!ParamValidateUtil.isValidPnrNo(pnrNo)
		        || !ParamValidateUtil.isValidTktId(tktId)
		        || !ParamValidateUtil.isValidEquipId(equipId)
		        || !ParamValidateUtil.isValidStation(departStation)
		        || !ParamValidateUtil.isValidStation(arrivalStation)) {
		    log.warn("request param invalidate");
			return false;
		}
		
		if(!ParamValidateUtil.isValidTimestamp(firDateTime) || !ParamValidateUtil.isValidDate(departDate)) {
		    log.warn("request param timeString invalidate");
		    return false;
		}
		
		return true;

	}

	protected InsertTktPurchaseRecordErrorCode rtnCode2ErrorCode(int rtnCode) {
		switch (rtnCode) {
		case 0:
			return InsertTktPurchaseRecordErrorCode.NO_ERROR;
		case -1:
			return InsertTktPurchaseRecordErrorCode.INSERT_FAIL_ORACLE_EXCEPTION;
		case -99:
		    return InsertTktPurchaseRecordErrorCode.UNDEFINED_RETURN;
		default:
			return InsertTktPurchaseRecordErrorCode.UNDEFINED_RETURN;
		}

	}

	protected String errorCode2Msg(InsertTktPurchaseRecordErrorCode error) {
		switch (error) {
		case NO_ERROR:
			return "新增成功";
		case INVALID_INPUT_DATA:
		    return "輸入資料驗證有誤";
		case INSERT_FAIL_ORACLE_EXCEPTION:
			return "新增失敗";
		case UNDEFINED_RETURN:
			return "錯誤(非定義的回傳值)";
			
		default:
			return "unknown error code: " + error.getValue();

		}

	}

}

