# Por que trabalhar na Contabilizei

**Eleita a melhor startup B2B da América Latina em 2016, a Contabilizei** é um escritório de contabilidade online, líder de mercado, com sede em Curitiba (PR). Nosso propósito é resolver a dor e burocracia de micro e pequenas empresas ao se manterem regulares com o governo. Somos contadores, só que online, simples assim. Acreditamos no poder da tecnologia para melhorar continuamente a vida das pessoas. 

Se você tem espírito e comportamento empreendedor, muita disposição e proatividade para trabalhar em uma empresa em franca expansão, você é um forte candidato :)

Como Desenvolvedor Full-stack você irá atuar no desenvolvimento de soluções em arquitetura Java Web MVC com RestFul APis (JAX-RS), integrações com outros sistemas (SOAP, XML, JSON), banco de dados NoSQL e soluções escaláveis, participando de todo o processo de desenvolvimento, desde tomadas de decisões à codificação e testes.

### O que fazem os Ninjas da Contabilizei? O que comem (e bebem)? Onde vivem?

Somos um time de desenvolvimento ágil, focado em fazer as coisas acontecerem. Trabalhamos com Kanban, entregas contínuas, Git, Cloud, aplicações distribuídas e mais uma porrada de tecnologias novas... Queremos que nosso cliente tenha o produto e a experiência mais fodásticos do planeta. Gostamos de compartilhar ideias, testar tecnologias e de cerveja :)

# O trabalho por aqui

Que tal fazer parte de um time com atitude “get Fˆ%#ing things done”? Participar de uma das maiores disrupções no mercado? Ter a oportunidade de trabalhar com tecnologias e conceitos inovadores, como:
* Práticas ágeis como Kanban / Scrum
* Google Cloud Platform
* Escalabilidade
* Micro services e aplicações distribuídas
* Kubernetes
* Git
* AngularJs
* Material Design
* BDD

Mais informações sobre a vaga você encontra aqui: [Desenvolvedor Full Stack Java na Contabilizei](https://jobs.lever.co/contabilizei/826c32bd-d800-475a-9f05-531e86dc4ea3)

# O que preciso fazer?

Vamos ser práticos e diretos, se você quer trabalhar conosco siga os passos abaixo:

* Faça um "fork" desse projeto para sua conta GitHub.
* Implemente o desafio descrito no tópico abaixo.
* Faça um push para seu repositório com o desafio implementado.
* Envie um email para (souninja@contabilizei.com.br) avisando que finalizou o desafio com a url do seu fork.
* Cruze os dedos e aguarde nosso contato.

# O desafio (CRUD de pedidos)

Você deverá criar 2 aplicações para cadastramento de pedidos de venda: 

**Back-end:** aplicação JavaEE baseada em Web Services no padrão RESTful JAX-RS.

**Front-end:** Single Page Application que se comunique com estes serviços. 

A aplicação Back-end dever ter, ao menos, serviços para: inclusão, alteração, exclusão e listagem dos pedidos.

Pedidos deverão conter: 
* número
* data de emissão
* cliente
* lista de produtos
* valor total
 
Cada produto do pedido deve ter: 
* código
* descrição
* quantidade
* valor unitário

No pedido, deve constar as seguintes informações do cliente: 
* cpf ou cnpj
* nome ou razão social
* telefone
* e-mail

### Tecnologias

Escolha umas das opções abaixo para implementar sua solução. A modelagem dos dados fica a seu critério. Não se preocupe com autenticação ou multitenancy.

#### Back-end

**Opção 1**

* Aplicação JavaEE utilizando framework [**Google App Engine para Java**](https://cloud.google.com/appengine/)
* Banco de dados NOSQL [Datastore](https://cloud.google.com/datastore/)
* RESTFul API com [Google Endpoints](https://cloud.google.com/appengine/docs/java/endpoints/) ou Jersey JAX-RS

**Opção 2**

* Aplicação pura Java EE (não utilize Spring, Struts, EJB, etc)
* RESTful API JAX-RS utilizando Servlets ou framework Jersey
* Banco de dados SQL (MySQL, PostgreSQL, HSQLDB) com JPA ou NOSQL(MongoDB)

#### Front-end

* Single Page Application utilizando apenas HTML5 e CSS3 
* Javascript puro / JQuery (e plugins)
* AngularJS 1.x
* Bootstrap (http://getbootstrap.com/) ou Angular Material Design (será diferencial)

**Recomendações gerais:**

* Não utilize frameworks ou BD que não foram indicados
* Para servidor de aplicação utilize Jetty ou Tomcat (Não utilize: JBOSS, Wildfly ou qualquer outro servidor. Por quê? Critério de facilidade de configuração)
* Utilize o Maven para gerenciamento de dependências
* Utilizar automatizadores como Bower, Gulp, Grant é opcional.

### Arquitetura e documentação

No arquivo README do projeto explique o funcionamento e a arquitetura da solução adotada na sua implementação. Descreva também os passos para executar corretamente seu projeto.

### Avaliação

Entre os critérios de avaliação estão:

* Facilidade de configuração do projeto
* Performance
* Código limpo e organização
* Documentação de código
* Documentação do projeto (readme)
* Arquitetura
* Boas práticas de desenvolvimento
* Design Patterns

# Sobre você

Queremos saber um pouco mais sobre você também :) Por favor, responda o questionário do arquivo [questions.md](questions.md) e envie junto com seu projeto.

