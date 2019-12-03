#!/bin/bash


OAUTH2_SERVER="https://localhost:8243/token";
CONSUMER_KEY=jxPDMoLpQfX2Yz3XIofjy3pIVjAa
CONSUMER_SECRET=SBdmhjI31Wsqgxz0PXP28BmRyLka

JSON=$(curl -k -X POST $OAUTH2_SERVER -d "grant_type=client_credentials" -H"Authorization: Basic $(echo -n "$CONSUMER_KEY:$CONSUMER_SECRET"|base64)");

TOKEN_OAUTH=$(echo $JSON|sed -e "s/^.*\"access_token\":\"//g"|sed -e "s/\".*$//g");
echo "Token to be used:$TOKEN_OAUTH";

API_RESPONSE=$(curl -X GET https://localhost:8243/order/1.0.0 -H "Authorization: Bearer $(echo $TOKEN_OAUTH)" --insecure);

echo $API_RESPONSE;
