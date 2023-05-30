# Address API Spec

## Create Address

Endpoint : POST /api/contacts/{idContact}/addresses

Request Header :
- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "street" : "jalan",
  "city" : "",
  "province" : "",
  "country" : "",
  "postal_code" : ""
}
```

Response Body (Success) :

```json
{
  "data" : {
    "id" : "Random String",
    "street" : "jalan",
    "city" : "",
    "province" : "",
    "country" : "",
    "postal_code" : ""
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "Contact is not found"
}
```

## Update Address

Endpoint : PUT /api/contacts/{idContact}/addresses

Request Header :
- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "street" : "jalan",
  "city" : "",
  "province" : "",
  "country" : "",
  "postal_code" : ""
}
```

Response Body (Success) :

```json
{
  "data" : {
    "id" : "Random String",
    "street" : "jalan",
    "city" : "",
    "province" : "",
    "country" : "",
    "postal_code" : ""
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "Address is not found"
}
```

## Get Address

Endpoint : GET /api/contacts/{idContact}/addresses/{idAddress}

Request Header :
- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" : {
    "id" : "Random String",
    "street" : "jalan",
    "city" : "",
    "province" : "",
    "country" : "",
    "postal_code" : ""
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "Address is not found"
}
```

## Remove Address

Endpoint : DELETE /api/contacts/{idContact}/addresses/{idAddress}

Request Header :
- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" : "Oke"
}
```

Response Body (Failed) :

```json
{
  "errors" : "Address is not found"
}
```

## List Address

Endpoint : GET /api/contacts/{idContact}/addresses

Request Header :
- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" : [
    {
      "id" : "Random String",
      "street" : "jalan",
      "city" : "",
      "province" : "",
      "country" : "",
      "postal_code" : ""
    }
  ]
}
```

Response Body (Failed) :

```json
{
  "errors" : "Contact is not found"
}
```
