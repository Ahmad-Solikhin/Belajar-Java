# Contact API Spec

## Create Contact

Endpoint : POST /api/contacts

Request Header :
- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "first_name" : "",
  "last_name" : "",
  "email" : "",
  "phone" : ""
}
```

Response Body (Success) :

```json
{
  "data" : {
    "id" : "random string",
    "first_name" : "",
    "last_name" : "",
    "email" : "",
    "phone" : "" 
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : []
}
```

## Update Contact

Endpoint : PUT /api/contacts/{idContacts}

Request Header :
- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "first_name" : "",
  "last_name" : "",
  "email" : "",
  "phone" : ""
}
```

Response Body (Success) :

```json
{
  "data" : {
    "id" : "random string",
    "first_name" : "",
    "last_name" : "",
    "email" : "",
    "phone" : "" 
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : []
}
```

## Get Contact

Endpoint : GET /api/contacts/{idContact}

Request Header :
- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" : {
    "id" : "random string",
    "first_name" : "",
    "last_name" : "",
    "email" : "",
    "phone" : "" 
  }
}
```

Response Body (Failed, 404) :

```json
{
  "errors" : "Contact is not found"
}
```

## Search Contact

Endpoint : GET /api/contacts

Query Param :
- name : String, contact first name or last name, using like query, optional
- phone : String, contact phone, using like query, optional
- email : String, contact email, using like query, optional
- page : integer, starts from 0, default 0, optional
- size : integer, default 10, optional

Request Header :
- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" : [
    {
      "id" : "random string",
      "first_name" : "",
      "last_name" : "",
      "email" : "",
      "phone" : ""
    }
  ],
  "paging" : {
    "current_page" : 0,
    "total_page" : 10,
    "size" : 10
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "Unauthorized"
}
```

## Remove Contact

Endpoint : DELETE /api/contacts/{idContact}

Request Header :
- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" : "Oke"
}
```

Response Body (Failed, 404) :

```json
{
  "errors" : "Contact is not found"
}
```
