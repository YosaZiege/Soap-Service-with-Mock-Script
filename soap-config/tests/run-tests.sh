#!/bin/bash

echo " Waiting for mock server to start..."
sleep 3

echo " Testing eligibility request..."
RESPONSE_ELIGIBLE=$(curl -s -X POST http://localhost:8080/soap-pret/ws \
  -H 'Content-Type: text/xml' \
  --data @eligible-request.xml)

echo "$RESPONSE_ELIGIBLE"
echo

if echo "$RESPONSE_ELIGIBLE" | grep -q "<pret:isEligible>true</pret:isEligible>"; then
  echo "✅ Eligibility test passed"
else
  echo "❌ Eligibility test failed"
fi

echo





