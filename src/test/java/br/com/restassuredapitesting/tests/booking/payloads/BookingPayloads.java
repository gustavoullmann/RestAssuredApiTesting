package br.com.restassuredapitesting.tests.booking.payloads;

import org.json.JSONObject;

public class BookingPayloads {

    public static JSONObject payloadValidBookingToken() {
        JSONObject payload = new JSONObject();
        JSONObject bookingDates = new JSONObject();

        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2019-01-01");

        payload.put("firstname", "Cristiano");
        payload.put("lastname", "Ronaldo");
        payload.put("totalprice", 111);
        payload.put("depositpaid", true);
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", "token");

        return payload;
    }

    public static JSONObject payloadCreateValidBooking() {
        JSONObject payload = new JSONObject();
        JSONObject bookingDates = new JSONObject();

        bookingDates.put("checkin", "2020-01-01");
        bookingDates.put("checkout", "2021-01-01");

        payload.put("firstname", "Teste");
        payload.put("lastname", "Testando");
        payload.put("totalprice", 222);
        payload.put("depositpaid", true);
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", "token");

        return payload;
    }

    public static JSONObject payloadCreateInvalidBooking() {
        JSONObject payload = new JSONObject();
        JSONObject bookingDates = new JSONObject();

        bookingDates.put("checkin", "2020-01-01");
        bookingDates.put("checkout", "2021-01-01");

        payload.put("nameTeste", "Teste");
        payload.put("lastname", 1234);
        payload.put("totalprice", "pago");
        payload.put("depositpaid", true);
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", "token");

        return payload;
    }

    public static JSONObject payloadValidBookingAuthorization() {
        JSONObject payload = new JSONObject();
        JSONObject bookingDates = new JSONObject();

        bookingDates.put("checkin", "2000-01-01");
        bookingDates.put("checkout", "2001-01-01");

        payload.put("firstname", "Update");
        payload.put("lastname", "Authorisation");
        payload.put("totalprice", 333);
        payload.put("depositpaid", true);
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", "Authorisation");

        return payload;
    }
}