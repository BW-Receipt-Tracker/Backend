## Backend

* BaseUrl: http://project-receipt-tracker.herokuapp.com

# Endpoints

User Endpoints:
* POST /login 
  * Requires JSON User body username, password  returns access_token
* POST /createnewuser 
  * Requires JSON User body primaryemail, username, password  returns access_token
  
| RequestType | EndPoint       | Request Example                                                                                                                                                                                                                                                                                                                                                                                                                     | Response Example                                                                                                                                                        |
|-------------|----------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| POST        | /createnewuser | @Body JSON Object {         "primaryemail" : "demo@demo.com",         "username" : "demo",         "password" : "demo" }                                                                                                                                                                                                                                                                                                            | Status: 201 Created {     "access_token": "e73f96a6-d60d-4abb-9802-1a567c7f97da",     "token_type": "bearer",     "expires_in": 3599,     "scope": "read trust write" } |
| POST        | /login         | @Headers (These login headers will always be static) Key: Content-Type   Value: application/x-www-form-urlencoded Key: Authorization   Value: Basic bGFtYmRhLWNsaWVudDpsYW1iZGEtc2VjcmV0  @Body (the content type here is x-www-form-urlencoded) Key: grant_type   Value: password   (this key and value will be the same for everyone) Key: username   Value: demo   (your username) Key: password   Value: demo   (your password) | Status: 200 OK {     "access_token": "e73f96a6-d60d-4abb-9802-1a567c7f97da",     "token_type": "bearer",     "expires_in": 2625,     "scope": "read trust write" }      |


Receipt Endpoints:
* GET /receipts/receipts
  * Gets all User Specific Receipts 
* GET /receipts/receipt/{receipt's id}
  * Gets receipt by id
* PUT /receipts/receipt/{id}
  * (update receipt)
  * Requires changes in JSON receipt body
* POST /receipts/receipt 
  * (adds new receipt)
  * Requires JSON body receipt
* DELETE /receipts/receipt/delete/{receipt's id}
