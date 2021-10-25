package br.com.restassuredapitesting.tests.booking.payloads;

import org.json.JSONObject;

public class BookingPayloads {

    public static JSONObject validBookingPayload1() {
        JSONObject payload = new JSONObject();
        JSONObject bookingDates = new JSONObject();

        bookingDates.put("checkin", "2020-01-01");
        bookingDates.put("checkout", "2021-01-01");

        payload.put("firstname", "Teste1");
        payload.put("lastname", "Testando1");
        payload.put("totalprice", 111);
        payload.put("depositpaid", true);
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", "breakfast1");

        return payload;
    }

    public static JSONObject validBookingPayload2() {
        JSONObject payload = new JSONObject();
        JSONObject bookingDates = new JSONObject();

        bookingDates.put("checkin", "2021-02-02");
        bookingDates.put("checkout", "2002-02-02");

        payload.put("firstname", "Teste2");
        payload.put("lastname", "Testando2");
        payload.put("totalprice", 222);
        payload.put("depositpaid", true);
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", "breakfast2");

        return payload;
    }

    public static JSONObject invalidBookingPayload() {
        JSONObject payload = new JSONObject();

        payload.put("nameTeste", "Invalid");
        payload.put("lastname", 1234);
        payload.put("totalprice", "pago");
        payload.put("depositpaid", true);
        payload.put("checkin", "2020-01-01");
        payload.put("checkout", "2021-01-01");
        payload.put("additionalneeds", "Este payload é inválido");

        return payload;
    }

    public static JSONObject invalidBookingPayloadExtraParams() {
        JSONObject payload = new JSONObject();
        JSONObject bookingDates = new JSONObject();

        bookingDates.put("checkin", "2020-01-01");
        bookingDates.put("checkout", "2021-01-01");

        payload.put("firstname", "Teste1");
        payload.put("lastname", "Testando1");
        payload.put("country", "Russia");
        payload.put("totalprice", 222);
        payload.put("depositpaid", true);
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", "Este payload tem parâmetros extras");
        payload.put("Children", 3);

        return payload;
    }
}