# Guidance on how to use this

## Generating a "Billing" record

### Locally
`curl -X POST -d "{\"callerName\": \"Jack\", \"itemCount\": 10, \"itemName\": \"Pencil\"}" -H "Content-type: application/json" http://localhost:8082/billing/saveentry `

### In the cloud - assuming EXTERNAL_IP is set
`curl -X POST -d "{\"callerName\": \"Jack\", \"itemCount\": 10, \"itemName\": \"Pencil\"}" -H "Content-type: application/json" https://store.$EXTERNAL_IP.nip.io/log/billing/saveentry `


## Checking on the billing state info

### Locally
`curl  http://localhost:8082/billing/billinginfo`

### In the cloud - assuming EXTERNAL_IP is set

`curl -i -k https://store.$EXTERNAL_IP.nip.io/log/billing/billinginfo`

## Checking on the status of the billing service

### Locally

`curl -X GET http://localhost:8082/status`

### In the cloud - assuming EXTERNAL_IP is set
`curl -i -k https://store.$EXTERNAL_IP.nip.io/log/status`

## Billing service metrics

### Locally

`curl -X GET http://localhost:9082/metrics`

### In the cloud - assuming EXTERNAL_IP is set
`curl -i -k https://store.$EXTERNAL_IP.nip.io/logmgt/metrics`

## Billing service health

### Locally

`curl -X GET http://localhost:9082/health`

### In the cloud - assuming EXTERNAL_IP is set
`curl -i -k https://store.$EXTERNAL_IP.nip.io/logmgt/health`

## Disclaimer

ORACLE AND ITS AFFILIATES DO NOT PROVIDE ANY WARRANTY WHATSOEVER, EXPRESS OR IMPLIED, FOR ANY SOFTWARE, MATERIAL OR CONTENT OF ANY KIND CONTAINED OR PRODUCED WITHIN THIS REPOSITORY, AND IN PARTICULAR SPECIFICALLY DISCLAIM ANY AND ALL IMPLIED WARRANTIES OF TITLE, NON-INFRINGEMENT, MERCHANTABILITY, AND FITNESS FOR A PARTICULAR PURPOSE. FURTHERMORE, ORACLE AND ITS AFFILIATES DO NOT REPRESENT THAT ANY CUSTOMARY SECURITY REVIEW HAS BEEN PERFORMED WITH RESPECT TO ANY SOFTWARE, MATERIAL OR CONTENT CONTAINED OR PRODUCED WITHIN THIS REPOSITORY. IN ADDITION, AND WITHOUT LIMITING THE FOREGOING, THIRD PARTIES MAY HAVE POSTED SOFTWARE, MATERIAL OR CONTENT TO THIS REPOSITORY WITHOUT ANY REVIEW. USE AT YOUR OWN RISK.

## Copyright

Copyright (c) 2024 Oracle and/or its affiliates. All rights reserved.
The Universal Permissive License (UPL), Version 1.0