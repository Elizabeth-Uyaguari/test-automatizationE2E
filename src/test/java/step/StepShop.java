package step;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import navigate.Navigate;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.SearchableTarget;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.Wait;
import org.htmlunit.javascript.host.performance.Performance;

public class StepShop {

    /**
     * Prepara el escenario para la prueba, configurando actores
     */
    @Before
    public void init() {
        OnStage.setTheStage(new OnlineCast());
    }

    /**
     * Abre la pagina principal, espera 5 segundos y se asigna un actor
     *
     * @param actor
     */
    @Given("^(.*) go to main page")
    public void goMainPage(String actor) {
        Wait.until(() -> true).forNoMoreThan(5).seconds();
        OnStage.theActorCalled(actor).attemptsTo(Navigate.openPage());
    }

    @When("select product")
    public void selectProduct() {
        Performable action = Click.on(By.cssSelector("#content > div.row > div:nth-child(2) > div > div.image > a > img"));
        OnStage.theActorInTheSpotlight().attemptsTo(action);
    }

    @Then("add product to cart")
    public void addProductToCart() {
        Performable action = Click.on(By.cssSelector("#button-cart"));
        OnStage.theActorInTheSpotlight().attemptsTo(action);
    }

    @And("back to main page")
    public void backToMainPage() {
        OnStage.theActorInTheSpotlight().attemptsTo(Navigate.openPage());
    }

    @And("select second product")
    public void selectSecondProduct() {
        Performable action = Click.on(By.cssSelector("#content > div.row > div:nth-child(4) > div > div.image > a > img"));
        OnStage.theActorInTheSpotlight().attemptsTo(action);
    }

    @And("select product color")
    public void selectProductColor() {
        Performable action = SelectFromOptions.byVisibleText("Red").from(By.cssSelector("#input-option226"));
        OnStage.theActorInTheSpotlight().attemptsTo(action);
    }

    @Then("add product to cart again")
    public void addProductToCartAgain() {
        Performable action = Click.on(By.cssSelector("#button-cart"));
        OnStage.theActorInTheSpotlight().attemptsTo(action);
    }


    @Then("go to shop cart")
    public void goToShopCart() {
        Performable action = Click.on(By.id("cart-total"));
        Performable action1 = Click.on(By.cssSelector("#cart > ul > li:nth-child(2) > div > p > a:nth-child(1) > strong"));
        OnStage.theActorInTheSpotlight().attemptsTo(action, action1);
    }

    @And("go to checkout")
    public void goToCheckout() {
        Performable action = Click.on(By.cssSelector("#content > div.buttons.clearfix > div.pull-right > a"));
        OnStage.theActorInTheSpotlight().attemptsTo(action);
    }

    @And("select guest checkout")
    public void selectGuestCheckout() {
        Performable action = Click.on(By.cssSelector("#collapse-checkout-option > div > div > div:nth-child(1) > div:nth-child(4) > label > input[type=radio]"));
        OnStage.theActorInTheSpotlight().attemptsTo(action);
    }

    @And("continue to billing details")
    public void continueToBillingDetails() {
        Performable action = Click.on(By.id("button-account"));
        OnStage.theActorInTheSpotlight().attemptsTo(action);
    }

    public Performable fillOutInformation(String valor, String id) {
        return Enter.theValue(valor).into(By.id(id));
    }


    @And("fill out personal information {string}, {string}, {string}, {string}")
    public void fillOutPersonalInformation(String nombre, String apellido, String correo, String telefono) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                fillOutInformation(nombre, "input-payment-firstname"),
                fillOutInformation(apellido, "input-payment-lastname"),
                fillOutInformation(correo, "input-payment-email"),
                fillOutInformation(telefono, "input-payment-telephone")
        );
    }

    @And("fill out address information {string}, {string}, {string}")
    public void fillOutAddressInformation(String direccion1, String ciudad, String codigoPostal) {

        Performable action = SelectFromOptions.byVisibleText("Ecuador").from(By.id("input-payment-country"));
        Performable action2 = SelectFromOptions.byVisibleText("Azuay").from(By.id("input-payment-zone"));
        OnStage.theActorInTheSpotlight().attemptsTo(
                fillOutInformation(direccion1, "input-payment-address-1"),
                fillOutInformation(ciudad, "input-payment-city"),
                fillOutInformation(codigoPostal, "input-payment-postcode"),
                action,
                action2
        );
    }

    @And("continue to delivery method")
    public void continueToDeliveryMethod() {
        Performable action = Click.on(By.id("button-guest"));
        OnStage.theActorInTheSpotlight().attemptsTo(action);
    }

    @And("continue to Payment Method")
    public void continueToPaymentMethod() {
        Performable action = Click.on(By.id("button-shipping-method"));
        OnStage.theActorInTheSpotlight().attemptsTo(action);
    }

    @And("select terms and conditions and continue to confirm order")
    public void selectTermsAndConditions() {
        Performable action = Click.on(By.cssSelector("#collapse-payment-method > div > div.buttons > div > input[type=checkbox]:nth-child(2)"));
        Performable action1 = Click.on(By.id("button-payment-method"));
        OnStage.theActorInTheSpotlight().attemptsTo(action, action1);
    }

    @And("confirm order")
    public void confirmOrder() {
        Performable action = Click.on(By.id("button-confirm"));
        OnStage.theActorInTheSpotlight().attemptsTo(action);
    }

    @And("verify order")
    public void verifyOrder() {
        Target target = Target.the("Your order has been placed!").locatedBy("#content > h1");
        OnStage.theActorInTheSpotlight().attemptsTo(Ensure.that(Text.of(target)).isEqualTo("Your order has been placed!"));
    }

}
