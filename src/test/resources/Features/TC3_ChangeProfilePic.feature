Feature: Change profile pic module API automation
@pic
  Scenario: Verify change profile pic to the application through api
    Given User add header and bearer authorization and multipart/form-data for accessing changeprofilepic endpoints
    When User send "POST" request for changeprofilepic
    Then User should verify the status code is 200
    And User verify the delete address response message matches "Profile updated Successfully"