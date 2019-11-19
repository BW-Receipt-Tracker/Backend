## Backend

* BaseUrl: http://project-receipt-tracker.herokuapp.com

# Endpoints

User Endpoints:
* POST /login 
  * Requires JSON User body username, password  returns access_token
* POST /createnewuser 
  * Requires JSON User body primaryemail, username, password  returns access_token


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
