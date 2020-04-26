# nexfar-api

# Endpoint

  - /api/product/search
  
# Swagger

  - http://localhost:8080/swagger-ui.html
  
# Explicação da solução

  - Primeiro pegar os produtos que baseados no corpo passado na requisição. Caso a lista esteja vazia é retornado um erro 404
  pois não foi possível encontrar um produto com o nome específicado então não faria sentido continuar a execução
  - Pego as taxas para o cliente específicado no header da requisição
  - Pego os produtos restringidos para um cliente
  - Depois o que eu pensei foi em fazer a verificação para cada produto, se o mesmo estiver restringido pelo usuário, esse produto
  mão entra na lista de retorno, caso contrário é calculado a taxa para o produto e adicionado no objeto resposta.
