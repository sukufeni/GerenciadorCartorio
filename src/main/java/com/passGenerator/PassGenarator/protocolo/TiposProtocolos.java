package com.passGenerator.PassGenarator.protocolo;

import java.util.List;
import java.util.Map;

// Convert to yml
public final class TiposProtocolos {
    
    public static List<Map<String,Long>>tipoProtocolo = List.of(

        // -------------- Tabelionato de Notas -----------------
        Map.of("Atos de Valor economico",1L),
        Map.of("Escritura de Convenção de condominio", 1L),
        Map.of("Atos sem valor economico", 1L),
        Map.of("Testamento", 1L),
        Map.of("Procuração E substabelecimento", 1L),
        Map.of("Certidão, traslado ou cópia de documentos arquivados", 1L),
        Map.of("Busca, incluida certidão negativa", 1L),
        Map.of("Reconhecimento de firma, letra ou sinal", 1L),
        Map.of("Autenticação de fotocópia", 1L),
        Map.of("Pública forma, por página", 1L),
        Map.of("Confecção e guarda do cartão de assinatura", 1L),
        Map.of("Ata Notarial", 1L),
        Map.of("Escrituras de divórcio, separação, dissolução de união estável e inventário sem partilha de bens e direitos.", 1L),
        Map.of("Escrituras de declaração de união estável e homoafetiva, de pacto antenupcial e contrato de namoro", 1L),
        Map.of("Escritura de divisão ou estremação", 1L),
        Map.of("Apostilamento de Haia", 1L),

        // ------------- Protesto de Títulos -----------------
        Map.of("Atos com Valor Econômico", 2L),
        Map.of("Certidões, na forma de página, relatório, listagem, boletim ou assemelhados, por qualquer meio, convencional ou magnético, por registro, fornecidas às instituições de proteção ao crédito.", 2L),
        Map.of("Certidão, por nome", 2L),
        Map.of("Cancelamento de protesto, por título ou documento", 2L),
        Map.of("Retirada do protesto, por título ou documento", 2L),
        Map.of("Sustação Judicial ou suspensão dos efeitos de protesto, por título ou documento", 2L),
        Map.of("Ato de distribuição, por título ou documento", 2L),

        // -------------- Registro Civil ---------------------
        Map.of("Habilitação de casamento e de conversão da união estável em casamento, incluindo-se preparo de papéis, lavratura do assento e a certidão da habilitação.", 3L),
        Map.of("Assento de casamento, a vista de certidão de habilitação de outro cartório.", 3L),
        Map.of("Registro ou inscrição de casamento religioso com efeito civil ou união estável.", 3L),
        Map.of("Emancipação, interdição, ausência, aquisição definitiva de nacionalidade brasileira.", 3L),
        Map.of("Transcrição de registro de nascimento, casamento ou óbito ocorridos no estrangeiro e averbação de sentença estrangeira de divórcio.", 3L),
        Map.of("Retificação ou averbação de assento, por documento ou mandado apresentado.", 3L),
        Map.of("Publicação de Editais de proclamas de outro cartório, incluída a fixação, o registro e o fornecimento da certidão respectiva, excluídas as despesas com publicação na imprensa.", 3L),
        Map.of("Certidão em geral ou cópia de documento arquivado", 3L),
        Map.of("Certidão em geral com busca", 3L),
        Map.of("Certidão de inteiro teor", 3L),
        Map.of("Busca, incluída a certidão negativa", 3L),
        Map.of("Registro de nascimento ou Óbito, incluída a 1ª certidão.", 3L)
    );

}
