# desafio

##Cidade

###Cadastrar Cidade (POST)
http://localhost:8080/cidade

Body (JSON):
{
    "nome": "Santos",
    "estado": "SP"
}

###Consultar cidade pelo estado (GET)
http://localhost:8080/cidade/estado/?sigla=SP

###Consultar cidade pelo nome (GET)
http://localhost:8080/cidade/nome?nome=Santos

##
##Cliente

###Cadastrar Cliente (POST)
http://localhost:8080/cliente

Body (JSON):
{
    "nome": "Renan",
    "sexo": "MASCULINO",
    "dataNascimento": "1996-08-27",
    "cidade": {
        "nome": "Santos",
        "estado": "SP"
    }
}

###Consultar cliente pelo id (GET)
http://localhost:8080/cliente/2

###Consultar cliente pelo nome (GET)
http://localhost:8080/cliente?nome=Renan

###Alterar nome cliente (PATCH)
http://localhost:8080/cliente/3

Body (text):
Adalberto

###Remover cliente (DELETE)
http://localhost:8080/cliente/2