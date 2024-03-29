# Sobre o Projeto

Trata-se de um projeto simples CRUD consumindo API Rest através do Front End React JS.


# Tecnologias utilizadas

IDE VS Code
IDE Intelij
Java SpringBoot
ReactJS
Postman
Git/GitHub
MySQL Workbench

# Back End

1 - Está dividido nas camadas, model, repository, service e controller
2 - Possui 2 classes pojo, empresa e fornecedor com mapeamento de relacionamento N para N
3 - Persistindo os dados no banco de dados MySQL.

# Front End

# Iniciar Projeto

1 - npm install
2 - npm start - Executando na porta http://localhost:3000/
3 - Possui tela principal que apartir dela chama as outras através dos botões, cadastrar empres, cadastrar fornecedor, buscar empresa e buscar fornecedor que exibe os dados de cada 1 consultando pelo cnpj ou nome.
4 - Não permite campos vazios, cnpj deve ser preenchido no padrão correto e email tbm.

# Rotas de consumo das Entidades

Empresa: 

Listar todas empresas: http://localhost:8080/empresa/listar
Buscar empresa pelo Id: http://localhost:8080/empresa/buscarPorId/{id}
Buscar empresa pelo CNPJ: http://localhost:8080/empresa/buscarPorCnpj/{cnpj}
Salvar empresa: http://localhost:8080/empresa/salvar
Atualizar empresa: http://localhost:8080/empresa/atualizar/{id}
Deletar empresa: http://localhost:8080/empresa/deletar/{id}

Fornecedor:

Listar todos fornecedores: http://localhost:8080/fornecedor/listar
Buscar fornecedor pelo Id: http://localhost:8080/fornecedor/buscarPorId/{id}
Buscar fornecedor pelo Nome: http://localhost:8080/fornecedor/buscarPo/{nome}
Buscar fornecedor pelo CNPJ: http://localhost:8080/fornecedor/buscarPorCnpj/{cnpj}
Salvar fornecedor: http://localhost:8080/fornecedor/salvar
Salvar Fornecedor/Empresa: http://localhost:8080/fornecedor/fornecedorEmpresa/{empresaId}/salvar
Atualizar fornecedor: http://localhost:8080/fornecedor/atualizar/{id}
Deletar fornecedor: http://localhost:8080/fornecedor/deletar/{id}

