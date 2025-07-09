switch (context.request.queryParams.action) {
    case 'create':
        respond()
            .withStatusCode(200)
            .withHeader("Content-Type", "text/xml;charset=UTF-8")
            .withFile('create-response.xml')  
            .skipDefaultBehaviour()
        break

    case 'fetch':
        respond()
            .withStatusCode(400)
            .withHeader("Content-Type", "text/xml;charset=UTF-8")
            .withFile('fetch-error.xml')   
            .skipDefaultBehaviour()
        break

    default:
        respond()
            .withStatusCode(200)
            .withHeader("Content-Type", "text/xml;charset=UTF-8")
            .withFile('default-response.xml')  
            .skipDefaultBehaviour()
        break
}
