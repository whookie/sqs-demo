*** Settings ***
Library    RequestsLibrary
Library    DateTime

*** Variables ***
${PAGE URL}    http://localhost:8080
${BROWSER}     chrome

*** Test Cases ***
Verify landing page responds within 500ms
    Create Session            landingpage    ${PAGE URL}
    ${response time}=         Make Request And Measure Response Time    landingpage    GET    /
    Should Be True            ${response time} < 0.500

Verify adding new information responds within 500ms
    [Teardown]    Remove Information Entry    adding    1.1.1.1    Test Key
    ${data eval}=             Evaluate    {'address': '1.1.1.1', 'name': 'Test Key', 'value': 'Test Value'}
    Create Session            adding       ${PAGE URL}
    ${response time}=         Make Request And Measure Response Time    adding    POST    /information/add        params=address=1.1.1.1&name=Test+Key&value=Test+Value
    Should Be True            ${response time} < 0.500

Verify deleting new information responds within 500ms
    [Teardown]    Remove Information Entry    deleting    1.1.1.1    Test Key
    ${data eval}=             Evaluate    {'address': '1.1.1.1', 'name': 'Test Key'}
    Create Session            deleting    ${PAGE URL}
    Add Information Entry     deleting    1.1.1.1    Test Key    Test Value
    ${response time}=         Make Request And Measure Response Time    deleting    DELETE    /information/delete    params=address=1.1.1.1&name=Test+Key
    Should Be True            ${response time} < 0.500

Verify getting information responds within 500ms
    [Teardown]    Remove Information Entry    fetching    1.1.1.1    Test Key
    Create Session            fetching    ${PAGE URL}
    Add Information Entry     fetching    1.1.1.1    Test Key    Test Value
    ${response time}=         Make Request And Measure Response Time    fetching    GET    /information/get    params=address=1.1.1.1
    Should Be True            ${response time} < 0.500

*** Keywords ***
Make Request And Measure Response Time
    [Arguments]    ${session}    ${method}    ${path}    ${params}=
    ${start time}=            Get Current Date
    ${response}=              Run Keyword   ${method} On Session    alias=${session}    url=${path}    params=${params}    expected_status=200
    ${end time}=              Get Current Date
    ${duration}=              Subtract Date From Date    ${end time}    ${start time}
    [Return]                  ${duration}

Add Information Entry
    [Arguments]    ${session}    ${address}    ${key}    ${value}
    ${data eval}=    Evaluate    {'address': '${address}', 'name': '${key}', 'value': '${value}'}
    POST On Session    alias=${session}    url=/information/add    data=${data eval}

Remove Information Entry
    [Arguments]    ${session}    ${address}    ${key}
    ${data eval}=    Evaluate    {'address': '${address}', 'name': '${key}'}
    DELETE On Session    alias=${session}    url=/information/delete    data=${data eval}
