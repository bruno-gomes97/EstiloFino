<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Conta Criada com Sucesso</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            color: #333;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
        }
        .container {
            max-width: 600px;
            margin: 20px auto;
            background: #fff;
            border: 1px solid #ddd;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }
        h1 {
            color: #4CAF50;
        }
        p {
            margin: 10px 0;
        }
        .footer {
            text-align: center;
            margin-top: 20px;
            font-size: 12px;
            color: #666;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Bem-vindo(a), ${nome}!</h1>
    <p>Sua conta foi criada com sucesso.</p>
    <p><strong>Seu ID de cliente:</strong> ${idCliente}</p>
    <p><strong>Email da nossa equipe:</strong> ${emailEmpresa}</p>
    <p>Estamos felizes em tê-lo(a) conosco! Se precisar de assistência, não hesite em entrar em contato com nosso suporte.</p>
    <p>Atenciosamente,</p>
    <p><strong>Equipe ${nomeDaEmpresa}</strong></p>
    <div class="footer">
        <p>Este é um email automático, por favor, não responda a este endereço.</p>
    </div>
</div>
</body>
</html>
