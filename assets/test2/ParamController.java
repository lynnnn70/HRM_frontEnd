@RestController
@RequestMapping("api")
public class ParamController{
    public static final String API_PARAM = "ecpQueryParams";

    private final static Logger log = LoggerFactory.getLogger(ParamController.class);

    private String ecpsApiUrl;

    @Autiwired
    private EcpCaller ecpsCaller;

    @PostMapping(path = "params", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(OperationId = "queryParams", summary = "參數下載", description = "呼叫ECPS 參數下載 API 回覆結果"),
                requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = {
                    @Content(examples = {@ExampleObject(name = "normal", values = "") }) }), responses = {
                        @ApiResponse(responseCode = "200", description = "success", content ={
                            @Content(schema = @Schema(implementation = ParamResponse.class))
                        })
                    }
}