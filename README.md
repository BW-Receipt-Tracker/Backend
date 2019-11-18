## Backend

* BaseUrl: http://project-receipt-tracker.herokuapp.com

# Endpoints

User Endpoints:
* POST /login Requires JSON User body username, password  returns access_token
* POST /createnewuser Requires JSON User body primaryemail, username, password  returns access_token


Receipt Endpoints:
* GET All Receipts /receipts/receipts
* GET Receipt By ID /receipts/receipt/{receipt's id}
* PUT (update receipt) /receipts/receipt/{id}  Requires changes in JSON receipt body
* POST (add new receipt /receipts/receipt/{user's id}  Requires JSON body receipt
* DELETE /receipts/receipt/delete/{receipt's id}
