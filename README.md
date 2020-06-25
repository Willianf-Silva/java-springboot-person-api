### Reprodução do projeto desenvolvido na platadorma Digital Innovation One
Durante a live coding foi desenvolvido por [Rodrigo Peleias](https://github.com/rpeleias "Rodrigo Peleias") um pequeno sistema para o gerenciamento de pessoas de uma empresa através de uma API REST.

- Disponível no link o [Projeto Original](https://github.com/rpeleias/personapi_dio_live_coding "Projeto Original")

# 
##### Proposta para evolução do projeto
- Desenvolver um serviço para pequenas empresas gerenciar a fila de atedimento.

# 
##### Neste projeto foi abordado os seguintes tópicos:
- Operações para gerenciamento de usuários, atendentes, produtos e senhas utilizando  o padrão arquitetural REST (Cadastro, leitura, atualização e remoção) do sistema.
- Controle de versão através do Github.
- Implantação do sistema na nuvem através do Heroku.

# 
São necessários os seguintes pré-requisitos para a execução do projeto
- Java 11 ou versões superiores.
- Maven 3.6.3 ou versões superiores.
- Intellj IDEA Community Edition ou sua IDE favorita.

# 
Para executar o projeto no terminal, digite o seguinte comando:

`mvn spring-boot:run `

Após executar o comando acima, basta apenas abrir os seguintes endereços e visualizar a execução do projeto:

`http://localhost:8080/api/v1/people`
`http://localhost:8080/api/v1/attendant`
`http://localhost:8080/api/v1/product`
`http://localhost:8080/api/v1/productItem`
`http://localhost:8080/api/v1/password`

# 
Script para inputar os dados utilizando postman:

POST `http://localhost:8080/api/v1/product`
```json
{
	"name": "Barba",
	"description": "Fazer a barba.",
	"value": "10.99"
}
```

POST `http://localhost:8080/api/v1/people`
```json
{
	"firstName": "Rodrigo",
	"lastName": "Silva",
	"cpf": "409.251.368-24",
	"birthDate": "23-03-1988",
	"phones": [
		{
			"type":"MOBILE",
			"number": "(19)999999999"
		}
		]
}
```

POST `http://localhost:8080/api/v1/attendant`
```json
{
	"firstName": "Willian",
	"lastName": "Silva",
	"cpf": "409.251.368-24",
	"function": "barbeiro",
	"phones": [
		{
			"type":"MOBILE",
			"number": "(19)999999999"
		}
		]
}
```

POST `http://localhost:8080/api/v1/productItem`
```json
{	
	"quantity": "2",	
    "productDTO":{
        "id": 1,
        "name": "Barba",
        "description": "Fazer a barba.",
        "value": 10.99
    }
}
```

POST `http://localhost:8080/api/v1/password`
```json
{
	"status": "Pendente",
	"cost": 10.99,
	"date": "23-03-1988",
	"person":
		{
        "id": 1,
        "firstName": "Rodrigo",
        "lastName": "Silva",
        "cpf": "369.333.878-79",
        "birthDate": "1988-03-23",
        "phones": [
            {
                "id": 1,
                "type": "MOBILE",
                "number": "(19)999999999"
            }
        ]
		},
	"attendant":{
        "id": 1,
        "firstName": "Willian",
        "lastName": "Silva",
        "cpf": "409.251.368-24",
        "function": "barbeiro",
        "phones": [
            {
                "id": 2,
                "type": "MOBILE",
                "number": "(19)999999999"
            }
        ]
		},
	"productItems": [
		{
        	"id": 1,
        	"quantity": 2,
        	"totalValue": 21.98,
        	"productDTO": {
            "id": 1,
            "name": "Barba",
            "description": "Fazer a barba.",
            "value": 10.99
        }
		}
		]
}
```
