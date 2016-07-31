# StarStore
Teste para vaga de dev Fullstack na Contabilizei.

**StarStore** é o melhor gerenciador de pedidos de naves espaciais da galáxia.

## Serviços
1. Listagem de pedidos
2. Consulta de pedido
3. Atualização de pedido
4. Remoção de pedido

## Tecnologias
```
Java
Apache Tomcat 7
Mongodb 3.2.5
Angularjs 1.5
REST API Jersey
Bootstrap 4
HTML5
```

## Exemplo de documento de pedido armazenado no Mongodb
```
{
    "_id" : ObjectId("579d66434795871de83aefc2"),
    "numero" : 14,
    "data_de_emissao" : ISODate("2016-07-30T23:45:23.869-03:00"),
    "valor_total" : 50,
    "cliente" : {
        "cpf" : 123345,
        "nome" : "Luan Andrade",
        "email" : "luan@email.com",
        "telefone" : NumberLong(2433333333)
    },
    "produtos" : [ 
        {
            "codigo" : "ABC",
            "descricao" : "Nave 1",
            "quantidade" : 5,
            "valor_unitario" : 10
        }
    ]
}
```

## Diretórios
```
/contabilizei - Projeto em Java com a API REST
/starstore - Projeto em Angularjs da StarStore
/screens - imagens da aplicação
```

## Rodando
1. Abra o projeto **contabilizei** no Eclipse
2. Rode o projeto no servidor desejado
3. Abra a aplicação **starstore** no servidor e inicie
4. Navegue pelos menus

**Pode ser necessário definir credenciais de acesso ao seu banco de dados Mongodb!!**

## Comandos para testar a API (Pode ser feito via POSTMAN)
```
1. Retornar um pedido pelo número (GET)
curl "http://localhost:8080/contabilizei/rest/pedidos/2"

2. Retornar todos os pedidos (GET)
curl "http://localhost:8080/contabilizei/rest/pedidos/"

3. Salvando um pedido (POST)
curl -X POST -H "Accept: application/json" -H  -d '{"valorTotal":100,"produtos":[{"codigo":"ABC","descricao":"DEF","quantidade":10,"valorUnitario":9},{"codigo":"ABCDEF","descricao":"GHI","quantidade":19,"valorUnitario":7}],"cliente":{"cpf":1234567890,"nome":"Joao","telefone":33333333,"email":"eu@email.com"}}' "http://localhost:8080/contabilizei/rest/pedidos/"

3. Deletando pedido pelo numero (DELETE)
curl -X DELETE "http://localhost:8080/contabilizei/rest/pedidos/4"

4. Atualizando pedido (PUT)
curl -X POST -H "Accept: application/json" -H  -d '{"numero": 3,"valorTotal":101,"produtos":[{"codigo":"ABC","descricao":"DEF","quantidade":10,"valorUnitario":9},{"codigo":"ABCDEF","descricao":"GHI","quantidade":19,"valorUnitario":7}],"cliente":{"cpf":1234567890,"nome":"Joao","telefone":33333333,"email":"eu@email.com"}}' "http://localhost:8080/contabilizei/rest/pedidos/"

```
