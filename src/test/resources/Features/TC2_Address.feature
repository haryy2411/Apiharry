@Address
Feature: Address module API automation

  Scenario Outline: Verify add new address to the application through api
    Given User should add header and bearer authorization for accessing address endpoints
    When User should add request body for add new address "<first_name>","<last_name>","<mobile>","<apartment>","<state>","<city>","<country>","<zipcode>","<address>"and"<addressType>"
    When User should send "POST" request for add new address
    Then User should verify the status code is 200
    And User should verify the created address response message matches "Address added successfully"

    Examples: 
      | first_name | last_name | mobile     | apartment  | state | city | country | zipcode | address          | addressType |
      | Hareesh    | kumar     | 9789939905 | WhiteHOuse |    33 | 3378 |     101 |  600044 | New York Nagaram | Vacation    |

  Scenario Outline: Verify update address to the application through api
    Given User should add header and bearer authorization for accessing address endpoints
    When User should add request body for add update address "<first_name>","<last_name>","<mobile>","<apartment>","<state>","<city>","<country>","<zipcode>","<address>"and"<addressType>"
    When User should send "PUT" request for add update address
    Then User should verify the status code is 200
    And User should verify the update address response message matches "Address updated successfully"

    Examples: 
      | first_name | last_name | mobile    | apartment  | state | city | country | zipcode | address      | addressType |
      | Hareesh    | kumar     | 258963147 | BlackHouse |    33 | 3378 |     101 |  600044 | Kancheepuram | Home        |

  Scenario: Verify delete address to the application through api
    Given User should add header and bearer authorization for accessing address endpoints
    When User should add request body for delete address address_id
    When User should send "DELETE" request for delete address
    Then User should verify the status code is 200
    And User should verify the delete address response message matches "Address deleted successfully"

  Scenario: Verify get address to the application through api
    Given User should add header and bearer authorization for accessing address endpoints
    When User should send "GET" request for get address
    Then User should verify the status code is 200
    And User should verify the get address response message matches "OK"
