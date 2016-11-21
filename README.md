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

# O Desafio de Notas Fiscais e Cálculo de Impostos

Você deverá criar 2 aplicações para cadastramento de notas fiscais e cálculo de impostos:

**Back-end:** aplicação JavaEE baseada em Web Services no padrão RESTful JAX-RS.

**Front-end:** Single Page Application que se comunique com estes serviços.

**Requisitos:**

- Permitir o cadastro de clientes (empresas)
- Permitir o registro de notas fiscais emitidas
- Realizar o cálculo dos impostos devidos por mês e ano de referência
- Permitir a consulta de notas fiscais e impostos por mês e ano de referência
- Permitir marcar o imposto como PAGO

O cadastro do cliente da Contabilizei deve conter as seguintes informações:

* Razão Social
* CNPJ
* Regime Tributário (Simples Nacional ou Lucro Presumido)
* Anexos (no caso de Regime Simples Nacional)
* E-mail

O cliente da Contabilizei deve registrar as notas fiscais para que seus impostos sejam calculados mensalmente. 

As Notas Fiscais contém as seguintes informações:

* Numero da Nota fiscal
* Data de emissão
* Descrição (Apenas texto. Pode ser um serviço prestado, um produto vendido ou produzido)
* Valor
* Anexo (1,2,3)

Quando o cliente terminar de lançar suas notas fiscais, ele poderá solicitar o cálculo de seus impostos do mês. Cada mês deverá ter apenas 1 imposto de cada tipo. Os impostos deverão ter as seguintes informações:

* Tipo de Imposto (Simples Nacional, Imposto de Renda, ISS, Cofins)
* Vencimento
* Valor
* Mês e ano de referência (por exemplo, 10/2016)
* Pago ou não

**Regras para o cálculo dos impostos:**

- Se a empresa for do Simples Nacional, somente o imposto Simples Nacional é calculado. Para isso, somam-se as notas fiscais do mês por anexo e aplicam-se, para cada anexo as alíquotas conforme a tabela abaixo e criamos esse imposto.

| Anexo                     | Alíquota de imposto  |
| --------------------------|:---------------------|
| 1 - Comércio              | 6%                   |
| 2 - Indústria             | 8,5%                 |
| 3 - Prestação de serviços | 11%                  |

Exemplo:

| Número da nota | Valor      | Anexo | Valor x Alíquota |
| ---------------|:-----------|:------|:-----------------|
| 001            | 1.000,00   | 1     | 60,00            |
| 002            | 5.000,00   | 3     | 550,00           |

**Total Imposto Simples Nacional = R$ 610,00**

Neste caso, será gerado 1 imposto do Tipo Simples Nacional cujo valor será R$ 610,00

- Se a empresa for do Lucro Presumido, será necessário calcular o IRPJ, o ISS e o Cofins. Então, desconsideramos o anexo, somamos todas as notas fiscais do mês, aplicamos as alíquotas abaixo e criamos os impostos (IRPJ, ISS e Cofins).

| Tipo do imposto           | Alíquota de imposto  |
| --------------------------|:---------------------|
| IRPJ                      | 4,8%                 |
| ISS                       | 2%                   |
| COFINS                    | 3%                   |

Exemplo:

| Número da nota | Valor      | IRPJ      | ISS    |COFINS      |
| ---------------|:-----------|:----------|:-------|:-----------|
| 001            | 1.000,00   | 48,00     | 20,00  |30,00       |
| 002            | 500,00     | 24,00     | 10,00  |15,00       |

**Total de impostos Lucro Presumido: IRPJ R$ 72,00, ISS R$ 30,00, COFINS R$ 45,00**

Neste caso, serão gerados 3 impostos (IR, ISS e Cofins) cujos valores serão (R$ 72, R$ 30 e R$ 45)


Após o cálculo dos impostos, o cliente ainda poderá consultar quais impostos está devendo por mês e marcar os impostos pagos.

### Tecnologias

Escolha umas das opções abaixo para implementar sua solução. A modelagem dos dados fica a seu critério. Não se preocupe com autenticação ou multitenancy.

#### BACK-END

**Opção 1**

* Aplicação JavaEE utilizando framework [**Google App Engine para Java**](https://cloud.google.com/appengine/)
* Banco de dados NOSQL [Datastore](https://cloud.google.com/datastore/)
* RESTFul API com [Google Endpoints](https://cloud.google.com/appengine/docs/java/endpoints/) ou Jersey JAX-RS

**Opção 2**

* Aplicação pura Java EE (não utilize Spring, Struts, EJB, etc)
* RESTful API JAX-RS utilizando Servlets ou framework Jersey
* Banco de dados SQL (MySQL, PostgreSQL, HSQLDB) com JPA ou NOSQL(MongoDB)

#### FRONT-END

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

#### Bonus

Se voce fosse utilizar esse sistema comercialmente, que alterações vc faria para escalar e/ou facilitar a vida do usuario? OBS: Voce pode descrever isso aqui ou mostrar na implementação.

# Sobre você

Queremos saber um pouco mais sobre você também :) Por favor, responda o questionário do arquivo [questions.md](questions.md) e envie junto com seu projeto.

