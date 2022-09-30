@Login
Feature: Login module API automation

Scenario: Get user logtoken from login endpoint
Given User add header
And User add basic authentication for login
When User send "POST" request for login endpoint
Then User should verify the status code is 200
And User verify the login response body firstname present as "Hareesh" and get the logtoken