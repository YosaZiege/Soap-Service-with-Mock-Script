#!/bin/bash

set -e

echo "Testing eligibility request..."
RESPONSE1=$(curl -s -X POST http://localhost:8080/soap-pret/ws \
  -H 'Content-Type: text/xml' \
  --data-binary @eligible-request.xml)

echo "$RESPONSE1" | grep -q "<pret:isEligible>true</pret:isEligible>" || {
  echo "❌ Eligibility test failed"
  exit 1
}
echo "✅ Eligibility test passed"

echo "Testing ineligibility request..."
RESPONSE2=$(curl -s -X POST http://localhost:8080/soap-pret/ws \
  -H 'Content-Type: text/xml' \
  --data-binary @ineligible-request.xml)

echo "$RESPONSE2" | grep -q "<pret:isEligible>false</pret:isEligible>" || {
  echo "❌ Ineligibility test failed"
  exit 1
}
echo "✅ Ineligibility test passed"



