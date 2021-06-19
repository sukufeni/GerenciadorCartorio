package com.passGenerator.PassGenarator.protocolo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class TipoProtocolo {
    
    public static List<Map<String,Long>>tipoProtocolo = List.of(
        Map.of("Atos de Valor economico",1L),
        Map.of("", 1L),
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

        Map.of("Atos com Valor Econômico", 2L),
        Map.of("Certidões, na forma de página, relatório, listagem, boletim ou assemelhados, por qualquer meio, convencional ou magnético, por registro, fornecidas às instituições de proteção ao crédito.", 2L),
        Map.of("Certidão, por nome", 2L),
        Map.of("Cancelamento de protesto, por título ou documento", 2L),
        Map.of("Retirada do protesto, por título ou documento", 2L),
        Map.of("Sustação Judicial ou suspensão dos efeitos de protesto, por título ou documento", 2L),
        Map.of("Ato de distribuição, por título ou documento", 2L)

    );

}
