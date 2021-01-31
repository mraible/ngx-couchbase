import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the FieldTestMapstructAndServiceClassEntity entity.
 */
class FieldTestMapstructAndServiceClassEntityGatlingTest extends Simulation {

    val context: LoggerContext = LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]
    // Log all HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("TRACE"))
    // Log failed HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("DEBUG"))

    val baseURL = Option(System.getProperty("baseURL")) getOrElse """http://localhost:8080"""

    val httpConf = http
        .baseUrl(baseURL)
        .inferHtmlResources()
        .acceptHeader("*/*")
        .acceptEncodingHeader("gzip, deflate")
        .acceptLanguageHeader("fr,fr-fr;q=0.8,en-us;q=0.5,en;q=0.3")
        .connectionHeader("keep-alive")
        .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:33.0) Gecko/20100101 Firefox/33.0")
        .silentResources // Silence all resources like css or css so they don't clutter the results

    val headers_http = Map(
        "Accept" -> """application/json"""
    )

    val headers_http_authentication = Map(
        "Content-Type" -> """application/json""",
        "Accept" -> """application/json"""
    )

    val headers_http_authenticated = Map(
        "Accept" -> """application/json""",
        "Authorization" -> "${access_token}"
    )

    val scn = scenario("Test the FieldTestMapstructAndServiceClassEntity entity")
        .exec(http("First unauthenticated request")
        .get("/api/account")
        .headers(headers_http)
        .check(status.is(401))
        ).exitHereIfFailed
        .pause(10)
        .exec(http("Authentication")
        .post("/api/authenticate")
        .headers(headers_http_authentication)
        .body(StringBody("""{"username":"admin", "password":"admin"}""")).asJson
        .check(header("Authorization").saveAs("access_token"))).exitHereIfFailed
        .pause(2)
        .exec(http("Authenticated request")
        .get("/api/account")
        .headers(headers_http_authenticated)
        .check(status.is(200)))
        .pause(10)
        .repeat(2) {
            exec(http("Get all fieldTestMapstructAndServiceClassEntities")
            .get("/api/field-test-mapstruct-and-service-class-entities")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new fieldTestMapstructAndServiceClassEntity")
            .post("/api/field-test-mapstruct-and-service-class-entities")
            .headers(headers_http_authenticated)
            .body(StringBody("""{
                "id":"SAMPLE_TEXT"
                , "stringEva":"SAMPLE_TEXT"
                , "stringRequiredEva":"SAMPLE_TEXT"
                , "stringMinlengthEva":"SAMPLE_TEXT"
                , "stringMaxlengthEva":"SAMPLE_TEXT"
                , "stringPatternEva":"SAMPLE_TEXT"
                , "integerEva":"0"
                , "integerRequiredEva":"0"
                , "integerMinEva":"0"
                , "integerMaxEva":"0"
                , "longEva":null
                , "longRequiredEva":null
                , "longMinEva":null
                , "longMaxEva":null
                , "floatEva":null
                , "floatRequiredEva":null
                , "floatMinEva":null
                , "floatMaxEva":null
                , "doubleRequiredEva":null
                , "doubleMinEva":null
                , "doubleMaxEva":null
                , "bigDecimalRequiredEva":"0"
                , "bigDecimalMinEva":"0"
                , "bigDecimalMaxEva":"0"
                , "localDateEva":"2020-01-01T00:00:00.000Z"
                , "localDateRequiredEva":"2020-01-01T00:00:00.000Z"
                , "instantEva":"2020-01-01T00:00:00.000Z"
                , "instanteRequiredEva":"2020-01-01T00:00:00.000Z"
                , "zonedDateTimeEva":"2020-01-01T00:00:00.000Z"
                , "zonedDateTimeRequiredEva":"2020-01-01T00:00:00.000Z"
                , "durationEva":null
                , "durationRequiredEva":null
                , "booleanEva":null
                , "booleanRequiredEva":null
                , "enumEva":"ENUM_VALUE_1"
                , "enumRequiredEva":"ENUM_VALUE_1"
                , "uuidEva":null
                , "uuidRequiredEva":null
                , "byteImageEva":null
                , "byteImageRequiredEva":null
                , "byteImageMinbytesEva":null
                , "byteImageMaxbytesEva":null
                , "byteAnyEva":null
                , "byteAnyRequiredEva":null
                , "byteAnyMinbytesEva":null
                , "byteAnyMaxbytesEva":null
                , "byteTextEva":null
                , "byteTextRequiredEva":null
                }""")).asJson
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_fieldTestMapstructAndServiceClassEntity_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created fieldTestMapstructAndServiceClassEntity")
                .get("${new_fieldTestMapstructAndServiceClassEntity_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created fieldTestMapstructAndServiceClassEntity")
            .delete("${new_fieldTestMapstructAndServiceClassEntity_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(Integer.getInteger("users", 100)) during (Integer.getInteger("ramp", 1) minutes))
    ).protocols(httpConf)
}
