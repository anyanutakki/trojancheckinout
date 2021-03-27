package cs310.trojancheckinout;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.endsWith;

public class DeleteAccountLogIn {
    @Rule
    public ActivityScenarioRule<LogInActivity> activityRule =
            new ActivityScenarioRule<>(LogInActivity.class);

    @Test
    public void listGoesOverTheFold() {
        Espresso.onView(withId(R.id.email_address_edit)).perform(typeText("gauritest@usc.edu"));
        Espresso.onView(withId(R.id.password_edit)).perform(typeText("gauri"));
        Espresso.onView(withId(R.id.loginButton)).perform(click());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Espresso.onView(withText(endsWith("SHOW PROFILE"))).perform(click());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Espresso.onView(withText(endsWith("Delete Account"))).perform(click());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Espresso.onView(withText(endsWith("Email Address: "))).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.email_address_edit)).perform(typeText("gauritest@usc.edu"));
        Espresso.onView(withId(R.id.password_edit)).perform(typeText("gauri"));
        Espresso.onView(withId(R.id.loginButton)).perform(click());
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Espresso.onView(withId(R.id.error_text)).check(matches(withText("Email Address not found")));
    }
}
