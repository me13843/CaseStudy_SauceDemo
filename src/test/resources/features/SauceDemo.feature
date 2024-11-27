Feature: SauceDemo Portal Automation
  As a user
  I want to verify the functionality of the SauceDemo portal
  So that I can ensure all key workflows work as expected

  @LaunchURL
  Scenario: Verify the portal title
    Given I navigate to the URL "https://www.saucedemo.com/"
    Then the page title should be "Swag Labs"

  @Login
  Scenario Outline: Verify user login functionality
    Given I am on the SauceDemo login page
    When I enter username "<username>" and password "<password>"
    And I click the login button
    Then I should be logged in successfully

    Examples:
      | username       | password       |
      | standard_user  | secret_sauce   |

  @AddToCart
  Scenario: Add multiple items to the cart
    Given I am logged in to the portal
    When I add the items "Sauce Labs Backpack" and "Sauce Labs Bike Light" to the cart
    Then the cart should display 2 items

  @Checkout
  Scenario: Perform checkout and verify order message
    #Given I have items in my cart
    When I click on the checkout button
    And I provide the details:
      | First Name | Muthuraman      |
      | Last Name  | Esakkimuthu       |
      | Zip Code   | B3H4K8     |
    And I click on the continue button
    And I click on the finish button
    Then the order confirmation message should be "Thank you for your order!"
