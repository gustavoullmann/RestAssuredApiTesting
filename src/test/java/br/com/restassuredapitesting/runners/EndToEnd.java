package br.com.restassuredapitesting.runners;

import br.com.restassuredapitesting.tests.booking.tests.DeleteBookingTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.restassuredapitesting.suites.EndToEnd.class)
@Suite.SuiteClasses({
        DeleteBookingTest.class
})
public class EndToEnd {
}