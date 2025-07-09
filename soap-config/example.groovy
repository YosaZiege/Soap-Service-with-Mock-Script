import groovy.xml.XmlSlurper

def action = context.request.queryParams.action ?: ""

def requestBody = context.request.body?.toString()

def nom = ""
def age = 0
def nationalite = ""

try {
    def xml = new XmlSlurper().parseText(requestBody)
    def pretRequest = xml.'**'.find { it.name() == 'pretRequest' }

    nom = pretRequest.nom.text()
    age = pretRequest.age.text().toInteger()
    nationalite = pretRequest.nationalite.text()
} catch (Exception e) {
    return respond()
        .withStatusCode(400)
}

switch (action) {
    case "create":
        def eligible = (age >= 18 && nationalite == "Marocaine")
        def reason = eligible ? "Eligible car ta cette ${age}" : "Not eligible car ta cette ${age}"

        respond()
        .withStatusCode(200)
        .withHeader("Content-Type", "text/xml;charset=UTF-8")
        .withContent("""
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                            xmlns:pret="http://soap.example.com/pret">
            <soapenv:Body>
                <pret:pretResponse>
                <pret:isEligible>${eligible}</pret:isEligible>
                <pret:reason>${reason}</pret:reason>
                </pret:pretResponse>
            </soapenv:Body>
            </soapenv:Envelope>
        """)
        break


    default:
        return respond()
            .withStatusCode(200)
            .withHeader("Content-Type", "text/xml;charset=UTF-8")
            .print("""
                <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                                  xmlns:pret="http://soap.example.com/pret">
                    <soapenv:Body>
                        <pret:pretResponse>
                            <pret:isEligible>[boolean]</pret:isEligible>
                            <pret:reason>[string]</pret:reason>
                        </pret:pretResponse>
                    </soapenv:Body>
                </soapenv:Envelope>
            """)
        break
}
