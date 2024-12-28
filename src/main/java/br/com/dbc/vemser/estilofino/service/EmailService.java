package br.com.dbc.vemser.estilofino.service;


import br.com.dbc.vemser.estilofino.entity.Carrinho;
import br.com.dbc.vemser.estilofino.entity.Cliente;
import br.com.dbc.vemser.estilofino.entity.Endereco;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {
    @Value("${spring.mail.username}")
    private String de;

    @Value("${spring.mail.username}")
    private String para;

    private final JavaMailSender emailSender;
    private final FreeMarkerConfigurer fmConfiguration;

    public EmailService(JavaMailSender emailSender, FreeMarkerConfigurer fmConfiguration){
        this.emailSender = emailSender;
        this.fmConfiguration = fmConfiguration;
    }

    public void sendEmail(TipoEmail tipoEmail, Cliente cliente, Carrinho carrinho, Endereco endereco) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(de);
            mimeMessageHelper.setTo(para);
            if(tipoEmail == TipoEmail.PAGAMENTO){
                mimeMessageHelper.setSubject("EstiloFino - Pagamento concluído");
            } else {
                mimeMessageHelper.setSubject("EstiloFino - Criação de conta concluída");
            }
            mimeMessageHelper.setText(getContentFromTemplate(tipoEmail, cliente, carrinho, endereco), true);

            emailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    private String getContentFromTemplate(TipoEmail tipoEmail, Cliente cliente, Carrinho carrinho, Endereco endereco) throws IOException, TemplateException {
        Template template = null;
        switch (tipoEmail) {
            case CRIACAO -> template = fmConfiguration.getConfiguration().getTemplate("email-criacao.ftl");
            case PAGAMENTO -> template = fmConfiguration.getConfiguration().getTemplate("email-pagamento.ftl");
        }
        Map<String, Object> dados = new HashMap<>();
        if(carrinho != null) {
            dados.put("totalItens", carrinho.getQuantidadeItens());
            dados.put("totalPago", carrinho.getTotal());
        }
        if(endereco != null){
            dados.put("estado", endereco.getEstado());
            dados.put("cidade", endereco.getCidade());
            dados.put("bairro", endereco.getBairro());
            dados.put("logradouro", endereco.getLogradouro());
            dados.put("numero", endereco.getNumero());
            dados.put("cep", endereco.getCep());
        }
        dados.put("nome", cliente.getNome());
        dados.put("emailEmpresa", de);
        dados.put("nomeDaEmpresa", "EstiloFino");
        dados.put("idCliente", cliente.getIdCliente());
        return FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);
    }
}