# SCSP

Sistema de Controle Servidor Público

# Dados

- Nome  - Giovanny Montinny de Almeida Dantas;
- Email - gmontinny@gmail.com;
- CPF   - 817.699.971-72

## Informação sobre uso da API

- Foi utilizado o POSTMAN para exibir resultado das APIs
- Foi utilizado o docker-compose junto com Dockerfile para levantar as imagens.
- Dockerfile enviar o arquivo scsp.jar para imagem de execução da API
- Para consumir as APIs é necessario passar o TOKEN de autênticação.
- Para uso do swagger na url `http://localhost:8080/swagger-ui/index.html`
- A hierarquia do sistema está dividido em;


+ br.gov.mt.controladoria.scsp
    + component 
    + config
    + event
    + exception
    + mapper
    + model
    + repository
    + resource
    + security
    + service
    + util
    

## DOCKER 
#### Comando para usar docker-compose

`$ docker-compose up -d`


## Documentação da API

#### Configuração para usar o TOKEN de Autênticação

```http
  POST /user/login
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `user`    | `string` | **Obrigatório**. |
| `password` | `string` | **Obrigatório**. |


[![Alt-Text](/imagens/img_token.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_token.jpg)


#### Configuração para usar o TOKEN de Refresh

```http
  POST /user/refresh-token
```

| Parâmetro | Tipo       | Descrição                           |
|:----------| :--------- | :---------------------------------- |
| `token`   | `string` | **Obrigatório**. |



[![Alt-Text](/imagens/img_token.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_refresh_token.jpg)



#### API Cidades - Listar Todas

```http
  GET /cidades
```

[![Alt-Text](/imagens/img_cidade_get.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_cidade_get.jpg)

#### API Cidades - Paginação

```http
  GET /cidades?page=0&size=3
```

[![Alt-Text](/imagens/img_cidade_paginate.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_cidade_paginate.jpg)


#### API Cidades - Gravar

```http
  POST /cidades
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `nomeCidade`       | `string` | **Obrigatório**. |
| `ufCidade`       | `string` | **Obrigatório**. |

[![Alt-Text](/imagens/img_cidade_post.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_cidade_post.jpg)


#### API Cidades - Atualizar

```http
  PUT /cidades/{codigo}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `codigo`       | `int` | **Obrigatório**. |


[![Alt-Text](/imagens/img_cidade_put.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_cidade_put.jpg)	
	

#### API Cidades - Deletar

```http
  DELETE /cidades/{codigo}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `codigo`       | `int` | **Obrigatório**. |


[![Alt-Text](/imagens/img_cidade_delete.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_cidade_delete.jpg)	

#### API Endereço - Listar Todas

```http
  GET /enderecos
```

[![Alt-Text](/imagens/img_endereco_get.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_endereco_get.jpg)

#### API Endereço - Paginação

```http
  GET /enderecos?page=0&size=3
```

#### API Endereço - Gravar

```http
  POST /enderecos
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `tipoLogradouroEndereco`       | `string` | **Obrigatório**. |
| `logradouroEndereco`       | `string` | **Obrigatório**. |
| `numeroEndereco`       | `int` | **Obrigatório**. |
| `bairroEndereco`       | `string` | **Obrigatório**. |
| `cidade`       | `Cidade` | **Obrigatório**. |

[![Alt-Text](/imagens/img_endereco_post.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_endereco_post.jpg)


#### API Endereços - Atualizar

```http
  PUT /enderecos/{codigo}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `codigo`       | `int` | **Obrigatório**. |


[![Alt-Text](/imagens/img_endereco_put.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_endereco_put.jpg)	
	

#### API Endereços - Deletar

```http
  DELETE /enderecos/{codigo}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `codigo`       | `int` | **Obrigatório**. |


[![Alt-Text](/imagens/img_endereco_delete.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_endereco_delete.jpg)	


#### API File - Upload de Arquivo

```http
  POST /upload
```

#### Observação

> Você tem que selecionar BODY e marcar form-data e também tem que selecionar FILE ao invés de TEXT


| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `file`       | `File` | **Obrigatório**. |
| `title`       | `string` | **Obrigatório**. |
| `description`       | `string` | **Obrigatório**. |

[![Alt-Text](/imagens/img_file_post.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_file_post.jpg)


#### API File - Exibir Aquivo

```http
  GET /file/{arquivo}
```

#### Observação

> Vai ser exibido o formato stream do arquivo

[![Alt-Text](/imagens/img_file_get.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_file_get.jpg)


#### API FotoPessoa - Listar Todas

```http
  GET /fotoPessoas
```

[![Alt-Text](/imagens/img_fotopessoa_get.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_fotopessoa_get.jpg)

#### API FotoPessoa - Paginação

```http
  GET /fotoPessoas?page=0&size=3
```

#### API FotoPessoa - Gravar

```http
  POST /fotoPessoas
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `pessoa`       | `Pessoa` | **Obrigatório**. |
| `dataFotoPessoa`       | `date` | **Obrigatório**. |
| `bucketFotoPessoa`       | `string` | **Obrigatório**. |
| `hashFotoPessoa`       | `string` | **Obrigatório**. |


[![Alt-Text](/imagens/img_fotopessoa_post.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_fotopessoa_post.jpg)


#### API FotoPessoa - Atualizar

```http
  PUT /fotoPessoas/{codigo}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `codigo`       | `int` | **Obrigatório**. |


[![Alt-Text](/imagens/img_fotopessoa_put.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_fotopessoa_put.jpg)
	

#### API FotoPessoa - Deletar

```http
  DELETE /fotoPessoas/{codigo}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `codigo`       | `int` | **Obrigatório**. |


[![Alt-Text](/imagens/img_fotopessoa_delete.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_fotopessoa_delete.jpg)	

 
 #### API Lotação - Listar Todas

```http
  GET /lotacoes
```

[![Alt-Text](/imagens/img_lotacao_get.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_lotacao_get.jpg)

#### API Lotação - Paginação

```http
  GET /lotacoes?page=0&size=3
```


#### API Lotação - Gravar

```http
  POST /lotacoes
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `pessoa`       | `Pessoa` | **Obrigatório**. |
| `unidade`       | `Unidade` | **Obrigatório**. |
| `dataLotacao`       | `date` | **Obrigatório**. |
| `dataRemocao`       | `date` | **Obrigatório**. |
| `portariaLotacao`  | `string` | **Obrigatório**. |


[![Alt-Text](/imagens/img_lotacao_post.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_lotacao_post.jpg)


#### API Lotação - Atualizar

```http
  PUT /lotacoes/{codigo}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `codigo`       | `int` | **Obrigatório**. |


[![Alt-Text](/imagens/img_lotacao_put.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_lotacao_put.jpg)
	

#### API Lotação - Deletar

```http
  DELETE /lotacoes/{codigo}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `codigo`       | `int` | **Obrigatório**. |


[![Alt-Text](/imagens/img_lotacao_delete.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_lotacao_delete.jpg)


 #### API Pessoa Endereço - Listar Todas

```http
  GET /pessoaEnderecos
```

[![Alt-Text](/imagens/img_pessoaendereco_get.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_pessoaendereco_get.jpg)

#### API Pessoa Endereço - Paginação

```http
  GET /pessoaEnderecos?page=0&size=3
```


#### API Pessoa Endereço - Gravar

```http
  POST /pessoaEnderecos
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `pessoa`       | `Pessoa` | **Obrigatório**. |
| `endereco`    | `Endereco` | **Obrigatório**. |


[![Alt-Text](/imagens/img_pessoaendereco_post.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_pessoaendereco_post.jpg)


#### API Pessoa Endereço - Atualizar

```http
  PUT /pessoaEnderecos/pessoa/{idpessoa}/endereco/{idendereco}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `idpessoa`       | `int` | **Obrigatório**. |
| `idendereco`       | `int` | **Obrigatório**. |


[![Alt-Text](/imagens/img_pessoaendereco_put.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_pessoaendereco_put.jpg)	
	

#### API Pessoa Endereço - Deletar

```http
  DELETE /pessoaEnderecos/pessoa/{idpessoa}/endereco/{idendereco}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `idpessoa`       | `int` | **Obrigatório**. |
| `idendereco`       | `int` | **Obrigatório**. |


[![Alt-Text](/imagens/img_pessoaendereco_delete.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_pessoaendereco_delete.jpg)		


 #### API Pessoas - Listar Todas

```http
  GET /pessoas
```

[![Alt-Text](/imagens/img_pessoa_get.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_pessoa_get.jpg)	

#### API Pessoas - Paginação

```http
  GET /pessoas?page=0&size=3
```


#### API Pessoas - Gravar

```http
  POST /pessoas
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `nomePessoa`       | `string` | **Obrigatório**. |
| `dataNascimentoPessoa`    | `date` | **Obrigatório**. |
| `sexoPessoa`    | `string` | **Obrigatório**. |
| `maePessoa`    | `string` | **Obrigatório**. |
| `paiPessoa`    | `string` | **Obrigatório**. |


[![Alt-Text](/imagens/img_pessoa_post.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_pessoa_post.jpg)	


#### API Pessoas - Atualizar

```http
  PUT /pessoas/{codigo}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `codigo`       | `int` | **Obrigatório**. |



[![Alt-Text](/imagens/img_pessoa_put.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_pessoa_put.jpg)	
	

#### API Pessoas - Deletar

```http
  DELETE /pessoas/{codigo}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `codigo`       | `int` | **Obrigatório**. |


[![Alt-Text](/imagens/img_pessoa_delete.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_pessoa_delete.jpg)	


 #### API Servidor Efetivo - Listar Todos

```http
  GET /servidorEfetivo
```

[![Alt-Text](/imagens/img_servidorefetivo_get.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_servidorefetivo_get.jpg)	

#### API Servidor Efetivo - Mostra a pessoa lotada na unidade pelo codigo

```http
  GET /servidorEfetivo/{idUnidade}/unidade
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `idUnidade`       | `int` | **Obrigatório**. |

[![Alt-Text](/imagens/img_servidorefetivo_unidade.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_servidorefetivo_unidade.jpg)	


#### API Servidor Efetivo - Mostra o endereço pelo nome da pessoa

```http
  GET /servidorEfetivo/{nome}/endereco
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `nome`       | `string` | **Obrigatório**. |

[![Alt-Text](/imagens/img_servidorefetivo_endereco.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_servidorefetivo_endereco.jpg)	


#### API Servidor Efetivo - Gravar

```http
  POST /servidorEfetivo
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `seMatriculaServidorEfetivo`       | `string` | **Obrigatório**. |
| `pessoa`    | `Pessoa` | **Obrigatório**. |


[![Alt-Text](/imagens/img_servidorefetivo_post.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_servidorefetivo_post.jpg)	


#### API Servidor Efetivo - Atualizar

```http
  PUT /servidorEfetivo/{matricula}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `matricula`  | `string` | **Obrigatório**. |



[![Alt-Text](/imagens/img_servidorefetivo_put.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_servidorefetivo_put.jpg)		
	

#### API Servidor Efetivo - Deletar

```http
  DELETE /servidorEfetivo/{matricula}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `matricula`  | `string` | **Obrigatório**. |


[![Alt-Text](/imagens/img_servidorefetivo_delete.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_servidorefetivo_delete.jpg)	
	
 #### API Servidor Temporario - Listar Todas

```http
  GET /servidorTemporario
```

[![Alt-Text](/imagens/img_servidortemporario_get.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_servidortemporario_get.jpg)	

#### API Servidor Temporario - Paginação

```http
  GET /servidorTemporario?page=0&size=3
```

#### API Servidor Temporario - Gravar

```http
  POST /servidorTemporario
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `pessoa`       | `Pessoa` | **Obrigatório**. |
| `dataAdmissao`    | `date` | **Obrigatório**. |
| `dataDemissao`    | `date` | **Obrigatório**. |

[![Alt-Text](/imagens/img_servidortemporario_post.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_servidortemporario_post.jpg)	


#### API Servidor Temporario - Atualizar

```http
  PUT /servidorTemporario/{codigo}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `codigo`       | `int` | **Obrigatório**. |



[![Alt-Text](/imagens/img_servidortemporario_put.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_servidortemporario_put.jpg)		
	

#### API Servidor Temporario - Deletar

```http
  DELETE /servidorTemporario/{codigo}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `codigo`       | `int` | **Obrigatório**. |


[![Alt-Text](/imagens/img_servidortemporario_delete.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_servidortemporario_delete.jpg)	


 #### API Unidade Endereço - Listar Todas

```http
  GET /unidadeEnderecos
```

[![Alt-Text](/imagens/img_unidadeendereco_get.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_unidadeendereco_get.jpg)	

#### API Unidade Endereço - Paginação

```http
  GET /unidadeEnderecos?page=0&size=3
```



#### API Unidade Endereço - Gravar

```http
  POST /unidadeEnderecos
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `unidade`       | `Unidade` | **Obrigatório**. |
| `endereco`    | `Endereco` | **Obrigatório**. |

[![Alt-Text](/imagens/img_unidadeendereco_post.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_unidadeendereco_post.jpg)


#### API Unidade Endereço - Atualizar

```http
  PUT /unidadeEnderecos/unidade/{idUnidade}/endereco/{idEndereco}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `idUnidade`       | `int` | **Obrigatório**. |
| `idEndereco`       | `int` | **Obrigatório**. |



[![Alt-Text](/imagens/img_unidadeendereco_put.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_unidadeendereco_put.jpg)
	

#### API Unidade Endereço - Deletar

```http
  DELETE /unidadeEnderecos/unidade/{idUnidade}/endereco/{idEndereco}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `idUnidade`       | `int` | **Obrigatório**. |
| `idEndereco`       | `int` | **Obrigatório**. |


[![Alt-Text](/imagens/img_unidadeendereco_delete.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_unidadeendereco_delete.jpg)

 #### API Unidade - Listar Todas

```http
  GET /unidades
```

[![Alt-Text](/imagens/img_unidade_get.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_unidade_get.jpg)

#### API Unidade - Paginação

```http
  GET /unidades?page=0&size=3
```


#### API Unidade  - Gravar

```http
  POST /unidades
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `nomeUnidade`       | `String` | **Obrigatório**. |
| `siglaUnidade`    | `String` | **Obrigatório**. |

[![Alt-Text](/imagens/img_unidade_post.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_unidade_post.jpg)


#### API Unidade - Atualizar

```http
  PUT /unidades/{codigo}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `codigo`       | `int` | **Obrigatório**. |


[![Alt-Text](/imagens/img_unidade_put.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_unidade_put.jpg)




#### API Unidade - Deletar

```http
  DELETE /unidades/{codigo}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `codigo`       | `int` | **Obrigatório**. |


[![Alt-Text](/imagens/img_unidade_put.jpg)](https://git2.pjc.mt.gov.br/gmontinny/sistema-de-controle-de-servidores-publico/-/blob/master/imagens/img_unidade_delete.jpg)

