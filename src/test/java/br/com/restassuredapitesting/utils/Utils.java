package br.com.restassuredapitesting.utils;

import br.com.restassuredapitesting.tests.booking.payloads.BookingPayloads;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.PostBookingRequest;
import org.json.JSONObject;

import static org.hamcrest.CoreMatchers.notNullValue;

public class Utils {

    GetBookingRequest getBookingRequest = new GetBookingRequest();
    PostBookingRequest postBookingRequest = new PostBookingRequest();

    public static String getSchemaBasePath(String pack, String nameSchema) {

        return System.getProperty("user.dir")
                + "/src/test/java/br/com/restassuredapitesting/tests/"
                + pack
                + "/schema/"
                + nameSchema
                + ".json";
    }

    public int returnBookingIdsListFirstId() {

        Integer firstId = getBookingRequest.getBookingIdsList()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");
        if(firstId == null) {
            firstId = createBookingIfIdListIsEmpty();
        }
        return firstId.intValue();
    }

    public Integer createBookingIfIdListIsEmpty() {

        JSONObject booking1 = BookingPayloads.validBookingPayload1();
        postBookingRequest.postCreateBooking(booking1)
                .then()
                .statusCode(200)
                .body("bookingid", notNullValue());

        Integer firstId = returnBookingIdsListFirstId();

        return firstId;
    }
}