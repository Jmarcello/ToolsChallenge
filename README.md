# api_rrp_joao_marcello
API para trabalho de RRP do João Marcello  27/11/2021
Atualização na Model principal + Collection importada na pasta Collection com varios exemplos.

Listas de Rotas:
http://localhost:8080/listar GET (Listar Tudo)

/buscar/{id} GET (Busca por Id) GET (Busca Por Id)

/listar-cumpridas GET (Lista as tarefas cumpridas)

/listar-nao-cumpridas GET (Lista as tarefas não cumpridas)

/salvar POST (Salva as tarefas)

/modificar-cumprida/{id} PATCH (Modifica o status da tarefa para cumprida ou não)

/delete/{id} DELETE (Deleta as tarefas por Id)

/cancelar/{id} PATCH (Cancela as tarefas por Id)

/atualizar/{id} PUT (Cancela as tarefas por Id)

Código utilizando conceitos REST com JPA e H2 para facilitar o uso rápido

Para executar o código basta abrir a pasta target e execurtar o arquivo .jar e chamar as rotas via POSTMAN ou outros softwares com a mesma funcionalidade.
Rotas com Basic Auth com user: root e pass: root123

para mais informações ou dúvidas pode entrar em contato
