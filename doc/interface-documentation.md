# Programming Interface Documentation

## Endpoint: `/information/add`
Add a new custom information field to an IP address

- Parameters:
    - `address` - Valid IPv4 address using common notation
        - Example: `1.1.1.1`
    - `name` - Name for the custom information field
        - Example: `My Field`
    - `value` - Value of the custom information field identified by name
        - Example: `My Value`
- Response: None
- Possible Error Codes:
    - `200`: Success
    - `400`: IP Address invalid

```
curl \
    --data "address=1.1.1.1" \
    --data "name=My+Field" \
    --data "value=My+Value" \
    http://localhost:8080/information/add
```

## Endpoint: `/information/delete`
Delete a previously created custom information field belonging to an address

- Parameters:
    - `address` - Valid IPv4 address using common notation
        - Example: `1.1.1.1`
    - `name` - Name of the custom field
        - Example: `My Field`
- Response: None
- Possible Error Codes:
    - `200`: Success
    - `400`: IP Address Invalid

```
> curl \
    --data "address=1.1.1.1" \
    --data "name=My+Field" \
    -X "DELETE" \
    http://localhost:8080/information/delete
```

## Endpoint: `/information/get`
Get all associated fields for a single IP address.

- Parameters:
    - `address` - Valid IPv4 address using common notation
        - Example: `1.1.1.1`
- Response: JSON from IPAPI, with an added `fields` key containing a JSON object of all added
  key-value pairs for this address.
- Possible Error Codes:
    - `200`: Success
    - `400`: IP Address Invalid
    - `404`: No entries for this address
    - `503`: Remote API unreachable

```
> curl \
    --data "address=1.1.1.1" \
    --get http://localhost:8080/information/get
-----------------------------------------------
{
    "ip": "1.1.1.1",
    [...]
    "fields": {
        "My Field": "My Value",
        [...]
    }
}
```