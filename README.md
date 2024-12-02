# OAuth/OpenID

1. O fluxo do **OAuth** basicamente é, o **Resource Owner**, concede permissão ao **Client** para acessar seus dados.

2. O **Client** solicita um access token ao **Authorization Server**

3. O **Client** envia o Token ao **Resource Server** para acessar os dados. O **Resource Server** valida o token (verificando a assinatura e os dados dentro do token, como a expiração e o **issuerUri**) para garantir que o token é legítimo e que o **Client** tem
permissão para acessar os dados.


