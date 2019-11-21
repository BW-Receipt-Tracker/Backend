## Backend

* BaseUrl: http://project-receipt-tracker.herokuapp.com

# Endpoints

User Endpoints:
* POST /login 
  * Requires JSON User body username, password  returns access_token
* POST /createnewuser 
  * Requires JSON User body primaryemail, username, password  returns access_token

| EndPoint               	| Request Example                                                                                                                                                                                                                                                                                                                                                                                                                                          	| Response Example                                                                                                                                                                          	|
|------------------------	|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	|
| POST<br>/createnewuser 	| @Body JSON Object<br>{<br>        "primaryemail" : "demo@demo.com",<br>        "username" : "demo",<br>        "password" : "demo"<br>}                                                                                                                                                                                                                                                                                                                  	| Status: 201 Created<br>{<br>    "access_token": "e73f96a6-d60d-4abb-9802-1a567c7f97da",<br>    "token_type": "bearer",<br>    "expires_in": 3599,<br>    "scope": "read trust write"<br>} 	|
| POST<br>/login         	| @Headers (These login headers will always be static)<br>Key: Content-Type   Value: application/x-www-form-urlencoded<br>Key: Authorization   Value: Basic bGFtYmRhLWNsaWVudDpsYW1iZGEtc2VjcmV0<br><br>@Body (the content type here is x-www-form-urlencoded)<br>Key: grant_type   Value: password   (this key and value will be the same for everyone)<br>Key: username   Value: demo   (your username)<br>Key: password   Value: demo   (your password) 	| Status: 200 OK<br>{<br>    "access_token": "e73f96a6-d60d-4abb-9802-1a567c7f97da",<br>    "token_type": "bearer",<br>    "expires_in": 2625,<br>    "scope": "read trust write"<br>}      	|

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

| EndPoint                                          	| Request Example                                                                                                                                                                                                                                                                                                                                     	| Response Example                                                                                                                                                                                                                                                                                                                                                                                                                                                 	|
|---------------------------------------------------	|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	|
| GET <br>/receipts/receipts                        	| @Params<br>Key: access_token   Value: dfcd78e6-3215-4b27-8b56-43656e63beb4                                                                                                                                                                                                                                                                          	|  Status: 200 OK<br>[<br>    {<br>        "receiptid": 215,<br>        "date": "November",<br>        "amount": 22.34,<br>        "category": "Grocery",<br>        "merchantname": "GreatValue",<br>        "imageurl": "the URL"<br>    },<br>    {<br>        "receiptid": 222,<br>        "date": "January",<br>        "amount": 22.34,<br>        "category": "Grocery",<br>        "merchantname": "GreatValue",<br>        "imageurl": null<br>    }<br>] 	|
| GET<br>/receipts/receipt/{receipt's id}           	| @Params<br>Key: access_token   Value: dfcd78e6-3215-4b27-8b56-43656e63beb4                                                                                                                                                                                                                                                                          	| {<br>        "receiptid": 215,<br>        "date": "November",<br>        "amount": 22.34,<br>        "category": "Grocery",<br>        "merchantname": "GreatValue",<br>        "imageurl": "the URL"<br>}                                                                                                                                                                                                                                                       	|
| PUT<br>/receipts/receipt/{receipt's id}           	| @Params<br>Key: access_token   Value: dfcd78e6-3215-4b27-8b56-43656e63beb4<br><br>@Headers<br>Key: Content-Type   Value: application/json<br><br>@Body (JSON Object)<br>{<br>        "amount" : "30.66"<br>}                                                                                                                                        	| Currently Returns Status: 201 Created<br>Updates object and whichever fields<br>mentioned.                                                                                                                                                                                                                                                                                                                                                                       	|
| POST<br>/receipts/receipt                         	|  @Params<br>Key: access_token   Value: dfcd78e6-3215-4b27-8b56-43656e63beb4<br><br>@Headers<br>Key: Content-Type   Value: application/json<br><br>@Body (JSON Object)<br>{<br>        "date": "November",<br>        "amount": 18.34,<br>        "category": "Software",<br>        "merchantname": "Amazon",<br>        "imageurl": "the URL"<br>} 	| Status: 201 Created                                                                                                                                                                                                                                                                                                                                                                                                                                              	|
| DELETE<br>/receipts/receipt/delete/{receipt's id} 	| @Params<br>Key: access_token   Value: dfcd78e6-3215-4b27-8b56-43656e63beb4                                                                                                                                                                                                                                                                          	| Status: 200 OK                                                                                                                                                                                                                                                                                                                                                                                                                                                   	|

Additional documentation can be found at http://project-receipt-tracker.herokuapp.com/swagger-ui.html#/
