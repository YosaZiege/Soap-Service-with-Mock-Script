

create:
	sudo docker run -ti -p 8080:8080 -v $(pwd)/soap-config:/opt/imposter/config outofcoffee/imposter
getWSDL:
	sudo wget http://localhost:8080/soap-pret/ws/pret.wsdl -O soap-config/pret.wsdl



.PHONY: create
