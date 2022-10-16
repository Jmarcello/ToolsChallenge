# API Tools Challenge
API Desenvolvida por João Marcello Cardoso de Almeida - 17/10/2022

**Tecniologias trabalhadas**

- JPA
- Hibernate
- JAVA 8
- Spring Boot
- Mock
- H2

**Informações Importantes**:

API protegida com Auth Basic.

**Credenciais**: 

user: toolschallenge

pass: tools123

Collection importada na pasta "Collection" com varios exemplos.

Também foi implementado o Swagger para conferir as rotas disponiveis. Para acessa-lo basta entrar na URL "http://localhost:8080/swagger-ui.html" quando estiver rodando o projeto.


A model de "pagamentoDTO", foi criada com o objetivo de seguir precisamente os exemplos enviados nas instruções no e-mail, assim como, algumas funcionalidades para esse mesmo princípio.

Diversas funções no código se encontram com comentários.

Foi criado um Handler para tratamento de erros. Além de diversas validações mais especificas na service de pagamento.

-------------------------------------------------------------------------------------------------

Listas de Rotas:
http://localhost:8080/pagamento/processar-pagamento POST (Processa um Pagamento e retona o Pagamento DTO Autorizado)

http://localhost:8080/pagamento/estornar/{id} PUT (Extorna um Pagamento DTO Autorizado por ID)

http://localhost:8080/pagamento/{id} GET (Retona um Pagamento DTO por ID)

http://localhost:8080/pagamento/listar GET (Retona a Lista Completa de Pagamentos DTO)

http://localhost:8080/pagamento/pagamento-estornado/{id} GET (Retona um Pagamento DTO Extornado por ID)



Para executar o código basta abrir a pasta target e execurtar o arquivo .jar e chamar as rotas via POSTMAN ou outros softwares com a mesma funcionalidade.
Rotas com Basic Auth com user: toolschallenge e pass: tools123

Em caso de qualquer dúvida ou para mais informações, me coloco para disposição:

- LinkedIn: https://br.linkedin.com/in/joaomarcellocardoso

- Celular: (21) 9 9318-4749

- Email: joaomarcellocardoso@gmail.com


