package kulongtai;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Rule;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.restassured3.RestDocumentationFilter;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.documentationConfiguration;

/**
 * 业务测试基类
 */
public class TestRestBussinessBase extends TestRestBase {
    static{
        //System.out.println("初始化static");
        //loadProperties();
        //System.out.println("初始化static end");
    }
    protected ObjectMapper objectMapper = new ObjectMapper();
    protected String token;
    protected RequestSpecification spec;
    public final String TOKEN ="Authorization";
    public final String TOKEN_PREFIX = "Bearer ";

    private RestDocumentationFilter documentationFilter;
    // 执行测试方法之前初始化模拟request,response
    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();
    @Before
    public void init() throws Exception {

        super.init();
        //this.getToken();
        setUp();
    }
    public static void loadProperties(){
        try {
            Properties properties = new Properties();
            File f =ResourceUtils.getFile("classpath:test_application.properties");
            System.out.println(f);
            properties.load(new FileInputStream(f));
            signingKey = properties.getProperty("online.ins.signing_key");
            tokenExpired =Long.parseLong(properties.getProperty("online.ins.token_expired")) ;
        }catch (Exception e){
            e.fillInStackTrace();
        }
    }

   // @Value("${online.ins.signing_key}")
    private static String signingKey;
    //@Value("${online.ins.token_expired}")
    private static long tokenExpired;




    public void getToken()throws Exception{
        String token =readFileToken();
       if(token==null||"".equals(token)){//||!isValidToken(token)
           token = getTokenHttp();
           writeFileToken(token);
       }
        this.token = token;
    }
    private String getTokenHttp(){
        Map<String,Object> body = new HashMap();
        body.put("deviceNo","0");
        body.put("fastFlag","false");
        body.put("smsCode","0000");
        body.put("userName","15732168576");
        Response r = RestAssured.given().contentType("application/json").body(body).post("/api/portal/loginByCode");
        r.then().statusCode(200);
        r.prettyPrint();
        Map m =   r.as(Map.class);
        return TOKEN_PREFIX+((Map)m.get("data")).get("accessToken");
    }
    private boolean isValidToken(String token){

        boolean f=false;
        Date expiration = Jwts.parser()
                .setSigningKey(signingKey)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody().getExpiration();
        if(expiration!=null){
            if(expiration.getTime()>=System.currentTimeMillis()){
                f=true;
            }
        }
        return f;
    }
    private String readFileToken()throws Exception{
        File tokenFile =  ResourceUtils.getFile("classpath:token");
        BufferedReader br = new BufferedReader(new FileReader(tokenFile));
        String token = br.readLine();
        br.close();
        return token;
    }
    private void writeFileToken(String token)throws Exception{
        File tokenFile =  ResourceUtils.getFile("classpath:token");
        FileWriter fw = new FileWriter(tokenFile);
        fw.write(token);
        fw.close();
    }

    public void setUp() {
        final String token1 = this.token;
        this.documentationFilter = document("{ClassName}/{methodName}",
                preprocessRequest(),preprocessResponse());
        /*this.documentationFilter = document("{ClassName}/{methodName}",
                preprocessRequest(),preprocessResponse(prettyPrint()));*/
        this.spec = new RequestSpecBuilder()
                .addFilter(documentationConfiguration(this.restDocumentation)
                        .snippets().withEncoding("UTF-8")).addFilter(documentationFilter).setContentType(ContentType.JSON)
                .build();
        //this.spec = new RequestSpecBuilder().addFilter(documentationConfiguration(this.restDocumentation).snippets().withTemplateFormat(TemplateFormats.markdown())).build();
    }
    /*public void setUp() {
        final String token1 = this.token;
        this.documentationFilter = document("{className}/{methodName}",
                preprocessRequest(new OperationPreprocessorAdapter() {
                   private final OperationRequestFactory requestFactory = new OperationRequestFactory();
                    @Override
                    public OperationRequest preprocess(OperationRequest request) {
                        return requestFactory.createFrom(request, this.addHeaders(request.getHeaders()));
                    }
                    private HttpHeaders addHeaders(HttpHeaders originalHeaders) {
                        HttpHeaders processedHeaders = new HttpHeaders();
                        processedHeaders.putAll(originalHeaders);
                        if(!processedHeaders.containsKey(TOKEN)){
                            processedHeaders.add(TOKEN,token1);
                        }
                        return processedHeaders;
                    }
                }),preprocessResponse(new OperationPreprocessorAdapter() {
                    private final OperationResponseFactory responseFactory = new OperationResponseFactory();
                    private  ObjectMapper objectMapper = new ObjectMapper();
                    @Override
                    public OperationResponse preprocess(OperationResponse response){

                        byte[] modifiedContent = new byte[0];
                        try {
                            modifiedContent = this.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(objectMapper.readTree(response.getContent()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return this.responseFactory.createFrom(response, modifiedContent);
                    }
                }));
        this.spec = new RequestSpecBuilder()
                .addFilter(documentationConfiguration(this.restDocumentation)
                        .snippets().withEncoding("UTF-8")).addFilter(documentationFilter).setContentType(ContentType.JSON)
                .build();

        //this.spec = new RequestSpecBuilder().addFilter(documentationConfiguration(this.restDocumentation).snippets().withTemplateFormat(TemplateFormats.markdown())).build();
    }*/

}
