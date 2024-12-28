<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pagamento Confirmado</title>
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
        .address-section {
            background-color: #f1f1f1;
            border-radius: 8px;
            padding: 15px;
            margin-top: 20px;
            border: 1px solid #ddd;
        }
        .address-section h2 {
            color: #333;
            font-size: 18px;
            margin-bottom: 10px;
        }
        .address-section p {
            margin: 5px 0;
        }
        .footer {
            text-align: center;
            margin-top: 20px;
            font-size: 12px;
            color: #666;
        }
        .footer p {
            margin: 5px 0;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Obrigado pelo pagamento, ${nome}!</h1>
    <p>Estamos felizes em confirmar o recebimento do seu pagamento.</p>

    <h3>Resumo do Pedido</h3>
    <p><strong>Total de itens:</strong> ${totalItens}</p>
    <p><strong>Total pago:</strong> R$ ${totalPago}</p>

    <div class="address-section">
        <h2>Endereço de Entrega</h2>
        <p><strong>Estado:</strong> ${estado}</p>
        <p><strong>Cidade:</strong> ${cidade}</p>
        <p><strong>Bairro:</strong> ${bairro}</p>
        <p><strong>Logradouro:</strong> ${logradouro}, ${numero}</p>
        <p><strong>CEP:</strong> ${cep}</p>
    </div>

    <p>Se você tiver dúvidas ou precisar de ajuda, não hesite em nos contatar.</p>
    <p>Atenciosamente,</p>
    <p><strong>Equipe ${nomeDaEmpresa}</strong></p>

    <div class="footer">
        <p>Este é um email automático, por favor, não responda a este endereço.</p>
    </div>
</div>
</body>
</html>
